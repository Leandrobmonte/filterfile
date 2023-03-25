package screens;

import services.FilterDuplicates;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScreenFilter {
    JFrame frame;
    JTextField textField1;
    JTextField textField2;
    JButton btnFiltrar;
    JButton buttonSource;
    JButton buttonDestiny;
    JPanel panel;

    public ScreenFilter() {
        // create a new JFrame
        frame = new JFrame("Mover Arquivos duplicados");

        panel = new JPanel();

        // create a new JLabel with text
        JLabel origem = new JLabel("Origem:");
        JLabel destino = new JLabel("destino:");

        // create a new JTextField
        textField1 = new JTextField(20);
        textField2 = new JTextField(20);

        buttonSource = new JButton("Origem...");
        btnBrowse(textField1, buttonSource);

        buttonDestiny = new JButton("Destino...");
        btnBrowse(textField2, buttonDestiny);

        this.btnFilter();

        // add the label to the JFrame
        panel.add(origem);
        panel.add(destino);
        //text field to the JFrame
        panel.add(textField1);
        panel.add(textField2);

        panel.add(btnFiltrar);
        panel.add(buttonSource);
        panel.add(buttonDestiny);

        // set the layout manager of the JFrame to null
        panel.setLayout(null);

        // set the bounds of the label and text field
        origem.setBounds(50, 30, 150, 20);
        textField1.setBounds(100, 30, 150, 20);

        destino.setBounds(50, 50, 150, 20);
        textField2.setBounds(100, 50, 150, 20);

        origem.setBounds(50, 30, 150, 20);
        textField1.setBounds(100, 30, 150, 20);

        buttonSource.setBounds(250, 30, 100, 20);
        buttonDestiny.setBounds(250, 50, 100, 20);

        btnFiltrar.setBounds(250, 80, 100, 20);

        // set the size of the JFrame
        // add the panel to the JFrame
        frame.add(panel);

        // set the size of the JFrame
        frame.setSize(400, 150);

        frame.setResizable(false);
        frame.setLocation(new Point(700,400));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set the JFrame to be visible
        frame.setVisible(true);
    }

    public void btnFilter(){
        btnFiltrar =  new JButton("Filtrar");

        btnFiltrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sourcePath = textField1.getText();
                String destPath = textField2.getText();
                try {
                    // some code that may throw an exception
                    FilterDuplicates filterDuplicates = new FilterDuplicates();
                    if(sourcePath.equals(null) || sourcePath.equals("")){
                        JOptionPane.showMessageDialog(frame, "Info: campo origem obrigatório.", "Error", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    if(destPath.equals(null) || destPath.equals("")){
                        JOptionPane.showMessageDialog(frame, "Info: campo destino obrigatório.", "Error", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    filterDuplicates.filter(sourcePath, destPath);
                } catch (Exception exception) {
                    // show an error message if an exception is thrown
                    JOptionPane.showMessageDialog(frame, "Error: Não foi possivel encontrar caminho de origem/destino", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }

    private void btnBrowse(JTextField textField, JButton buttonDestiny) {
        buttonDestiny.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // create a new JFileChooser
                JFileChooser chooser = new JFileChooser();

                // show the dialog and get the result
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                //chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

                // show the dialog and get the result
                int result = chooser.showOpenDialog(null);

                // if the result is APPROVE_OPTION, set the text of the JTextField to the selected file's path
                if (result == JFileChooser.APPROVE_OPTION) {
                    textField.setText(chooser.getSelectedFile().getPath());
                }
            }
        });
    }

}
