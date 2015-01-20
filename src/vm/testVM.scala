package vm

import vm.VM._
import vm.Bytecode._

object testVM {
  
  val test_iconst: List[Int] = List(ICONST, 99, PRINT, HALT)
  val test_iadd: List[Int] = List(ICONST, 2, ICONST, 2, IADD, PRINT, HALT)
  val test_sub: List[Int] = List(ICONST, 4, ICONST, 2, ISUB, PRINT, HALT)
  val test_mul: List[Int] = List(ICONST, 4, ICONST, 4, IMUL, PRINT, HALT)
  val test_ieq: List[Int] = List(ICONST, 4, ICONST, 4, IEQ, PRINT, ICONST, 2, ICONST, 3, IEQ, PRINT, HALT)
  def main(args: Array[String]) = {
    
    val datasize: Int = 1
    val main = 0
    val vm = new VM(test_ieq, main, datasize)
    
    vm.cpu()
    
    

    
  }

}