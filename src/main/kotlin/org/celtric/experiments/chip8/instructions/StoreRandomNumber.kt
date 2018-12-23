package org.celtric.experiments.chip8.instructions

import org.celtric.experiments.chip8.VirtualMachine

internal class StoreRandomNumber(instructionData: InstructionData) : Instruction() {

    private val register = instructionData.msbLowerNibble().toRegister()
    private val mask = instructionData.lsbAsNumber()

    companion object {
        fun matches(data: InstructionData) = data.instructionCode() == InstructionCode(0xC)
    }

    override fun execute(vm: VirtualMachine) {
        vm.store(register, Number.random(mask))
    }

    override fun debug() = DebugInfo("Set $register to a random number with a mask of $mask")
}
