package labo2;

public class ProbaOrderedCircularLinkedList {	
		
		public static void main(String[] args)  {
			
			OrderedCircularLinkedList<Integer> l = new OrderedCircularLinkedList<Integer>();
			l.add(20);
			l.add(455);
			l.add(62);
			l.add(71);
			l.add(1);
			l.add(4);
			l.add(220);
			l.remove(new Integer(71));

			
			System.out.print(" Lista ...............");
			l.adabegiakInprimatu();
			System.out.println(" Elementu-kopurua: " + l.size());
					
			
			System.out.println("Proba Find ...............");
			System.out.println("20? " + l.find(20));
			System.out.println("9? " + l.find(9));
			System.out.println("9? " + l.find(9));
			System.out.println("0? " + l.find(0));
			System.out.println("7? " + l.find(7));
			
			
			
			OrderedCircularLinkedList<Pertsona> l2 = new OrderedCircularLinkedList<Pertsona>();
			l2.add(new Pertsona("jon", "1111"));
			l2.add(new Pertsona("ana", "7777"));
			l2.add(new Pertsona("amaia", "3333"));
			l2.add(new Pertsona("unai", "8888"));
			l2.add(new Pertsona("pedro", "2222"));
			l2.add(new Pertsona("olatz", "5555"));
			l2.remove(new Pertsona("", "8888"));

			
			System.out.print(" Lista ...............");
			l2.adabegiakInprimatu();
			System.out.println(" Elementu-kopurua: " + l2.size());
					
			
			System.out.println("Proba Find ...............");
			System.out.println("2222? " + l2.find(new Pertsona("", "2222")));
			System.out.println("5555? " + l2.find(new Pertsona("", "5555")));
			System.out.println("7777? " + l2.find(new Pertsona("", "7777")));	
			System.out.println("8888? " + l2.find(new Pertsona("", "8888")));	
			
			
	}
	}

