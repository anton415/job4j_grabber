# job4j_grabber
Job aggregator

[![Build Status](https://app.travis-ci.com/anton415/job4j_grabber.svg?branch=main)](https://app.travis-ci.com/anton415/job4j_grabber)
[![codecov](https://codecov.io/gh/anton415/job4j_grabber/branch/main/graph/badge.svg?token=NVPARHNG2R)](https://codecov.io/gh/anton415/job4j_grabber)

##Description.

The system starts on schedule. The launch period is specified in the settings - app.properties.
The first site will be career.habr.com. It has a section https://career.habr.com/vacancies/java_developer.
He will work with him. The program should read all vacancies related to Java and write them to the database.
Access to the interface will be through the REST API.

###Quartz.
Quartz is a richly featured, open source job scheduling library that can be integrated within 
virtually any Java application - from the smallest stand-alone application to the largest 
e-commerce system. Quartz can be used to create simple or complex schedules for executing tens, 
hundreds, or even tens-of-thousands of jobs; jobs whose tasks are defined as standard Java 
components that may execute virtually anything you may program them to do. The Quartz Scheduler 
includes many enterprise-class features, such as support for JTA transactions and clustering.

Quartz is freely usable, licensed under the Apache 2.0 license.