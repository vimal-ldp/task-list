package com.codurance.training.tasks.service;

import com.codurance.training.tasks.model.Task;
import com.codurance.training.tasks.model.UserEntry;

import java.util.List;
import java.util.Map;

public class TaskUtilityServiceImpl implements TaskUtilityService{

    private long lastId = 0;
    private UserEntry user;

    public TaskUtilityServiceImpl() {
        user=new UserEntry();
    }

    public void check(Map<String, List<Task>> tasks, String idString) {
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
