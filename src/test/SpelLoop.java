package test;

import org.lwjgl.opengl.Display;

import modeller.RaaModel;
import renderingsMotor.DisplayHanterare;
import renderingsMotor.Laddare;
import renderingsMotor.Renderare;
import shaders.StatiskShader;
import texturer.ModelTextur;
import modeller.TextureradModell;

public class SpelLoop {

	public static void main(String[] args) {

		DisplayHanterare.skapaDisplay();
		
		Laddare laddare = new Laddare();
		Renderare renderare = new Renderare();
		StatiskShader shader = new StatiskShader();
		
        float[] vertices = {            
                -0.5f,0.5f,0,   //V0
                -0.5f,-0.5f,0,  //V1
                0.5f,-0.5f,0,   //V2
                0.5f,0.5f,0     //V3
        };
         
        float[] texturKoordinater = {
                 
                0,0,
                0,1,
                1,1,
                1,0
                 
        };
         
        int[] indices = {
                0,1,3,  //Top left triangle (V0,V1,V3)
                3,1,2   //Bottom right triangle (V3,V1,V2)
        };
		
		
		RaaModel model = laddare.laddaTilVAO(vertices,texturKoordinater,indices);
		
		TextureradModell texturerad_modell = new TextureradModell(model,new ModelTextur(laddare.laddaTextur("mossigsten")));
		
		while(!Display.isCloseRequested()){
			
			
			
			renderare.forbered();
			shader.starta();
			//logik
			renderare.renderera(texturerad_modell);
			shader.stoppa();
	
			DisplayHanterare.uppdateraDisplay();
		}
		shader.stada();
		laddare.stada();
		DisplayHanterare.stangDisplay();
		
	}
	
}
