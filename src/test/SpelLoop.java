package test;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Entity;
import entities.Kamera;
import entities.Ljus;
import modeller.RaaModel;
import modeller.TextureradModell;
import renderingsMotor.DisplayHanterare;
import renderingsMotor.Laddare;
import renderingsMotor.MastarRenderare;
import renderingsMotor.OBJLaddare;
import spel_objekt.Trad;
import terrang.Terrang;
import texturer.ModelTextur;

public class SpelLoop {

	public static void main(String[] args) {

		
		
		System.out.println(2048.0 / 64.0);
		
		DisplayHanterare.skapaDisplay();

		Laddare laddare = new Laddare();
		RaaModel model = OBJLaddare.laddaObjModel("dragon", laddare);
		ModelTextur modelTextur = new ModelTextur(laddare.laddaTextur("vit"));
		modelTextur.setReflektivitet(0.1f / 100f);
		modelTextur.setShinedamper(0.1f);
		
		
		Trad trad1 = new Trad(laddare, new Vector3f(0,0,0));
		Trad trad2 = new Trad(laddare, new Vector3f(10,0,10));
		Trad trad3 = new Trad(laddare, new Vector3f(20,0,20));
		Trad trad4 = new Trad(laddare, new Vector3f(30,0,30));
		Trad trad5 = new Trad(laddare, new Vector3f(40,0,40));
		
		Trad trad11 = new Trad(laddare, new Vector3f(30,0,0));
		Trad trad22 = new Trad(laddare, new Vector3f(40,0,10));
		Trad trad33 = new Trad(laddare, new Vector3f(50,0,20));
		Trad trad44 = new Trad(laddare, new Vector3f(60,0,30));
		Trad trad55 = new Trad(laddare, new Vector3f(70,0,40));
		
		
		TextureradModell texturerad_modell = new TextureradModell(model, modelTextur);

		Entity entity = new Entity(texturerad_modell, new Vector3f(1, 0, -15), 0, 120, 1, 1);
		Ljus ljus = new Ljus(new Vector3f(200, 200, 0), new Vector3f(1, 1, 1));
		
		Terrang terrang1 = new Terrang(0,0,laddare,new ModelTextur(laddare.laddaTextur("gras_battre")));
		Terrang terrang2 = new Terrang(0,-1,laddare,new ModelTextur(laddare.laddaTextur("gras_battre")));
		Terrang terrang3 = new Terrang(-1,0,laddare,new ModelTextur(laddare.laddaTextur("gras_battre")));
		Terrang terrang4 = new Terrang(-1,-1,laddare,new ModelTextur(laddare.laddaTextur("gras_battre")));
		
		
		Kamera kamera = new Kamera();

		MastarRenderare mastarRenderare = new MastarRenderare();

		Mouse.setGrabbed(true);
		
		while (!Display.isCloseRequested()) {

			if(Keyboard.isKeyDown(Keyboard.KEY_END)){
				Mouse.setGrabbed(false);
				System.exit(0);
			}
			
			if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
				
				Mouse.setGrabbed(!Mouse.isGrabbed());
				
			}
			
			kamera.move();
			
			mastarRenderare.processTerrang(terrang1);
			mastarRenderare.processTerrang(terrang2);
			mastarRenderare.processTerrang(terrang3);
			mastarRenderare.processTerrang(terrang4);
			
			trad1.rendera(mastarRenderare);
			trad2.rendera(mastarRenderare);
			trad3.rendera(mastarRenderare);
			trad4.rendera(mastarRenderare);
			trad5.rendera(mastarRenderare);
			
			trad11.rendera(mastarRenderare);
			trad22.rendera(mastarRenderare);
			trad33.rendera(mastarRenderare);
			trad44.rendera(mastarRenderare);
			trad55.rendera(mastarRenderare);
			
			mastarRenderare.processEntity(entity);
			mastarRenderare.render(ljus, kamera);

			DisplayHanterare.uppdateraDisplay();
		}
		Mouse.setGrabbed(true);
		mastarRenderare.stada();
		laddare.stada();
		DisplayHanterare.stangDisplay();

	}

}
