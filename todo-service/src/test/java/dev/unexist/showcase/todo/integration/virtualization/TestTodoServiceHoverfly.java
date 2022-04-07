/**
 * @package Showcase-Testing-Quarkus
 *
 * @file Tests with Hoverfly
 * @copyright 2022 Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the Apache License v2.0.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.integration.virtualization;

import dev.unexist.showcase.todo.domain.todo.TodoBase;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
@QuarkusTestResource(value = HoverflyResource.class, restrictToAnnotatedClass = true)
public class TestTodoServiceHoverfly {

    @Test
    void shouldGetIdWithRestAssured() {
        String location =
            given()
                .contentType(ContentType.JSON)
                .body(new TodoBase("test", "test"))
            .when()
                .post("/todo")
            .then()
                .statusCode(201)
                    .extract().header("location");

        assertThat(location).isNotBlank();
    }
}
