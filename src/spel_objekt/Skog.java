package spel_objekt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.util.ArrayList;

import org.lwjgl.util.vector.Vector3f;

import renderingsMotor.Laddare;
import renderingsMotor.MastarRenderare;

public class Skog {

	private ArrayList<Trad> skog;
	private String filnamn;
	
	public Skog(Laddare laddare,String filnamn) {
		
		this.filnamn = filnamn;
		
		if(Files.exists((new File(filnamn).toPath()), LinkOption.NOFOLLOW_LINKS)){
			
			//om filen existerar läs filen
			
			lasSkogFil(laddare);
			
		}	// om filen inte existerar, gör ingenting
		
		skog = new ArrayList<Trad>();
	}
	
	public void skapaTrad(Laddare laddare,Vector3f position){
		skog.add(new Trad(laddare,position));
	}
	
	public void lasSkogFil(Laddare laddare){
				
		try {
			
			skog.clear();
			
			FileReader fl = null;
			ArrayList<String> fil = new ArrayList<String>();
			
			fl = new FileReader(new File("res/" + filnamn + ".sf"));
			
			BufferedReader bl = new BufferedReader(fl);
			
			String buffer = "";
			
			while((buffer = bl.readLine())!= null){
				fil.add(buffer);
			}
			
			bl.close();
			
			for(String rad : fil){
				
				if(rad.charAt(0) == 't'){

//					FORMATERING = t,[x],[y],[z] (om det är ett träd)
					
					String[] radData = rad.split(","); 
					
					float x = ((float)(Double.parseDouble(radData[1])));
					float y = ((float)(Double.parseDouble(radData[2])));
					float z = ((float)(Double.parseDouble(radData[3])));
					
					
					Trad trad = new Trad(laddare, new Vector3f(x,y,z));
					
					skog.add(trad);
				}
				
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void skrivSkogFil(Laddare laddare){
		
		try {
			
			FileWriter fs = null;
			fs = new FileWriter("res/"+new File(this.filnamn)+".sf");
		
			BufferedWriter bs = new BufferedWriter(fs);
			
			for(Trad trad : skog){
				
				bs.write("t,"+trad.getPosition().getX()+","+trad.getPosition().getY()+","+trad.getPosition().getZ()+"\n");
				
			}
			
			bs.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	public void processera(	MastarRenderare mr){
		for(Trad trad : skog){		trad.rendera(mr);	}	
	}

	public ArrayList<Trad> getSkog() {
		return skog;
	}

	public void setSkog(ArrayList<Trad> skog) {
		this.skog = skog;
	}

	public String getFilnamn() {
		return filnamn;
	}

	public void setFilnamn(String filnamn) {
		this.filnamn = filnamn;
	}
	
}
