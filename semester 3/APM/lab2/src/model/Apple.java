package model;

public class Apple implements Trees {
    int ageA;

    public Apple(int a) {
        ageA = a;
    }

    public int getAge() {
        return ageA;
    }

    public String toString() {
        return "Apple" + " " + ageA;
    }
}

