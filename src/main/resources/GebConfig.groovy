import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxProfile
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.openqa.selenium.phantomjs.PhantomJSDriver

import static org.openqa.selenium.remote.DesiredCapabilities.firefox

waiting {
    timeout = 2
}

environments {

    htmlUnit {
        driver = { new HtmlUnitDriver() }
    }

    // run via “./gradlew chromeTest”
    // See: http://code.google.com/p/selenium/wiki/ChromeDriver
    chrome {
        driver = { new ChromeDriver() }
    }

    // run via “./gradlew firefoxTest”
    // See: http://code.google.com/p/selenium/wiki/FirefoxDriver
    firefox {
        driver = {
            //set the firefox locale to 'en-us' since the tests expect english
            //see http://stackoverflow.com/questions/9822717 for more details
            FirefoxProfile profile = new FirefoxProfile()
            profile.setPreference('intl.accept_languages', 'en-us')
            def driverInstance = new FirefoxDriver(profile)
        }
    }

    phantomJs {
        driver = { new PhantomJSDriver() }
    }

}


baseNavigatorWaiting = true
atCheckWaiting = true
