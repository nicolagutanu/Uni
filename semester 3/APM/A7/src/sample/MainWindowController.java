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

        statementList=new ArrayList<>(Arrays.asList(ex1,ex2,ex3,ex4,ex5,ex6,ex7,ex8,ex9,ex10,ex11,ex12));
        ObservableList<IStatement> prgStates=FXCollections.observableArrayList(ex1,ex2,ex3,ex4,ex5,ex6,ex7,ex8,ex9,ex10,ex11,ex12);
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

                    PrgState prg=new PrgState(new MyStack<>(), new MyDictionary<String, Value>(), new MyHeap<Value>(), new MyList<Value>(), new MyDictionary<StringValue, BufferedReader>(), selectedStmt);
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
