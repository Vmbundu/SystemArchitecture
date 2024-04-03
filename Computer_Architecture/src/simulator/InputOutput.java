package simulator;
import java.io.FileWriter;
import java.io.IOException;

public class InputOutput {
    //Common Variables in the Class:
    //r: General purpose register
    //i: indirect addressing
    //ix: index register
    //adr: address
    //opcode: opcode
    //ind: indirect addressing
    //devid: Device ID
    //indexZero/valueZero: Fill out remaining zeros for index and instruction value respectively

    public InputOutput(){

    }

    //Methods for floating point operations
    public void floating_point(String[] arr, int opcode, FileWriter output, int index){
        //Method for instructions with up to 4 parameters
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
        String indexstr = Integer.toOctalString(index);

        int indexZero = 6 - indexstr.length();
        int valueZero = 6 - value.length();

        try {
            output.write("0".repeat(indexZero)+indexstr+"      "+ "0".repeat(valueZero)+value+"\n");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //System.out.println("0".repeat(indexZero)+indexstr+"      "+ "0".repeat(valueZero)+value);

    }

    //Methods for input/output operations
    public void io_operations(String[] arr, int opcode, FileWriter output, int index){
        //Method for i/o instructions
        String [] para = arr[1].split(",");
        int r = Integer.parseInt(para[0]);
        int devid = Integer.parseInt(para[1]);

        int result = opcode;
        result = (result << 2) | r;
        result = (result << 8) | devid;

        String value = Integer.toOctalString(result);
        String indexstr = Integer.toOctalString(index);

        int indexZero = 6 - indexstr.length();
        int valueZero = 6 - value.length();

        try {
            output.write("0".repeat(indexZero)+indexstr+"      "+ "0".repeat(valueZero)+value+"\n");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}