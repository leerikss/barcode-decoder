package fi.leif.java.screenshot.decoder.tools;

import java.awt.HeadlessException;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Popup {
    public static void show(String message) {
        try {
            JTextArea textarea= new JTextArea(message);
            textarea.setEditable(true);            
            JOptionPane.showMessageDialog(null,textarea);
        } catch(HeadlessException e) {
            System.out.println(message);
        }
      }

}
