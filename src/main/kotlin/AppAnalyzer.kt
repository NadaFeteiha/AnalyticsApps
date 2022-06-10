import model.App
import utilities.Constant
import utilities.calculatePercentage

class AppAnalyzer {

    fun findAppDevelopedByGivenCompany(apps: List<App>, companyName: String): Int? {
        return getAllAppsCompany(apps, companyName)?.count()
    }

    fun findPercentageOfAppsByCategory(apps: List<App>, categoryName: String): Double? {
        return if (apps.isNotEmpty() && categoryName.isNotEmpty()) {
            apps.count { count -> count.category.contains(categoryName.trim(), true) }
                .calculatePercentage(apps.size)
        }
        else { null }
    }

    fun findOldestApp(apps: List<App>): App? {
        return if (apps.isNotEmpty()) {
            apps.filterNot { app -> app.updated == null }.minByOrNull { selector -> selector.updated!! }
        }
        else { null }
    }

    fun findPercentageOfAppRunningOnSpecificAndroid(apps: List<App>, version: Double): Double? {
        return if (apps.isNotEmpty() && version >= Constant.MIN_COMPARE_INT) {
            apps.count { count -> count.requiresAndroid != null && count.requiresAndroid == version }
                .calculatePercentage(apps.size)
        }
        else { null }
    }

    fun findLargestApps(app: List<App>, rankSize: Int ): List<App>? {
        return if (app.isNotEmpty() && rankSize in Constant.MIN_COMPARE_INT..app.size) {
            app.sortedByDescending { dataSorted -> dataSorted.size }.take(rankSize)
        }
        else { null }
    }

    fun topAppInstall(apps: List<App>, size: Int): List<App>? {
        return if (apps.isNotEmpty() && size in Constant.MIN_COMPARE_INT..apps.size) {
            apps.sortedByDescending { dataSorted -> dataSorted.installs }
                .take(size)
        }
        else { null }
    }

    fun getLargestAppSizeByCompanyName(apps: List<App>, companyName: String): App? {
        return getAllAppsCompany(apps, companyName)?.let {appList ->
            findLargestApps(appList,1)?.first() //replace magic number or make it optional
        }
    }

    private fun getAllAppsCompany(apps: List<App>, companyName: String): List<App>? {
        return if (apps.isNotEmpty() && companyName.isNotEmpty()) {
            apps.filter { app: App ->  app.company.contains(companyName.trim(), true) }
        }
        else { null }
    }
}
