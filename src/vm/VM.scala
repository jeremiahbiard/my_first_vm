package vm

import vm.Bytecode._
import vm.Bytecode.Instruction


object VM {
}
  
class VM(program: List[Int], main: Int, datasize: Int) {

   val TRUE: Int = 1
   val FALSE: Int = -1
   def disassemble(program: List[Int], opcode: Instruction, ip: Int, sp: Int, stack: Array[Int]) = {
       // Print the current instruction address, opcode, and arguments
        printf("%04d: %s", ip, opcode)
        opcode.n match {
          case 1 =>
            printf(" %d", program(ip + 1))
          case 2 =>
            printf(" %d, %d", program(ip + 1), program(ip + 2))
          case _ => ""
        } 
        
        // Print the stack
        printf("\t\tStack: [ ")
        for {i <- (0 to sp)} print(stack(i) + " ")
        printf("]\n")
    }
  
  def cpu(program: List[Int], ip: Int, sp: Int, stack: Array[Int]): Unit = {
    
    if (ip > program.length) return else {
      
      val trace: Boolean = true
      val opcode: Int = program(ip)
      val instr: Instruction = Bytecode.opcodes(opcode)
    
      opcode match {
        case IADD =>
          val new_sp = sp - 1
          val new_ip = ip + 1
          val b = stack(sp)
          val a = stack(new_sp)
          stack(new_sp) = a + b
          if (trace) disassemble(program, instr, ip, new_sp, stack)
          cpu(program, new_ip, new_sp, stack)
        case ISUB =>
          val new_sp = sp - 1
          val new_ip = ip + 1
          val b = stack(sp)
          val a = stack(new_sp)
          stack(new_sp) = a - b
          if (trace) disassemble(program, instr, ip, new_sp, stack)
          cpu(program, new_ip, new_sp, stack)
        case IMUL =>
          val new_sp = sp - 1
          val new_ip = ip + 1
          val b = stack(sp)
          val a = stack(new_sp)
          stack(new_sp) = a * b
          if (trace) disassemble(program, instr, ip, new_sp, stack)
          cpu(program, new_ip, new_sp, stack)
        case ILT =>
          val new_ip = ip + 1
          val new_sp = sp - 1
          val b = stack(sp)
          val a = stack(new_sp)
          stack(new_sp) = if (a < b) TRUE else FALSE
          if (trace) disassemble(program, instr, ip, new_sp, stack)
          cpu(program, new_ip, new_sp, stack)
        case IEQ =>
          val new_ip = ip + 1
          val new_sp = sp - 1
          val b = stack(sp)
          val a = stack(new_sp)
          stack(new_sp) = if (a == b) TRUE else FALSE
          if (trace) disassemble(program, instr, ip, new_sp, stack)
          cpu(program, new_ip, new_sp, stack)
        case BR =>
          val new_ip = program(ip + 1)
          if (trace) disassemble(program, instr, ip, sp, stack)
          cpu(program, new_ip, sp, stack)
        case BRT =>
          val addr = program(ip + 1)
          val new_sp = sp - 1
          if (stack(sp) == TRUE) cpu(program, addr, new_sp, stack) else
            cpu(program, ip + 1, new_sp, stack)
        case BRF =>
          val addr = program(ip + 1)
          val new_sp = sp - 1
          if (stack(sp) == FALSE) cpu(program, addr, new_sp, stack) else
            cpu(program, ip + 1, new_sp, stack)
        case ICONST =>
          val new_sp = sp + 1
          val data_addr = ip + 1
          val new_ip = ip + 2
          stack(new_sp) = program(data_addr)
          if (trace) disassemble(program, instr, ip, new_sp, stack)
          cpu(program, new_ip, new_sp, stack)
        case LOAD =>
        case GLOAD =>
        case STORE =>
        case GSTORE =>
        case PRINT =>
          println(stack(sp))
          val new_sp = sp - 1
          val new_ip = ip + 1
          if (trace) disassemble(program, instr, ip, new_sp, stack)
          cpu(program, new_ip, new_sp, stack)
        case POP =>
        case HALT =>
          if (trace) disassemble(program, instr, ip, sp, stack)
          println("Program execution halted.")
        case _ => println("BARF")
      }        
    }
  }
}