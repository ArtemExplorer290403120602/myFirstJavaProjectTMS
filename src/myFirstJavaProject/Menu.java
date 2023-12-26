package myFirstJavaProject;

import actions.ParsingMoney;
import actions.TransferMoneyToAnAccount;
import actions.ViewingTheTotal;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu extends TransferMoneyToAnAccount {
    protected static void menu() {
        boolean exit = false;
        while (!exit) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(REPORT_FILE));

                // Вывод списка всех переводов из файла-отчета
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
                reader.close();

                System.out.println("""
                    Добро пожаловать в банковскую систему. Перед вами доступны следующие действия:
                      1: Парсинг файлов перевода из input
                      2: Вывод списка всех переводов из файла-отчета
                      0: Завершить программу
                    """);
                System.out.print("Пожалуйста, введите выбранную вами цифру: ");
                BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
                int action = Integer.parseInt(consoleReader.readLine());

                switch (action) {
                    case 0:
                        exit = true;
                        break;
                    case 1:
                        ParsingMoney.parseFiles();
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
            } catch (NumberFormatException e) {
                System.out.println("Ошибка ввода. Пожалуйста, введите числовое значение.");
            }
        }
    }
}