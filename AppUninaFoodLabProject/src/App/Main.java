package App;

import javax.swing.SwingUtilities;
import GUI.WelcomeFrame;

//Classe Main che fa delle cose 
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	//prova commit
                WelcomeFrame frame = new WelcomeFrame();
                frame.setVisible(true);
            }
        });
    }
}
