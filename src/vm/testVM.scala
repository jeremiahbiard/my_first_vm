package vm

import vm.VM._
import vm.Bytecode._

object testVM {
  
  val test_iconst: List[Int] = List(ICONST, 99, PRINT, HALT)
  val test_iadd: List[Int] = List(ICONST, 2, ICONST, 2, IADD, PRINT, HALT)
  val test_sub: List[Int] = List(ISUB, 4, 2, PRINT, HALT)
  val test_mul: List[Int] = List(IMUL, 4, 4, PRINT, HALT)
  def main(args: Array[String]) = {
    val vm = new VM()
    val main = 0
    var stack = new Array[Int](10)
    // vm.cpu(test_iconst, main, -1, stack)
    vm.cpu(test_iadd, main, -1, stack)
    

    
  }

}