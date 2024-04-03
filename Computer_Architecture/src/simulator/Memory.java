package simulator;

import java.util.HashMap;
import java.util.LinkedList;

import simulator.Cache.CacheLine;
//import simulator.Cache.CacheLine;
//Store memory from the txt file
public class Memory {
	static HashMap<Integer, Integer> memory;
	private Cache cache;
	private String keyboardContent;
	public Memory() {
		memory = new HashMap<Integer, Integer>();
		cache = new Cache();
	}
	//Add data or instructions into memory
	public void addMemory(int adr, int value) {
		memory.put(adr,value);
		String strADR = Integer.toString(adr);
		
		
		String strVAL = Integer.toString(value);
		
		
		//System.out.println("MAR: "+strADR+" MBR: "+strVAL);
	}
	
	//Get data/instructions from memory
	public int getMemory(int adr) {
		if(memory.get(adr) == null) {
			return adr;
		}
		return memory.get(adr);
	}
	
	//Return the entire memory 
	public HashMap<Integer, Integer> getMemory(){
		return memory;
	}
	
	//Clears memory
	public static void Delete(){
		if(memory == null)
		{
			System.out.println("Memory was no initialized");
		}
		memory.clear();
		System.out.println("Memory is empty");
	}

	//Returns the entire cache
	public Cache getCache() {
		return cache;
	}

	//Get instructions/data from the cache. If not there, get from memory
	//and add to cache
	public int loadFromCache(int address) {
		int block = address & 3;
		address = address  & ~3;
		int []data = new int[8];
		
		for (CacheLine line : cache.getCacheLines()) { // check every block
			if (address == line.getAddress()) {
				int[] blocks = line.getData(); // this data exists in cache.
				return blocks[block];
			}
		}

		int value = getMemory(address + block);
		
		for(int i = 0; i < 8; i++) {
			data[i] = getMemory(address+i);
		}
		cache.addLine(address,data);
		return value;
	}

	//Store data/instructions into cache 
	public void storeIntoCache(int address) {
		int adr = address >> 3;
		int []data = new int[8];
		
		for(int i = 0; i < 8; i++) {
			data[i] = getMemory(adr+i);
		}
		cache.addLine(address, data);
	}

	public void setKeyboardContent(String keyboardContent){
		this.keyboardContent = keyboardContent;
	}
}
