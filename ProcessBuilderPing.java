import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessBuilderPing {
    public static void main(String[] args) {
        ProcessBuilder processBuilder = new ProcessBuilder();

        // Windows
        processBuilder.command("cmd.exe", "/c", "ping -n 3 google.com");

        try {
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor(); // Wait for the process to finish
            System.out.println("\nExited with error code: " + exitCode);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
