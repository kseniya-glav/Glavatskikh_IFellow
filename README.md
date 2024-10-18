### IFellow_FinalHomeWork_API

## Тестирование API с использованием Cucumber и Allure

Проект содержит два кейса по тестированию двух API с использованием Cucumber и Allure

## Тестовый класс [ReqResTest](src/test/java/tests/ReqResTest.java)

#### API: [REQRES](https://reqres.in)

#### Что реализовано?

* поиск информацию по персонажу Морти Смит;
* выбор последнего эпизода, где появлялся Морти;
* получение из списка последнего эпизода последнего персонажа;
* получение данных по местонахождению и расе персонажа;
* проверка персонажа на соответствие расы и местонахождение с Морти.

## Тестовый класс [RickAndMortyTest](src/test/java/tests/RickAndMortyTest.java)

#### API: [The Rick and Morty API](https://rickandmortyapi.com)

#### Что реализовано?

* создание .json файла с полем name;
* редактирование .json файла - изменение поля name и добавление поля job;
* запрос на создание пользователя с данными из .json файла;
* проверка получения ответа 201;
* проверка полученного response на валидные данные по значениям key и value.

## Установка проекта

1. Клонирование репозитория - выполните  
   `git clone https://github.com/kseniya-glav/Glavatskikh_iFellow`
2. Выбор ветки FHW_API - выполните  
   `git checkout FHW_API`
3. Установка зависимостей Maven - выполните  
   `mvn install`

## Использование проекта

### Тестирование

1. Очистка артефактов и запуск тестов  
   `mvn clean test`

### Формирование отчёта

1. Генерация отчёта  
   `mvn allure:report`
2. Запуск сервера для просмотра отчёта  
   `mvn allure:serve`

### Автор - Главатских Ксения

* email: glavatskikh.kseni@gmail.com
* telegram: @ksesha_tut
