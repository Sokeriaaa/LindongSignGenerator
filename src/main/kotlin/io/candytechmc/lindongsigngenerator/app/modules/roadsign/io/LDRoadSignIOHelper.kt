package io.candytechmc.lindongsigngenerator.app.modules.roadsign.io

import io.candytechmc.lindongsigngenerator.app.modules.roadsign.LDRoadData
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException

/**
 * IO Helper
 * @author LovelySokeriaaa
 * @date 2023/1/7
 */
object LDRoadSignIOHelper {

    fun readData(): List<LDRoadData> {
        var reader: BufferedReader? = null
        try {
            reader = BufferedReader(
                FileReader(
                    File("data/roadsign/roads.txt")
                )
            )
            val list = mutableListOf<LDRoadData>()
            while (true) {
                val line = reader.readLine() ?: break
                val lineSplit = line.split('|')
                list.add(
                    LDRoadData(
                        nameCN = lineSplit[0],
                        nameEN = lineSplit[1],
                        direction = lineSplit[2].toInt(),
                        colorText = lineSplit[3],
                    )
                )
            }
            return list
        } catch (ex: IOException) {
            throw ex
        } finally {
            reader?.close()
        }
    }

}