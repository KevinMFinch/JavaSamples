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

public class Eskiv extends JPanel implements KeyListener,Runnable, MouseListener
{
	int enemySpeed = 1;
	int charSpeed = 1;
	int enemyRadius =5;
	int charRadius =12;
	int boxLength=20;
	boolean mouseIsPressed = false;
	int x;
	int y;
	int xx;
	int yy;
	int score;
	JFrame frame=new JFrame();
	Thread 	runner ;
	boolean downPressed=false;
	boolean upPressed=false;
	boolean rightPressed=false;
	boolean leftPressed=false;
	boolean notStopped = true;
	Color lightGrey = new Color(156,157,161);
	Color white = Color.WHITE;
	Color grey = new Color(106,110,108);


	ArrayList<EskivEnemy> enemies = new ArrayList<EskivEnemy>(); ;

	public Eskiv()
	{
		generateEnemy();
		x=300;
		y=300;
		frame.addMouseListener(this);
		frame.addKeyListener(this);
		frame.add(this);
		frame.setSize(600,475);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		newRect();
		runner= new Thread(this);
		runner.start();
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		//Background
		g2d.setColor(grey);
		g2d.fillRect(0,0,650,450);
		//Game area /Bounds are 150,550
		g2d.setColor(lightGrey);
		g2d.fillRect(150,25,400,400);

		Polygon p=new Polygon();
		p.addPoint(150,25); //140,25
		p.addPoint(550,25); //540,25
		p.addPoint(550,35); //540,35
		p.addPoint(150,35); //150,35
		GradientPaint gp=new GradientPaint(150.0f, 25.0f, grey, 150.0f, 35.0f, lightGrey, true);
		g2d.setPaint(gp);
		g2d.fill(p);

		Polygon p2=new Polygon();
		p2.addPoint(150,25);
		p2.addPoint(160,35);
		p2.addPoint(160,425);
		p2.addPoint(150,425);
		GradientPaint gp2=new GradientPaint(150.0f, 35.0f, grey, 160.0f, 35.0f, lightGrey, true);
		g2d.setPaint(gp2);
		g2d.fill(p2);
		//Score box

		g2d.setColor(grey);
		g2d.fillRect(xx,yy,boxLength,boxLength);
		g2d.setColor(Color.BLACK);
		g2d.drawRect(xx-1,yy-1,boxLength+1,boxLength+1);
		//Text
		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("Courier",Font.BOLD,25));
		g2d.drawString("Score:",32,197);
		g2d.drawString(""+score,32,227);
		g2d.setFont(new Font("Courier",Font.BOLD,45));
		g2d.drawString("Eskiv",5,50);
		g2d.setFont(new Font("Courier",Font.BOLD,30));
		//New Button
		if(mouseIsPressed == false)
		{
			g2d.setColor(lightGrey);
			RoundRectangle2D.Double roundRectOutline = new RoundRectangle2D.Double(27,85,100,50,20,20);
			g2d.fill(roundRectOutline);
			g2d.setColor(white);
			RoundRectangle2D.Double roundRectInside = new RoundRectangle2D.Double(32,87,90,45,20,20);
			g2d.fill(roundRectInside);
			g2d.setColor(lightGrey);
			g2d.drawString("New",50,117);
		}
		else
		{
			g2d.setColor(lightGrey);
			RoundRectangle2D.Double roundRectOutline = new RoundRectangle2D.Double(24,88,100,50,20,20);
			g2d.fill(roundRectOutline);
			g2d.setColor(white);
			RoundRectangle2D.Double roundRectInside = new RoundRectangle2D.Double(30,90,90,45,20,20);
			g2d.fill(roundRectInside);
			g2d.setColor(lightGrey);
			g2d.drawString("New",47,120);
		}
		//Enemies
		g2d.setColor(Color.BLUE);
		for(int z=0; z<enemies.size();z++)
		{
			g2d.fillOval(enemies.get(z).getX(),enemies.get(z).getY(),2*enemyRadius,2*enemyRadius);
		}
		//Character
		g2d.setColor(Color.BLACK);
		g2d.fillOval(x,y,2*charRadius,2*charRadius);

		//LINESU
		g2d.setColor(lightGrey);
		g2d.draw(new Line2D.Double(22, 380, 92, 380)); //(x1,y1,x2,y2)
		g2d.draw(new Line2D.Double(22, 379, 92, 379)); //(x1,y1,x2,y2)
		g2d.draw(new Line2D.Double(29, 350, 29, 390)); //(x1,y1,x2,y2)
		g2d.draw(new Line2D.Double(28, 350, 28, 390));

		g2d.setFont(new Font("Courier",Font.BOLD,20));
		g2d.setColor(white);
		g2d.drawString("by kf",30,378);

		g2d.draw(new Line2D.Double(150,425,550,425));
		g2d.draw(new Line2D.Double(550,25,550,425));
		g2d.setColor(lightGrey);
		g2d.draw(new Line2D.Double(150,25,550,25));


	}

	public void keyReleased(KeyEvent ke)
	{
		if(ke.getKeyCode()==39)
		{
			rightPressed = false;
		}
		if(ke.getKeyCode()==37)
		{
			leftPressed=false;
		}
		if(ke.getKeyCode()==38)
		{
			upPressed=false ;
		}
		if(ke.getKeyCode()==40)
		{
			downPressed=false;
		}
	}

	public void keyPressed(KeyEvent ke)
	{
		if(ke.getKeyCode()==39)
		{
			rightPressed = true;
		}
		if(ke.getKeyCode()==37)
		{
			leftPressed=true;
		}
		if(ke.getKeyCode()==38)
		{
			upPressed=true ;
		}
		if(ke.getKeyCode()==40)
		{
			downPressed=true;
		}
	}

	public void keyTyped(KeyEvent ke)
	{

	}


	public void newRect()
	{
		xx = (int)(Math.random()*(400-boxLength))+151 ;
		yy = (int)(Math.random()*(400-boxLength))+25 ;
	}

	public void generateEnemy()
	{
		int randX = (int)(Math.random()*390)+151 ;
		int randY = (int)(Math.random()*390)+25 ;
		int randDir = (int)(Math.random()*4)+1;
		EskivEnemy enemy = new EskivEnemy(randX,randY,randDir);
		enemies.add(enemy);
	}

	public Ellipse2D.Double collisionBall()
	{
		// We get better gameplay with a 90% collison box
		return new Ellipse2D.Double(x,y,2*charRadius,2*charRadius);
    }

	public Rectangle rectCollisionbox()
	{
		return new Rectangle((int)xx, (int)yy, boxLength,boxLength );
	}

	public boolean intersectsEnemy(EskivEnemy enemy)
	{
		Double distance = Math.sqrt(Math.pow((x+charRadius)-(enemy.getX() +enemyRadius),2) + Math.pow((y+charRadius)-(enemy.y+enemyRadius),2));
		return distance < charRadius + enemyRadius ;
	}

	public void actionPerformed(ActionEvent e)
	{

	}

	public void run()
	{
		while(true)
		{
			if(notStopped)
			{

				if(rightPressed && x+(2*charRadius)<=550 )
				{
					x+=1;
				}
				if(leftPressed && x-1>=150)
				{
					x+=-1;
				}
				if(upPressed && y-1>=25)
				{
					y+=-1;
				}
				if(downPressed && y+(2*charRadius)<=425)
				{
					y+=1;
				}

				for(int z=0;z<enemies.size();z++)
				{
					//1 is right, 2 is left, 3 is up, 4 is down
					switch(enemies.get(z).getDir())
					{
						case 1: if(enemies.get(z).getX()+8 <550)
								{
									enemies.get(z).changeX(enemySpeed);
									break;
								}
								else
								{
									enemies.get(z).setDir(2);
									enemies.get(z).changeX(-enemySpeed);
								}
								break;
						case 2: if(enemies.get(z).getX()-3 >= 150)
								{
									enemies.get(z).changeX(-enemySpeed);
									break;
								}
								else
								{
									enemies.get(z).setDir(1);
									enemies.get(z).changeX(enemySpeed);
								}
								break;
						case 3: if(enemies.get(z).getY()-3 >= 25)
								{
									enemies.get(z).changeY(-enemySpeed);
									break;
								}
								else
								{
									enemies.get(z).setDir(4);
									enemies.get(z).changeY(enemySpeed);
								}
								break;
						case 4: if(enemies.get(z).getY()+8 < 425)
								{
									enemies.get(z).changeY(enemySpeed);
									break;
								}
								else
								{
									enemies.get(z).setDir(3);
									enemies.get(z).changeY(-enemySpeed);
								}
								break;
					}
				}

				repaint();
				if(collisionBall().intersects(rectCollisionbox()))
				{
					newRect();
					generateEnemy();
					score+=5;
				}
				for(int count=0;count <enemies.size();count++)
				{
					if(intersectsEnemy(enemies.get(count)))
					{
						notStopped=false;
					}
				}

		}
		try
		{
			Thread.sleep(5);
		}
		catch (InterruptedException e)
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
		if(mouseIsPressed)
		{
			mouseIsPressed = false;

		}
	}
	public void mousePressed(MouseEvent me)
	{
		if((me.getX() >=35 && me.getX() <=135) && (me.getY() >=85 && me.getY() <=185))
		{
			mouseIsPressed = true;
			resetGame();
			repaint();
		}
	}
	public void mouseClicked(MouseEvent me)
	{

	}

	public void resetGame()
	{
		x=300;
		y=300;
		enemies=new ArrayList<EskivEnemy>();
		generateEnemy();
		score=0;
		newRect();
		notStopped=true;
	}
	public static void main(String args[])
	{
		Eskiv app=new Eskiv();
	}
}