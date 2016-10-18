package labo1;
import java.util.ArrayList;
import java.util.Iterator;

public class Aktorea {
	//atributuak
	private String abizenIzen;
	private ArrayList<Pelikula> listaPelikula;

	//metodoak
	public Aktorea(String pAbizenIzen){
		this.abizenIzen = pAbizenIzen;
		listaPelikula = new ArrayList<Pelikula>();
	}

	public String getAbizenaIzena() {
		return this.abizenIzen;
	}

	public void setAbizenaIzena(String pAbizenIzen) {
		this.abizenIzen = pAbizenIzen;
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

	public boolean izenBerdinaDu(String pAbizenIzen) {
		if (this.abizenIzen.equals(pAbizenIzen)) {
			return true;
		}
		else {
			return false;
		}
	}

	public void pantailaratuIzenAbizenak() {
		System.out.println(this.abizenIzen);
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
		result = prime * result + ((this.abizenIzen == null) ? 0 : this.abizenIzen.hashCode());
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
		if (this.abizenIzen==null) {
			if (other.abizenIzen!=null)
				return false;
		} else if (!this.abizenIzen.equals(other.abizenIzen)) 
			return false;
		return true;
	} 
	
	public int compareTo(Aktorea a) {
		return this.abizenIzen.compareTo(a.abizenIzen);
	}
}