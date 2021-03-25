package sample;

import controller.Controller;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Pair;
import model.adt.*;
import model.exceptions.MyException;
import model.statement.IStatement;
import model.value.StringValue;
import model.value.Value;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class SecondWindowController {
    @FXML
    private Label nrPrgState_label;
    @FXML
    private TextField nrPrgStates_textfield;
    @FXML
    private Label exeStack_label;
    @FXML
    private ListView<String> exeStack_listview;
    @FXML
    private Label symTable_label;
    @FXML
    private TableView<SymTableView> symTable_tableview;
    @FXML
    private TableColumn<SymTableView,String> symTable_variableColumn;
    @FXML
    private TableColumn<SymTableView,String> symTable_valueColumn;
    @FXML
    private Label heapTable_label;
    @FXML
    private TableView<HeapTableView> heapTable_tableview;
    @FXML
    private TableColumn<HeapTableView,Integer> heapTable_addressColumn;
    @FXML
    private TableColumn<HeapTableView,Integer> heapTable_valueColumn;
    @FXML
    private Label out_label;
    @FXML
    private ListView<String> out_listview;
    @FXML
    private Label fileTable_label;
    @FXML
    private ListView<String> fileTable_listview;
    @FXML
    private Label prgStateIds_label;
    @FXML
    private ListView<Integer> prgStateIds_listview;
    @FXML
    private Label lockTable_label;
    @FXML
    private TableView<LockTableView> lockTable_tableview;
    @FXML
    private TableColumn<LockTableView,Integer> lockTable_addressColumn;
    @FXML
    private TableColumn<LockTableView,Integer> lockTable_valueColumn;
    @FXML
    private Label latchTable_label;
    @FXML
    private TableView<LatchTableView> latchTable_tableview;
    @FXML
    private TableColumn<LatchTableView,Integer> latchTable_locationColumn;
    @FXML
    private TableColumn<LatchTableView,Integer> latchTable_valueColumn;
    @FXML
    private Label barrierTable_label;
    @FXML
    private TableView<BarrierTableView> barrierTable_tableview;
    @FXML
    private TableColumn<BarrierTableView,Integer> barrierTable_indexColumn;
    @FXML
    private TableColumn<BarrierTableView,Integer> barrierTable_valueColumn;
    @FXML
    private TableColumn<BarrierTableView, List<Integer>> barrierTable_listColumn;
    @FXML
    private Label semaphoreTable_label;
    @FXML
    private TableView<SemaphoreTableView> semaphoreTable_tableview;
    @FXML
    private TableColumn<SemaphoreTableView,Integer> semaphoreTable_indexColumn;
    @FXML
    private TableColumn<SemaphoreTableView,Integer> semaphoreTable_valueColumn;
    @FXML
    private TableColumn<SemaphoreTableView,List<Integer>> semaphoreTable_listColumn;
    @FXML
    private Button runOneStep_button;

    Controller controller;

    public void initialize() {
        symTable_variableColumn.setCellValueFactory(new PropertyValueFactory<SymTableView,String>("variable"));
        symTable_valueColumn.setCellValueFactory(new PropertyValueFactory<SymTableView,String>("value"));

        heapTable_addressColumn.setCellValueFactory(new PropertyValueFactory<HeapTableView,Integer>("address"));
        heapTable_valueColumn.setCellValueFactory(new PropertyValueFactory<HeapTableView,Integer>("value"));

        lockTable_addressColumn.setCellValueFactory(new PropertyValueFactory<LockTableView,Integer>("address"));
        lockTable_valueColumn.setCellValueFactory(new PropertyValueFactory<LockTableView,Integer>("value"));

        barrierTable_indexColumn.setCellValueFactory(new PropertyValueFactory<BarrierTableView,Integer>("address"));
        barrierTable_valueColumn.setCellValueFactory(new PropertyValueFactory<BarrierTableView,Integer>("value"));
        barrierTable_listColumn.setCellValueFactory(new PropertyValueFactory<BarrierTableView,List<Integer>>("list"));

        exeStack_listview.getItems().clear();
        symTable_tableview.getItems().clear();
        heapTable_tableview.getItems().clear();
        out_listview.getItems().clear();
        fileTable_listview.getItems().clear();
        prgStateIds_listview.getItems().clear();
        lockTable_tableview.getItems().clear();

        EventHandler<MouseEvent> eventHandlerForRunOneStep=new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    runOneStep();
                }catch (InterruptedException| IOException | MyException e) {
                    Alert alert=new Alert(Alert.AlertType.ERROR,"Error: "+e.getMessage(),ButtonType.OK);
                    alert.show();
                    return;
                }
            }
        };
        runOneStep_button.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandlerForRunOneStep);
    }

    public void setController(Controller c) {
        controller=c;
        populateNrPrgStates_textfield();
        populateHeapTable();
        populateOutput();
        populateFileTable();;
        populatePrgStateIds();
        populateLockTable();
        populateBarrierTable();
    }

    //a
    public void populateNrPrgStates_textfield() {
        int totalPrgStates=controller.getRepo().getAll().size();
        nrPrgStates_textfield.setText(""+totalPrgStates);
    }

    //b
    public void populateHeapTable() {
        if (!controller.getRepo().getAll().isEmpty()) {
            IHeap<Value> heap=controller.getRepo().getAll().get(0).getHeap();
            heapTable_tableview.getItems().clear();
            for (Map.Entry<Integer,Value> e:heap.getContent().entrySet())
                heapTable_tableview.getItems().add(new HeapTableView(e.getKey(),e.getValue()));
        }
    }

    //c
    public void populateOutput() {
        if (!controller.getRepo().getAll().isEmpty()) {
            Iterable<Value> out=controller.getRepo().getAll().get(0).getList().getAll();
            out_listview.getItems().clear();
            for (Value v:out)
                out_listview.getItems().add(""+v);
        }
    }

    //d
    public void populateFileTable() {
        if (!controller.getRepo().getAll().isEmpty()) {
            IDictionary<StringValue, BufferedReader> fileTable=controller.getRepo().getAll().get(0).getFileTable();
            fileTable_listview.getItems().clear();
            for (Map.Entry<StringValue,BufferedReader> e:fileTable.getContent().entrySet())
                fileTable_listview.getItems().add(e.getKey().toString());
        }
    }

    //e
    public void populatePrgStateIds() {
        prgStateIds_listview.getItems().clear();
        if (!controller.getRepo().getAll().isEmpty())
            for (PrgState state:controller.getRepo().getAll())
                prgStateIds_listview.getItems().add(state.getId());
    }

    //f
    public void populateSymTable() {
        if (prgStateIds_listview.getSelectionModel().getSelectedIndex()<0)
            return;
        int index = prgStateIds_listview.getSelectionModel().getSelectedItem();
        PrgState prg=controller.getRepo().getPrgStateById(index);
        IDictionary<String, Value> symTable=prg.getDictionary();
        symTable_tableview.getItems().clear();
        if (!symTable.getContent().entrySet().isEmpty())
            for (Map.Entry<String,Value> e:symTable.getContent().entrySet())
                symTable_tableview.getItems().add(new SymTableView(e.getKey(),e.getValue()));
    }

    //g
    public void populateExeStack() {
        if (prgStateIds_listview.getSelectionModel().getSelectedIndex()<0)
            return;
        int index = prgStateIds_listview.getSelectionModel().getSelectedItem();
        PrgState prg=controller.getRepo().getPrgStateById(index);
        Iterable<IStatement> exeStack=prg.getStack().getAll();
        exeStack_listview.getItems().clear();
        if (!prg.getStack().isEmpty())
            for (IStatement state: exeStack)
                exeStack_listview.getItems().add(""+state);
    }

    //h
    public void runOneStep() throws InterruptedException, IOException, MyException {
        if (controller.getRepo().getAll().size()<1) {
            Alert alert=new Alert(Alert.AlertType.WARNING,"Program is done", ButtonType.OK);
            alert.show();
            return;
        }else {
            controller.allStepGUI();
            populateExeStack();
            populateSymTable();
            populateHeapTable();
            populateOutput();
            populateFileTable();
            populatePrgStateIds();
            populateLockTable();
            populateBarrierTable();
        }
    }

    public void populateLockTable() {
        if (!controller.getRepo().getAll().isEmpty()) {
            ILock<Integer,Integer> lockTable=controller.getRepo().getAll().get(0).getLockTable();
            lockTable_tableview.getItems().clear();
            for (Map.Entry<Integer,Integer> e:lockTable.getContent().entrySet())
                lockTable_tableview.getItems().add(new LockTableView(e.getKey(),e.getValue()));
        }
    }

    public void populateBarrierTable() {
        if (!controller.getRepo().getAll().isEmpty()) {
            IBarrier<Integer, Pair<Integer,List<Integer>>> barrierTable=controller.getRepo().getAll().get(0).getBarrierTable();
            barrierTable_tableview.getItems().clear();
            for (Map.Entry<Integer,Pair<Integer,List<Integer>>> e:barrierTable.getContent().entrySet())
                barrierTable_tableview.getItems().add(new BarrierTableView(e.getKey(),e.getValue().getKey(),e.getValue().getValue()));
        }
    }
}
