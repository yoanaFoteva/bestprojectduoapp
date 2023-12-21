package com.academy.bestprojectduoapp.controller;

import com.academy.bestprojectduoapp.exception.ResourceNotFoundException;
import com.academy.bestprojectduoapp.model.WorkHistory;
import com.academy.bestprojectduoapp.repository.WorkHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/workhistory")
public class WorkHistoryController {

    //@Autowired
    private WorkHistoryRepository workHistoryRepository;

    @GetMapping
    public List<WorkHistory> getAllWorkHistoryData(){
        return workHistoryRepository.findAll();
    }

    // create work history data REST API
    @PostMapping
    public WorkHistory createWorkHistoryData(@RequestBody WorkHistory workHistory){
        return workHistoryRepository.save(workHistory);
    }

    // get work history by id REST API
    @GetMapping("{id}")
    public ResponseEntity<WorkHistory> getWorkHistoryById(@PathVariable long id){
        WorkHistory workHistory = workHistoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Work history data do not exist with id: " + id));
        return ResponseEntity.ok(workHistory);
    }

    // update work history REST API
    @PutMapping("{id}")
    public ResponseEntity<WorkHistory> updateWorkHistory(@PathVariable long id, @RequestBody WorkHistory workHistoryData){
        WorkHistory updateWorkHistory = workHistoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Work history data do not exist with id: " + id));

        updateWorkHistory.setEmployeeID(workHistoryData.getEmployeeID());
        updateWorkHistory.setProjectID(workHistoryData.getProjectID());
        updateWorkHistory.setDateFrom(workHistoryData.getDateFrom());
        updateWorkHistory.setDateTo(workHistoryData.getDateTo());

        workHistoryRepository.save(updateWorkHistory);

        return ResponseEntity.ok(updateWorkHistory);
    }

    // delete work history data REST API
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteWorkHistory(@PathVariable long id){
        WorkHistory workHistory = workHistoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Work history data do not exist with id: " + id));

        workHistoryRepository.delete(workHistory);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
