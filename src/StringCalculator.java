import java.util.Scanner;

public class StringCalculator {
    public void stringCalculate() throws Exception {
        Scanner console = new Scanner(System.in);
        String input = console.nextLine();
        String result;
        char operation;
        String[] data;
        if (input.contains(" + ")) {
            data = input.split(" \\+ ");
            operation = '+';
        } else if (input.contains(" - ")) {
            data = input.split(" - ");
            operation = '-';
        } else if (input.contains(" * ")) {
            data = input.split(" \\* ");
            operation = '*';
        } else if (input.contains(" / ")) {
            data = input.split(" / ");
            operation = '/';
        }else{
            throw new Exception("Некорректный знак действия.");
        }
        if (operation == '*' || operation == '/') {
            if (data[1].contains("\"")) throw new Exception("Строку можно делить или умножать только на число.");
            if (!data[0].contains("\"")) throw new Exception("Первым аргументом выражения должна быть строка.");
        }
        for (int i = 0; i < data.length; i++) {
            data[i] = data[i].replace("\"", "");
        }

        if (data[0].length() <= 10 && data[1].length() <= 10) {
            switch (operation) {
                case '+':
                    printInQuotes(data[0] + data[1]);
                    break;
                case '-':
                    int index = data[0].indexOf(data[1]);
                    if(index == -1){
                        printInQuotes(data[0]);
                    }else{
                        result = data[0].substring(0, index);
                        result+=data[0].substring(index+data[1].length());
                        printInQuotes(result);
                    }
                    break;
                case '*':
                    if (Integer.parseInt(data[1]) > 0 && Integer.parseInt(data[1]) <= 10) {
                        result = "";
                        for (int i = 0; i < Integer.parseInt(data[1]); i++) {
                            result+=data[0];
                        }
                        printInQuotes(result);
                        break;
                    } else {
                        throw new Exception("Множитель должен быть от 1 до 10");
                    }
                default:
                    if (Integer.parseInt(data[1]) > 0 && Integer.parseInt(data[1]) <= 10) {
                        int newLen = data[0].length()/Integer.parseInt(data[1]);
                        result = data[0].substring(0,newLen);
                        printInQuotes(result);
                        break;
                    } else {
                        throw new Exception("Делитель должен быть от 1 до 10");
                    }
            }
        } else {
            throw new Exception("Каждая строка не должна содержать в себе более 10ти символов.");
        }
    }
    static void printInQuotes(String text){
        if (text.length() > 40) {
            System.out.println("\""+text.substring(0,40)+"..."+"\"");
        } else {
            System.out.println("\""+text+"\"");
        }
    }
}
