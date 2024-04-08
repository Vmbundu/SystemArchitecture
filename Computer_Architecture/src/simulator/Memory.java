package simulator;

import java.io.FileWriter;
import java.util.HashMap;
import java.util.LinkedList;
import simulator.util.*;
import simulator.Cache.CacheLine;
//import simulator.Cache.CacheLine;
//Store memory from the txt file
public class Memory {
	static HashMap<Integer, Integer> memory;
	private Cache cache;
	private String keyboardContent;
	private String printerContent;
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

	public String getPrinterContent() {
		return this.printerContent;
	}
	public void setPrinterContent(String inputStr) {
		this.printerContent = inputStr;
	}
	public String getKeyboardContent() {
		return this.keyboardContent;
	}
	public void setKeyboardContent(String keyboardContent) {
		this.keyboardContent = keyboardContent;
		System.out.println("keyboardContent: " + keyboardContent);
		/*String[] lines = keyboardContent.split(System.getProperty("line.separator"));
		for (int i = 0; i < lines.length; i++) {
			String resultStr = "000000";
			String line = lines[i];
			System.out.println(line);
			String[] arr = line.split("\\s+");
			System.out.println(arr[0] + arr[1] + " " + Const.OPCODE.get(arr[0]));

			switch (arr[0]) {
				case "LOC":
					resultStr = Assembler.loc(arr);
					break;
				case "Data":
					resultStr = Assembler.data(arr);
					break;
				case "RFS":
					resultStr = Assembler.rfs(arr, Integer.parseInt("13", 8));
					break;
				case "SETCCE":
					resultStr = Assembler.setcce(arr, Integer.parseInt("44", 8));
					break;
				case "TRAP":
					resultStr = Assembler.trap(arr, Integer.parseInt("45", 8));
					break;
				case "HLT":
					resultStr = Assembler.hlt();
					System.out.println("Code Ran!");
					break;
				default:
					break;

			}
			if (Const.OPCODE.get(arr[0]) != null){
				String op = Const.OPCODE.get(arr[0]);
				//Load/Store instructions with at most 4 parameters
				if(op == "04" || op == "05" || op == "06" || op == "07" || op == "11" || op == "12" || op == "14" || op == "15"){
					resultStr = Assembler.method_two(arr, Integer.parseInt(op,8));
				} else if(op == "01" || op == "02" || op == "03" || op == "10") {
					//Load/Store instructions with at most 3 parameters
					resultStr = Assembler.method_one(arr, Integer.parseInt(op, 8));
				} else if(op == "16" || op == "17"){
					resultStr = Assembler.logicalInstruction(arr, Integer.parseInt(op,8));
					//index = index + 1;
					//Intermediate to Register instructions
				}else if(op == "20" || op == "21") {
					resultStr = Assembler.method_three(arr, Integer.parseInt(op, 8));
					//index = index + 1;
					//Shift operation instructions
				} else if (op == "22" || op == "23" || op == "24" || op == "25" || op == "26" || op == "27"){
					resultStr = Assembler.operations(arr, Integer.parseInt(op, 8));
				} else if (op == "30" || op == "31") {
					resultStr = Assembler.shift(arr, Integer.parseInt(op, 8));
					//index = index + 1;
					//Register to Register operation instructions
				} else{
					resultStr = Assembler.io_operations(arr, Integer.parseInt(op, 8));
					//index = index + 1;
				}
			}
			System.out.println(resultStr);
		}*/
	}
}
