import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Enemy {
	int x;
	int y;
	int startX;
	int moveLimit = 20;
	int direction = 0;
	int width = 30;
	int height= 10;
	
	

	public Enemy(int x, int y){
		this.x = x;
		this.y = y;
		startX = x;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public void paint(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.red);
		g2d.fillRect(x, y, width, height);
	}
	
	public void move(){
		if(direction == 0){ //moving right
			if(x == startX + moveLimit){
				direction = 1;
				y += 3;
			}
			else{
				x++;
			}
		}
		else{
			if(x == startX - moveLimit){
				direction = 0;
				y += 3;
			}
			else{
				x--;
			}
		}
	}
	
	public Rectangle getBorder(){
		return new Rectangle(x,y,width,height);
	}
}
