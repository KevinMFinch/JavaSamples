//package my.packageName;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class sliderListener extends SoundAppProgram implements ChangeListener
{
    public void stateChanged(ChangeEvent e)
    {
        JSlider source = (JSlider) e.getSource();
        if (!source.getValueIsAdjusting())
        {
            tempo = (int) source.getValue();
        }
    }
}
