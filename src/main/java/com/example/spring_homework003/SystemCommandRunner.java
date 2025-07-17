import java.io.IOException;

public class SystemCommandRunner {

    public void runCommand(String userInput) {
        try {
            // ⚠️ Potential Security Hotspot: Command execution
            Runtime.getRuntime().exec("ls " + userInput); // 🔥 Dangerous if userInput is not sanitized
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
