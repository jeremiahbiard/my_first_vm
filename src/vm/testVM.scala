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
      
  val factorial: List[Int] = List(
      //.def fact: ARGS=1, LOCALS=0        ADDRESS
      //  IF n < 2 RETURN 1
          LOAD, -3,                // 0
          ICONST, 2,               // 2
          ILT,                     // 4
          BRF, 10,                  // 5
          ICONST, 1,                // 7
          RET,                      // 9
          
      // CONT:
      // RETURN N = FACT(N - 1)
          LOAD, -3,                // 10
          LOAD, -3,                //12
          ICONST, 1,               // 14
          ISUB,                    // 16
          CALL, 0, 1,              // 17
          IMUL,                    // 20
          RET,                      // 21
          
      //.DEF MAIN: ARGS=0, LOCALS=0
      // PRINT FACT(10)
          ICONST, 1,                // 22 <-- Main method
          CALL, 0, 1,              //  24
          PRINT,                    // 27
          HALT                      // 28
      )
  def main(args: Array[String]) = {
    
    val datasize: Int = 1
    val main = 0
    //val vm = new VM(factorial, 22, 0)
    val vm = new VM(loop, 0, 2)
    vm.TRACE = true
    vm.exec()
    
    

    
  }

}