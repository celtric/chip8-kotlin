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
    fun memoryAddress() = MemoryAddress(((mostSignificantByte.toInt() and 0xf) shl 8) or (leastSignificantByte.toInt() and 0xff))
}

data class InstructionCode(private val code: Int) {
    override fun toString(): String {
        return "InstructionCode(code=${code.toHex().toUpperCase()})"
    }
}

data class Nibble(private val value: Int) {
    fun toRegister() = Register(value)
    fun toInstructionCode() = InstructionCode(value)
}

data class Register(private val number: Int)
data class Number(private val value: Int)
data class MemoryAddress(private val address: Int) {
    override fun toString(): String {
        return "MemoryAddress(address=0x${address.toHex()})"
    }
}

abstract class Instruction {

    abstract fun debug()

    companion object {
        fun fromReference(instructionData: InstructionData): Instruction {
            return when (instructionData.instructionCode()) {
                StoreNumber.instructionCode -> StoreNumber(instructionData)
                StoreMemoryAddressInRegister.instructionCode -> StoreMemoryAddressInRegister(instructionData)
                SetToRandomNumberWithMask.instructionCode -> SetToRandomNumberWithMask(instructionData)
                else -> UnknownInstruction(instructionData)
            }
        }
    }
}

private fun Int.toHex() = Integer.toHexString(this)
