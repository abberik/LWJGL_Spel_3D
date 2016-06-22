package modeller;

import texturer.ModelTextur;

public class TextureradModell {

	private RaaModel raaModel;
	private ModelTextur textur;
	
	public TextureradModell(RaaModel raaModel, ModelTextur textur) {
		this.raaModel = raaModel;
		this.textur = textur;
	}
	
	public RaaModel getRaaModel() {
		return raaModel;
	}
	
	public void setRaaModel(RaaModel raaModel) {
		this.raaModel = raaModel;
	}
	
	public ModelTextur getTextur() {
		return textur;
	}
	
	public void setTextur(ModelTextur textur) {
		this.textur = textur;
	}
	
	
	
}
