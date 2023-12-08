@api
Feature: Test Automation Rest Api

  Scenario: Test get list data normal
    Given prepare URL for "GET_LIST_USERS"
    And  hit api get list users
    Then validation status code is equals 200
    Then validation response body get list users
    Then validation response json with JSONSchema "get_list_users_normal.json"

  Scenario: Test create new user normal
    Given prepare URL for "CREATE_NEW_USER"
    And hit api post create new users
    Then validation status code is equals 200
    Then validation response body create new user
    Then validation response json with JSONSchema "post_create_new_user_normal.json"

  Scenario: Test delete user normal
    Given prepare URL for "CREATE_NEW_USER"
    And  hit api post create new users
    Then validation status code is equals 200
    Then validation response body create new user
    And hit api delete new
    Then validation status code is equals 200
    Then validation response json with JSONSchema "delete_user_normal.json"

  Scenario: Test update user normal
    Given prepare URL for "CREATE_NEW_USER"
    And  hit api post create new users
    Then validation status code is equals 200
    Then validation response body create new user
    And hit api update data
    Then validation status code is equals 200
    Then validation response body update user
    Then validation response json with JSONSchema "update_user_normal.json"

  Scenario: Test get list data with invalid endpoint
    Given prepare URL for "INVALID_ENDPOINT"
    When hit api get list users
    Then validation status code is equals 404
    Then validation response json with JSONSchema "get_list_data_with_invalid_endpoint.json"

  Scenario: Test get list data with invalid authentication
    Given prepare URL for "GET_LIST_USERS"
    When hit api get list users with invalid token
    Then validation status code is equals 403
    Then validation response json with JSONSchema "get_list_data_with_invalid_authentication.json"

  Scenario: Test get list data with no auth
    Given prepare URL for "GET_LIST_USERS"
    When hit api get list with no auth
    Then validation status code is equals 403
    Then validation response json with JSONSchema "get_list_data_with_no_auth.json"

  Scenario: Test create new user with missing required fields
    Given prepare URL for "CREATE_NEW_USER"
    When hit api post create new users with missing fields
    Then validation status code is equals 400
    Then validation response json with JSONSchema "create_new_user_with_missing_fields.json"

  Scenario: Test create new user with duplicate data
    Given prepare URL for "CREATE_NEW_USER"
    And hit api post create new user first
    And hit api post create new user duplicate
    Then validation status code is equals 400
    Then validation response json with JSONSchema "create_new_user_with_duplicate_data.json"

  Scenario: Test delete non-existent user
    Given prepare URL for "DELETE_USER"
    When hit api delete non-existent user
    Then validation status code is equals 404
    Then validation response json with JSONSchema "delete_non_existent_user.json"

  Scenario: Test update non-existent user
    Given prepare URL for "UPDATE_USER"
    And hit api update non-existent user
    Then validation status code is equals 400
    Then validation response json with JSONSchema "update_non_existent_user.json"














