import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import ui.Welcome_0;

public class App {
    public static void main(String[] args) {
        FlatDarkLaf.setup();
        Welcome_0 start = new Welcome_0();
        start.setVisible(true);
    }
}
