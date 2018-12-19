package framework;

import framework.browser.BrowserSingleton;
import framework.utils.ReaderContentFromConfigXML;
import org.openqa.selenium.WebDriver;

import java.text.SimpleDateFormat;

//Класс находиться на вершине иерархии всех классов, и выполняет базовые настройки теста
public class BaseEntity {

    //Название тега конфигурациооного файла, содержащего формат даты
    private static String tagNameDateFormat="dateformat";
    //Значение атрибута id тега конфигурациооного файла, содержащего формат даты
    private static String attributeIdDateFormat ="dateformat";
    //Название тега конфигурациооного файла, содержащего языковую версию тестового сайта
    private static String tagNameLanguageSite="language";
    //Значение атрибута id тега конфигурациооного файла, содержащего языковую версию тестового сайта
    private static String attributeIdLanguageSite ="languagesite";

    //Формат даты из конфигурациооного файла
    private static String stringDateFormat = ReaderContentFromConfigXML.readTagContent(tagNameDateFormat, attributeIdDateFormat);
    //Языковая версия тестового сайта из конфигурациооного файла
    private static String stringLanguageSite = ReaderContentFromConfigXML.readTagContent(tagNameLanguageSite, attributeIdLanguageSite);

    protected BrowserSingleton browser=BrowserSingleton.getInstanceDriwer();
    protected WebDriver driver=browser.getDriver();

    //Формат даты
    protected SimpleDateFormat formatForDate = new SimpleDateFormat(stringDateFormat);

    public static String getStringLanguageSite() {
        return stringLanguageSite;
    }
}
