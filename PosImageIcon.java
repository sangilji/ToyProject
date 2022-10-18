package ToyProject;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class PosImageIcon extends ImageIcon {
	int pX;            // ImageIcon�� X��ǥ
	   int pY;            // ImageIcon�� y��ǥ
	   int width;         // ImageIcon�� ����
	   int height;         // ImageIcon�� ����
	   
	   public PosImageIcon(String img, int x, int y, int width, int height) {
	      super(img);
	      pX=x;
	      pY=y;
	      this.width = width;
	      this.height = height;
	   }
	   
	   public void draw(Graphics g) {
	      g.drawImage(this.getImage(), pX, pY, width, height, null);
	   }

}
