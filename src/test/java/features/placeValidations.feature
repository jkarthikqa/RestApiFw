Feature: Validating place via Api

@AddPlace @Regression
  Scenario Outline: Validate if place is being added successfully using app place api
    Given add place payload "<name>" "<language>" "<address>"
    When user calls "Add_Place_Api" with "Post" http request
    Then the response Api call is success with status code 200
    And "status" in response body return is as "OK"
    And "scope" in response body return is as "APP"
    Then verify place_id created maps to "<name>" using "Get_Place_Api" with "Get" http request

    Examples:
      | name     | language   | address             |
      | BBPerson | English-EN | World Global Center |
  #    | CCPerson | Spanish    | Sea Cross Road      |


@DeletePlace @Regression
  Scenario: Verify if Delete place functionality is working
    Given delete place payload
    When user calls "Delete_Place_Api" with "Post" http request
    Then the response Api call is success with status code 200
    And "status" in response body return is as "OK"
