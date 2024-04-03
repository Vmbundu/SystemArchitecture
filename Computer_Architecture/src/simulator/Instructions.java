package simulator;

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
		  int r = (value >> 6) & 3;
		  int result = exactAddress(value);
		  result = memory.getMemory(result);
		  
		  registers.setXnByNum(r, result);
		  System.out.println("Loaded X Register");
		  System.out.println("Register 2 value: "+ Integer.toString(registers.getX2()));
	  }
	  
	//Store Instruction for General Purpose Register
	  public void stx(int value) {
		  int r = (value >> 6) & 3;
		  int result = exactAddress(value);
		  
		  memory.addMemory(result, registers.getXnByNum(r));
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
			  registers.increasePCByOne();
		  }else {
			  registers.setCC(result);
		  }
	  }
	 
	  public void jne(int value) {
		  int result = exactAddress(value);
		  
		  if(registers.getCCElementByBit(0) == true) {
			  registers.setCC(result);
		  }else {
			  registers.increasePCByOne();
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
	  }
 ///NEEDS to BE TESTED 
	  /*
	   public void AMR(int value)
	  {
		  int y = exactAddress(value);
		  int x = memory.getMemory(y);
		  int register = (value >> 8) & 3;
		  System.out.println("Using register: " + register);
		  int result = registers.getRnByNum(register);
		  result = result + x;
		  registers.setRnByNum(register, result);
		  registers.increasePCByOne();
	  }
	  public void SMR(int value)
	  {
		  int y = exactAddress(value);
		  int x = memory.getMemory(y);
		  int register = (value >> 8) & 3;
		  System.out.println("Using register: " + register);
		  int result = registers.getRnByNum(register);
		  result = result - x;
		  registers.setRnByNum(register, result);
		  registers.increasePCByOne();
	  }
	  public void AIR(int value)
	  {
		  int y = exactAddress(value);
		  int x = memory.getMemory(y);
		  int register = (value >> 8) & 3;
		  System.out.println("Using register: " + register);
		  if(x != 0)
		  {
			  int regx = registers.getRnByNum(register);
			  if(regx == 0)
			  {
				  registers.setRnByNum(register, (x));
			  }
			  else
			  {
				  regx = regx + x;
				  registers.setRnByNum(register, regx);
			  }
		  }
		  registers.increasePCByOne();
	  }
	  public void SIR(int value)
	  {
		  int y = exactAddress(value);
		  int x = memory.getMemory(y);
		  int register = (value >> 8) & 3;
		  System.out.println("Using register: " + register);
		  if(x != 0)
		  {
			  int regx = registers.getRnByNum(register);
			  if(regx == 0)
			  {
				  registers.setRnByNum(register, (-x));
			  }
			  else
			  {
				  regx = regx - x;
				  registers.setRnByNum(register, regx);
			  }
		  }
		  registers.increasePCByOne();
	  }
	  */
	  
	  
	  //Using the register number the DVD function gets the number in the register and divides
	  public void DVD(int regist1, int regist2)
	  {
		  int regx = registers.getRnByNum(regist1);
		  int regy = registers.getRnByNum(regist2);
		  if((regx == 0)||(regx == 2)&&((regy == 0)||(regy == 2)))
		  {
		  	if(regy == 0)
		  	{
		  		//divide by 0
		  	}
		  	else 
		  	{
		 		int result  = regx / regy;
		  		if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE)
		  		{
		  			int remainder = registers.getRnByNum(regx) % registers.getRnByNum(regy);
		  			registers.setRnByNum(regist1, result);
		  			registers.setRnByNum((regist1+1), remainder);
		  		}
		  		else
		  		{
		  			registers.setRnByNum((regist1), result);
		  		}
		  	}
		  }
		  registers.increasePCByOne();
	
	  }
	  
	  public void MLT(int regist1, int regist2)
	  {
		  int regx = registers.getRnByNum(regist1);
		  int regy = registers.getRnByNum(regist2);
		  if((regx == 0)||(regx == 2)&&((regy == 0)||(regy == 2)))
		  {
			  int result = regx * regy;
			  registers.setRnByNum(regist1, result);
		  }
		  registers.increasePCByOne();
	  }
	  
	  public void AND(int regist1, int regist2)
	  {
		  int regx = registers.getRnByNum(regist1);
		  int regy = registers.getRnByNum(regist2);
		  int result = regx | regy;
		  registers.setRnByNum(regist1, result);
		  registers.increasePCByOne();
	  }
   		//Tara: the not function need to limit the length of binary string.
	  public void NOT(int regist1)
	  {
		  int regx = registers.getRnByNum(regist1);
		  regx = ~regx;
		  registers.setRnByNum(regist1, regx);
		  registers.increasePCByOne();
	  }
	  public void ORR(int regist1, int regist2)
	  {
		  int regx = registers.getRnByNum(regist1);
		  int regy = registers.getRnByNum(regist2);
		  int result = regx & regy;
		  registers.setRnByNum(regist1, result);
		  registers.increasePCByOne();
	  }
	  public void TRR(int regist1, int regist2)
	  {
		  int regx = registers.getRnByNum(regist1);
		  int regy = registers.getRnByNum(regist2);
		  if(regx == regy)
		  {
			  registers.setCC(1);
		  }
		  else
		  {
			  registers.setCC(0);
		  }
		  registers.increasePCByOne();
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
