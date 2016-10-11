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

	public void pelikulaGehitu(Pelikula pelikula) {
		this.lista.add(pelikula);
		
	}
	
	public void pelikulakPantailaratu() {
		
	}
}
