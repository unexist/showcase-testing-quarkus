/**
 * @package Showcase-Arquillian-Quarkus
 *
 * @file Todo class and aggregate root
 * @copyright 2021-present Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the Apache License v2.0.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.domain.todo;

import com.tersesystems.echopraxia.Field;

import java.util.List;

public class Todo extends TodoBase {
    private String id;

    /**
     * Constructor
     **/

    public Todo() {
    }

    /**
     * Constructor
     *
     * @param  base  Base entry
     **/

    public Todo(final TodoBase base) {
        this.update(base);
    }

    /**
     * Update values from base
     *
     * @param  base  Todo base class
     **/

    public void update(final TodoBase base) {
        this.setTitle(base.getTitle());
        this.setDescription(base.getDescription());
        this.setDone(base.getDone());

        if (null != base.getDueDate()) {
            this.setDueDate(base.getDueDate());
        }
    }

    /**
     * Get id of entry
     *
     * @return Id of the entry
     **/

    public String getId() {
        return id;
    }

    /**
     * Set id of entry
     *
     * @param  id  Id of the entry
     **/

    public void setId(String id) {
        this.id = id;
    }

    public static class FieldBuilder implements Field.Builder {

        /**
         * Field builder for {@link Todo}
         *
         * @param  name  Name of the log key
         * @param  todo  A {@link Todo} entry to log
         *
         * @return Logging {@link Field}
         **/

        public Field todo(String name, Todo todo) {
            return object(
                    name,
                    string("id", todo.getId()),
                    string("title", todo.getTitle()),
                    string("description", todo.getDescription())
            );
        }

        /**
         * Convenience field builder for {@link Todo}
         *
         * @param  name  Name of the log key
         * @param  todo  A {@link Todo} entry to log
         *
         * @return Logging list with only one {@link Field}
         **/

        public List<Field> onlyTodo(String name, Todo todo) {
            return List.of(todo(name, todo));
        }
    }
}