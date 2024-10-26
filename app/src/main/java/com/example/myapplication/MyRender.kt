package com.example.myapplication

import android.content.Context
import android.view.MotionEvent
import org.rajawali3d.renderer.Renderer
import org.rajawali3d.scene.Scene
import org.rajawali3d.math.vector.Vector3
import org.rajawali3d.primitives.Line3D
import org.rajawali3d.materials.Material
import org.rajawali3d.materials.methods.DiffuseMethod
import org.rajawali3d.lights.DirectionalLight
import java.util.Stack

class MyRenderer(context: Context) : Renderer(context) {

    override fun initScene() {
        val light = DirectionalLight(1.0, 0.2, -1.0)
        light.setColor(1.0f, 1.0f, 1.0f)
        light.setPower(2.0f)
        currentScene.addLight(light)

        // Create X, Y, Z axes
        val xAxis = Line3D(Stack<Vector3>().apply {
            add(Vector3(-10.0, 0.0, 0.0))
            add(Vector3(10.0, 0.0, 0.0))
        }, 1.0f)
        val yAxis = Line3D(Stack<Vector3>().apply {
            add(Vector3(0.0, -10.0, 0.0))
            add(Vector3(0.0, 10.0, 0.0))
        }, 1.0f)
        val zAxis = Line3D(Stack<Vector3>().apply {
            add(Vector3(0.0, 0.0, -10.0))
            add(Vector3(0.0, 0.0, 10.0))
        }, 1.0f)

        val material = Material()
        material.diffuseMethod = DiffuseMethod.Lambert()
        xAxis.material = material
        yAxis.material = material
        zAxis.material = material

        currentScene.addChild(xAxis)
        currentScene.addChild(yAxis)
        currentScene.addChild(zAxis)
    }

    fun plotFunction(function: (Double, Double, Double) -> Vector3) {
        // Example function plotting
        val points = Stack<Vector3>()
        for (x in -10..10) {
            for (y in -10..10) {
                val z = function(x.toDouble(), y.toDouble(), 0.0).z
                points.add(Vector3(x.toDouble(), y.toDouble(), z))
            }
        }
        val line = Line3D(points, 1.0f)
        val material = Material()
        material.diffuseMethod = DiffuseMethod.Lambert()
        line.material = material
        currentScene.addChild(line)
    }

    override fun onOffsetsChanged(
        xOffset: Float, yOffset: Float, xOffsetStep: Float, yOffsetStep: Float,
        xPixelOffset: Int, yPixelOffset: Int
    ) {
        // Implement this method as needed
    }

    override fun onTouchEvent(event: MotionEvent) {
        // Implement this method as needed
    }
}