package labo2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularLinkedList<T> implements ListADT<T> {

	// Atributuak
	protected Node<T> last; // azkenaren erreferentzia
	protected String deskr;  // deskribapena
	protected int count;

	public CircularLinkedList() {
		last = null;
		deskr = "";
		count = 0;
	}

	public void setDeskr(String ize) {
		deskr = ize;
	}

	public String getDeskr() {
		return deskr;
	}

	public T removeFirst() {
		// listako lehen elementua kendu da
		// Aurrebaldintza: zerrenda ez da hutsa
		Node<T> lehenengoa= null;
		if(count==1){
			lehenengoa=last;
			last=null;
			return lehenengoa.data;
		}else{
			lehenengoa=last.next;
			last.next=lehenengoa.next;
		}
		count--;
		return lehenengoa.data;
	}

	public T removeLast() {
		// listako azken elementua kendu da
		// Aurrebaldintza: zerrenda ez da hutsa
		Node<T> lag=last.next;
		T datua=null;
		boolean aurkitu=false;
		if(count==1){
			datua=last.data;
			last=null;
		}
		while(!aurkitu){
			if(lag.next.equals(last)){
				lag.next=last.next;
				datua= last.data;
				last=lag;
			}else{
				lag=lag.next;
			}
		}
		count--;
		return datua;

	}


	public T remove(T elem) {
		// Aurrebaldintza: zerrenda ez da hutsa
		// Balio hori listan baldin badago, bere lehen agerpena ezabatuko dut. Kendutako objektuaren erreferentzia 
		//  bueltatuko du (null ez baldin badago)
		Node <T>bilatzaile= last.next;
		Node <T> lag=null;
		boolean aurkitua=false;
		if(count==1&&last.data.equals(elem)){
			lag=last;
			last=null;
			count--;
			return lag.data; 
		}else{
			while(!aurkitua&&bilatzaile!=last){
				if(bilatzaile.next.data.equals(elem)){
					lag= bilatzaile.next;
					bilatzaile.next=lag.next;				
				}
				else{
					bilatzaile=bilatzaile.next;
				}
			}
		}
		if(!aurkitua){
			return null;}
		else{
			count--;
			return lag.data;
		}
	}


	public T first() {
		// listako lehen elementua ematen du
		if (isEmpty())
			return null;
		else return last.next.data;
	}

	public T last() {
		// listako azken elementua ematen du
		if (isEmpty())
			return null;
		else return last.data;
	}

	public boolean contains(T elem) {
		// Egiazkoa bueltatuko du aurkituz gero, eta false bestela
		boolean aurkitua=false;
		Node<T> lag=last.next;
		while(!aurkitua&lag!=last){
			if(lag.next.data.equals(elem)){
				aurkitua=true;
			}
			else{
				lag=lag.next;
			}
		}	
		return aurkitua;

	}

	public T find(T elem) {
		// Elementua bueltatuko du aurkituz gero, eta null bestela
		if (contains(elem)){
			return elem;
		}
		else{
			return null;
		}
	}

	public boolean isEmpty() 
	{ return last == null;};

	public int size() 
	{ return count;};

	/** Return an iterator to the stack that iterates through the items . */ 
	public Iterator<T> iterator() { return new ListIterator(); } 

	// an iterator, doesn't implement remove() since it's optional 
	private class ListIterator implements Iterator<T> { 
		
		// KODEA OSATU 
	} // private class


	public void adabegiakInprimatu() {
		System.out.println(this.toString());
	}


	@Override
	public String toString() {
		String result = new String();
		Iterator<T> it = iterator();
		while (it.hasNext()) {
			T elem = it.next();
			result = result + "[" + elem.toString() + "] \n";
		}	
		return "SimpleLinkedList " + result + "]";
	}

}
