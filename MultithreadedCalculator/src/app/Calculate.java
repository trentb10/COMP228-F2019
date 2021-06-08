package app;

public class Calculate extends Thread {
    private String calcType; // calculation type
    private int a, b; // first and second integers

    public Calculate(String calcType, int a, int b) {
        this.calcType = calcType;
        this.a = a;
        this.b = b;
    }

    public void run() {
        // Addition
        if (calcType == "add") {
            int ans = a + b; // answer
            System.out.println(a + " + " + b + " = " + ans);
        }
        // Subtraction
        else if (calcType == "subt") {
            int ans = a - b; // answer
            System.out.println(a + " - " + b + " = " + ans);
        }
        // Multiplication
        else if (calcType == "mulx") {
            int ans = a * b;
            System.out.println(a + " x " + b + " = " + ans);
        }
        // Division
        else if (calcType == "div") {
            int ans = a / b;
            System.out.println(a + " / " + b + " = " + ans);
        }
    }
}