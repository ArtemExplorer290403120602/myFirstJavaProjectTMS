package actions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ViewingTheTotal extends TransferMoneyToAnAccount{
    private static void showReport() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(REPORT_FILE));

        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        reader.close();
    }
}
