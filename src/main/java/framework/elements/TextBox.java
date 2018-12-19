package framework.elements;

import org.openqa.selenium.By;

//Клас наследник BaseElement для работы с текстовыми полями
public class TextBox extends BaseElement {

    public TextBox(By locator) {
        super(locator);
    }

    public void sendKeys(String keys) {
        waitElementIsVisiblePresent();
        element.clear();
        element.sendKeys(keys);
    }

}
