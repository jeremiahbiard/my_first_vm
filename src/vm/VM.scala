package vm

import vm.Bytecode._
import vm.Bytecode.Instruction


object VM {
}
  
class VM(program: List[Int], main: Int, datasize: Int) {

   var TRACE: Boolean = false
   
   val TRUE: Int = 1
   val FALSE: Int = -1
   
   var fp: Int = 0              // frame pointer
   var stack: Array[Int] = new Array[Int](100)
   var global_register: Array[Int] = new Array[Int](datasize)
   
   def exec(): Unit = {
     cpu(program, main, -1, stack)
   }
   
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
      
      val opcode: Int = program(ip)
      val instr: Instruction = Bytecode.opcodes(opcode)
    
      opcode match {
        case IADD =>
          val new_sp = sp - 1
          val new_ip = ip + 1
          val b = stack(sp)
          val a = stack(new_sp)
          stack(new_sp) = a + b
          if (TRACE) disassemble(program, instr, ip, new_sp, stack)
          cpu(program, new_ip, new_sp, stack)
        case ISUB =>
          val new_sp = sp - 1
          val new_ip = ip + 1
          val b = stack(sp)
          val a = stack(new_sp)
          stack(new_sp) = a - b
          if (TRACE) disassemble(program, instr, ip, new_sp, stack)
          cpu(program, new_ip, new_sp, stack)
        case IMUL =>
          val new_sp = sp - 1
          val new_ip = ip + 1
          val b = stack(sp)
          val a = stack(new_sp)
          stack(new_sp) = a * b
          if (TRACE) disassemble(program, instr, ip, new_sp, stack)
          cpu(program, new_ip, new_sp, stack)
        case ILT =>
          val new_ip = ip + 1
          val new_sp = sp - 1
          val b = stack(sp)
          val a = stack(new_sp)
          stack(new_sp) = if (a < b) TRUE else FALSE
          if (TRACE) disassemble(program, instr, ip, new_sp, stack)
          cpu(program, new_ip, new_sp, stack)
        case IEQ =>
          val new_ip = ip + 1
          val new_sp = sp - 1
          val b = stack(sp)
          val a = stack(new_sp)
          stack(new_sp) = if (a == b) TRUE else FALSE
          if (TRACE) disassemble(program, instr, ip, new_sp, stack)
          cpu(program, new_ip, new_sp, stack)
        case BR =>
          val new_ip = program(ip + 1)
          if (TRACE) disassemble(program, instr, ip, sp, stack)
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
          if (TRACE) disassemble(program, instr, ip, new_sp, stack)
          cpu(program, new_ip, new_sp, stack)
        case LOAD =>
          val offset = program(ip + 1)
          val new_ip = ip + 2
          val new_sp = sp + 1
          println(fp + offset)
          stack(new_sp) = stack(fp + offset)
          if (TRACE) disassemble(program, instr, ip, new_sp, stack)
          cpu(program, new_ip, new_sp, stack)
        case GLOAD =>
          val addr = program(ip + 1)
          val new_sp = sp + 1
          stack(new_sp) = global_register(addr)
          val new_ip = ip + 2
          if (TRACE) disassemble(program, instr, ip, new_sp, stack)
          cpu(program, new_ip, new_sp, stack)
        case STORE =>
        case GSTORE =>
          val addr = program(ip + 1)
          global_register(addr) = stack(sp)
          val new_sp = sp - 1
          val new_ip = ip + 2
          if (TRACE) disassemble(program, instr, ip, new_sp, stack)
          cpu(program, new_ip, new_sp, stack)
        case PRINT =>
          println(stack(sp))
          val new_sp = sp - 1
          val new_ip = ip + 1
          if (TRACE) disassemble(program, instr, ip, new_sp, stack)
          cpu(program, new_ip, new_sp, stack)
        case POP =>
          val new_sp = sp - 1
          val new_ip = ip + 1
          if (TRACE) disassemble(program, instr, ip, new_sp, stack)
          cpu(program, new_ip, new_sp, stack)
        case HALT =>
          if (TRACE) disassemble(program, instr, ip, sp, stack)
          println("Program execution halted.")
        case CALL =>
          // expects all args on stack
          val addr = program(ip + 1)          // target addr of function
          val nargs = program(ip + 2)
          val new_sp = sp + 3 // how many args got pushed
          println(addr, nargs)
          stack(sp + 1) = nargs               // save num args
          stack(sp + 2) = fp                  // save function pointer
          stack(sp + 3) = ip                  // push return address
          fp = new_sp                        // fp points to return addr on stack
          cpu(program, addr, new_sp, stack)
          // code preamble of func must push space for locals
        case RET =>
          val rvalue = stack(sp)              // pop return value
          val temp_sp = fp                    // jump over locals to fp
          val new_ip = stack(temp_sp - 1)          // pop return address and jump
          fp = stack(temp_sp - 2)                  // restore fp
          val nargs = stack(temp_sp - 3)            // how many args to throw asay
          val new_sp = temp_sp - nargs
          stack(new_sp + 1) = rvalue           // leave result on stack
          cpu(program, new_ip, new_sp, stack)
        case _ => throw new Error("invalid opcode: " + opcode + " at ip: "+ ip + ".")
      }        
    }
  }
}