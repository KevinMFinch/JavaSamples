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
import javax.imageio.*;

public class MissleCommand extends JPanel implements Runnable, MouseListener,KeyListener
{
	//5 per each rocket left, 100 for each city left, 25 for each enemy

	JFrame frame=new JFrame();
	Thread 	runner ;
	Image img ;
	Image bImage ;
	boolean notStopped = true;
	Polygon p1 = new Polygon() ;
	Polygon p2 = new Polygon() ;
	Polygon p3 = new Polygon() ;
	int level =1 ;
	int a1 = 5 ;
	int commonD = 3 ;
	int step = 0 ;
	int score =0;
	boolean inbetweenLevels ;
	boolean lostGame ;
	ArrayList<ExtendingLine> lines = new ArrayList<ExtendingLine>() ;
	ArrayList<Explosion> explosions = new ArrayList<Explosion>() ;
	ArrayList<Silo> silos = new ArrayList<Silo>() ;
	ArrayList<City> cities = new ArrayList<City>() ;
	ArrayList<ExtendingLine> enemies = new ArrayList<ExtendingLine>() ;

	public MissleCommand()
	{
		inbetweenLevels=true;
		frame.addKeyListener(this);
		addMouseListener(this);
		frame.add(this);
		frame.setSize(610,600);
		frame.setVisible(true);

		try
		{
			img = ImageIO.read(new File("City.gif"));
			bImage = ImageIO.read(new File("Bullet.gif"));
		}
		catch(IOException ie)
		{
		}

		p1.addPoint(0,500); p1.addPoint(25,480); p1.addPoint(75,480); p1.addPoint(100,500) ;
		p2.addPoint(250,500); p2.addPoint(275,480); p2.addPoint(325,480); p2.addPoint(350,500);
		p3.addPoint(500,500) ; p3.addPoint(525,480); p3.addPoint(575,480); p3.addPoint(600,500);

		//middles are 50,300,550

		generateSilos();
		generateEnemies() ;
		generateCities();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		runner= new Thread(this);
		runner.start();
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;

		g2d.setColor(Color.BLACK) ;
		g2d.fillRect(0,0,600,500) ;
		g2d.setColor(Color.BLUE);


		if(inbetweenLevels==true)
		{
			g2d.setColor(Color.WHITE);
			g2d.drawString("Starting level: "+level+". Press any key to begin the level...",150,250) ;
		}
		else if(lostGame ==true)
		{
			g2d.setColor(Color.WHITE);
			g2d.drawString("GAME OVER! Do you want to play again?(y/n)",150,250);
		}
		else
		{
			g2d.setColor(Color.YELLOW);
			g2d.drawString("Score: "+score,50,10);

			for(int z=0;z<explosions.size();z++)
			{
				Explosion ex = explosions.get(z) ;
				Ellipse2D splosion = new Ellipse2D.Double(ex.getX()-ex.getRadius()/2,ex.getY()- ex.getRadius()/2,ex.getRadius(),ex.getRadius());
				g2d.fill(splosion) ;
			}
			g2d.setColor(Color.BLUE) ;

			for(int z=0;z<lines.size();z++)
			{
				g2d.drawLine(lines.get(z).getStartX(),lines.get(z).getStartY(),lines.get(z).getCurrentX(),lines.get(z).getCurrentY()) ;
			}

			for(int z=0;z<cities.size();z++)
			{
				if(cities.get(z).getAlive())
				{
					g2d.drawImage(img,cities.get(z).getX(),cities.get(z).getY(),this) ;
				}
			}

			g2d.setColor(Color.RED);

			for(int z=0;z<enemies.size();z++)
			{
				if(enemies.get(z).isActive())
					g2d.drawLine(enemies.get(z).getStartX(),enemies.get(z).getStartY(),enemies.get(z).getCurrentX(),enemies.get(z).getCurrentY()) ;
			}

			g2d.setColor(new Color(255,255,0)) ;
			g2d.fill(p1) ;
			g2d.fill(p2) ;
			g2d.fill(p3) ;
			g2d.fillRect(0,500,600,30);

			for(int z=0;z<silos.size();z++)
			{
				switch(silos.get(z).getMissles())
				{
					case 10: g2d.drawImage(bImage,60+z*250,511,this) ;
					case 9 : g2d.drawImage(bImage,53+z*250,511,this) ;
					case 8 : g2d.drawImage(bImage,46+z*250,511,this) ;
					case 7 : g2d.drawImage(bImage,39+z*250,511,this) ;
					case 6 : g2d.drawImage(bImage,57+z*250,501,this) ;
					case 5 : g2d.drawImage(bImage,50+z*250,501,this) ;
					case 4 : g2d.drawImage(bImage,43+z*250,501,this) ;
					case 3 : g2d.drawImage(bImage,54+z*250,491,this) ;
					case 2 : g2d.drawImage(bImage,47+z*250,491,this) ;
					case 1 : g2d.drawImage(bImage,50+z*250,481,this) ;
							 break ;
				}
			}
		}

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
				if(inbetweenLevels==false && lostGame == false)
				{
					for(int z=0;z<explosions.size();z++)
					{
						if(explosions.get(z).getRadius() ==70)
						{
							explosions.get(z).setToContract();
						}
						if(explosions.get(z).getRadius() <=0)
						{
							explosions.remove(z) ;
							z-- ;
						}
						else
						{
							explosions.get(z).adjustRadius() ;
						}
					}

					for(int z=0;z<lines.size();z++)
					{
						lines.get(z).updateLine();
						if(lines.get(z).getCurrentY()<=lines.get(z).getEndY())
						{
							Explosion ex = new Explosion(lines.get(z).getCurrentX()-5,lines.get(z).getCurrentY()-5,5);
							explosions.add(ex);
							lines.remove(z);
							z--;
						}
					}

					if(step%4==0)
					{
						for(int z=0;z<enemies.size();z++)
						{
							if(enemies.get(z).isActive())
							{
								enemies.get(z).updateLine(-1);
								if(enemies.get(z).getCurrentY()>=enemies.get(z).getEndY())
								{
									int endX = enemies.get(z).getEndX();
									switch(endX)
									{
										case 50 :  enemies.remove(z);
												   z--;
												   while(silos.get(0).getMissles() != 0)
												   {
													   silos.get(0).loseMissle();
												   }
												   break ;
										case 110 :  Explosion exp = new Explosion(enemies.get(z).getCurrentX()-5,enemies.get(z).getCurrentY()-5,5);
													explosions.add(exp);
													cities.get(0).setDead() ;
													enemies.remove(z);
													z--;
													break ;
										case 160 :  exp = new Explosion(enemies.get(z).getCurrentX()-5,enemies.get(z).getCurrentY()-5,5);
													explosions.add(exp);
													cities.get(1).setDead() ;
													enemies.remove(z);
													z--;
													break ;
										case 210 :  exp = new Explosion(enemies.get(z).getCurrentX()-5,enemies.get(z).getCurrentY()-5,5);
													explosions.add(exp);
													cities.get(2).setDead() ;
													enemies.remove(z);
													z--;
													break ;
										case 300 :  enemies.remove(z);
													z--;
													while(silos.get(1).getMissles() != 0)
													{
													   silos.get(1).loseMissle();
													}
												   break ;
										case 360 :  exp = new Explosion(enemies.get(z).getCurrentX()-5,enemies.get(z).getCurrentY()-5,5);
													explosions.add(exp);
													cities.get(3).setDead() ;
													enemies.remove(z);
													z--;
													break ;
										case 410 :  exp = new Explosion(enemies.get(z).getCurrentX()-5,enemies.get(z).getCurrentY()-5,5);
													explosions.add(exp);
													cities.get(4).setDead() ;
													enemies.remove(z);
													z--;
													break ;
										case 460 :  exp = new Explosion(enemies.get(z).getCurrentX()-5,enemies.get(z).getCurrentY()-5,5);
													explosions.add(exp);
													cities.get(5).setDead() ;
													enemies.remove(z);
													z--;
													break ;
										case 550 : enemies.remove(z);
												   z--;
												   while(silos.get(2).getMissles() != 0)
												   {
													   silos.get(2).loseMissle();
												   }

												 break ;
									}
								}
							}
							else if(z != 0 && enemies.get(z-1).getCurrentY() > ((int)(Math.random()*150)+100))
							{
								enemies.get(z).setActive() ;
							}
						}
					}


					for(int z=0;z<explosions.size();z++)
					{
						Ellipse2D splosion=new Ellipse2D.Double(explosions.get(z).getX()-explosions.get(z).getRadius()/2,explosions.get(z).getY()-explosions.get(z).getRadius()/2,explosions.get(z).getRadius(),explosions.get(z).getRadius());
						for(int w=0;w<enemies.size();w++)
						{
							double dist=Math.sqrt(Math.pow(explosions.get(z).getX()-explosions.get(z).getRadius()-enemies.get(w).getCurrentX(),2)+Math.pow(explosions.get(z).getY()-explosions.get(z).getRadius()-enemies.get(w).getCurrentY(),2));
							if(splosion.contains(enemies.get(w).getCurrentX(),enemies.get(w).getCurrentY()))
							{
								Explosion ex = new Explosion(enemies.get(w).getCurrentX()-5,enemies.get(w).getCurrentY()-5,5) ;
								explosions.add(ex) ;
								enemies.remove(w) ;
								w--;
								score+=25 ;
							}
						}
					}

					int deadCount=0;
					for(int z=0;z<cities.size();z++)
					{
						if(cities.get(z).getAlive() == false)
						{
							deadCount++ ;
						}
					}

					if(deadCount==6)
					{
						loseGame();
					}

					if(enemies.size() == 0)
					{
						advanceLevel() ;
					}


					step++ ;
				}
				repaint();

			}

			try
			{
				Thread.sleep(6);
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

	}
	public void mousePressed(MouseEvent me)
	{
	}
	public void mouseClicked(MouseEvent me)
	{
			int startX = 0 ;
			if(me.getX() < 200)
			{
				if(silos.get(0).getMissles() > 0)
				{
					silos.get(0).loseMissle() ;
					startX = 50 ;
				}
				else if(silos.get(1).getMissles() >0)
				{
					silos.get(1).loseMissle();
					startX = 300 ;
				}
				else if(silos.get(2).getMissles() >0)
				{
					silos.get(2).loseMissle();
					startX = 550 ;
				}

			}
			else if(me.getX() <= 400 && me.getX() >= 200)
			{
				if(silos.get(1).getMissles() >0)
				{
					silos.get(1).loseMissle();
					startX = 300 ;
				}
				else if(silos.get(0).getMissles() >0)
				{
					silos.get(0).loseMissle() ;
					startX = 50 ;
				}
				else if(silos.get(2).getMissles() >0)
				{
					silos.get(2).loseMissle() ;
					startX = 550 ;
				}
			}
			else if(me.getX() > 400)
			{
				if(silos.get(2).getMissles() > 0 )
				{
					silos.get(2).loseMissle() ;
					startX =550 ;
				}
				else if(silos.get(1).getMissles()>0)
				{
					silos.get(1).loseMissle() ;
					startX = 300 ;
				}
				else if(silos.get(0).getMissles() >0)
				{
					silos.get(0).loseMissle();
					startX = 50 ;
				}
			}

			if(startX != 0)
			{
				double slope = (me.getY()+0.0 - 480) / (me.getX() - startX) ;
				int b = (int)((-slope*startX) + 480) ;
				ExtendingLine eL = new ExtendingLine(startX,480,me.getX(),me.getY(),b,slope);
				lines.add(eL) ;
			}

	}

	public void generateCities()
	{
		for(int z = 105;z<=205;z+=50)
		{
			City city = new City(z,480) ;
			cities.add(city);
		}
		for(int z = 355;z<=455;z+=50)
		{
			City city = new City(z,480) ;
			cities.add(city);
		}
	}

	public void generateEnemies()
	{
		int numEnemies = a1 + commonD*(level-1) ;

		for(int w = 0;w<numEnemies;w++)
		{
			int startX = (int)(Math.random()*600)+1 ;
			int target = (int)(Math.random()*9)+1 ;
			int endX = 0;
			switch(target)
			{
				case 1 : endX = 50 ;
						break ;
				case 2 : endX = 110 ;
						break ;
				case 3 : endX = 160 ;
						break;
				case 4 : endX = 210 ;
						break;
				case 5 : endX = 300 ;
						break;
				case 6 : endX = 360 ;
						break;
				case 7 : endX = 410 ;
						break;
				case 8 : endX = 460 ;
						break;
				case 9 : endX = 550 ;
						 break ;
			}
			double slope = (1+0.0 - 480) / (startX - endX) ;
			int b = (int)(1 - slope*startX) ;
			ExtendingLine eL = new ExtendingLine(startX,1,endX,480,b,slope);
			enemies.add(eL) ;
		}
		enemies.get(0).setActive() ;
	}

	public void advanceLevel()
	{
		for(int z=0;z<cities.size();z++)
		{
			score+=100 ;
		}
		for(int z=0;z<silos.size();z++)
		{
			score+=5*silos.get(z).getMissles();
		}
		level++ ;
		explosions.clear();
		generateEnemies() ;
		for(Silo si : silos)
		{
			si.reload();
		}
		inbetweenLevels=true ;
	}

	public void loseGame()
	{
		lostGame = true ;
	}

	public void generateSilos()
	{
		for(int z = 50;z<=550;z+=250)
		{
			silos.add(new Silo(z)) ;
		}
	}

	public void keyTyped(KeyEvent e)
	{

	}

	public void keyPressed(KeyEvent ke)
	{
		if(inbetweenLevels==true)
		{
			inbetweenLevels = false ;
		}
		if(lostGame == true && ke.getKeyCode() == 89)
		{
			lostGame=false ;
			level=1 ;
			score=0;
			inbetweenLevels=true;
			cities.clear();
			silos.clear();
			explosions.clear();
			lines.clear();
			enemies.clear();
			generateSilos();
			generateEnemies();
			generateCities();
		}
		else if(lostGame == true && ke.getKeyCode() == 78)
		{
			System.exit(0);
		}
	}

	public void keyReleased(KeyEvent e)
	{
    }

	public static void main(String args[])
	{
		MissleCommand app=new MissleCommand();
	}
}