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
		String strADR = Integer.toString(adr);
		
		
		String strVAL = Integer.toString(value);
		
		
		System.out.println("MAR: "+strADR+" MBR: "+strVAL);
	}
	
	public int getMemory(int adr) {
		if(memory.get(adr) == null) {
			return adr;
		}
		return memory.get(adr);
	}
	
	public HashMap<Integer, Integer> getMemory(){
		return memory;
	}
}
