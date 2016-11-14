package labo3;

import java.util.ArrayList;
import java.util.HashMap;

public class GraphHash {

	HashMap<String, ArrayList<String>> g;
	
	public void grafoaSortu(PelikulenZerrenda lPelikulak) {
		// Post: pelikulen zerrendatik grafoa sortzen du
		//       Adabegiak aktoreen izenak eta pelikulen izenburuak dira 
           
            // KODEA OSATU
	}

	public void print() {
		int i = 1;
		for (String s: g.keySet()){
			System.out.print("Element: " + i++ + " " + s + " --> ");
			for (String k: g.get(s)){
				System.out.print(k + " ### ");
			}
			System.out.println();
		}
	}
	
	public boolean erlazionatuta(String p1, String p2) {
            // KODEA OSATU
	}
}