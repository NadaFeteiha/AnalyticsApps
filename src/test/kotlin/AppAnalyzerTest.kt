import model.App
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInstance
import java.util.*

/*
   * Points That Will Be Tested:
   * 1. The apps that have been developed by Company.
   * 2. The percentage of category.
   * 3. The oldest app.
   * 4. The percentage of apps running on android specific version.
   * 5. The largest apps by size.
   * 6. The top installed apps.
   * 7. Tha the largest App in specific company
   *
   * Note: The (1-5 .. etc) that are below refers to:
   * left side is the point that will be tested.
   * right side is the number of test cases for that point.
   * */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class AppAnalyzerTest {

    private lateinit var appAnalyzer: AppAnalyzer
    private lateinit var apps: MutableList<App>

    /*
      * Indexes of the app list that define above.
      * We will use these indexes instead of the magic numbers.
      * */
    private val bookAppIndex = 0
    private val adAppIndex = 1
    private val googlePhotoAppIndex = 2
    private val googleFilesAppIndex = 3
    private val largestAmazonApp = 1

    /*
    * initialize the appAnalyzer class before all tests.
    * */
    @BeforeAll
    fun setup() {
        appAnalyzer = AppAnalyzer()
    }

    /*
    * before each test, reinitialize the apps list.
    * */
    @BeforeEach
    fun initializeApps() {
        apps = mutableListOf()
    }

    /*
    * Setup list of four demo applications just to test the appAnalyzer.
    * */
    private fun setList(testSet: Int = 0): MutableList<App> {
        val appList = mutableListOf<App>()
        appList.add(
            App(
                appName = "Book",
                company = "Amazon",
                category = "Libraries & Demo",
                updated = Date(2000, 5, 1),
                size = 6000,
                installs = 500000,
                requiresAndroid = 4.4
            )
        )
        appList.add(
            App(
                appName = "AD",
                company = "Amazon",
                category = "Libraries & Demo",
                updated = Date(2020, 1, 1),
                size = 30000,
                installs = 30,
                requiresAndroid = 9.0
            )
        )
        appList.add(
            App(
                appName = "Google Photo",
                company = "Google",
                category = "Libraries & Demo",
                updated = Date(2000, 1, 2),
                size = 50000,
                installs = 500,
                requiresAndroid = 6.0
            )
        )
        appList.add(
            App(
                appName = "Google Files",
                company = "Google",
                category = "Medical",
                updated = Date(2000, 1, 2),
                size = 5000,
                installs = 1000000,
                requiresAndroid = 9.0
            )
        )
        return appList
    }


    /*
    * Point 1: The apps that have been developed by Company.
    * No. of test cases: 7
    * */

    @Test // 1-1
    fun should_ReturnNumOfAppsDevelopedByCompany_When_ValidCompanyInput() {
        // given a list of apps.
        apps = setList()
        // when the company name is completely valid.
        val companyName = "Google"
        val result = appAnalyzer.findAppDevelopedByGivenCompany(apps, companyName)
        // then check the number of apps developed by the company.
        assertEquals(2, result)
    }

    @Test // 1-2
    fun should_ReturnNumOfAppsDevelopedByCompany_When_CompanyNameContainsSpaces() {
        // given a list of apps.
        apps = setList()
        // when the company name contains spaces and the company name is Google.
        val companyName = " Google "
        val result = appAnalyzer.findAppDevelopedByGivenCompany(apps, companyName)
        // then check the number of apps developed by the company.
        assertEquals(2, result)
    }

    @Test // 1-3
    fun should_ReturnNumOfAppsDevelopedByCompany_When_CompanyNameInUpperCase() {
        // given a list of apps.
        apps = setList()
        // when the company name is in upper case and the company name is Google.
        val companyName = "GOOGLE"
        val result = appAnalyzer.findAppDevelopedByGivenCompany(apps, companyName)
        // then check the number of apps developed by the company.
        assertEquals(2, result)
    }

    @Test // 1-4
    fun should_ReturnNumOfAppsDevelopedByCompany_When_CompanyNameInMixedCase() {
        // given a list of apps.
        apps = setList()
        // when the company name is in mixed case and the company name is Google.
        val companyName = "gOOgLE"
        val result = appAnalyzer.findAppDevelopedByGivenCompany(apps, companyName)
        // then check the number of apps developed by the company.
        assertEquals(2, result)
    }

    @Test // 1-5
    fun should_ReturnZeroOfAppsDevelopedByCompany_When_CompanyNameIsNotFound() {
        // given a list of apps.
        apps = setList()
        // when the company name is not found.
        val companyName = "UnKnownCompany"
        val result = appAnalyzer.findAppDevelopedByGivenCompany(apps, companyName)
        // then check the number of apps developed by the company.
        assertEquals(0, result)
    }

    @Test // 1-6
    fun should_ReturnNullOfAppsDevelopedByCompany_When_CompanyNameIsEmpty() {
        // given a list of apps.
        apps = setList()
        // when the company name is empty.
        val companyName = ""
        val result = appAnalyzer.findAppDevelopedByGivenCompany(apps, companyName)
        // then check the number of apps developed by the company.
        assertNull(result)
    }

    @Test // 1-7
    fun should_ReturnNullOfAppsDevelopedByCompany_When_AppListIsEmpty() {
        // given empty list of apps.
        apps
        // when the app list is empty and the company name is Google.
        val companyName = "Google"
        val result = appAnalyzer.findAppDevelopedByGivenCompany(apps, companyName)
        // then check the number of apps developed by the company.
        assertNull(result)
    }

    /*
    * Point 2: The percentage of category.
    * No. of test cases: 8
    * */

    @Test // 2-1
    fun should_ReturnPercentageOfGivenCategory_When_AppListIsNotEmpty() {
        // given a list of apps.
        apps = setList()
        // when the app list is not empty and the category is medical.
        val category = "Medical"
        val result = appAnalyzer.findPercentageOfAppsByCategory(apps, category)
        // then check the percentage of apps in the category.
        assertEquals(25.0, result)
    }

    @Test // 2-2
    fun should_ReturnPercentageOfGivenCategory_When_CategoryContainsSpaces() {
        // given a list of apps.
        apps = setList()
        // when the category contains spaces and the category is medical.
        val category = " Medical   "
        val result = appAnalyzer.findPercentageOfAppsByCategory(apps, category)
        // then check the percentage of apps in the category.
        assertEquals(25.0, result)
    }

    @Test // 2-3
    fun should_ReturnZero_When_CategoryContainsDots() {
        // given a list of apps.
        apps = setList()
        // when the category contains dots and the category is medical.
        val category = ".Medical.."
        val result = appAnalyzer.findPercentageOfAppsByCategory(apps, category)
        // then check the percentage of apps in the category.
        assertEquals(0.0, result)
    }

    @Test // 2-4
    fun should_ReturnPercentageOfGivenCategory_When_CategoryInUpperCase() {
        // given a list of apps.
        apps = setList()
        // when the category is in upper case and the category is medical.
        val category = "MEDICAL"
        val result = appAnalyzer.findPercentageOfAppsByCategory(apps, category)
        // then check the percentage of apps in the category.
        assertEquals(25.0, result)
    }

    @Test // 2-5
    fun should_ReturnPercentageOfGivenCategory_When_CategoryInMixedCase() {
        // given a list of apps.
        apps = setList()
        // when the category is in mixed case and the category is medical.
        val category = "mEdIcAl"
        val result = appAnalyzer.findPercentageOfAppsByCategory(apps, category)
        // then check the percentage of apps in the category.
        assertEquals(25.0, result)
    }

    @Test // 2-6
    fun should_ReturnNullOfCategory_When_AppListIsEmpty() {
        // given an empty list of apps.
        apps
        // when the app list is empty and the category is medical.
        val category = "Medical"
        val result = appAnalyzer.findPercentageOfAppsByCategory(apps, category)
        // then check the percentage of apps in the category.
        assertNull(result)
    }

    @Test // 2-7
    fun should_ReturnNullOfCategory_When_CategoryIsEmpty() {
        // given a list of apps.
        apps = setList()
        // when the category is empty.
        val category = ""
        val result = appAnalyzer.findPercentageOfAppsByCategory(apps, category)
        // then check the percentage of apps in the category.
        assertNull(result)
    }

    @Test // 2-8
    fun should_ReturnZeroOfCategory_When_CategoryIsNotFound() {
        // given a list of apps.
        apps = setList()
        // when the category is not found.
        val category = "NotFound"
        val result = appAnalyzer.findPercentageOfAppsByCategory(apps, category)
        // then check the percentage of apps in the category.
        assertEquals(0.0, result)
    }

    /*
    * Point 3: The oldest app.
    * No. of test cases: 2
    * */

    @Test // 3-1
    fun should_ReturnOldestApp_When_ValidAppList() {
        // given a list of apps.
        apps = setList()
        // when the app list is valid.
        val result = appAnalyzer.findOldestApp(apps)
        // then check the oldest app in the list , which is the Google Photo app.
        assertEquals(apps[googlePhotoAppIndex], result)
    }

    @Test // 3-2
    fun should_ReturnNullOfOldestApp_When_AppListIsEmpty() {
        // given an empty list of apps.
        apps
        // when the app list is empty.
        val result = appAnalyzer.findOldestApp(apps)
        // then check the oldest app in the list.
        assertNull(result)
    }

    /*
    * Point 4: The percentage of apps running on android specific version.
    * No. of test cases: 6
    * */

    @Test // 4-1
    fun should_ReturnPercentageOfAppsRunningOnAndroidSpecificVersion_When_ValidAppListAndVersion() {
        // given a list of apps.
        apps = setList()
        // when the app list is valid and android version is valid.
        val version = 9.0
        val result = appAnalyzer.findPercentageOfAppRunningOnSpecificAndroid(apps, version)
        // then check the percentage of apps running on specific android version.
        assertEquals(50.0, result)
    }

    @Test // 4-2
    fun should_ReturnPercentageOfAppsRunningOnAndroidSpecificVersion_When_AndroidVersionIsPositive() {
        // given a list of apps.
        apps = setList()
        // when the app list is valid and version is positive.
        val version = +9.0
        val result = appAnalyzer.findPercentageOfAppRunningOnSpecificAndroid(apps, version)
        // then check the percentage of apps running on specific android version.
        assertEquals(50.0, result)
    }

    @Test // 4-3
    fun should_ReturnZero_When_AndroidSpecificVersionIsNotFound() {
        // given a list of apps.
        apps = setList()
        // when the app list is valid and version is not found.
        val version = 14.0
        val result = appAnalyzer.findPercentageOfAppRunningOnSpecificAndroid(apps, version)
        // then check the percentage of apps running on specific android version.
        assertEquals(0.0, result)
    }

    @Test // 4-4
    fun should_ReturnNullOfPercentage_When_AppListIsEmpty() {
        // given an empty list of apps.
        apps
        // when the app list is empty and version is valid.
        val version = 9.0
        val result = appAnalyzer.findPercentageOfAppRunningOnSpecificAndroid(apps, version)
        // then check the percentage of apps running on specific android version.
        assertNull(result)
    }

    @Test // 4-5
    fun should_ReturnNullOfPercentage_When_AndroidVersionIsZero() {
        // given a list of apps.
        apps = setList()
        // when the app list is valid and version is zero.
        val version = 0.0
        val result = appAnalyzer.findPercentageOfAppRunningOnSpecificAndroid(apps, version)
        // then check the percentage of apps running on specific android version.
        assertNull(result)
    }

    @Test // 4-6
    fun should_ReturnNullOfPercentage_When_AndroidVersionIsNegative() {
        // given a list of apps.
        apps = setList()
        // when the app list is valid and version is negative.
        val version = -9.0
        val result = appAnalyzer.findPercentageOfAppRunningOnSpecificAndroid(apps, version)
        // then check the percentage of apps running on specific android version.
        assertNull(result)
    }


    /*
    * Point 5: The largest apps by size.
    * No. of test cases: 5
    * */

    @Test // 5-1
    fun should_ReturnLargestApps_When_ValidAppListAndRange() {
        // given a list of apps.
        apps = setList()
        // when the app list is valid and range is valid.
        val range = 4
        val result = appAnalyzer.findLargestApps(apps, range)
        // then check the largest apps in the list.
        val expectedResult =
            mutableListOf(apps[googlePhotoAppIndex], apps[adAppIndex], apps[bookAppIndex], apps[googleFilesAppIndex])
        assertEquals(expectedResult, result)
    }

    @Test // 5-2
    fun should_ReturnNull_When_RangeIsBiggerThanAppListSize() {
        // given a list of apps.
        apps = setList()
        // when the app list is valid and range is bigger than the size of the list.
        val range = apps.size.plus(1)
        val result = appAnalyzer.findLargestApps(apps, range)
        // then check the largest apps in the list.
        assertNull(result)
    }

    @Test // 5-3
    fun should_ReturnNullOfLargestApps_When_AppListIsEmpty() {
        // given an empty list of apps.
        apps = mutableListOf()
        // when the app list is empty and range is valid.
        val range = 1
        val result = appAnalyzer.findLargestApps(apps, range)
        // then check the largest apps in the list.
        assertNull(result)
    }

    @Test // 5-4
    fun should_ReturnNullOfLargestApps_When_RangeIsZero() {
        // given a list of apps.
        apps = setList()
        // when the app list is valid and range is zero.
        val range = 0
        val result = appAnalyzer.findLargestApps(apps, range)
        // then check the largest apps in the list.
        assertNull(result)
    }

    @Test // 5-5
    fun should_ReturnNullOfLargestApps_When_RangeIsNegative() {
        // given a list of apps.
        apps = setList()
        // when the app list is valid and range is negative.
        val range = -3
        val result = appAnalyzer.findLargestApps(apps, range)
        // then check the largest apps in the list.
        assertNull(result)
    }

    /*
    * Point 6: The top installed apps.
    * No. of test cases: 6
    * */

    @Test // 6-1
    fun should_ReturnTopInstallApps_When_ValidAppListAndRange() {
        // given a list of apps.
        apps = setList()
        // when the app list is valid and range is valid.
        val range = 2
        val result = appAnalyzer.topAppInstall(apps, range)
        // then check the top number of installed apps in the list.
        val expectedResult = mutableListOf(apps[googleFilesAppIndex], apps[bookAppIndex])
        assertEquals(expectedResult, result)
    }

    @Test // 6-2
    fun should_ReturnNull_When_AppListIsEmpty() {
        // given an empty list of apps.
        apps = mutableListOf()
        // when the app list is empty and range is valid.
        val range = 1
        val result = appAnalyzer.topAppInstall(apps, range)
        // then check the top number of installed apps in the list.
        assertNull(result)
    }

    @Test // 6-3
    fun should_ReturnNullOfTopInstallApps_When_RangeIsBiggerThanAppListSize() {
        // given a list of apps.
        apps = setList()
        // when the app list is valid and range is bigger than the size of the list.
        val range = apps.size.plus(1)
        val result = appAnalyzer.topAppInstall(apps, range)
        // then check the top number of installed apps in the list.
        assertNull(result)
    }

    @Test // 6-4
    fun should_ReturnNullOfTopInstallApps_When_AppListIsEmpty() {
        // given a null list of apps.
        apps
        // when the app list is null and range is valid.
        val range = 1
        val result = appAnalyzer.topAppInstall(apps, range)
        // then check the top number of installed apps in the list.
        assertNull(result)
    }

    @Test // 6-5
    fun should_ReturnNullOfTopInstallApps_When_RangeIsNegative() {
        // given a list of apps.
        apps = setList()
        // when the app list is valid and range is negative.
        val range = -2
        val result = appAnalyzer.topAppInstall(apps, range)
        // then check the top number of installed apps in the list.
        assertNull(result)
    }

    @Test // 6-6
    fun should_ReturnNullOfTopInstallApps_When_RangeIsZero() {
        // given a list of apps.
        apps = setList()
        // when the app list is valid and range is zero.
        val range = 0
        val result = appAnalyzer.topAppInstall(apps, range)
        // then check the top number of installed apps in the list.
        assertNull(result)
    }

    /*
       * Point 7: the largest apps size developed by "Meta platforms"
       * No. of test cases: 5
       * */

    @Test // 7-1
    fun should_ReturnLargestApp_When_ValidCompanyInput() {
        // given a list of apps.
        apps = setList()
        // when the company name is completely valid.
        val companyName = "Amazon"
        val result = appAnalyzer.getLargestAppSizeByCompanyName(apps, companyName)
        // then check
        assertEquals(apps[largestAmazonApp], result)
    }

    @Test //7-2
    fun should_ReturnLargestApp_When_CompanyNameContainsSpace() {
        //given a list of apps
        apps = setList()
        //when the company name contains space
        val companyName = " Amazon "
        val result = appAnalyzer.getLargestAppSizeByCompanyName(apps, companyName)
        // then check
        assertEquals(apps[largestAmazonApp], result)
    }

    @Test //7-3
    fun should_ReturnLargestApp_When_companyNameInUpperCase() {
        //given app list
        apps = setList()
        // when the company name in upper case
        val companyName = "AMAZON"
        val result = appAnalyzer.getLargestAppSizeByCompanyName(apps, companyName)
        // then check
        assertEquals(apps[largestAmazonApp], result)
    }

    @Test //7-5
    fun should_ReturnNull_When_CompanyNameIsNotFound() {
        //given  a list of app
        apps = setList()
        //when the  company name is not found
        val companyName = "unknown"
        val result = appAnalyzer.getLargestAppSizeByCompanyName(apps, companyName)
        //given check the num of app
        assertNull(result)
    }

}