package com.codurance.training.tasks.service;

import com.codurance.training.tasks.model.Task;

import java.util.List;
import java.util.Map;

public interface TaskListService {
    public void show(Map<String, List<Task>> tasks);
    public void add(Map<String, List<Task>> tasks,String commandLine);
    public void addProject(Map<String, List<Task>> tasks,String name);
    public void addTask(Map<String, List<Task>> tasks,String project, String description);
    public void check(Map<String, List<Task>> tasks,String idString);
    public void uncheck(Map<String, List<Task>>tasks,String idString);
    public void setDone(Map<String, List<Task>> tasks,String idString, boolean done);
    public void help();
    public void error(String command);
    public long nextId();

}
