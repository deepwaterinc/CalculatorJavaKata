package CalculatorJava;

public class InputException extends Exception{
    public InputException() {
//        System.out.println("Что то пошло не так");
    }
    public InputException(String message){
        this();
        System.out.println(message);
    }
}
