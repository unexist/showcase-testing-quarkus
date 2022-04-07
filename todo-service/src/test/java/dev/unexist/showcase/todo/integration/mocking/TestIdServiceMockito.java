/**
 * @package Showcase-Testing-Quarkus
 *
 * @file Tests with Mockito
 * @copyright 2022 Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the Apache License v2.0.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.integration.mocking;

import dev.unexist.showcase.todo.adapter.IdService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
public class TestIdServiceMockito {

    @InjectMock
    @RestClient
    IdService idService;

    @Test
    void shouldGetIdWithRestClient() {
        Mockito.when(idService.getId()).thenReturn(UUID.randomUUID().toString());

        assertThat(this.idService.getId()).isNotEmpty();
    }
}
