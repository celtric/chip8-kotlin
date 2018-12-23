package org.celtric.experiments.chip8.instructions

import org.celtric.experiments.chip8.VirtualMachine

internal class AddValueToRegister(instructionData: InstructionData) : Instruction() {

    private val register = instructionData.msbLowerNibble().toRegister()
    private val number = instructionData.lsbAsNumber()

    companion object {
        fun matches(data: InstructionData) = data.instructionCode() == InstructionCode(0x7)
    }

    override fun execute(vm: VirtualMachine) {
        TODO()
    }

    override fun debug() = DebugInfo("Add $number to $register")
}
