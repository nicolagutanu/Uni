package first_java;

import java.util.Scanner;

public class firstly {
    public static void printOut(int c, int i)
    {
        System.out.print("The number is prime");
    }
    public static void printOut(boolean c, int i)
    {
        System.out.print(i + " ");
    }
    public static void primeDiv(int n) throws Exception
    {
        if (n<0)
        {
            throw new Exception("Please input a number greater than 0");
        }
        if (n == 0 || n==1)
        {
            System.out.print("The number "+ n +" is divisible");
        }
        int copyN = n;
        int check = 0;
        for (int i=2; i<=Math.sqrt(n)+1; i++)
        {
            boolean checkD=false;
            while (copyN%i==0)
            {
                copyN = copyN/i;
                checkD = true;
                check+=1;
            }
            if (checkD) {
                printOut(checkD, i);
            }
        }
        if (check == 0)
        {
            printOut(check,0);
        }
    }
    public static void main(String[] args)
    {
        Scanner console = new Scanner(System.in);
        int givenNumber;
        while ((givenNumber=console.nextInt())!=-1000000)
        {
            //int givenNumber = console.nextInt();
            try{
                primeDiv(givenNumber);
            }
            catch(Exception ex){
                System.out.print(ex.getMessage());
            }
            System.out.println();
        }
    }
}
