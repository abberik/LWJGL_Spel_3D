package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Kamera {

	private Vector3f position = new Vector3f(0, 0, 0);
	private float pitch;
	private float yaw; // Den som styr vart kameran siktar.
	private float roll;
	
	private float avstandFranSpelare = 50;
	private float vinkelfranSpelare = 0;
	
	private Spelare spelare;
	
	public Kamera(Spelare spelare) {
		this.spelare = spelare;
	}

	public void move() {
		raknaUtZoom();
		raknaUtLutningsVinkel();
		raknaUtLutningFranSpelare();
		
		float horisontelltAvstand = horisontelltAvstandFranSpelare();
		float vertikaltAvstand = vertikaltAvstandFranSpelare();
		
		raknaUtKameraPosition(horisontelltAvstand, vertikaltAvstand);
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public float getPitch() {
		return pitch;
	}

	public void setPitch(float pitch) {
		this.pitch = pitch;
	}

	public float getYaw() {
		return yaw;
	}		


	public void setYaw(float yaw) {
		this.yaw = yaw;
	}

	public float getRoll() {
		return roll;
	}

	public void setRoll(float roll) {
		this.roll = roll;
	}
	
	private void raknaUtZoom(){
		float zoomNiva = Mouse.getDWheel() * 0.1f;
		avstandFranSpelare += zoomNiva; //kan vändas för att vända scrollhjulets paverkan
	}
	
	private void raknaUtLutningsVinkel(){
		if(Mouse.isButtonDown(2)){
			float lutningsVinkelForandring = Mouse.getDY() * 0.1f;
			pitch -= lutningsVinkelForandring;
		}
		pitch = (float)Math.max((double)pitch, 0.1);
	}
	
	private void raknaUtLutningFranSpelare(){
		if(Mouse.isButtonDown(2)){
			float vinkelForandring = Mouse.getDX() * 0.3f;
			vinkelfranSpelare += vinkelForandring;
		}
	}
	
	private float horisontelltAvstandFranSpelare(){		return (avstandFranSpelare * ((float)Math.cos(Math.toRadians((double)pitch))));		}

	private float vertikaltAvstandFranSpelare(){		return (avstandFranSpelare * ((float)Math.sin(Math.toRadians((double)pitch))));		}
	
	private void raknaUtKameraPosition(float horisontelltAvstand,float vertikaltAvstand){
				
		float offset_langs_X = (float)Math.sin(Math.toRadians((double)(spelare.getRotY() + vinkelfranSpelare))) * horisontelltAvstand; 
		float offset_langs_Z = (float)Math.cos(Math.toRadians((double)(spelare.getRotY() + vinkelfranSpelare))) * horisontelltAvstand;
		
		this.setPosition(new Vector3f(     spelare.getPosition().x - offset_langs_X     ,spelare.getPosition().y + vertikaltAvstand,     spelare.getPosition().z - offset_langs_Z      ));
		
		
		this.setYaw(180 - spelare.getRotY() - vinkelfranSpelare);
	}
	
	
}
