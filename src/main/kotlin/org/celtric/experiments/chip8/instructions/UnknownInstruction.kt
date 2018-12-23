package org.celtric.experiments.chip8.instructions

import org.celtric.experiments.chip8.VirtualMachine

internal class UnknownInstruction(instructionData: InstructionData) : Instruction() {

    private val instructionCode = instructionData.instructionCode()

    override fun execute(vm: VirtualMachine) {
        TODO()
    }

    override fun debug() = DebugInfo("Unknown instruction code $instructionCode")
}
