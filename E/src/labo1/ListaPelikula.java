package labo1;

import java.util.ArrayList;
import java.util.Iterator;

public class ListaPelikula {
	//atributuak
	private ArrayList <Pelikula> lista;
	private static ListaPelikula nireListaPelikula=null;

	// eraikitzailea
	private ListaPelikula(){
		this.lista = new ArrayList<Pelikula>();
	}

	// gainontzeko metodoak0
	public static ListaPelikula getListaPelikula() {
		if (nireListaPelikula==null){
			nireListaPelikula = new ListaPelikula();
			return nireListaPelikula;
		}
		else{
			return nireListaPelikula;
		}

	}
	private Iterator<Pelikula> getIteradorea() {
		return this.lista.iterator();
	}

	public void gehituPelikula(Pelikula peli) {
		if (this.lista.contains(peli)) {
			System.out.println("Pelikula listan zegoen jadanik.");
		}
		else {
			this.lista.add(peli);
		}
	}

	public void ezabatuPelikula(Pelikula peli) {
		if (this.lista.contains(peli)) {
			this.lista.remove(peli);
		}
		else {
			System.out.println("Pelikula ez zegoen listan.");
		}
	}

	public void pelikulaBatekoAktoreakPantailaratu(String izena){
		Pelikula p=bilatuPelikula(izena);
		if(p==null){
			System.out.println("Pelikula ez dago zerrendan");
		}
		else{
			p.AktoreakPantailaratu();
		}
	}

	public Pelikula bilatuPelikula (String izena){
		Iterator <Pelikula>i=getIteradorea();
		Pelikula p1=null;
		boolean topatu = false;
		while(i.hasNext()&&(!topatu)){
			p1=i.next();
			if(p1.izenBerdinaDu(izena)){
				topatu=true;
			}
		}
		if(!topatu){
			p1=null;
		}
		return p1;
	}

}
