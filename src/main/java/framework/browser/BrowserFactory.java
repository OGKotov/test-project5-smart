package framework.browser;

import framework.utils.ReaderContentFromConfigXML;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.HashMap;
import java.util.Map;

//Выбор браузера для ВебДрайвера согласно настроек конфиг. файла
public final class BrowserFactory {

    //Название тега, содержащего путь к папке для скачивания файлов
    private static String tagNameDownloadDirectoryPath ="path";
    //Значение атрибута id тега, содержащего путь к папке для скачивания файлов
    private static String attributeIdValueDownloadDirectoryPath ="downloaddirectorypath";

    //Путь к папке для скачивания файлов
    private static String downloadDirectoryPath = ReaderContentFromConfigXML.readTagContent(tagNameDownloadDirectoryPath, attributeIdValueDownloadDirectoryPath);

    private static WebDriver driver;

    public static WebDriver getDriver(String nameBrowser){
        switch (nameBrowser) {
            case "FIREFOX":
                driver=new FirefoxDriver(settingDownloadFirefox());
                break;
            case "CHROME":
                driver=new ChromeDriver(settingDownloadChrome());
                break;
            case "IEEXPLORER":
                driver=new InternetExplorerDriver();
                break;
            case "OPERA":
                driver=new OperaDriver();
                break;
            case "EDGE":
                driver=new EdgeDriver();
                break;
            case "SAFARI":
                driver=new SafariDriver();
                break;
            default:
                driver=new FirefoxDriver();
                break;
        }
        return driver;
    }

    public static String getDownloadDirectoryPath() {
        return downloadDirectoryPath;
    }

    //Метод для настроек браузера Firefox для скачивания файла в указанную папку
    public static FirefoxOptions settingDownloadFirefox(){
        FirefoxProfile profile = new FirefoxProfile();
        FirefoxOptions options = new FirefoxOptions();
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk" , "application/octet-stream;application/csv;text/csv;application/vnd.ms-excel;");
        profile.setPreference("browser.helperApps.alwaysAsk.force", false);
        profile.setPreference("browser.download.manager.showWhenStarting",false);
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.dir", downloadDirectoryPath);
        options.setProfile(profile);
        return options;
    }

    //Метод для настроек браузера Chrome для скачивания файла в указанную папку
    public static ChromeOptions settingDownloadChrome(){
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("credentials_enable_service", "false");
        chromePrefs.put("profile.password_manager_enabled", "false");
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", downloadDirectoryPath);
        chromePrefs.put("download.prompt_for_download", "false");
        chromePrefs.put("download.directory_upgrade", "false");
        chromePrefs.put("safebrowsing.enabled", "true");
        chromePrefs.put("select_file_dialogs.allowed", "false");
        options.setExperimentalOption("prefs", chromePrefs);
        return options;
    }
}
