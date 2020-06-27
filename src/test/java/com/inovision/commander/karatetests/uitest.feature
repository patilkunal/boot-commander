@ignore
Feature: UI Test Feature
  Background:
    * configure driver = { type: 'chromedriver' }

  Scenario: Google Search
    Given driver 'https://www.google.com'
    And driver.input('input[name=q]', 'karate testing')
    When driver.click('input[type=submit]')
    Then match driver.text('.g') contains 'intuit/karate'

