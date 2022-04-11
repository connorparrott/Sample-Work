
public class Player {
	 public Node first;
	 public Node last;
	    
	    Player(){
	    	first = null;
	    	last = null;
	    }
	    public void print(){
	        Node iter = first;
	        if(iter == null) {
	        	return;
	        }
	        while(iter != last)
	        {
	            System.out.println(iter.getData());
	            iter = iter.getNext();
	        }
	        System.out.println(iter.getData());
	    }
	    
	    public void add(int num){
	        Node temp = new Node(num);
	        if(first == null)
	        {
	           temp.setNext(temp);
	           temp.setPrev(temp);
	           last = temp;
	           
	        } 
	        else{    
	            temp.setNext(first);
	            temp.setPrev(last);
	            first.setPrev(temp);
	            last.setNext(temp);
	         }
	        first = temp;
	        
	        
	    }
	    
	    public Node getNode(int num){
	        Node iter = first;
	        while(iter != last)
	        {
	            if(iter.getData() == num){
	                 return iter;
	            }
	             iter = iter.getNext();
	        }
	        if(iter.getData() == num) {
	        	return iter;
	        } else {
	        	return null;
	        }
	    }
	    
	    public void deleteNode(int num){
	        Node iter = first;
	        
	        if(iter == null) {
	        return;
	        }
	        
	        if(iter.getData() == num) {
	        	first.getNext().setPrev(last);
	        	last.setNext(iter.getNext());
	        	first = iter.getNext();
	        	return;
	        }
	        else {
	        	iter = iter.getNext();
	        }
	        while(iter != last) {
	        	if(iter.getData() == num) {
	        		iter.getPrev().setNext(iter.getNext());
	        		iter.getNext().setPrev(iter.getPrev());
	        		return;
	        	}
	        	iter = iter.getNext();
	        }
	        if(iter == last && iter.getData() == num) {
	        	iter.getPrev().setNext(first);
	        	iter.getNext().getNext().setPrev(iter.getPrev());
	        	last = iter.getPrev();
	        	return;
	        }
	        System.out.println("Didn't find " + num);
	    }
	    
	    public void deleteNodeCow(int num){
	        Node iter = first;
	        
	        if(iter == null) {
	        return;
	        }
	        
	        if(iter.getData() == num) {
	        	System.out.println("Deleting the first node");
	        	
	        	first.getNext().setPrev(last);
	        	last.setNext(iter.getNext());
	        	first = iter.getNext();
	        	return;
	        }
	        else {
	        	iter = iter.getNext();
	        }
	        while(iter != last) {
	        	if(iter.getData() == num) {
	        		System.out.println("deleting a node in the middle");
	        		iter.getPrev().setNext(iter.getNext());
	        		iter.getNext().setPrev(iter.getPrev());
	        		return;
	        	}
	        	iter = iter.getNext();
	        }
	        if(iter == last && iter.getData() == num) {
	        	System.out.println("deleting the last node");
	        	iter.getPrev().setNext(first);
	        	iter.getNext().setPrev(iter.getPrev());
	        	last = iter.getPrev();
	        	return;
	        }
	        System.out.println("Didn't find " + num);
	    }
}
