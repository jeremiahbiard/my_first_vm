package vm

import vm.VM._
import vm.Bytecode._

object testVM {
  
  val test_iconst: List[Int] = List(ICONST, 99, PRINT, HALT)
  val test_iadd: List[Int] = List(ICONST, 2, ICONST, 2, IADD, PRINT, HALT)
  val test_sub: List[Int] = List(ICONST, 4, ICONST, 2, ISUB, PRINT, HALT)
  val test_mul: List[Int] = List(ICONST, 4, ICONST, 4, IMUL, PRINT, HALT)
  val test_ieq: List[Int] = List(ICONST, 4, ICONST, 4, IEQ, PRINT, ICONST, 2, ICONST, 3, IEQ, PRINT, HALT)
  val test_gstore: List[Int] = List(
      ICONST, 99,
      GSTORE, 0,
      GLOAD, 0,
      PRINT,
      HALT
      )
      
  val loop: List[Int] = List(
      ICONST, 10,
      GSTORE, 0,
      
      ICONST, 0,
      GSTORE, 1,
      
      GLOAD, 1,
      GLOAD, 0,
      ILT,
      BRF, 24,
      
      GLOAD, 1,
      ICONST, 1,
      IADD,
      GSTORE, 1,
      BR, 8,
      HALT
      )
      
  val fact: Int = 5
  
  def main(args: Array[String]) = {
    
    val datasize: Int = 1
    val main = 0
    
    val vm = new VM(22, 0)
    val program = vm.load("src/vm/fact.txt")
    vm.TRACE = false
    vm.exec(program)
    
    

    
  }

}