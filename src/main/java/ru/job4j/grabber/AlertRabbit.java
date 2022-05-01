package ru.job4j.grabber;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

public class AlertRabbit {
    private static final Logger LOG = LoggerFactory.getLogger(AlertRabbit.class.getName());
    private static Properties properties;
    private static Connection connection;

    public static void main(String[] args) {
        initProperties();
        initConnection();
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            JobDataMap data = new JobDataMap();
            data.put("connection", connection);
            JobDetail job = newJob(Rabbit.class)
                    .usingJobData(data)
                    .build();
            int rabbitScheduleInterval = Integer.parseInt(properties.getProperty("schedule.interval"));
            SimpleScheduleBuilder times = simpleSchedule()
                    .withIntervalInSeconds(rabbitScheduleInterval)
                    .repeatForever();
            Trigger trigger = newTrigger()
                    .startNow()
                    .withSchedule(times)
                    .build();
            scheduler.scheduleJob(job, trigger);
            int sleepInterval = Integer.parseInt(properties.getProperty("sleep.interval"));
            Thread.sleep(sleepInterval);
            scheduler.shutdown();
        } catch (SchedulerException | InterruptedException exception) {
            LOG.error(exception.getMessage());
        }
    }

    public static class Rabbit implements Job {
        public Rabbit() {
            LOG.info(String.valueOf(hashCode()));
        }

        @Override
        public void execute(JobExecutionContext context) {
            LOG.info("Rabbit runs here ...");
            Connection connection = (Connection) context.getJobDetail().getJobDataMap().get("connection");
            try (PreparedStatement prepareStatement = connection.prepareStatement("insert into rabbit (created_date) values (current_timestamp)")) {
                prepareStatement.execute();
            } catch (SQLException exception) {
                LOG.error(exception.getMessage());
            }
        }
    }

    public static void initProperties() {
        try (InputStream inputStream = AlertRabbit.class.getClassLoader().getResourceAsStream("rabbit.properties")) {
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException exception) {
            LOG.error(exception.getMessage());
        }
    }

    public static void initConnection() {
        try {
            Class.forName(properties.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("username"),
                    properties.getProperty("password")
            );
        } catch (ClassNotFoundException | SQLException exception) {
            LOG.error(exception.getMessage());
        }
    }
}