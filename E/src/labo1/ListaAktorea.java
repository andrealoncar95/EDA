package labo1;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
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

    // gainontzeko metodoak0
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
    public Aktorea bilatuAktorea(Aktorea akt) {
    	Aktorea a=null;
    	return a;
    }
    public void aktoreaGehitu(Aktorea akt) {
        lista.add(akt);
    }
    public void AktoreBatenPelikulakBueltatu(Aktorea akt) {
    }
}
