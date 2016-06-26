package texturer;

public class TerrangTexturPaket {

	private TerrangTextur bakgrund;
	private TerrangTextur rTextur;
	private TerrangTextur gTextur;
	private TerrangTextur bTextur;
	
	public TerrangTexturPaket(TerrangTextur bakgrund, TerrangTextur rTextur, TerrangTextur gTextur,TerrangTextur bTextur) {
		this.bakgrund = bakgrund;
		this.rTextur = rTextur;
		this.gTextur = gTextur;
		this.bTextur = bTextur;
	}
	
	public TerrangTextur getBakgrund() {
		return bakgrund;
	}
	public void setBakgrund(TerrangTextur bakgrund) {
		this.bakgrund = bakgrund;
	}
	public TerrangTextur getrTextur() {
		return rTextur;
	}
	public void setrTextur(TerrangTextur rTextur) {
		this.rTextur = rTextur;
	}
	public TerrangTextur getgTextur() {
		return gTextur;
	}
	public void setgTextur(TerrangTextur gTextur) {
		this.gTextur = gTextur;
	}
	public TerrangTextur getbTextur() {
		return bTextur;
	}
	public void setbTextur(TerrangTextur bTextur) {
		this.bTextur = bTextur;
	}
	
	
	
}
