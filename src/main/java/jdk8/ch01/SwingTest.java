package jdk8.ch01;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingTest {

    public static void main(String[] args) {
        System.out.println("");

        JFrame jFrame = new JFrame("MyJframe");
        JButton jButton = new JButton("MyJbuttom");

        //此处可被匿名类替换
        /*jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button Pressed");
            }
        });*/

        //lamdba
        jButton.addActionListener(e -> {
            System.out.println("Button Pressed");
        });

        jFrame.add(jButton);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
