package simulator;

import java.util.LinkedList;
import simulator.util.Const;
import java.util.Stack;

public class Cache {

	public class CacheLine {
		int address;
		int data;
		/*String[] blockNum = {"0","0","0","0"};
        Registers registers = new Registers();*/
		public CacheLine(int address, int data) {
			this.address = address;
			this.data = data;
		}
		public int getAddress() {
			return this.address;
		}

		public void setAddress(int address) {
			this.address = address;
		}

		public int getData() {
			return data;
		}

		public void setData(int data) {
			this.data = data;
		}
	}

	LinkedList<CacheLine> cacheLines;
	public Cache() {
		this.cacheLines = new LinkedList<CacheLine>();
	}

	public void addLine(int address, int value) {
		if (this.cacheLines.size() >= Const.CACHE_LINES) {
			this.cacheLines.removeLast();
		}
		this.cacheLines.addFirst(new CacheLine(address, value));
	}

	public LinkedList<CacheLine> getCacheLines() {
		return cacheLines;
	}
}
