package com.codurance.training.tasks.processor;

import com.codurance.training.tasks.model.Task;
import com.codurance.training.tasks.model.UserEntry;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MenuService implements Runnable{

    private static final String QUIT = "quit";

    private final Map<String, List<Task>> tasks = new LinkedHashMap<>();
    private final UserEntry user;

    private final MenuExcecuterService excecutor;

    public MenuService() {
        this.user = new UserEntry();
        excecutor =new MenuExcecutorServiceImpl();
    }

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
            excecutor.execute(command,tasks);
            System.out.println("\nTask list:"+tasks);
        }
    }

  }
