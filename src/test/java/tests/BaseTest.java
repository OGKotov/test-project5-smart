package tests;

import framework.BaseEntity;
import framework.utils.ReaderContentFromConfigXML;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

//Класс для предварительной настройки тестов
public class BaseTest extends BaseEntity {

    //Название тега, содержащего путь к тестируемому сайту
    private static String tagNameUrlSite="url";
    //Значение атрибута id тега, содержащего путь к тестируемому сайту
    private static String attributeIdValueUrlSite ="urlSiteForTes";

    //Путь к тестируемому сайту из конфигурационного файла
    private static String urlSite= ReaderContentFromConfigXML.readTagContent(tagNameUrlSite, attributeIdValueUrlSite);


    @BeforeTest
    public void before() {
        browser.windowMaximize();
        System.out.format("Открываем сайт %s. %n", urlSite);
        driver.get(urlSite);
    }

    @AfterTest
    protected void after() {
        if (browser.isBrowserAlive()) {
            browser.exit();
        }

    }
}
