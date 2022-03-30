/**
 * @package Showcase
 * @file Test with Hoverfly
 * @copyright 2022 Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the Apache License v2.0.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.hoverfly;

import io.specto.hoverfly.junit.core.Hoverfly;
import io.specto.hoverfly.junit5.HoverflyExtension;
import io.specto.hoverfly.junit5.api.HoverflyConfig;
import io.specto.hoverfly.junit5.api.HoverflyCore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ws.rs.core.MediaType;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static io.specto.hoverfly.junit.core.SimulationSource.dsl;
import static io.specto.hoverfly.junit.dsl.HoverflyDsl.service;
import static io.specto.hoverfly.junit.dsl.ResponseCreators.success;

@HoverflyCore(config = @HoverflyConfig(proxyLocalHost = true, destination = "0.0.0.0/id"))
@ExtendWith(HoverflyExtension.class)
public class TestTodoHoverfly {

    @BeforeEach
    public void setup(Hoverfly hoverfly) {
        hoverfly.simulate(dsl(
                service("0.0.0.0")
                        .get("/id")
                        .willReturn(success(UUID.randomUUID().toString(), MediaType.APPLICATION_JSON))));
    }

    @Test
    void testIdService() {
        given()
                .when().get("/id")
                .then()
                .statusCode(200);
    }
}
