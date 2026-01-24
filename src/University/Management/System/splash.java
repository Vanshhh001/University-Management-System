package University.Management.System;

import javax.swing.*;
import java.awt.*;

public class splash extends JFrame implements Runnable{   //for running thread runnable used
     Thread  t;   //made thread bcz we have to splash for 7 sec then automatically close
    splash() {//constructor
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/first.png"));
        Image i2 = i1.getImage().getScaledInstance(1000, 800, Image.SCALE_DEFAULT);  //image scale
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        add(img);

        //starting thread
        t = new Thread(this);
        t.start();
        setVisible(true);

        int x = 1;
        for (int i = 2; i <= 600; i += 4, x += 1) {
            setLocation(600 - ((i + x) / 2), 350 - (i / 2));
            setSize(i + 3 * x, i + x / 2);

            try {
                Thread.sleep(10);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public void run(){
        try{
            Thread.sleep(4000);
            setVisible(false);   // 7sec ho gye to close kr do
            new Login();        //eske badd login class ko show karana hai


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new splash();

    }

}
