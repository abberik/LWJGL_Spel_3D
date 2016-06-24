package spel_objekt;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector3f;

import entities.Entity;
import modeller.RaaModel;
import modeller.TextureradModell;
import renderingsMotor.Laddare;
import renderingsMotor.MastarRenderare;
import renderingsMotor.OBJLaddare;
import texturer.ModelTextur;

public class Trad {

	private ArrayList<Entity> entities;
	private Vector3f position;
	//Klass som beskriver ett träd
	
	public Trad(Laddare laddare,Vector3f position) {				
		
		this.position = position;
		
		entities = new ArrayList<Entity>();

		
		//Ladda stam
		
		RaaModel tradStamModel = OBJLaddare.laddaObjModel("trad_stam",laddare);
		RaaModel tradKonModel = OBJLaddare.laddaObjModel("trad_kon",laddare);
		
		ModelTextur tradKonTextur = new ModelTextur(laddare.laddaTextur("trad_gren_1"));
		ModelTextur tradStamTextur = new ModelTextur(laddare.laddaTextur("trad_stam"));
		
		tradKonTextur.setReflektivitet(0.1f / 100f);
		tradKonTextur.setShinedamper(0.05f);
		
		tradStamTextur.setReflektivitet(0.1f / 100f);
		tradStamTextur.setShinedamper(0.05f);
		
		TextureradModell tradKonTM = new TextureradModell(tradKonModel,tradKonTextur);
		TextureradModell tradStamTM = new TextureradModell(tradStamModel,tradStamTextur);
		
		entities.add(new Entity(tradStamTM,position,0,0,1,1));
		entities.add( new Entity(tradKonTM,position,0,0,1,1));
		entities.add(new Entity(tradKonTM,new Vector3f(position.x,position.y + 3.3f,position.z),0,0,1,1));
		entities.add(new Entity(tradKonTM,new Vector3f(position.x,position.y + 7.5f, position.z),0,0,1,1));
		
		//Trad kod//
		
	}
	
	public void rendera(MastarRenderare mastarRenderare){
		for(Entity entity : entities) mastarRenderare.processEntity(entity);
	}
	
}
