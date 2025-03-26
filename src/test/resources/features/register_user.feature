Feature: Registro de usuario

  Scenario: Registrar un nuevo usuario
    When registro un usuario con nombre "Luis"
    Then el sistema devuelve un usuario con nombre "Luis"
