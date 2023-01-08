package io.candytechmc.lindongsigngenerator.app.conf

import io.candytechmc.lindongsigngenerator.arch.io.FileHelper
import io.candytechmc.lindongsigngenerator.arch.io.IniHelper
import org.ini4j.Ini

/**
 * App Configs
 * @author LovelySokeriaaa
 * @date 2023/1/7
 */
object AppConfigs {

    const val version: String = "0.1.1"

    private val ini: Ini by lazy {
        val file = FileHelper.getOrCreate("configs/conf.ini")
        IniHelper.readIniFromFile(file) ?: error("读取配置文件失败！")
    }

    object RoadSign {

        val fontCN: String by lazy { ini["RoadSign", "RSFontCN"] }
        val fontEN: String by lazy { ini["RoadSign", "RSFontEN"] }
        val spaceGapCN: Int by lazy { ini["RoadSign", "RSSpaceGapCN"].toIntOrNull() ?: 0 }
        val disableSpaceGapAt: Int by lazy { ini["RoadSign", "RSDisableSpaceGapAt"].toIntOrNull() ?: 0 }
        val textOffsetEN: Int by lazy { ini["RoadSign", "RSTextOffsetEN"].toIntOrNull() ?: 0 }
        val textOffsetDirCN: Int by lazy { ini["RoadSign", "RSTextOffsetDirCN"].toIntOrNull() ?: 0 }
        val textOffsetNameCN: Int by lazy { ini["RoadSign", "RSTextOffsetNameCN"].toIntOrNull() ?: 0 }
        val outputWidth: Int by lazy { ini["RoadSign", "RSOutputWidth"].toIntOrNull() ?: 0 }
        val outputHeight: Int by lazy { ini["RoadSign", "RSOutputHeight"].toIntOrNull() ?: 0 }

    }

}