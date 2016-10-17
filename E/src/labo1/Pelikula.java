package labo1;
import java.util.ArrayList;
import java.util.Iterator;

public class Pelikula {
	// atributuak
	private String izena;
	private int kostua;
	private ArrayList<Aktorea> listaAktore; 
	
	// eraikitzailea
	public Pelikula(String pIzena) {
		this.izena = pIzena;
		this.kostua = 0;
		listaAktore= new ArrayList<Aktorea>();
	}
	
	// getters & setters
	public String getIzena() {
		return this.izena;
	}
		
	public int getKostua() {
		return this.kostua;
	}
		
	public void setIzena(String izena) {
		this.izena = izena;
	}
	
	public ArrayList<Aktorea> getAktoreLista() {
		return this.listaAktore;
	}
	
	// metodoak
	public Iterator<Aktorea> getIteradorea() {
		return this.listaAktore.iterator();
	}
	
	public void pantailaratuIzena() {
		System.out.println(this.izena);
	}
	
	public void gehituAktorea(Aktorea akt) {
			this.listaAktore.add(akt);
	}
	
	public void AktoreakPantailaratu(){
    	if(listaAktore==null){
    		System.out.println("Ez dago aktorerik zerrendan");
    	}
    	else{
    		Aktorea akt=null;
    		Iterator<Aktorea>i=getIteradorea();
    		while(i.hasNext()){
    			akt= i.next();
    			System.out.println(akt);
    		}
    	}
    	}
    	public boolean izenBerdinaDu (String izena2){
    		if(this.izena.equals(izena2)){
    			return true;
    		}
    		else{
    			return false;
    		}
    	
    }
    	public void DiruaGehitu(int kantitatea){
    		this.kostua=kantitatea+ this.kostua;
    		}


	
	

}
