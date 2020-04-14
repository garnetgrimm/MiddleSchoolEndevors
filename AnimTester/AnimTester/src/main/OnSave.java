package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

public class OnSave {

	public static String FileExt = "txt";
	public static File saveLoc;
	public static boolean SavingAnim = false;
	
	public static void Save() {
		if (saveLoc == null) {
			JFileChooser fc2 = new JFileChooser();
			// fc2.addChoosableFileFilter();

			FileFilter filter = new FileFilter() {
				public boolean accept(File file) {
					if (file.isDirectory()) {
						return true;
					}
					String filename = file.getName();
					return (filename.endsWith("." +  FileExt));
				}

				public String getDescription() {
					return "*." +  FileExt;
				}
			};

			fc2.setFileFilter(filter);

			int returnValue2 = fc2.showOpenDialog(null);
			if (returnValue2 == JFileChooser.APPROVE_OPTION) {
				File selectedFile2 = fc2.getSelectedFile();
				saveLoc = selectedFile2;
			}
		}

		if(!saveLoc.toString().substring(saveLoc.toString().length() - 4, saveLoc.toString().length()).equals("." +  FileExt)) {
			String tempPath = saveLoc.toString();
			saveLoc = new File(tempPath + "." + FileExt);
		}
		
		if (saveLoc.exists()) {
			int dialogButton = JOptionPane.showConfirmDialog(null, "Would you like to override previous save?", "Warning", JOptionPane.YES_NO_OPTION);
			if(dialogButton == JOptionPane.YES_OPTION) {
				saveLoc.delete();
			}
		} 
		
		CreateSave();

	}
	
	public static void CreateSave() {
		try {
			FileWriter fw = new FileWriter(saveLoc);
			fw.write("XSIZE: " + Main.XSize + "\n");
			fw.write("YSIZE: " + Main.YSize + "\n");
			fw.write("SCALE: " + Main.Scale + "\n");
			fw.write("ANIMSPEED: " + Math.round(Main.Speed * 10) + "\n");
			fw.write("MOVESPEED: " + Math.round(Main.MovSpeed) + "\n");
			
			if(Anim.Started && !SavingAnim) {
				fw.write("ANIMS:");
				for(int i = 0; i < Anim.animNames.length; i++) {
					fw.write(" " + Anim.animNames[i] + "=" + Anim.animVals[i]);
				}
			}
			
			if(SavingAnim) {
				String tempSave = "";
				
				tempSave = "ANIMS: ";
				fw.write("ANIMS: ");
				if(Anim.Started) {
					for(int i = 0; i < Anim.animNames.length; i++) {
						tempSave = tempSave + Anim.animNames[i] + "=" + Anim.animVals[i] + " ";
						fw.write(Anim.animNames[i] + "=" + Anim.animVals[i] + " ");
					}
					tempSave = tempSave + CreationMenu.nameField.getText() + "=" + CreationMenu.OrderField.getText();
					fw.write(CreationMenu.nameField.getText() + "=" + CreationMenu.OrderField.getText() + "\n");
				} else {
					tempSave = "ANIMS: " + CreationMenu.nameField.getText() + "=" + CreationMenu.OrderField.getText();
					fw.write(CreationMenu.nameField.getText() + "=" + CreationMenu.OrderField.getText() + "\n");
				}
				Anim.getAnims(tempSave);
			}
			fw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		OnSave.SavingAnim = false;
		
	}
	
}
