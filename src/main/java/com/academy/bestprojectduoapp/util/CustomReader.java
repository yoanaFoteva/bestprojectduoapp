package com.academy.bestprojectduoapp.util;

import com.academy.bestprojectduoapp.model.WorkHistory;

import java.io.IOException;
import java.util.List;
public interface CustomReader {
    List<WorkHistory> read(String fileName) throws IOException;
}
