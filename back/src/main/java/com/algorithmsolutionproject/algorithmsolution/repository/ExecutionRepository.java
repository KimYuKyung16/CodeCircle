package com.algorithmsolutionproject.algorithmsolution.repository;

import com.algorithmsolutionproject.algorithmsolution.entity.Execution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExecutionRepository extends JpaRepository<Execution, Integer> {
}
