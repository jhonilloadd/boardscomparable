package com.boards.comparable;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

@QuarkusTest
class BoardsResourceTest {

    @Test
    void testCreateBoard() {
        String requestBody = "{\"name\":\"Test Board\",\"model\":\"Test Model\",\"brand\":\"Test Brand\"}";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/boards")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("name", equalTo("Test Board"))
                .body("model", equalTo("Test Model"))
                .body("brand", equalTo("Test Brand"));
    }

    @Test
    void testGetBoard() {
        // First, create a board
        String requestBody = "{\"name\":\"Get Test Board\",\"model\":\"Get Test Model\",\"brand\":\"Get Test Brand\"}";

        Integer boardId = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/boards")
                .then()
                .statusCode(201)
                .extract()
                .path("id");

        // Now, test getting the board
        given()
                .when()
                .get("/boards/" + boardId)
                .then()
                .statusCode(200)
                .body("id", equalTo(boardId))
                .body("name", equalTo("Get Test Board"))
                .body("model", equalTo("Get Test Model"))
                .body("brand", equalTo("Get Test Brand"));
    }

    @Test
    void testUpdateBoard() {
        // First, create a board
        String createRequestBody = "{\"name\":\"Update Test Board\",\"model\":\"Update Test Model\",\"brand\":\"Update Test Brand\"}";

        Integer boardId = given()
                .contentType(ContentType.JSON)
                .body(createRequestBody)
                .when()
                .post("/boards")
                .then()
                .statusCode(201)
                .extract()
                .path("id");

        // Now, update the board
        String updateRequestBody = "{\"name\":\"Updated Board\",\"model\":\"Updated Model\",\"brand\":\"Updated Brand\"}";

        given()
                .contentType(ContentType.JSON)
                .body(updateRequestBody)
                .when()
                .put("/boards/" + boardId)
                .then()
                .statusCode(200)
                .body("id", equalTo(boardId))
                .body("name", equalTo("Updated Board"))
                .body("model", equalTo("Updated Model"))
                .body("brand", equalTo("Updated Brand"));
    }

    @Test
    void testDeleteBoard() {
        // First, create a board
        String requestBody = "{\"name\":\"Delete Test Board\",\"model\":\"Delete Test Model\",\"brand\":\"Delete Test Brand\"}";

        Integer boardId = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/boards")
                .then()
                .statusCode(201)
                .extract()
                .path("id");

        // Now, delete the board
        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/boards")
                .then()
                .statusCode(204);

        // Verify that the board is deleted
        given()
                .when()
                .get("/boards/" + boardId)
                .then()
                .statusCode(404);
    }
}
