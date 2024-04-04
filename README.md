# USER GUIDE CSCI 6461
### TEAM 02
 
The following user guide is for a Simulator developed by Team 02.
### Content of zip file
Source Code(Simulator.java, Compution.java)
Design Notes
User Guide
Requirement
·       Java
## Starting the Program
There are two way to start the program
·       Through the terminal
·       Through File Explorer. Directly Click the Highlighted Jar File in your File Explorer(similar to the one pictured below)

 
## Running the Code/Start-Up
Picture above is the simulator on start-up. All the button and input fields needed are available from the start. The frame must be in full screen to see all available options. The application consists of Text Fields and Buttons. Some Text fields have accompanying buttons such as…
- GR0 – GR3 (General Purpose Registers)
- XR1-XR3 (Index Registers)
- PC (Program Counter)
- MAR (Memory Address Register)
- MBR (Memory Buffer Register)
## Loading Data
Data can be inserted into any of the Text Fields within the gray region. In order to do, one must first input their value of interest into the “Octal Value” input field and then press “Enter”. It is important to mention that the input field only accepts values in octal form. After pressing “Enter”, you can see the result in the Binary input in binary form.
Now that the value is in the Binary field, you can place it into any one of aforementioned fields simply by pressing its corresponding button. For example, in order to put the value in General Register 0(GRO), simply just select the “GR0”. The value will then appear in that space.
## Store Data Into Memory
In order to store data into memory, you must first detail the memory location you want it to be in. This is done by entering an octal value and then loading that value into the MAR input field (refer to the last section for details). Do the same for the MBR field. Afterwards, press the “Store” button. Now, you have inserted a value into memory! Now we will show you an example.
Here, we will be inserting the value 5 into memory location 3.
- Insert 3 into the “Octal Value” and press “Enter”
- Click the MAR button
- Insert 5 into the “Octal Value” and Press Enter
- Click the “MBR” button
If your screen looks like this, you can now press “Store”. You have successfully entered data into memory!
Loading Data From Memory
Data must first be stored into data before it can be loaded back out. In order to load data from memory. Simply enter the memory address you want to get content from in the “Octal Value”, hit “Enter”, and then hit the “MAR” button. Finally, once the value is in the “MAR” input field, hit the “Load” button. The contents of the memory address should appear in the “MBR” input field.
## Inserting an Input File
 The input file should usually be the outcome of an assembler (Look at the user guide for the Assembler). When you click the IPL button, a file explorer will appear. While there, search for your input file. This program only accepts input files with the .txt extension. Once the file is open, you will see the first instruction is computed and visualized in the simulator, this is to ensure that the file has been read and loaded into data.
File Explorer
The use “Run” or “Step”, first put a memory address into the PC input field. Now when you click the “Step” button, the locations in the PC field will extract the contents from the location and execute it. If you use the “Run” button, you will notice that the simulator will run every line until it reaches a HLT instruction. While, instructions are actively running, you can click the “HLT” button to stop the code from running suddenly, with the last executed instruction displayed.
   
