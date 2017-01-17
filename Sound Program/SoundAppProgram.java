//package my.packageName;

import javax.swing.*;
import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.net.*;

public class SoundAppProgram extends JApplet implements Runnable, ActionListener
{

    Container container;
    AudioClip soundClip[] = new AudioClip[16];
    Thread runner;
    JToggleButton button[][] = new JToggleButton[16][16];
    JPanel base = new JPanel();
    JPanel drumPanel = new JPanel();
    JPanel resetArea = new JPanel();
    JSlider speedSlider = new JSlider(130, 305);
    static int tempo = 205;
    final String LINK_TO_FILE = "file:New Tones/";
    AudioClip drumBeat, excellent;
    JButton drumButton = new JButton("Drum hit");
    JButton excellentButton = new JButton("EXCELLENT!!!");
    Timer timer;
    JButton reset;

    boolean notStopped = true, buttonOn = false;

    public void init()
    {
        try
        {
            excellent = getAudioClip(new URL(LINK_TO_FILE + "EXCELLENT.wav"));
            drumBeat = getAudioClip(new URL(LINK_TO_FILE + "sa14ssgb.wav"));
            soundClip[0] = getAudioClip(new URL(LINK_TO_FILE + "C3.wav"));
            soundClip[1] = getAudioClip(new URL(LINK_TO_FILE + "CSharp3.wav"));
            soundClip[2] = getAudioClip(new URL(LINK_TO_FILE + "D3.wav"));
            soundClip[3] = getAudioClip(new URL(LINK_TO_FILE + "DSharp3.wav"));
            soundClip[4] = getAudioClip(new URL(LINK_TO_FILE + "E3.wav"));
            soundClip[5] = getAudioClip(new URL(LINK_TO_FILE + "F3.wav"));
            soundClip[6] = getAudioClip(new URL(LINK_TO_FILE + "FSharp3.wav"));
            soundClip[7] = getAudioClip(new URL(LINK_TO_FILE + "G3.wav"));
            soundClip[8] = getAudioClip(new URL(LINK_TO_FILE + "GSharp3.wav"));
            soundClip[9] = getAudioClip(new URL(LINK_TO_FILE + "A3.wav"));
            soundClip[10] = getAudioClip(new URL(LINK_TO_FILE + "ASharp3.wav"));
            soundClip[11] = getAudioClip(new URL(LINK_TO_FILE + "B3.wav"));
            soundClip[12] = getAudioClip(new URL(LINK_TO_FILE + "C4.wav"));
            soundClip[13] = getAudioClip(new URL(LINK_TO_FILE + "CSharp4.wav"));
            soundClip[14] = getAudioClip(new URL(LINK_TO_FILE + "D4.wav"));
            soundClip[15] = getAudioClip(new URL(LINK_TO_FILE + "DSharp4.wav"));
        }
        catch (MalformedURLException mue)
        {
        }

        container = getContentPane();

        reset = new JButton("Reset");
        reset.setActionCommand("clear");
        reset.addActionListener(this);
        resetArea.add(reset);

        JPanel sliderPanel = new JPanel();
        speedSlider.addChangeListener(new sliderListener());
        sliderPanel.add(speedSlider);
        speedSlider.setLabelTable(speedSlider.createStandardLabels(25));
        speedSlider.setMajorTickSpacing(25);
        speedSlider.setPaintLabels(true);
        speedSlider.setPaintTicks(true);
        speedSlider.setSnapToTicks(true);
        JToolTip slideSpeed = new JToolTip();
        speedSlider.add(slideSpeed);
        speedSlider.setToolTipText("The current tempo is set to: " + tempo);

        base.setLayout(new GridLayout(16, 16));//button.length,1));
        String buttonText[] =
        {
            "ON", "OFF"
        };
        for (int y = 0; y < 16; y++)
        {
            for (int x = 0; x < 16; x++)
            {

                button[x][y] = new JToggleButton();
                button[x][y].setBackground(Color.BLUE);
                base.add(button[x][y]);
            }
        }

        drumPanel.setLayout(new GridLayout(2, 1));
        drumButton.setActionCommand("drum");
        drumButton.addActionListener(this);
        drumPanel.add(drumButton);

        excellentButton.setActionCommand("excellent");
        excellentButton.addActionListener(this);
        drumPanel.add(excellentButton);

        setSize(1500, 700);
        container.add(base, BorderLayout.CENTER);
        container.add(resetArea, BorderLayout.NORTH);
        container.add(sliderPanel, BorderLayout.SOUTH);
        container.add(drumPanel, BorderLayout.WEST);
        setVisible(true);
    }

    public void start()
    {
        if (runner == null)
        {
            runner = new Thread(this);
            runner.start();
        }
    }

    public void run()
    {
        do
        {
            goApp();
        } while (notStopped);

    }

    public void actionPerformed(ActionEvent ae)
    {
        if ("clear".equals(ae.getActionCommand()))
        {
            for (int y = 0; y < 16; y++)
            {
                for (int x = 0; x < 16; x++)
                {
                    if (button[y][x].isSelected())
                    {
                        button[y][x].setSelected(false);
                    }
                }
            }
        }
        if ("drum".equals(ae.getActionCommand()))
        {
            drumBeat.stop();
            drumBeat.play();
        }
        if ("excellent".equals(ae.getActionCommand()))
        {
            excellent.stop();
            excellent.play();
        }
    }

    public void goApp()
    {
        //Add a way to see which column im in
        try
        {
            for (int y = 0; y < 16; y++)
            {
                for (int x = 0; x < 16; x++)
                {
                    if (button[y][x].isSelected())
                    {
                        if (x > 0)
                        {
                            soundClip[x - 1].stop();
                            soundClip[x].play();
                        }
                        else
                        {
                            soundClip[15].stop();
                            soundClip[x].play();
                        }

                    }
                }
                Thread.sleep(tempo);
            }
            speedSlider.setToolTipText("The current speed is set to: " + tempo);

        }
        catch (InterruptedException e)
        {
        }
    }

}
