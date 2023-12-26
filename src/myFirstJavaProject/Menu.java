package myFirstJavaProject;

import java.util.Scanner;

public class Menu {
    static void menu(){
        Scanner scanner=new Scanner(System.in);
        System.out.print(""" 
                Добро пожаловать в банковскую систему , перед вам существуют вот такие дейсвтия:
                  1: вызов операции парсинга файлов перевода из input
                  2: вызов операции вывода списка всех переводов из  файла-отчета.
                  """);
        System.out.print("Пожалуйста введите выбранную вами цифру: ");
        if(scanner.hasNextInt()){
            final int THE_SELECTED_NUMBER = scanner.nextInt();
            switch (THE_SELECTED_NUMBER){
                case 1:
                    System.out.println("Первое");
                    break;
                case 2:
                    System.out.println("Второе");
                    break;
                default:
                    System.out.println("Ошибка, вы ввели число , которое не поддерживает система!!!");
                    break;
            }
        }
        else {
            System.out.println("Вы ввели не число!!!");
        }
    }
}
