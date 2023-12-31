package actions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParsingMoney extends TransferMoneyToAnAccount{
    private static final String DB_URL = "jdbc:mysql://Artem:3306/tms1";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root2";

    public static void parseFiles() throws IOException {
        File inputDirectory = new File(INPUT_DIRECTORY);
        if (!inputDirectory.exists() || !inputDirectory.isDirectory()) {
            System.out.println("Каталог input не найден или не является директорией");
            return;
        }

        File[] files = inputDirectory.listFiles();
        if (files == null || files.length == 0) {
            System.out.println("В каталоге input нет подходящих файлов");
            return;
        }

        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".txt")) {
                processFile(file);
                moveFileToArchive(file);
            }
        }
    }

    private static String getCurrentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentDate = new Date();
        return dateFormat.format(currentDate);
    }

    private static void insertIntoDatabase(String dateTime, String fromAccount, String toAccount, double amount) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO MoneyTransfers (date_time, from_account, to_account, amount) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, dateTime);
                preparedStatement.setString(2, fromAccount);
                preparedStatement.setString(3, toAccount);
                preparedStatement.setDouble(4, amount);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void processFile(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        FileWriter reportWriter = new FileWriter(REPORT_FILE, true);

        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\\|");

            if (parts.length != 3) {
                reportWriter.write(getCurrentDateTime() + " | " + file.getName() + " | неверный формат записи\n");
                continue;
            }

            String fromAccount = parts[0].replaceAll(":", "").trim();
            String toAccount = parts[1].trim();
            String amountStr = parts[2].replaceAll(":", "").trim();

            // Проверка наличия проблем с форматом номера счета
            if (!isValidAccount(fromAccount) || !isValidAccount(toAccount)) {
                reportWriter.write(getCurrentDateTime() + " | " + file.getName() + " | неверный формат номера счета\n");
                continue;
            }

            // Проверка, является ли amountStr числом
            try {
                double amount = Double.parseDouble(amountStr);

                // Проверка на положительное значение amount
                if (amount <= 0) {
                    reportWriter.write(getCurrentDateTime() + " | " + file.getName() + " | перевод суммы " + amount + " недопустим\n");
                    continue;
                }

                // Выполнение перевода с одного счета на другой
                performTransfer(fromAccount, toAccount, amount);

                // Вставка данных в базу данных
                insertIntoDatabase(getCurrentDateTime(), fromAccount, toAccount, amount);

                reportWriter.write(getCurrentDateTime() + " | " + file.getName() + " | перевод с " + fromAccount + " на " + toAccount + " " + String.format("%.2f", amount) + " | успешно обработан\n");
            } catch (NumberFormatException e) {
                reportWriter.write(getCurrentDateTime() + " | " + file.getName() + " | неверный формат суммы перевода\n");
            }
        }

        reader.close();
        reportWriter.close();
    }

    private static void moveFileToArchive(File file) {
        File archiveDirectory = new File(ARCHIVE_DIRECTORY);
        if (!archiveDirectory.exists()) {
            archiveDirectory.mkdir();
        }

        String archiveFilePath = ARCHIVE_DIRECTORY + File.separator + file.getName();
        File archiveFile = new File(archiveFilePath);
        file.renameTo(archiveFile);
    }

    private static boolean isValidAccount(String account) {
        // Проверка формата номера счета
        // Вернуть true, если номер счета валидный, иначе false
        return account.matches("\\d{5}-\\d{5}");
    }

    private static void performTransfer(String fromAccount, String toAccount, double amount) {
        System.out.println("Выполняется перевод счета " + fromAccount + " на счет " + toAccount +
                " в размере " + amount + " рублей");
    }
}
