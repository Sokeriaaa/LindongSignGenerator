package io.candytechmc.lindongsigngenerator

import io.candytechmc.lindongsigngenerator.app.modules.roadsign.LDRoadSignCanvas
import io.candytechmc.lindongsigngenerator.app.modules.roadsign.io.LDRoadSignIOHelper
import io.candytechmc.lindongsigngenerator.app.vc.VCHelper
import java.io.File

fun main() {
    println("临东道路标识牌生成器")
    println("程序设计：Sokeriaaa")

    VCHelper.handleConfVersion()

    val outputFolder = File("output/")
    if (!outputFolder.exists()) {
        outputFolder.mkdirs()
    }

    val roadList = LDRoadSignIOHelper.readData()

    roadList.forEach {
        val canvas1 = LDRoadSignCanvas(
            roadData = it,
            type = 0
        )
        val canvas2 = LDRoadSignCanvas(
            roadData = it,
            type = 1
        )
        val canvas3 = LDRoadSignCanvas(
            roadData = it,
            type = 2
        )

        val fileRoadName = it.nameEN.replace(" ", "").lowercase()

        canvas1.outputTo(
            File("output/lupai_${fileRoadName}.png")
        )
        canvas2.outputTo(
            File("output/lupai_${fileRoadName}_${if (it.direction == 0) "e" else "n"}.png")
        )
        canvas3.outputTo(
            File("output/lupai_${fileRoadName}_${if (it.direction == 0) "w" else "s"}.png")
        )
    }
}