package labo2;

import java.util.Iterator;


public class ProbaCircularLinkedList {
	
	public static void visualizarNodos(UnorderedCircularLinkedList<Integer> l) {
		Iterator<Integer> it = l.iterator();
		System.out.println();
		while (it.hasNext()) {
			Integer num = it.next();
			System.out.println(num);
		}
	}
	
	
	public static void main(String[] args)  {
		
		UnorderedCircularLinkedList<Integer> l =new UnorderedCircularLinkedList<Integer>();
		//azkenean gehitu
		l.addToRear(1);
		l.addToRear(3);
		l.addToRear(6);
		l.addToRear(7);
		l.addToRear(9);
		l.addToRear(0);
		l.addToRear(20);
		//hasieran gehitu
		l.addToFront(8);
		l.addToFront(4);
		//ezabatu elementua 
		l.remove(new Integer(7));
		//gehitu elementua target ondoren
		l.addAfter(2, 6);
		//lehena ezabatu
		l.removeFirst();
		//azkena ezabatu
		l.removeLast();

		
		System.out.print(" Lista ...............");
		//iteradorea frogatzeko balio du
		visualizarNodos(l);
		System.out.println("Elementu-kopurua: " + l.size());
				
		
		System.out.println("Proba Find ...............");
		System.out.println("9? " + l.find(9));
		System.out.println("0? " + l.find(0));
		System.out.println("7? " + l.find(7));
		System.out.println("2? " + l.find(2));
		System.out.println("20? " + l.find(20));
		
}
}
