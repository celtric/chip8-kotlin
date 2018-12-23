package org.celtric.experiments.chip8.ui

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.layout.GridPane
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.stage.Stage
import org.celtric.experiments.chip8.ROM
import org.celtric.experiments.chip8.VirtualMachine

class Display : Application() {

    private val width = 64
    private val height = 32
    private val cellSize = 10
    private val backgroundColor = Color.BLACK
    private val fillColor = Color.WHITE

    private val coordinates: MutableMap<ScreenCoordinate, Rectangle> = mutableMapOf()

    override fun start(primaryStage: Stage) {

        val instructions = ROM("/roms/maze.ch8").read()
        instructions.debug()

        val root = StackPane()
        root.id = "root"
        val scene = Scene(root, ((width - 1) * cellSize).toDouble(), ((height - 1) * cellSize).toDouble())
        root.children.add(grid())
        primaryStage.title = "CHIP-8 emulator"
        primaryStage.isResizable = false
        primaryStage.scene = scene
        primaryStage.show()

        val vm = VirtualMachine(this)
        vm.execute(instructions)
    }

    private fun grid(): GridPane {
        val grid = GridPane()
        grid.isGridLinesVisible = true

        for (y in 0 until height) {
            for (x in 0 until width) {
                coordinates[ScreenCoordinate(x, y)] = emptyCell()
                grid.add(coordinates[ScreenCoordinate(x, y)], x, y)
            }
        }

        return grid
    }

    private fun emptyCell() = Rectangle(cellSize.toDouble(), cellSize.toDouble(), backgroundColor)

    fun draw(coordinate: ScreenCoordinate) {
        coordinates[coordinate]!!.fill = fillColor
    }
}

