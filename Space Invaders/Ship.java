import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Ship {
	int x;
	int y;
	
	public Ship(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void paint(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.yellow);
		g2d.fillRect(x, y, 10, 10);
	}
	
	public void move(int dir){
		if(dir == 0){ //right
			x++;
		}
		else if(dir == 1){ //left
			x--;
		}
	}
	
}
