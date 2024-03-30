package simulator;

import simulator.util.NumeralConvert;
import javax.lang.model.type.NullType;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Consumer;


public class FrontPanel extends JFrame {
    private JFrame FrontPanel;

    private Registers registers;
    private JPanel pnlGnrRegisters;
    private JPanel pnlR0;
    private JPanel pnlR1;
    private JPanel pnlR2;
    private JPanel pnlR3;
    private JPanel pnlX1;
    private JPanel pnlX2;
    private JPanel pnlX3;
    private JButton bntR0;
    private JButton bntR1;
    private JButton bntR2;
    private JButton bntR3;
    private JButton bntX1;
    private JButton bntX2;
    private JButton bntX3;


    private JPanel pnlOthRegisters;
    private JPanel pnlPC;
    private JPanel pnlMAR;
    private JPanel pnlMBR;
    private JPanel pnlIR;
    private JPanel pnlMFR;
    private JPanel pnlCC;

    private JButton bntPC;
    private JButton bntMAR;
    private JButton bntMBR;
    private JButton bntMFR;
    /*private JButton bntIR;

    private JButton bntCC;*/

    private JPanel pnlButtons;
    private JButton buttonIPL;
    private JButton buttonStore;
    private JButton buttonLoad;
    private JButton buttonRun;
    private JButton buttonHalt;
    private JButton buttonStep;

    private JPanel pnlAlu;
    private JPanel pnlOpcode;
    private JButton bntExecute;
    private JPanel pnlGPR;
    private JPanel pnlIXR;
    private JPanel pnlI;
    private JPanel pnlAddress;

    private JPanel pnlFields;
    private JPanel pnlInputField;
    private JTextField inputTextField;
    private JPanel pnlOutputField;
    private JTextField outputTextField;
    private JPanel pnlCacheField;
    private JTextField cacheTextField;
    
    private File selected;
    private BufferedReader buffer;
    private Memory memory = new Memory();
    private boolean halt = false;

    public class Pair<T, U> {
        private T first;
        private U second;

        public Pair(T first, U second) {
            this.first = first;
            this.second = second;
        }

        public T getFirst() {
            return first;
        }

        public U getSecond() {
            return second;
        }

        public void setFirst(T first) {
            this.first = first;
        }

        public void setSecond(U second) {
            this.second = second;
        }
    }



    public FrontPanel() {
        FrontPanel = new JFrame();
        FrontPanel.setTitle("Front Panel");
        FrontPanel.setBounds(100, 100, 1300, 1200);
        FrontPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FrontPanel.getContentPane().setLayout(null);; // Layout to hold 2 panels
        
        this.registers = new Registers();
        registers.init();

        pnlGnrRegisters = new JPanel();
        pnlGnrRegisters.setLayout(new BoxLayout(pnlGnrRegisters, BoxLayout.Y_AXIS));
        pnlGnrRegisters.setBounds(20, 20, 600, 400);


        Pair<JPanel, JButton> resultR0 = createRegisterPanel("R0", 16, true);
        pnlR0 = resultR0.getFirst();
        bntR0 = resultR0.getSecond();
        Pair<JPanel, JButton> resultR1 = createRegisterPanel("R1", 16, true);
        pnlR1 = resultR1.getFirst();
        bntR1 = resultR1.getSecond();
        Pair<JPanel, JButton> resultR2 = createRegisterPanel("R2", 16, true);
        pnlR2 = resultR2.getFirst();
        bntR2 = resultR2.getSecond();
        Pair<JPanel, JButton> resultR3 = createRegisterPanel("R3", 16, true);
        pnlR3 = resultR3.getFirst();
        bntR3 = resultR3.getSecond();
        Pair<JPanel, JButton> resultX1 = createRegisterPanel("X1", 16, true);
        pnlX1 = resultX1.getFirst();
        bntX1 = resultX1.getSecond();
        Pair<JPanel, JButton> resultX2 = createRegisterPanel("X2", 16, true);
        pnlX2 = resultX2.getFirst();
        bntX2 = resultX2.getSecond();
        Pair<JPanel, JButton> resultX3 = createRegisterPanel("X3", 16, true);
        pnlX3 = resultX3.getFirst();
        bntX3 = resultX3.getSecond();
        /*
        pnlR1 = createRegisterPanel("R1", 16, true, true, this.bntR1);
        pnlR2 = createRegisterPanel("R2", 16, true, true, this.bntR2);
        pnlR3 = createRegisterPanel("R3", 16, true, true, this.bntR3);
        pnlX1 = createRegisterPanel("X1", 16, true, true, this.bntX1);
        pnlX2 = createRegisterPanel("X2", 16, true, true, this.bntX2);
        pnlX3 = createRegisterPanel("X3", 16, true, true, this.bntX3);*/
        pnlGnrRegisters.add(pnlR0, BorderLayout.WEST);
        pnlGnrRegisters.add(pnlR1, BorderLayout.WEST);
        pnlGnrRegisters.add(pnlR2, BorderLayout.WEST);
        pnlGnrRegisters.add(pnlR3, BorderLayout.WEST);
        pnlGnrRegisters.add(pnlX1, BorderLayout.WEST);
        pnlGnrRegisters.add(pnlX2, BorderLayout.WEST);
        pnlGnrRegisters.add(pnlX3, BorderLayout.WEST);


        pnlOthRegisters = new JPanel();
        pnlOthRegisters.setLayout(new BoxLayout(pnlOthRegisters, BoxLayout.Y_AXIS));
        pnlOthRegisters.setBounds(625, 20, 600, 400);
        //pnlPC = createRegisterPanel("PC", 12, false, true, this.bntPC);
        Pair<JPanel, JButton> resultPC = createRegisterPanel("PC", 12, true);
        pnlPC = resultPC.getFirst();
        bntPC = resultPC.getSecond();
        //pnlMAR = createRegisterPanel("MAR", 12, false,true, this.bntMAR);
        Pair<JPanel, JButton> resultMAR = createRegisterPanel("MAR", 12, true);
        pnlMAR = resultMAR.getFirst();
        bntMAR = resultMAR.getSecond();
        //pnlMBR = createRegisterPanel("MBR", 16, false,true, this.bntMBR );
        Pair<JPanel, JButton> resultMBR = createRegisterPanel("MBR", 16, true);
        pnlMBR = resultMBR.getFirst();
        bntMBR = resultMBR.getSecond();
        //pnlMFR = createRegisterPanel("MFR", 4, false, false, this.bntPC);
        Pair<JPanel, JButton> resultMFR = createRegisterPanel("MFR", 4, true);
        pnlMFR = resultMFR.getFirst();
        bntMFR = resultMFR.getSecond();
        //pnlIR = createRegisterPanel("IR", 16, false, false, this.bntPC);
        Pair<JPanel, JButton> resultIR = createRegisterPanel("IR", 16, true);
        pnlIR = resultIR.getFirst();
        //bntR0 = resultR0.getSecond();
        //pnlCC = createRegisterPanel("CC", 4, false, false, this.bntPC);
        Pair<JPanel, JButton> resultCC = createRegisterPanel("CC", 4, true);
        pnlCC = resultCC.getFirst();
        //bntR0 = resultR0.getSecond();
        pnlOthRegisters.add(pnlPC, BorderLayout.EAST);
        pnlOthRegisters.add(pnlMAR, BorderLayout.EAST);
        pnlOthRegisters.add(pnlMBR, BorderLayout.EAST);
        pnlOthRegisters.add(pnlIR, BorderLayout.EAST);
        pnlOthRegisters.add(pnlMFR, BorderLayout.EAST);
        pnlOthRegisters.add(pnlCC, BorderLayout.EAST);

        pnlButtons = new JPanel();
        pnlButtons.setLayout(new BoxLayout(pnlButtons, BoxLayout.X_AXIS));
        pnlButtons.setBounds(50, 420, 1400, 50);
        buttonIPL = new JButton("IPL");
        buttonStore = new JButton("Store");
        buttonLoad = new JButton("Load");
        buttonRun = new JButton("Run");
        buttonHalt = new JButton("Halt");
        buttonStep = new JButton("Step");
        pnlButtons.add(buttonIPL);
        pnlButtons.add(buttonStore);
        pnlButtons.add(buttonLoad);
        pnlButtons.add(buttonRun);
        pnlButtons.add(buttonHalt);
        pnlButtons.add(buttonStep);


        pnlAlu = new JPanel();
        pnlAlu.setLayout(new BoxLayout(pnlAlu, BoxLayout.X_AXIS));
        pnlAlu.setBounds(50, 520, 1400, 50);
        /*
        pnlOpcode = createOpcodePanel("Operation", 6);
        pnlGPR = createOpcodePanel("GPR", 2);
        pnlIXR = createOpcodePanel("IXR", 2);
        pnlI = createOpcodePanel("I", 2);
        pnlAddress = createOpcodePanel("Address", 5);
        pnlAlu.add(pnlOpcode);
        pnlAlu.add(pnlGPR);
        pnlAlu.add(pnlIXR);
        pnlAlu.add(pnlI);
        pnlAlu.add(pnlAddress);*/
        Pair<JPanel, JButton> resultAlu = createOpcodePanel("instructions", 16);
        pnlOpcode = resultAlu.getFirst();
        bntExecute = resultAlu.getSecond();
        pnlAlu.add(pnlOpcode);

        FrontPanel.getContentPane().add(pnlGnrRegisters);
        FrontPanel.getContentPane().add(pnlOthRegisters);
        FrontPanel.getContentPane().add(pnlButtons);
        FrontPanel.getContentPane().add(pnlAlu);


        //addStoreListener(pnlR0, bntR0, value -> registers.setR0(value));
        addStoreListener(pnlR0, bntR0, value -> registers.setR0(value),"R0");
        addStoreListener(pnlR1, bntR1, value -> registers.setR1(value), "R1");
        addStoreListener(pnlR2, bntR2, value -> registers.setR2(value), "R2");
        addStoreListener(pnlR3, bntR3, value -> registers.setR3(value),"R3");
        addStoreListener(pnlX1, bntX1, value -> registers.setX1(value),"X1");
        addStoreListener(pnlX2, bntX2, value -> registers.setX2(value),"X2");
        addStoreListener(pnlX3, bntX3, value -> registers.setX3(value), "X3");
        addStoreListener(pnlPC, bntPC, value -> registers.setPC(value), "PC");
        addStoreListener(pnlMAR, bntMAR, value -> registers.setMAR(value),"MAR");
        addStoreListener(pnlMBR, bntMBR, value -> registers.setMBR(value), "MBR");

        addIPLListener(buttonIPL);
        addStrListener(buttonStore);
        addStepListener(buttonStep);
        addLoadListener(buttonLoad);
        
        //TODO: execute instructions here
        addExecuteListener(pnlOpcode, bntExecute);

    }
    
    
    private void addTestListener(JButton testButton) {
        testButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                String[]inst = {"jz", "jne", "jcc", "jma", "setcce", "dvd", "mlt", "and", "not", "orr", "trr", "jsr","rfs"};
                String name = testButton.getName();
                switch(name) {
                	case "jz":
                		break;
                	case "jne":
                		break;
                	case "jcc":
                		break;
                	case "jma":
                		break;
                	case "setcce":
                		break;
                	case "dvd":
                		break;
                	case "mlt":
                		break;
                	case "and":
                		break;
                	case "not":
                		break;
                	case "orr":
                		break;
                	case "trr":
                		break;
                	case "jsr":
                		break;
                	case "rfs":
                		break;
                }
                display();
            }
        });
    }
    
    public void step() {
  	  Instructions instruct = new Instructions(registers, memory);
		  int pc = registers.getPC();
		  registers.setMAR(pc);
		  int mar = registers.getMAR();
		  
		  int value = memory.getMemory(mar);
		  registers.setMBR(value);
		  //Running an instruction based on its opcode 
		  int opcode = value >> 10;
		  String op = Integer.toString(opcode);
		  opcode = Integer.parseInt(op,8);
		  switch(opcode) {
		  	case 0:
		  		halt = true;
		  	case 1:
		  		instruct.ldr(value);
		  		registers.increasePCByOne();
		  		break;
		  	case 2:
		  		instruct.str(value);
		  		registers.increasePCByOne();
		  		break;
		  	case 3:
		  		instruct.lda(value);
		  		registers.increasePCByOne();
		  		break;
		  	case 4:
		  		instruct.ldx(value);
		  		registers.increasePCByOne();
		  		break;
		  	case 5:
		  		instruct.stx(value);
		  		registers.increasePCByOne();
		  		break;
		  }
		  display();
	  }
    
    private void addStepListener(JButton testButton) {
        testButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                step();
                
            }
        });
    }
    
    private Pair<JPanel, JButton> createRegisterPanel(String registerName, int bitLen, boolean left) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        if (left) {
            panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        }


        // Label for the register
        JLabel label = new JLabel(registerName);
        panel.add(label);

        // Create 16 buttons for the register bits
        /*
        for (int i = 0; i < bitLen; i++) {
            JRadioButton button = new JRadioButton("");
            button.setPreferredSize(new Dimension(25, 20)); // Small square shape
            button.setBackground(Color.WHITE);
            panel.add(button);

        }
 		*/
 
        JTextField input = new JTextField("0".repeat(registers.getBitLongByName(registerName)));
        panel.add(input);
        JButton button = new JButton("*");
        panel.add(button);;
        return new Pair<>(panel, button);
    }

    private Pair<JPanel, JButton> createOpcodePanel(String labelName, int bitLen) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        // Label for the register
        JLabel label = new JLabel(labelName);
        panel.add(label,0);

        // Create 16 buttons for the register bits
        //for (int i = 0; i < bitLen; i++) {
        
        String[]inst = {"jz", "jne", "jcc", "jma", "setcce", "dvd", "mlt", "and", "not", "orr", "trr", "jsr","rfs"};
        //Buttons available for testing instructions
        for (int i = 0; i < 12; i++) {
            //JRadioButton button = new JRadioButton();
        	Button button = new Button(inst[i]);
            button.setPreferredSize(new Dimension(25, 20)); // Small square shape
            button.setBackground(Color.WHITE);
            panel.add(button);

        }
		
        JTextField opcode = new JTextField("            ");
        JTextField binary = new JTextField("0000");
        
        panel.add(opcode,1);
        panel.add(binary,2);
        JButton executeButton = new JButton("execute");
        panel.add(executeButton,3);
        return new Pair<>(panel, executeButton);
    }

    private void addStoreListener(JPanel panel, JButton storeButton, Consumer<Integer> action, String button) {
        storeButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                StringBuffer buffer = new StringBuffer();
                String val = "";
                
                JTextField opcode = (JTextField)pnlOpcode.getComponent(2);
                String binary = opcode.getText();
                
                for (Component com : panel.getComponents()) {
                    if (com instanceof JTextField) {
                        JTextField textField = (JTextField) com;
                        textField.setText(binary);
                    }
                }
                //int value = NumeralConvert.BinaryToDecimal(buffer.toString());
                //int value = NumeralConvert.BinaryToDecimal(val);
                //textFieldX1.setText(String.valueOf(value));
                //action.accept(value); // Use the Consumer to accept the value
                //System.out.println("X1 is set to: " + buffer);
                //registers.setRegistersByName(button, value);
                //printConsole("X1 is set to: " + value);
            }
        });
    }

    private void addExecuteListener(JPanel panel, JButton storeButton) {
        storeButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
            	/*
                StringBuffer buffer = new StringBuffer();
                for (Component com : panel.getComponents()) {
                    if (com instanceof JRadioButton) {
                        JRadioButton rdb = (JRadioButton) com;
                        buffer = rdb.isSelected() ? buffer.append("1") : buffer.append("0");
                    }
                }
                */
            	JTextField opcode = (JTextField)panel.getComponent(1);
            	JTextField binary = (JTextField)panel.getComponent(2);
            	
            	String op = opcode.getText();
            	int value = Integer.parseInt(op.trim(), 8);
            	op = Integer.toBinaryString(value);
            	binary.setText(op);
                //int value = NumeralConvert.BinaryToDecimal(buffer.toString());
                //textFieldX1.setText(String.valueOf(value));
                //action.accept(value); // Use the Consumer to accept the value
                //System.out.println("instruction value: " + buffer);
            	System.out.println("Opcode Set!");
                //printConsole("X1 is set to: " + value);
            }
        });
    }

    private void addIPLListener(JButton IPLButton) {
        IPLButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                registers.init();
                System.out.println("IPL");
                try {
					
					//Code for User Selected Input txt file
					JFileChooser fileChooser = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("Instruction File", "txt");
					fileChooser.setFileFilter(filter);
					fileChooser.setCurrentDirectory(new File("."));
					
					int result = fileChooser.showOpenDialog(null);
					
					//Buffer Reader for the Input File
					if(result == JFileChooser.APPROVE_OPTION) {
						selected = fileChooser.getSelectedFile();
						buffer = new BufferedReader(new FileReader(selected));
					}
				}catch(Exception exception) {
					System.out.print("Problem");			}
				
				String str = null;
				try {
					str = buffer.readLine();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("Done");
					return;
				}
				
				str = str.trim();
				String [] arr = str.split("\\s+");
				int octInd = Integer.parseInt(arr[0],8);
				int octVal = Integer.parseInt(arr[1],8);
				
				memory.addMemory(octInd, octVal);
				while(str != null) {
					//Loop through rest of the file and adding to memory
					arr = str.split("\\s+");
					octInd = Integer.parseInt(arr[0],8);
					octVal = Integer.parseInt(arr[1],8);
					memory.addMemory(octInd, octVal);
					
					try {
						str = buffer.readLine();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
                //printConsole("X1 is set to: " + value);
            }
        });
    }

    private void addAluListener(JPanel panel, JButton storeButton, Consumer<Integer> action) {
        storeButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                StringBuffer buffer = new StringBuffer();
                for (Component com : panel.getComponents()) {
                    if (com instanceof JRadioButton) {
                        JRadioButton rdb = (JRadioButton) com;
                        buffer = rdb.isSelected() ? buffer.append("1") : buffer.append("0");
                    }
                }
                int value = NumeralConvert.BinaryToDecimal(buffer.toString());
                //textFieldX1.setText(String.valueOf(value));
                action.accept(value); // Use the Consumer to accept the value
                System.out.println("X1 is set to: " + buffer);
                //printConsole("X1 is set to: " + value);
            }
        });
    }

    
    private void addLoadListener(JButton storeButton) {
        storeButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
            	
            		for (Component com : pnlMAR.getComponents()) {
                        if (com instanceof JTextField) {
                            JTextField regDisplay = (JTextField) com;
                            String binVal = regDisplay.getText().trim();
                            
                            int mar = Integer.parseInt(binVal, 2);
                            registers.setRegistersByName("MAR", mar);
                            int mbr = memory.getMemory(mar);
                            registers.setRegistersByName("MBR", mbr);
                            display();
                            
                        }
            		}
          }
      });
    }
    
    private void addStrListener(JButton storeButton) {
        storeButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
            	JPanel[] panels = {pnlR0, pnlR1, pnlR2, pnlR3, pnlX1, pnlX2, pnlX3, pnlMBR, pnlPC, pnlMAR};
            	String[] regWords = {"R0","R1", "R2", "R3", "X1", "X2", "X3", "MBR", "PC", "MAR"};
            	
            	for(int i = 0; i < panels.length; i++) {
            		for (Component com : panels[i].getComponents()) {
                        if (com instanceof JTextField) {
                            JTextField regDisplay = (JTextField) com;
                            String binVal = regDisplay.getText().trim();
                            int val = Integer.parseInt(binVal, 2);
                            if(val != 0) {
                            	int value = Integer.parseInt(binVal, 2);
                                registers.setRegistersByName(regWords[i], value);
                                System.out.println(regWords[i]+" Bin Value: "+binVal+" was set!");
                            }
                            
                            
                        }
            		}
            	}
          }
      });
    }
    public void display() {
    	JPanel[] panels = {pnlR0, pnlR1, pnlR2, pnlR3, pnlX1, pnlX2, pnlX3, pnlMBR, pnlPC, pnlMAR};
    	String[] regWords = {"R0","R1", "R2", "R3", "X1", "X2", "X3", "MBR", "PC", "MAR"};
    	
    	for(int i = 0; i < panels.length; i++) {
    		int value = registers.getRegistersByName(regWords[i]);
    		String binValue = Integer.toBinaryString(value);
    		for (Component com : panels[i].getComponents()) {
                if (com instanceof JTextField) {
                    JTextField regDisplay = (JTextField) com;
                    regDisplay.setText(binValue);
                    
                }
    		}
    	}
    }


    public static void main(String[] args) {
        FrontPanel GUI = new FrontPanel();
        SwingUtilities.invokeLater(() -> GUI.FrontPanel.setVisible(true));
    }
}
