package actions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Vtregw extends TransferMoneyToAnAccount{
    public static void showReportByDateRange(String startDate, String endDate) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(REPORT_FILE));

        String line;
        boolean isWithinRange = false;
        while ((line = reader.readLine()) != null) {
            // Проверка, находится ли дата записи в заданном диапазоне
            if (line.startsWith(startDate)) {
                isWithinRange = true;
            }

            if (isWithinRange) {
                System.out.println(line);
            }

            if (line.startsWith(endDate)) {
                isWithinRange = false;
                break;  // Выход из цикла после того, как достигнута конечная дата
            }
        }

        reader.close();
    }

}
