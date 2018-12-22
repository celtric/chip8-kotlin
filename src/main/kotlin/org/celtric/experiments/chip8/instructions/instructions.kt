package org.celtric.experiments.chip8.instructions

class Instructions(private val instructions: List<Instruction>) {

    fun debug() {
        instructions.forEach { it.debug() }
    }
}

class InstructionData(private val mostSignificantByte: Byte, private val leastSignificantByte: Byte) {
    fun upperNibble() = UpperNibble((mostSignificantByte.toInt() and 0xf0) shr 4)
    fun lowerNibble() = UpperNibble(mostSignificantByte.toInt() and 0xf)
    fun leastSignificantByteAsNumber() = Number(leastSignificantByte.toInt())
}

data class UpperNibble(private val value: Int) {
    fun toRegister() = Register(value)
}

data class LowerNibble(private val value: Int)

data class Register(private val number: Int)
data class Number(private val value: Int)

abstract class Instruction {

    abstract fun debug()

    companion object {
        fun fromReference(instructionData: InstructionData): Instruction {
            return when (instructionData.upperNibble()) {
                StoreNumber.instructionCode() -> StoreNumber(instructionData)
                else -> UnknownInstruction(instructionData)
            }
        }
    }
}
