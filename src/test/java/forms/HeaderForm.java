package forms;

import framework.elements.Label;
import org.openqa.selenium.By;

/*Класс предназначен для работы с верхним основым меню, инициализирует необходимые для работы с нем переменные
 */
public class HeaderForm {

    /*Начальная часть элементов хэдера в текстовой форме*/
    private String globalHeader="//descendant::*[@id='global_header']//%s";

    /*Конеченая часть в текстовом формате элемента Хэдера "Install Steam" для русскоязычной и англоязычной версии сайта*/
    enum InstallSteamHeadRuEn {
        InstallSteam("*[@class='header_installsteam_btn_content']");

        private String pathInstal;
        InstallSteamHeadRuEn(String path) {
            this.pathInstal = path;
        }

        public String getPathInstal() {
            return pathInstal;
        }
    }

    /*Метод собирает начальную и конечную часть элемента Хэдера и возыращает локатор этого элемента*/
    public By fullByPath(InstallSteamHeadRuEn item) {
        String stringPath=String.format(globalHeader, item.getPathInstal());
        By byPath=By.xpath(stringPath);
        return byPath;
    }

    //Метод кликает по передонному в параметре элементу
    public void clickHeaderElement(InstallSteamHeadRuEn item){
        Label headerElement=new Label(fullByPath(item));
        headerElement.click();
    }

    //Метод ждет, пока переданный в параметре элемент перестанет отображаться в DOM
    public void invisibilityHeaderElement(InstallSteamHeadRuEn item){
        Label headerElement=new Label(fullByPath(item));
        headerElement.waitElementIsInvisibilityPresent();
    }

    /*Конеченая часть в текстовом формате элемента Хэдера "Language" для русскоязычной и англоязычной версии сайта*/
    enum LanguageHeadRuEn {
        Language("*[@id='language_pulldown']"),
        Russian("*[text()='Русский (Russian)']"),
        English("*[text()='English (английский)']");

        private String pathLanguage;
        LanguageHeadRuEn(String path) {
            this.pathLanguage = path;
        }

        public String getPathLanguage() {
            return pathLanguage;
        }
    }

    /*Метод собирает начальную и конечную часть элемента Хэдера и возыращает локатор этого элемента*/
    public By fullByPath(LanguageHeadRuEn item) {
        String stringPath=String.format(globalHeader, item.getPathLanguage());
        By byPath=By.xpath(stringPath);
        return byPath;
    }

    //Метод кликает по передонному в параметре элементу
    public void clickHeaderElement(LanguageHeadRuEn item){
        Label headerElement=new Label(fullByPath(item));
        headerElement.click();
    }

    //Метод ждет, пока переданный в параметре элемент перестанет отображаться в DOM
    public void invisibilityHeaderElement(LanguageHeadRuEn item){
        Label headerElement=new Label(fullByPath(item));
        headerElement.waitElementIsInvisibilityPresent();
    }
}
