package com.academy.bestprojectduoapp.service;

import com.academy.bestprojectduoapp.model.WorkHistory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class BestDuoService {

    // Method to find the longest working pair
    public static long[] findLongestWorkingPair(List<WorkHistory> projects) {
        // Initialize the result array to store the longest working pair and the duration
        long[] longestPair = new long[3];

        // Map to store pairs of employee IDs and their total overlapping days
        Map<Map<Long, Long>, Integer> mapResult = new HashMap<>();

        // Nested loops to iterate over all pairs of projects
        for (int i = 0; i < projects.size() - 1; i++) {
            for (int j = i + 1; j < projects.size(); j++) {
                WorkHistory proj1 = projects.get(i);
                WorkHistory proj2 = projects.get(j);

                // Check if projects have the same ProjectID
                if (Objects.equals(proj1.getProjectID(), proj2.getProjectID())) {
                    // Calculate the overlapping days between the two projects
                    int days = calculateOverlap(proj1.getDateFrom(), proj1.getDateTo(),
                            proj2.getDateFrom(), proj2.getDateTo());

                    // Get employee IDs
                    long empId1 = proj1.getEmployeeID();
                    long empId2 = proj2.getEmployeeID();

                    // Create a map with employee IDs as keys and their overlapping days as the value
                    Map<Long, Long> empIds = new HashMap<>();
                    empIds.put(empId1, empId2);

                    // Update the mapResult with the calculated overlapping days
                    if (mapResult.containsKey(empIds)) {
                        mapResult.put(empIds, days + mapResult.get(empIds));
                    } else {
                        mapResult.put(empIds, days);
                    }

                    // Update the longestPair array based on the current result
                    longestPair = result(mapResult);
                }
            }
        }

        return longestPair;
    }

    // Method to calculate the overlapping days between two date ranges
    private static int calculateOverlap(LocalDate startDate1, LocalDate endDate1, LocalDate startDate2, LocalDate endDate2) {
        int overlap;
        // Check for non-overlapping scenarios
        if (startDate1.isAfter(endDate2) || startDate2.isAfter(endDate1)) {
            overlap = 0;
        } else if (startDate1.isBefore(startDate2) && endDate1.isBefore(endDate2)) {
            // Calculate the overlapping days in case 1 is completely within 2
            overlap = (int) ChronoUnit.DAYS.between(startDate2, endDate1) + 1;
        } else if (startDate2.isBefore(startDate1) && endDate2.isBefore(endDate1)) {
            // Calculate the overlapping days in case 2 is completely within 1
            overlap = (int) ChronoUnit.DAYS.between(startDate1, endDate2) + 1;
        } else if (startDate2.isBefore(startDate1) && endDate2.isAfter(endDate1)) {
            // Calculate the overlapping days in case 2 starts before 1 but ends after 1
            overlap = (int) ChronoUnit.DAYS.between(startDate1, endDate1) + 1;
        } else {
            // Calculate the overlapping days in case 1 is completely within 2
            overlap = (int) ChronoUnit.DAYS.between(startDate2, endDate2) + 1;
        }
        return overlap;
    }

    // Method to find the result with the longest overlapping days
    private static long[] result(Map<Map<Long, Long>, Integer> mapResult) {
        long[] longestPair = new long[3];
        int maxDays = 0;

        // Iterate over the map entries to find the entry with the maximum overlapping days
        for (Map.Entry<Map<Long, Long>, Integer> entry : mapResult.entrySet()) {
            if (entry.getValue() > maxDays) {
                Map<Long, Long> temp = entry.getKey();
                for (Map.Entry<Long, Long> emplIds : temp.entrySet()) {
                    longestPair[0] = emplIds.getKey();
                    longestPair[1] = emplIds.getValue();
                }
                longestPair[2] = entry.getValue();
                maxDays = entry.getValue();
            }
        }
        return longestPair;
    }
}
