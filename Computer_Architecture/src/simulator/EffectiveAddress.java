public class EffectiveAddress {
   public EffectiveAddress() {
   }
   
   public static int EA(String instruction, MCU mcu, Registers registers) {
      
	  int address = StringUtil.binaryToDecimal(instruction.substring(11, 16));
      int i = StringUtil.binaryToDecimal(instruction.substring(10, 11));
      int ix = StringUtil.binaryToDecimal(instruction.substring(8, 10));
      if (i == 0) {
         return ix == 0 ? address : address + registers.getXnByNum(ix);
      } else if (i == 1) {
         if (ix == 0) {
            registers.setMAR(address);
            registers.setMBR(mcu.fetchFromCache(registers.getMAR()));
         } else {
            registers.setMAR(address + registers.getXnByNum(ix));
            registers.setMBR(mcu.fetchFromCache(registers.getMAR()));
         }

         return registers.getMBR();
      } else {
         return 0;
      }
   }

   public static int calculateEA(int ix, int address, int i, MCU mcu, Registers registers) throws MachineFaultException {
      if (i == 0) {
         if (ix == 0) {
            if (checkMachineFault(address, mcu) == 1) {
               return address;
            }
         } else if (checkMachineFault(address + registers.getXnByNum(ix), mcu) == 1) {
            return address + registers.getXnByNum(ix);
         }
      } else if (i == 1) {
         if (ix == 0) {
            if (checkMachineFault(address, mcu) == 1) {
               registers.setMAR(address);
               registers.setMBR(mcu.fetchFromCache(registers.getMAR()));
            }
         } else if (checkMachineFault(address + registers.getXnByNum(ix), mcu) == 1) {
            registers.setMAR(address + registers.getXnByNum(ix));
            registers.setMBR(mcu.fetchFromCache(registers.getMAR()));
         }

         return registers.getMBR();
      }

      return 0;
   }

   public static int checkMachineFault(int address, MCU mcu) throws MachineFaultException {
      if (address < 6) {
         throw new MachineFaultException(FaultCode.ILL_MEM_RSV.getValue(), FaultCode.ILL_MEM_RSV.getMessage());
      } else if (address > mcu.getCurrentMemorySize() - 1) {
         throw new MachineFaultException(FaultCode.ILL_MEM_BYD.getValue(), FaultCode.ILL_MEM_BYD.getMessage());
      } else {
         return 1;
      }
   }
   */
}
