package io.candytechmc.lindongsigngenerator.app.modules.roadsign

import io.candytechmc.lindongsigngenerator.app.conf.AppConfigs
import io.candytechmc.lindongsigngenerator.app.util.canvas.Canvas
import io.candytechmc.lindongsigngenerator.arch.graph.drawAlignedString
import java.awt.Color
import java.awt.Font

/**
 * File description here
 * @author LovelySokeriaaa
 * @date 2023/1/7
 */
class LDRoadSignCanvas(
    val roadData: LDRoadData,
    val type: Int
) : Canvas(
    width = 2592,
    height = 1266
) {

    private val dirText = if (roadData.direction == 0) {
        arrayOf("西", "W", "东", "E")
    } else {
        arrayOf("南", "S", "北", "N")
    }

    override fun onDraw() {
        drawTopBar()
        drawContent(atRow = 0)
        drawContent(atRow = 1)
    }

    private fun drawTopBar() {
        g2.color = Color(166, 166, 166)
        g2.fillRect(0, 0, width, 32)

        g2.color = Color(255, 192, 0)
        g2.fillRect(61, 0, 56, 32)

        g2.color = Color(255, 255, 255)
        g2.fillRect(117, 0, 56, 32)
    }

    private fun drawContent(atRow: Int) {
        val themeColor = Color.decode(roadData.colorText)
        val startY = 32 + 617 * atRow

        val leftX = 180
        val rightX = width - leftX
        val centerY = startY + 231

        val cnDirTextY = centerY + AppConfigs.RoadSign.textOffsetDirCN
        val cnNameTextY = centerY + AppConfigs.RoadSign.textOffsetNameCN
        val enCenterY = startY + 540 + AppConfigs.RoadSign.textOffsetEN

        val cnRoadName = if (
            AppConfigs.RoadSign.spaceGapCN <= 0 ||
            roadData.nameCN.length >= AppConfigs.RoadSign.disableSpaceGapAt
        ) {
            roadData.nameCN
        } else {
            roadData.nameCN.toCharArray().joinToString(separator = " ".repeat(AppConfigs.RoadSign.spaceGapCN))
        }

        // Backgrounds
        g2.color = themeColor
        g2.fillRect(0, startY, width, 463)

        g2.color = Color.WHITE
        g2.fillRect(0, startY + 463, width, 154)

        // Chinese Road Name
        g2.font = Font(AppConfigs.RoadSign.fontCN, Font.BOLD, 300)
        g2.drawAlignedString(cnRoadName, width / 2, cnNameTextY, 1)

        // Arrows (Or circle)
        val leftIsArrow = type == 0 || atRow == 1
        val rightIsArrow = type == 0 || atRow == 0

        val leftTextX = if (leftIsArrow) {
            leftX + 18
        } else {
            leftX
        }
        val rightTextX = if (rightIsArrow) {
            rightX - 18
        } else {
            rightX
        }
        if (leftIsArrow) {
            drawArrowAt(
                centerX = leftX,
                centerY = centerY,
                isLeft = true
            )
        } else {
            g2.fillOval(
                leftX - 88,
                centerY - 88,
                177,
                177
            )
        }
        if (rightIsArrow) {
            drawArrowAt(
                centerX = rightX,
                centerY = centerY,
                isLeft = false
            )
        } else {
            g2.fillOval(
                rightX - 88,
                centerY - 88,
                177,
                177
            )
        }

        // English Road Name
        g2.color = themeColor
        g2.font = Font(AppConfigs.RoadSign.fontEN, Font.BOLD, 120)
        g2.drawAlignedString(roadData.nameEN, width / 2, enCenterY, 1)

        // Direction Text
        g2.drawAlignedString(
            dirText[((atRow + type) * 2 + 1) % 4],
            leftX,
            enCenterY,
            1
        )
        g2.drawAlignedString(
            dirText[((atRow + type) * 2 + 3) % 4],
            rightX,
            enCenterY,
            1
        )
        g2.font = Font(AppConfigs.RoadSign.fontCN, Font.BOLD, 100)
        g2.drawAlignedString(
            dirText[((atRow + type) * 2) % 4],
            leftTextX,
            cnDirTextY,
            1
        )
        g2.drawAlignedString(
            dirText[((atRow + type) * 2 + 2) % 4],
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