package view;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TextMenu {
    Map<String, Command> commands;

    public TextMenu() { commands=new HashMap<>(); }

    public void addCommand(Command comm) { commands.put(comm.getKey(), comm); }

    private void printMenu() {
        for (Command comm:commands.values()) {
            String line=String.format("%4s : %s", comm.getKey(), comm.getDescription());
            System.out.println(line);
        }
    }

    public void show() {
        Scanner scanner=new Scanner(System.in);
        while (true) {
            printMenu();
            System.out.println("Input your command: ");
            String key=scanner.nextLine();
            Command comm=commands.get(key);
            if (comm==null) {
                System.out.println("Not a command\n");
                continue;
            }
            comm.execute();
        }
    }
}
