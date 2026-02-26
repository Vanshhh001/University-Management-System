package University.Management.System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Marks extends JFrame implements ActionListener {
    String rollno;
    JButton cancel;



    public Marks(String rollno, String semester){
        this.rollno = rollno;


        setSize(500, 600);
        setLocation(500, 100);
        setLayout(null);

        getContentPane().setBackground(new Color(210, 252, 248));

        JLabel heading = new JLabel("V.S Technical Univeristy");
        heading.setBounds(110, 10, 500, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(heading);

        JLabel subheading = new JLabel("Result of Examination 2023");
        subheading.setBounds(100, 50, 500, 20);
        subheading.setFont(new Font("Tahoma", Font.BOLD, 18));
        add(subheading);

        JLabel lblrollno = new JLabel("Roll Number " + rollno);
        lblrollno.setBounds(60, 100, 500, 20);
        lblrollno.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblrollno);

        JLabel lblsemester = new JLabel();
        lblsemester.setBounds(60, 130, 500, 20);
        lblsemester.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblsemester);

        JLabel subjectHeading = new JLabel("Subject");
        subjectHeading.setBounds(100,170,200,25);
        subjectHeading.setFont(new Font("Tahoma", Font.BOLD, 18));
        add(subjectHeading);

        JLabel marksHeading = new JLabel("Marks");
        marksHeading.setBounds(300,170,100,25);
        marksHeading.setFont(new Font("Tahoma", Font.BOLD, 18));
        marksHeading.setHorizontalAlignment(SwingConstants.RIGHT);
        add(marksHeading);

        JLabel sub1 = new JLabel();
        sub1.setBounds(100, 200, 500, 20);
        sub1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(sub1);
        JLabel m1 = new JLabel();
        m1.setBounds(350, 200, 500, 20);
        m1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(m1);


        JLabel sub2 = new JLabel();
        sub2.setBounds(100, 230, 500, 20);
        sub2.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(sub2);
        JLabel m2 = new JLabel();
        m2.setBounds(350, 230, 500, 20);
        m2.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(m2);


        JLabel sub3 = new JLabel();
        sub3.setBounds(100, 260, 500, 20);
        sub3.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(sub3);
        JLabel m3 = new JLabel();
        m3.setBounds(350, 260, 500, 20);
        m3.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(m3);



        JLabel sub4 = new JLabel();
        sub4.setBounds(100, 290, 500, 20);
        sub4.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(sub4);
        JLabel m4 = new JLabel();
        m4.setBounds(350, 290, 500, 20);
        m4.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(m4);



        JLabel sub5 = new JLabel();
        sub5.setBounds(100, 320, 500, 20);
        sub5.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(sub5);
        JLabel m5 = new JLabel();
        m5.setBounds(350, 320, 500, 20);
        m5.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(m5);


        try {
            Conn c = new Conn();

            ResultSet rs = c.statement.executeQuery(
                    "select s.subj1, s.subj2, s.subj3, s.subj4, s.subj5, " +
                            "m.mrk1, m.mrk2, m.mrk3, m.mrk4, m.mrk5, m.semester " +
                            "from subject s join marks m " +
                            "on s.rollno = m.rollno and s.semester = m.semester " +
                            "where s.rollno = '"+rollno+"' and s.semester = '"+semester+"'"
            );

            while(rs.next()) {
                sub1.setText(rs.getString("subj1"));
                m1.setText(rs.getString("mrk1"));

                sub2.setText(rs.getString("subj2"));
                m2.setText(rs.getString("mrk2"));

                sub3.setText(rs.getString("subj3"));
                m3.setText(rs.getString("mrk3"));

                sub4.setText(rs.getString("subj4"));
                m4.setText(rs.getString("mrk4"));

                sub5.setText(rs.getString("subj5"));
                m5.setText(rs.getString("mrk5"));

                lblsemester.setText("Semester: " + rs.getString("semester"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        cancel = new JButton("Back");
        cancel.setBounds(250, 500, 120, 25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
    }

    public static void main(String[] args) {
        new Marks("","");
    }
}