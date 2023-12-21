package com.academy.bestprojectduoapp.repository;

import com.academy.bestprojectduoapp.model.WorkHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkHistoryRepository extends JpaRepository <WorkHistory, Long> {
    Optional<List<WorkHistory>> findByEmployeeID (Long employeeID);

    Optional<List<WorkHistory>> findByProjectID (Long projectID);
}
