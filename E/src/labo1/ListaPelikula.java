package labo1;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

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

	public void pelikulaBatekoAktoreakPantailaratu(String izen){
		Pelikula p = bilatuPelikula(izen);
		if(p==null){
			System.out.println("Pelikula ez dago zerrendan");
		}
		else{
			p.AktoreakPantailaratu();
		}
	}
	
	public void pelikuletatikAktoreaEzabatu(Aktorea akt) {
		Iterator <Pelikula>i=getIteradorea();
		Pelikula p1=null;
		while(i.hasNext()) {
			p1.ezabatuAktorea(akt);
		}
	}

	public Pelikula bilatuPelikula (String izen){
		Iterator <Pelikula>i=getIteradorea();
		Pelikula p1=null;
		boolean topatu = false;
		while(i.hasNext()&&(!topatu)){
			p1=i.next();
			if(p1.izenBerdinaDu(izen)){
				topatu=true;
			}
		}
		if(!topatu){
			p1=null;
		}
		return p1;
	}
	
	 public void FitxategiaKargatu(String nomFile){
	        try{
	            Scanner entrada = new Scanner(new FileReader(nomFile));
	            String linea;
	            Aktorea akt;
	            HashMap<String,Aktorea> ht=new HashMap<String,Aktorea>();
	            while (entrada.hasNext()) {
	                linea = entrada.nextLine();
	                String[] datuak=linea.split("\\s+--->\\s+");
	                Pelikula pelikula=new Pelikula(datuak[0]);
	                ListaPelikula.getListaPelikula().gehituPelikula(pelikula);
	                datuak=datuak[1].split("\\s+&&&\\s+");
	                for(int i=0;i<datuak.length;i++){
	                    if(ht.containsKey(datuak[i])){
	                        akt=ht.get(datuak[i]);
	                    }
	                    else{
	                    	akt= new Aktorea(datuak[i]);
	                        ht.put(datuak[i],akt);
	                        ListaAktorea.getListaAktorea().gehituAktorea(akt);
	                    }
	                    pelikula.gehituAktorea(akt);
	                    akt.gehituPelikula(pelikula);
	                }
	            }
	            entrada.close();
	        }
	        catch(IOException e) {e.printStackTrace();}
	    }

	public void reset() {
		ListaPelikula.getListaPelikula().lista.clear();
	}

	public int tamaina() {
		return this.lista.size();
	}
}