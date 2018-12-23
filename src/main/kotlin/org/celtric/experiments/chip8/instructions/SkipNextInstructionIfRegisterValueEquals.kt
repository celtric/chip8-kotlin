package org.celtric.experiments.chip8.instructions

import org.celtric.experiments.chip8.VirtualMachine

internal class SkipNextInstructionIfRegisterValueEquals(instructionData: InstructionData) : Instruction() {

    private val register = instructionData.msbLowerNibble().toRegister()
    private val compareTo = instructionData.lsbAsNumber()

    companion object {
        fun matches(data: InstructionData) = data.instructionCode() == InstructionCode(0x3)
    }

    override fun execute(vm: VirtualMachine) {
        TODO()
    }

    override fun debug() = DebugInfo("Skip the following instruction if the value of $register equals $compareTo")
}
