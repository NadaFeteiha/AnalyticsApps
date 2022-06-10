package datasource

import model.App

interface DataSource {
    fun getAllApps(fileName:String):List<App>?
}
