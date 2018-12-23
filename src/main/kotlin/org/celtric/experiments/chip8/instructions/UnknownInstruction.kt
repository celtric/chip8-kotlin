package org.celtric.experiments.chip8.instructions

import org.celtric.experiments.chip8.VirtualMachine
import java.lang.RuntimeException

internal class UnknownInstruction(instructionData: InstructionData) : Instruction() {

    private val instructionCode = instructionData.instructionCode()

    override fun execute(vm: VirtualMachine) {
        RuntimeException("An unknown instruction cannot be executed")
    }

    override fun debug() = DebugInfo("Unknown instruction code $instructionCode")
}
