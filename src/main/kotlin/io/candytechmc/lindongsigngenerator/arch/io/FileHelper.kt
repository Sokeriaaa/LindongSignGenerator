package io.candytechmc.lindongsigngenerator.arch.io

import java.io.File

/**
 * File Helper
 * @author LovelySokeriaaa
 * @date 2023/1/7
 */
object FileHelper {

    fun getOrCreate(
        filePath: String
    ): File = File(filePath).also {
        if (!it.exists()) {
            it.createNewFile()
        }
    }

}