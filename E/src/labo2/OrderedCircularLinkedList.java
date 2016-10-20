package labo2;

public class OrderedCircularLinkedList<T> extends CircularLinkedList<T> implements OrderedListADT<T> {

	public void add( T  elem ) {
		// Txikienetik handienera ordenatuta dago lista
		Node<T> lag=last;
		Node <T> sartu = new Node<T>(elem);
		boolean aurkitua=false;
		Comparable obj=(Comparable) elem;
		//lista huts badago
		if(isEmpty()){
			last=sartu;
			last.next=last;
			count++;
		}
		else{
			//lehengo, ematen diguten datua handiena dela edo listan objetu bat
			//baino ez daukagun kasua aztertuko dugu
			if(obj.compareTo(last.data)==1){
				last.next=sartu;
				sartu.next=last;
				last=sartu;
				count++;
				aurkitua=true;
			}
			while(!aurkitua){
				if(obj.compareTo(lag.next.data)==1){
					lag=lag.next;
				}
				else{
					aurkitua=true;
					sartu.next=lag.next;
					lag.next=sartu;
					count++;
				}
			}
		}
	}

}
