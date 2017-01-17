import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ButtonLayoutAssessment extends JFrame implements ActionListener
{
    Container container ;
    JPanel buttonPanel ;
    JTextField textField ;
    JButton[] buttons = new JButton[11];
    Font serif = new Font("Serif", Font.PLAIN, 12);
    Font times = new Font("Times", Font.PLAIN,12);

    public ButtonLayoutAssessment()
    {
        init() ;
    }

    public void init()
    {
        textField = new JTextField("Hey") ;
        buttons[0] = new JButton("Set font to Serif") ;
        buttons[0].setFont(serif) ;
        buttons[1] = new JButton("Set font to Times New Roman") ;
        buttons[1].setFont(times) ;
        buttons[2] = new JButton("Buttons to the bottom");
        buttons[3] = new JButton("Buttons to the left");
        buttons[4] = new JButton("Buttons to the top");
        buttons[5] = new JButton("Buttons to the right");
        buttons[6] = new JButton("Text color to blue");
        buttons[6].setForeground(Color.BLUE);
        buttons[7] = new JButton("Text color to red");
        buttons[7].setForeground(Color.RED);
        buttons[8] = new JButton("Background to black");
        buttons[8].setBackground(Color.BLACK);
        buttons[9] = new JButton("Background to yellow");
        buttons[9].setBackground(Color.YELLOW);
        buttons[10] = new JButton("Reset") ;

        buttonPanel = new JPanel() ;
        for(int x=0;x<11;x++)
        {
            buttonPanel.add(buttons[x]) ;
            buttons[x].addActionListener(this);
        }
        buttonPanel.setLayout(new GridLayout(1, 11)) ;
        container = getContentPane();
        container.add(buttonPanel,BorderLayout.SOUTH);
        container.add(textField,BorderLayout.CENTER);
        setVisible(true);
        setSize(2000,500);
    }

    public void clearAll()
    {

    }

    public static void main(String[] args)
    {
        ButtonLayoutAssessment app = new ButtonLayoutAssessment() ;
    }

    public void actionPerformed(ActionEvent ae)
    {
        for(int x=0;x<11;x++)
        {
            if(ae.getSource() == buttons[x])
            {
                if(x==0)
                {
                    for(int y = 0;y<11;y++)
                    {
                        if(y!=1)
                        buttons[y].setFont(serif);
                    }
                    textField.setFont(serif);
                    invalidate() ;
                    validate() ;
                }
                if(x==1)
                {
                    for(int y = 0;y<11;y++)
                    {
                        if(y!=0)
                        buttons[y].setFont(times);
                    }
                    textField.setFont(times);
                    invalidate() ;
                    validate() ;
                }
                if(x==2)
                {
                    container.remove(buttonPanel) ;
                    buttonPanel.setLayout(new GridLayout(1,11));
                    container.add(buttonPanel,BorderLayout.SOUTH);
                    invalidate();
                    validate();
                }
                if(x==3)
                {
                    container.remove(buttonPanel) ;
                    buttonPanel.setLayout(new GridLayout(11, 1)) ;
                    container.add(buttonPanel,BorderLayout.WEST);
                    invalidate();
                    validate();
                }
                if(x==4)
                {
                    container.remove(buttonPanel) ;
                    buttonPanel.setLayout(new GridLayout(1, 11)) ;
                    container.add(buttonPanel,BorderLayout.NORTH);
                    invalidate();
                    validate();
                }
                if(x==5)
                {
                    container.remove(buttonPanel) ;
                    buttonPanel.setLayout(new GridLayout(11, 1)) ;
                    container.add(buttonPanel,BorderLayout.EAST);
                    invalidate();
                    validate();
                }
                if(x==6)
                {
                    for(int y=0;y<11;y++)
                    {
                        if(y !=7)
                        buttons[y].setForeground(Color.BLUE);
                    }
                    textField.setForeground(Color.BLUE);
                    invalidate();
                    validate() ;
                }
                if(x==7)
                {
                    for(int y=0;y<11;y++)
                    {
                        if(y != 6)
                        buttons[y].setForeground(Color.RED);
                    }
                    textField.setForeground(Color.RED);
                    invalidate();
                    validate() ;
                }
                if(x==8)
                {
                    textField.setBackground(Color.BLACK);
                    textField.setForeground(Color.WHITE);
                    invalidate();
                    validate();
                }
                if(x==9)
                {
                   textField.setBackground(Color.YELLOW);
                   textField.setForeground(Color.BLACK);
                   invalidate() ;
                   validate() ;
                }
                if(x==10)
                {
                    container.remove(buttonPanel) ;
                    buttonPanel.setLayout(new GridLayout(1,11));
                    container.add(buttonPanel,BorderLayout.SOUTH);

                    for(int y=0;y<11;y++)
                    {
                        if(y != 6 && y !=7)
                        {
                            buttons[y].setForeground(Color.BLACK);
                        }
                    }
                    buttons[6].setForeground(Color.WHITE);

                    textField.setBackground(Color.WHITE);
                    textField.setForeground(Color.BLACK);
                    invalidate();
                    validate();
                }
            }
        }

    }

}