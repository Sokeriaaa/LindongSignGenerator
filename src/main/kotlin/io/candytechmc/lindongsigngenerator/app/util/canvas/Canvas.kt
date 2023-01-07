package io.candytechmc.lindongsigngenerator.app.util.canvas

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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

    val bufferedImage by lazy { BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB) }

    val g2 by lazy { bufferedImage.createGraphics() }

    abstract fun onDraw()

    fun outputTo(file: File) {
        CoroutineScope(Dispatchers.IO).launch {
            onDraw()
            ImageIO.write(bufferedImage, "png", file)
        }
    }


}