package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import States.StartUpState;

public class SaveModel {	
	
	static int Vnum = 0;
	public static String[] face = null;
	public static String[] face1 = null;
	public static String[] face2 = null;
	public static String[] face3 = null;
	public static String[] ALLFACES = null;
	//public static List<Integer> zeroed = new ArrayList<Integer>();
	public static String[] rescueVerts = null;
	public static String[] vt = null;
	
	public static String[] removeDuplicates(String[] arr) {
		  String[] hs = new HashSet<String>(Arrays.asList(arr)).toArray(new String[0]); 
		  return hs;
	}
	
	public static String getVtFromV(String s) {
		String vt = "";
		float x = (float) Transform.getX(s);
		float y = (float) Transform.getY(s);
		x += 0.5;
		y -= 0.5;
		x = x / StartUpState.cat.getWidth();
		y = y / StartUpState.cat.getHeight();
		
		vt = "vt " + x + " " + y;
		return vt;
	}
	
	public static void findFaceVerts() {
		findXFaceVerts();
		findYFaceVerts();
		findZFaceVerts();
		
		ALLFACES = new String[face.length + face1.length + face2.length + face3.length];
		int currNum = 0;
		
		for(int i = 0; i < face.length; i++) {
			if(!face[i].equals("$")) {
				ALLFACES[currNum] = face[i];
				currNum++;
			}
		}
		
		for(int i = 0; i < face1.length; i++) {
			if(!face1[i].equals("$")) {
				ALLFACES[currNum] = face1[i];
				currNum++;
			}
		}
		
		for(int i = 0; i < face2.length; i++) {
			if(!face2[i].equals("$")) {
				ALLFACES[currNum] = face2[i];
				currNum++;
			}
		}
		
		for(int i = 0; i < face3.length; i++) {
			if(!face3[i].equals("$")) {
				ALLFACES[currNum] = face3[i];
				currNum++;
			}
		}
		
		String[] SortedAllFaces = Grouping.sortFaces(ALLFACES);
		ALLFACES = SortedAllFaces;
		
	}
	
	public static String[] sort(String[] list) {
		List<Tuple> cords = new ArrayList<Tuple>();
		
		for(int i = 0; i < list.length; i++) {
			double x = Transform.getX(list[i]);
			double y = Transform.getY(list[i]);
			double z = Transform.getZ(list[i]);
			Tuple t = new Tuple(x,y,z);
			cords.add(t);
		}

		Collections.sort(cords);
		
		String[] cordsString = new String[cords.size()];
		for(int i = 0; i < cords.size(); i++) {
			cordsString[i] = cords.get(i).toAString();
		}
		
		return cordsString;
		
	}
	
	public static void findXFaceVerts() {
		face1 = new String[StartUpState.forFaces1.length];
		face2 = new String[StartUpState.forFaces2.length];
		int[] f1vert = new int[4];
		int[] f2vert = new int[4];

		String[] temp1 = removeDuplicates(StartUpState.forFaces1);
		StartUpState.forFaces1 = temp1;
		
		String[] temp2 = removeDuplicates(StartUpState.forFaces2);
		StartUpState.forFaces2 = temp2;
		
		for(int t = 0; t < StartUpState.forFaces1.length; t++) {
			double x = Transform.getX(StartUpState.forFaces1[t]);
			double y = Transform.getY(StartUpState.forFaces1[t]);
			
			double z = Transform.getZ(StartUpState.forFaces1[t]);
			
			for(int i = 0; i < StartUpState.drawingPixels.length; i++) {
				double x1 = Transform.getX(StartUpState.drawingPixels[i]);
				double y1 = Transform.getY(StartUpState.drawingPixels[i]);
				double z1 = Transform.getZ(StartUpState.drawingPixels[i]);
				
				if((x + 0.5) == x1 && (y + 0.5) == y1 && z == z1) f1vert[0] = i + 1;
				if((x + 0.5) == x1 && (y + -0.5) == y1 && z == z1) f1vert[1] = i + 1;
				if((x + -0.5) == x1 && (y + 0.5) == y1 && z == z1) f1vert[2] = i + 1;
				if((x + -0.5) == x1 && (y + -0.5) == y1 && z == z1) f1vert[3] = i + 1;
			}
			
			for(int i = 0; i < StartUpState.extrudedPixels.length; i++) {
				double x1 = Transform.getX(StartUpState.extrudedPixels[i]);
				double y1 = Transform.getY(StartUpState.extrudedPixels[i]);
				double z1 = Transform.getZ(StartUpState.extrudedPixels[i]);
				
				if((x + 0.5) == x1 && (y + 0.5) == y1 && -z == z1) f2vert[0] = StartUpState.drawingPixels.length + i + 1;
				if((x + 0.5) == x1 && (y + -0.5) == y1 && -z == z1) f2vert[1] = StartUpState.drawingPixels.length + i + 1;
				if((x + -0.5) == x1 && (y + 0.5) == y1 && -z == z1) f2vert[2] = StartUpState.drawingPixels.length + i + 1;
				if((x + -0.5) == x1 && (y + -0.5) == y1 && -z == z1) f2vert[3] = StartUpState.drawingPixels.length + i + 1;
			}
			
			face1[t] = "f " + f1vert[1] + "/"  + f1vert[1] + " " + f1vert[0] + "/"  + f1vert[0] + " " + f1vert[2] + "/"  + f1vert[2]  + " " + f1vert[3] + "/"  + f1vert[3];
			face2[t] = "f " + f2vert[1] + "/"  + f2vert[1] + " " + f2vert[0] + "/"  + f2vert[0] + " " + f2vert[2] + "/"  + f2vert[2]  + " " + f2vert[3] + "/"  + f2vert[3];
		}
	}
	
	public static void findYFaceVerts() {
		face = new String[StartUpState.drawingPixels.length];
		int[] vert = new int[4];
		int[] viewingVert = new int[4];
		
		for(int t = 0; t < face.length; t++) {
			double startX = Transform.getX(StartUpState.drawingPixels[t]);
			double startY = Transform.getY(StartUpState.drawingPixels[t]);
			double startZ = Transform.getZ(StartUpState.drawingPixels[t]);
			
			for(int i = 0; i < StartUpState.drawingPixels.length; i++) {
				double x1 = Transform.getX(StartUpState.drawingPixels[i]);
				double y1 = Transform.getY(StartUpState.drawingPixels[i]);
				double z1 = Transform.getZ(StartUpState.drawingPixels[i]);
				
				double x2 = Transform.getX(StartUpState.extrudedPixels[i]);
				double y2 = Transform.getY(StartUpState.extrudedPixels[i]);
				double z2 = Transform.getZ(StartUpState.extrudedPixels[i]);
				
				if(x1 == startX && y1 == startY && z1 == startZ)  { vert[3] = i + 1; viewingVert[0] = i; }
				if(x2 == startX && y2 == startY && z2 == -startZ) { vert[1] = StartUpState.drawingPixels.length + i + 1; viewingVert[1] = i; }
				
				try {
					if(x1 == startX && y1 == startY - 1 && z1 == startZ) { vert[2] = i + 1; viewingVert[2] = i; }
					if(x2 == startX && y2 == startY - 1 && z2 == -startZ) {  vert[0] = StartUpState.drawingPixels.length + i + 1; viewingVert[3] = i; }
				} catch(Exception e) {}
				
			}
			
			double x1 = Transform.getX(StartUpState.extrudedPixels[viewingVert[3]]);
			double x2 = Transform.getX(StartUpState.extrudedPixels[viewingVert[1]]);
			double x3 = Transform.getX(StartUpState.drawingPixels[viewingVert[2]]);
			double x4 = Transform.getX(StartUpState.drawingPixels[viewingVert[0]]);
			
			if(x1 == x2 && x2 == x3 && x3 == x4 && t != 0) {
				if(t != 0) {
					face[t] = "f " + vert[1] + "/"  + vert[1] + " " + vert[0] + "/"  + vert[0] + " " + vert[2] + "/"  + vert[2]  + " " + vert[3] + "/"  + vert[3];
				}
			} else {
				face[t] = "$";
			}
		}
	}
	
	public static void findZFaceVerts() {
		face3 = new String[StartUpState.drawingPixels.length];
		int[] vert = new int[4];
		int[] viewingVert = new int[4];
		
		for(int t = 0; t < face.length; t++) {
			double startX = Transform.getX(StartUpState.drawingPixels[t]);
			double startY = Transform.getY(StartUpState.drawingPixels[t]);
			double startZ = Transform.getZ(StartUpState.drawingPixels[t]);
			
			for(int i = 0; i < StartUpState.drawingPixels.length; i++) {
				double x1 = Transform.getX(StartUpState.drawingPixels[i]);
				double y1 = Transform.getY(StartUpState.drawingPixels[i]);
				double z1 = Transform.getZ(StartUpState.drawingPixels[i]);
				
				double x2 = Transform.getX(StartUpState.extrudedPixels[i]);
				double y2 = Transform.getY(StartUpState.extrudedPixels[i]);
				double z2 = Transform.getZ(StartUpState.extrudedPixels[i]);
				
				if(x1 == startX && y1 == startY && z1 == startZ)  { vert[3] = i + 1; viewingVert[0] = i; }
				if(x2 == startX && y2 == startY && z2 == -startZ) { vert[1] = StartUpState.drawingPixels.length + i + 1; viewingVert[1] = i; }
				
				try {
					if(x1 == startX + 1 && y1 == startY && z1 == startZ) { vert[2] = i + 1; viewingVert[2] = i; }
					if(x2 == startX + 1 && y2 == startY && z2 == -startZ) {  vert[0] = StartUpState.drawingPixels.length + i + 1; viewingVert[3] = i; }
				} catch(Exception e) {}
				
			}
			
			double x1 = Transform.getX(StartUpState.extrudedPixels[viewingVert[3]]);
			double x2 = Transform.getX(StartUpState.extrudedPixels[viewingVert[1]]);
			double x3 = Transform.getX(StartUpState.drawingPixels[viewingVert[2]]);
			double x4 = Transform.getX(StartUpState.drawingPixels[viewingVert[0]]);
			
			x2++;
			x4++;
			
			if(x1 == x2 && x2 == x3 && x3 == x4 && t != 0) {
				face3[t] = "f " + vert[1] + "/"  + vert[1] + " " + vert[0] + "/"  + vert[0] + " " + vert[2] + "/"  + vert[2]  + " " + vert[3] + "/"  + vert[3];
			} else {
				face3[t] = "$";
			}
		}
		//also change it to be only border pixels maybe
	}
	
	public static void writeFile() {
		
		try {
			String temppath = StartUpState.path.getAbsolutePath();
			String path = temppath.substring(0, temppath.length() - 4) + ".obj";
			File file = new File(path);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(path);
			BufferedWriter bw = new BufferedWriter(fw);

			String[] etemp = sort(StartUpState.extrudedPixels);
			StartUpState.extrudedPixels = etemp;
			String[] dtemp = sort(StartUpState.drawingPixels);
			StartUpState.drawingPixels = dtemp;
			
			bw.write("mtllib " + StartUpState.ImageName + ".mtl" + "\n");
			bw.write("usemtl Material.001" + "\n");			
			
			String[] temp1 = removeDuplicates(StartUpState.drawingPixels);
			StartUpState.drawingPixels = temp1;
			
			for(int i = 0; i < StartUpState.drawingPixels.length; i++) {
					bw.write(StartUpState.drawingPixels[i] + "\n");
					bw.write(getVtFromV(StartUpState.drawingPixels[i]) + "\n");
			}
			
			String[] temp2 = removeDuplicates(StartUpState.extrudedPixels);
			StartUpState.extrudedPixels = temp2;
			
			for(int i = 0; i < StartUpState.extrudedPixels.length; i++) {
				bw.write(StartUpState.extrudedPixels[i] + "\n");
				bw.write(getVtFromV(StartUpState.extrudedPixels[i]) + "\n");
			}
			
			findFaceVerts();
			
			for(int i = 0; i < ALLFACES.length; i++) {
				if(!(ALLFACES[i] == null)) bw.write(ALLFACES[i] + "\n");
			}
			 
			bw.close();
			
			WriteTexture.write();
			GenMtl.generate();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		StartUpState.finished();
	}
}
