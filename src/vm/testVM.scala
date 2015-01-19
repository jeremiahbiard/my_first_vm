package vm

import vm.VM._
import vm.Bytecode._

object testVM {
  
  val test_add: List[Int] = List(IADD, 2, 2, PRINT, HALT)
  val test_sub: List[Int] = List(ISUB, 4, 2, PRINT, HALT)
  val test_mul: List[Int] = List(IMUL, 4, 4, PRINT, HALT)
  def main(args: Array[String]) = {
    
    println("Testing Addition:")
    println("-----------------")
    val vm_add: VM = new VM(Nil, 0, 0)
    // vm.data(1) = HALT
    vm_add.cpu(test_add)
    
    println("")
    println("Testing Subtraction:")
    println("--------------------")
    val vm_sub: VM = new VM(Nil, 0, 0)
    vm_sub.cpu(test_sub)
    
    println("")
    println("Testing Multiplication:")
    println("-----------------------")
    val vm_mul: VM = new VM(Nil, 0, 0)
    vm_mul.cpu(test_mul)
    
  }

}