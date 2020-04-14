package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import States.StartUpState;

public class GenMtl {
	public static void generate() {
		try {
		String temppath = StartUpState.path.getAbsolutePath();
		String path = temppath.substring(0, temppath.length() - 4) + ".mtl";
		File file = new File(path);

		// if file doesnt exists, then create it
		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(path);
		BufferedWriter bw = new BufferedWriter(fw);
		
		bw.write("newmtl Material.001" + "\n");
		bw.write("map_Kd " + StartUpState.ImageName + "Texture.png\n");
		bw.close();
		
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		}
	}