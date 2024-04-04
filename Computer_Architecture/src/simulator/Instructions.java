package simulator;

import simulator.util.Const;

import javax.swing.JTextField;

public class Instructions {
	Registers registers;
	Memory memory;
	
	public Instructions(Registers registers, Memory memory) {
		this.registers = registers;
		this.memory = memory;
	}
	
	public int exactAddress(int value) {
		int r = (value >> 8) & 3;
		int ix = (value >> 6) & 3;
		int i = (value >> 5) & 1;
		int adr = value & 31;
		
		//will hold the correct register value 
		//int currReg = registers.getRnByNum(ix);
		int currReg = registers.getXnByNum(ix);
		
		adr = adr + currReg;
		
		if(i == 1) {
			adr = memory.getMemory(adr);
		}
		
		return adr;
	}
	
	 public void ldr(int value) {
		  int r = (value >> 8) & 3;
		  int result = exactAddress(value);
		  
		  
		  result = memory.getMemory(result);
		  
		  registers.setRnByNum(r, result);
		  System.out.println("Load Complete");
		  System.out.println("Register #: "+Integer.toString(r)+" Results: "+Integer.toString(result));
	  }
	 
	//Store Instruction for General Purpose Register
	  public void str(int value) {
		  int r = (value >> 8) & 3;
		  int result = exactAddress(value);
		  
		  
		  memory.addMemory(result, registers.getRnByNum(r));
		  System.out.println("Store Complete");
		  System.out.println("Register #: "+Integer.toString(r)+" Results: "+Integer.toString(result));

	  }
	  
	  public void ldx(int value) {
		  int xi = (value >> 6) & 3;
		  int result = exactAddress(value);
		  
		  
		  result = memory.getMemory(result);
		  
		  registers.setXnByNum(xi, result);
		  System.out.println("Loaded X Register");
		  System.out.println("Register 2 value: "+ Integer.toString(registers.getX2()));
	  }
	  
	//Store Instruction for General Purpose Register
	  public void stx(int value) {
		  int xi = (value >> 6) & 3;
		  int result = exactAddress(value);
		  
		  
		  memory.addMemory(result, registers.getXnByNum(xi));
		  System.out.println("Store into General");
	  }
	  
	  public void lda(int value) {
		  int r = (value >> 6) & 3;
		  int adr = value & 31;
		  
		  registers.setXnByNum(adr,r);
		  System.out.println("Load Direct Address");
		  	  
	  }
	  
	  public void jz(int value) {
		  int result = exactAddress(value);
		  
		  if(registers.getCCElementByBit(0) == true) {
			  registers.setPC(result);
		  }else {
			  registers.increasePCByOne();
		  }
	  }
	 
	  public void jne(int value) {
		  int result = exactAddress(value);
		  
		  if(registers.getCCElementByBit(0) == true) {
			  registers.increasePCByOne();
		  }else {
			  registers.setPC(result);
		  }
	  }
	  
	  public void jcc(int value) {
		  int r = (value >> 8) & 3;
		  r = 3 - r;
		  int result = exactAddress(value);
		  
		  if(registers.getCCElementByBit(r) == true) {
			  registers.setPC(result);
		  } else {
			  registers.increasePCByOne();
		  }
	  }
	  public void jma(int value) {
		  int result = exactAddress(value);
		  registers.setPC(result);
		  System.out.println("JMA");
	  }
	  public void setcce(int value) {
		  int r = (value >> 8) & 3;
		  int register = registers.getRnByNum(r);
		  int cc = registers.getCC();
		  
		  if(register == 0) {
			registers.setCC(cc | 1);  
		  }else {
			  registers.setCC(cc & 14);   
		  }
		  System.out.println("SetCCE");
	  }

		  
	  
	   public void AMR(int value)
	  {
		  int y = exactAddress(value);
		 // System.out.println("what is y: " + y);
		  int x = memory.getMemory(y);
		  System.out.println("what is x: " + x);
		  int register = (value >> 8) & 3;
		 // //System.out.println("Using register: " + register);
		  int result = registers.getRnByNum(register);
		  result = result + x;
		  registers.setRnByNum(register, result);
		 
	  }
	  public void SMR(int value)
	  {
		  int y = exactAddress(value);
		  //System.out.println("what is y: " + y);
		  int x = memory.getMemory(y);
		 // System.out.println("what is x: " + x);
		  int register = (value >> 8) & 3;
		  //System.out.println("Using register: " + register);
		  int result = registers.getRnByNum(register);
		  result = result - x;
		  registers.setRnByNum(register, result);
		  
	  }
	  public void AIR(int value)
	  {
		  int y = exactAddress(value);
		  //int x = memory.getMemory(y);
		 //System.out.println("what is x: " + x);
		  int register = (value >> 8) & 3;
		  //System.out.println("Using register: " + register);
		  if(y != 0)
		  {
			  int regx = registers.getRnByNum(register);
			  if(regx == 0)
			  {
				  registers.setRnByNum(register, (y));
			  }
			  else
			  {
				  regx = regx + y;
				  registers.setRnByNum(register, regx);
			  }
		  }
		 
	  }
	  public void SIR(int value)
	  {
		  int y = exactAddress(value);
		 // int x = memory.getMemory(y);
		 // System.out.println("what is x: " + x);
		  int register = (value >> 8) & 3;
		  //System.out.println("Using register: " + register);
		  if(y != 0)
		  {
			  int regx = registers.getRnByNum(register);
			  if(regx == 0)
			  {
				  registers.setRnByNum(register, (-y));
			  }
			  else
			  {
				  regx = regx - y;
				  registers.setRnByNum(register, regx);
			  }
		  }
		
	  }
	  
	  
	  public void MLT(int value)
	  {
		  int regx = (value >> 7) & 3;
		  int regy = (value >> 7) & 3;
		 
		  if((regx == 0)||(regx == 2)&&((regy == 0)||(regy == 2)))
		  {
			  int result = registers.getRnByNum(regx) * registers.getRnByNum(regy);
			  if(result > Integer.MAX_VALUE || result < Integer.MIN_VALUE)
			  {
				  registers.setCCElementByBit(Const.ConditionCode.OVERFLOW(0));
			  }
			  else
			  {
				  registers.setRnByNum(regx, (result >> 16));
				  registers.setRnByNum(regx+1, (result & 0xFFF));
			  }
		  }
	  }
	  
	  
	  public void dvd(int value) {
		int rx = (value >> 7) & 3;
		int ry = (value >> 5) & 3;
		if (rx >=3 || ry >= 3) {
			// TODO: throw exceptions
			return;
		}
		int register1 = registers.getRnByNum(rx);
		int register2 = registers.getRnByNum(ry);
		if (register2 == 0){
			int cc = registers.getCC();
			registers.setCC(cc | 2);
			return;
		}
		int quotient = register1 / register2;
		int remainder = register1 % register2;
		registers.setRnByNum(rx,quotient);
		registers.setRnByNum((rx+1), remainder);

	}

	public void trr(int value) {
		int rx = (value >> 7) & 3;
		int ry = (value >> 5) & 3;
		int register1 = registers.getRnByNum(rx);
		int register2 = registers.getRnByNum(ry);
		int cc = registers.getCC();
		if (register1 == register2) {
			registers.setCC(cc | 1);
		} else {
			registers.setCC((cc | 1) - 1);
		}
	}


	public void and(int value) {
		int rx = (value >> 7) & 3;
		int ry = (value >> 5) & 3;
		int register1 = registers.getRnByNum(rx);
		int register2 = registers.getRnByNum(ry);

		registers.setRnByNum(rx, register1 & register2);
	}

	public void orr(int value) {
		int rx = (value >> 7) & 3;
		int ry = (value >> 5) & 3;
		int register1 = registers.getRnByNum(rx);
		int register2 = registers.getRnByNum(ry);

		registers.setRnByNum(rx, register1 | register2);
	}

	public void not(int value) {
		int rx = (value >> 7) & 3;
		int register1 = registers.getRnByNum(rx);
		registers.setRnByNum(rx, (~register1) & 0xFFFF);
	}
	
	public void jsr(int value) {
		int ix = (value >> 8) & 3;
		int address = exactAddress(value);

		// Save the return address (PC + 1) in R3
		registers.setR3(registers.getPC() + 1);

		// Set the Program Counter (PC) to the effective address
		registers.setPC(address);
	}
	public void rfs(int value) {
		int immed = (value >> 11) & 31; // Extract the immediate value from the instruction

		// Set R0 to the immediate value
		registers.setR0(immed);

		// Set PC to the value stored in R3
		registers.setPC(registers.getR3());
	}

	public void in(int value) {
		int r = (value >> 7) & 3;
		int devID =  value & 31;
		if (devID == Const.DevId.KEYBOARD.getValue()) {
			String buffer = memory.getKeyboardContent();

			if (buffer != null && buffer.length() > 0) {

				int val = buffer.charAt(0);
				registers.setRnByNum(r, val);
				memory.setKeyboardContent(buffer.substring(1, buffer.length()));

			} else {
				registers.setRnByNum(r, 0);
			}

		}
	}

	public void out(int value) {
		int r = (value >> 7) & 3;
		int devID =  value & 31;
		//int register1 = registers.getRnByNum(r);
		int val = registers.getRnByNum(r);
		char c = (char) val;
		memory.setKeyboardContent(String.valueOf(c));
	}

	public void chk(int value) {
		int r = (value >> 7) & 3;
		int devID =  value & 31;
		if (devID == Const.DevId.KEYBOARD.getValue()) {
			registers.setRnByNum(r, 1);
		}
		if (devID == Const.DevId.CARD.getValue() || devID == Const.DevId.PRINTER.getValue()) {
			registers.setRnByNum(r, 1);
		}
	}
	    /*
	    public void sob(int value) {
	        int r = (value >> 6) & 3;
	        int ix = (value >> 8) & 3;
	        int i = (value >> 10) & 1;
	        int address = (value >> 11) & 31;

	        int effectiveAddress = EffectiveAddress.calculateEA(ix, address, i, memory, registers);

	        // Decrement register value
	        int currRegValue = registers.getRnByNum(r);
	        registers.setRnByNum(r, currRegValue - 1);

	        // Check if the decremented value is greater than 0
	        if (currRegValue > 0) {
	            // Jump to effective address
	            registers.setPC(effectiveAddress);
	        } else {
	            // Continue to the next instruction
	            registers.increasePCByOne();
	        }

	    }
	    
	    public void jge(int value) {
	        int r = (value >> 6) & 3;
	        int ix = (value >> 8) & 3;
	        int i = (value >> 10) & 1;
	        int address = (value >> 11) & 31;

	        int effectiveAddress = EffectiveAddress.calculateEA(ix, address, i, memory, registers);

	        // Check if the value in the specified register is greater than or equal to 0
	        if (registers.getRnByNum(r) >= 0) {
	            // Jump to the effective address
	            registers.setPC(effectiveAddress);
	        } else {
	            // Continue to the next instruction
	            registers.increasePCByOne();
	        }
	    }*/
	  
}
