package labo1;
import java.util.ArrayList;
import java.util.Iterator;

public class Pelikula {
	// atributuak
	private String izena;
	private int kostua;
	private ArrayList<Aktorea> listaAktore = new ArrayList<Aktorea>();
	
	// eraikitzailea
	public Pelikula(String pIzena) {
		this.izena = pIzena;
		this.kostua = 0;
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
	
	// metodoak
	public Iterator<Aktorea> getIteradorea() {
		return this.listaAktore.iterator();
	}
	
	public void pantailaratuIzena() {
		System.out.println(this.izena);
	}
	
	public void pelikulaBatekoAktoreakBueltatu() {
		
	}
}
