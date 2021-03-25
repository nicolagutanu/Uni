package main;

import controller.Controller;
import model.adt.*;
import model.expression.ArithExp;
import model.expression.RelationalExp;
import model.expression.ValueExp;
import model.expression.VarExp;
import model.statement.*;
import model.type.BoolType;
import model.type.IntType;
import model.type.StringType;
import model.value.BoolValue;
import model.value.IntValue;
import model.value.StringValue;
import model.value.Value;
import repository.IRepo;
import repository.Repository;
import view.ExitCommand;
import view.RunExample;
import view.TextMenu;
import view.View;

import java.io.BufferedReader;

public class Main {

    public static void main(String[] args) {
        /*IRepo repo=new Repository();
        Controller controller=new Controller(repo);
        View view=new View(controller);
        view.run();*/

        IStatement ex1=new CompStmt(
                new VarDeclStmt("v", new IntType()),
                new CompStmt(
                        new AssignStmt("v", new ValueExp(new IntValue(2))),
                        new PrintStmt(new VarExp("v"))));
        IStack<IStatement> stack1=new MyStack<>();
        PrgState prg1=new PrgState(stack1, new MyDictionary<String, Value>(), new MyList<Value>(), new MyDictionary<StringValue, BufferedReader>(), ex1);
        IRepo repo1=new Repository(prg1, "log1.txt");
        Controller controller1=new Controller(repo1);

        IStatement ex2=new CompStmt(
                new VarDeclStmt("a", new IntType()),
                new CompStmt(new VarDeclStmt("b", new IntType()),
                        new CompStmt(new AssignStmt("a",
                                new ArithExp('+', new ValueExp(new IntValue(2)),
                                        new ArithExp('*', new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                                new CompStmt(new AssignStmt("b", new ArithExp('+', new VarExp("a"), new ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));
        IStack<IStatement> stack2=new MyStack<>();
        PrgState prg2=new PrgState(stack2, new MyDictionary<String, Value>(), new MyList<Value>(), new MyDictionary<StringValue, BufferedReader>(), ex2);
        IRepo repo2=new Repository(prg2, "log2.txt");
        Controller controller2=new Controller(repo2);

        IStatement ex3=new CompStmt(new VarDeclStmt("a", new BoolType()),
                new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))),
                                        new PrintStmt(new VarExp("v"))))));
        IStack<IStatement> stack3=new MyStack<>();
        PrgState prg3=new PrgState(stack3, new MyDictionary<String, Value>(), new MyList<Value>(), new MyDictionary<StringValue, BufferedReader>(), ex3);
        IRepo repo3=new Repository(prg3, "log3.txt");
        Controller controller3=new Controller(repo3);

        IStatement ex4=new CompStmt(new VarDeclStmt("fileName", new StringType()),
                new CompStmt(new AssignStmt("fileName", new ValueExp(new StringValue("E:\\School\\sem3\\APM\\lab3\\test.in.txt"))),
                        new CompStmt(new OpenRFileStmt(new VarExp("fileName")),
                                new CompStmt(new VarDeclStmt("v", new IntType()),
                                        new CompStmt(new ReadFileStmt(new VarExp("fileName"), "v"),
                                                new CompStmt(new PrintStmt(new VarExp("v")),
                                                        new CompStmt(new ReadFileStmt(new VarExp("fileName"), "v"),
                                                                new CompStmt(new PrintStmt(new VarExp("v")), new CloseRFileStmt(new VarExp("fileName"))))))))));
        IStack<IStatement> stack4=new MyStack<>();
        PrgState prg4=new PrgState(stack4, new MyDictionary<String, Value>(), new MyList<Value>(), new MyDictionary<StringValue, BufferedReader>(), ex4);
        IRepo repo4=new Repository(prg4, "log4.txt");
        Controller controller4=new Controller(repo4);

        IStatement ex5=new CompStmt(new VarDeclStmt("fileName", new StringType()),
                new CompStmt(new AssignStmt("fileName", new ValueExp(new StringValue("E:\\School\\sem3\\APM\\lab3\\test_fail.in.txt"))),
                        new CompStmt(new OpenRFileStmt(new VarExp("fileName")),
                                new CompStmt(new VarDeclStmt("v", new IntType()),
                                        new CompStmt(new ReadFileStmt(new VarExp("fileName"), "v"),
                                                new CompStmt(new PrintStmt(new VarExp("v")),
                                                        new CompStmt(new ReadFileStmt(new VarExp("fileName"), "v"),
                                                                new CompStmt(new PrintStmt(new VarExp("v")), new CloseRFileStmt(new VarExp("fileName"))))))))));
        IStack<IStatement> stack5=new MyStack<>();
        PrgState prg5=new PrgState(stack5, new MyDictionary<String, Value>(), new MyList<Value>(), new MyDictionary<StringValue, BufferedReader>(), ex5);
        IRepo repo5=new Repository(prg5, "log5.txt");
        Controller controller5=new Controller(repo5);

        IStatement ex6=new CompStmt(
                new VarDeclStmt("a", new IntType()),
                new CompStmt(new VarDeclStmt("b", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new IntValue(2))),
                                new CompStmt(new AssignStmt("b", new ValueExp(new IntValue(5))), new PrintStmt(new RelationalExp(1, new VarExp("a"), new VarExp("b")))))));
        IStack<IStatement> stack6=new MyStack<>();
        PrgState prg6=new PrgState(stack6, new MyDictionary<String, Value>(), new MyList<Value>(), new MyDictionary<StringValue, BufferedReader>(), ex6);
        IRepo repo6=new Repository(prg6, "log6.txt");
        Controller controller6=new Controller(repo6);

        repo1.add(prg1);
        repo2.add(prg2);
        repo3.add(prg3);
        repo4.add(prg4);
        repo5.add(prg5);
        repo6.add(prg6);

        TextMenu menu=new TextMenu();
        menu.addCommand((new ExitCommand("0", "exit")));
        menu.addCommand(new RunExample("1", ex1.toString(), controller1));
        menu.addCommand(new RunExample("2", ex2.toString(), controller2));
        menu.addCommand(new RunExample("3", ex3.toString(), controller3));
        menu.addCommand(new RunExample("4", ex4.toString(), controller4));
        menu.addCommand(new RunExample("5", ex5.toString(), controller5));
        menu.addCommand(new RunExample("6", ex6.toString(), controller6));
        menu.show();
    }
}
