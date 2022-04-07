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

import dev.unexist.showcase.todo.adapter.IdService;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.builder.RequestSpecBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
@QuarkusTestResource(value = HoverflyResource.class, restrictToAnnotatedClass = true)
public class TestIdServiceHoverfly {

    @ConfigProperty(name = "id.service.url", defaultValue = "")
    String serviceUrl;

    @Inject
    @RestClient
    IdService idService;

    @Test
    void shouldGetIdWithRestAssured() {
        given()
                .spec(new RequestSpecBuilder().setBaseUri("http://" + this.serviceUrl).build())
        .when()
                .get("/id")
        .then()
                .statusCode(200);
    }

    @Test
    void shouldGetIdWithRestClient() {
        assertThat(this.idService.getId()).isNotEmpty();
    }
}
