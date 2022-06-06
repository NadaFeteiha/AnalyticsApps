package datasource
import model.App
import org.json.JSONArray
import org.json.JSONObject
import utilities.Constant
import utilities.convertToDouble
import utilities.converterToByte
import utilities.stringToDate


class JSONDataSource:DataSource {

    override fun getAllApps(fileName: String): List<App> {
        val jsonReader = FileReader(fileName,Constant.JSON_SUFFIX_FILE_NAME)
        val apps = mutableListOf<App>()
        val jsonString= jsonReader.getStringInFile()

        if (!jsonString.isNullOrBlank()){
            val jsonArray = JSONArray(jsonString)
            jsonArray.forEach{
                val jsonObject =  JSONObject(it.toString())
                val app = parseJsonToApp(jsonObject)
                if (app != null) {
                    apps.add(app)
                }
            }
        }
        //because there is no unique field in DataSource to used as primary key;
        // Used 2 foreign key as a primary key (appName and company)
        return apps.distinctBy { app -> Pair(app.appName, app.company) }
    }

    private fun parseJsonToApp(jsonObject: JSONObject): App? {
        return if (!jsonObject.isEmpty) {
            App(
                appName = jsonObject.getString(Constant.ColumnName.APP_NAME),
                company =  jsonObject.getString(Constant.ColumnName.COMPANY),
                category = jsonObject.getString(Constant.ColumnName.CATEGORY),
                updated =  jsonObject.getString(Constant.ColumnName.UPDATE_DATE).stringToDate(),
                size = jsonObject.getString(Constant.ColumnName.SIZE).converterToByte(),
                installs = jsonObject.getInt(Constant.ColumnName.INSTALLS).toLong(),
                requiresAndroid = jsonObject.getString(Constant.ColumnName.REQUIRED_ANDROID).convertToDouble(),
            )
        } else {
            null
        }
    }
}