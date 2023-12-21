package com.academy.bestprojectduoapp.util;

import com.academy.bestprojectduoapp.model.WorkHistory;
import com.academy.bestprojectduoapp.helper.DateHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

public class CsvReader implements CustomReader{
    @Override
    public List<WorkHistory> read(String filePath) {
        Path pathToFile = Paths.get(filePath);
        List<WorkHistory> workHistoryList = new ArrayList<>();

        try (BufferedReader bufferedReader = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                validateInput(values);
                Long id = Long.parseLong(values[0].trim());
                Long employeeID = Long.parseLong(values[1].trim());
                Long projectID = Long.parseLong(values[2].trim());
                LocalDate dateFrom = DateHelper.parseDate(values[3].trim());
                LocalDate dateTo =DateHelper.parseDate(values[4].trim());

                WorkHistory workHistory = WorkHistory.builder()
                        .id(id)
                        .employeeID(employeeID)
                        .projectID(projectID)
                        .dateFrom(dateFrom)
                        .dateTo(dateTo)
                        .build();
                workHistoryList.add(workHistory);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return workHistoryList;
    }

    private void validateInput(String [] values) throws IOException {

        if (values.length != 5) {
            throw new IOException("Invalid number of fields");
        }

        try {
            Long.parseLong(values[0].trim());
            Long.parseLong(values[1].trim());
            Long.parseLong(values[2].trim());
        }
        catch (NumberFormatException e) {
            throw new IOException("Invalid data format: " + e.getMessage());
        }
    }
}
