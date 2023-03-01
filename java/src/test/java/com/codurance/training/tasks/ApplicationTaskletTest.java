package com.codurance.training.tasks;

import com.codurance.training.tasks.model.Task;

import com.codurance.training.tasks.service.TaskMenuService;
import com.codurance.training.tasks.service.TaskMenuServiceImpl;
import com.codurance.training.tasks.service.TaskUtilityService;
import com.codurance.training.tasks.service.TaskUtilityServiceImpl;
import org.junit.Test;
import org.junit.Assert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class ApplicationTaskletTest {

    private final Map<String, List<Task>> tasks;
    private final TaskMenuService menuService;
    private final TaskUtilityService utilityService;
    private final PipedOutputStream inStream = new PipedOutputStream();
    private final PrintWriter inWriter = new PrintWriter(inStream, true);


    private final PipedInputStream outStream = new PipedInputStream();
    private final BufferedReader outReader = new BufferedReader(new InputStreamReader(outStream));


    public ApplicationTaskletTest() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(new PipedInputStream(inStream)));
        PrintWriter out = new PrintWriter(new PipedOutputStream(outStream), true);
;
        tasks = new LinkedHashMap<>();
        menuService = new TaskMenuServiceImpl();
        utilityService = new TaskUtilityServiceImpl();


    }


    @Test
    public void it_works() throws IOException {

        //  execute("show");
        menuService.show(tasks);


        // execute("add project secrets");
        menuService.add(tasks, "project secrets");
        //  execute("add task secrets Eat more donuts.");
        menuService.add(tasks, "task secrets Eat more donuts.");
        //  execute("add task secrets Destroy all humans.");
        menuService.add(tasks, "task secrets Destroy all humans.");

        //   execute("show");
        Assert.assertEquals(tasks.entrySet().size(),1);
        menuService.show(tasks);

//        execute("add project training");
        menuService.add(tasks, "project training");
//        execute("add task training Four Elements of Simple Design");
        menuService.add(tasks, "task training Four Elements of Simple Design");
//        execute("add task training SOLID");
        menuService.add(tasks, "task training SOLID");
//        execute("add task training Coupling and Cohesion");
        menuService.add(tasks, "task training Coupling and Cohesion");
//        execute("add task training Primitive Obsession");
        menuService.add(tasks, "add task training Primitive Obsession");
//        execute("add task training Outside-In TDD");
        menuService.add(tasks, "task training Outside-In TDD");
//        execute("add task training Interaction-Driven Design");
        menuService.add(tasks, "task training Interaction-Driven Design");
//
        Assert.assertEquals(tasks.get("training").size(),5);
        menuService.show(tasks);

//        execute("check 1");
        utilityService.check(tasks, "1");
//        execute("check 3");
        utilityService.check(tasks, "3");
//        execute("check 5");
        utilityService.check(tasks, "5");
//        execute("check 6");
        utilityService.check(tasks, "6");

//
//        execute("show");
        menuService.show(tasks);
        Assert.assertEquals(tasks.get("training").get(3).isDone(),true);
        Assert.assertEquals(tasks.get("training").size(),5);

    }



}
