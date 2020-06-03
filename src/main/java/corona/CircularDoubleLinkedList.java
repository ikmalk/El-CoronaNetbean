package corona;

import java.util.Iterator;
import java.util.ListIterator;

/*
 * ******************************************************************
 ***Created at 2/6/2020
 *
 *CircularDoubleLinkedList class copied from my lab work. 
 *Got ListIterator because doing while loop every time you want to search is a bad idea
 *
 ********************************
 ***
 * 
 * ********************************************************************
 */

public class CircularDoubleLinkedList<T> extends DoubleLinkedList<T> {
	
	 private DoubleListNode<T> tail;
	 
	 public CircularDoubleLinkedList() {
		 
		 head=null;
		 tail=null;
		 
	 }
	 
	 public void addLeftNode(T t) {
		 if(head==null) {
			 head = new DoubleListNode(t, head, head);
			 
		 }
		 else if(tail==null){
			 tail = head;
			 head = new DoubleListNode(t, tail, tail);
			 tail.setPreviousLink(head);
		 }
		 else {
			 head = new DoubleListNode(t,tail,head);
		 }
		
		 
	 }
	 
	 public void addRightNode(T t) {
		 if(head==null) {
			 head = new DoubleListNode(t, head, head);
		 }
		 else if(tail==null) {
			 tail = new DoubleListNode(t, head, head);
			 head.setNextLink(tail);
		 }
		 else {
			 DoubleListNode newNode = new DoubleListNode(t,tail,head);
			 tail.setNextLink(newNode);
			 newNode.setPreviousLink(tail);
			 tail = newNode;
//			 System.out.println(head.getNextLink().toString());
//			 System.out.println(tail.toString());
//			 System.out.println(tail.getNextLink().toString());
		 }
	 }
	 
	 //length
	 public int length() {
		 
		 int length = 0;
		 DoubleListNode currentNode = head;
		 
		 
		 do {
			 
			 currentNode = currentNode.getNextLink();
			 length++;
			 
		 }while(currentNode!=head);
		 
		 return length;
		 
	 }
	 
	 
	 public T getItem(int index) {
		 
		 DoubleListNode currentNode = head;
		 for(int i=0;i<index;i++) {
			 currentNode = currentNode.getNextLink();
		 }
		 
		 return (T)currentNode.getData();
		 
	 }
	 

	 
	 public void showCircularList() {
		 
		 DoubleListNode currentNode = head;
		 String s = "";
		 if(head==null) {
			 System.out.println("The list is empty");
		 }
		 else if(tail==null) {
			 System.out.println(currentNode.toString());
		 }
		 else {
			 do{
				 
				 s+=currentNode.toString();
				 currentNode = currentNode.getNextLink();
				 
			 }while(currentNode!=head);
//			 s+=currentNode.toString();
//			 currentNode=currentNode.getNextLink();
			 s+="(head)"+currentNode.toString();
			 System.out.println(s);
		 }
		 
		 
		
		 
	 }
	 
		public Iterator<T> getIterator() {
			
			CircularLinkedListIterator iterator = new CircularLinkedListIterator();
			
			return iterator;
			
		}
		
		public Iterator<T> updateIterator(T currentData) {
			CircularLinkedListIterator iterator = new CircularLinkedListIterator();
			T data = iterator.next();
			while(data!=currentData) {
				data = iterator.next();
			}
			
			return iterator;
			
		}
		

		
		
		private class CircularLinkedListIterator implements ListIterator<T>{
	    	

	    	private DoubleListNode currentNode = null,previousNode = null;
	    	private boolean atHead = true;
	    	
			@Override
			public boolean hasNext() {
				
				if(atHead) {
					atHead = false;
					return true;
				}
				
				return currentNode.getNextLink()!=null;
				
			
			}
			
			public void setNode(DoubleListNode node) {
				currentNode = node;
				previousNode = currentNode.getPreviousLink();
			}

			@Override
			public T next() {
				
				if(currentNode==null) {
					 currentNode = head;
					 return (T)currentNode.getData();
				}
				
				previousNode = currentNode;
				currentNode = currentNode.getNextLink();
				return (T)currentNode.getData();
				
				
			}
		
			
			public void remove() {
				
				
				if(previousNode.getPreviousLink()==currentNode) {
					previousNode = currentNode.getPreviousLink();
				}
				previousNode.setNextLink(currentNode.getNextLink());
				currentNode = previousNode;
				previousNode = null;
			
			}

			@Override
			public boolean hasPrevious() {
				if(atHead) {
					atHead = false;
					return true;
				}
				
				return currentNode.getPreviousLink()!=null;
			}

			@Override
			public T previous() {
				if(currentNode==null) {
					 currentNode = head;
					 return (T)currentNode.getData();
				}
				
				previousNode = currentNode;
				currentNode = currentNode.getPreviousLink();
				return (T)currentNode.getData();
				
			}

			@Override
			public int nextIndex() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int previousIndex() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public void set(T e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void add(T e) {
				// TODO Auto-generated method stub
				
			}
			
		}
 
}
