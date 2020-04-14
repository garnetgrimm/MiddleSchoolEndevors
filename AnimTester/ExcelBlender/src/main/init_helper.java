package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

public class init_helper {

	public static boolean FillHighlight = false;
	public static int[][] Depth = null;
	public static int[][] Group = null;
	
	public static boolean started = false;
	public static File path;
	public static int[][] Pixels = null;
	public static BufferedImage img = null;
	public static int[] highestPixel = null;
	public static int[] lowestPixel = null;
	
	public static int drawingNum = 0;
	public static String[] drawingPixels = null;
	public static String[] extrudedPixels = null;
	public static String[] forFaces1 = null;
	public static String[] forFaces2 = null;
	
	public static int borderNum = 0;
	public static String[] borderPixels = null;
	
	public static String ImageName = "";
	
	public static boolean Down = false;
	public static boolean Click = false;
	public static int MouseX = 0;
	public static int MouseY = 0;
	
	public static int highestPoint = 1;
	
	public static void setDepths() {
		for(int x = 0; x < img.getHeight(); x++) {
			for(int y = 0; y < img.getHeight(); y++) {
				//only want to draw if its touching the pink
				if(Pixels[x][y] != -65281) {
						//if(x >= Highlighter.StartX && x < Highlighter.MouseX && y >= Highlighter.StartY && y < Highlighter.MouseY)
						//	Depth[x][y] = Integer.parseInt(textField.getText());
						//if(x < Highlighter.StartX && x >= Highlighter.MouseX && y < Highlighter.StartY && y >= Highlighter.MouseY)
						//	Depth[x][y] = Integer.parseInt(textField.getText());
				}
			}
		}
	}
	
	public static void calcHighest() {
		for(int x = 0; x < img.getWidth(); x++) {
			for(int y = 0; y < img.getHeight(); y++) {
				if(Depth[x][y] > highestPoint) highestPoint = Depth[x][y];
			}
		}
		
		//possible %% by 16 in case depth is greater than 16
		if(highestPoint > 16) highestPoint = 16;
		if(highestPoint < 1) highestPoint = 1;
	}
	
	public static String getImageFromPath(String path) {
		System.out.println(path.toString());
		String[] tokens = path.toString().split("\\\\");
		System.out.println(tokens[tokens.length - 1].substring(0, tokens[tokens.length - 1].length() - 4));
		return tokens[tokens.length - 1].substring(0, tokens[tokens.length - 1].length() - 4);		
	}
	
	public static boolean touchingPink(int x, int y) {
		try{
			if(Pixels[x - 1][y] == -65281) return true;
			if(Pixels[x + 1][y] == -65281) return true;
			if(Pixels[x][y - 1] == -65281) return true;
			if(Pixels[x][y + 1] == -65281) return true;
		} catch(Exception e) {}	
		
		return false;
	}
	
	public static void openJChooser() {
		JFileChooser fc1 = new JFileChooser();

		FileFilter filter = new FileFilter() {
			public boolean accept(File file) {
				if (file.isDirectory()) {
					return true;
				}
				String filename = file.getName();
				return (filename.endsWith(".png"));
			}

			public String getDescription() {
				return "*.png";
			}
		};

		fc1.setFileFilter(filter);

		int returnValue1 = fc1.showOpenDialog(null);
		if (returnValue1 == JFileChooser.APPROVE_OPTION) {
			File selectedFile1 = fc1.getSelectedFile();
			path = selectedFile1;
			System.out.println(path);
			init.Done.setEnabled(true);
		}
	}
	
	public static void start() {
		
		started = true;
		ImageName = getImageFromPath(path.toString());
		
		try {
			img = ImageIO.read(new File(path.toString()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		highestPixel = new int[img.getWidth()];
		lowestPixel = new int[img.getWidth()];
		
		for(int i = 0; i < img.getWidth(); i++) {
			lowestPixel[i] = 0;
			highestPixel[i] = -1;
		}
		
		Pixels = new int[img.getWidth()][img.getHeight()];
		Depth = new int[img.getWidth()][img.getHeight()];
		Group = new int[img.getWidth()][img.getHeight()];
		
		for(int x = 0; x < img.getWidth(); x++) {
			for(int y = 0; y < img.getHeight(); y++) {
				Pixels[x][y] = img.getRGB(x, y);
				Depth[x][y] = 0;
				Group[x][y] = 0;
				//if the color is not magic pink
				if(Pixels[x][y] != -65281) {
					if(y >= lowestPixel[x]) lowestPixel[x] = y;
					if(highestPixel[x] < 0) highestPixel[x] = y;
				}
			}
		}
		
		for(int x = 0; x < img.getWidth(); x++) {
			for(int y = 0; y < img.getHeight(); y++) {
				if(Pixels[x][y] != -65281) {
					
					if(x != 0 && x != img.getWidth() || x == 0 || x == img.getWidth() - 1) {
						drawingNum++;
						if(touchingPink(x, y)) borderNum++; 
					}
				}
			}
		}
		
		forFaces1 = new String[drawingNum];
		forFaces2 = new String[drawingNum];
		
		drawingPixels = new String[drawingNum * 4];
		extrudedPixels = new String[drawingNum * 4];
		borderPixels = new String[borderNum];
	}
	
	public static void setValues() {
		
		int currDPixel = 0;
		int currBPixel = 0;
		int currFPixel = 0;
		
		for(int x = 0; x < img.getWidth(); x++) {
			for(int y = 0; y < img.getHeight(); y++) {
				if(Pixels[x][y] != -65281) {
					if(x != 0 && x != img.getWidth() || x == 0 || x == img.getWidth() - 1) {
						
						forFaces1[currFPixel] = "v " + (x) + " " + ((-y + img.getHeight())) + " " + (-Depth[x][y] / 2);
						forFaces2[currFPixel] = "v " + (x) + " " + ((-y + img.getHeight())) + " " +(Depth[x][y] / 2);
						
						drawingPixels[currDPixel] = "v " + (x + 0.5) + " " + ((-y + img.getHeight()) + 0.5) + " " + (-Depth[x][y] / 2);
						drawingPixels[currDPixel + 1] = "v " + (x + 0.5) + " " + ((-y + img.getHeight()) + -0.5) + " " + (-Depth[x][y] / 2);
						drawingPixels[currDPixel + 2] = "v " + (x + -0.5) + " " + ((-y + img.getHeight()) + 0.5) + " " + (-Depth[x][y] / 2);
						drawingPixels[currDPixel + 3] = "v " + (x + -0.5) + " " + ((-y + img.getHeight()) + -0.5) + " " + (-Depth[x][y] / 2);

						extrudedPixels[currDPixel] = "v " + (x + 0.5) + " " + ((-y + img.getHeight()) + 0.5) + " " + (Depth[x][y] / 2);
						extrudedPixels[currDPixel + 1] = "v " + (x + 0.5) + " " + ((-y + img.getHeight()) + -0.5) + " " + (Depth[x][y] / 2);
						extrudedPixels[currDPixel + 2] = "v " + (x + -0.5) + " " + ((-y + img.getHeight()) + 0.5) + " " + (Depth[x][y] / 2);
						extrudedPixels[currDPixel + 3] = "v " + (x + -0.5) + " " + ((-y + img.getHeight()) + -0.5) + " " + (Depth[x][y] / 2);
						
						currDPixel+=4;
						currFPixel+=1;
						if(touchingPink(x, y)) {
							borderPixels[currBPixel] = "v " + x + " " + (-y + img.getHeight()) + " 0"; 
							currBPixel++;
						}
				}
				}
			}
		}
	}
	
	public static void finished() {
		JOptionPane.showMessageDialog(null, "Finished");
	}

	public void update() {
		
		//if(open.isMouseOver(arg0) && Click) openJChooser();
		//if(save.isMouseOver(arg0) && Click) { Grouping.setGroups(); setValues(); SaveModel.writeFile(); }
		//if(done.isMouseOver(arg0) && Click) { setDepths(); DrawDepth.calcHighest(); }
		
	}

}