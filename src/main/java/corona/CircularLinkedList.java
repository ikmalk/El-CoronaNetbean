package corona;

import java.util.Iterator;

/*
 * ******************************************************************
 ***Created at 2/6/2020
 *
 *CircularDoubleLinkedList class copied from my lab work. 
 *Got ListIterator because doing while loop every time you want to search is a bad idea
 *
 ********************************
 ***To do list
 *-Create
 * 
 * ********************************************************************
 */

public class CircularLinkedList<T> extends LinkedList{
	
	private ListNode tail;
	
	public CircularLinkedList() {
		head = null;
		//tail = null;
	}
	
	
	//add from back
	public void addCircularNode(T a) {
		ListNode newNode =new ListNode(a, null);
        ListNode currentNode = head; 
		if(head==null) {
			head = newNode;
			tail = new ListNode(head.getData(), head);
			head.setLink(tail);
		}
		else {
			tail.setLink(newNode);
			newNode.setLink(head);
			tail = newNode;
			
		}
	}
	
	//delete from back
	public void deleteCircularNode() {
		ListNode currentNode = head;
		if(head!=null) {
			if(head.getLink()==tail) {
				head = null;
				tail = null;
			}
			else {
				while(currentNode.getLink()!=tail) {				
					currentNode = currentNode.getLink();
					
				}
				currentNode.setLink(head);
				tail = currentNode;
				currentNode = null;
				//tail.setLink(head);
			}
		}
		else {
			System.out.println("The list is empty");
		}
		
	}
	
	public int length() {
		
		ListNode currentNode = head;
		int count = 0;
		do {
			currentNode = currentNode.getLink();
			count++;
		}while(currentNode!=head);
		return count;
	}
	
	
	public void showCircularList() {
		
		ListNode currentNode = head;    		
        while (currentNode != tail) {
            System.out.print(currentNode.toString());
            currentNode = currentNode.getLink();
        }
        System.out.print(tail.toString());
        System.out.print("(Head)"+tail.getLink().toString());
		
	}
	
	public T getCircularItem(int index) {

		if(index==0) {
			return (T)head.getData();
		}
		else if(index==length()-1) {
			return (T)tail.getData();
		}
		else {
			ListNode currentNode = head;                        
            for(int i=1; i<=index; i++)
                currentNode = currentNode.getLink();
            return (T) currentNode.getData();
		}
	}
	

}
