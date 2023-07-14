package top.candytechmc.lindongsigngenerator.arch.io

import org.ini4j.Ini
import java.io.File
import java.io.IOException

/**
 * ini files helper
 * @author LovelySokeriaaa
 * @date 2023/1/7
 */
object IniHelper {

    fun writeIniFile(
        iniFile: File,
        entries: List<IniEntry>
    ): Int {
        try {
            val ini = readIniFromFile(iniFile) ?: return -1
            entries.forEach {
                ini.add(it)
            }
            ini.store(iniFile)
        } catch (ex: IOException) {
            return -1
        }
        return 0
    }

    fun readIniFromFile(
        iniFile: File,
    ): Ini? {
        return try {
            Ini().apply {
                load(iniFile)
            }
        } catch (ex: IOException) {
            null
        }
    }

    fun Ini.add(
        entry: IniEntry
    ) {
        this.add(entry.section, entry.key, entry.value)
    }

}