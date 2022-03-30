/**
 * @package Showcase
 * @file Hoverfly resource manager
 * @copyright 2022 Christoph Kappel <christoph@unexist.dev>
 * @version $Id\$
 *
 *         This program can be distributed under the terms of the Apache License v2.0.
 *         See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.hoverfly;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import io.specto.hoverfly.junit.core.Hoverfly;
import io.specto.hoverfly.junit.core.HoverflyConfig;
import io.specto.hoverfly.junit.core.HoverflyMode;

import javax.ws.rs.core.MediaType;
import java.util.Map;
import java.util.UUID;

import static io.specto.hoverfly.junit.core.SimulationSource.dsl;
import static io.specto.hoverfly.junit.dsl.HoverflyDsl.service;
import static io.specto.hoverfly.junit.dsl.ResponseCreators.success;

public class HoverflyResource implements QuarkusTestResourceLifecycleManager {
    private Hoverfly hoverfly;

    @Override
    public Map<String, String> start() {
        this.hoverfly = new Hoverfly(HoverflyConfig.localConfigs()
                .destination("localhost").proxyPort(8085), HoverflyMode.SIMULATE);

        this.hoverfly.start();
        this.hoverfly.simulate(dsl(
                service("localhost:8085")
                        .get("/id")
                        .willReturn(success(UUID.randomUUID().toString(), MediaType.APPLICATION_JSON))));
        return null;
    }

    @Override
    public void stop() {
        this.hoverfly.close();
    }
}
