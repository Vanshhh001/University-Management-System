package University.Management.System;

import javax.swing.*;
import java.awt.*;

public class splash extends JFrame {
     Thread  t;   //made thread bcz we have to splash for 7 sec then automatically close
    splash(){//constructor
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/first.png"));
        Image i2 = i1.getImage().getScaledInstance(1000,700,Image.SCALE_DEFAULT);  //image scale
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        add(img);

        //starting thread
        t = new Thread(this);



        setVisible(true);
    }
    public void run(){
        try{
            Thread.sleep(7000);
            setVisible(false);   // 7sec ho gye to close kr do
            // next class   //eskebadd login class ko show karana hai


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new splash();

    }

}
