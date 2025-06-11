package com.algorithmsolutionproject.algorithmsolution.repository;

import com.algorithmsolutionproject.algorithmsolution.dto.problem.TestCaseDTO;
import com.algorithmsolutionproject.algorithmsolution.entity.TestCase;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TestCaseRepository extends JpaRepository<TestCase, Integer> {
    @Query("""
                SELECT new com.algorithmsolutionproject.algorithmsolution.dto.problem.TestCaseDTO(
                    id, input, expectedOutput, isSample
                )
                FROM TestCase
                WHERE problem.id = :problemId
            """)
    List<TestCaseDTO> findByProblemId(@Param("problemId") Integer problemId);
}
