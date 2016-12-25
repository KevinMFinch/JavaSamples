import javax.imageio.ImageIO;
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

public class SpaceInvaders extends JPanel implements Runnable, KeyListener
{
	
	JFrame frame=new JFrame();
	Thread 	runner ;
	boolean rightPressed=false;
	boolean leftPressed=false;
	boolean gameOver = false;
	Color lightGrey = new Color(156,157,161);
	Color white = Color.WHITE;
	Color grey = new Color(106,110,108);
	Ship ship ;
	ArrayList<Bullet> bulletList ;
	ArrayList<Enemy> enemyList ;
	int rightBorder = 600;
	int turnsForEnemy = 0;
	Image alienImg, shipImg;


	public SpaceInvaders()
	{
		try
		{
			alienImg = ImageIO.read(new File("Alien.gif"));
			shipImg = ImageIO.read(new File("Ship.gif"));
		}
		catch(IOException ie)
		{
		}
		bulletList = new ArrayList<Bullet>();
		enemyList = new ArrayList<Enemy>();
		for(int rows=0;rows<5;rows++){
			for(int cols = 0;cols<15;cols++){
				enemyList.add(new Enemy((cols+1)*35,(rows+1)*15));
			}
		}
		ship = new Ship(300,300);
		frame.addKeyListener(this);
		frame.add(this);
		frame.setSize(rightBorder,475);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		runner= new Thread(this);
		runner.start();
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		//Background
		g2d.setColor(Color.black);
		g2d.fillRect(0,0,rightBorder+10,450);
		g2d.drawImage(shipImg, ship.getX(), ship.getY(), this);
		for(int i = 0;i<bulletList.size();i++){
			bulletList.get(i).paint(g2d);
		}
		for(int i = 0;i<enemyList.size();i++){
			g2d.drawImage(alienImg, enemyList.get(i).getX(), enemyList.get(i).getY(), this);
		}

	}

	public void actionPerformed(ActionEvent e)
	{

	}

	public void run()
	{
		while(gameOver == false){
			if(turnsForEnemy % 3 ==0){
				updateEnemies();
			}
			updateShip();
			updateBullets();
			turnsForEnemy++;
			repaint();
			
			try
            {
                Thread.sleep(5);
            } catch (InterruptedException e)
            {
            }
		}
	}
	
	public void updateEnemies(){
		for(int i =0;i<enemyList.size();i++){
			enemyList.get(i).move();
		}
	}
    
	private void updateBullets() {
		for(int i =0;i<bulletList.size();i++){
			bulletList.get(i).move();
			for(int x=0;x<enemyList.size();x++){
				if(bulletList.get(i).getBorder().intersects(enemyList.get(x).getBorder())){
					bulletList.remove(i);
					enemyList.remove(x);
					i--;
					x--;
					break;
				}
			}
		}
		
	}

	private void updateShip() {
		if(rightPressed){
			ship.move(0);
		}
		else if(leftPressed){
			ship.move(1);
		}
		
	}

	public static void main(String args[])
	{
		SpaceInvaders app=new SpaceInvaders();
	}

	@Override
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			if(ship.getX() < rightBorder){
				rightPressed = true;
			}
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			if(ship.getX() > 0){
				leftPressed = true;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE){
			bulletList.add(new Bullet(ship.getX()+25,ship.getY()));
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			rightPressed = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			leftPressed = false;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}
}