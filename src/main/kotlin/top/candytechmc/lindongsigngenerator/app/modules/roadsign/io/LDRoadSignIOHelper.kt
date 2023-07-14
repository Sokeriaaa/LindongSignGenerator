package top.candytechmc.lindongsigngenerator.app.modules.roadsign.io

import top.candytechmc.lindongsigngenerator.app.modules.roadsign.LDRoadData
import top.candytechmc.lindongsigngenerator.arch.io.FileHelper
import java.io.*

/**
 * IO Helper
 * @author LovelySokeriaaa
 * @date 2023/1/7
 */
object LDRoadSignIOHelper {

    fun readData(): List<LDRoadData> {

        // Create folder
        val dataFolder = File("data/roadsign/")
        if (!dataFolder.exists()) {
            dataFolder.mkdirs()
        }

        val roadsFile = FileHelper.getOrCreate("data/roadsign/roads.txt") {
            var fos: FileOutputStream? = null
            var osw: OutputStreamWriter? = null
            try {
                fos = FileOutputStream(it)
                osw = OutputStreamWriter(fos, "UTF-8")
                // Write default data
                osw.appendLine("机场路|JICHANG LU|0|#004796")
                osw.appendLine("建业街|JIANYE JIE|1|#1E892C")
                osw.flush()
            } catch (ex: IOException) {
                throw ex
            } finally {
                fos?.close()
                osw?.close()
            }
            println()
            println("注意：道路数据文件roads.txt不存在或损坏，已重新生成。")
        }

        var reader: BufferedReader? = null
        var fis: FileInputStream? = null
        var isr: InputStreamReader? = null
        try {
            fis = FileInputStream(roadsFile)
            isr = InputStreamReader(fis, "UTF-8")
            reader = BufferedReader(isr)
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
            fis?.close()
            isr?.close()
        }
    }

}