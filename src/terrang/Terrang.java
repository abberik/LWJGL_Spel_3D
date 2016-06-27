package terrang;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import modeller.RaaModel;
import renderingsMotor.Laddare;
import texturer.TerrangTextur;
import texturer.TerrangTexturPaket;
import verktygslada.Matematik;

public class Terrang {

	private static final float SIZE = 800;

	private static final float MAX_HOJD = 10;
	private static final float MAX_FARG = (float)Math.pow(256, 3);
	
	
	private float x;
	private float z;
	private RaaModel model;
	private TerrangTexturPaket textur_paket;
	private TerrangTextur blend_karta;
	
	private float[][] hojder;
		
	public Terrang(float gridX, float gridZ,Laddare laddare ,TerrangTexturPaket textur_paket,TerrangTextur blend_karta,String hojdKarta) {
		
		this.x = gridX * SIZE;
		this.z = gridZ * SIZE;
		this.model = generateTerrain(laddare,hojdKarta);
		this.blend_karta = blend_karta;
		this.textur_paket = textur_paket;
		
	}
	
	public float getHojdAvTerrang(float varldX, float varldZ){
		
		float terrangX = varldX - this.x;
		float terrangZ = varldZ - this.z;
		
		float rutnatsRutaStorlek = SIZE / ((float)hojder.length - 1);
		
		int gridX = (int) Math.floor(terrangX / rutnatsRutaStorlek);
		int gridZ = (int) Math.floor(terrangZ / rutnatsRutaStorlek);
		
		if(gridX >= hojder.length-1 || gridZ >= hojder.length || gridX < 0 || gridZ < 0){
			return 0;
		}
		
		float xKoordinat = (terrangX % rutnatsRutaStorlek)/rutnatsRutaStorlek;
		float zKoordinat = (terrangZ % rutnatsRutaStorlek)/rutnatsRutaStorlek;
		
		if (xKoordinat <= (1-zKoordinat)) {
			return Matematik.barysentriskaKoordinater(new Vector3f(0, hojder[gridX][gridZ], 0), new Vector3f(1,hojder[gridX + 1][gridZ], 0), new Vector3f(0,hojder[gridX][gridZ + 1], 1), new Vector2f(xKoordinat, zKoordinat));
		} else {
			return Matematik.barysentriskaKoordinater(new Vector3f(1, hojder[gridX + 1][gridZ], 0), new Vector3f(1,	hojder[gridX + 1][gridZ + 1], 1), new Vector3f(0,hojder[gridX][gridZ + 1], 1), new Vector2f(xKoordinat, zKoordinat));
		}
		
	}
	
	private RaaModel generateTerrain(Laddare loader,String hojdKarta){
		
		BufferedImage bild = null;
		

		
		try {
			bild = ImageIO.read(new File("res/" + hojdKarta + ".png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	
		int VERTEX_COUNT = bild.getHeight();
		hojder = new float[VERTEX_COUNT][VERTEX_COUNT];
		
		try {
			bild = ImageIO.read(new File("res/" + hojdKarta + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int count = VERTEX_COUNT * VERTEX_COUNT;
		float[] vertices = new float[count * 3];
		float[] normals = new float[count * 3];
		float[] textureCoords = new float[count*2];
		int[] indices = new int[6*(VERTEX_COUNT-1)*(VERTEX_COUNT-1)];
		int vertexPointer = 0;
		for(int i=0;i<VERTEX_COUNT;i++){
			for(int j=0;j<VERTEX_COUNT;j++){
				vertices[vertexPointer*3] = (float)j/((float)VERTEX_COUNT - 1) * SIZE;
				float hojd = getHojd(j, i, bild);
				hojder[j][i] = hojd;
				vertices[vertexPointer*3+1] = getHojd(j, i, bild);
				vertices[vertexPointer*3+2] = (float)i/((float)VERTEX_COUNT - 1) * SIZE;
				Vector3f normal = raknaUtNormal(j, i, bild);
				normals[vertexPointer*3] = normal.x;
				normals[vertexPointer*3+1] = normal.y;
				normals[vertexPointer*3+2] = normal.z; 
				textureCoords[vertexPointer*2] = (float)j/((float)VERTEX_COUNT - 1);
				textureCoords[vertexPointer*2+1] = (float)i/((float)VERTEX_COUNT - 1);
				vertexPointer++;
			}
		}
		int pointer = 0;
		for(int gz=0;gz<VERTEX_COUNT-1;gz++){
			for(int gx=0;gx<VERTEX_COUNT-1;gx++){
				int topLeft = (gz*VERTEX_COUNT)+gx;
				int topRight = topLeft + 1;
				int bottomLeft = ((gz+1)*VERTEX_COUNT)+gx;
				int bottomRight = bottomLeft + 1;
				indices[pointer++] = topLeft;
				indices[pointer++] = bottomLeft;
				indices[pointer++] = topRight;
				indices[pointer++] = topRight;
				indices[pointer++] = bottomLeft;
				indices[pointer++] = bottomRight;
			}
		}
		return loader.laddaTilVAO(vertices, textureCoords, normals, indices);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}

	public RaaModel getModel() {
		return model;
	}

	public void setModel(RaaModel model) {
		this.model = model;
	}

	public TerrangTexturPaket getTextur_paket() {
		return textur_paket;
	}

	public void setTextur_paket(TerrangTexturPaket textur_paket) {
		this.textur_paket = textur_paket;
	}

	public TerrangTextur getBlend_karta() {
		return blend_karta;
	}

	public void setBlend_karta(TerrangTextur blend_karta) {
		this.blend_karta = blend_karta;
	}

	public static float getSize() {
		return SIZE;
	}

	private float getHojd(int x, int z, BufferedImage bild){
		if(x < 0 || x >= bild.getHeight() || z < 0 || z >= bild.getHeight()) return 0;
		float hojd = bild.getRGB(x,z);
		hojd += MAX_FARG / 2f;
		hojd /= MAX_FARG / 2f;
		hojd *= MAX_HOJD;
		return hojd;
		
	}
	
	private Vector3f raknaUtNormal(int x, int z, BufferedImage bild){
		float heightL = getHojd(x-1, z, bild);
		float heightR = getHojd(x+1, z, bild);
		float heightD = getHojd(x, z-1, bild);
		float heightU = getHojd(x+1, z+1, bild);
		Vector3f normal = new Vector3f(heightL - heightR, 2f, heightD - heightU);
		normal.normalise();
		return normal;
	}
	
	
	
}
