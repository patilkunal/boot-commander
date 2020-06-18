#Author: Kunal Patil

#  @hostAPI
  Feature: Test Host API
    Background:
      * url baseURI
      * header ACCEPT = 'application/json'
      * header Authorization = bearerToken
      * def HOST_URL = '/hosts'


      @positive-scenario @get-hosts
        Scenario: Test case to list all hosts
        Given path HOST_URL
        When method GET
        Then status 200
          * print response

    @positive-scenario @get-single-host
    Scenario: Test case to list all hosts
      Given path HOST_URL + '/1'
      When method GET
      Then status 200
      * print response

      @negative-scenario
      Scenario: Test case for host not found
        Given path HOST_URL + '/badHostId'
        When method GET
        Then status 404
