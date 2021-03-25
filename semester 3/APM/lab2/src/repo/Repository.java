package repo;

import exception.Exception;
import model.Trees;

public class Repository implements Repo{
        Trees[] orchard;
        int size;
        private int capacity = 50;

        public Repository(){
            orchard = new Trees[capacity];
            size=0;
        }

        public void add(Trees tree) throws Exception {
            if (size==capacity){
                throw new Exception("we're at full capacity!");
            }
            else {
                orchard[size] = tree;
                size++;
            }
        }

        public int getSize() {return size;}

        public Trees[] getAll(){
            return orchard;
        }
}
