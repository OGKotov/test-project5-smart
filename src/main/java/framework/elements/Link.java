package framework.elements;

import org.openqa.selenium.By;

//Клас наследник BaseElement для работы с ссылочным элементом Вебастраницы
public class Link extends BaseElement {

    public Link(By locator) {
        super(locator);
    }
}
