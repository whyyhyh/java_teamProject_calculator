package pck01;

import java.util.Scanner;

public class TextUserInterface {
  private String inputData;
  private CentralProcessingUnit cpu;
  private ProgramCounter pc;
  private MemoryAddressRegister mar;
  private MemoryBufferRegister mbr;
  private InstructionRegister ir;
  private Accumulator acc;
  //private 
  //private
  private Register[] memory;
  
  public TextUserInterface(CentralProcessingUnit cpu) {
    this.cpu = cpu;
    pc = cpu.getPC();
    mar = cpu.getMAR();
    mbr = cpu.getMBR();
    ir = cpu.getIR();
    acc = cpu.getACC();
    //ALU
    //CU
    memory = cpu.getMemory();
    
  }
  
  public static void clearScreen() {
    // 콘솔창 지우기
    for (int i=0; i<50; i++) {
      System.out.println();
    }
  }
  
  public String getInputData() {
    return inputData;
  }
  
  
  public void displayMainMenu() {
    System.out.println("수식을 입력하십시오");
  }
    
  public void displayStructure() {
    clearScreen();
    System.out.printf("================= CPU =================\n");
    System.out.printf("| PC [%10s]                    |\n", pc.getValue()); //cpu.getPC().getValue();
    System.out.printf("| MAR [%10s]                   |\n", mar.getValue());
    System.out.printf("| MBR [%10s]                   |\n", mbr.getValue());
    System.out.printf("| IR [%10s]                    |\n", ir.getValue());
    System.out.printf("| ACC [%10s]                   |\n", acc.getValue());
    //System.out.printf("| ALU [%10%s]                   |\n", cpu.getALU().getValue());
    //System.out.printf("| CU [%10%s]                    |\n", cpu.getCU().getValue());
    System.out.printf("=======================================\n");
    for (int i = 0; i < memory.length; i++) {
      System.out.printf("Memory %2d [%10s] \n", i, memory[i].getValue());
    }
  }
}
