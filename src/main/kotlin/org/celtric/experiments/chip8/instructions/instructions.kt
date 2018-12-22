package org.celtric.experiments.chip8.instructions

class Instructions(private val instructions: List<Instruction>) {

    fun debug() {
        instructions.forEach { it.debug() }
    }
}

class InstructionData(private val mostSignificantByte: Byte, private val leastSignificantByte: Byte) {
    fun instructionCode() = upperNibble().toInstructionCode()
    private fun upperNibble() = Nibble((mostSignificantByte.toInt() and 0xf0) shr 4)
    fun lowerNibble() = Nibble(mostSignificantByte.toInt() and 0xf)
    fun leastSignificantByteAsNumber() = Number(leastSignificantByte.toInt())
}

data class InstructionCode(private val code: Int)

data class Nibble(private val value: Int) {
    fun toRegister() = Register(value)
    fun toInstructionCode() = InstructionCode(value)
}

data class Register(private val number: Int)
data class Number(private val value: Int)

abstract class Instruction {

    abstract fun debug()

    companion object {
        fun fromReference(instructionData: InstructionData): Instruction {
            return when (instructionData.instructionCode()) {
                StoreNumber.instructionCode() -> StoreNumber(instructionData)
                else -> UnknownInstruction(instructionData)
            }
        }
    }
}
