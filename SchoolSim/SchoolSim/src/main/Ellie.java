package main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.*;

import States.HallwayState;

public class Ellie {

	public static int dir = 0;

	public static int Delay = 8;
	public static int TickNum = 0;
	public static int CurrAnimNum = 0;
	public static boolean Loop = true;

	public static int lastAnim = 0;
	public static int lastPlayed = 0;

	public static Image CurrImage = null;
	public static Image EllieSprite = null;
	public static Image EllieSit = null;
	public static Image EllieLocker = null;
	public static Image ElliePunch = null;

	public static int x = 0;

	public static boolean right = false;

	public static boolean Hiding = false;

	public static int lockerStart = 0;
	public static int tileStart = 0;

	public static int blinkLength = 20;
	public static boolean headDown = false;

	public static boolean Wake = false;

	public static int[] Walk = { 1, 2 };
	public static int[] Stand = { 0 };
	public static int[] Sit = { 0 };
	public static int[] Sleep = { 1, 0, 1, 0, 2, 3 };
	public static int[] PunchRun = { 1, 2 };
	public static int[] PunchIdle = { 0 };

	public static int sleepTime = 0;
	public static boolean playingSit = false;

	public static void init(GameContainer arg0) throws SlickException {
		CurrImage = new Image("Resources/EllieWalk.png", false, Image.FILTER_NEAREST);
		EllieSit = new Image("Resources/EllieSit.png", false, Image.FILTER_NEAREST);
		ElliePunch = new Image("Resources/Punch.png", false, Image.FILTER_NEAREST);
		EllieSprite = new Image("Resources/EllieWalk.png", false, Image.FILTER_NEAREST);
		EllieLocker = new Image("Resources/LockerWithEllie.png", false, Image.FILTER_NEAREST);
	}

	public static void playAnim(int AnimNum, StateBasedGame arg1) {

		int[] CurrAnimList = null;

		if (AnimNum == 0) {
			CurrImage = EllieSprite;
			CurrAnimList = Stand;
			Loop = true;
			Delay = 8;
		}
		if (AnimNum == 1 || AnimNum == -1) {
			CurrImage = EllieSprite;
			dir = AnimNum;
			CurrAnimList = Walk;
			Loop = true;
			Delay = 8;
		}
		if (AnimNum == 2) {
			CurrImage = EllieSprite;
			CurrAnimList = Sit;
			Loop = false;
			Delay = 50;
		}
		if (AnimNum == 5) {
			CurrImage = EllieSprite;
			CurrAnimList = Sleep;
			Loop = false;
			Delay = 50;
		}
		if (AnimNum == 3) {
			CurrImage = ElliePunch;
			CurrAnimList = PunchIdle;
			Loop = false;
			Delay = 2;
		}
		if (AnimNum == 4) {
			dir = 1;
			CurrImage = ElliePunch;
			CurrAnimList = PunchRun;
			Loop = true;
			Delay = 8;
		}
		if (AnimNum == -4) {
			dir = -1;
			CurrImage = ElliePunch;
			CurrAnimList = PunchRun;
			Loop = true;
			Delay = 8;
		}

		TickNum++;

		if (AnimNum == lastAnim) {
			if (TickNum >= Delay) {

				if (AnimNum == 5 && lastPlayed >= CurrAnimList.length - 1) {
					arg1.enterState(2, new FadeOutTransition(), new FadeInTransition());
				}

				if (lastPlayed + 1 < CurrAnimList.length)
					CurrAnimNum = lastPlayed + 1;
				else {
					if (Loop == true)
						CurrAnimNum = 0;
				}

				lastPlayed = CurrAnimNum;
				TickNum = 0;
			}
		} else {
			CurrAnimNum = 0;
		}

		try {
			int multi = 0;
			if (playingSit)
				multi = 15;
			else
				multi = 11;

			x = CurrAnimList[CurrAnimNum] * multi;
		} catch (Exception e) {
		}

		lastAnim = AnimNum;

	}

	public static void hide() {
		if (HallwayState.mapNum[((HallwayState.x / 36) * -1) + 1] == 2) {
			if (Hiding)
				Hiding = false;
			else
				Hiding = true;
		}
	}

	public static void render(GameContainer arg0, StateBasedGame arg1, Graphics g, int EllieY) throws SlickException {

		if (dir == -1)
			right = true;
		else
			right = false;

		if (!playingSit) {
			if (!Hiding) {
				CurrImage.getSubImage(x, 0, 11, 28).getFlippedCopy(right, false).draw(Main.EllieX, EllieY);
			} else {
				lockerStart = ((-HallwayState.x % 36) / 12) * 12;
				tileStart = 36 * ((-HallwayState.x / 36) + 1);
				EllieLocker.draw(HallwayState.x + tileStart + lockerStart, 2);
			}
		} else {
			EllieSit.getSubImage(x, 0, 15, 28).getFlippedCopy(right, false).draw(Main.EllieX, EllieY - 3);
		}
	}
}
