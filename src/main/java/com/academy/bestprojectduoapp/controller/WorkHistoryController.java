package com.academy.bestprojectduoapp.controller;

import com.academy.bestprojectduoapp.exception.ResourceNotFoundException;
import com.academy.bestprojectduoapp.model.WorkHistory;
import com.academy.bestprojectduoapp.repository.WorkHistoryRepository;
import com.academy.bestprojectduoapp.service.BestDuoService;
import com.academy.bestprojectduoapp.util.CsvReader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/best-duo")
    public String findBestDuo(@RequestParam String filePath) {

        CsvReader csvReader = new CsvReader();
        List<WorkHistory> workHistoryList = csvReader.read(filePath);

        // Use BestDuoService to find the longest working pair
        long[] longestWorkingPair = BestDuoService.findLongestWorkingPair(workHistoryList);

        // Extract info from the result
        long emp1Id = longestWorkingPair[0];
        long emp2Id = longestWorkingPair[1];
        int daysWorked = (int) longestWorkingPair[2];

        return "Longest Working Pair: Employee " + emp1Id + " and Employee " + emp2Id +
                ", Days Worked: " + daysWorked;
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
