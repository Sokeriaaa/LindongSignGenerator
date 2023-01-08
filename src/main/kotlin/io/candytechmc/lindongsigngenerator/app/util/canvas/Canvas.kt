package io.candytechmc.lindongsigngenerator.app.util.canvas

import java.awt.Graphics2D
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

/**
 * Canvas
 * @author LovelySokeriaaa
 * @date 2023/1/7
 */
abstract class Canvas(
    val width: Int,
    val height: Int
) {

    private val bufferedImage: BufferedImage by lazy { BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB) }

    val g2: Graphics2D by lazy {
        bufferedImage.createGraphics().also {
            it.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON)
        }
    }

    abstract fun onDraw()

    fun outputTo(
        file: File,
        scaleWidth: Int = width,
        scaleHeight: Int = height
    ) {
        onDraw()
        val outputImage: BufferedImage = if (scaleWidth == width && scaleHeight == height) {
            bufferedImage
        } else {
            val scaledImage = bufferedImage.getScaledInstance(scaleWidth, scaleHeight, BufferedImage.TYPE_INT_ARGB)
            BufferedImage(scaleWidth, scaleHeight, BufferedImage.TYPE_INT_ARGB).also {
                it.createGraphics().drawImage(scaledImage, 0, 0, null)
            }
        }
        ImageIO.write(outputImage, "png", file)
    }
}