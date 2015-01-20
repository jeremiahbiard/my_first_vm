package vm

import vm.VM._
import vm.Bytecode._

object testVM {
  
  val test_iconst: List[Int] = List(ICONST, 99, PRINT, HALT)
  val test_add: List[Int] = List(IADD, 2, 2, PRINT, HALT)
  val test_sub: List[Int] = List(ISUB, 4, 2, PRINT, HALT)
  val test_mul: List[Int] = List(IMUL, 4, 4, PRINT, HALT)
  def main(args: Array[String]) = {
    val vm = new VM()
    val main = 0
    var stack = Array(100: Int)
    vm.cpu(test_iconst, main, -1, stack)
    

    
  }

}