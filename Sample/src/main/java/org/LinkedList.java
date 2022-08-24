package org;

public class LinkedList {

	public static void main(String[] args) {
		
	
		
		Node <Integer> chdchd = new Node<Integer>(-1);	chdchd.insert(-2);chdchd.insert(-3);
		Node <Integer> chd = new Node<Integer>(9);	chd.insert(2);  chd.insert(chdchd); chd.insert(3);
		
		//Node <Integer> node1 = new Node(10); Node <Integer> node2 = new Node(30); Node <Integer> node3 = new Node(60); 
		//Node <Integer> node4 = new Node(20); Node <Integer> node5 = new Node(40); Node <Integer> node6 = new Node(50); 
	
		
		Node <Integer> node = new Node<Integer>(5); 
		node.insert(2);node.insert(3); node.insert(chd); node.insert(4);node.insert(15);
		node.printNode();
	}
	
	static class Node<E>
	{
	    Node <E> head = null;
	    Node <E> next;
	    Node <E> child ;
	    E e;
	    Node(E e){
	    	head = this;
	    	head.e = e;
	    }
		  
		  
	    public void insert(E e){
		 	Node <E> curNode = new Node<E>(e);
			if(head==null){
				head = curNode;
			}else{
				head.next = curNode;

				head = curNode;
			}
		  }
		  
		  public void insert(Node <E> child){
			  Node <E> node = child ;
				if(head==null){
					head = node;
				}else{
					while(node != null) {
						head.next = node;
						
						head = node;
						if(node.child != null) {
							
							insert(node.child);
						}
						
						node = node.next;
						
						
					}
					
				}
		}
		  
		/**
		 * 
		 */
		private void printNode() {
			  Node <E> node = this;
			  if(node.child != null)
			  {
				  
			  }
			  while(node != null) {
				  System.out.println(node.e);
				  node = node.next;
			  }
		  }
				
	 }
		 
}

