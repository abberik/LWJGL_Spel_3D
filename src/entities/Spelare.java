package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

import modeller.TextureradModell;
import renderingsMotor.DisplayHanterare;
import terrang.Terrang;

public class Spelare extends Entity{

	private static final float GA_HASTIGHET = 120;
	private static final float GIR_HASTIGHET = 200;
	private static float GRAVITATION = -50;
	private static final float HOPP_EFFEKT = 30;
	
	private static float TERRAIN = 0;
	
	private float nuvarandeHastighet = 0;
	private float nuvarandeGir = 0;
	private float nuvarandeYHastighet;
	private boolean arILuften;
	
	public Spelare(TextureradModell model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
		super(model, position, rotX, rotY, rotZ, scale);
	}
	
	public void forflytta(Terrang terrang){
			
		kontrolleraInput();
		
		super.okaRotation(0,nuvarandeGir * DisplayHanterare.getFrameTimeSeconds(), 0);
		float avstand = nuvarandeHastighet * DisplayHanterare.getFrameTimeSeconds();

		float dX = avstand * ((float)(Math.sin(Math.toRadians( super.getRotY() - 90 ))));
		float dZ = avstand * ((float)(Math.cos(Math.toRadians( super.getRotY() - 90 ))));
		
		nuvarandeYHastighet += (GRAVITATION * DisplayHanterare.getFrameTimeSeconds() );
		super.okaPosition(dX, nuvarandeYHastighet * DisplayHanterare.getFrameTimeSeconds(), dZ);
		float terrangHojd =  terrang.getHojdAvTerrang(super.getPosition().getX(),super.getPosition().getZ() );
		if(super.getPosition().y<terrangHojd){
			nuvarandeYHastighet = 0;
			super.getPosition().y = terrangHojd;
			arILuften = false;
		}
		
		
	}
	
	public void jump(){
		this.nuvarandeYHastighet = HOPP_EFFEKT;
	}
	
	private void kontrolleraInput(){
		
		if(Keyboard.isKeyDown(Keyboard.KEY_W)){
			this.nuvarandeHastighet = GA_HASTIGHET;
		}else if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			this.nuvarandeHastighet = -GA_HASTIGHET;
		}else {
			this.nuvarandeHastighet = 0;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			this.nuvarandeGir = - GIR_HASTIGHET;
		}else if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			this.nuvarandeGir = GIR_HASTIGHET;
		}else{
			this.nuvarandeGir = 0;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
			if(!arILuften){
				jump();
				arILuften = true;				
			}
		}
		
		
	}
	
	
}
