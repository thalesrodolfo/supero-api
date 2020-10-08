package com.supero.desafio.repositories;

import com.supero.desafio.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskListRepository extends JpaRepository<Task, Long> {
}
