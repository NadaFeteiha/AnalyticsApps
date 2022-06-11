package utilities

import java.io.File

class FileReader(private var fileName: String, suffix:String) {

    init {
        if (!fileName.contains(suffix)) { fileName = fileName.plus(suffix) }
    }

    fun getStringInFile(): String? {
        File(fileName).apply {
            return if (this.exists()) { this.readText() }
            else { null }
        }
    }

    fun getListOFLinesInFile(): List<String>?{
        val lines = mutableListOf<String>()
        File(fileName).apply {
            if (this.exists()) {
                this.forEachLine { row -> lines.add(row) }
            }
            else { return null }
        }
        return lines
    }

}