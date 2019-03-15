package com.example.java1200.demo017;

import javax.swing.*;
import java.awt.*;

/**
 * @ClassName Calculator
 * @Author nihui
 * @Date 2019/3/14 16:43
 * @Version 1.0
 * @Description TODO
 */
public class Calculator extends JFrame {
    private JTextField jTextField;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    Calculator frame = new Calculator();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public Calculator(){
        super();
        //获取到内容Pane,设置布局方式为空
        getContentPane().setLayout(null);
        //设置大小
        setBounds(100,100,355,225);
        //设置默认的关闭方式
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        jTextField = new JTextField();
        //设置文本框大小
        jTextField.setBounds(10, 10, 321, 22);
        //将文本框添加到面板中
        getContentPane().add(jTextField);

        final JButton jButton = new JButton();
        jButton.setForeground(Color.RED);
        jButton.setText("/");
        jButton.setBounds(255,76,41,28);
        getContentPane().add(jButton);
    }
}
