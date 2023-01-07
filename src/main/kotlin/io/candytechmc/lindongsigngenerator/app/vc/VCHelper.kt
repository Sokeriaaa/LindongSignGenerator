package io.candytechmc.lindongsigngenerator.app.vc

import io.candytechmc.lindongsigngenerator.arch.io.FileHelper
import io.candytechmc.lindongsigngenerator.arch.io.IniEntry
import io.candytechmc.lindongsigngenerator.arch.io.IniHelper
import io.candytechmc.lindongsigngenerator.arch.io.IniHelper.add
import org.ini4j.Ini

/**
 * Configs VC
 * @author LovelySokeriaaa
 * @date 2023/1/7
 */
object VCHelper {

    fun handleConfVersion(): Int {
        val file = FileHelper.getOrCreate("configs/conf.ini")
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