package labo1;
import java.util.ArrayList;
import java.util.Iterator;

public class Aktorea {
	//atributuak
	private String izen;
	private String abizena;
	private ArrayList<Pelikula> listaPelikula = new ArrayList<Pelikula>();

	//metodoak
	public Aktorea(String pIzena, String pAbizena){
        this.izen=pIzena;
        this.abizena=pAbizena;
    }
	
	public String getIzena() {
		return this.izen;
	}
	
	public String getAbizena() {
		return this.abizena;
	}
	
	public void setIzena(String izen) {
		this.izen = izen;
	}
	
	public void setAbizena(String abizena) {
		this.abizena = abizena;
	}
	
	public ArrayList<Pelikula> getPelikulaLista() {
		return this.listaPelikula;
	}
	
	public Iterator<Pelikula> getIteradorea() {
		return this.listaPelikula.iterator();
	}
	
	public void gehituPelikula(Pelikula peli) {
		this.listaPelikula.add(peli);
	}
    
    public boolean izenBerdinaDu(String pIzen, String pAbizen) {
		if (this.izen.equals(pIzen)&&(this.abizena.equals(pAbizen))) {
			return true;
		}
		else {
			return false;
		}
	}
    
    public void pantailaratuIzenAbizenak() {
		System.out.println(this.izen + this.abizena);
	}
	
	public void pantailaratuPelikulak() {
		Iterator<Pelikula> i = getIteradorea();
		Pelikula peli = null;
		while (i.hasNext()) {
			peli = i.next();
			peli.pantailaratuIzena();
		}
	}
}
