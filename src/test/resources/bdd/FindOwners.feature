Feature: Find owners

  Scenario: Find unique owner
    Given There's an owner "Jeff Black"
    When I search for "Black"
    Then I should get the details of "Jeff Black"

