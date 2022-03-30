/**
 * @package Showcase
 * @file dd
 * @copyright 2022 Christoph Kappel <christoph@unexist.dev>
 * @version $Id\$
 *
 *         This program can be distributed under the terms of the Apache License v2.0.
 *         See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import io.specto.hoverfly.junit.core.Hoverfly;

import java.util.Map;

public class HoverflyLifecycleManager implements QuarkusTestResourceLifecycleManager {
    Hoverfly hoverfly;

    @Override
    public Map<String, String> start() {

        return null;
    }

    @Override
    public void stop() {
        this.hoverfly.close();
    }
}
