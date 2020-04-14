package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import States.StartUpState;

public class WriteTexture {
  static public void write() {
    try {
      int width = StartUpState.cat.getWidth() * 10, height = StartUpState.cat.getHeight() * 10;

      // TYPE_INT_ARGB specifies the image format: 8-bit RGBA packed
      // into integer pixels
      BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

      Graphics2D g = bi.createGraphics();
      
      g.setColor(Color.black);
      g.fillRect(0, 0, width, height);
      
      g.scale(10, 10);
      
		for(int x = 0; x < StartUpState.cat.getWidth(); x++) {
			for(int y = 0; y < StartUpState.cat.getHeight(); y++) {
				//only want to draw if its touching the pink
				if(StartUpState.Pixels[x][y] != -65281) {
						g.setColor(new Color(StartUpState.Pixels[x][y]));
						g.fillRect(x, y, 1, 1);
				}
						//make so it spits out a blender readable file
						//containing x and y information of each border pixel position
			}
		}

      String temppath = StartUpState.path.getAbsolutePath();
      String path = temppath.substring(0, temppath.length() - 4) + "Texture.png";
      ImageIO.write(bi, "PNG", new File(path));
      
      
    } catch (IOException ie) {
      ie.printStackTrace();
    }

  }
}