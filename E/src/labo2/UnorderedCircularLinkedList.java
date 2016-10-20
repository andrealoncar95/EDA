package labo2;

public class UnorderedCircularLinkedList<T> extends CircularLinkedList<T> implements UnorderedListADT<T> {

	public void addToFront(T elem) {
		// hasieran gehitu
		if (!isEmpty()){
			Node<T> elementua= new Node<T>(elem);
			elementua.next=last.next.next;
			last.next=elementua;
			count++;
		}

	}

	public void addToRear(T elem) {
		// bukaeran gehitu
		if (!isEmpty()){
			Node <T> elementua= new Node<T>(elem);
			elementua.next= last.next;
			last.next=elementua;
			last=elementua;
			count++;
		}
	}

	public void addAfter(T elem, T target) {
		Node <T> elementua= new Node<T>(elem);
		Node<T> bilatu= last;
		boolean aurkitu=false;
		if (!isEmpty()){
			while (!aurkitu){
				if(bilatu.data.equals(target)){
					aurkitu=true;
					elementua.next=bilatu.next;
					bilatu.next=elementua;
				}else{
					bilatu= bilatu.next;
				}
			}
			count++;
		}

	}

}
