package datasource

import utilities.Constant
import java.io.File

class CSVReader {
    fun getTableRows(fileName: String): List<String>? {
        val tableRows = mutableListOf<String>()
            if (!fileName.contains(Constant.SUFFIX_FILE_NAME)) {
                fileName.plus(Constant.SUFFIX_FILE_NAME) }
            else { fileName }.also {fName->
                File(fName).apply {
                    if (this.exists()) { this.forEachLine { row -> tableRows.add(row) } }
                    else { return null }
                }
            }
        return tableRows
    }
}