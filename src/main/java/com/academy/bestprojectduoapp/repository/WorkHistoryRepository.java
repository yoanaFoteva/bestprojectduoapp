package com.academy.bestprojectduoapp.repository;

import com.academy.bestprojectduoapp.model.WorkHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkHistoryRepository extends JpaRepository <WorkHistory, Long> {

}
