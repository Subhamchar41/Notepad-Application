import java.awt.*;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(()-> {
            try {
                new Notepad().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
