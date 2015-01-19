package vm

import vm.Bytecode._

object VM {
  
  var code: Array[Int] = Array()
  var stack: Array[Int] = Array()
  var data: Array[Int] = Array()
  
  def cpu(program: List[Int]): Unit = program match {
    case Nil => Nil
    case head :: rest =>
      head match {
        case HALT => println("Execution halted");
        cpu(rest)
        case BR => println("Break")
      }
  }
  
  
}

class VM() {

  
  var ip: Int = 0
  var sp: Int = -1
  var fp: Int = 0
  
}