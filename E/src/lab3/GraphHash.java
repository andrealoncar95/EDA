package lab3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import labo1.Aktorea;
import labo1.ListaPelikula;
import labo1.Pelikula;

public class GraphHash {
	HashMap<String, ArrayList<String>> g= new HashMap<String, ArrayList<String>>();
	private static GraphHash instantzia= null;
	
	private GraphHash() {
		// TODO Auto-generated constructor stub
	}
	
	public static GraphHash getInstantzia(){
		if (instantzia==null){
			instantzia= new GraphHash();
		}
		return instantzia;
	}
	public void grafoaSortu(){
		Iterator<Pelikula> i= ListaPelikula.getListaPelikula().getIteradorea();
		while(i.hasNext()){
			Pelikula p= i.next();
			if(!g.containsKey(p.getIzena())){
				ArrayList<Aktorea> aktoreak= p.getAktoreLista();
				ArrayList<String> aktoreIzena= new ArrayList<String>();
				Iterator<Aktorea> j= aktoreak.iterator();
				while(j.hasNext()){
					Aktorea a= j.next();
					aktoreIzena.add(a.getAbizenaIzena());
				}
				g.put(p.getIzena(), aktoreIzena);
				int kont=0;
				Iterator<String> k= aktoreIzena.iterator();
				while(k.hasNext()){
					String izena=k.next();
					if (!g.containsKey(izena)){
						ArrayList<Pelikula> pelikulak= aktoreak.get(kont).getPelikulaLista();
						ArrayList<String> pelikulaIzena= new ArrayList<String>();
						Iterator<Pelikula> m= pelikulak.iterator();
						while(m.hasNext()){
							Pelikula pe=m.next();
							pelikulaIzena.add(pe.getIzena());
						}
						g.put(izena, pelikulaIzena);
						kont++;
					}
				}
					
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
	
	public void kalkulatuPageRank(){
		
	}

}

