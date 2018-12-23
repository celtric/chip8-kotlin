package org.celtric.experiments.chip8.instructions

internal class DrawSprite(instructionData: InstructionData) : Instruction() {

    private val coordinate = instructionData.msbLowerNibble().toCoordinate(instructionData.lsbUpperNibble())
    private val height = instructionData.lsbLowerNibble().toNumber()

    companion object {
        fun matches(data: InstructionData) = data.instructionCode() == InstructionCode(0xD)
    }

    override fun debug() =
        DebugInfo("Draw a sprite at $coordinate with $height bytes of sprite data starting at the address stored in I\n" +
                  "    Set VF to 01 if any set pixels are changed to unset, and 00 otherwise")
}
