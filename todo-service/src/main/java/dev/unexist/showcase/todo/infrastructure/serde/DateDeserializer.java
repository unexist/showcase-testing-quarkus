/**
 * @package Showcase-Arquillian-Quarkus
 *
 * @file Todo deserializer
 * @copyright 2021 Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the Apache License v2.0.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.infrastructure.serde;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import dev.unexist.showcase.todo.domain.todo.DueDate;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateDeserializer extends JsonDeserializer<LocalDate> {

    /**
     * Deserialize {@link LocalDate} from string
     *
     * @param  parser
     * @param  context
     * @return
     * @throws IOException
     */

    @Override
    public LocalDate deserialize(JsonParser parser,
                                 DeserializationContext context) throws IOException {
        return LocalDate.from(DateTimeFormatter.ofPattern(DueDate.DATE_PATTERN)
                .parse(parser.getText()));
    }
}