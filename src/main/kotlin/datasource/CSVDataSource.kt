package datasource

import model.App
import utilities.*

class CSVDataSource : DataSource {

    override fun getAllApps(fileName:String): List<App> {
        val csvReader = CSVReader()
        val apps = mutableListOf<App>()
        csvReader.getTableRows(fileName)?.forEach { line ->
            val app = parseStringToApp(line)
            if(app!= null)
                apps.add(app)
        }
        //because there is no unique field in DataSource to used as primary key;
        // Used 2 foreign key as a primary key (appName and company)
        return apps.distinctBy { app -> Pair(app.appName, app.company) }
    }

    private fun parseStringToApp(appStr: String): App? {
        if (appStr.isNotEmpty() && appStr.isNotBlank()) {
            val appFields = appStr.split(",")
            // check there is 7 fields in the list
            return if (appFields.lastIndex == Constant.ColumnIndex.REQUIRED_ANDROID) {
                App(
                    appName = appFields[Constant.ColumnIndex.APP_NAME],
                    company = appFields[Constant.ColumnIndex.COMPANY],
                    category = appFields[Constant.ColumnIndex.CATEGORY],
                    updated = appFields[Constant.ColumnIndex.UPDATE_DATE].stringToDate(),
                    size = appFields[Constant.ColumnIndex.SIZE].converterToByte(),
                    installs = appFields[Constant.ColumnIndex.INSTALLS].toLong(),
                    requiresAndroid = appFields[Constant.ColumnIndex.REQUIRED_ANDROID].convertToDouble(),
                )
            } else {
                null
            }
        } else {
            return null
        }
    }
}