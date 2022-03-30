/**
 * @package Showcase-Arquillian-Quarkus
 *
 * @file Todo service and domain service
 * @copyright 2021-2022 Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the Apache License v2.0.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.domain.todo;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class TodoService {

    @Inject
    TodoRepository todoRepository;

    /**
     * Create new {@link Todo} entry and store it in repository
     *
     * @param  base  A {@link TodoBase} entry
     * @param  id    Id for the new entry
     *
     * @return Either {@code true} on success; otherwise {@code false}
     **/

    public boolean create(TodoBase base, String id) {
        Todo todo = new Todo(base);

        todo.setId(id);

        return this.todoRepository.add(todo);
    }

    /**
     * Update {@link Todo} at with given id
     *
     * @param  id    Id to update
     * @param  base  Values for the entry
     *
     * @return Either {@code true} on success; otherwise {@code false}
     **/

    public boolean update(String id, TodoBase base) {
        Optional<Todo> todo = this.findById(id);
        boolean ret = false;

        if (todo.isPresent()) {
            todo.get().update(base);

            ret = this.todoRepository.update(todo.get());
        }

        return ret;
    }

    /**
     * Delete {@link Todo} with given id
     *
     * @param  id  Id to delete
     *
     * @return Either {@code true} on success; otherwise {@code false}
     **/

    public boolean delete(String id) {
        return this.todoRepository.deleteById(id);
    }

    /**
     * Get all {@link Todo} entries
     *
     * @return List of all {@link Todo}; might be empty
     **/

    public List<Todo> getAll() {
        return this.todoRepository.getAll();
    }

    /**
     * Find {@link Todo} by given id
     *
     * @param  id  Id to look for
     *
     * @return A {@link Optional} of the entry
     **/

    public Optional<Todo> findById(String id) {
        return this.todoRepository.findById(id);
    }
}