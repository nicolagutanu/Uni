package model;

public class Pear implements Trees {
    int ageP;

    public Pear(int a) {
        ageP = a;
    }

    public int getAge() {
        return ageP;
    }

    public String toString() {
        return "Pear" + " " + ageP;
    }

}
