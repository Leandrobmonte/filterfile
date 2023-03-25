package screens;

import services.FilterDuplicates;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen {
    JFrame frame;

    JButton btnFilterScreen;
    JPanel panel;
    public MainScreen() {
        // create a new JFrame
        frame = new JFrame("Mover Arquivos duplicados");

        panel = new JPanel();

        // create a new JLabel with text
        JLabel labelMenuPrincipal = new JLabel("Menu Principal");
        setBtnFilterScreen();
        panel.add(labelMenuPrincipal);
        panel.add(btnFilterScreen);

        labelMenuPrincipal.setBounds(50, 30, 150, 30);
        btnFilterScreen.setBounds(50, 40, 150, 10);
        frame.add(panel);

        // set the size of the JFrame
        frame.setSize(400, 150);

        frame.setResizable(false);
        frame.setLocation(new Point(700,400));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // set the JFrame to be visible
        frame.setVisible(true);
    }

    public void setBtnFilterScreen(){
        btnFilterScreen =  new JButton("Filtrar Arquivos Duplicados");

        btnFilterScreen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ScreenFilter();
            }
        });
    }

}
