package Pages;

import helper.Endpoint;
import helper.Utility;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.File;
import java.util.List;

import static helper.Models.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ApiPage {

    String setURL;
    String global_id;

    Response res;



    public void prepareURLFor(String url) {
        switch (url) {
            case "GET_LIST_USERS":
                setURL = Endpoint.GET_LIST_USERS;
                break;
            case "CREATE_NEW_USER":
                setURL = Endpoint.CREATE_NEW_USER;
                break;
            case "DELETE_USER":
                setURL = Endpoint.DELETE_USER;
                break;
            case "UPDATE_USER":
                setURL = Endpoint.UPDATE_USER;
                break;
            case "INVALID_ENDPOINT":
                setURL = Endpoint.INVALID_ENDPOINT;
                break;


        }
    }

    public void hitApiGetListUsers() {
        res = getListUsers(setURL);
        System.out.println(res.getBody().asString());

    }

    public void hitApiGetListUsersWithInvalidToken() {
        res = getListUsersWithInvalidToken(setURL);
        System.out.println(res.getBody().asString());

    }

    public void hitApiGetListUsersWithNoAuth() {
        res = getListUsersWithNoAuth(setURL);
        System.out.println(res.getBody().asString());

    }

    public void hitApiPostCreateUser() {
        res = postCreateUser(setURL);
        JsonPath jsonPathEvaluator = res.jsonPath();
        global_id = jsonPathEvaluator.get("id");
//        System.out.println("New user ID: " + global_id);
//        System.out.println(res.getBody().asString());

    }

    public void hitApiPostCreateUserWithMissingFields() {
        res = postCreateUserWithMissingFields(setURL);
        JsonPath jsonPathEvaluator = res.jsonPath();
        global_id = jsonPathEvaluator.get("id");
//        System.out.println("New user ID: " + global_id);
//        System.out.println(res.getBody().asString());

    }

    public void hitApiPostCreateUserDuplicate() {
        res = postCreateUserDuplicate(setURL);
        JsonPath jsonPathEvaluator = res.jsonPath();
        global_id = jsonPathEvaluator.get("id");
//        System.out.println("New user ID: " + global_id);
//        System.out.println(res.getBody().asString());

    }


    public void hitApiDeleteUser() {
        if (global_id != null) {
            res = deleteUser(setURL, global_id);
            System.out.println(res.getBody().asString());
        } else {
            System.out.println("Error: Global ID is null. Please ensure it is set before attempting to delete.");
        }


    }

    public void hitApiUpdateUser() {
        res = updateUser(setURL, global_id);
        System.out.println(res.getBody().asString());

    }

    public void hitApiUpdateNonExistentUser() {
        res = updateNonExistentUser(setURL);
        System.out.println(res.getBody().asString());

    }



    public void hitApiDeleteNonExistentUser() {
        res = deleteUserNonExistent(setURL);
        System.out.println(res.getBody().asString());


    }





    public void validationStatusCodeIsEquals(int status_code) {
        assertThat(res.statusCode()).isEqualTo(status_code);
    }


    public void validationResponseBodyGetListUsers() {
        JsonPath jsonPath = res.jsonPath();

        List<Object> idList = jsonPath.getList("data.id");
        List<Object> titleList = jsonPath.getList("data.title");
        List<Object> firstNameList = jsonPath.getList("data.firstName");
        List<Object> lastNameList = jsonPath.getList("data.lastName");
        List<Object> pictureList = jsonPath.getList("data.picture");

        // Validasi elemen pertama dari setiap daftar
        assertThat(getFirstElement(idList)).isNotNull();
        assertThat(getFirstElement(titleList)).isNotNull();
        assertThat(getFirstElement(firstNameList)).isNotNull();
        assertThat(getFirstElement(lastNameList)).isNotNull();
        assertThat(getFirstElement(pictureList)).isNotNull();
    }

    private Object getFirstElement(List<Object> list) {
        return list.isEmpty() ? null : list.get(0);
    }


    public void validationResponseJsonWithJSONSchema(String filename) {
        File JSONFile = Utility.getJSONSchemaFile(filename);
        res.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(JSONFile));
    }

    public void validationResponseBodyCreateNewUser(){
        JsonPath jsonPathEvaluator = res.jsonPath();
        String id = global_id;
        String firstName = jsonPathEvaluator.get("firstName");
        String lastName = jsonPathEvaluator.get("lastName");
        String email = jsonPathEvaluator.get("email");
        String registerDate = jsonPathEvaluator.get("registerDate");
        String updatedDate = jsonPathEvaluator.get("updatedDate");

        // Validasi bahwa nilai tidak null
        assertThat(firstName).isNotNull().withFailMessage("Nama Depan tidak boleh null");
        assertThat(lastName).isNotNull().withFailMessage("Nama Belakang tidak boleh null");
        assertThat(email).isNotNull().withFailMessage("Email tidak boleh null");
        assertThat(registerDate).isNotNull().withFailMessage("Tanggal Registrasi tidak boleh null");
        assertThat(updatedDate).isNotNull().withFailMessage("Tanggal Update tidak boleh null");

        // Validasi format tanggal (contoh: format harus ISO8601)
        assertThat(registerDate).matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.\\d{3}Z")
                .withFailMessage("Format Tanggal Registrasi tidak sesuai");

        assertThat(updatedDate).matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.\\d{3}Z")
                .withFailMessage("Format Tanggal Update tidak sesuai");

    }

    public void validationResponseBodyUpdateUser() {
        JsonPath jsonPathEvaluator = res.jsonPath();
        String id = jsonPathEvaluator.get("id");
        String firstName = jsonPathEvaluator.get("firstName");
        String lastName = jsonPathEvaluator.get("lastName");
        String email = jsonPathEvaluator.get("email");
        String registerDate = jsonPathEvaluator.get("registerDate");
        String updatedDate = jsonPathEvaluator.get("updatedDate");

        assertThat(id).isNotNull();
        assertThat(firstName).isNotNull();
        assertThat(lastName).isNotNull();
        assertThat(email).isNotNull();
        assertThat(registerDate).matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.\\d{3}Z")
                .withFailMessage("Format Tanggal Registrasi tidak sesuai");
        assertThat(updatedDate).matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.\\d{3}Z")
                .withFailMessage("Format Tanggal Update tidak sesuai");


    }

//    public void saveUserIdFromResponseBodyCreateNewUser() {
//        // Implementasi untuk mengambil dan menyimpan ID pengguna dari respons operasi POST
//        String createdUserId = // Ambil ID dari respons (gunakan library atau metode yang sesuai)
//                // Simpan ID dalam variabel atau konteks yang dapat diakses oleh langkah selanjutnya
//                ScenarioContext.getContext().setContext("createdUserId", createdUserId);
//    }
}
