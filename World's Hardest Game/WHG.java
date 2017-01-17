import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.*;
import java.math.*;
import java.awt.image.*;
import java.applet.*;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.awt.geom.RoundRectangle2D;
import java.awt.geom.Line2D;

public class WHG extends JPanel implements KeyListener, Runnable, MouseListener
{

    int enemySpeed = 1;
    int charSpeed = 1;
    int enemyRadius = 7;
    int charRadius = 12;
    int boxLength = 20;
    boolean mouseIsPressed = false;
    int x;
    int y;
    int deadCount=0;

    int score;
    JFrame frame = new JFrame();
    Thread runner;
    boolean downPressed = false;
    boolean upPressed = false;
    boolean rightPressed = false;
    boolean leftPressed = false;
    boolean notStopped = true;
    Color lightGrey = new Color(156, 157, 161);
    Color white = Color.WHITE;
    Color grey = new Color(106, 110, 108);
    int level = 1;
    boolean levelOne, levelTwo, levelThree = false;
    boolean wonGame = false;
    String currentColor = "white";

    ArrayList<WHGEnemy> enemies = new ArrayList<WHGEnemy>();
    Polygon p = new Polygon();
    BufferedReader input ;
    Ellipse2D.Double scoreBubble ;
    boolean canProceed = false;

    Color endZoneColor = new Color(181, 254, 180);
    Color whiteColor = new Color(247, 247, 255);
    Color pinkColor = new Color(230, 230, 255);
    Color backgroundColor = new Color(180, 181, 254);

    public WHG()
    {
        switch (level)
        {
            case 1:
                levelOne = true;
                break;
            case 2:
                levelOne = false;
                levelTwo = true;
                break;
            case 3:
                levelTwo = false;
                levelThree = true;
                break;

        }
        generateEnemy();
        getPolygon();
        x = 100;
        y = 75;
        frame.addMouseListener(this);
        frame.addKeyListener(this);
        frame.add(this);
        frame.setSize(650, 475);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        runner = new Thread(this);
        runner.start();
    }

    public void getPolygon()
    {
		try
		{
			if(levelOne)
			  input = new BufferedReader(new FileReader(new File("levelOneLines.txt")));
			else if(levelTwo)
			  input = new BufferedReader(new FileReader(new File("levelTwoLines.txt")));
			else if(levelThree)
			  input = new BufferedReader(new FileReader(new File("levelThreeLines.txt")));
			String text = "";
			Integer coordinates[] = new Integer[4];
			while ((text = input.readLine()) != null)
			{
				String parts[] = text.split(" ");
				for (int x = 0; x < parts.length; x++)
				{
					p.addPoint(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
				}

			}
		} catch (IOException io)
		{

        }
	}

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;


        g2d.setColor(backgroundColor);
		g2d.fillRect(0, 0, 650, 475);

        if (levelOne)
        {
            g2d.setColor(endZoneColor);
            g2d.fillRect(50, 50, 90, 180); //Each box is 30x30
            g2d.fillRect(500, 50, 90, 180);
            //g2d.setColor(pinkColor);
            for (int xPos = 170; xPos < 470; xPos += 30)
            {
                if (currentColor.equals("pink"))
                {
                    currentColor = "white";
                    g2d.setColor(whiteColor);
                } else if (currentColor.equals("white"))
                {
                    currentColor = "pink";
                    g2d.setColor(pinkColor);
                }
                for (int yPos = 80; yPos < 200; yPos += 30)
                {
                    g2d.fillRect(xPos, yPos, 30, 30);
                    if (currentColor.equals("pink"))
                    {
                        currentColor = "white";
                        g2d.setColor(whiteColor);
                    } else if (currentColor.equals("white"))
                    {
                        currentColor = "pink";
                        g2d.setColor(pinkColor);
                    }
                }
            }

            g2d.setColor(whiteColor);
            g2d.fillRect(140, 200, 30, 30);
            g2d.fillRect(470, 50, 30, 30);

            g2d.setColor(pinkColor);
            g2d.fillRect(170, 200, 30, 30);
            g2d.fillRect(440, 50, 30, 30);

            g2d.setColor(Color.BLACK);

            g2d.draw(p);
        }

        if(levelTwo)
        {
			g2d.setColor(endZoneColor);
			g2d.fillRect(50,200,90,60); //Level two is 360 long?
			g2d.fillRect(500,200,90,60);
			for (int xPos = 140; xPos < 500; xPos += 30)
			{
				if (currentColor.equals("pink"))
				{
					currentColor = "white";
					g2d.setColor(whiteColor);
				} else if (currentColor.equals("white"))
				{
					currentColor = "pink";
					g2d.setColor(pinkColor);
				}
				for (int yPos = 140; yPos < 320; yPos += 30)
				{
					g2d.fillRect(xPos, yPos, 30, 30);
					if (currentColor.equals("pink"))
					{
						currentColor = "white";
						g2d.setColor(whiteColor);
					} else if (currentColor.equals("white"))
					{
						currentColor = "pink";
						g2d.setColor(pinkColor);
					}
				}
            }
			g2d.setColor(Color.BLACK);
            g2d.draw(p);

            if(!canProceed)
            {
				scoreBubble = new Ellipse2D.Double(315,230,10,10) ;
				g2d.draw(scoreBubble);
				g2d.setColor(Color.YELLOW);
				g2d.fill(scoreBubble);
			}
		}

		if(levelThree)
		{
			g2d.setColor(pinkColor);
			g2d.fillRect(50,50,30,30);

			currentColor="pink";
			for (int xPos = 50; xPos < 230; xPos += 30)
			{
				if (currentColor.equals("pink"))
				{
					currentColor = "white";
					g2d.setColor(whiteColor);
				} else if (currentColor.equals("white"))
				{
					currentColor = "pink";
					g2d.setColor(pinkColor);
				}
				for (int yPos = 80; yPos < 260; yPos += 30)
				{
					g2d.fillRect(xPos, yPos, 30, 30);
					if (currentColor.equals("pink"))
					{
						currentColor = "white";
						g2d.setColor(whiteColor);
					} else if (currentColor.equals("white"))
					{
						currentColor = "pink";
						g2d.setColor(pinkColor);
					}
				}
            }

            if(!canProceed)
			{
				g2d.setColor(Color.BLACK);
				scoreBubble = new Ellipse2D.Double(57,57,10,10) ;
				g2d.draw(scoreBubble);
				g2d.setColor(Color.YELLOW);
				g2d.fill(scoreBubble);
			}
            g2d.setColor(endZoneColor);
			g2d.fillRect(110,140,60,60);
			g2d.setColor(Color.BLACK);
			g2d.draw(p);
		}

		g2d.setColor(Color.BLUE);
		for (int z = 0; z < enemies.size(); z++)
		{
			g2d.fillOval(enemies.get(z).getX(), enemies.get(z).getY(), 2 * enemyRadius, 2 * enemyRadius);
		}


		g2d.setColor(Color.RED);
		g2d.fillRect(x, y, 20, 20);
		g2d.setColor(Color.BLACK);
		g2d.drawRect(x - 1, y - 1, 21, 21);

		g2d.drawString("Death count: "+deadCount,200,400);
		if(wonGame)
		{
			g2d.setColor(Color.WHITE);
			g2d.fillRect(0,0,650,450);
			g2d.setColor(Color.BLACK);
			g2d.drawString("Congratulations! You beat the World's Hardest Game!!!",100,100);
		}
    }

    public void keyReleased(KeyEvent ke)
    {
        if (ke.getKeyCode() == 39)
        {
            rightPressed = false;
        }
        if (ke.getKeyCode() == 37)
        {
            leftPressed = false;
        }
        if (ke.getKeyCode() == 38)
        {
            upPressed = false;
        }
        if (ke.getKeyCode() == 40)
        {
            downPressed = false;
        }
    }

    public void keyPressed(KeyEvent ke)
    {
        if (ke.getKeyCode() == 39)
        {
            rightPressed = true;
        }
        if (ke.getKeyCode() == 37)
        {
            leftPressed = true;
        }
        if (ke.getKeyCode() == 38)
        {
            upPressed = true;
        }
        if (ke.getKeyCode() == 40)
        {
            downPressed = true;
        }
    }

    public void keyTyped(KeyEvent ke)
    {

    }

    public void generateEnemy()
    {
        //1 is right, 2 is left, 3 is up, 4 is down

        if (levelOne)
        {
            int count = 1;
            for (int y = 90; y < 200; y += 30)
            {
                if (count % 2 == 1)
                {
                    WHGEnemy enemy = new WHGEnemy(315, y, 1);
                    enemies.add(enemy);
                } else
                {
                    WHGEnemy enemy = new WHGEnemy(315, y, 2);
                    enemies.add(enemy);
                }
                count++;
            }
        }
        if(levelTwo)
        {
			enemies.clear();
			//12 dudes. starts down and alternates
			int count = 1;
			for (int x = 147; x < 500; x += 30)
			{
				if (count % 2 == 1)
				{
					WHGEnemy enemy = new WHGEnemy(x, 200, 4);
					enemies.add(enemy);
				} else
				{
					WHGEnemy enemy = new WHGEnemy(x, 200, 3);
					enemies.add(enemy);
				}
				count++;
            }
		}
		if(levelThree)
		{
			enemies.clear();
			int centerX = 135;
			int centerY = 165;
			int radius =60;
			for(int angle = 0;angle <360;angle+=30)
			{
				if(angle != 30 && angle !=60)
				{
					Double radians = Math.toRadians(angle);
					Double horizontal = Math.cos(radians) * 60 ;
					Double vertical = Math.sin(radians)*60;

					WHGEnemy enemy = new WHGEnemy((int)(centerX + horizontal),(int)(centerY + vertical), 1,Math.toRadians(angle));
					enemies.add(enemy);
				}
			}
		}
    }

    public Rectangle collisionBox()
    {
        return new Rectangle((int) x, (int) y, 20, 20);
    }

    public boolean intersectsEnemy(WHGEnemy enemy)
    {
        return enemy.collisionBall(enemyRadius).intersects(collisionBox()) ;
    }

    public void actionPerformed(ActionEvent e)
    {

    }

    public void run()
    {
        while (true)
        {
            if (notStopped)
            {

                if (rightPressed)
                {
                    boolean contains = false;
                    x++;
                    if (p.contains(collisionBox()))
                    {
                        contains = true;
                    }
                    if (!contains)
                    {
                        x--;
                    }
                }
                if (upPressed)
                {
                    boolean contains = false;
                    y--;
                    if (p.contains(collisionBox()))
                    {
                        contains = true;
                    }
                    if (!contains)
                    {
                        y++;
                    }
                }
                if (leftPressed)
                {
                    boolean contains = false;
                    x--;
                    if (p.contains(collisionBox()))
                    {
                        contains = true;
                    }
                    if (!contains)
                    {
                        x++;
                    }
                }
                if (downPressed)
                {
                    boolean contains = false;
                    y++;
                    if (p.contains(collisionBox()))
                    {
                        contains = true;
                    }
                    if (!contains)
                    {
                        y--;
                    }
                }

                if (levelOne)
                {
                    for (int z = 0; z < enemies.size(); z++)
                    {
                        //1 is right, 2 is left, 3 is up, 4 is down
                        switch (enemies.get(z).getDir())
                        {
                            case 1:
                                if (enemies.get(z).getX() + 14 < 470)
                                {
                                    enemies.get(z).changeX(enemySpeed);
                                    break;
                                } else
                                {
                                    enemies.get(z).setDir(2);
                                    enemies.get(z).changeX(-enemySpeed);
                                }
                                break;
                            case 2:
                                if (enemies.get(z).getX() - 1 >= 167)
                                {
                                    enemies.get(z).changeX(-enemySpeed);
                                    break;
                                } else
                                {
                                    enemies.get(z).setDir(1);
                                    enemies.get(z).changeX(enemySpeed);
                                }
                                break;

                   		 }
                	}
				}
				if(levelTwo)
				{
					for (int z = 0; z < enemies.size(); z++)
					{
						//1 is right, 2 is left, 3 is up, 4 is down
						switch (enemies.get(z).getDir())
						{
							case 3:
								if (enemies.get(z).getY() - 1 >= 140)
								{
									enemies.get(z).changeY(-enemySpeed);
									break;
								} else
								{
									enemies.get(z).setDir(4);
									enemies.get(z).changeY(enemySpeed);
								}
								break;
							case 4:
								if (enemies.get(z).getY() + 9 < 320)
								{
									enemies.get(z).changeY(enemySpeed);
									break;
								} else
								{
									enemies.get(z).setDir(3);
									enemies.get(z).changeY(-enemySpeed);
								}
								break;
						}
					}
				}

				if(levelThree)
				{
					for(int z=0;z<enemies.size();z++)
					{
						Double angle = enemies.get(z).getAngle() ;
						int radius = 90;
						angle += Math.toRadians(1) ;
						if(angle > 2*Math.PI)
						{
							angle = 0.0;
						}
						Double horizontal = Math.cos(angle) * 60 ;
						Double vertical = Math.sin(angle)*60;
						int centerX =135;
						int centerY = 165;
						int newX = (int)(centerX + horizontal) ;
						int newY =(int)(centerY + vertical) ;
						enemies.get(z).updateCoordinates(newX,newY,angle);

					}
				}
                repaint();

                for (int count = 0; count < enemies.size(); count++)
                {
                    if (intersectsEnemy(enemies.get(count)))
                    {
						deadCount++;
                        canProceed = false;
                        if(levelOne)
                        {
							x=100;
							y=75;
						}
						else if(levelTwo)
						{
							x=60;
							y=210;
						}
						else if(levelThree)
						{
							x=140;
							y = 150;
						}
                    }
                }

				if(!levelOne)
				{
					if(scoreBubble.intersects(collisionBox()))
					{
						canProceed=true;
					}
				}


                if(levelOne)
                {
					if (collisionBox().intersects(new Rectangle(500, 50, 90, 180)))
					{
						clearLevel();
						setLevelTwo();
					}
				}

				if(levelTwo)
				{
					if (collisionBox().intersects(new Rectangle(500, 200, 90, 60)) && canProceed)
					{
						clearLevel();
						setLevelThree();
					}
				}

				//110,140
				if(levelThree)
				{
					if(collisionBox().intersects(new Rectangle(110,140,60,60)) && canProceed)
					{
						winGame();
					}
				}

            }
            try
            {
                Thread.sleep(5);
            } catch (InterruptedException e)
            {
            }
        }
    }

    public void mouseExited(MouseEvent me)
    {
    }

    public void mouseEntered(MouseEvent me)
    {
    }

    public void mouseReleased(MouseEvent me)
    {
        if (mouseIsPressed)
        {
            mouseIsPressed = false;

        }
    }

    public void mousePressed(MouseEvent me)
    {

    }

    public void mouseClicked(MouseEvent me)
    {

    }

    public void clearLevel()
    {
		enemies = new ArrayList<WHGEnemy>();
		notStopped=true;
    }

    public void setLevelTwo()
    {
		x=60;
		y=210;
		p.reset();
		levelOne = false;
		levelTwo = true;
		generateEnemy();
		getPolygon();
		repaint();
	}

	public void setLevelThree()
	{
		x=130;
		y= 150;
		p.reset();
		levelTwo = false;
		levelThree = true;
		generateEnemy();
		getPolygon();
		canProceed = false;
		repaint();
	}

	public void winGame()
	{
		notStopped = false;
		wonGame = true ;

	}

    public static void main(String args[])
    {
        WHG app = new WHG();
    }
}