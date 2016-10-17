package labo1;
import java.util.ArrayList;
import java.util.Iterator;

public class Aktorea {
	//atributuak
	private String izen;
	private String abizena;
	private ArrayList<Pelikula> listaPelikula;

	//metodoak
	public Aktorea(String pIzena, String pAbizena){
		this.izen=pIzena;
		this.abizena=pAbizena;
		listaPelikula = new ArrayList<Pelikula>();
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

	public void pelikulakPantailaratu(){
		if(listaPelikula==null){
			System.out.println("Ez dago pelikularik zerrendan");
		}
		else{
			Pelikula p=null;
			Iterator<Pelikula>i=getIteradorea();
			while(i.hasNext()){
				p= i.next();
				System.out.println(p);
			}
		}
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((izen == null) ? 0 : izen.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Aktorea))
			return false;
		Aktorea other = (Aktorea) obj;
		if ((this.izen==null)&&(this.abizena==null)) {
			if ((other.izen!=null)||(other.abizena!=null))
				return false;
		} else if ((!this.izen.equals(other.izen))||(!this.abizena.equals(other.abizena))) 
			return false;
		return true;
	} 
	
	public int compareTo(Aktorea a) {
		return this.abizena.compareTo(a.abizena);
	}
}