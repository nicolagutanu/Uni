package model;

public class Cherry implements Trees{
    int ageC;

    public Cherry(int a) {
        ageC = a;
    }

    public int getAge() {
        return ageC;
    }

    public String toString() {
        return "Cherry" + " " + ageC;
    }

}
