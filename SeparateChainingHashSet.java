

import java.util.LinkedList;

public class SeparateChainingHashSet {
	private LinkedList<Integer>[] data;
	
	public SeparateChainingHashSet() {
		data = new LinkedList[997];
	}
	
	private int hash(int element) {
		return Math.abs(element % data.length);
	}
	
	public void add(int element) {
		if(data[hash(element)] == null) {
			data[hash(element)] = new LinkedList<>();
		}
		
		data[hash(element)].add(element);
	}
	
	public void remove(int element) {
		data[hash(element)].remove((Integer) element);
	}
	
	public boolean contains(int element) {
		//true iff there are no collisions. Can say for sure if its false
		if(data[hash(element)] == null) {
			return false;
		}
		
		return data[hash(element)].contains(element);
	}
	
	
}
