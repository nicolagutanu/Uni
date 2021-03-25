package view;

import controller.Controller;
import model.exceptions.MyException;
import model.value.StringValue;

import java.io.IOException;

public class RunExample extends Command {
    Controller controller;

    public RunExample(String key, String desc, Controller c) {
        super(key, desc);
        controller=c;
    }

    @Override
    public void execute() {
        try {
            controller.allStep();
        }
        catch (MyException | IOException | InterruptedException e) {
            System.out.println(e.toString());
        }
    }
}
