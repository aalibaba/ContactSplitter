package de.contact.splitter;


import javax.swing.SwingUtilities;
import javax.swing.JFrame;

public class SplitterGui {
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    	
    private static void createAndShowGUI() {
        JFrame f = new JFrame("ContactSplitter");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(250,250);
        f.setVisible(true);
    }
}