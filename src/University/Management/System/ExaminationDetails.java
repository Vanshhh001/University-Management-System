package University.Management.System;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class ExaminationDetails extends JFrame implements ActionListener {

    JTextField search;
    JButton result,back;
    JTable table;
    Choice croll;
    Choice csem;
    JComboBox comboBox;


    ExaminationDetails(){



        getContentPane().setBackground(new Color(241,252,210));

        JLabel heading = new JLabel("check result");
        heading.setBounds(350,15,400,50);
        heading.setFont(new Font("Tahoma",Font.BOLD,24));
        add(heading);



        search = new JTextField();
        search.setBounds(80,90,150,30);
        search.setFont(new Font("Tahoma",Font.PLAIN,18));
        add(search);

        JLabel sem = new JLabel("Select Semester");
        sem.setBounds(600,90,150,30);
        add(sem);

        String semesters[] = {
                "1st Semester",
                "2st Semester",
                "3st Semester",
                "4st Semester",
                "5st Semester",
                "6st Semester",
                "7st Semester",
                "8st Semester"
        };

        comboBox = new JComboBox(semesters);
        comboBox.setBounds(700,90,150,30);
        add(comboBox);


        result = new JButton("Result");
        result.setBounds(300,90,120,30);
        result.setBackground(Color.black);
        result.setForeground(Color.white);
        result.addActionListener(this);
        add(result);

        back = new JButton("Back");
        back.setBounds(440,90,120,30);
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.addActionListener(this);
        add(back);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0,130,1000,310);
        add(scrollPane);

        try {
            Conn c = new Conn();
            ResultSet resultSet =  c.statement.executeQuery("select * from student");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

        }catch (Exception e){
            e.printStackTrace();
        }

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();

                    search.setText(table.getModel().getValueAt(row, 2).toString());

            }
             });


        setSize(1000,475);
        setLocation(300,100);
        setLayout(null);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == result) {

            int row = table.getSelectedRow();

            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Select a student first");
                return;
            }

            String roll = table.getValueAt(row, 2).toString();
            String semester = comboBox.getSelectedItem().toString();

            new Marks(roll, semester);


        }else {
            setVisible(false);
        }

    }

    public static void main(String[] args) {
       new ExaminationDetails();

    }
}
