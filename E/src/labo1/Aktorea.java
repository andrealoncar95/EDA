package labo1;

import java.util.ArrayList;
import java.util.Iterator;
public class Aktorea {
	
	private String izen;
	private String abizena;

 
    public Aktorea(String pIzena, String pAbizena){
        this.izen=pIzena;
        this.abizena=pAbizena;
    }
    
	  public int compareTo(Aktorea akt) {
	        return this.izenAbizena.compareTo(akt.izenAbizena);
	    }
}
