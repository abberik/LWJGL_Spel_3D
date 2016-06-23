package test;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Entity;
import entities.Kamera;
import modeller.RaaModel;
import modeller.TextureradModell;
import renderingsMotor.DisplayHanterare;
import renderingsMotor.Laddare;
import renderingsMotor.Renderare;
import shaders.StatiskShader;
import texturer.ModelTextur;

public class SpelLoop {

	public static void main(String[] args) {

		DisplayHanterare.skapaDisplay();
		
		Laddare laddare = new Laddare();
		StatiskShader shader = new StatiskShader();
		Renderare renderare = new Renderare(shader);
		
		float[] vertices = {			
				-0.5f,0.5f,-0.5f,	
				-0.5f,-0.5f,-0.5f,	
				0.5f,-0.5f,-0.5f,	
				0.5f,0.5f,-0.5f,		
				
				-0.5f,0.5f,0.5f,	
				-0.5f,-0.5f,0.5f,	
				0.5f,-0.5f,0.5f,	
				0.5f,0.5f,0.5f,
				
				0.5f,0.5f,-0.5f,	
				0.5f,-0.5f,-0.5f,	
				0.5f,-0.5f,0.5f,	
				0.5f,0.5f,0.5f,
				
				-0.5f,0.5f,-0.5f,	
				-0.5f,-0.5f,-0.5f,	
				-0.5f,-0.5f,0.5f,	
				-0.5f,0.5f,0.5f,
				
				-0.5f,0.5f,0.5f,
				-0.5f,0.5f,-0.5f,
				0.5f,0.5f,-0.5f,
				0.5f,0.5f,0.5f,
				
				-0.5f,-0.5f,0.5f,
				-0.5f,-0.5f,-0.5f,
				0.5f,-0.5f,-0.5f,
				0.5f,-0.5f,0.5f
				
		};
		
		float[] texturKoordinater = {
				
				0,0,
				0,1,
				1,1,
				1,0,			
				0,0,
				0,1,
				1,1,
				1,0,			
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0

				
		};
		
		int[] indices = {
				0,1,3,	
				3,1,2,	
				4,5,7,
				7,5,6,
				8,9,11,
				11,9,10,
				12,13,15,
				15,13,14,	
				16,17,19,
				19,17,18,
				20,21,23,
				23,21,22

		};
		

         

         

		
		
		RaaModel model = laddare.laddaTilVAO(vertices,texturKoordinater,indices);
		ModelTextur modelTextur = new ModelTextur(laddare.laddaTextur("stenblock"));
		TextureradModell texturerad_modell = new TextureradModell(model,modelTextur);
		
		Entity entity = new Entity(texturerad_modell,new Vector3f(0,0,-1),0,0,0,1);
		
		Kamera kamera = new Kamera();
		
		while(!Display.isCloseRequested()){
			
			entity.okaPosition(0, 0, 0);
			entity.okaRotation(1, 1, 1);
			kamera.move();
			renderare.forbered();
			shader.starta();
			shader.laddaVyMatris(kamera);
			renderare.renderera(entity,shader);
			shader.stoppa();
	
			DisplayHanterare.uppdateraDisplay();
		}
		shader.stada();
		laddare.stada();
		DisplayHanterare.stangDisplay();
		
	}
	
}
