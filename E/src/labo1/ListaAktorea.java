package labo1;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import lab1verano.Aktore;

import java.util.ArrayList;
import java.util.Iterator;

public class ListaAktorea {
	//atributuak
    private ArrayList <Aktorea> lista;
    private static ListaAktorea nireListaAktorea=null;

    // eraikitzailea
    private ListaAktorea(){
        this.lista = new ArrayList<Aktorea>();
    }

    // gainontzeko metodoak
    public static ListaAktorea getListaAktorea() {
        if (nireListaAktorea==null){
            nireListaAktorea = new ListaAktorea();
            return nireListaAktorea;
        }
        else{
            return nireListaAktorea;
        }
        
    }
    
    private Iterator<Aktorea> getIteradorea() {
        return this.lista.iterator();
    }
    
    public int tamaina() {
        return this.lista.size();
    }
    
    public void gehituAktorea(Aktorea akt) {
		if (this.lista.contains(akt)) {
			System.out.println("Aktore hau listan dago jadanik");
		}
		else {
			this.lista.add(akt);
		}
	}
	
	public void ezabatuAktorea(Aktorea akt) {
		if (this.lista.contains(akt)) {
			this.lista.remove(akt);
		}
		else {
			System.out.println("Aktore hau ez da existitzen.");
		}
	}
	
	public Aktorea bilatuAktorea(String izenAbizen) {
		Iterator<Aktorea> i = getIteradorea();
		Aktorea akt = null;
		boolean aurkituta = false;
		while ((i.hasNext())&&(!aurkituta)) {
			akt = i.next();
			if (akt.izenBerdinaDu(izenAbizen)) {
				aurkituta = true;
			}
		}
		if (!aurkituta) {
			akt = null;
			System.out.println("Aktorea ez da aurkitu listan.");
		}
		else {
			System.out.println("Aktorea aurkitu da:");
		}
		return akt;
	}
}
