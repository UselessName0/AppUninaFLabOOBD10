package App;

import javax.swing.SwingUtilities;
import GUI.WelcomeFrame;


public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WelcomeFrame frame = new WelcomeFrame();
            frame.setVisible(true);
        });
    }
}
