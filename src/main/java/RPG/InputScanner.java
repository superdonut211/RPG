import java.util.Scanner;
import java.nio.charset.StandardCharsets;

public class InputScanner {
    
    public static final Scanner SCANNER = new Scanner(System.in, StandardCharsets.UTF_8.name());

    public static void closeScanner() {
        SCANNER.close();
    }
}
