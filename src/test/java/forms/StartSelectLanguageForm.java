package forms;

import framework.BaseEntity;
import framework.elements.Label;
import org.openqa.selenium.By;

//Класс для выбора языка при старте сайта
public class StartSelectLanguageForm extends BaseEntity {
    private static String stringTitleRu="Добро пожаловать в Steam";
    private static String stringTitleEn="Welcome to Steam";

    private By htmlLocator =By.xpath("//html");
    private Label titleLabel=new Label(htmlLocator);

/*Метод выбирает язык загрузки тестируемого сайта, в зависимости от параметра языка в конфигурациооном файле.
По умолчанию, загружается русская версия
 */
    public void selectLanguage(){
        switch (getStringLanguageSite()) {
            case "EN":
                System.out.format("Тестируем англоязычную версию сайта.%n");
                if(!titleLabel.getTitleContent().equals(stringTitleEn)){
                    new HeaderForm().clickHeaderElement(HeaderForm.LanguageHeadRuEn.Language);
                    new HeaderForm().clickHeaderElement(HeaderForm.LanguageHeadRuEn.English);
                    new HeaderForm().invisibilityHeaderElement(HeaderForm.LanguageHeadRuEn.English);
                }
                break;
            case "RU":
                System.out.format("Тестируем русскоязычную версию сайта.%n");
                if(!titleLabel.getTitleContent().equals(stringTitleRu)) {
                    new HeaderForm().clickHeaderElement(HeaderForm.LanguageHeadRuEn.Language);
                    new HeaderForm().clickHeaderElement(HeaderForm.LanguageHeadRuEn.Russian);
                    new HeaderForm().invisibilityHeaderElement(HeaderForm.LanguageHeadRuEn.Russian);
                }
                break;
            default:
                System.out.format("Тестируем русскоязычную версию сайта по умолчанию%n" +
                        "Для установки нужного языка, установите нужное значене в конфигурациооном файле.%n");
                if(!titleLabel.getTitleContent().equals(stringTitleRu)) {
                    new HeaderForm().clickHeaderElement(HeaderForm.LanguageHeadRuEn.Language);
                    new HeaderForm().clickHeaderElement(HeaderForm.LanguageHeadRuEn.Russian);
                    new HeaderForm().invisibilityHeaderElement(HeaderForm.LanguageHeadRuEn.Russian);
                }
                break;
        }
    }
}
