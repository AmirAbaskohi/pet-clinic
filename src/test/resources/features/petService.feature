Feature: pet service
  Scenario: find owner
    Given owner with id 11 exists
    When finding for owner
    Then returned owner is not null and has id 11

  Scenario: new pet
    Given an owner exists with any pet
    When request an new pet for this owner
    Then pet added to owner successfully

  Scenario: find pet
    Given pet with id 14 exists
    When finding for pet
    Then returned pet is not null and has id 14

  Scenario: save pet
    Given an owner with any pet and a pet exists
    When save the pet to this owner
    Then pet saved to owner successfully
