package labo1;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
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

	public Iterator<Aktorea> getIteradorea() {
		return this.lista.iterator();
	}

	public int tamaina() {
		return this.lista.size();
	}

	public void gehituAktorea(Aktorea akt) {
			this.lista.add(akt);
	}

	public void ezabatuAktorea(Aktorea akt) {
		if (this.lista.contains(akt)) {
			this.lista.remove(akt);
		}
		else {
			System.out.println("Aktore hau ez da existitzen.");
		}
	}
	
	public ArrayList<Aktorea> getLista(){
		return this.lista;
	}
	
	public void AktoreakIdatzi(){
    	try{
    		PrintWriter pw= new PrintWriter("listaAktoreak.txt");
    		Aktorea akt;
    		Iterator<Aktorea> i= ListaAktorea.getListaAktorea().getIteradorea();
    		while (i.hasNext()){
    			akt=i.next();
    			pw.print(akt.getAbizenaIzena());
    			pw.println();
    		}
    		pw.close();
    		System.out.println("Idatzi egin da");
    	}
    	catch (Exception e) {
			e.printStackTrace();
	
    	}
	}

	public Aktorea bilatuAktorea(String abizenIzen) {
		Iterator<Aktorea> i = getIteradorea();
		Aktorea akt = null;
		boolean aurkituta = false;
		while ((i.hasNext())&&(!aurkituta)) {
			akt = i.next();
			if (akt.izenBerdinaDu(abizenIzen)) {
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



	public void aktoreBatenPelikulakBueltatu(String abizenIzen){
		Aktorea a = bilatuAktorea(abizenIzen);
		if(a==null){
			System.out.println("Aktorea ez dago zerrendan");
		}
		else{
			a.pelikulakPantailaratu();
		}
	}

	public void AktoreakPantailaratu(){
		Aktorea akt=null;
		Iterator<Aktorea>i=getIteradorea();
		while(i.hasNext()){
			akt= i.next();
			System.out.println(akt.getAbizenaIzena());
		}
	}

	public Aktorea[] toArray()
	{
		Aktorea[] aktore= new Aktorea[ListaAktorea.getListaAktorea().tamaina()];
		Aktorea akt=null;
		int i=0;
		Iterator<Aktorea>itr= ListaAktorea.getListaAktorea().getIteradorea();
		while (itr.hasNext() && i<=ListaAktorea.getListaAktorea().tamaina()){
			akt=itr.next();
			aktore[i]=akt;
			i++;
		}
		return aktore;
	}



	private void toArrayList(Aktorea[] akt){
		ListaAktorea.getListaAktorea().reset();
		int k=0;
		for (k=0; k<akt.length;k++) {
			ListaAktorea.getListaAktorea().gehituAktorea(akt[k]);
		}
	}
	public void reset() {
		ListaAktorea.getListaAktorea().lista.clear();;
	}
	// quickSort
	public void zerrendaOrdenatu(ArrayList<Aktorea> listaAktorea, int hasiera, int bukaera) {
		if (bukaera - hasiera >= 0) {
			int ind = zatiketa(listaAktorea, hasiera, bukaera);
			zerrendaOrdenatu(listaAktorea, hasiera, ind - 1);
			zerrendaOrdenatu(listaAktorea, ind + 1, bukaera);
		}
	}
	
	private int zatiketa(ArrayList<Aktorea> listaAktorea, int i, int f) {
		Aktorea a = listaAktorea.get(i);

		int ezker = i;
		int eskuin = f;

		while (ezker < eskuin) {
			while (listaAktorea.get(ezker).compareTo(a) <= 0 && ezker < eskuin)
				ezker++;
			while (listaAktorea.get(eskuin).compareTo(a) > 0)
				eskuin--;

			if (ezker < eskuin)
				Collections.swap(listaAktorea, ezker, eskuin);
		}
		listaAktorea.set(i, listaAktorea.get(eskuin));
		listaAktorea.set(eskuin, a);

		return eskuin;
	}
}