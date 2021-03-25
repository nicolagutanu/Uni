package sample;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
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

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class MainWindowController {
    @FXML
    private ListView<IStatement> allPrgStates_listview;

    private List<IStatement> statementList;

    private SecondWindowController runPrgWindow;
    public void setSecondWindow(SecondWindowController runPrgW) {
        runPrgWindow=runPrgW;
    }

    public void populate() {
        IStatement ex1=new CompStmt(
                new VarDeclStmt("v", new IntType()),
                new CompStmt(
                        new AssignStmt("v", new ValueExp(new IntValue(2))),
                        new PrintStmt(new VarExp("v"))));

        IStatement ex2=new CompStmt(
                new VarDeclStmt("a", new IntType()),
                new CompStmt(new VarDeclStmt("b", new IntType()),
                        new CompStmt(new AssignStmt("a",
                                new ArithExp('+', new ValueExp(new IntValue(2)),
                                        new ArithExp('*', new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                                new CompStmt(new AssignStmt("b", new ArithExp('+', new VarExp("a"), new ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));

        IStatement ex3=new CompStmt(new VarDeclStmt("a", new BoolType()),
                new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))),
                                        new PrintStmt(new VarExp("v"))))));

        IStatement ex4=new CompStmt(new VarDeclStmt("fileName", new StringType()),
                new CompStmt(new AssignStmt("fileName", new ValueExp(new StringValue("D:\\School\\sem3\\APM\\lab7_plm\\test.in.txt"))),
                        new CompStmt(new OpenRFileStmt(new VarExp("fileName")),
                                new CompStmt(new VarDeclStmt("v", new IntType()),
                                        new CompStmt(new ReadFileStmt(new VarExp("fileName"), "v"),
                                                new CompStmt(new PrintStmt(new VarExp("v")),
                                                        new CompStmt(new ReadFileStmt(new VarExp("fileName"), "v"),
                                                                new CompStmt(new PrintStmt(new VarExp("v")), new CloseRFileStmt(new VarExp("fileName"))))))))));

        IStatement ex5=new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewHeapStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new NewHeapStmt("a", new VarExp("v")),
                                        new CompStmt(new PrintStmt(new VarExp("v")),
                                                new PrintStmt(new VarExp("a")))))));

        IStatement ex6=new CompStmt(
                new VarDeclStmt("a", new IntType()),
                new CompStmt(new VarDeclStmt("b", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new IntValue(2))),
                                new CompStmt(new AssignStmt("b", new ValueExp(new IntValue(5))), new PrintStmt(new RelationalExp(1, new VarExp("a"), new VarExp("b")))))));

        IStatement ex7=new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewHeapStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new NewHeapStmt("a", new VarExp("v")),
                                        new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v"))),
                                                new PrintStmt(new ArithExp('+', new ReadHeapExp(new ReadHeapExp(new VarExp("a"))),new ValueExp(new IntValue(5)))))))));

        IStatement ex8=new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewHeapStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v"))),
                                new CompStmt(new WriteHeapStmt("v", new ValueExp(new IntValue(30))),
                                        new PrintStmt(new ArithExp('+', new ReadHeapExp(new VarExp("v")), new ValueExp(new IntValue(5))))))));

        IStatement ex9=new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewHeapStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new NewHeapStmt("a", new VarExp("v")),
                                        new CompStmt(new NewHeapStmt("v", new ValueExp(new IntValue(30))),
                                                new PrintStmt(new ReadHeapExp(new ReadHeapExp(new VarExp("a")))))))));

        IStatement ex10=new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(4))),
                        new CompStmt(new WhileStmt(new RelationalExp(5, new VarExp("v"), new ValueExp(new IntValue(0))), new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithExp('-', new VarExp("v"), new ValueExp(new IntValue(1)))))),
                                new PrintStmt(new VarExp("v")))));

        IStatement ex11=new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
                        new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))),
                                new CompStmt(new NewHeapStmt("a", new ValueExp(new IntValue(22))),
                                        new CompStmt(new ForkStmt(
                                                new CompStmt(new WriteHeapStmt("a", new ValueExp(new IntValue(30))),
                                                        new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(32))),
                                                                new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHeapExp(new VarExp("a"))))))),
                                                new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHeapExp(new VarExp("a")))))))));

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

        IStatement ex13=new CompStmt(new VarDeclStmt("a",new BoolType()),
                new CompStmt(new AssignStmt("a",new ValueExp(new BoolValue(true))),
                        new CompStmt(new VarDeclStmt("v1",new IntType()),
                                new CompStmt(new AssignStmt("v1", new ValueExp(new IntValue(2))),
                                        new CompStmt(new VarDeclStmt("v2", new IntType()),
                                                new CompStmt(new AssignStmt("v2", new ValueExp(new IntValue(3))),
                                                        new IfStmt(new VarExp("a"), new PrintStmt(new MulExp(new VarExp("v1"), new VarExp("v2"))), new PrintStmt(new VarExp("v1")))))))));

        IStatement ex14=new CompStmt(new VarDeclStmt("v",new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new WaitStmt(new IntValue(10)),
                                new PrintStmt(new ArithExp('*',new VarExp("v"),new ValueExp(new IntValue(10)))))));

        IStatement ex15=new CompStmt(new VarDeclStmt("a",new IntType()),
                new CompStmt(new AssignStmt("a",new ValueExp(new IntValue(1))),
                        new CompStmt(new VarDeclStmt("b",new IntType()),
                                new CompStmt(new AssignStmt("b",new ValueExp(new IntValue(2))),
                                        new CompStmt(new VarDeclStmt("c",new IntType()),
                                                new CompStmt(new AssignStmt("c",new ValueExp(new IntValue(5))),
                                                        new CompStmt(new SwitchStmt(new ArithExp('*',new VarExp("a"),new ValueExp(new IntValue(10))),
                                                                new ArithExp('*',new VarExp("b"),new VarExp("c")),
                                                                new CompStmt(new PrintStmt(new VarExp("a")),new PrintStmt(new VarExp("b"))),
                                                                new ValueExp(new IntValue(10)),
                                                                new CompStmt(new PrintStmt(new ValueExp(new IntValue(100))),new PrintStmt(new ValueExp(new IntValue(200)))),
                                                                new PrintStmt(new ValueExp(new IntValue(300)))),
                                                                new PrintStmt(new ValueExp(new IntValue(300))))))))));

        IStatement ex16=new CompStmt(new VarDeclStmt("v",new IntType()),
                new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(10))),
                        new CompStmt(new ForkStmt(new CompStmt(new AssignStmt("v",new ArithExp('-',new VarExp("v"),new ValueExp(new IntValue(1)))),
                                new CompStmt(new AssignStmt("v",new ArithExp('-',new VarExp("v"),new ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("v"))))),
                                new CompStmt(new SleepStmt(new IntValue(10)),new PrintStmt(new ArithExp('*',new VarExp("v"),new ValueExp(new IntValue(10))))))));

        IStatement ex17=new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(0))),
                        new CompStmt(new RepeatStmt(new CompStmt(new ForkStmt(new CompStmt(new PrintStmt(new VarExp("v")),new AssignStmt("v",new ArithExp('-',new VarExp("v"),new ValueExp(new IntValue(1)))))),
                                new AssignStmt("v",new ArithExp('+',new VarExp("v"),new ValueExp(new IntValue(1))))),new RelationalExp(3,new VarExp("v"),new ValueExp(new IntValue(3)))),
                                new PrintStmt(new ArithExp('*',new VarExp("v"), new ValueExp(new IntValue(10)))))));

        IStatement ex18=new CompStmt(new VarDeclStmt("a",new RefType(new IntType())),
                new CompStmt(new VarDeclStmt("b", new RefType(new IntType())),
                        new CompStmt(new VarDeclStmt("v", new IntType()),
                                new CompStmt(new NewHeapStmt("a",new ValueExp(new IntValue(0))),
                                        new CompStmt(new NewHeapStmt("b",new ValueExp(new IntValue(0))),
                                                new CompStmt(new WriteHeapStmt("a",new ValueExp(new IntValue(1))),
                                                        new CompStmt(new WriteHeapStmt("b",new ValueExp(new IntValue(2))),
                                                                new CompStmt(new SymStmt("v",new RelationalExp(1,new ReadHeapExp(new VarExp("a")),new ReadHeapExp(new VarExp("b"))),new ValueExp(new IntValue(100)),new ValueExp(new IntValue(200))),
                                                                        new CompStmt(new PrintStmt(new VarExp("v")),
                                                                                new CompStmt(new SymStmt("v",new RelationalExp(5,
                                                                                        new ArithExp('-',new ReadHeapExp(new VarExp("b")),new ValueExp(new IntValue(2))),
                                                                                        new ReadHeapExp(new VarExp("a"))),new ValueExp(new IntValue(100)),new ValueExp(new IntValue(200))),new PrintStmt(new VarExp("v"))))))))))));

        IStatement ex19=new CompStmt(new VarDeclStmt("a",new RefType(new IntType())),
                new CompStmt(new NewHeapStmt("a",new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("v",new IntType()),
                                new CompStmt(new ForStmt("v",new ValueExp(new IntValue(0)),new ValueExp(new IntValue(3)),new ArithExp('+',new VarExp("v"),new ValueExp(new IntValue(1))),
                                        new ForkStmt(new CompStmt(new PrintStmt(new VarExp("v")),new AssignStmt("v",new ArithExp('*',new VarExp("v"),new ReadHeapExp(new VarExp("a"))))))),
                                        new PrintStmt(new ReadHeapExp(new VarExp("a")))))));

        IStatement ex20=new CompStmt(new VarDeclStmt("v1",new RefType(new IntType())),
                new CompStmt(new VarDeclStmt("v2",new RefType(new IntType())),
                        new CompStmt(new VarDeclStmt("x",new IntType()),
                                new CompStmt(new VarDeclStmt("q",new IntType()),
                                        new CompStmt(new NewHeapStmt("v1",new ValueExp(new IntValue(20))),
                                                new CompStmt(new NewHeapStmt("v2",new ValueExp(new IntValue(30))),
                                                        new CompStmt(new NewLockStmt("x"),
                                                                new CompStmt(new ForkStmt(new CompStmt(new ForkStmt(new CompStmt(new LockStmt("x"),
                                                                        new CompStmt(new WriteHeapStmt("v1",new ArithExp('-',new ReadHeapExp(new VarExp("v1")),new ValueExp(new IntValue(1)))),new UnlockStmt("x")))),
                                                                        new CompStmt(new LockStmt("x"),
                                                                                new CompStmt(new WriteHeapStmt("v1",new ArithExp('*',new ReadHeapExp(new VarExp("v1")),new ValueExp(new IntValue(10)))),new UnlockStmt("x"))))),
                                                                        new CompStmt(new NewLockStmt("q"),
                                                                                new CompStmt(new ForkStmt(new CompStmt(new ForkStmt(new CompStmt(new LockStmt("q"),
                                                                                        new CompStmt(new WriteHeapStmt("v2",new ArithExp('+',new ReadHeapExp(new VarExp("v2")),new ValueExp(new IntValue(5)))),new UnlockStmt("q")))),
                                                                                        new CompStmt(new LockStmt("q"),
                                                                                                new CompStmt(new WriteHeapStmt("v2",new ArithExp('*',new ReadHeapExp(new VarExp("v2")),new ValueExp(new IntValue(10)))),new UnlockStmt("q"))))),
                                                                                        new CompStmt(new NopStmt(),
                                                                                                new CompStmt(new NopStmt(),
                                                                                                        new CompStmt(new NopStmt(),
                                                                                                                new CompStmt(new NopStmt(),
                                                                                                                        new CompStmt(new LockStmt("x"),
                                                                                                                                new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v1"))),
                                                                                                                                        new CompStmt(new UnlockStmt("x"),
                                                                                                                                                new CompStmt(new LockStmt("q"),
                                                                                                                                                        new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v2"))), new UnlockStmt("q"))))))))))))))))))));

        IStatement ex21=new CompStmt(new VarDeclStmt("v1", new RefType(new IntType())),
                new CompStmt(new NewHeapStmt("v1",new ValueExp(new IntValue(2))),
                        new CompStmt(new VarDeclStmt("v2", new RefType(new IntType())),
                                new CompStmt(new NewHeapStmt("v2",new ValueExp(new IntValue(3))),
                                        new CompStmt(new VarDeclStmt("v3", new RefType(new IntType())),
                                                new CompStmt(new NewHeapStmt("v3", new ValueExp(new IntValue(4))),
                                                        new CompStmt(new VarDeclStmt("cnt",new IntType()),
                                                                new CompStmt(new NewBarrierStmt("cnt",new ReadHeapExp(new VarExp("v2"))),
                                                                        new CompStmt(new ForkStmt(new CompStmt(new AwaitStmt("cnt"), new CompStmt(new WriteHeapStmt("v1",new ArithExp('*',new ValueExp(new IntValue(10)),new ReadHeapExp(new VarExp("v1")))), new PrintStmt(new ReadHeapExp(new VarExp("v1")))))),
                                                                                new CompStmt(new ForkStmt(new CompStmt(new AwaitStmt("cnt"), new CompStmt(new WriteHeapStmt("v2",new ArithExp('*',new ValueExp(new IntValue(10)),new ReadHeapExp(new VarExp("v2")))),new CompStmt(new WriteHeapStmt("v2",new ArithExp('*',new ValueExp(new IntValue(10)),new ReadHeapExp(new VarExp("v2")))), new PrintStmt(new ReadHeapExp(new VarExp("v2"))))))),
                                                                                        new CompStmt(new AwaitStmt("cnt"),new PrintStmt(new ReadHeapExp(new VarExp("v3"))))))))))))));

        IStatement ex22=new CompStmt(new VarDeclStmt("v1",new RefType(new IntType())),
                new CompStmt(new VarDeclStmt("v2",new RefType(new IntType())),
                        new CompStmt(new VarDeclStmt("v3",new RefType(new IntType())),
                                new CompStmt(new NewHeapStmt("v1",new ValueExp(new IntValue(2))),
                                        new CompStmt(new NewHeapStmt("v2",new ValueExp(new IntValue(3))),
                                                new CompStmt(new NewHeapStmt("v3",new ValueExp(new IntValue(4))),
                                                        new CompStmt(new VarDeclStmt("cnt",new IntType()),
                                                                new CompStmt(new NewLatchStmt("cnt",new ReadHeapExp(new VarExp("v2"))),
                                                                        new CompStmt(new ForkStmt(new CompStmt(new WriteHeapStmt("v1",new ArithExp('*',new ReadHeapExp(new VarExp("v1")),new ValueExp(new IntValue(10)))),
                                                                                new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v1"))),
                                                                                        new CompStmt(new CountDownStmt("cnt"), new ForkStmt(new CompStmt(new WriteHeapStmt("v2",new ArithExp('*',new ReadHeapExp(new VarExp("v2")),new ValueExp(new IntValue(10)))),
                                                                                                new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v2"))),
                                                                                                        new CompStmt(new CountDownStmt("cnt"), new ForkStmt(new CompStmt(new WriteHeapStmt("v3",new ArithExp('*',new ReadHeapExp(new VarExp("v3")),new ValueExp(new IntValue(10)))),
                                                                                                                new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v3"))),new CountDownStmt("cnt")))))))))))),
                                                                                new CompStmt(new AwaitLatchStmt("cnt"),
                                                                                        new CompStmt(new PrintStmt(new ValueExp(new IntValue(100))),
                                                                                                new CompStmt(new CountDownStmt("cnt"), new PrintStmt(new ValueExp(new IntValue(100)))))))))))))));

        IStatement ex23=new CompStmt(new VarDeclStmt("v1",new RefType(new IntType())),
                new CompStmt(new VarDeclStmt("cnt",new IntType()),
                        new CompStmt(new NewHeapStmt("v1",new ValueExp(new IntValue(1))),
                                new CompStmt(new CreateSemaphoreStmt("cnt",new ReadHeapExp(new VarExp("v1"))),
                                        new CompStmt(new ForkStmt(new CompStmt(new AcquireStmt("cnt"),
                                                new CompStmt(new WriteHeapStmt("v1",new ArithExp('*',new ReadHeapExp(new VarExp("v1")),new ValueExp(new IntValue(10)))),
                                                        new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v1"))),new ReleaseStmt("cnt"))))),
                                                new CompStmt(new ForkStmt(new CompStmt(new AcquireStmt("cnt"),
                                                        new CompStmt(new WriteHeapStmt("v1",new ArithExp('*',new ReadHeapExp(new VarExp("v1")),new ValueExp(new IntValue(10)))),
                                                                new CompStmt(new WriteHeapStmt("v1",new ArithExp('*',new ReadHeapExp(new VarExp("v1")), new ValueExp(new IntValue(2)))),
                                                                        new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v1"))),new ReleaseStmt("cnt")))))),
                                                        new CompStmt(new AcquireStmt("cnt"),
                                                                new CompStmt(new PrintStmt(new ArithExp('-',new ReadHeapExp(new VarExp("v1")),new ValueExp(new IntValue(1)))), new ReleaseStmt("cnt")))))))));


        statementList=new ArrayList<>(Arrays.asList(ex1,ex2,ex3,ex4,ex5,ex6,ex7,ex8,ex9,ex10,ex11,ex12,ex13,ex14,ex15,ex16,ex17,ex18,ex19,ex20,ex21,ex22,ex23));
        ObservableList<IStatement> prgStates=FXCollections.observableArrayList(ex1,ex2,ex3,ex4,ex5,ex6,ex7,ex8,ex9,ex10,ex11,ex12,ex13,ex14,ex15,ex16,ex17,ex18,ex19,ex20,ex21,ex22,ex23);
        allPrgStates_listview.setItems(prgStates);
    }

    public void initialize() {
        populate();

        EventHandler<MouseEvent> eventHandlerForPrgStateList= new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int index=allPrgStates_listview.getSelectionModel().getSelectedIndex();

                try {
                    IStatement selectedStmt=statementList.get(index);
                    selectedStmt.typeCheck(new MyDictionary<String,Type>());

                    PrgState prg=new PrgState(new MyStack<>(), new MyDictionary<String, Value>(), new MyHeap<Value>(), new MyList<Value>(), new MyDictionary<StringValue, BufferedReader>(), new MyLock<Integer,Integer>(),new MyBarrier<Integer, Pair<Integer,List<Integer>>>(),new MyLatch<Integer,Integer>(),new MySemaphore<Integer, Pair<Integer,List<Integer>>>(),selectedStmt);
                    IRepo repo=new Repository(prg, "log"+index+".txt");
                    repo.add(prg);
                    Controller controller=new Controller(repo);
                    runPrgWindow.setController(controller);
                } catch (MyException| AdtException e) {
                    Alert alert=new Alert(Alert.AlertType.ERROR, "TypeCheck error: "+e.getMessage(), ButtonType.OK);
                    alert.show();
                    return;
                }
            }
        };
        allPrgStates_listview.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandlerForPrgStateList);
    }
}
