package repo;
import exception.Exception;
import model.*;
import exception.*;

public interface Repo{
    public void add(Trees tree) throws Exception;
    public int getSize();
    public Trees[] getAll();
}

