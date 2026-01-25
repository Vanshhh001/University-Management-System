package University.Management.System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main_class extends JFrame implements ActionListener {
    main_class(){
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/third.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1540,750,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        add(img);

        JMenuBar mb = new JMenuBar();

        //new Infomation

        JMenu newInfo = new JMenu("New Information");
        newInfo.setForeground(Color.BLACK);
        mb.add(newInfo);

        JMenuItem facultyInfo = new JMenuItem("New Faculty Information");
        facultyInfo.setBackground(Color.WHITE);
        newInfo.add(facultyInfo);

        JMenuItem studentInfo = new JMenuItem("New Student Information");
        studentInfo.setBackground(Color.WHITE);
        newInfo.add(studentInfo);

        //Details
        JMenu details = new JMenu("View Details");
        details.setForeground(Color.BLACK);
        mb.add(details);

        JMenuItem facultydetails = new JMenuItem("View Faculty Details");
        facultydetails.setBackground(Color.WHITE);
        details.add(facultydetails);

        JMenuItem studentdetails = new JMenuItem("View Student Details");
        studentdetails.setBackground(Color.WHITE);
        details.add(studentdetails);

        //Leave
        JMenu leave = new JMenu("Apply Leave");
        leave.setForeground(Color.BLACK);
        mb.add(leave);

        JMenuItem facultyLeave = new JMenuItem("Faculty Leave");
        facultyLeave.setBackground(Color.WHITE);
        leave.add(facultyLeave);

        JMenuItem studentLeave = new JMenuItem("Student Leave");
        studentLeave.setBackground(Color.WHITE);
        leave.add(studentLeave);

        //Leave Details
        JMenu leaveDetails = new JMenu("Leave Details");
        leaveDetails.setForeground(Color.BLACK);
        mb.add(leaveDetails);

        JMenuItem facultyLeaveDetails = new JMenuItem("Faculty Leave Details");
        facultyLeaveDetails.setBackground(Color.WHITE);
        leaveDetails.add(facultyLeaveDetails);

        JMenuItem studentLeaveDetails = new JMenuItem("Student Leave Details");
        studentLeaveDetails.setBackground(Color.WHITE);
        leaveDetails.add(studentLeaveDetails);

        //Exams
        JMenu exam = new JMenu("Examination");
        exam.setForeground(Color.BLACK);
        mb.add(exam);

        JMenuItem ExaminationDetails = new JMenuItem("Examination Results");
        ExaminationDetails.setBackground(Color.WHITE);
        exam.add(ExaminationDetails);

        JMenuItem EnterMarks = new JMenuItem("Enter Marks");
        EnterMarks.setBackground(Color.WHITE);
        exam.add(EnterMarks);

        //update Info
        JMenu updateInfo = new JMenu("Update Details");
        updateInfo.setForeground(Color.BLACK);
        mb.add(updateInfo);

        JMenuItem updatefacultyInfo =  new JMenuItem("Update Faculty Details");
        updatefacultyInfo.setBackground(Color.WHITE);
        updateInfo.add(updatefacultyInfo);

        JMenuItem updatestudentInfo = new JMenuItem("Update Student Details");
        updatestudentInfo.setBackground(Color.WHITE);
        updateInfo.add(updatestudentInfo);

        //Fees
        JMenu fee = new JMenu("Fee Details");
        fee.setForeground(Color.BLACK);
        mb.add(fee);

        JMenuItem feestructure =  new JMenuItem("Fee Structure");
        feestructure.setBackground(Color.WHITE);
        fee.add(feestructure);

        JMenuItem feeForm = new JMenuItem("Student Fee Form");
        feeForm.setBackground(Color.WHITE);
        fee.add(feeForm);

        //utility
        JMenu utility = new JMenu("Utility");
        utility.setForeground(Color.BLACK);
        mb.add(utility);

        JMenuItem Ca =  new JMenuItem("Calculator");
        Ca.setBackground(Color.WHITE);
        Ca.addActionListener(this);
        utility.add(Ca);

        JMenuItem Notepad = new JMenuItem("Notepad");
        Notepad.setBackground(Color.WHITE);
        Notepad.addActionListener(this);
        utility.add(Notepad);

        //About
        JMenu about = new JMenu("About");
        about.setForeground(Color.BLACK);
        mb.add(about);

        JMenuItem About =  new JMenuItem("About");
        About.setBackground(Color.WHITE);
        about.add(About);

        //Exit
        JMenu exit = new JMenu("Exit");
        exit.setForeground(Color.BLACK);
        mb.add(exit);

        JMenuItem Exit =  new JMenuItem("Exit");
        Exit.setBackground(Color.WHITE);
        Exit.addActionListener(this);
        exit.add(Exit);


        setJMenuBar(mb);


        setSize(1540,850);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String sm = e.getActionCommand();

        if(sm.equals("Exit")){
            System.exit(15);
        }else if(sm.equals("Calculator")){
            try{
                Runtime.getRuntime().exec("calc.exe");
            }catch (Exception E){
                E.printStackTrace();
            }
        } else if (sm.equals("Notepad")) {

            try{
                Runtime.getRuntime().exec("notepad.exe");

            }catch (Exception E){
                E.printStackTrace();

            }
            
        }

    }

    public static void main(String[] args) {
        new main_class();
    }
}
