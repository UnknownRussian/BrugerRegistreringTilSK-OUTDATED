package org.example.frames;

import org.example.handlers.HandlerData;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class FrameMenu extends JFrame implements ActionListener {
    private JButton buttonCreateUser, buttonShowStartedProgress, buttonShowEndedProgress, buttonSettings, buttonExit;
    private HandlerData handlerData;

    public FrameMenu(){
        try{
            handlerData = new HandlerData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new GridLayout(5,1,0,5));
        setSize(new Dimension(550,725));

        buttonCreateUser = new JButton("Start oprettelse af bruger");
        buttonShowStartedProgress = new JButton("Igangværende oprettelser");
        buttonShowEndedProgress = new JButton("Afsluttet oprettelser");
        buttonSettings = new JButton("Indstillinger");
        buttonExit = new JButton("Afslut program");

        buttonCreateUser.addActionListener(this);
        buttonExit.addActionListener(this);
        buttonSettings.addActionListener(this);

        add(buttonCreateUser);
        add(buttonShowStartedProgress);
        add(buttonShowEndedProgress);
        add(buttonSettings);
        add(buttonExit);

        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == buttonCreateUser){
            if(handlerData.getOptionsToAdd().size() == 0){
                JOptionPane.showMessageDialog(null,"Lav venligst en liste i indstillinger!","Advarsel!",JOptionPane.ERROR_MESSAGE);
            }else{
                new FrameCreateUser(handlerData);
            }
        }
        if(e.getSource() == buttonExit){
            int dialogBtn = JOptionPane.showConfirmDialog(null, "Vil du lukke programmet?","Afslut",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
            if(dialogBtn == JOptionPane.YES_OPTION)
                System.exit(0);
        }
        if(e.getSource() == buttonSettings){
            new FrameSettings(handlerData);
        }
    }
}
