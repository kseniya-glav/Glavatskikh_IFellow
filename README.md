### IFellow_FinalHomeWork_UI

## Тестирование UI с использованием JUnit.Jupiter и Allure

Проект содержит кейс по тестированию UI с использованием JUnit.Jupiter и Allure

## Тестовый класс [RunTest](src/test/java/tests/RunTest.java)

#### Что реализовано?
* авторизация в edujira.ifellow.ru;
* переход в проект "Test";
* проверка общего количества заведенных задач в проекте;
* поиск задачи "TestSeleniumATHomework" и её проверка на статус и версию;
* создание бага с описанием и проведение его по всем статусам задачи.

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