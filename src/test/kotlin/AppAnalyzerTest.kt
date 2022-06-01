import model.App
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import utilities.TestConstant
import java.util.*

//import org.junit.jupiter.api.Assertions.*
//import java.time.LocalDate
//import java.time.format.DateTimeFormatter
//
//import org.junit.jupiter.api.function.Executable
//import utilities.Constant
//import java.text.SimpleDateFormat

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class AppAnalyzerTest {

    private lateinit var appAnalyzer: AppAnalyzer
    private lateinit var apps: MutableList<App>

    @BeforeAll
    fun setup() { appAnalyzer = AppAnalyzer() }

    @BeforeEach
    fun initialize() { apps=  mutableListOf() }

    private fun setList(testSet:Int =0):MutableList<App>{
        val  appList=  mutableListOf<App>()
        when(testSet){
            TestConstant.CHANGE_SIZE_UPPER_LOWER_CASE ->{
                appList.add(App("Books", "Amazon", "Libraries & Demo", Date("5/1/2000"), "21M", 500000, "1.0.0", 4.4))
                appList.add( App("AD", "Amazon", "Libraries & Demo", Date("1/1/2020"), "30k", 30, "1.0.0", 9.0))
                appList.add(App("Google Photo", "Google ", "Libraries & Demo", Date("2/1/2000"), "5.5g", 500, "1.0.0", 6.0))
                appList.add(App("Google Files", "Google", "Medical", Date("1/1/2019"), "5g", 1000000, "1.0.0", 9.0))
            }
            else ->{
                appList.add(App("Books", "Amazon", "Libraries & Demo", Date("5/1/2000"), "21M", 500000, "1.0.0", 4.4))
                appList.add(App("AD", "Amazon", "Libraries & Demo", Date("1/1/2020"), "30M", 30, "1.0.0", 9.0))
                appList.add(App("Google Photo", "Google", "Libraries & Demo", Date("2/1/2000"), "50M", 500, "1.0.0", 6.0))
                appList.add(App("Google Files", "Google", "Medical", Date("1/1/2019"), "5M", 1000000, "1.0.0", 9.0))
            }
        }
        return appList
    }

    /////////////////////// Start test "findAppDevelopedByGivenCompany" function  ///////////////////////
    // should_ReturnNumber1_When_HaveListWith1GoogleApp
    @Test
    fun should_ReturnApps_ThatDeveloped_WithDeterminedCompany() {
        // given list & some the company name
        apps = setList()
        val companyName="Google"
        // when calculate number of Apps developed by this company
        val numberOfApps = appAnalyzer.findAppDevelopedByGivenCompany(apps,companyName)
        // then check the result
        assertEquals(2, numberOfApps)
    }

    @Test
    fun should_ReturnZero_When_HaveListWithNoAppsOfDeterminedCompany() {
        // given list that doesn't contain the determined company
        apps = setList()
        var companyName="Daovude"
        // when calculate number of Apps
        val numberOfApps = appAnalyzer.findAppDevelopedByGivenCompany(apps,companyName)
        // then check the result
        assertEquals(0, numberOfApps)
    }

    @Test
    fun should_ReturnZero_When_HaveNullList() {
        // given null list
        apps = mutableListOf()
        val companyName="Google"
        // when calculate number of Apps
        val numberOfApps = appAnalyzer.findAppDevelopedByGivenCompany(apps,companyName)
        // then check the result
        assertEquals(0, numberOfApps)
    }
/////////////////////// End test "findAppDevelopedByGivenCompany" function  ///////////////////////

/////////////////////// Start test "findPercentageOfAppsByCategory" function  ///////////////////////
    //should_ReturnCorrectPercentageOfMedicalApps_When_HaveListOfMedicalAppsOnly
    @Test
    fun should_ReturnCorrectPercentageOfCategoryApps_When_HaveListOfDeterminedCategoryOnly() {
        // given list of apps with 100% of the determined category
        apps = setList()
        val categoryName="Medical"
        // when calculate percentage
        val percentage = appAnalyzer.findPercentageOfAppsByCategory(apps,categoryName)
        // then check the result
        assertEquals(100.0, percentage)
    }

    @Test
    fun should_ReturnCorrectPercentageOfCategoryApps_When_HaveListOfAppsWithDeterminedCategory() {
        // given list of apps with 50% of the determined category
        apps = setList()
        val categoryName="Medical"
        // when calculate percentage
        val percentage = appAnalyzer.findPercentageOfAppsByCategory(apps,categoryName)
        // then check the result
        assertEquals(33.3, percentage)
    }

    //should_ReturnZero_When_HaveListOfAppsWithOutMedicalApp
    @Test
    fun should_ReturnZero_When_HaveListOfAppsWithOutDeterminedCategoryApp() {
        // given list of apps with zero of the Category apps
        apps = setList()
        val categoryName="someCategory"
        // when calculate percentage
        val percentage = appAnalyzer.findPercentageOfAppsByCategory(apps,categoryName)
        // then check the result
        assertEquals(0.0, percentage)
    }

    @Test
    fun should_ReturnZero_When_HaveEmptyList() {
        // given empty list
        apps =mutableListOf()
        val categoryName="Medical"
        // when calculate percentage
        val percentage = appAnalyzer.findPercentageOfAppsByCategory(apps,categoryName)
        // then check the result
        assertEquals(0.0, percentage)
    }
/////////////////////// End test "findPercentageOfAppsByCategory" function  ///////////////////////


/////////////////////// Start test "findOldestApp" function  ///////////////////////
    @Test
    fun should_ReturnOlderApp_When_HaveListWithApp() {
        // given list
        apps = setList()
        // when calculate number of Apps
        val olderApp = appAnalyzer.findOldestApp(apps)
        // then check the result
        assertEquals("Medical Mnemonics", olderApp)
    }

    @Test
    fun should_ReturnOlderApp_When_HaveListWithMultiApps() {
        // given list
        apps = setList()
        // when calculate number of Apps
        val olderApp = appAnalyzer.findOldestApp(apps)
        // then check the result
        assertEquals("Jewel Blast : Temple", olderApp)
    }

    @Test
    fun should_ReturnNull_When_HaveEmptyList() {
        // given empty list
        apps = setList()
        // when calculate number of Apps
        val olderApp = appAnalyzer.findOldestApp(apps)
        // then check the result
        assertNull(olderApp)
    }
/////////////////////// End test "findOldestApp" function  ///////////////////////

/////////////////////// Start test "findPercentageOfAppRunningOnSpecificAndroid" function  ///////////////////////
    @Test
    fun should_ReturnZero_When_NotAppHaveSpecificAndroidVersionAndUp() {
        //given list & the android version
         apps = setList()
         val androidVersion=1.0.0
        //when find the oldest app
        val percentage = appAnalyzer.findPercentageOfAppRunningOnSpecificAndroid(apps,androidVersion)
        //then
        assertEquals(0.0, percentage)
    }

    @Test
    fun should_ReturnCorrectPercentage_When_ListOfAppsHaveSpecificAndroidVersionAndUp() {
        //given list & the android version
        apps = setList()
        val androidVersion=1.0.0
        //when
        val percentage = appAnalyzer.findPercentageOfAppRunningOnSpecificAndroid(apps,androidVersion)
        //then
        assertEquals(33.3, percentage)
    }

    @Test
    fun should_ReturnZero_When_ListEmpty() {
        //given empty list
        apps = mutableListOf()
        val androidVersion=1.0.0
        //when
        val percentage = appAnalyzer.findPercentageOfAppRunningOnSpecificAndroid(apps,androidVersion)
        //then
        assertEquals(0.0, percentage)
    }
/////////////////////// End test "findPercentageOfAppRunningOnSpecificAndroid" function  ///////////////////////


/////////////////////// Start test "findLargestApps" function  ///////////////////////
    @Test
    fun should_ReturnTheLargestApps_Of_DeterminedRankSize_When_TheListOfAppsIsCorrect() {
        // given list & the rank size
         apps = setList()
         val rankSize=3
        // when fined the largest app of the list
        val result = appAnalyzer.findLargestApps(apps,rankSize)
        // then
        assertEquals(mutableListOf("Baby Game for 2 3 4 Year Old", "Crazy Pusher"), result)
    }

// should_ReturnNullValueAndNoLargestApp_When_TheListOfAppsIsEmpty
    @Test
    fun should_ReturnNullValueAndNoLargestApp_When_TheListOfAppsIsEmpty() {
        // given empty list
        apps = mutableListOf()
        val rankSize=3
        // when
        val result = appAnalyzer.findLargestApps(apps,rankSize)
        // then
        assertNull(result)
    }
/////////////////////// End test "findLargestApps" function  ///////////////////////

/////////////////////// Start test "findTopNumberInstalledApps" function  ///////////////////////
    //should_ReturnTop10InstalledAppName_When_TheListOfAppsIsCorrectAndContainsMoreThan9Apps
    @Test
    fun should_ReturnTopNumberInstalledAppName_When_TheListOfAppsIsCorrect() {
        // given list & the top number
        apps = setList()
        val topNumber=3
        // when find the top number installed apps
        val result = appAnalyzer.findTopNumberInstalledApps(apps,topNumber)
        // then
        assertEquals(mutableListOf("Dinosaur Airport:Game for kids", "Crazy Pusher"), result)
    }

    /////should_ReturnAllElementAppName_When_TheListOfAppsIsCorrectAndContainsLiseThan10Apps/////
    @Test
    fun should_ReturnNullValueAndNoTopInstalled_When_TheListOfAppsIsEmpty() {
        // given empty list
        apps= mutableListOf()
        val topNumber=2
        // when find the top installed apps name
        val result = appAnalyzer.findTopNumberInstalledApps(apps,topNumber)
        // then
        assertNull(result)
    }
/////////////////////// End test "findTopNumberInstalledApps" function  ///////////////////////

}