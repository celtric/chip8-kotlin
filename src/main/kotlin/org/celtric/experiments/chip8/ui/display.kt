package org.celtric.experiments.chip8.ui

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.layout.GridPane
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.stage.Stage
import java.util.HashSet

class Display : Application() {

    private val width = 64
    private val height = 32
    private val cellSize = 10
    private val backgroundColor = Color.BLACK

    private val grid = HashSet<Coordinate>()

    override fun start(primaryStage: Stage) {
        fillGrid(width, height)

        val root = StackPane()
        root.id = "root"
        val scene = Scene(root, ((width - 1) * cellSize).toDouble(), ((height - 1) * cellSize).toDouble())
        root.children.add(grid())
        primaryStage.title = "CHIP-8 emulator"
        primaryStage.isResizable = false
        primaryStage.scene = scene
        primaryStage.show()
    }

    private fun grid(): GridPane {
        val grid = GridPane()
        grid.isGridLinesVisible = true

        this.grid.forEach { c ->
            grid.add(c.rectangle, c.x, c.y)
        }

        return grid
    }

    private fun fillGrid(width: Int, height: Int) {
        for (y in 0 until height) {
            for (x in 0 until width) {
                grid.add(Coordinate(x, y, Rectangle(cellSize.toDouble(), cellSize.toDouble(), backgroundColor)))
            }
        }
    }
}

private class Coordinate(val x: Int, val y: Int, val rectangle: Rectangle)
