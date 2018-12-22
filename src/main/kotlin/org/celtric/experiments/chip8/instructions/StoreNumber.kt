package org.celtric.experiments.chip8.instructions

internal class StoreNumber(instructionData: InstructionData) : Instruction() {

    private val register = instructionData.lowerNibble().toRegister()
    private val number = instructionData.leastSignificantByte()

    companion object {
        fun instructionCode() = UpperNibble(0x6)
    }

    override fun debug() {
        println("Store 0x${number.toHex()} in register v$register")
    }
}
