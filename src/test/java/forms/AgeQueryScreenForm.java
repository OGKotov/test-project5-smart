package forms;

import framework.elements.Button;
import framework.elements.Label;
import org.openqa.selenium.By;

//Класс для работы со страницей возрастного предупреждения, которая иногда открывается при открытии страницы игры
public class AgeQueryScreenForm {

    private By locatorAgeWarning= By.xpath("//*[@id='app_agegate']//h2");
    private By locatorViewPageRu=By.xpath("//*[@id='app_agegate']//*[text()='Открыть страницу']");
    private By locatorViewPageEn=By.xpath("//*[@id='app_agegate']//*[text()='View Page']");

    //Метод определяет , открылась ли страница возрастного предупреждения
    public boolean isAgeWarning(){
            Label agewWarning=new Label(locatorAgeWarning);
            return agewWarning.waitElementIsVisiblePresentWithoutException();
    }

    //Метод кликает по кнопке Открыть страницу
    public void clickButtonViewPage() {
        Button buttonClickViewPage=new Button(BaseForm.selectLanguageByPar(locatorViewPageRu, locatorViewPageEn));
        buttonClickViewPage.click();
    }

}