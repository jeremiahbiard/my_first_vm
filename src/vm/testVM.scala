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
      
      
  val factorial: List[Int] = List(
      //.def fact: ARGS=1, LOCALS=0        ADDRESS
      //  IF n < 2 RETURN 1
          LOAD, -3,
          ICONST, 2,
          ILT,
          BRF, 10,
          ICONST, 1,
          RET,
          
      // CONT:
      // RETURN N = FACT(N - 1)
          LOAD, -3,
          LOAD, -3,
          ICONST, 1,
          ISUB,
          CALL, 0, 1,
          IMUL,
          RET,
          
      //.DEF MAIN: ARGS=0, LOCALS=0
      // PRINT FACT(10)
          ICONST, 1,
          CALL, 0, 1,
          PRINT,
          HALT
      )
  def main(args: Array[String]) = {
    
    val datasize: Int = 1
    val main = 0
    val vm = new VM(factorial, 22, 0)
    
    vm.TRACE = true
    vm.exec()
    
    

    
  }

}