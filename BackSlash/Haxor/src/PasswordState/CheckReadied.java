package PasswordState;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import Networking.Client;
import Networking.Server;
import main.Main;

public class CheckReadied {
	private static Image Passcheck;
	private static Image Clientcheck;
	private static Image Servercheck;
	private static Image NPasscheck;
	private static Image NClientcheck;
	private static Image NServercheck;

	private static Image EPasscheck;
	private static Image EClientcheck;
	private static Image EServercheck;
	private static Image ENPasscheck;
	private static Image ENClientcheck;
	private static Image ENServercheck;
	
	public static boolean EPassReady = false;
	public static boolean EClientReady = false;
	public static boolean EServerReady = false;
	
	public static boolean PassReady = false;
	public static boolean ClientReady = false;
	public static boolean ServerReady = false;
	public static boolean ServerFirst = false;
	
	public static boolean AllReady = false;
	
	public static int MosX = 0;
	public static int MosY = 0;
	public static boolean Click = false;
	
	public static int CTimer = 0;
	public static int STimer = 0;
	
	public static String password = "";
	
	public static void init(GameContainer container, StateBasedGame menu) throws SlickException {
		Passcheck = new Image("res/ccheck.png", false, Image.FILTER_NEAREST);
		Clientcheck = new Image("res/ccheck.png", false, Image.FILTER_NEAREST);
		Servercheck = new Image("res/ccheck.png", false, Image.FILTER_NEAREST);
		
		NPasscheck = new Image("res/notcheck.png", false, Image.FILTER_NEAREST);
		NClientcheck = new Image("res/notcheck.png", false, Image.FILTER_NEAREST);
		NServercheck = new Image("res/notcheck.png", false, Image.FILTER_NEAREST);
		
		EPasscheck = new Image("res/ccheck.png", false, Image.FILTER_NEAREST);
		EClientcheck = new Image("res/ccheck.png", false, Image.FILTER_NEAREST);
		EServercheck = new Image("res/ccheck.png", false, Image.FILTER_NEAREST);
		
		ENPasscheck = new Image("res/notcheck.png", false, Image.FILTER_NEAREST);
		ENClientcheck = new Image("res/notcheck.png", false, Image.FILTER_NEAREST);
		ENServercheck = new Image("res/notcheck.png", false, Image.FILTER_NEAREST);
	}
	
	public static void update(GameContainer container, StateBasedGame menu) {
		MosX = container.getInput().getMouseX();
		MosY = container.getInput().getMouseY();
		Click = container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON);
		
		password = PasswordBox.passField.getText();
		
		if(password.toCharArray().length != 4) { PassReady = false; }
		if(PasswordBox.CipField.getText().equals("")) { ClientReady = false; }
		if(PasswordBox.SipField.getText().equals("")) { ServerReady = false; }
		
		//PASSWORD
		if(MosX > 130 && MosX < 155 && MosY > 110 && MosY < 140 && Click && !PassReady && password.toCharArray().length == 4) { PassReady = true; Main.sendRequest = true; }
		else if(MosX > 130 && MosX < 155 && MosY > 110 && MosY < 140 && Click && PassReady) { PassReady = false; Main.sendRequest = true; }
		//CLIENT
		if(MosX > 480 && MosX < 510 && MosY > 130 && MosY < 155 && Click && !ClientReady && !PasswordBox.CipField.getText().equals("")) { if(ServerReady) { ClientReady = true; } if(!ServerReady) { ServerFirst = true; } Main.sendRequest = true; }
		else if(MosX > 480 && MosX < 510 && MosY > 130 && MosY < 155 && Click && ClientReady) { ClientReady = false; Main.sendRequest = true; }
		//SERVER
		if(MosX > 485 && MosX < 510 && MosY > 70 && MosY < 95 && Click && !ServerReady && !PasswordBox.SipField.getText().equals("")) { ServerReady = true; Main.sendRequest = true; }
		else if(MosX > 485 && MosX < 510 && MosY > 70 && MosY < 95 && Click && ServerReady) { ServerReady = false; Main.sendRequest = true; }
	
		if(ClientReady && CTimer < 10) { CTimer += 1; if(CTimer == 2) Client.Start(); }
		if(ServerReady && STimer < 10) { STimer += 1; if(STimer == 2) Server.Start(); }
 		
		if(ClientReady && ServerReady && PassReady && EClientReady && EServerReady && EPassReady) { AllReady = true; }
		else AllReady = false;
		
	}
	
	public static void render(GameContainer container, StateBasedGame menu, Graphics g) throws SlickException {
		if(PassReady)Passcheck.draw(PasswordState.disXPos - 85, 57);
		else NPasscheck.draw(PasswordState.disXPos - 85, 57);
		if(ClientReady)Clientcheck.draw(PasswordState.disXPos + 87, 65);
		else NClientcheck.draw(PasswordState.disXPos + 90, 65);
		if(ServerReady)Servercheck.draw(PasswordState.disXPos + 90, 35);
		else NServercheck.draw(PasswordState.disXPos + 90, 35);
		
		if(EPassReady) EPasscheck.draw(PasswordState.disXPos - 85, 177);
		else ENPasscheck.draw(PasswordState.disXPos - 85, 177);
		if(EClientReady) EClientcheck.draw(PasswordState.disXPos + 87, 205);
		else ENClientcheck.draw(PasswordState.disXPos + 90, 205);
		if(EServerReady) EServercheck.draw(PasswordState.disXPos + 90, 175);
		else ENServercheck.draw(PasswordState.disXPos + 90, 175);
	}
}
