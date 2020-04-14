package main;

import States.StartUpState;

public class Transform {
	
	public static String getVertFromFace(String face, int VertNum) {
		String[] tokens = face.substring(2,face.length()).split(" ");
		String[] rmVt = tokens[VertNum].split("/");
		int lineNum = Integer.parseInt(rmVt[0]);
		
		if(lineNum - 1 >= StartUpState.drawingPixels.length) return StartUpState.extrudedPixels[lineNum - 1 - StartUpState.drawingPixels.length];
		else return StartUpState.drawingPixels[lineNum - 1];
	}
	
	public static String translate(String pos, double x, double y, double z) {
		String[] values = pos.substring(2, pos.length()).split(" ");
		double[] xyz = new double[values.length];
		
		try {
			for(int i = 0; i < values.length; i++) {
				xyz[i] = Integer.parseInt(values[i].substring(0, values[i].length()));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		xyz[0] += x;
		xyz[1] += y;
		xyz[2] += z;
		
		return "v " + xyz[0] + " " + (xyz[1] + StartUpState.cat.getHeight()) + " " + xyz[2];
	}
	
	public static double getX(String pos) {
		String[] values = pos.substring(2, pos.length()).split(" ");
		double[] xyz = new double[values.length];
		
		try {
			for(int i = 0; i < values.length; i++) {
				xyz[i] = Double.parseDouble(values[i]);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return xyz[0];
	}
	
	public static double getY(String pos) {
		String[] values = pos.substring(2, pos.length()).split(" ");
		double[] xyz = new double[values.length];
		
		try {
			for(int i = 0; i < values.length; i++) {
				xyz[i] = Double.parseDouble(values[i]);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return xyz[1];
	}
	
	public static double getZ(String pos) {
		String[] values = pos.substring(2, pos.length()).split(" ");
		double[] xyz = new double[values.length];
		
		try {
			for(int i = 0; i < values.length; i++) {
				xyz[i] = Double.parseDouble(values[i]);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return xyz[2];
	}
}
