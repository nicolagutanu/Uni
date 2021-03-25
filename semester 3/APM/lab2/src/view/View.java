package view;
import exception.Exception;
import model.*;
import controller.*;
import exception.*;
import java.util.Scanner;

public class View {
    private Controller controller;

    public View(Controller cont){controller = cont;}

    public void print_filteredTrees(){
        Trees[] filteredTrees = controller.filter();
        int size = controller.getSizeFiltered();
        for (int i=0; i<size;i++)
            System.out.println(filteredTrees[i].toString());
    }

    public void menu(){
        System.out.println("1 - Add Apple");
        System.out.println("2 - Add Pear");
        System.out.println("3 - Add Cherry");
        System.out.println("4 - Filter");
        System.out.println("5 - Exit");
    }

    public void run(){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            menu();
            int command = scanner.nextInt();
            int age;
            if (command == 1) {
                System.out.println("Enter the age of the apple: ");
                age = scanner.nextInt();
                try {
                    if (age < 1) throw new NegativeAge("Age has to be above 0");
                } catch (NegativeAge exception) {
                    System.out.println(exception.getMessage());
                }
                try {
                    controller.addApple(age);
                } catch (Exception exp) {
                    System.out.println(exp.getMessage());
                }
            } else if (command == 2) {
                System.out.println("Enter the age of the apple: ");
                age = scanner.nextInt();
                try {
                    if (age < 1) throw new NegativeAge("Age has to be above 0");
                } catch (NegativeAge exception) {
                    System.out.println(exception.getMessage());
                }
                try {
                    controller.addPear(age);
                } catch (Exception exp) {
                    System.out.println(exp.getMessage());
                }
            }else if (command == 3) {
                System.out.println("Enter the age of the apple: ");
                age = scanner.nextInt();
                try {
                    if (age < 1) throw new NegativeAge("Age has to be above 0");
                } catch (NegativeAge exception) {
                    System.out.println(exception.getMessage());
                }
                try {
                    controller.addCherry(age);
                } catch (Exception exp) {
                    System.out.println(exp.getMessage());
                }
            }else if (command == 4){
                print_filteredTrees();
            }else if (command == 5){
                System.exit(0);
            }else{
                System.out.println("Not an existing command");
            }
        }
    }
}
