package vm

import vm.VM._
import vm.Bytecode._

object testVM {
  
  val hello: List[Int] = List(BR, HALT)

  def main(args: Array[String]) = {
    val vm = new VM
    // vm.data(1) = HALT
    cpu(hello)
  }

}