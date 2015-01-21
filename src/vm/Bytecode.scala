package vm
object Bytecode {
  val IADD:     Short = 1  // Integer add
  val ISUB:     Short = 2  // Integer subtract
  val IMUL:     Short = 3  // Integer multiply
  val ILT:      Short = 4
  val IEQ:      Short = 5
  val BR:       Short = 6
  val BRT:      Short = 7
  val BRF:      Short = 8
  val ICONST:   Short = 9
  val LOAD:     Short = 10
  val GLOAD:    Short = 11
  val STORE:    Short = 12
  val GSTORE:   Short = 13
  val PRINT:    Short = 14
  val POP:      Short = 15
  val HALT:     Short = 16
  val CALL:     Short = 17
  val RET:      Short = 18
  
  class Instruction(name: String, n_args: Int) {
    val n: Int = n_args
    override def toString = name
    def this(name: String) = {
      this(name, 0)
    }
  }
  
  val opcodes: List[Instruction] = {
    List(null,
    new Instruction("iadd"),
    new Instruction("isub"),
    new Instruction("imul"),
    new Instruction("ilt"),
    new Instruction("ieq"),
    new Instruction("br", 1),
    new Instruction("brt", 1),
    new Instruction("brf", 1),
    new Instruction("iconst", 1),
    new Instruction("load", 1),
    new Instruction("gload", 1),
    new Instruction("store", 1),
    new Instruction("gstore", 1),
    new Instruction("print"),
    new Instruction("pop"),
    new Instruction("halt"),
    new Instruction("call"),
    new Instruction("ret")
    )
  }
  
  
  
  
}