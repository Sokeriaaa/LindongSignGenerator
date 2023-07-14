package top.candytechmc.lindongsigngenerator.app.vc

import org.ini4j.Ini
import top.candytechmc.lindongsigngenerator.arch.io.FileHelper
import top.candytechmc.lindongsigngenerator.arch.io.IniEntry
import top.candytechmc.lindongsigngenerator.arch.io.IniHelper
import top.candytechmc.lindongsigngenerator.arch.io.IniHelper.add
import java.io.File

/**
 * Configs VC
 * @author LovelySokeriaaa
 * @date 2023/1/7
 */
object VCHelper {

    fun handleConfVersion(): Int {
        val confFolder = File("configs/")
        if (!confFolder.exists()) {
            confFolder.mkdirs()
        }

        val file = FileHelper.getOrCreate("configs/conf.ini") {
            println()
            println("注意：配置文件conf.ini不存在或损坏，已重新生成。")
        }
        val ini = IniHelper.readIniFromFile(file) ?: return -1

        updater0to1(ini)

        ini.store(file)
        return 0
    }

    private fun updater0to1(ini: Ini) {
        if (getIniVersion(ini) > 0) {
            return
        }
        setIniVersion(ini, 1)

        ini.add(
            "RoadSign",
            "RSFontCN",
            "未来荧黑 Normal Regular"
        )
        ini.add(
            "RoadSign",
            "RSFontEN",
            "未来荧黑 Normal Regular"
        )
        ini.add(
            "RoadSign",
            "RSSpaceGapCN",
            "1"
        )
        ini.add(
            "RoadSign",
            "RSDisableSpaceGapAt",
            "5"
        )
        ini.add(
            "RoadSign",
            "RSTextOffsetEN",
            "0"
        )
        ini.add(
            "RoadSign",
            "RSTextOffsetDirCN",
            "0"
        )
        ini.add(
            "RoadSign",
            "RSTextOffsetNameCN",
            "0"
        )
        ini.add(
            "RoadSign",
            "RSOutputWidth",
            "2560"
        )
        ini.add(
            "RoadSign",
            "RSOutputHeight",
            "1280"
        )
    }

    private fun getIniVersion(ini: Ini): Int {
        return ini["Main", "Version"]?.toInt() ?: 0
    }

    private fun setIniVersion(ini: Ini, version: Int) {
        ini.add(
            IniEntry(
                section = "Main",
                key = "Version",
                value = version.toString()
            )
        )
    }

}