import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AffineCipherGUI extends JFrame implements ActionListener {
    private JTextField inputField, outputField;
    private JButton encryptButton, decryptButton;
    private JLabel inputLabel, outputLabel;
    
    private int a = 5; // You can change these values according to your requirements
    private int b = 8; // These values are for example purposes only
    
    public AffineCipherGUI() {
        setTitle("Affine Cipher");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        inputLabel = new JLabel("Input:");
        inputField = new JTextField(20);
        
        outputLabel = new JLabel("Output:");
        outputField = new JTextField(20);
        outputField.setEditable(false);
        
        encryptButton = new JButton("Encrypt");
        encryptButton.addActionListener(this);
        
        decryptButton = new JButton("Decrypt");
        decryptButton.addActionListener(this);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        panel.add(inputLabel);
        panel.add(inputField);
        panel.add(outputLabel);
        panel.add(outputField);
        panel.add(encryptButton);
        panel.add(decryptButton);
        
        add(panel);
        setVisible(true);
    }
    
    // Encryption function
    private String encrypt(String input) {
        StringBuilder encryptedText = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (Character.isLetter(c)) {
                char encryptedChar = (char) (((a * (c - 'A') + b) % 26) + 'A');
                encryptedText.append(encryptedChar);
            } else {
                encryptedText.append(c);
            }
        }
        return encryptedText.toString();
    }
    
    // Decryption function
    private String decrypt(String input) {
        StringBuilder decryptedText = new StringBuilder();
        int modInverse = 0;
        for (int i = 0; i < 26; i++) {
            if ((a * i) % 26 == 1) {
                modInverse = i;
                break;
            }
        }
        for (char c : input.toCharArray()) {
            if (Character.isLetter(c)) {
                char decryptedChar = (char) (((modInverse * (c - 'A' - b + 26)) % 26) + 'A');
                decryptedText.append(decryptedChar);
            } else {
                decryptedText.append(c);
            }
        }
        return decryptedText.toString();
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == encryptButton) {
            String inputText = inputField.getText().toUpperCase();
            String encryptedText = encrypt(inputText);
            outputField.setText(encryptedText);
        } else if (e.getSource() == decryptButton) {
            String inputText = inputField.getText().toUpperCase();
            String decryptedText = decrypt(inputText);
            outputField.setText(decryptedText);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AffineCipherGUI();
            }
        });
    }
}