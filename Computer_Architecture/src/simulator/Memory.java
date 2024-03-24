package simulator;

import java.util.HashMap;
//Store memory from the txt file
public class Memory {
	HashMap<Integer, Integer> memory; 
	
	public Memory() {
		memory = new HashMap<Integer, Integer>();
	}
	
	public void addMemory(int adr, int value) {
		memory.put(adr,value);
	}
	
	public int getMemory(int adr) {
		return memory.get(adr);
	}
	
	public HashMap<Integer, Integer> getMemory(){
		return memory;
	}
	
	public void Delete(){
		if(memory == null)
		{
			System.out.println("Memory was no initialized");
		}
		memory.clear();
		System.out.println("Memory is empty");
	}
}
