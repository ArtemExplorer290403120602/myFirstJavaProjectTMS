package myFirstJavaProject;

import actions.ParsingMoney;
import actions.TransferMoneyToAnAccount;
import actions.ViewingTheTotal;

import java.io.*;
import java.util.Scanner;

public class Menu extends TransferMoneyToAnAccount {
    protected static void menu() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(REPORT_FILE));

            // Вывод списка всех переводов из файла-отчета
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            reader.close();

            System.out.print(""" 
                    Добро пожаловать в банковскую систему , перед вам существуют вот такие дейсвтия:
                      1: Парсинг файлов перевода из input
                      2: Вывод списка всех переводов из файла-отчета.
                      """);
            System.out.print("Пожалуйста введите выбранную вами цифру: ");
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            int action = Integer.parseInt(consoleReader.readLine());

                switch (action) {
                    case 1: ParsingMoney.parseFiles();
                        break;
                    case 2:
                        ViewingTheTotal.showReport();
                        break;
                    default:
                        System.out.println("Некорректный выбор действия");
                        break;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
