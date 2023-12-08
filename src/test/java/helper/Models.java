package helper;

import Pages.ApiPage;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import static helper.Utility.generateRandomEmail;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Models extends ApiPage {

    private static RequestSpecification request;

    public static void setupHeaders() {
        request = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("app-id", "6569a5f62915b04dceceae60");
    }

    public static void setupHeadersForInvalidToken() {
        request = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("app-id", "invalidtokeninvalid");
    }

    public static void setupHeadersWithNoAuth() {
        request = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json");
    }

    public static Response getListUsers(String endpoint) {
        setupHeaders();
        return request.when().get(endpoint);
    }

    public static Response getListUsersWithInvalidToken(String endpoint) {
        setupHeadersForInvalidToken();
        return request.when().get(endpoint);
    }

    public static Response getListUsersWithNoAuth(String endpoint) {
        setupHeadersWithNoAuth();
        return request.when().get(endpoint);
    }

    public static Response postCreateUser(String endpoint) {
        String firstName = "fafa fari";
        String lastName = "male";
        String email = generateRandomEmail();
        JSONObject payload = new JSONObject();
        payload.put("firstName", firstName);
        payload.put("lastName", lastName);
        payload.put("email", email);

        setupHeaders();
        String finalEndpoint = endpoint + "/create" ;
        return request.body(payload.toString()).when().post(finalEndpoint);


    }

    public static Response postCreateUserDuplicate(String endpoint) {
        String firstName = "fafa fari";
        String lastName = "male";
        String email = "emailcobacoba@gmail.com";
        JSONObject payload = new JSONObject();
        payload.put("firstName", firstName);
        payload.put("lastName", lastName);
        payload.put("email", email);

        setupHeaders();
        String finalEndpoint = endpoint + "/create" ;
        return request.body(payload.toString()).when().post(finalEndpoint);

    }

    public static Response deleteUser(String endpoint, String global_id) {
        setupHeaders();


        // Menggunakan nilai "id" dari operasi POST untuk operasi DELETE
        String finalEndpoint = endpoint + "/" + global_id;
        System.out.println(finalEndpoint);

        // Melakukan permintaan DELETE dan mengembalikan respons
        return request.when().delete(finalEndpoint);
//
//        String finalEndpoint = endpoint + "/" + user_id;
//        System.out.println(finalEndpoint);
//        return request.when().delete(finalEndpoint);
    }

    public static Response deleteUserNonExistent(String endpoint) {
        setupHeaders();


        // Menggunakan nilai "id" dari operasi POST untuk operasi DELETE
        String finalEndpoint = endpoint + "/";
        System.out.println(finalEndpoint);

        // Melakukan permintaan DELETE dan mengembalikan respons
        return request.when().delete(finalEndpoint);
//
//        String finalEndpoint = endpoint + "/" + user_id;
//        System.out.println(finalEndpoint);
//        return request.when().delete(finalEndpoint);
    }

    public static Response updateUser(String endpoint, String global_id) {
        setupHeaders();

        String firstName = "fafa update";
        JSONObject payload = new JSONObject();
        payload.put("firstName", firstName);

        String finalEndpoint = endpoint + "/" + global_id;
        System.out.println(finalEndpoint);
        return request.body(payload.toString()).when().put(finalEndpoint);
    }

    public static Response updateNonExistentUser(String endpoint) {
        setupHeaders();

        String firstName = "fafa update";
        JSONObject payload = new JSONObject();
        payload.put("firstName", firstName);

        String finalEndpoint = endpoint + "/" + "usernotvalid";
        System.out.println(finalEndpoint);
        return request.body(payload.toString()).when().put(finalEndpoint);
    }


    public static Response postCreateUserWithMissingFields(String endpoint) {
        String firstName = "fafa fari";
        String email = generateRandomEmail();
        JSONObject payload = new JSONObject();
        payload.put("firstName", firstName);
        payload.put("email", email);

        setupHeaders();
        String finalEndpoint = endpoint + "/create" ;
        return request.body(payload.toString()).when().post(finalEndpoint);


    }

}
