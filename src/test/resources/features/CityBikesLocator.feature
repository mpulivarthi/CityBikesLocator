Business Need: As a biker I would like to know the exact location of city bikes around the world in a given application.

  Scenario: As a user I want to verify that the city Frankfurt is in Germany and return their corresponded latitude and longitude
    Given I have the city to locate on CityBike network
    When I access CityBike network
    Then I verify Response is successful
    And I verify the city is in the respective Country
    And I get the corresponding latitude and longitude

