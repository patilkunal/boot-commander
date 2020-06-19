@ignore
Feature: Authentication feature
  Background:
    * url baseURI
    Scenario: Single Use Authenticate
      Given path '/login'
      And request { userName: "admin", password: "pass123" }
      When method POST
      Then status 200

      * def token = responseHeaders['Authorization'][0]