package University.Management.System;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Marks extends JFrame implements ActionListener {
    private static final Color NAVY = new Color(20, 49, 85);
    private static final Color LIGHT_BLUE = new Color(235, 243, 252);

    private final String rollno;
    private final String selectedSemester;
    private final JLabel semesterValue = new JLabel("-");
    private final JLabel totalValue = new JLabel("0 / 500");
    private final JLabel percentageValue = new JLabel("0.00%");
    private final JLabel resultValue = new JLabel("PENDING");
    private final DefaultTableModel marksModel = new DefaultTableModel(
            new String[]{"S.No.", "Subject", "Marks (out of 100)"}, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public Marks(String rollno, String semester) {
        this.rollno = rollno;
        this.selectedSemester = semester;

        setTitle("Examination Result");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 650);
        setLocation(420, 80);

        JPanel root = new JPanel(new BorderLayout(0, 16));
        root.setBackground(new Color(248, 250, 253));
        root.setBorder(new EmptyBorder(20, 28, 20, 28));
        setContentPane(root);

        root.add(createHeader(), BorderLayout.NORTH);
        root.add(createReportBody(), BorderLayout.CENTER);
        root.add(createFooter(), BorderLayout.SOUTH);

        loadResult();
        setVisible(true);
    }

    private JPanel createHeader() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBackground(NAVY);
        header.setBorder(new EmptyBorder(16, 16, 16, 16));

        JLabel university = new JLabel("V.S. TECHNICAL UNIVERSITY");
        university.setFont(new Font("Tahoma", Font.BOLD, 24));
        university.setForeground(Color.WHITE);
        university.setAlignmentX(Component.CENTER_ALIGNMENT);
        header.add(university);

        JLabel title = new JLabel("EXAMINATION RESULT");
        title.setFont(new Font("Tahoma", Font.PLAIN, 15));
        title.setForeground(new Color(205, 222, 243));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        header.add(Box.createVerticalStrut(5));
        header.add(title);
        return header;
    }

    private JPanel createReportBody() {
        JPanel body = new JPanel(new BorderLayout(0, 14));
        body.setOpaque(false);
        body.add(createStudentInfo(), BorderLayout.NORTH);

        JTable marksTable = new JTable(marksModel);
        marksTable.setRowHeight(30);
        marksTable.setFont(new Font("Tahoma", Font.PLAIN, 15));
        marksTable.setShowVerticalLines(false);
        marksTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
        marksTable.getTableHeader().setBackground(LIGHT_BLUE);
        marksTable.getTableHeader().setForeground(NAVY);
        marksTable.getColumnModel().getColumn(0).setPreferredWidth(55);
        marksTable.getColumnModel().getColumn(1).setPreferredWidth(290);
        marksTable.getColumnModel().getColumn(2).setPreferredWidth(180);

        JScrollPane tableScroll = new JScrollPane(marksTable);
        tableScroll.setBorder(new LineBorder(new Color(205, 214, 226)));
        body.add(tableScroll, BorderLayout.CENTER);
        body.add(createSummary(), BorderLayout.SOUTH);
        return body;
    }

    private JPanel createStudentInfo() {
        JPanel info = new JPanel(new GridLayout(1, 2, 12, 0));
        info.setOpaque(false);
        info.add(createInfoCard("ROLL NUMBER", rollno));
        JPanel semesterCard = createInfoCard("SEMESTER", "");
        semesterValue.setFont(new Font("Tahoma", Font.BOLD, 16));
        semesterValue.setForeground(NAVY);
        semesterCard.remove(2);
        semesterCard.add(semesterValue);
        info.add(semesterCard);
        return info;
    }

    private JPanel createInfoCard(String labelText, String valueText) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(new EmptyBorder(10, 14, 10, 14));
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Tahoma", Font.BOLD, 11));
        label.setForeground(new Color(100, 110, 124));
        JLabel value = new JLabel(valueText);
        value.setFont(new Font("Tahoma", Font.BOLD, 16));
        value.setForeground(NAVY);
        card.add(label);
        card.add(Box.createVerticalStrut(4));
        card.add(value);
        return card;
    }

    private JPanel createSummary() {
        JPanel summary = new JPanel(new GridLayout(1, 3, 10, 0));
        summary.setOpaque(false);
        summary.add(createInfoCard("TOTAL", ""));
        summary.add(createInfoCard("PERCENTAGE", ""));
        summary.add(createInfoCard("RESULT", ""));
        replaceCardValue((JPanel) summary.getComponent(0), totalValue);
        replaceCardValue((JPanel) summary.getComponent(1), percentageValue);
        replaceCardValue((JPanel) summary.getComponent(2), resultValue);
        return summary;
    }

    private void replaceCardValue(JPanel card, JLabel value) {
        value.setFont(new Font("Tahoma", Font.BOLD, 16));
        value.setForeground(NAVY);
        card.remove(2);
        card.add(value);
    }

    private JPanel createFooter() {
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footer.setOpaque(false);
        JButton back = new JButton("Back");
        back.setBackground(NAVY);
        back.setForeground(Color.WHITE);
        back.setFocusPainted(false);
        back.setFont(new Font("Tahoma", Font.BOLD, 14));
        back.setPreferredSize(new Dimension(130, 34));
        back.addActionListener(this);
        footer.add(back);
        return footer;
    }

    private void loadResult() {
        int total = 0;
        boolean hasResult = false;
        try {
            Conn c = new Conn();
            PreparedStatement statement = c.connection.prepareStatement(
                    "select s.subj1, s.subj2, s.subj3, s.subj4, s.subj5, "
                            + "m.mrk1, m.mrk2, m.mrk3, m.mrk4, m.mrk5, m.semester "
                            + "from subject s join marks m on s.rollno = m.rollno and s.semester = m.semester "
                            + "where s.rollno = ? and s.semester = ?");
            statement.setString(1, rollno);
            statement.setString(2, selectedSemester);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                semesterValue.setText(resultSet.getString("semester"));
                for (int index = 1; index <= 5; index++) {
                    String subject = resultSet.getString("subj" + index);
                    String markText = resultSet.getString("mrk" + index);
                    int mark = Integer.parseInt(markText);
                    total += mark;
                    marksModel.addRow(new Object[]{index, subject, mark});
                }
                hasResult = true;
            } else {
                semesterValue.setText(selectedSemester);
                JOptionPane.showMessageDialog(this, "No result is available for this semester.");
            }
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this, "Could not load the examination result.");
            exception.printStackTrace();
        }

        totalValue.setText(total + " / 500");
        percentageValue.setText(String.format("%.2f%%", total / 5.0));
        resultValue.setText(hasResult && total >= 200 ? "PASS" : "PENDING");
        resultValue.setForeground(hasResult && total >= 200 ? new Color(29, 122, 68) : new Color(170, 80, 30));
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        dispose();
    }

    public static void main(String[] args) {
        new Marks("", "");
    }
}
