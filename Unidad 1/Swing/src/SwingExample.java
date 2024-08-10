import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingExample {
    private JPanel formPanel;
    private JButton pulsameButton;
    private JLabel mensajeLabel;

    public SwingExample() {
        pulsameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mensajeLabel.setText("¡Botón pulsado!");
            }
        });
    }

    public void createUIComponents() {

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("SwingExample");
        frame.setContentPane(new SwingExample().formPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
