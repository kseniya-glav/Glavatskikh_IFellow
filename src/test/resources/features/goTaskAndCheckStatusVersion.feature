Feature: Проверка задачи на статус и версию

  Background:
    Given пользователь открывает браузер "url"
    Given пользователь на странице входа
    Then пользователь вводит логин "login" и пароль "password"

  @test
  Scenario: Проверка статуса и версии
    And пользователь переходит к проекту "project"
    And пользователь создаёт задачу "nameCreateTask"
    And пользователь переходит к задаче "nameTask"
    And статус задачи "statusTask"
    And версия задачи "fixVersion"
    Then браузер закрывается
