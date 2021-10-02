/**
 * @package Showcase-Testing-Quarkus
 *
 * @file Stupid integration test
 * @copyright 2019 Christoph Kappel <christoph@unexist.dev>
 * @version $Id: todo-service-helper/src/test/java/dev/unexist/showcase/todo/adapter/TodoResourceTest.java,v 102 1633004665.0-7200 unexist $
 *
 * This program can be distributed under the terms of the Apache License v2.0.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.adapter;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class TodoResourceTest {

    @Test
    public void testTodoEndpoint() {
        given()
          .when().get("/todo")
          .then()
             .statusCode(204);
    }
}