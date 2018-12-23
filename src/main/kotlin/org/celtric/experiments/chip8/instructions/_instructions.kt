package org.celtric.experiments.chip8.instructions

class Instructions(private val instructions: List<Instruction>) {

    fun debug() {
        instructions.forEach { it.debug().print() }
    }
}

class InstructionData(private val mostSignificantByte: Byte, private val leastSignificantByte: Byte) {
    fun instructionCode() = msbUpperNibble().toInstructionCode()
    fun msbUpperNibble() = mostSignificantByte.upperNibble()
    fun msbLowerNibble() = mostSignificantByte.lowerNibble()
    fun lsbUpperNibble() = leastSignificantByte.upperNibble()
    fun lsbLowerNibble() = leastSignificantByte.lowerNibble()
    fun lsbAsNumber() = Number(leastSignificantByte.toInt())
    fun memoryAddress() = MemoryAddress(((mostSignificantByte.toInt() and 0xf) shl 8) or (leastSignificantByte.toInt() and 0xff))
}

data class InstructionCode(private val code: Int) {
    override fun toString(): String {
        return "InstructionCode(code=${code.toHex().toUpperCase()})"
    }
}

data class Nibble(private val value: Int) {
    fun toInstructionCode() = InstructionCode(value)
    fun toRegister() = Register(value)
    fun toNumber() = Number(value)
    fun toCoordinate(y: Nibble) = Coordinate(value, y.value)
}

data class Register(private val number: Int)
data class Number(private val value: Int)
data class MemoryAddress(private val address: Int) {
    override fun toString(): String {
        return "MemoryAddress(address=0x${address.toHex()})"
    }
}
data class Coordinate(private val x: Int, private val y: Int)

abstract class Instruction {

    abstract fun debug(): DebugInfo

    companion object {
        fun fromData(instructionData: InstructionData): Instruction {
            return when (instructionData.instructionCode()) {
                JumpToAddress.instructionCode -> JumpToAddress(instructionData)
                ExecuteSubroutine.instructionCode -> ExecuteSubroutine(instructionData)
                SkipNextInstructionIfRegisterValueEquals.instructionCode -> SkipNextInstructionIfRegisterValueEquals(instructionData)
                StoreNumber.instructionCode -> StoreNumber(instructionData)
                AddValueToRegister.instructionCode -> AddValueToRegister(instructionData)
                CopyRegister.instructionCode -> CopyRegister(instructionData)
                StoreMemoryAddressInRegister.instructionCode -> StoreMemoryAddressInRegister(instructionData)
                SetToRandomNumberWithMask.instructionCode -> SetToRandomNumberWithMask(instructionData)
                DrawSprite.instructionCode -> DrawSprite(instructionData)
                else -> UnknownInstruction(instructionData)
            }
        }
    }
}

class DebugInfo(private val description: String) {
    fun print() {
        println(description)
    }
}

private fun Int.toHex() = Integer.toHexString(this)
private fun Byte.toHex() = Integer.toHexString(toInt())
private fun Byte.upperNibble() = Nibble((toInt() and 0xf0) shr 4)
private fun Byte.lowerNibble() = Nibble(toInt() and 0xf)
