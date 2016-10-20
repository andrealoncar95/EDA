package labo2;

public class UnorderedCircularLinkedList<T> extends CircularLinkedList<T> implements UnorderedListADT<T> {

	public void addToFront(T elem) {
		// hasieran gehitu
		Node<T> elementua= new Node<T>(elem);
		if (!isEmpty()){
			Node<T> lehengoa=last.next;
			elementua.next=lehengoa.next;
			last.next=elementua;
		}else{
			last=elementua;
			last.next=elementua;
		}
		count++;

	}

	public void addToRear(T elem) {
		// bukaeran gehitu
		Node <T> elementua= new Node<T>(elem);
		if (!isEmpty()){
			elementua.next= last.next;
			last.next=elementua;
			last=elementua;
		}else{
			last=elementua;
			last.next=last;
		}
		count++;
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
		}
		else{
			last=elementua;
			last.next=elementua;
		}
		count++;
	}

}
