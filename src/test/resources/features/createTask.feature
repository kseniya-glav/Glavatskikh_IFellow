Feature: Создание задачи

  Background:
    Given пользователь открывает браузер "url"
    Given пользователь на странице входа
    Then пользователь вводит логин "login" и пароль "password"

  @test
  Scenario: Создание задачи
    And пользователь переходит к проекту "project"
    And пользователь создаёт задачу "nameCreateTask"
    Then браузер закрывается