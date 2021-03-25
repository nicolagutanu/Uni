package main;

import controller.Controller;
import javafx.util.Pair;
import model.adt.*;
import model.exceptions.AdtException;
import model.exceptions.MyException;
import model.expression.*;
import model.statement.*;
import model.type.*;
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
import java.util.List;

public class Main {

    public static void main(String[] args) throws AdtException, MyException {
        TextMenu menu=new TextMenu();

        try{
            IStatement ex1=new CompStmt(
                    new VarDeclStmt("v", new IntType()),
                    new CompStmt(
                            new AssignStmt("v", new ValueExp(new IntValue(2))),
                            new PrintStmt(new VarExp("v"))));
            ex1.typeCheck(new MyDictionary<String, Type>());
            IStack<IStatement> stack1=new MyStack<>();
            PrgState prg1=new PrgState(stack1, new MyDictionary<String, Value>(), new MyHeap<Value>(), new MyList<Value>(), new MyDictionary<StringValue, BufferedReader>(), new MyBarrier<Integer, Pair<Integer, List<Integer>>>(),ex1);
            IRepo repo1=new Repository(prg1, "log1.txt");
            Controller controller1=new Controller(repo1);
            repo1.add(prg1);
            menu.addCommand(new RunExample("1", ex1.toString(), controller1));
        }catch (MyException e) {
            System.out.println("ex1: "+e.getMessage());
        }

        /*
        try{
            IStatement ex2=new CompStmt(
                    new VarDeclStmt("a", new IntType()),
                    new CompStmt(new VarDeclStmt("b", new IntType()),
                            new CompStmt(new AssignStmt("a",
                                    new ArithExp('+', new ValueExp(new IntValue(2)),
                                            new ArithExp('*', new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                                    new CompStmt(new AssignStmt("b", new ArithExp('+', new VarExp("a"), new ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));
            ex2.typeCheck(new MyDictionary<String, Type>());
            IStack<IStatement> stack2=new MyStack<>();
            PrgState prg2=new PrgState(stack2, new MyDictionary<String, Value>(), new MyHeap<Value>(), new MyList<Value>(), new MyDictionary<StringValue, BufferedReader>(), ex2);
            IRepo repo2=new Repository(prg2, "log2.txt");
            Controller controller2=new Controller(repo2);
            repo2.add(prg2);
            menu.addCommand(new RunExample("2", ex2.toString(), controller2));
        }catch (MyException e) {
            System.out.println("ex2: "+e.getMessage());
        }

        try{
            IStatement ex3=new CompStmt(new VarDeclStmt("a", new BoolType()),
                    new CompStmt(new VarDeclStmt("v", new IntType()),
                            new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                    new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))),
                                            new PrintStmt(new VarExp("v"))))));
            ex3.typeCheck(new MyDictionary<String, Type>());
            IStack<IStatement> stack3=new MyStack<>();
            PrgState prg3=new PrgState(stack3, new MyDictionary<String, Value>(), new MyHeap<Value>(), new MyList<Value>(), new MyDictionary<StringValue, BufferedReader>(), ex3);
            IRepo repo3=new Repository(prg3, "log3.txt");
            Controller controller3=new Controller(repo3);
            repo3.add(prg3);
            menu.addCommand(new RunExample("3", ex3.toString(), controller3));
        }catch (MyException e) {
            System.out.println("ex3: "+e.getMessage());
        }

        try {
            IStatement ex4=new CompStmt(new VarDeclStmt("fileName", new StringType()),
                    new CompStmt(new AssignStmt("fileName", new ValueExp(new StringValue("D:\\School\\sem3\\APM\\lab5\\test.in.txt"))),
                            new CompStmt(new OpenRFileStmt(new VarExp("fileName")),
                                    new CompStmt(new VarDeclStmt("v", new IntType()),
                                            new CompStmt(new ReadFileStmt(new VarExp("fileName"), "v"),
                                                    new CompStmt(new PrintStmt(new VarExp("v")),
                                                            new CompStmt(new ReadFileStmt(new VarExp("fileName"), "v"),
                                                                    new CompStmt(new PrintStmt(new VarExp("v")), new CloseRFileStmt(new VarExp("fileName"))))))))));
            ex4.typeCheck(new MyDictionary<String, Type>());
            IStack<IStatement> stack4=new MyStack<>();
            PrgState prg4=new PrgState(stack4, new MyDictionary<String, Value>(), new MyHeap<Value>(), new MyList<Value>(), new MyDictionary<StringValue, BufferedReader>(), ex4);
            IRepo repo4=new Repository(prg4, "log4.txt");
            Controller controller4=new Controller(repo4);
            repo4.add(prg4);
            menu.addCommand(new RunExample("4", ex4.toString(), controller4));
        }catch (MyException e){
            System.out.println("ex4: "+e.getMessage());
        }

        try{
            IStatement ex5=new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                    new CompStmt(new NewHeapStmt("v", new ValueExp(new IntValue(20))),
                            new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                    new CompStmt(new NewHeapStmt("a", new VarExp("v")),
                                            new CompStmt(new PrintStmt(new VarExp("v")),
                                                    new PrintStmt(new VarExp("a")))))));
            ex5.typeCheck(new MyDictionary<String, Type>());
            IStack<IStatement> stack5=new MyStack<>();
            PrgState prg5=new PrgState(stack5, new MyDictionary<String, Value>(), new MyHeap<Value>(), new MyList<Value>(), new MyDictionary<StringValue, BufferedReader>(), ex5);
            IRepo repo5=new Repository(prg5, "log5.txt");
            Controller controller5=new Controller(repo5);
            repo5.add(prg5);
            menu.addCommand(new RunExample("5", ex5.toString(), controller5));
        }catch (MyException e) {
            System.out.println("ex5: "+e.getMessage());
        }

        try{
            IStatement ex6=new CompStmt(
                    new VarDeclStmt("a", new IntType()),
                    new CompStmt(new VarDeclStmt("b", new IntType()),
                            new CompStmt(new AssignStmt("a", new ValueExp(new IntValue(2))),
                                    new CompStmt(new AssignStmt("b", new ValueExp(new IntValue(5))), new PrintStmt(new RelationalExp(1, new VarExp("a"), new VarExp("b")))))));
            ex6.typeCheck(new MyDictionary<String, Type>());
            IStack<IStatement> stack6=new MyStack<>();
            PrgState prg6=new PrgState(stack6, new MyDictionary<String, Value>(), new MyHeap<Value>(), new MyList<Value>(), new MyDictionary<StringValue, BufferedReader>(), ex6);
            IRepo repo6=new Repository(prg6, "log6.txt");
            Controller controller6=new Controller(repo6);
            repo6.add(prg6);
            menu.addCommand(new RunExample("6", ex6.toString(), controller6));
        }catch (MyException e) {
            System.out.println("ex6: "+e.getMessage());
        }

        try{
            IStatement ex7=new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                    new CompStmt(new NewHeapStmt("v", new ValueExp(new IntValue(20))),
                            new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                    new CompStmt(new NewHeapStmt("a", new VarExp("v")),
                                            new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v"))),
                                                    new PrintStmt(new ArithExp('+', new ReadHeapExp(new ReadHeapExp(new VarExp("a"))),new ValueExp(new IntValue(5)))))))));
            ex7.typeCheck(new MyDictionary<String, Type>());
            IStack<IStatement> stack7=new MyStack<>();
            PrgState prg7=new PrgState(stack7, new MyDictionary<String, Value>(), new MyHeap<Value>(), new MyList<Value>(), new MyDictionary<StringValue, BufferedReader>(), ex7);
            IRepo repo7=new Repository(prg7, "log7.txt");
            Controller controller7=new Controller(repo7);
            repo7.add(prg7);
            menu.addCommand(new RunExample("7", ex7.toString(), controller7));
        }catch (MyException e) {
            System.out.println("ex7: "+e.getMessage());
        }

        try{
            IStatement ex8=new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                    new CompStmt(new NewHeapStmt("v", new ValueExp(new IntValue(20))),
                            new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v"))),
                                    new CompStmt(new WriteHeapStmt("v", new ValueExp(new IntValue(30))),
                                            new PrintStmt(new ArithExp('+', new ReadHeapExp(new VarExp("v")), new ValueExp(new IntValue(5))))))));
            ex8.typeCheck(new MyDictionary<String, Type>());
            IStack<IStatement> stack8=new MyStack<>();
            PrgState prg8=new PrgState(stack8, new MyDictionary<String, Value>(), new MyHeap<Value>(), new MyList<Value>(), new MyDictionary<StringValue, BufferedReader>(), ex8);
            IRepo repo8=new Repository(prg8, "log8.txt");
            Controller controller8=new Controller(repo8);
            repo8.add(prg8);
            menu.addCommand(new RunExample("8", ex8.toString(), controller8));
        }catch (MyException e) {
            System.out.println("ex8: "+e.getMessage());
        }

        try{
            IStatement ex9=new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                    new CompStmt(new NewHeapStmt("v", new ValueExp(new IntValue(20))),
                            new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                    new CompStmt(new NewHeapStmt("a", new VarExp("v")),
                                            new CompStmt(new NewHeapStmt("v", new ValueExp(new IntValue(30))),
                                                    new PrintStmt(new ReadHeapExp(new ReadHeapExp(new VarExp("a")))))))));
            ex9.typeCheck(new MyDictionary<String, Type>());
            IStack<IStatement> stack9=new MyStack<>();
            PrgState prg9=new PrgState(stack9, new MyDictionary<String, Value>(), new MyHeap<Value>(), new MyList<Value>(), new MyDictionary<StringValue, BufferedReader>(), ex9);
            IRepo repo9=new Repository(prg9, "log9.txt");
            Controller controller9=new Controller(repo9);
            repo9.add(prg9);
            menu.addCommand(new RunExample("9", ex9.toString(), controller9));
        }catch (MyException e) {
            System.out.println("ex9: "+e.getMessage());
        }

        try{
            IStatement ex10=new CompStmt(new VarDeclStmt("v", new IntType()),
                    new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(4))),
                            new CompStmt(new WhileStmt(new RelationalExp(5, new VarExp("v"), new ValueExp(new IntValue(0))), new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithExp('-', new VarExp("v"), new ValueExp(new IntValue(1)))))),
                                    new PrintStmt(new VarExp("v")))));
            ex10.typeCheck(new MyDictionary<String, Type>());
            IStack<IStatement> stack10=new MyStack<>();
            PrgState prg10=new PrgState(stack10, new MyDictionary<String, Value>(), new MyHeap<Value>(), new MyList<Value>(), new MyDictionary<StringValue, BufferedReader>(), ex10);
            IRepo repo10=new Repository(prg10, "log10.txt");
            Controller controller10=new Controller(repo10);
            repo10.add(prg10);
            menu.addCommand(new RunExample("10", ex10.toString(), controller10));
        }catch (MyException e) {
            System.out.println("ex10: "+e.getMessage());
        }

        try{
            IStatement ex11=new CompStmt(new VarDeclStmt("v", new IntType()),
                    new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
                            new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))),
                                    new CompStmt(new NewHeapStmt("a", new ValueExp(new IntValue(22))),
                                            new CompStmt(new ForkStmt(
                                                    new CompStmt(new WriteHeapStmt("a", new ValueExp(new IntValue(30))),
                                                            new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(32))),
                                                                    new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHeapExp(new VarExp("a"))))))),
                                                    new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHeapExp(new VarExp("a")))))))));
            ex11.typeCheck(new MyDictionary<String, Type>());
            IStack<IStatement> stack11=new MyStack<>();
            PrgState prg11=new PrgState(stack11, new MyDictionary<String, Value>(), new MyHeap<Value>(), new MyList<Value>(), new MyDictionary<StringValue, BufferedReader>(), ex11);
            IRepo repo11=new Repository(prg11, "log11.txt");
            Controller controller11=new Controller(repo11);
            repo11.add(prg11);
            menu.addCommand(new RunExample("11", ex11.toString(), controller11));
        }catch (MyException e) {
            System.out.println("ex11: "+e.getMessage());
        }

        try{
            IStatement ex12=new CompStmt(new VarDeclStmt("v", new IntType()),
                    new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
                            new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))),
                                    new CompStmt(new NewHeapStmt("a", new ValueExp(new IntValue(22))),
                                            new CompStmt(new ForkStmt(
                                                    new CompStmt(new WriteHeapStmt("a", new ValueExp(new IntValue(30))),
                                                            new CompStmt(new ForkStmt(new PrintStmt(new VarExp("v"))),
                                                                    new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(32))),
                                                                            new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHeapExp(new VarExp("a")))))))),
                                                    new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHeapExp(new VarExp("a")))))))));
            ex12.typeCheck(new MyDictionary<String, Type>());
            IStack<IStatement> stack12=new MyStack<>();
            PrgState prg12=new PrgState(stack12, new MyDictionary<String, Value>(), new MyHeap<Value>(), new MyList<Value>(), new MyDictionary<StringValue, BufferedReader>(), ex12);
            IRepo repo12=new Repository(prg12, "log12.txt");
            Controller controller12=new Controller(repo12);
            repo12.add(prg12);
            menu.addCommand(new RunExample("12", ex12.toString(), controller12));
        }catch (MyException e) {
            System.out.println("ex12: "+e.getMessage());
        }

        try{
            IStatement ex13=new CompStmt(
                    new VarDeclStmt("v", new StringType()),
                    new CompStmt(
                            new AssignStmt("v", new ValueExp(new IntValue(2))),
                            new PrintStmt(new VarExp("v"))));
            ex13.typeCheck(new MyDictionary<String, Type>());
            IStack<IStatement> stack13=new MyStack<>();
            PrgState prg13=new PrgState(stack13, new MyDictionary<String, Value>(), new MyHeap<Value>(), new MyList<Value>(), new MyDictionary<StringValue, BufferedReader>(), ex13);
            IRepo repo13=new Repository(prg13, "log13.txt");
            Controller controller13=new Controller(repo13);
            repo13.add(prg13);
            menu.addCommand(new RunExample("13", ex13.toString(), controller13));
        }catch (MyException e) {
            System.out.println("ex13: "+e.getMessage());
        }
         */

        try{
            IStatement ex2=new CompStmt(new VarDeclStmt("v1",new RefType(new IntType())),
                    new CompStmt(new VarDeclStmt("v2",new RefType(new IntType())),
                            new CompStmt(new VarDeclStmt("v3",new RefType(new IntType())),
                                    new CompStmt(new VarDeclStmt("cnt",new IntType()),
                                            new CompStmt(new NewHeapStmt("v1",new ValueExp(new IntValue(2))),
                                                    new CompStmt(new NewHeapStmt("v2",new ValueExp(new IntValue(3))),
                                                            new CompStmt(new NewHeapStmt("v3",new ValueExp(new IntValue(4))),
                                                                    new CompStmt(new NewBarrierStmt("cnt",new ReadHeapExp(new VarExp("v2"))),
                                                                            new CompStmt(new ForkStmt(new CompStmt(new AwaitStmt("cnt"),
                                                                                    new CompStmt(new WriteHeapStmt("v1",new ArithExp('*',new ReadHeapExp(new VarExp("v1")),new ValueExp(new IntValue(10)))),new PrintStmt(new ReadHeapExp(new VarExp("v1")))))),
                                                                                    new CompStmt(new ForkStmt(new CompStmt(new AwaitStmt("cnt"),
                                                                                            new CompStmt(new WriteHeapStmt("v2",new ArithExp('*',new ReadHeapExp(new VarExp("v2")),new ValueExp(new IntValue(10)))),
                                                                                                    new CompStmt(new WriteHeapStmt("v2",new ArithExp('*',new ReadHeapExp(new VarExp("v2")),
                                                                                                            new ValueExp(new IntValue(10)))),new PrintStmt(new ReadHeapExp(new VarExp("v2"))))))),
                                                                                            new CompStmt(new AwaitStmt("cnt"),new PrintStmt(new ReadHeapExp(new VarExp("v3"))))))))))))));
            ex2.typeCheck(new MyDictionary<String, Type>());
            IStack<IStatement> stack2=new MyStack<>();
            PrgState prg2=new PrgState(stack2, new MyDictionary<String, Value>(), new MyHeap<Value>(), new MyList<Value>(), new MyDictionary<StringValue, BufferedReader>(), new MyBarrier<Integer,Pair<Integer,List<Integer>>>(),ex2);
            IRepo repo2=new Repository(prg2, "log2.txt");
            Controller controller2=new Controller(repo2);
            repo2.add(prg2);
            menu.addCommand(new RunExample("2", ex2.toString(), controller2));
        }catch (MyException e) {
            System.out.println("ex2: "+e.getMessage());
        }

        menu.addCommand((new ExitCommand("0", "exit")));
        menu.show();
    }
}
