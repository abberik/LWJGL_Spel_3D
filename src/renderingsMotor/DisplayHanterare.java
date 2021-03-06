package renderingsMotor;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

public class DisplayHanterare {

	private static final int WIDTH = 1280, HEIGHT = 720, FRAMES = 60;
	private static final String TITEL = "Abberix 3D spel.";
	private static long sistaFrameTid;
	private static float delta;
	
	public static void skapaDisplay() {

		ContextAttribs attributes = new ContextAttribs(3, 2).withForwardCompatible(true).withProfileCore(true);

		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.create(new PixelFormat(), attributes);
			Display.setTitle(TITEL);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}

		GL11.glViewport(0, 0, WIDTH, HEIGHT);
		sistaFrameTid = getNuvarandeTid();
	}

	public static void uppdateraDisplay() {
		Display.sync(FRAMES);
		Display.update();
		
		long nuvarandeFrameTid = getNuvarandeTid();
		delta = (nuvarandeFrameTid - sistaFrameTid)/1000f;
		sistaFrameTid = nuvarandeFrameTid;
	}

	public static float getFrameTimeSeconds(){
		return delta;
	}
	
	public static void stangDisplay() {

		Display.destroy();

	}
	
	private static long getNuvarandeTid(){
		return ((Sys.getTime()*1000) / Sys.getTimerResolution());
	}

}
