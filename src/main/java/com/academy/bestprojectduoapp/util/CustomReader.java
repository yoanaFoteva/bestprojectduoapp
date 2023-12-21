package com.academy.bestprojectduoapp.util;

import com.academy.bestprojectduoapp.model.WorkHistory;
import java.util.List;

interface CustomReader {
    public List<WorkHistory> read (String filename);
}
