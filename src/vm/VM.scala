package vm

import vm.Bytecode._
import vm.Bytecode.Instruction
import System.err.println

object VM {
}
  
 class VM(code: List[Int], main: Int, dataSize: Int) {
  val trace: Boolean = true
  private var stack: Array[Int] = new Array(100: Int)
  private var data: Array[Int] = Array(dataSize: Int)
 
  var sp: Int = -1
  var ip: Int = main
  
  def cpu(program: List[Int]): Unit = {
    
    // println(opcode)

    while (ip < program.length) {
      var opcode:Int = program(ip)
      var instr: Instruction = Bytecode.opcodes(opcode)
      if (trace) disassemble(instr)
      

      opcode match {
        case IADD =>
          ip += 1
          val reg_a: Int = program(ip)
          ip += 1
          val reg_b: Int = program(ip)
          sp += 1
          stack(sp) = reg_a + reg_b
        case ISUB =>
          ip += 1
          val reg_a: Int = program(ip)
          ip += 1
          val reg_b: Int = program(ip)
          sp += 1
          stack(sp) = reg_a - reg_b
        case IMUL =>
          ip += 1
          val reg_a: Int = program(ip)
          ip += 1
          val reg_b: Int = program(ip)
          sp += 1
          stack(sp) = reg_a * reg_b
        case ICONST =>
          ip += 1
          sp += 1
          stack(sp) = program(ip)
        case PRINT =>
          printf("%d\n", stack(sp))
          sp -= 1
        case HALT => 
          print("Execution halted.\n")
          return
      }     
      
      ip += 1
    }
    
    def disassemble(opcode: Instruction) = {
        printf("%04d: %s", ip, opcode)
        opcode.n match {
          case 1 =>
            printf(" %d", program(ip + 1))
          case 2 =>
            printf(" %d, %d", program(ip + 1), program(ip + 2))
          case _ => ""
        } 
        printf("\n")
    }
    
  }
 }