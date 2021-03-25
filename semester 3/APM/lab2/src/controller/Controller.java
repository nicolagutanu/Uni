package controller;
import exception.Exception;
import model.*;
import repo.*;

public class Controller {
    private Repo repository;

    public Controller(Repo repo){repository = repo;}

    public void addApple(int age) throws Exception{
        Trees newTree = new Apple(age);
        repository.add(newTree);
    }

    public void addPear(int age) throws Exception{
        Trees newTree = new Pear(age);
        repository.add(newTree);
    }

    public void addCherry(int age) throws Exception{
        Trees newTree = new Cherry(age);
        repository.add(newTree);
    }

    public Trees[] filter(){
        Trees[] filteredTrees = new Trees[50];
        int sizeT = 0;
        Trees[] treesArray = repository.getAll();
        int length = repository.getSize();
        for (int i=0; i<length;i++)
            if (treesArray[i].getAge()>3){
                filteredTrees[sizeT] = treesArray[i];
                sizeT++;
            }
        return filteredTrees;
    }

    public int getSizeFiltered(){
        int sizeT = 0;
        Trees[] treesArray = repository.getAll();
        int length = repository.getSize();
        for (int i=0; i<length;i++)
            if (treesArray[i].getAge()>3){

                sizeT++;
            }
        return sizeT;
    }

    public Trees[] getAll(){
        return repository.getAll();
    }

    public int getSize(){
        return repository.getSize();
    }
}
