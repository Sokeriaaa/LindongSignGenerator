package io.candytechmc.lindongsigngenerator.arch.graph

import java.awt.Graphics2D

fun Graphics2D.drawAlignedString(
    str: String,
    x: Int,
    y: Int,
    align: Int,
    width: Int = -1
) {
    val fm = fontMetrics
    val realY = y - fm.height / 2 + fm.ascent
    if (align == 0) {
        drawString(str, x, realY)
        return
    }
    drawString(
        str, x - align * (if (width < 0) fm.stringWidth(str) else width) / 2, realY
    )
}