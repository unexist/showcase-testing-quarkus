/**
 * @package Showcase-Arquillian-Quarkus
 *
 * @file Todo repository
 * @copyright 2021-2022 Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the Apache License v2.0.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.infrastructure.persistence;

import dev.unexist.showcase.todo.domain.todo.Todo;
import dev.unexist.showcase.todo.domain.todo.TodoRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@ApplicationScoped
public class ListTodoRepository implements TodoRepository {
    private List<Todo> list;

    /**
     * Constructor
     **/

    ListTodoRepository() {
        this.list = new ArrayList<>();
    }

    @Override
    public boolean add(final Todo todo) {
        return this.list.add(todo);
    }

    @Override
    public boolean update(final Todo todo) {
        this.list = this.list.stream()
                .map(t -> t.getId().equals(todo.getId()) ? todo : t)
                .collect(toList());

        if (this.list.stream().noneMatch(t -> t.getId().equals(todo.getId()))) {
            this.list.add(todo);
        }

        return this.list.stream().anyMatch(t -> t.getId().equals(todo.getId()));
    }

    @Override
    public boolean deleteById(String id) {
        return this.list.removeIf(t -> t.getId().equals(id));
    }

    @Override
    public List<Todo> getAll() {
        return Collections.unmodifiableList(this.list);
    }

    @Override
    public Optional<Todo> findById(String id) {
        return this.list.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
    }
}