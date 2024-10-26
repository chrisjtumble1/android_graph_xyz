package com.example.myapplication

import android.content.Context
import org.rajawali3d.renderer.Renderer
import org.rajawali3d.scene.Scene
import org.rajawali3d.math.vector.Vector3
import org.rajawali3d.primitives.Line3D
import org.rajawali3d.materials.Material
import org.rajawali3d.materials.methods.DiffuseMethod
import org.rajawali3d.lights.DirectionalLight

class MyRenderer(context: Context) : Renderer(context) {

    override fun initScene() {
        val light = DirectionalLight(1.0, 0.2, -1.0)
        light.setColor(1.0, 1.0, 1.0)
        light.setPower(2.0)
        currentScene.addLight(light)

        // Create X, Y, Z axes
        val xAxis = Line3D(listOf(Vector3(-10.0, 0.0, 0.0), Vector3(10.0, 0.0, 0.0)), 1.0)
        val yAxis = Line3D(listOf(Vector3(0.0, -10.0, 0.0), Vector3(0.0, 10.0, 0.0)), 1.0)
        val zAxis = Line3D(listOf(Vector3(0.0, 0.0, -10.0), Vector3(0.0, 0.0, 10.0)), 1.0)

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
        val points = mutableListOf<Vector3>()
        for (x in -10..10) {
            for (y in -10..10) {
                val z = function(x.toDouble(), y.toDouble(), 0.0).z
                points.add(Vector3(x.toDouble(), y.toDouble(), z))
            }
        }
        val line = Line3D(points, 1.0)
        val material = Material()
        material.diffuseMethod = DiffuseMethod.Lambert()
        line.material = material
        currentScene.addChild(line)
    }
}