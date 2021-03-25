package main;
import exception.Exception;
import model.*;
import repo.*;
import controller.*;
import view.*;
import exception.*;

public class Main {
    public static  void main(String[] args){
        Repo repository = new Repository();
        Controller controller = new Controller(repository);
        View view = new View(controller);
        view.run();
    }
}
