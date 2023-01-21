package src.main.java;

import java.util.Scanner;

class CalculatorArab {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите задачу в формате a оператор b: ");
        String expression = scanner.nextLine();
        System.out.println(parse(expression));
    }

    public static String parse(String expression) throws Exception {
        int num1;
        int num2;
        String operator;
        String res;
        boolean isRoman;
        String[] operands = expression.split("[+\\-*/]");
        if (operands.length != 2)
            throw new Exception("т.к. формат математической операции не удовлетворяет заданию - два оперсанда и один оператор");
        operator = detectOperation(expression);
        if (Roman.isRoman(operands[0]) && Roman.isRoman(operands[1])) {
            num1 = Roman.convertToArabian(operands[0]);
            num2 = Roman.convertToArabian(operands[1]);
            isRoman = true;
        } else if (!Roman.isRoman(operands[0]) && !Roman.isRoman(operands[1])) {
            num1 = Integer.parseInt(operands[0]);
            num2 = Integer.parseInt(operands[1]);
            isRoman = false;
        } else {
            throw new Exception("Используются одновременно разные системы счисления");
        }
        if (num1 > 10 || num2 > 10) {
            throw new Exception("Числа должны быть от 1 до 10");
        }
        int arabian = calc(num1, num2, operator);
        if (isRoman) {
            if (arabian <= 0) {
                throw new Exception("В римской системе нет отрицательных чисел");
            }
            res = Roman.convertToRoman(arabian);
        } else {
            res = String.valueOf(arabian);
        }
        return res;
    }

    static String detectOperation(String expression) throws Exception {
        if (expression.contains("+")) {
            return "+";
        } else if (expression.contains("-")) {
            return "-";
        } else if (expression.contains("*")) {
            return "*";
        } else if (expression.contains("/")) {
            return "/";
        } else {
            throw new Exception("нет такогй опперации");
        }
    }

    static int calc(int a, int b, String operator) {
        return switch (operator) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            default -> a / b;
        };
    }
}