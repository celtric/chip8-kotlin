package org.celtric.experiments.chip8.instructions

internal class UnknownInstruction(instructionData: InstructionData) : Instruction() {

    private val instructionCode = instructionData.instructionCode()

    override fun debug() = DebugInfo("Unknown instruction code $instructionCode")
}
