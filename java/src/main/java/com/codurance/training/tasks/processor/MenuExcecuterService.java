package com.codurance.training.tasks.processor;

import com.codurance.training.tasks.model.Task;

import java.util.List;
import java.util.Map;

public interface MenuExcecuterService {
    public void execute(String commandLine, Map<String, List<Task>> tasks) ;

}
