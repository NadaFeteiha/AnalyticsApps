import datasource.CSVDataSource
import datasource.DataSource
import datasource.JSONDataSource
import model.App
import utilities.Constant

fun main() {
    val appAnalyzer = AppAnalyzer()

    val jsonData: DataSource = JSONDataSource()
    var appList = jsonData.getAllApps()
    println("Using JSON: ")
    printAnalyzer(appAnalyzer,appList)

    val csvData = CSVDataSource()
    appList = csvData.getAllApps()
    println("Using CSV: ")
    printAnalyzer(appAnalyzer,appList)
}


fun printAnalyzer(appAnalyzer:AppAnalyzer, appList:List<App>?){
    if (appList != null){
    println("The numbers of apps develop by google \n${appAnalyzer.findAppDevelopedByGivenCompany(appList, "Google")} apps")
    println("-----------------------------------------------------------")
    println("The percentage of Medical apps is \n% ${appAnalyzer.findPercentageOfAppsByCategory(appList, "Medical")} ")
    println("-----------------------------------------------------------")
    println("The oldest app in the dataset is \n${appAnalyzer.findOldestApp(appList)}")
    println("-----------------------------------------------------------")
    println("The percentage of apps running on android 9 and up only is \n% ${appAnalyzer.findPercentageOfAppRunningOnSpecificAndroid(appList, 9.0)}")
    println("-----------------------------------------------------------")
    println("The largest 10 apps in the dataset are: \n${appAnalyzer.findLargestApps(appList, 10)}")
    println("-----------------------------------------------------------")
    println("The top 10 installed apps in the dataset are: \n${appAnalyzer.topAppInstall(appList, 10)}")
    println("-----------------------------------------------------------")
    println("The largest app developed by Meta Platforms Inc. is \n${appAnalyzer.getLargestAppSizeByCompanyName(appList, "Meta Platforms Inc.")}")
    println("=====================================================================")
    }}
