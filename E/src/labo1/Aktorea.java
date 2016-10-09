package labo1;

import java.util.ArrayList;
import java.util.Iterator;
public class Aktorea {
	
	private String izenAbizena;
    private ArrayList<Pelikula> pLista;
 
    public Aktorea(String pIzenAbizena){
        this.izenAbizena=pIzenAbizena;
        this.pLista=new ArrayList<Pelikula>();
    }
    
	  public int compareTo(Aktorea akt) {
	        return this.izenAbizena.compareTo(akt.izenAbizena);
	    }
}
