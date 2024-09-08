import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import loginStandardUser

//Login first
loginStandardUser.loginasStandardUser()

WebUI.maximizeWindow()

//Add product to Cart
WebUI.verifyElementPresent(findTestObject('Object Repository/Sauce Demo/Products page/image_product'), 2)
WebUI.verifyElementPresent(findTestObject('Object Repository/Sauce Demo/Products page/txt_product_title'), 2)
WebUI.verifyElementPresent(findTestObject('Object Repository/Sauce Demo/Products page/txt_product_price'), 2)
WebUI.waitForElementVisible(findTestObject('Sauce Demo/Products page/button_add to cart'), 2)
WebUI.click(findTestObject('Sauce Demo/Products page/button_add to cart'))
WebUI.verifyElementPresent(findTestObject('Sauce Demo/Products page/shopping_cart'), 2)
cartValue = WebUI.getText(findTestObject('Sauce Demo/Products page/shopping_cart_value'), FailureHandling.STOP_ON_FAILURE)
cartBefore = 0

//Verifying the cart is not null
WebUI.verifyGreaterThan(cartValue, cartBefore, FailureHandling.STOP_ON_FAILURE)

//Open Sauce Labs Bike Light product then add to cart
WebUI.click(findTestObject('Sauce Demo/Products page/div_Sauce Labs Bike Light'))
WebUI.click(findTestObject('Sauce Demo/Products page/button_Add to cart_detailpage'))
WebUI.click(findTestObject('Sauce Demo/Products page/shopping_cart'))
WebUI.click(findTestObject('Sauce Demo/Products page/button_Checkout'))

//Checkout page
WebUI.verifyElementPresent(findTestObject('Sauce Demo/Products page/firstname'), 2)
WebUI.verifyElementPresent(findTestObject('Sauce Demo/Products page/lastname'), 2)
WebUI.verifyElementPresent(findTestObject('Sauce Demo/Products page/postalCode'), 2)
WebUI.setText(findTestObject('Sauce Demo/Products page/firstname'), 'dummy')
WebUI.setText(findTestObject('Sauce Demo/Products page/lastname'), '')
WebUI.setText(findTestObject('Sauce Demo/Products page/postalcode'), '28654')
WebUI.click(findTestObject('Sauce Demo/Products page/button_Continue'))
WebUI.verifyElementPresent(findTestObject('Sauce Demo/Products page/error_Last Name is Required'), 2)
WebUI.setText(findTestObject('Sauce Demo/Products page/lastname'), 'user')
WebUI.click(findTestObject('Sauce Demo/Products page/button_Continue'))

//Overview page
//verify backpack and bike light product is in overview page
WebUI.verifyTextPresent("Sauce Labs Backpack", false)
WebUI.verifyTextPresent("Sauce Labs Bike Light", false)
WebUI.scrollToElement(findTestObject('Sauce Demo/Products page/div_Total Price'), 3)

//verify the price match
WebUI.verifyElementPresent(findTestObject('Sauce Demo/Products page/div_Total Price'), 3, FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Sauce Demo/Products page/button_Finish'))
WebUI.verifyTextPresent("Thank you for your order!", false)
WebUI.click(findTestObject('Sauce Demo/Products page/button_Back Home'))
//verify user in homepage
WebUI.verifyElementPresent(findTestObject('Object Repository/Sauce Demo/Products page/image_product'), 2)
WebUI.click(findTestObject('Sauce Demo/Products page/button_hamburger Menu'))
WebUI.click(findTestObject('Sauce Demo/Products page/link_Logout'))
WebUI.verifyElementPresent(findTestObject('Object Repository/Sauce Demo/Products page/loginLogo'), 2)

