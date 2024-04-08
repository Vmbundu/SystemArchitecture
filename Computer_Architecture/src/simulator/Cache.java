package simulator;

import java.util.LinkedList;
import simulator.util.Const;
import java.util.Stack;
//Clss that handles cache operations
public class Cache {

	public class CacheLine {
		int address;
		int [] data;
		//int data;
		/*String[] blockNum = {"0","0","0","0"};
        Registers registers = new Registers();*/
		//Constructor method
		public CacheLine(int address, int []data) {
			this.address = address;
			this.data = data;
		}
		//Get Addresses
		public int getAddress() {
			return this.address;
		}
		//Set Addressses
		public void setAddress(int address) {
			this.address = address;
		}
		//Get Data
		public int[] getData() {
			return data;
		}
		//Sets Data
		public void setData(int[] data) {
			
			this.data = data;
		}
	}

	//How the cache lines are referenced
	LinkedList<CacheLine> cacheLines;
	public Cache() {
		cacheLines = new LinkedList<CacheLine>();
	}
	//Add data into cache
	public void addLine(int address, int[] value) {
		if (this.cacheLines.size() >= Const.CACHE_LINES) {
			this.cacheLines.removeLast();
		}
		this.cacheLines.addFirst(new CacheLine(address, value));
	}
	//Return the cache line
	public LinkedList<CacheLine> getCacheLines() {
		return cacheLines;
	}
}
