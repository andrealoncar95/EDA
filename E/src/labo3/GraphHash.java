package labo3;

import java.util.ArrayList;
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
	
	public void kalkulatuPageRank(ListaAktorea listaAkt) {
		// POST: Emaitza aktore zerrendaren aktore bakoitzaren PageRank algoritmoaren balioa da
		
		double InitialPageRank;
		double OutgoingLinks = 0; 
		double DampingFactor = 0.85; 
		double TempPageRank[] = new double[10];		 
		int ExternalNodeNumber;
		int InternalNodeNumber; 
		int k = 1;
		int count = 1;
		 
		InitialPageRank = 1/listaAkt.tamaina();
		System.out.printf("Aktore guztiak: "+listaAkt.tamaina()+"\t Hasierako PageRank aktore guztiekin: "+InitialPageRank+"\n");
		  
		// Hasieraketa (0 iterazio)
		for(k=1;k<=listaAkt.tamaina();k++) {
			listaAkt.getLista().get(k).pageRank = InitialPageRank;
		}   
		   
		System.out.printf("\n Hasierako PageRank, 0 iterazio \n");
		for(k=1;k<=listaAkt.tamaina();k++) {
			System.out.printf("Listako "+k+". aktorearen PageRank hau da:\t"+listaAkt.getLista().get(k).pageRank+"\n");
		}
		
		while(count<=2) {
			// Aktore guztien PageRank-a gorde tenporalki
			
			for(k=1;k<=listaAkt.tamaina();k++) {
				TempPageRank[k]=listaAkt.getLista().get(k).pageRank;
				listaAkt.getLista().get(k).pageRank=0;
			}
			
			for(InternalNodeNumber=1;InternalNodeNumber<=listaAkt.tamaina();InternalNodeNumber++) {
				for(ExternalNodeNumber=1;ExternalNodeNumber<=listaAkt.tamaina();ExternalNodeNumber++) {
					if(listaAkt.getLista().get(k).getPath()[ExternalNodeNumber][InternalNodeNumber] == 1) {
						k=1;
						OutgoingLinks=0;  //OutgoingLinks kantitatea kontatu ExternalNodeNumber bakoitzeko
						while(k<=listaAkt.tamaina()) {
							if(listaAkt.getLista().get(k).getPath()[ExternalNodeNumber][k]==1) {
								OutgoingLinks = OutgoingLinks+1;
							}
							k = k+1;
						}
						
						// PageRank kalkulatu
						listaAkt.getLista().get(InternalNodeNumber).pageRank+=TempPageRank[ExternalNodeNumber]*(1/OutgoingLinks);
					}
				}
			}
			
			for(k=1;k<=listaAkt.tamaina();k++) {
				System.out.printf("Listako "+k+". aktorearen PageRank da:\t"+listaAkt.getLista().get(k).pageRank+"\n");
				count = count+1;
			}
			
			// Indargetze faktorea sartu (DampingFactor)
			for(k=1;k<=listaAkt.tamaina();k++) {
				listaAkt.getLista().get(k).pageRank=(1-DampingFactor)+ DampingFactor*listaAkt.getLista().get(k).pageRank;
			}
			
			// PageRank pantailaratu
			System.out.printf("\n PageRank definitiboa: \n");
			for(k=1;k<=listaAkt.tamaina();k++) {
				System.out.printf("Listako "+k+". aktorearena:\t"+listaAkt.getLista().get(k).pageRank+"\n");
			}
		}
	}
	
	public ArrayList<String> pageRankOrdenatuta(ListaAktorea listaAkt) {
		// POST: Emaitza, PageRank balioa erabilita, beheruntz ordenaturiko aktore zerrenda da
		
		//TODO
	}	
}