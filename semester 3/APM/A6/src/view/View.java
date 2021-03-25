package view;

import controller.Controller;
import model.adt.*;
import model.exceptions.MyException;
import model.expression.ArithExp;
import model.expression.ValueExp;
import model.expression.VarExp;
import model.statement.*;
import model.type.BoolType;
import model.type.IntType;
import model.value.BoolValue;
import model.value.IntValue;
import model.value.StringValue;
import model.value.Value;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class View {
    Controller controller;

    public View(Controller c) { controller=c; }

    public void menu() {
        System.out.println("Press 1-3 for a hardcoded example");
        System.out.println("1 - int v; v=2; Print(v);");
        System.out.println("2 - int a; int b; a=2+3*5; b=a+1; Print(b);");
        System.out.println("3 - bool a; int v; a=true; (If a Then v=2 Else v=3); Print(v);");
        System.out.println("4 - int a; a=true; error: type don't match");
        System.out.println("5 - a=5; error: var not defined");
        System.out.println("0 - exit");
    }

    public void run() {
        Scanner scanner=new Scanner(System.in);
        while (true){
            menu();
            int command = scanner.nextInt();
            if (command==1){
                try{
                    IStack<IStatement> stack=new MyStack<>();
                    IStatement ex1=new CompStmt(
                            new VarDeclStmt("v", new IntType()),
                            new CompStmt(
                                    new AssignStmt("v", new ValueExp(new IntValue(2))),
                                    new PrintStmt(new VarExp("v"))));
                    stack.push(ex1);
                    PrgState state=new PrgState(stack, new MyDictionary<String, Value>(), new MyHeap<Value>(), new MyList<Value>(), new MyDictionary<StringValue, BufferedReader>(), ex1);
                    System.out.println(state);
                    controller.addState(state);
                    controller.allStep();
                }catch(MyException | IOException | InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }else if (command==2){
                try{
                    IStack<IStatement> stack=new MyStack<>();
                    IStatement ex2=new CompStmt(
                            new VarDeclStmt("a", new IntType()),
                            new CompStmt(new VarDeclStmt("b", new IntType()),
                                    new CompStmt(new AssignStmt("a",
                                            new ArithExp('+', new ValueExp(new IntValue(2)),
                                                    new ArithExp('*', new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                                            new CompStmt(new AssignStmt("b", new ArithExp('+', new VarExp("a"), new ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));
                    stack.push(ex2);
                    PrgState state=new PrgState(stack, new MyDictionary<String, Value>(), new MyHeap<Value>(), new MyList<Value>(), new MyDictionary<StringValue, BufferedReader>(), ex2);
                    System.out.println(state);
                    controller.addState(state);
                    controller.allStep();
                }catch(MyException | IOException | InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }else if (command==3){
                try{
                    IStack<IStatement> stack=new MyStack<>();
                    IStatement ex3=new CompStmt(new VarDeclStmt("a", new BoolType()),
                            new CompStmt(new VarDeclStmt("v", new IntType()),
                                    new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                            new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))),
                                                    new PrintStmt(new VarExp("v"))))));
                    stack.push(ex3);
                    PrgState state=new PrgState(stack, new MyDictionary<String, Value>(), new MyHeap<Value>(), new MyList<Value>(), new MyDictionary<StringValue, BufferedReader>(), ex3);
                    System.out.println(state);
                    controller.addState(state);
                    controller.allStep();
                }catch (MyException | IOException | InterruptedException e){
                    System.out.println(e.getMessage());
                }
            } else if (command==0){
                System.exit(0);
            }else if(command==4){
                try{
                    IStack<IStatement> stack=new MyStack<>();
                    IStatement ex4=new CompStmt(
                            new VarDeclStmt("a", new IntType()),
                            new CompStmt(
                                    new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                    new PrintStmt(new VarExp("a"))));
                    stack.push(ex4);
                    PrgState state=new PrgState(stack, new MyDictionary<String, Value>(), new MyHeap<Value>(), new MyList<Value>(), new MyDictionary<StringValue, BufferedReader>(), ex4);
                    System.out.println(state);
                    controller.addState(state);
                    controller.allStep();
                }catch (MyException | IOException | InterruptedException e){
                    System.out.println(e.getMessage());
                }
            }else if (command==5){
                try{
                    IStack<IStatement> stack=new MyStack<>();
                    IStatement ex5=new CompStmt(new AssignStmt("a", new ValueExp(new IntValue(5))),
                                    new PrintStmt(new VarExp("a")));
                    stack.push(ex5);
                    PrgState state=new PrgState(stack, new MyDictionary<String, Value>(), new MyHeap<Value>(), new MyList<Value>(), new MyDictionary<StringValue, BufferedReader>(), ex5);
                    System.out.println(state);
                    controller.addState(state);
                    controller.allStep();
                }catch (MyException | IOException | InterruptedException e){
                    System.out.println(e.getMessage());
                }
            }else System.out.println("No such command\n");
        }
    }
}
