package test;

import org.lwjgl.opengl.Display;

import renderingsMotor.DisplayHanterare;

public class SpelLoop {

	public static void main(String[] args) {

		DisplayHanterare.skapaDisplay();
		
		while(!Display.isCloseRequested()){
			
			
			//logik
			
			
			//rendering
			DisplayHanterare.uppdateraDisplay();
		}
		
		DisplayHanterare.stangDisplay();
		
	}
	
}
