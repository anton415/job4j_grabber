# job4j_grabber
Агрегатор вакансий

[![Build Status](https://app.travis-ci.com/anton415/job4j_grabber.svg?branch=main)](https://app.travis-ci.com/anton415/job4j_grabber)
[![codecov](https://codecov.io/gh/anton415/job4j_grabber/branch/main/graph/badge.svg?token=NVPARHNG2R)](https://codecov.io/gh/anton415/job4j_grabber)

##Описание.

Система запускается по расписанию. Период запуска указывается в настройках - app.properties.
Первый сайт будет career.habr.com. В нем есть раздел https://career.habr.com/vacancies/java_developer. 
С ним будет идти работа. Программа должна считывать все вакансии относящиеся к Java и записывать их в базу.
Доступ к интерфейсу будет через REST API.