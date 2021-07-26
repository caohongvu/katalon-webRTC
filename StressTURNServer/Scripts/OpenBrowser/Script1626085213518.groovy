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
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.server.handler.FindElement
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension as Dimension
import org.openqa.selenium.Point as Point

import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.microsoft.edge.seleniumtools.EdgeDriver
import com.microsoft.edge.seleniumtools.EdgeOptions

import groovy.inspect.swingui.BytecodeCollector

import com.kms.katalon.core.webui.driver.WebUIDriverType

String url = 'https://meet.masterisehomes.com/'

Map<Integer, WebDriver> mapWebDriver = new HashMap<>();

Map<String, String> mapForCall = new HashMap<>();

Map<String, String> mapForCallStatus = new HashMap<>();



List<String> arrayList = new ArrayList<>();
// adding String object to arrayList
////////////// Modify code here
int brower_to_peer = 20
int start = 21
int end = 40
int temp = 0
for (int j = start; j <= end; j ++ ) {
	arrayList.add(j.toString())
	temp = j - brower_to_peer
	mapForCall.put(j.toString(), temp.toString())	
}
//////////////

//arrayList.add("5");
//arrayList.add("6");
//
//arrayList.add("7");
//arrayList.add("8");
//
//arrayList.add("9");
//arrayList.add("10");

//arrayList.add("11");
//arrayList.add("12");
//
//arrayList.add("13");
//arrayList.add("14");
//
//arrayList.add("15");
//arrayList.add("16");
//
//arrayList.add("17");
//arrayList.add("18");
//
//arrayList.add("19");
//arrayList.add("20");
//
//arrayList.add("21");
//arrayList.add("22");
//
//arrayList.add("23");
//arrayList.add("24");
//
//arrayList.add("25");
//arrayList.add("26");
//
//arrayList.add("27");
//arrayList.add("28");
//
//arrayList.add("29");
//arrayList.add("30");
//
//arrayList.add("31");
//arrayList.add("32");
//
//arrayList.add("33");
//arrayList.add("34");
//
//arrayList.add("35");
//arrayList.add("36");
//
//arrayList.add("37");
//arrayList.add("38");
//
//arrayList.add("39");
//arrayList.add("40");

for(int index=0; index < arrayList.size(); index++) {
	// open the first browse in normal mode on the LEFT
	WebDriver incognitoChromeLEFT = openChromeBrowserPlain();
	resizeHorizontalHalfLocateLeft(incognitoChromeLEFT);
	DriverFactory.changeWebDriver(incognitoChromeLEFT);
	WebUI.navigateToUrl(url);
	WebUI.waitForPageLoad(10);
	mapWebDriver.put(index, incognitoChromeLEFT);
	WebUI.setText(findTestObject('Object Repository/Page_Flutter WebRTC Demo/input_P2P call testing_socketId'), arrayList.get(index));
	WebUI.click(findTestObject('Object Repository/Page_Flutter WebRTC Demo/button_P2P call testing'));
	WebUI.waitForPageLoad(2000);
	if(index == arrayList.size() - 1) {
		WebUI.waitForPageLoad(2000);
		clickToCall(mapWebDriver, mapForCall, mapForCallStatus);
	}
	
}




/**
 * opens a Chrome browser with nothing special
 * returns the ChromeDriver instance that is assocated with the window
 * @return
 */
ChromeDriver openChromeBrowserPlain() {
	ChromeOptions options = new ChromeOptions();
	options.addArguments("use-fake-device-for-media-stream");
	options.addArguments("use-fake-ui-for-media-stream");
	return openChromeBrowser(options);
}
/**
 * opens a Chrome browser with -incoginito mode,
 * returns the ChromeDriver instance that is associated with the window
 */
ChromeDriver openChromeBrowserInIncognitoMode() {
	ChromeOptions options = new ChromeOptions()
	options.addArguments("inprivate")
	options.addArguments("use-fake-device-for-media-stream");
	options.addArguments("use-fake-ui-for-media-stream");
	
	return openChromeBrowser(options);
}

/**
 * opens a ChromeBrowser with the ChromeOptions given.
 * returns the ChromeDriver instance that is associated with the window
 * @param options
 * @return
 */
ChromeDriver openChromeBrowser(ChromeOptions options) {
	System.setProperty("webdriver.chrome.driver", "D:\\Download\\Katalon_Studio_Windows_64-8.0.5\\configuration\\resources\\drivers\\chromedriver_win64\\chromedriver.exe")
	return new ChromeDriver(options);
}
/**
 * resize the browser window to horizontal half, and move it to the right side
 * @param driver
 * @returns Dimension of the window
 */
Dimension resizeHorizontalHalfLocateLeft(WebDriver driver) {
	Dimension d = resizeToHorizontalHalf(driver)
	driver.manage().window().setPosition(new Point(0, 0));
	return d
}
/**
 * resize the browser window to horizontal half, and move it to the left side
 *
 * @param driver
 * @returns Dimension of the window
 */
Dimension resizeHorizontalHalfLocateRight(WebDriver driver) {
	Dimension d = resizeToHorizontalHalf(driver)
	driver.manage().window().setPosition(new Point(d.getWidth(), 0))
	return d
}

/**
 * resize the browser window to half-width tile;
 * width=half of full screen, height=height of full screen
 *
 * @param driver
 * @return
 */
Dimension resizeToHorizontalHalf(WebDriver driver) {
	driver.manage().window().maximize()
	Dimension maxDim = driver.manage().window().getSize()
	Dimension curDim = new Dimension((Integer)(maxDim.getWidth() / 2), maxDim.getHeight())
	driver.manage().window().setSize(curDim)
	return curDim
}

void clickToCall(Map<Integer, WebDriver> mapWebDriver, Map<String, String> mapForCall, Map<String, String> mapForCallStatus) {
	
	for (WebDriver webdrive in mapWebDriver.values()) {
		Thread.sleep(1500);
		DriverFactory.changeWebDriver(webdrive)
		String myID = webdrive.findElement(By.id("socketId")).getAttribute("value");
		System.out.println("myID:"+myID);
		if(mapForCall.get(myID) != null) {
			
			webdrive.findElement(By.id(mapForCall.get(myID))).click();
			Thread.sleep(3000);
			String status = webdrive.findElement(By.id("call_status")).getText();
			mapForCallStatus.put(myID, status);
		}
	}
	report(mapForCallStatus);
}


void report(Map<String, String> mapForCallStatus) {
	int totalPeer = 0;
	int totalSuccess = 0;
	for (String status in mapForCallStatus.values()) {
		totalPeer +=1;
		if(status.equals("OK")) {
			totalSuccess+=1;
		}
	}
	file_path = "D:\\Reports\\1_report.txt"
	File file = new File(file_path)
	file.write("Total peer connection:" + totalPeer + "\n")
	file.append("Total peer connection success:"+ totalSuccess + "\n")	
	file.append("Total peer connection failed:"+ (totalPeer-totalSuccess))	
	System.out.println("Access for the report of connections at" + file_path );
}