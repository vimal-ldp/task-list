package com.codurance.training.tasks.processor;

import com.codurance.training.tasks.model.Task;
import com.codurance.training.tasks.service.TaskMenuService;
import com.codurance.training.tasks.service.TaskMenuServiceImpl;
import com.codurance.training.tasks.service.TaskUtilityService;
import com.codurance.training.tasks.service.TaskUtilityServiceImpl;

import java.util.List;
import java.util.Map;

public class MenuExcecutor  {

    TaskMenuService taskMenuService;
    TaskUtilityService utilityService;

    public MenuExcecutor() {

        this.taskMenuService =  new TaskMenuServiceImpl();
        this.utilityService= new TaskUtilityServiceImpl();
    }

    void execute(String commandLine, Map<String, List<Task>> tasks) {
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "show":
                taskMenuService.show(tasks);
                break;
            case "add":
                taskMenuService.add(tasks,commandRest[1]);
                break;
            case "check":
                utilityService.check(tasks,commandRest[1]);
                break;
            case "uncheck":
                utilityService.uncheck(tasks,commandRest[1]);
                break;
            case "help":
                utilityService.help();
                break;
            default:
                utilityService.error(command);
                break;
        }
    }

}
