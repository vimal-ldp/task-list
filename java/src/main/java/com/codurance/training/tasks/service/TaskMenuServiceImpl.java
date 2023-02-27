package com.codurance.training.tasks.service;

import com.codurance.training.tasks.model.Task;
import com.codurance.training.tasks.model.UserEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TaskMenuServiceImpl implements TaskMenuService {

    private UserEntry user;
    private TaskUtilityService utilityService;


    public TaskMenuServiceImpl() {
        this.user =new UserEntry();
        this.utilityService= new TaskUtilityServiceImpl();
    }

    public void show(Map<String, List<Task>> tasks) {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            user.getOut().println(project.getKey());
            for (Task task : project.getValue()) {
                user.getOut().printf("    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
            }
            user.getOut().println();
        }
    }

    public void add(Map<String, List<Task>> tasks,String commandLine) {
        String[] subcommandRest = commandLine.split(" ", 2);
        String subcommand = subcommandRest[0];
        if (subcommand.equals("project")) {
            addProject(tasks,subcommandRest[1]);
        } else if (subcommand.equals("task")) {
            String[] projectTask = subcommandRest[1].split(" ", 2);
            addTask(tasks,projectTask[0], projectTask[1]);
        }
    }

    public void addProject(Map<String, List<Task>> tasks,String name) {
        tasks.put(name, new ArrayList<Task>());
    }

    public void addTask(Map<String, List<Task>> tasks,String project, String description) {
        List<Task> projectTasks = tasks.get(project);
        if (projectTasks == null) {
            user.getOut().printf("Could not find a project with the name \"%s\".", project);
            user.getOut().println();
            return;
        }
        projectTasks.add(new Task(utilityService.nextId(), description, false));
    }

}
