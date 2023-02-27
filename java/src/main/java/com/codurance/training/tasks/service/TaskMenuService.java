package com.codurance.training.tasks.service;

import com.codurance.training.tasks.model.Task;

import java.util.List;
import java.util.Map;

public interface TaskMenuService {
    public void show(Map<String, List<Task>> tasks);
    public void add(Map<String, List<Task>> tasks,String commandLine);
    public void addProject(Map<String, List<Task>> tasks,String name);
    public void addTask(Map<String, List<Task>> tasks,String project, String description);
}
