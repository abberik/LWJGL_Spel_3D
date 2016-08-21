package test;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Entity;
import entities.Kamera;
import entities.Ljus;
import entities.Spelare;
import modeller.RaaModel;
import modeller.TextureradModell;
import renderingsMotor.DisplayHanterare;
import renderingsMotor.Laddare;
import renderingsMotor.MastarRenderare;
import renderingsMotor.OBJLaddare;
import terrang.Terrang;
import texturer.ModelTextur;
import texturer.TerrangTextur;
import texturer.TerrangTexturPaket;

//slick http://slick.ninjacave.com/slick-util.jar
//lwjgl https://sourceforge.net/projects/java-game-lib/files/Official%20Releases/LWJGL%202.9.1/lwjgl-2.9.1.zip/download

public class SpelLoop {

	public static void main(String[] args) {

		
		
		System.out.println(2048.0 / 64.0);
		
		DisplayHanterare.skapaDisplay();

		Laddare laddare = new Laddare();
		RaaModel model = OBJLaddare.laddaObjModel("dragon", laddare);
		ModelTextur modelTextur = new ModelTextur(laddare.laddaTextur("rod"));
		modelTextur.setReflektivitet(0.1f / 100f);
		modelTextur.setShinedamper(0.1f);
		
		TerrangTextur terrangBakgrund = new TerrangTextur(laddare.laddaTextur("orange1"));
		TerrangTextur rTerrangTextur = new TerrangTextur(laddare.laddaTextur("jord1"));
		TerrangTextur gTerrangTextur = new TerrangTextur(laddare.laddaTextur("gras1"));
		TerrangTextur bTerrangTextur = new TerrangTextur(laddare.laddaTextur("sten1"));
		
		TerrangTexturPaket terrangPaket = new TerrangTexturPaket(terrangBakgrund, rTerrangTextur, gTerrangTextur, bTerrangTextur);
		TerrangTextur blend_textur= new TerrangTextur(laddare.laddaTextur("textur_karta"));
		
		Terrang terrang1 = new Terrang(0, 0, laddare, terrangPaket, blend_textur,"hojd_karta");
		
		TextureradModell texturerad_modell = new TextureradModell(model, modelTextur);
		
		Spelare spelare = new Spelare(texturerad_modell, new Vector3f(0,0,0), 0, 0, 0, 1);	

		Entity entity = new Entity(texturerad_modell, new Vector3f(1, 0, -15), 0, 120, 1, 1);
		Ljus ljus = new Ljus(new Vector3f(200, 200, 0), new Vector3f(1, 1, 1));
		
		Kamera kamera = new Kamera(spelare);
		kamera.setPosition(new Vector3f(10,10,0));
		MastarRenderare mastarRenderare = new MastarRenderare();

		Mouse.setGrabbed(true);
		
		boolean wroteforest = false;
		
		while (!Display.isCloseRequested()) {

			if(Keyboard.isKeyDown(Keyboard.KEY_END)){
				Mouse.setGrabbed(false);
				System.exit(0);
			}
			
			if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
				
				Mouse.setGrabbed(!Mouse.isGrabbed());
				
			}
			
			spelare.forflytta(terrang1);
			kamera.move();
			mastarRenderare.processEntity(spelare);
			
			mastarRenderare.processTerrang(terrang1);
			
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
