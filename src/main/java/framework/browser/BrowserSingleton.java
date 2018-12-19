package framework.browser;

import framework.utils.ReaderContentFromConfigXML;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;


//Реализация шаблона Одиночка, инициализазия ВебДрайвера фабричным браузером согласно настроек конфиг. файла
public final class BrowserSingleton {

    //Название тега, содержащего браузер в котором проходит тест
    private static String tagNameBrowser="browser";
    //Значение атрибута id тега, содержащего браузер в котором проходит тест
    private static String attributeIdValueBrowser ="browserForTest";
    //Название тега, содержащего время явного и неявного ожиданий
    private static String tagNameWait="wait";
    //Значение атрибута id тега, содержащего неявное ожидание
    private static String attributeIdValueImplicitlyWai="implicitly";
    //Значение атрибута id тега, содержащего явное ожидание
    private static String attributeIdValueExplicitlyWai="explicitly";

    private static int implicitlyWaitTime =Integer.parseInt(ReaderContentFromConfigXML.readTagContent(tagNameWait, attributeIdValueImplicitlyWai));
    private static int explicitlyWaitTime=Integer.parseInt(ReaderContentFromConfigXML.readTagContent(tagNameWait, attributeIdValueExplicitlyWai));
    //Название браузера из конфигурационного файла
    private static String browserNameFromConfig=ReaderContentFromConfigXML.readTagContent(tagNameBrowser, attributeIdValueBrowser);

    //Образец назваанию браузеров, одним из которых может инициализироваться  ВебДрайвер
    private static String[] allBrowsers={"FIREFOX", "CHROME", "IEEXPLORER", "OPERA", "EDGE", "SAFARI"};
    //Браузер по умолчанию
    private static final String BROWSER_DEFAULT_NAME ="FIREFOX";

    private static BrowserSingleton instance;
    private static WebDriver driver;
    private static boolean browserFlag=true;

    public static int getExplicitlyWaitTime() {
        return explicitlyWaitTime;
    }
    public static int getImplicitlyWaitTime() {
        return implicitlyWaitTime;
    }

    public static BrowserSingleton getInstanceDriwer() {
        if(instance==null) {
            instance=new BrowserSingleton();
            driver= BrowserFactory.getDriver(browserName());
            startBrowserLog();
            driver.manage().timeouts().implicitlyWait(implicitlyWaitTime, TimeUnit.SECONDS);
        }
        return instance;
    }

   /*Метод проверяет установку браузекра в конфигурациооном файле, и если установка не соответствует образцам,
   то возращает браузер по умолчанию
    */
    public static String browserName(){
        for (String browserNameCheck: allBrowsers) {
            if(browserNameFromConfig.equals(browserNameCheck)) {
                return browserNameFromConfig;
            }
        }
       browserFlag=false;
       return BROWSER_DEFAULT_NAME;
    }

    public void windowMaximize() {
        driver.manage().window().maximize();
    }

    public WebDriver getDriver() {
        return driver;
    }

   //Метод логирует открытие браузера
    public static void startBrowserLog() {
        if (browserFlag) {
            System.out.format("Открыли браузер %s. %n%n", browserNameFromConfig);
        } else {
            System.out.format("Открыли браузер по умолчанию: %s. %nЕсли необходимо запустить тест в другом браузере - " +
                    "задайте правильное название браузера в конфигурациооном файле. %n", BROWSER_DEFAULT_NAME);

        }
    }

    //Метод проверяет, инициализировалcя ли браузер
    public boolean isBrowserAlive() {
        return instance != null;
    }

    //Метод закрывает браузер
    public void exit() {
        driver.close();
        System.out.format("Закрыли браузер %s.%n", browserName());
    }
}

