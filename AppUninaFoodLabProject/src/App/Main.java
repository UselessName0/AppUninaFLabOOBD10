package App;

import javax.swing.SwingUtilities;
import GUI.WelcomeFrame;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                WelcomeFrame frame = new WelcomeFrame();
                frame.setVisible(true);
            }
        });
    }
}
