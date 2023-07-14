package top.candytechmc.lindongsigngenerator.app.modules.roadsign

import top.candytechmc.lindongsigngenerator.app.conf.AppConfigs
import top.candytechmc.lindongsigngenerator.app.util.canvas.Canvas
import top.candytechmc.lindongsigngenerator.arch.graph.drawAlignedString
import java.awt.Color
import java.awt.Font

/**
 * File description here
 * @author LovelySokeriaaa
 * @date 2023/1/7
 */
class LDRoadSignCanvas(
    val roadData: LDRoadData,
    val way: Int,
) : Canvas(
    width = 2560,
    height = 1280
) {

    private val dirText = if (roadData.direction == 0) {
        arrayOf("西", "W", "东", "E")
    } else {
        arrayOf("南", "S", "北", "N")
    }

    override fun onDraw() {
        drawContent(y = 640)
    }

    private fun drawContent(y: Int) {
        val themeColor = Color.decode(roadData.colorText)

        val leftX = 180
        val rightX = width - leftX
        val centerY = y + 240 + 20

        val cnDirTextY = centerY + AppConfigs.RoadSign.textOffsetDirCN
        val cnNameTextY = centerY + AppConfigs.RoadSign.textOffsetNameCN
        val enCenterY = y + 540 + AppConfigs.RoadSign.textOffsetEN

        val cnRoadName = if (
            AppConfigs.RoadSign.spaceGapCN <= 0 ||
            roadData.nameCN.length >= AppConfigs.RoadSign.disableSpaceGapAt
        ) {
            roadData.nameCN
        } else {
            roadData.nameCN.toCharArray().joinToString(separator = " ".repeat(AppConfigs.RoadSign.spaceGapCN))
        }

        // Backgrounds

        g2.color = Color.WHITE
        g2.fillRect(0, y, width, 640)

        g2.color = themeColor
        g2.fillRect(40, y + 40, width - 80, 480 - 40)

        g2.color = Color.WHITE

        // Chinese Road Name
        g2.font = Font(AppConfigs.RoadSign.fontCN, Font.BOLD, 300)
        g2.drawAlignedString(cnRoadName, width / 2, cnNameTextY, 1)

        val leftTextX = leftX + 18
        val rightTextX = rightX - 18
        drawArrowAt(
            centerX = leftX,
            centerY = centerY,
            isLeft = true
        )
        drawArrowAt(
            centerX = rightX,
            centerY = centerY,
            isLeft = false
        )

        // English Road Name
        g2.color = themeColor
        g2.font = Font(AppConfigs.RoadSign.fontEN, Font.BOLD, 120)
        g2.drawAlignedString(roadData.nameEN, width / 2, enCenterY, 1)

        // Direction Text
        g2.drawAlignedString(
            dirText[(way * 2 + 1) % 4],
            leftTextX,
            enCenterY,
            1
        )
        g2.drawAlignedString(
            dirText[(way * 2 + 3) % 4],
            rightTextX,
            enCenterY,
            1
        )
        g2.font = Font(AppConfigs.RoadSign.fontCN, Font.BOLD, 100)
        g2.drawAlignedString(
            dirText[(way * 2) % 4],
            leftTextX,
            cnDirTextY,
            1
        )
        g2.drawAlignedString(
            dirText[(way * 2 + 2) % 4],
            rightTextX,
            cnDirTextY,
            1
        )
    }

    private fun drawArrowAt(
        centerX: Int,
        centerY: Int,
        isLeft: Boolean
    ) {
        val triXList = if (isLeft) {
            intArrayOf(
                centerX + 14,
                centerX + 14,
                centerX - 68
            )
        } else {
            intArrayOf(
                centerX - 14,
                centerX - 14,
                centerX + 68
            )
        }
        val triYList = intArrayOf(
            centerY - 150,
            centerY + 150,
            centerY
        )
        val rectX = if (isLeft) {
            centerX + 14
        } else {
            centerX - 72
        }
        g2.fillPolygon(
            triXList,
            triYList,
            3
        )
        g2.fillRect(
            rectX,
            centerY - 74,
            58,
            148
        )
    }

}