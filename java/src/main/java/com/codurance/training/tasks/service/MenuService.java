package com.codurance.training.tasks.service;

import com.codurance.training.tasks.model.Task;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MenuService implements Runnable{

    private static final String QUIT = "quit";

    private final Map<String, List<Task>> tasks = new LinkedHashMap<>();
    private UserEntry user=new UserEntry();
    TaskListService taskListService= new TaskServiceImpl();

    @Override
    public void run() {
        while (true) {
            user.getOut().print("> ");
            user.getOut().flush();
            String command;
            try {
                command = user.getIn().readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (command.equals(QUIT)) {
                break;
            }
            execute(command);
            System.out.println("\nTask list:"+tasks);
        }
    }

    private void execute(String commandLine) {
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "show":
                taskListService.show(tasks);
                break;
            case "add":
                taskListService.add(tasks,commandRest[1]);
                break;
            case "check":
                taskListService.check(tasks,commandRest[1]);
                break;
            case "uncheck":
                taskListService.uncheck(tasks,commandRest[1]);
                break;
            case "help":
                taskListService.help();
                break;
            default:
                taskListService.error(command);
                break;
        }
    }
}
