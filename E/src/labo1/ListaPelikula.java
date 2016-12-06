package labo1;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
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

	public void kideakAurkitu(){
		HashMap<String,Pelikula> ht;
		int l=ListaPelikula.getListaPelikula().lista.size();
		int m;
		int n;
		for(int i=0;i<l;i++){
			ht=new HashMap<String,Pelikula>();
			Pelikula p=ListaPelikula.getListaPelikula().lista.get(i);
			ht.put(p.getIzena(),p);
			m=p.getAktoreLista().size();
			for(int k=0;k<m;k++){
				Aktorea ak=p.getAktoreLista().get(k);
				n=ak.pelikulaKopurua();
				for(int j=0;j<n;j++){
					Pelikula current=ak.getPelikulaLista().get(j);
					if(!ht.containsKey(current.getIzena())){
						ht.put(current.getIzena(),current);
						p.kideaGehitu(current);
					}
				}
			}
		}

	}

	public boolean erlazionatuta(String p1,String p2){
		Pelikula unekoa=this.bilatuPelikula(p1);
		boolean badago=false;
		HashMap<String,String> ht=new HashMap<String,String>();
		if(ListaPelikula.getListaPelikula().bilatuPelikula(p1)==null ||
				ListaPelikula.getListaPelikula().bilatuPelikula(p2)==null){
			System.out.println("Konparatu nahi diren elementuetariko bat ez dago listan!");
			return false;}
		else if(unekoa.getIzena().equals(p2) ){
			System.out.println("Pelikula berdina ari zara bilatzen");
			return true;}
		else{
			HashSet<String> bisitatuak=new HashSet<String>();
			Queue<Pelikula> aztGabeak=new LinkedList<Pelikula>();
			aztGabeak.add(unekoa);
			ht.put(unekoa.getIzena(),"");
			bisitatuak.add(unekoa.getIzena());


			while(!badago && !aztGabeak.isEmpty()){
				unekoa=aztGabeak.remove(); 
				if(unekoa.getIzena().equals(p2) ){badago=true;}
				else{
					int k=unekoa.getKideak().size();
					for(int i=0;i<k;i++){
						if(!bisitatuak.contains(unekoa.getKideak().get(i).getIzena())){
							aztGabeak.add(unekoa.getKideak().get(i));
							ht.put(unekoa.getKideak().get(i).getIzena(),unekoa.getIzena());
							bisitatuak.add(unekoa.getKideak().get(i).getIzena());
						}
					}
				}
			}
		}

		if(badago){
			ArrayList<String> lista=this.erlazionatuNondik(ht,p2);
			inprimatu(lista);
		}
		return badago;

	}

	private void inprimatu(ArrayList<String> lista) {
		//Post:Lista inprimatzen du.
		System.out.println("-~~-~~-~~-~~-~~-~~-~~-~~-~~-~~-~~-~~-~~-~~-~~-~~-~~-~~-~~-~~-~~-~~-~~-~~-~~-~~-~~-~~-~~-~~-~~-");
		System.out.println(lista.get(0)+" eta "+lista.get(lista.size()-1)+" pelikuleen arteko erlazioa hemendik dator:");
		System.out.println("==============================================================================================");
		int count=0;
		while(count<lista.size()-2){
			System.out.println(lista.get(count)+"   "+lista.get(++count)+"   "+lista.get(++count));
		}	

	}

	private ArrayList<String> erlazionatuNondik(HashMap<String, String> ht, String izen) {
		ArrayList<String> lista=new ArrayList<String>();
		String current=izen;
		String aurreko="";
		lista.add(current);
		while(!ht.get(current).equals("")){
			aurreko=current;
			current=ht.get(current);
			lista.add(bilatuBienPelikula(aurreko, current));
			lista.add(current);
		}
		return lista;
	}

	private String bilatuBienPelikula(String pel1, String pel2) {
		String emaitza="";
		Pelikula p1=this.bilatuPelikula(pel1);
		Pelikula p2=this.bilatuPelikula(pel2);
		Aktorea ak=null;
		if(!pel2.equals("") && !pel1.equals("")){
			Iterator<Aktorea> itr=p1.getAktoreLista().iterator();
			while(itr.hasNext() && emaitza==""){
				ak=itr.next();   
				if(ak.getPelikulaLista().contains(p2)){
					emaitza=ak.getAbizenaIzena(); 
				}
			}
		}
		if (emaitza.equals("")){
			return "ez dute pelikularik amankomunean";
		}else
			return emaitza;
	}
}