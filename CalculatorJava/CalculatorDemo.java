package CalculatorJava;

import java.util.Scanner;

public class CalculatorDemo {

    public static void main(String[] args) throws InputException{
        Scanner scanner = new Scanner(System.in); // Читаем данные на входе
        String input = scanner.nextLine(); // Записываем введенные данные в пер. input
        System.out.println(parse(input)); // Парсим введенные данные c помощью метода parse
    }
    public static String parse(String input) throws InputException {
        int a =0;
        int b =0;
        String operator;
        String result = null;
        boolean isRome = true;

        String[] strArray = input.split("[+\\-*/]"); // делим данные по знаку
        operator = detectOperator(input); // определяем оператор методом detectOperator
        if(operator == null) {
            throw new InputException("Строка не является математической операцией");
        }
        if(strArray.length != 2) {
            throw new InputException("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        strArray[0] = strArray[0].replaceAll("\\s", ""); // Убираем пробелы в операндах
        strArray[1] = strArray[1].replaceAll("\\s", "");
        if(Rome.isRome(strArray[0]) && Rome.isRome(strArray[1])){
            a = Rome.changeToArab(strArray[0]);
            b = Rome.changeToArab(strArray[1]);
            isRome = true;
        }
        else if(!Rome.isRome(strArray[0]) && !Rome.isRome(strArray[1])){
            a = Integer.parseInt(strArray[0]);
            b = Integer.parseInt(strArray[1]);
            isRome = false;
        }
        else {
            throw new InputException("Используются одновременно разные системы счисления");
        }

        if (a > 10 || b > 10){
            throw new InputException("Числа не должны быть больше 10!");
        }
        int arab = calc(a, b, operator);
        if (isRome) { //проверка на отрицательные римские числа
            if (arab <= 0){
                throw new InputException("В римской системе нет oтрицательных чисел");
            }
            result = Rome.changeToRome(arab);
        }
        else {
            result = String.valueOf(arab);
        }
        return result;
    }

    public static int calc(int arg1, int arg2, String arg3){ // Считаем
        if  (arg3.equals("+")) return arg1 + arg2;
        else if (arg3.equals("-")) return arg1 - arg2;
        else if (arg3.equals("*")) return arg1 * arg2;
        else if (arg3.equals("/")) return arg1 / arg2;
        else return 0;
    }


    public static String detectOperator(String input) { // Определяем что за оператор введен
        if (input.contains("+")) return "+";
        else if (input.contains("-")) return "-";
        else if (input.contains("*")) return "*";
        else if (input.contains("/")) return "/";
        else return null;
    }
}

class Rome {
    public static String[] romeArray = new String[]{
            "zero","I","II","III","IV","V","VI","VII","VIII","IX","X","XI","XII","XIII","XIV","XV","XVI","XVII","XVIII","XIX","XX","XXI","XXII","XXIII","XXIV","XXV","XXVI","XXVII","XXVIII","XXIX","XXX","XXXI","XXXII","XXXIII","XXXIV","XXXV","XXXVI","XXXVII","XXXVIII","XXXIX","XL","XLI","XLII","XLIII","XLIV","XLV","XLVI","XLVII","XLVIII","XLIX","L","LI","LII","LIII","LIV","LV","LVI","LVII","LVIII","LIX","LX","LXI","LXII","LXIII","LXIV","LXV","LXVI","LXVII","LXVIII","LXIX","LXX","LXXI","LXXII","LXXIII","LXXIV","LXXV","LXXVI","LXXVII","LXXVIII","LXXIX","LXXX","LXXXI","LXXXII","LXXXIII","LXXXIV","LXXXV","LXXXVI","LXXXVII","LXXXVIII","LXXXIX","XC","XCI","XCII","XCIII","XCIV","XCV","XCVI","XCVII","XCVIII","XCIX","C"
    };
    public static boolean isRome(String arg) { //  Проверяем является ли число римским
        for(int i = 0; i < romeArray.length; i++){
            if(arg.equals(romeArray[i])){
                return true;
            }
        }
        return false;
    }

    public static int changeToArab(String arg) { // конвертирует римское число в арабское
        for (int i = 0; i < romeArray.length; i++){
            if (arg.equals(romeArray[i])){
                return i;
            }
        }
        return -1;
    }
    public static String changeToRome(int arg){ // конвертирует арабское число в римское
        return romeArray[arg];
    }
}


