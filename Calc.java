package calc;

import java.util.List;
import java.util.Scanner;

public class Calc {
    private static final String[] operation = {"+", "-", "*", "/"};
    
    public static void runCalc() {
        int aNum1 = 0, aNum2 = 0;
        String rNum1 = null, rNum2 = null, oper;
        
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите выражение 'a + b' через пробел, где 'a' и 'b' - ");
        System.out.println("- целые арабские числа от 1 до 10 или римские числа от I до X,");
        System.out.println("'+' - знак операции (+, -, *, /), и нажмите клавишу 'Enter'");
        
        if (scan.hasNextInt()) {
            aNum1 = scan.nextInt();
            isCorrectANum(aNum1);
        }
        else {
            rNum1 = scan.next();
            isCorrectANum(romanToArabic(rNum1));
        }
        
        oper = scan.next();
        isCorrectOperation(oper);
        
        if (scan.hasNextInt()) {
            aNum2 = scan.nextInt();
            isCorrectANum(aNum2);
        }
        else {
            rNum2 = scan.next();
            isCorrectANum(romanToArabic(rNum2));
        }
        
        if ((aNum1 != 0 && rNum2 != null) || (aNum2 != 0 && rNum1 != null)) {
            System.err.println("Введены неверные данные!");
            System.exit(1);
        }
        
        if (rNum1 != null && rNum2 != null)
            System.out.println(calculate(rNum1, rNum2, oper));
        else
            System.out.println(calculate(aNum1, aNum2, oper));
        
        scan.close();
    }
    private static void isCorrectANum(int num) {
        if (num < 1 || num > 10) {
            System.err.println("Введены неверные данные!");
            System.exit(1);
        }
    }
    private static void isCorrectOperation(String oper) {
        int i = 0;
        for (String s : operation) {
            i = oper.compareTo(s);
            if (i == 0)
                break;
        }
        if (i != 0) {
            System.err.println("Введена неверная операция!");
            System.exit(1);
        }
    }
    private static int romanToArabic(String romanNumeral) {
        int result = 0;
        
        List<RomanNum> romanNumerals = RomanNum.getReverseSortedValues();

        int i = 0;
        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
            RomanNum symbol = romanNumerals.get(i);
            if (romanNumeral.startsWith(symbol.name())) {
                result += symbol.getValue();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            }
            else
                i++;
        }

        if (romanNumeral.length() > 0) {
            System.err.println("Введены неверные данные!");
            System.exit(1);
        }

        return result;
    }
    private static String arabicToRoman(int number) {
        if ((number <= 0) || (number > 100)) {
            System.err.println("Результат вне диапазона [1 - 100]");
            System.exit(1);
        }

        List<RomanNum> romanNumerals = RomanNum.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNum currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            }
            else
                i++;
        }

        return sb.toString();
    }
    private static int calculate(int num1, int num2, String op) {
        int result = 0;
        switch (op) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
        }
        
        return result;
    }
    private static String calculate(String num1, String num2, String op) {
        int a = romanToArabic(num1);
        int b = romanToArabic(num2);
        int result = 0;
        
        switch (op) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                result = a / b;
        }
        
        return arabicToRoman(result);
    }
}
