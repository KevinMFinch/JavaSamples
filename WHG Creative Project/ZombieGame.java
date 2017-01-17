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

public class ZombieGame extends JPanel implements KeyListener, Runnable, MouseListener
{
    int enemySpeed = 1;
    int charSpeed = 1;
    int enemyRadius = 7;
    int charRadius = 12;
    int boxLength = 20;
    boolean mouseIsPressed = false;
    int x;
    int y;

    JFrame frame = new JFrame();
    Thread runner;
    boolean downPressed = false;
    boolean upPressed = false;
    boolean rightPressed = false;
    boolean leftPressed = false;
    boolean notStopped = true;
    int[] numZombies = new int[7];
    int wave = 1;

    ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    Polygon p = new Polygon();
    BufferedReader input ;
    Ellipse2D.Double scoreBubble ;
    int enemyMoveTurn = 0;
    boolean dead = false ;
    boolean inbetweenLevels = true ;

    public ZombieGame()
    {
		for(int x=0;x<numZombies.length;x++)
		{
			numZombies[x] = (int)Math.pow(2,(x+1));
		}
        getPolygon();
        x = 350;
        y = 150;
        addMouseListener(this);
        frame.addKeyListener(this);
        frame.add(this);
        frame.setSize(1000, 1000);
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

			input = new BufferedReader(new FileReader(new File("levelLines.txt")));
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

		if(!dead)
		{
			if(!inbetweenLevels)
			{
				g2d.setColor(Color.BLUE);
				for (int z = 0; z < enemies.size(); z++)
				{
					g2d.fillOval(enemies.get(z).getX(), enemies.get(z).getY(), 2 * enemyRadius, 2 * enemyRadius);
				}

				g2d.setColor(Color.BLACK);
				g2d.draw(p);

				g2d.setColor(Color.RED);
				g2d.fillRect(x, y, 20, 20);
				g2d.setColor(Color.BLACK);
				g2d.drawRect(x - 1, y - 1, 21, 21);

				for(int z=0;z<bullets.size();z++)
				{
					g2d.fillRect(bullets.get(z).getX(),bullets.get(z).getY(), 5,5);
				}
			}
			else
			{
				g2d.setColor(Color.WHITE) ;
				g2d.fillRect(0,0,1000,500);
				g2d.setColor(Color.BLACK) ;
				String str = "You are about to begin level "+wave+". Press any key to continue..." ;
				g2d.drawString(str,300,250);
			}
		}
		else if(dead)
		{
			g2d.setColor(Color.WHITE) ;
			g2d.fillRect(0,0,1000,500) ;
			g2d.setColor(Color.BLACK);
			g2d.drawString("You lose! Would you like to play again? (y/n)",350,250);
		}

    }

    public void keyReleased(KeyEvent ke)
    {
        if (ke.getKeyCode() == 68)
        {
            rightPressed = false;
        }
        if (ke.getKeyCode() == 65)
        {
            leftPressed = false;
        }
        if (ke.getKeyCode() == 87)
        {
            upPressed = false;
        }
        if (ke.getKeyCode() == 83)
        {
            downPressed = false;
        }
    }

    public void keyPressed(KeyEvent ke)
    {
		if(inbetweenLevels)
		{
			generateEnemy();
			wave++ ;
			inbetweenLevels = false ;
		}
		if(dead)
		{
			if(ke.getKeyCode() == 89)
			{
				enemies.clear();
				wave = 1;
				dead = false ;
				inbetweenLevels = true ;
				x=350 ;
				y=150 ;
			}
			if(ke.getKeyCode() == 78)
			{
				System.exit(0) ;
			}
		}

        if (ke.getKeyCode() == 68)
        {
            rightPressed = true;
        }
        if (ke.getKeyCode() == 65)
        {
            leftPressed = true;
        }
        if (ke.getKeyCode() == 87)
        {
            upPressed = true;
        }
        if (ke.getKeyCode() == 83)
        {
            downPressed = true;
        }
    }

    public void keyTyped(KeyEvent ke)
    {

    }

    public int distanceFromCharacter(int xPos, int yPos)
    {
		double distance = Math.sqrt(Math.pow((x)-(xPos),2) + Math.pow((y)-(yPos),2));
		return (int)distance ;
	}

    public void generateEnemy()
    {
        //1 is right, 2 is left, 3 is up, 4 is down
		for(int x=0;x<numZombies[wave-1];x++)
		{
			boolean tooClose = true;
			int xPos;
			int yPos;
			while(tooClose)
			{
				xPos = (int)(Math.random()*850)+50;
				yPos = (int)(Math.random()*400)+50;
				if(distanceFromCharacter(xPos,yPos) >= 30)
				{
					tooClose = false;
					Enemy enemy = new Enemy(xPos,yPos,1);
					enemies.add(enemy);
				}
			}
		}

    }

    public Rectangle collisionBox()
    {
        return new Rectangle((int) x, (int) y, 20, 20);
    }

    public boolean intersectsEnemy(Enemy enemy)
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

				if(enemyMoveTurn %3==0)
				{
					for(int z=0;z<enemies.size();z++)
					{
						if(intersectsEnemy(enemies.get(z)))
						{
							loseGame() ;
							break ;
						}

						int enemyX = enemies.get(z).getX();
						int enemyY = enemies.get(z).getY();
						Enemy dude = enemies.get(z);

						boolean notMoved = true ;

						int dir = (int)(Math.random()*2)+1;

						if(dude.getX() > x && notMoved && dir ==1)
						{
							dude.updateCoordinates(enemyX-1,enemyY);
							notMoved=false;
						}
						if(dude.getX() < x && notMoved && dir==1)
						{
							dude.updateCoordinates(enemyX+1,enemyY);
							notMoved=false;
						}
						if(dude.getY() > y && notMoved && dir ==2)
						{
							dude.updateCoordinates(enemyX,enemyY -1);
							notMoved=false;
						}
						if(dude.getY() < y && notMoved && dir==2)
						{
							dude.updateCoordinates(enemyX,enemyY+1);
							notMoved =false;
						}
						notMoved = true ;


					}
				}
				enemyMoveTurn ++;

				for(int z=0;z<bullets.size();z++)
				{
					double theSlope = bullets.get(z).getSlope();
					if(bullets.get(z).getDir().equals("right"))
					{
						double nextX = 0.0 ;
						double nextY = 0.0 ;
						if(Math.abs(theSlope) <= 2.0)
						{
							nextX = bullets.get(z).getX() +1.0 ;
							nextY = bullets.get(z).getSlope() * nextX + bullets.get(z).getYInt()+0.0;
						}
						else
						{
							if(theSlope < 0)
							{
								nextY = bullets.get(z).getY() - 1 ;
								nextX = (nextY - bullets.get(z).getYInt()) / theSlope ;
							}
							else
							{
								nextY = bullets.get(z).getY() + 1 ;
								nextX = (nextY - bullets.get(z).getYInt()) / theSlope ;
							}
						}
						if(p.contains(new Rectangle((int)nextX,(int)nextY,5,5)))
						{
							bullets.get(z).updateCoordinates((int)nextX,(int)nextY);
						}
						else
						{
							bullets.remove(z) ;
							z--;
							break ;
						}

					}
					else
					{
						double nextX = 0.0;
						double nextY = 0.0;
						if(Math.abs(theSlope) <= 2.0)
						{
							nextX = bullets.get(z).getX() -1 ;
							nextY = (bullets.get(z).getSlope() * nextX + bullets.get(z).getYInt());
						}
						else
						{
							if(theSlope < 0)
							{
								nextY = bullets.get(z).getY() + 1 ;
								nextX = (nextY - bullets.get(z).getYInt()) / theSlope ;
							}
							else
							{
								nextY = bullets.get(z).getY() - 1 ;
								nextX = (nextY - bullets.get(z).getYInt()) / theSlope ;
							}
						}
						if(p.contains(new Rectangle((int)nextX,(int)nextY,5,5)))
						{
							bullets.get(z).updateCoordinates((int)nextX,(int)nextY);
						}
						else
						{
							bullets.remove(z) ;
							z--;
							break ;
						}
					}

					for(int w=0;w<enemies.size();w++)
					{
						if(enemies.get(w).collisionBall(enemyRadius).intersects(bullets.get(z).hitBox()))
						{
							enemies.remove(w) ;
							w-- ;
							bullets.remove(z);
							z--;
							break;
						}
					}
				}

				if(enemies.size() == 0)
				{
					if(!inbetweenLevels)
						inbetweenLevels = true ;
				}

                repaint();

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
		if(bullets.size()<10)
		{
			double mouseX = me.getX() - 0.0;
			double mouseY = me.getY() -15.0;
			double centerX = x+10.0;
			double centerY = y+10.0;
			double slope = (mouseY - centerY) / (mouseX - centerX) ;
			int b = (int)((-slope*centerX) + centerY) ;
			String dir="";
			if(mouseX > centerX)
			{
				dir="right" ;
			}
			else
			{
				dir="left" ;
			}

			Bullet bullet = new Bullet((int)centerX,(int)centerY,slope,b,dir) ;
			bullets.add(bullet);
		}

    }

    public void advanceLevel()
    {
		wave++ ;
		inbetweenLevels = true ;
		enemies.clear() ;
		bullets.clear();
	}

    public void loseGame()
    {
		dead = true ;
	}


    public static void main(String args[])
    {
        ZombieGame app = new ZombieGame();
    }
}