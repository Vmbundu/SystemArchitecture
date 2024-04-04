package simulator.util;

import java.util.HashMap;

//import jdk.nashorn.internal.runtime.regexp.joni.constants.OPCode;

public class Const {

    public static final Integer MEMORY_WORDS_BOUND = 2048;
    public static final Integer MEMORY_WORDS_BOUND_EXPAND = 4096;
    public static final Integer CACHE_LINES = 16;
    public static final Integer BOOT_PROG_BASE = 8;

    /**
     * 0 - OVERFLOW</br>
     * 1 - UNDERFLOW</br>
     * 2 - DIVZERO</br>
     * 3 - EQUALORNOT
     */
    public enum ConditionCode {
        OVERFLOW(0), UNDERFLOW(1), DIVZERO(2), EQUALORNOT(3);
        int value;

        private ConditionCode(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }

    /**
     * Machine Fault</br>
     * 0 - ILL_MEM_RSV: Illegal Memory Address to Reserved Locations</br>
     * 1 - ILL_TRPC: Illegal TRAP code</br>
     * 2 - ILL_OPRC: Illegal Operation Code</br>
     * 3 - ILL_MEM_BYD: Illegal Memory Address beyond 2048 (memory installed)
     */
    public enum FaultCode {
        ILL_MEM_RSV(0, "Illegal Memory Address to Reserved Locations"), ILL_TRPC(1, "Illegal TRAP code"), ILL_OPRC(2,
                "Illegal Operation Code"), ILL_MEM_BYD(3, "Illegal Memory Address beyond 2048 (memory installed)");
        int value;
        String messsage;

        private FaultCode(int value, String message) {
            this.value = value;
            this.messsage = message;
        }

        public int getValue() {
            return this.value;
        }

        public String getMessage() {
            return this.messsage;
        }
    }

    public enum DevId {
        KEYBOARD(0), PRINTER(1), CARD(2);
        int value;

        private DevId(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }

    /**
     * 000000 - HLT</br>
     * 000001 - LDR</br>
     * 000010 - STR</br>
     * 000011 - LDA</br>
     * 101001 - LDX</br>
     * 101010 - STX</br>
     */
    public static final HashMap<String, String> OPCODE = new HashMap<String, String>();
    static {
        //Instructions for four parameters

        //OPCODE.put("HLT", "00");
        OPCODE.put("LDR", "01");
        OPCODE.put("STR", "02");
        OPCODE.put("LDA", "03");
        OPCODE.put("LDX", "04");
        OPCODE.put("STX", "05");

        OPCODE.put("JZ", "06");
        OPCODE.put("JNE", "07");
        OPCODE.put("JCC", "10");
        OPCODE.put("JMA", "11");
        OPCODE.put("JSR", "12");
        OPCODE.put("RFS", "13");
        OPCODE.put("SOB", "14");
        OPCODE.put("JGE", "15");

        //Listing of Arithmetic/Logical instructions
        //Memory/Register exchange instructions
        OPCODE.put("AMR", "16");
        OPCODE.put("SMR", "17");
        //Instructions with Intermediates
        OPCODE.put("AIR","20");
        OPCODE.put("SIR", "21");
        //Instructions for register to register operations
        OPCODE.put("MLT", "22");
        OPCODE.put("DVD", "23");
        OPCODE.put("TRR", "24");
        OPCODE.put("AND", "25");
        OPCODE.put("ORR", "26");
        OPCODE.put("NOT", "27");


        //Instructions for shift/rotation operations
        OPCODE.put("SRC", "30");
        OPCODE.put("RRC", "31");

        //Listing of Input/Output instructions
        //Methods with 2 parameters(Input/Output Operations)
        OPCODE.put("IN", "32");
        OPCODE.put("OUT", "33");
        OPCODE.put("CHK", "34");
        //Methods for floating points
        OPCODE.put("FADD", "35");
        OPCODE.put("FSUB", "36");
        OPCODE.put("VADD", "37");
        OPCODE.put("VSUB", "40");
        OPCODE.put("CNVRT", "41");
        OPCODE.put("LDFR", "42");
        OPCODE.put("STFR", "43");
        //OPCODE.put("SETCCE", "44");
        //OPCODE.put("TRAP", "45");

        /*OPCODE.put(NumeralConvert.OctalToBinary("0", 6), "HLT");
        OPCODE.put(NumeralConvert.OctalToBinary("1", 6), "LDR");
        OPCODE.put(NumeralConvert.OctalToBinary("2", 6), "STR");
        OPCODE.put(NumeralConvert.OctalToBinary("3", 6), "LDA");
        OPCODE.put(NumeralConvert.OctalToBinary("4", 6), "LDX");
        OPCODE.put(NumeralConvert.OctalToBinary("5", 6), "STX");

        OPCODE.put(NumeralConvert.OctalToBinary("6", 6), "JZ");
        OPCODE.put(NumeralConvert.OctalToBinary("7", 6), "JNE");
        OPCODE.put(NumeralConvert.OctalToBinary("10", 6), "JCC");
        OPCODE.put(NumeralConvert.OctalToBinary("11", 6), "JMA");
        OPCODE.put(NumeralConvert.OctalToBinary("12", 6), "JSR");
        OPCODE.put(NumeralConvert.OctalToBinary("13", 6), "RFS");
        OPCODE.put(NumeralConvert.OctalToBinary("14", 6), "SOB");
        OPCODE.put(NumeralConvert.OctalToBinary("15", 6), "JGE");

        OPCODE.put(NumeralConvert.OctalToBinary("16", 6), "AMR");
        OPCODE.put(NumeralConvert.OctalToBinary("17", 6), "SMR");
        OPCODE.put(NumeralConvert.OctalToBinary("20", 6), "AIR");
        OPCODE.put(NumeralConvert.OctalToBinary("21", 6), "SIR");

        OPCODE.put(NumeralConvert.OctalToBinary("22", 6), "MLT");
        OPCODE.put(NumeralConvert.OctalToBinary("23", 6), "DVD");
        OPCODE.put(NumeralConvert.OctalToBinary("24", 6), "TRR");

        OPCODE.put(NumeralConvert.OctalToBinary("25", 6), "AND");
        OPCODE.put(NumeralConvert.OctalToBinary("26", 6), "ORR");
        OPCODE.put(NumeralConvert.OctalToBinary("27", 6), "NOT");

        OPCODE.put(NumeralConvert.OctalToBinary("30", 6), "SRC");
        OPCODE.put(NumeralConvert.OctalToBinary("31", 6), "RRC");
        OPCODE.put(NumeralConvert.OctalToBinary("32", 6), "IN");
        OPCODE.put(NumeralConvert.OctalToBinary("33", 6), "OUT");
        OPCODE.put(NumeralConvert.OctalToBinary("34", 6), "CHK");

        OPCODE.put(NumeralConvert.OctalToBinary("35", 6), "FADD");
        OPCODE.put(NumeralConvert.OctalToBinary("36", 6), "FSUB");
        OPCODE.put(NumeralConvert.OctalToBinary("37", 6), "VADD");
        OPCODE.put(NumeralConvert.OctalToBinary("40", 6), "VSUB");
        OPCODE.put(NumeralConvert.OctalToBinary("41", 6), "CNVRT");
        OPCODE.put(NumeralConvert.OctalToBinary("42", 6), "LDFR");
        OPCODE.put(NumeralConvert.OctalToBinary("43", 6), "STFR");

        OPCODE.put(NumeralConvert.OctalToBinary("44", 6), "SETCCE");
        OPCODE.put(NumeralConvert.OctalToBinary("45", 6), "TRAP");*/

    }


}
