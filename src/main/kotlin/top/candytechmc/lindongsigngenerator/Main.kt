package top.candytechmc.lindongsigngenerator

import top.candytechmc.lindongsigngenerator.app.conf.AppConfigs
import top.candytechmc.lindongsigngenerator.app.modules.roadsign.LDRoadSignCanvas
import top.candytechmc.lindongsigngenerator.app.modules.roadsign.io.LDRoadSignIOHelper
import top.candytechmc.lindongsigngenerator.app.vc.VCHelper
import java.io.File

fun main() {
    println("临东道路标识牌生成器")
    println("版本号：${AppConfigs.version}")
    println("程序设计：Sokeriaaa")

    VCHelper.handleConfVersion()

    val outputFolder = File("output/")
    if (!outputFolder.exists()) {
        outputFolder.mkdirs()
    }

    val roadList = LDRoadSignIOHelper.readData()
    val size = roadList.size
    println()
    println("已读取到 $size 条道路数据，即将开始生成图片。。。")
    println()

    roadList.forEachIndexed { index, roadData ->
        println("正在生成第 ${index + 1} / $size 个 （${roadData.nameCN}）")
        val canvas1 = LDRoadSignCanvas(
            roadData = roadData,
            type = 0
        )
        val canvas2 = LDRoadSignCanvas(
            roadData = roadData,
            type = 1
        )
        val canvas3 = LDRoadSignCanvas(
            roadData = roadData,
            type = 2
        )

        val fileRoadName = roadData.nameEN.replace(" ", "").lowercase()

        canvas1.outputTo(
            file = File("output/lupai_${fileRoadName}.png"),
            scaleWidth = AppConfigs.RoadSign.outputWidth,
            scaleHeight = AppConfigs.RoadSign.outputHeight
        )
        canvas2.outputTo(
            file = File("output/lupai_${fileRoadName}_${if (roadData.direction == 0) "e" else "n"}.png"),
            scaleWidth = AppConfigs.RoadSign.outputWidth,
            scaleHeight = AppConfigs.RoadSign.outputHeight
        )
        canvas3.outputTo(
            file = File("output/lupai_${fileRoadName}_${if (roadData.direction == 0) "w" else "s"}.png"),
            scaleWidth = AppConfigs.RoadSign.outputWidth,
            scaleHeight = AppConfigs.RoadSign.outputHeight
        )
    }
    println("所有标牌已生成完毕，感谢使用此程序哦~")
}