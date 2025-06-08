package pck01;

import java.util.Scanner;

public class TextUserInterface {
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_WHITE = "\u001B[0m";
  
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
  
  
  public void displayMainMenu() {
    System.out.println("수식을 입력하십시오");
  }

  
  
  public void displayStructure(String sender, String accepter) {
    clearScreen();
    System.out.printf("———————————————  CPU  ————————————————        Bus             Memory\n");
    
    if (sender.equals("Memory 0") || accepter.equals("Memory 0")) {
      System.out.printf("|    __ CU __                        |         ‖         " + ANSI_RED + "0 [%10s]\n" + ANSI_WHITE, memory[0].getValue());
    } else {
      System.out.printf("|    __ CU __                        |         ‖         0 [%10s]\n", memory[0].getValue());
    }
    
    if (sender.equals("Memory 1") || accepter.equals("Memory 1")) {
      System.out.printf("|   |        |                       |         ‖         " + ANSI_RED + "1 [%10s]\n" + ANSI_WHITE, memory[1].getValue());
    } else {
      System.out.printf("|   |        |                       |         ‖         1 [%10s]\n", memory[1].getValue());
    }
    
    if (sender.equals("Memory 2") || accepter.equals("Memory 2")) {
      System.out.printf("|   |        |                       |         ‖         " + ANSI_RED + "2 [%10s]\n" + ANSI_WHITE, memory[2].getValue());
    } else {
      System.out.printf("|   |        |                       |         ‖         2 [%10s]\n", memory[2].getValue());
    }
    
    if (sender.equals("Memory 3") || accepter.equals("Memory 3")) {
      System.out.printf("|   |________|        PC [%8s]  |         ‖         " + ANSI_RED + "3 [%10s]\n" + ANSI_WHITE, pc.getValue(), memory[3].getValue());
    } else if (sender.equals("ProgramCounter") || accepter.equals("ProgramCounter")) {
      System.out.printf("|   |________|        " + ANSI_RED + "PC [%8s]" + ANSI_WHITE + "  |         ‖         3 [%10s]\n", pc.getValue(), memory[3].getValue());
    } else {
      System.out.printf("|   |________|        PC [%8s]  |         ‖         3 [%10s]\n", pc.getValue(), memory[3].getValue());
    }
    
    if (sender.equals("Memory 4") || accepter.equals("Memory 4")) {
      System.out.printf("|                                    |         ‖         " + ANSI_RED + "4 [%10s]\n" + ANSI_WHITE, memory[4].getValue());
    } else {
      System.out.printf("|                                    |         ‖         4 [%10s]\n", memory[4].getValue());
    }
    
    if (sender.equals("Memory 5") || accepter.equals("Memory 5")) {
      if (sender.equals("MemoryAddressRegister") || accepter.equals("MemoryAddressRegister")) {
        System.out.printf("| IR  [%8s]     " + ANSI_RED + "MAR [%8s]" + ANSI_WHITE + "  |         ‖         " + ANSI_RED + "5 [%10s]\n" + ANSI_WHITE, ir.getValue(), mar.getValue(), memory[5].getValue());
      } else {
        System.out.printf("| IR  [%8s]     MAR [%8s]  |         ‖         " + ANSI_RED + "5 [%10s]\n" + ANSI_WHITE, ir.getValue(), mar.getValue(), memory[5].getValue());
      }
    } else {
      if (sender.equals("InstructionRegister") || accepter.equals("InstructionRegister")) {
        if (sender.equals("MemoryAddressRegister") || accepter.equals("MemoryAddressRegister")) {
          System.out.printf("| " + ANSI_RED + "IR  [%8s]" + ANSI_WHITE + "     " + ANSI_RED + "MAR [%8s]" + ANSI_WHITE + "  |         ‖         5 [%10s]\n", ir.getValue(), mar.getValue(), memory[5].getValue());
        } else {
          System.out.printf("| " + ANSI_RED + "IR  [%8s]" + ANSI_WHITE + "     MAR [%8s]  |         ‖         5 [%10s]\n", ir.getValue(), mar.getValue(), memory[5].getValue());
        }
      } else {
        if (sender.equals("MemoryAddressRegister") || accepter.equals("MemoryAddressRegister")) {
          System.out.printf("| IR  [%8s]     " + ANSI_RED + "MAR [%8s]" + ANSI_WHITE + "  |         ‖         5 [%10s]\n", ir.getValue(), mar.getValue(), memory[5].getValue());
        } else {
          System.out.printf("| IR  [%8s]     MAR [%8s]  |         ‖         5 [%10s]\n", ir.getValue(), mar.getValue(), memory[5].getValue());
        }
      }
    }
    
    if (sender.equals("Memory 6") || accepter.equals("Memory 6")) {
      System.out.printf("|                                    |         ‖         " + ANSI_RED + "6 [%10s]\n" + ANSI_WHITE, memory[6].getValue());
    } else {
      System.out.printf("|                                    |         ‖         6 [%10s]\n", memory[6].getValue());
    }
    
    if (sender.equals("MemoryBufferRegister") || accepter.equals("MemoryBufferRegister")) {
      if (sender.equals("Memory 7") || accepter.equals("Memory 7")) {
        System.out.printf("| ACC [%8s]     " + ANSI_RED + "MBR [%8s]" + ANSI_WHITE + "  |         ‖         " + ANSI_RED + "7 [%10s]\n" + ANSI_WHITE, acc.getValue(), mbr.getValue(), memory[7].getValue());
      } else {
        if (sender.equals("Accumulator") || accepter.equals("Accumulator")) {
          System.out.printf("| " + ANSI_RED + "ACC [%8s]" + ANSI_WHITE + "     " + ANSI_RED + "MBR [%8s]" + ANSI_WHITE + "  |         ‖         7 [%10s]\n", acc.getValue(), mbr.getValue(), memory[7].getValue());
        } else {
          System.out.printf("| ACC [%8s]     " + ANSI_RED + "MBR [%8s]" + ANSI_WHITE + "  |         ‖         7 [%10s]\n", acc.getValue(), mbr.getValue(), memory[7].getValue());
        }
      }
    } else {
      if (sender.equals("Accumulator") || accepter.equals("Accumulator")) {
        System.out.printf("| " + ANSI_RED + "ACC [%8s]" + ANSI_WHITE + "     MBR [%8s]  |         ‖         7 [%10s]\n", acc.getValue(), mbr.getValue(), memory[7].getValue());
      } else {
        if (sender.equals("Memory 7") || accepter.equals("Memory 7")) {
        System.out.printf("| ACC [%8s]     MBR [%8s]  |         ‖         " + ANSI_RED + "7 [%10s]\n" + ANSI_WHITE, acc.getValue(), mbr.getValue(), memory[7].getValue());
        } else {
          System.out.printf("| ACC [%8s]     MBR [%8s]  |         ‖         7 [%10s]\n", acc.getValue(), mbr.getValue(), memory[7].getValue());
        }
      }
    }
    
    if (sender.equals("Memory 8") || accepter.equals("Memory 8")) {
      System.out.printf("|                                    |         ‖         " + ANSI_RED + "8 [%10s]\n" + ANSI_WHITE, memory[8].getValue());
    } else {
      System.out.printf("|                                    |         ‖         8 [%10s]\n", memory[8].getValue());
    }
    
    if (sender.equals("Memory 9") || accepter.equals("Memory 9")) {
      System.out.printf("|   ___ ALU ___                      |         ‖         " + ANSI_RED + "9 [%10s]\n" + ANSI_WHITE, memory[9].getValue());
    } else {
      System.out.printf("|   ___ ALU ___                      |         ‖         9 [%10s]\n", memory[9].getValue());
    }
    
    if (sender.equals("Memory 10") || accepter.equals("Memory 10")) {
      System.out.printf("|   \\  \\___/  /                      |         ‖        " + ANSI_RED + "10 [%10s]\n" + ANSI_WHITE, memory[10].getValue());
    } else {
      System.out.printf("|   \\  \\___/  /                      |         ‖        10 [%10s]\n", memory[10].getValue());
    }
    
    if (sender.equals("Memory 11") || accepter.equals("Memory 11")) {
      System.out.printf("|    \\_______/                       |         ‖        " + ANSI_RED + "11 [%10s]\n" + ANSI_WHITE, memory[11].getValue());
    } else {
      System.out.printf("|    \\_______/                       |         ‖        11 [%10s]\n", memory[11].getValue());
    }
    
    if (sender.equals("Memory 12") || accepter.equals("Memory 12")) {
      System.out.printf("——————————————————————————————————————         ‖        " + ANSI_RED + "12 [%10s]\n" + ANSI_WHITE, memory[12].getValue());
    } else {
      System.out.printf("——————————————————————————————————————         ‖        12 [%10s]\n", memory[12].getValue());
    }
    
    for (int i = 13; i < memory.length; i++) {
      if (sender.equals("Memory " + String.valueOf(i)) || accepter.equals("Memory " + String.valueOf(i))) {
        System.out.printf("                                                        " + ANSI_RED + "%2d [%10s] \n" + ANSI_WHITE, i, memory[i].getValue());
      } else {
        System.out.printf("                                                        %2d [%10s] \n", i, memory[i].getValue());
      }
    }
  }
}
