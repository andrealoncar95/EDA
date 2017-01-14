package labo3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;


import labo1.Aktorea;
import labo1.ListaPelikula;
import labo1.Pelikula;
import labo1.ListaAktorea;

public class GraphHash {
	HashMap<String, ArrayList<String>> g= new HashMap<String, ArrayList<String>>();
	private static GraphHash instantzia= null;

	private GraphHash() {
		// TODO Auto-generated constructor stub
	}

	public static GraphHash getInstantzia() {
		if (instantzia==null){
			instantzia= new GraphHash();
		}
		return instantzia;
	}

	public void grafoaSortu() {
		Iterator<Pelikula> i= ListaPelikula.getListaPelikula().getIteradorea();
		while(i.hasNext()){
			Pelikula p= i.next();
			ArrayList<String> aktoreIzena= new ArrayList<String>();
			ArrayList<Aktorea> aktoreak= p.getAktoreLista();
			if(!g.containsKey(p.getIzena())){
				Iterator<Aktorea> j= aktoreak.iterator();
				while(j.hasNext()){
					Aktorea a= j.next();
					ArrayList<String> pelikulaIzena= new ArrayList<String>();
					if(!g.containsKey(a.getAbizenaIzena())){
						pelikulaIzena.add(p.getIzena());
						g.put(a.getAbizenaIzena(),pelikulaIzena);
					}
					else{
						pelikulaIzena= g.get(a.getAbizenaIzena());
						pelikulaIzena.add(p.getIzena());
						g.put(a.getAbizenaIzena(), pelikulaIzena);
					}
					aktoreIzena.add(a.getAbizenaIzena());
				}
				g.put(p.getIzena(), aktoreIzena);		
			}			
		}		
	}

	public void print(){
		int i = 1;
		for (String s: g.keySet()){
			System.out.print("Element: " + i++ + " " + s + " --> ");
			for (String k: g.get(s)){
				System.out.print(k + " ### ");
			}
			System.out.println();
		}
	}

	public HashMap<String,Double> pageRank() {
		// POST: Emaitza aktore zerrendaren aktore bakoitzaren PageRank algoritmoaren balioa da
		HashMap <String,Double> emaitza = new HashMap<String,Double>();
		HashMap <String,Double> aurrekoa = new HashMap<String,Double>();
		Double bukatu= 10.0;
		Double tamaina= new Double(g.size());
		Double value= 1.0/tamaina;
		int kont=0;
		for (String key: g.keySet()){
			emaitza.put(key, value);
		}
		while(bukatu>0.0001){
			for (String key: g.keySet()){
				Double konstantea= (1.0-0.85)/tamaina;
				Double baturaPR= 0.0;
				for (ArrayList<String> values: g.values()){
					Iterator <String> i= values.iterator();
					while(i.hasNext()){
						String p= i.next();
						Double konekzioak= new Double(g.get(p).size());
						Double pR= emaitza.get(p);
						baturaPR= baturaPR+ (pR/konekzioak);
					}
					baturaPR= 0.85*baturaPR;
				}
				konstantea= konstantea+baturaPR;
				aurrekoa.put(key, emaitza.get(key));
				emaitza.put(key, konstantea);
			}
			Double kenketa=0.0;
			bukatu=0.0;
			for (String key: g.keySet()){
				kenketa= emaitza.get(key)-aurrekoa.get(key);
				bukatu= bukatu+ Math.abs(kenketa);
			}
			System.out.println(kont + " iterazioa, bukatu " + bukatu );
			kont++;
		}
		//filtrar aktoreak
		Iterator<Pelikula> j= ListaPelikula.getListaPelikula().getIteradorea();
		while(j.hasNext()){
			emaitza.remove(j.next().getIzena());
		}
		
		//gehitu page rank atributua aktoreei
		Iterator<Aktorea> k = ListaAktorea.getListaAktorea().getIteradorea();
		while(k.hasNext()){
			Aktorea ak=k.next();
			ak.setPR(emaitza.get(ak.getAbizenaIzena()));
		}
		return emaitza;
	}

	public void printPageRank(){
		int i = 1;
		HashMap <String, Double> p= this.pageRank();
		for (String s: p.keySet()){
			System.out.print("Element: " + i++ + " " + s + " --> " + p.get(s));
			System.out.println();
		}
		System.out.println("Ordenatua ");
		Iterator<String> k= this.pageRankOrdenatuta(p).iterator();
		while(k.hasNext()){
			System.out.println("Aktorea -> " + k.next());
		}
	}

	public ArrayList<String> pageRankOrdenatuta(HashMap<String,Double> mapaPR) {
		ArrayList<String> emaitza=new ArrayList<String>();
		this.zerrendaOrdenatu(ListaAktorea.getListaAktorea().getLista(), 0, ListaAktorea.getListaAktorea().tamaina()-1);
		Iterator<Aktorea> i= ListaAktorea.getListaAktorea().getIteradorea();
		while(i.hasNext()){
			Aktorea a= i.next();
			emaitza.add(a.getAbizenaIzena());
		}
		return emaitza;
	}	
	
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
	
	


	public int tamaina(){
		return g.size();
	}
}