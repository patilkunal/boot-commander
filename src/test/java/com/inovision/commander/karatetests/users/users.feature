#Author: Kunal Patil

#  @hostAPI
  Feature: Test Users API
    Background:
      * url baseURI
      * header ACCEPT = 'application/json'
      * header Authorization = bearerToken
      * def USERS_URL = '/user'


      @positive-scenario @get-hosts
        Scenario: Test case to list all users
        Given path USERS_URL
        When method GET
        Then status 200
          * print response

    @positive-scenario @get-single-host
    Scenario: Test case to list single user
      Given path USERS_URL + '/1'
      When method GET
      Then status 200
      * print response

      @negative-scenario
      Scenario: Test case for bad request
        Given path USERS_URL + '/badHostId'
        When method GET
        # expect Bad Request, since host id param is non-numeric
        Then status 400

    @negative-scenario
    Scenario: Test case for user not found
      Given path USERS_URL + '/123213'
      When method GET
        # expect Bad Request, since host id param is non-numeric
      Then status 404
