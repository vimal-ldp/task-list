package com.codurance.training.tasks;

import com.codurance.training.tasks.service.MenuService;

public class TaskProcessor {

    public static void main(String[] args) throws Exception {
        new MenuService().run();
    }


}
