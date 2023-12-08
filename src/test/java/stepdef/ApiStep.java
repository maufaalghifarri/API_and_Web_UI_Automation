package stepdef;

import Pages.ApiPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ApiStep {

    ApiPage apiPage;

    public ApiStep() {
        this.apiPage = new ApiPage();
    }


    @Given("prepare URL for {string}")
    public void prepareURLFor(String url) {
        apiPage.prepareURLFor(url);
    }

    @And("hit api get list users")
    public void hitApiGetListUsers() {

        apiPage.hitApiGetListUsers();
    }

    @Then("validation status code is equals {int}")
    public void validationStatusCodeIsEquals(int status_code) {

        apiPage.validationStatusCodeIsEquals(status_code);
    }

    @Then("validation response body get list users")
    public void validationResponseBodyGetListUsers() {

        apiPage.validationResponseBodyGetListUsers();
    }

    @Then("validation response json with JSONSchema {string}")
    public void validationResponseJsonWithJSONSchema(String filename) {
        apiPage.validationResponseJsonWithJSONSchema(filename);
    }


    @And("hit api post create new users")
    public void hitApiPostCreateNewUsers() {
        apiPage.hitApiPostCreateUser();
    }

    @Then("validation response body create new user")
    public void validationResponseBodyCreateNewUser() {
        apiPage.validationResponseBodyCreateNewUser();
    }

    @And("hit api delete new")
    public void hitApiDeleteNew() {
        apiPage.hitApiDeleteUser();
    }

    @And("hit api update data")
    public void hitApiUpdateData() {
        apiPage.hitApiUpdateUser();
        
    }

    @Then("validation response body update user")
    public void validationResponseBodyUpdateUser() {
        apiPage.validationResponseBodyUpdateUser();
    }


    @When("hit api post create new users with missing fields")
    public void hitApiPostCreateNewUsersWithMissingFields() {
        apiPage.hitApiPostCreateUserWithMissingFields();
    }

    @When("hit api delete non-existent user")
    public void hitApiDeleteNonExistentUser() {
        apiPage.hitApiDeleteNonExistentUser();
    }


    @When("hit api get list users with invalid token")
    public void hitApiGetListUsersWithInvalidToken() {
        apiPage.hitApiGetListUsersWithInvalidToken();
    }

    @And("hit api post create new user first")
    public void hitApiPostCreateNewUserFirst() {
        apiPage.hitApiPostCreateUserDuplicate();
        
    }

    @And("hit api post create new user duplicate")
    public void hitApiPostCreateNewUserDuplicate() {
        apiPage.hitApiPostCreateUserDuplicate();
    }

    @When("hit api get list with no auth")
    public void hitApiGetListWithNoAuth() {
        apiPage.hitApiGetListUsersWithNoAuth();
    }

    @And("hit api update non-existent user")
    public void hitApiUpdateNonExistentUser() {
        apiPage.hitApiUpdateNonExistentUser();
    }
}
