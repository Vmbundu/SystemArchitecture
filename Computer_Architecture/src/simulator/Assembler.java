package simulator;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

//Main class where the main method of the Assembler is housed
public class Assembler {
    //Common Variables in the Class:
    //r: General purpose register
    //i: indirect addressing
    //ix: index register
    //adr: address
    //opcode: opcode
    //ind: indirect addressing
    //imed: intermediate
    //code: code for a Trap instruction


    //Index variable
    public static int index = 0;
    //Stat if a halt has been presented or not
    public static boolean cont = true;

    //Method for the LOC instruction
    public static String loc(String[] arr){
        index = Integer.parseInt(arr[1]);
        String numstr = Integer.toOctalString(index);

        String indexstr = Integer.toOctalString(index);
        int indexZero = 6 - indexstr.length();
        String result = "0".repeat(6)+"\n";
        //String result = "0".repeat(indexZero)+indexstr+"      "+ "0".repeat(6)+"\n";
        return result;
    }

    //Method for Data instruction
    public static String data(String[] arr){
        int numstr = 1024;
        if (arr[1].equals("End") == false) {
            numstr = Integer.parseInt(arr[1]);
        }
        String value = Integer.toOctalString(numstr);
        String indexstr = Integer.toOctalString(index);
        int indexZero = 6 - indexstr.length();
        int valueZero = 6 - value.length();
        String result = "0".repeat(valueZero)+value+"\n";
        //String result = "0".repeat(indexZero)+indexstr+"      "+ "0".repeat(valueZero)+value+"\n";
        return result;
    }

    //Method for Halt instruction
    public static String hlt(){
        //output.write("000000 000000;%n");
        String result = "000000;\n";
        return result;
    }

    //Method for data instructions with up to 4 parameters
    public static String method_one(String[] arr, int opcode){
        System.out.println("method_one: " + arr[0] + " " + arr[1]);
        String [] para = arr[1].split(",");
        int r = Integer.parseInt(para[0]);
        int ix = Integer.parseInt(para[1]);
        int adr = Integer.parseInt(para[2]);
        int ind = 0;

        if (para.length == 4){
            ind = Integer.parseInt(para[3]);
        }

        int result = (opcode << 2) | r;
        result = (result << 2) | ix;
        result = (result << 1) | ind;
        result = (result << 5) | adr;

        String value = Integer.toOctalString(result);

        int valueZero = 6 - value.length();
        String resultStr = "0".repeat(valueZero)+value+"\n";
        return resultStr;
    }

    //Method for data instructions up to 3 parameters
    public static String method_two(String[] arr, int opcode){
        System.out.println("method_two: " + arr[0] + " " + arr[1]);
        String [] para = arr[1].split(",");
        int ix = Integer.parseInt(para[0]);
        int adr = Integer.parseInt(para[1]);
        int ind = 0;

        if (para.length == 3){
            ind = Integer.parseInt(para[2]);
        }

        int result = opcode;
        result = (result << 4) | ix;
        result = (result << 1) | ind;
        result = (result << 5) | adr;

        String value = Integer.toOctalString(result);

        int valueZero = 6 - value.length();
        String resultStr = "0".repeat(valueZero)+value+"\n";
        return resultStr;
    }


    //Method for RFS instruction
    public static String rfs(String[] arr, int opcode){
        System.out.println("rfs: " + arr[0] + " " + arr[1]);
        int imed = Integer.parseInt(arr[1]);

        int result = opcode;
        result = (result << 10) | imed;

        String value = Integer.toOctalString(result);

        int valueZero = 6 - value.length();

        String resultStr = "0".repeat(valueZero)+value+"\n";
        return resultStr;
    }

    //Method for setcce instruction
    public static String setcce(String[] arr, int opcode){
        int r = Integer.parseInt(arr[1]);

        int result = opcode;
        result = (result << 2) | r;
        result = (result << 8);

        String value = Integer.toOctalString(result);
        int valueZero = 6 - value.length();
        String resultStr = "0".repeat(valueZero)+value+"\n";
        return resultStr;
    }

    public static String operations(String[] arr, int opcode){
        System.out.println("operations: " + arr[0] + " " + arr[1]);
        int rx = 0;
        int ry = 0;
        String [] para = arr[1].split(",");
        rx = Integer.parseInt(para[0]);
        if (para.length == 2){
            ry = Integer.parseInt(para[1]);
        }
        int result = opcode;
        result = (result << 2) | rx;
        result = (result << 2) | ry;
        result = (result << 6);
        String value = Integer.toOctalString(result);
        int valueZero = 6 - value.length();

        String resultStr = "0".repeat(valueZero)+value;
        return resultStr;
    }

    //Method for shift operations
    public static String shift(String[] arr, int opcode){
        System.out.println("shift: " + arr[0] + " " + arr[1]);
        String [] para = arr[1].split(",");
        int r = Integer.parseInt(para[0]);
        int count = Integer.parseInt(para[1]);
        int lr = Integer.parseInt(para[2]);
        int al = Integer.parseInt(para[3]);


        int result = opcode;
        result = (result << 2) | r;
        result = (result << 1) | al;
        result = (result << 1) | lr;
        result = (result << 6) | count;

        String value = Integer.toOctalString(result);

        int valueZero = 6 - value.length();

        String resultStr = "0".repeat(valueZero)+value;
        return resultStr;
    }

    //Method for Memory/Register instructions
    public static String logicalInstruction(String[] arr, int opcode){
        System.out.println("logical: " + arr[0] + " " + arr[1]);
        String [] para = arr[1].split(",");
        int r = Integer.parseInt(para[0]);
        int ix = Integer.parseInt(para[1]);
        int adr = Integer.parseInt(para[2]);
        int ind = 0;

        if (para.length == 4){
            ind = Integer.parseInt(para[3]);
        }

        int result = (opcode << 2) | r;
        result = (result << 2) | ix;
        result = (result << 1) | ind;
        result = (result << 5) | adr;

        String value = Integer.toOctalString(result);

        int valueZero = 6 - value.length();
        String resultStr = "0".repeat(valueZero)+value;
        return resultStr;
    }

    //Methods for instructions with only two parameter
    public static String method_three(String[] arr, int opcode){
        System.out.println("method_three: " + arr[0] + " " + arr[1]);
        String [] para = arr[1].split(",");
        int r = Integer.parseInt(para[0]);
        int adr = Integer.parseInt(para[1]);

        int result = opcode;
        result = (result << 2) | r;
        result = (result << 8) | adr;

        String value = Integer.toOctalString(result);
        int valueZero = 6 - value.length();

        String resultStr = "0".repeat(valueZero)+value;
        return resultStr;
        //System.out.println("0".repeat(indexZero)+indexstr+"      "+ "0".repeat(valueZero)+value);
    }

    public static String io_operations(String[] arr, int opcode){
        //Method for i/o instructions
        String [] para = arr[1].split(",");
        int r = Integer.parseInt(para[0]);
        int devid = Integer.parseInt(para[1]);

        int result = opcode;
        result = (result << 2) | r;
        result = (result << 8) | devid;

        String value = Integer.toOctalString(result);
        int valueZero = 6 - value.length();
        String resultStr = "0".repeat(valueZero)+value+"\n";
        return resultStr;

    }

    //Method for Trap instruction
    public static String trap(String[]arr, int opcode) {
        int code = Integer.parseInt(arr[1]);
        int result = opcode;
        result = (result << 10)| code;

        String value = Integer.toOctalString(result);
        String indexstr = Integer.toOctalString(index);

        int indexZero = 6 - indexstr.length();
        int valueZero = 6 - value.length();
        String resultStr = "0".repeat(indexZero)+indexstr+"      "+ "0".repeat(valueZero)+value+"\n";
        return resultStr;
    }

    //Main method for the Assembler
    /*public static void main(String [] args) throws Exception
    {
        //Listings of load/store instructions
        HashMap<String, String> loadStore = new HashMap<String, String>();
        //Instructions for four parameters
        loadStore.put("LDR", "01");
        loadStore.put("STR", "02");
        loadStore.put("LDA", "03");
        loadStore.put("STR", "02");
        loadStore.put("JCC", "10");
        loadStore.put("SOB", "14");
        loadStore.put("JGE", "15");
        //Instructions for three parameters
        loadStore.put("JZ", "06");
        loadStore.put("JNE", "07");
        loadStore.put("JMA", "11");
        loadStore.put("JSR", "12");
        loadStore.put("LDX", "04");
        loadStore.put("STX", "05");

        //Listing of Arithmetic/Logical instructions
        HashMap<String, String> arithLogs = new HashMap<String, String>();
        //Memory/Register exchange instructions
        arithLogs.put("AMR", "16");
        arithLogs.put("SMR", "17");
        //Instructions with Intermediates
        arithLogs.put("AIR","20");
        arithLogs.put("SIR", "21");
        //Instructions for register to register operations
        arithLogs.put("MLT", "22");
        arithLogs.put("DVD", "23");
        arithLogs.put("TRR", "24");
        arithLogs.put("AND", "25");
        arithLogs.put("ORR", "26");
        arithLogs.put("NOT", "27");
        //Instructions for shift/rotation operations
        arithLogs.put("SRC", "30");
        arithLogs.put("RRC", "31");

        //Listing of Input/Output instructions
        HashMap<String, String> inputOutput = new HashMap<String, String>();
        //Methods with 2 parameters(Input/Output Operations)
        inputOutput.put("IN", "32");
        inputOutput.put("OUT", "33");
        inputOutput.put("CHK", "34");
        //Methods for floating points
        HashMap<String, String> floating = new HashMap<String, String>();
        floating.put("FADD", "35");
        floating.put("FSUB", "36");
        floating.put("VADD", "37");
        floating.put("VSUB", "40");
        floating.put("CNVRT", "41");
        floating.put("LDFR", "42");
        floating.put("STFR", "43");

        //File paths for input and output files respectively
        //Ingests the input file
        File inFile = new File(args[0]);
        File outFile = new File(args[1]);

        //Object for Input Output instruction
        InputOutput inputOut = new InputOutput();

        // Creating an object of BufferedReader class
        BufferedReader br
                = new BufferedReader(new FileReader(inFile));

        //Creating a FileWriter object
        FileWriter output = new FileWriter(outFile, true);


        //output.write("000000 000000;%n");
        //output.write("000000      000000;\n");

        String str;


        //Loop through the lines of an input file
        while ((str = br.readLine()) != null){
            str = str.replace("End:", "");
            str = str.trim();
            //System.out.println(str);
            String [] arr = str.split("\\s+");


            switch(arr[0]){
                case "LOC":
                    loc(arr);
                    break;
                case "Data":
                    data(arr);
                    index = index + 1;
                    break;
                case "RFS":
                    rfs(arr, Integer.parseInt("13", 8));
                    index = index + 1;
                    break;
                case "SETCCE":
                    setcce(arr, Integer.parseInt("44", 8));
                    index = index + 1;
                    break;
                case "TRAP":
                    trap(arr, Integer.parseInt("45", 8));
                    index = index + 1;
                    break;
                case "HLT":
                    hlt();
                    index = 0;
                    System.out.println("Code Ran!");
                    break;
                default:
                    break;

            }

            if (loadStore.get(arr[0]) != null){
                String op = loadStore.get(arr[0]);
                //Load/Store instructions with at most 4 parameters
                if(op == "06" || op == "11" || op == "12" || op == "04" || op == "05" || op == "07"){
                    method_two(arr, Integer.parseInt(op,8));
                    index = index + 1;
                } else {
                    //Load/Store instructions with at most 3 parameters
                    method_one(arr, Integer.parseInt(op, 8));
                    index = index + 1;
                }

            }

            if (arithLogs.get(arr[0]) != null){
                String op = arithLogs.get(arr[0]);
                String resultStr;
                //Memory/Register exchange instructions
                if(op == "16" || op == "17"){
                    resultStr = logicalInstruction(arr, Integer.parseInt(op,8));
                    index = index + 1;
                    //Intermediate to Register instructions
                }else if(op == "20" || op == "21") {
                    resultStr = method_three(arr, Integer.parseInt(op, 8));
                    index = index + 1;
                    //Shift operation instructions
                }else if(op == "30" || op == "31") {
                    resultStr = shift(arr, Integer.parseInt(op, 8));
                    index = index + 1;
                    //Register to Register operation instructions
                } else {
                    resultStr = operations(arr, Integer.parseInt(op, 8));
                    index = index + 1;
                }

            }

            //Input/Output instructions
            if (inputOutput.get(arr[0]) != null){
                String op = inputOutput.get(arr[0]);
                inputOut.io_operations(arr, Integer.parseInt(op,8), output, index);
                index = index + 1;
                //Floating Point instructions
            }else if(floating.get(arr[0]) != null){
                String op = floating.get(arr[0]);
                inputOut.floating_point(arr, Integer.parseInt(op, 8), output, index);
                index = index + 1;
            }

            //If the index is larger than 1024, and error is pushed
            if(index > 1024) {
                //System.out.println("Error!");

            }
        }
        output.close();
    }*/
}

