package datasource
import model.App
import org.json.JSONArray
import org.json.JSONObject
import utilities.Constant
import utilities.convertToDouble
import utilities.converterToByte
import utilities.stringToDate


class JSONDataSource(private var fileName: String = Constant.FILE_NAME):DataSource {

    private val fileReader by lazy { FileReader(fileName,Constant.JSON_SUFFIX_FILE_NAME) }

    override fun getAllApps(fileName: String): List<App> {
        val apps = mutableListOf<App>()
        fileReader.getStringInFile().apply {
            if (!this.isNullOrBlank()) {
                JSONArray(this).onEach { any: Any? ->
                    JSONObject(any.toString()).also { jsonObject ->
                        parseJsonToApp(jsonObject)?.let { app -> apps.add(app) }
                    }
                }
            }
        }
        return if (apps.isNotEmpty()) { apps.distinctBy { app -> Pair(app.appName, app.company) } }
        else { null }
    }

    private fun parseJsonToApp(jsonObject: JSONObject): App? {
        return if (!jsonObject.isEmpty) {
            App(
                appName = jsonObject.getString(Constant.ColumnName.APP_NAME),
                company =  jsonObject.getString(Constant.ColumnName.COMPANY),
                category = jsonObject.getString(Constant.ColumnName.CATEGORY),
                updated =  jsonObject.getString(Constant.ColumnName.UPDATE_DATE).stringToDate(),
                size = jsonObject.getString(Constant.ColumnName.SIZE).converterToByte(),
                installs = jsonObject.getLong(Constant.ColumnName.INSTALLS),
                requiresAndroid = jsonObject.getString(Constant.ColumnName.REQUIRED_ANDROID).convertToDouble(),
            )
        } else {
            null
        }
    }
}