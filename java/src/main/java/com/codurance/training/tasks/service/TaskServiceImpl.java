package com.codurance.training.tasks.service;

import com.codurance.training.tasks.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TaskServiceImpl implements TaskListService {

    private UserEntry user=new UserEntry();
    private long lastId = 0;

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
        projectTasks.add(new Task(nextId(), description, false));
    }

    public void check(Map<String, List<Task>> tasks,String idString) {
        setDone(tasks,idString, true);
    }

    public void uncheck(Map<String, List<Task>> tasks,String idString) {

        setDone(tasks,idString, false);
    }

    public void setDone(Map<String, List<Task>> tasks,String idString, boolean done) {
        int id = Integer.parseInt(idString);
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            for (Task task : project.getValue()) {
                if (task.getId() == id) {
                    task.setDone(done);
                    return;
                }
            }
        }
        user.getOut().printf("Could not find a task with an ID of %d.", id);
        user.getOut().println();
    }

    public void help() {
        user.getOut().println("Commands:");
        user.getOut().println("  show");
        user.getOut().println("  add project <project name>");
        user.getOut().println("  add task <project name> <task description>");
        user.getOut().println("  check <task ID>");
        user.getOut().println("  uncheck <task ID>");
        user.getOut().println();
    }

    public void error(String command) {
        user.getOut().printf("I don't know what the command \"%s\" is.", command);
        user.getOut().println();
    }

    public long nextId() {
        return ++lastId;
    }
}
