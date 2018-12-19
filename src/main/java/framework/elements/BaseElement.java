package framework.elements;

import framework.BaseEntity;
import framework.browser.BrowserSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

//Класс предназначен для унификации работы с элементами страницы
public class BaseElement extends BaseEntity {

    private static final String PRESENCE ="presence";
    private static final String VISIBLE ="visible";
    private static final String CLICKABLE ="clickable";
    private static final String INVISIBILITY="invisibility";
    private static final int TIMEOUT_IMPLICITLY_WAIT_0 = 0;

    private By locator;
    protected WebElement element;
    protected ArrayList<WebElement> elements;
    private Actions actions=new Actions(driver);

    protected BaseElement(By loc) {
        locator = loc;
    }

    /*Метод ожидает скачивания файла, иницилазированного входными параметрами downDirectory, downloadFileName
    в течение времени timeExplicitly
     */
    public boolean waitForFileDownload(int timeExplicitly, String downDirectory, String downloadFileName) {
        try {
        WebDriverWait wait = new WebDriverWait(driver, timeExplicitly);
        wait.until(driver -> new File(downDirectory, downloadFileName).exists());
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    /*Метод ожидает наиболее распространенные события при загрузке в браузер элемента страницы, инициализирует
    этот элемент согласно локатора, обрабатывая при этом исключения и выдавая стек исключения
     */
    private boolean waitElementUntilConditions(int timeExplicitly, String type) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeExplicitly);
            switch (type) {
                case "presence":
                    element=wait.until(ExpectedConditions.presenceOfElementLocated(locator));
                    break;
                case "visible":
                    element=wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                    break;
                case "clickable":
                    element=wait.until(ExpectedConditions.elementToBeClickable(locator));
                    break;
                case "invisibility":
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
                    break;
            }
        } catch (TimeoutException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    /*Метод ожидает наиболее распространенные события при загрузке в браузер элемента страницы, инициализирует
    этот элемент согласно локатора обрабатывая при этом исключения. Стек исключений не выдает
     */
    private boolean waitElementUntilConditionsWithoutException(int timeExplicitly, String type) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeExplicitly);
            switch (type) {
                case "presence":
                    element=wait.until(ExpectedConditions.presenceOfElementLocated(locator));
                    break;
                case "visible":
                    element=wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                    break;
                case "clickable":
                    element=wait.until(ExpectedConditions.elementToBeClickable(locator));
                    break;
                case "invisibility":
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
                    break;
            }
        } catch (TimeoutException ex) {
            return false;
        }
        return true;
    }

    /*Метод ожидает наиболее распространенные события при загрузке в браузер группы элементов страницы, инициализирует
    эти элементы согласно локатора обрабатывая при этом исключения и выдавая стек исключения
     */
    private boolean waitElementsUntilConditions(int timeExplicitly, String type) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeExplicitly);
            switch (type) {
                case "presence":
                    elements=(ArrayList<WebElement>) wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
                    break;
                case "visible":
                    elements=(ArrayList<WebElement>) wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
                    break;
            }
        } catch (TimeoutException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }


    //Метод ожидает появления элемента в DOM
    public boolean waitElementIsPresencePresent(){
        driver.manage().timeouts().implicitlyWait(TIMEOUT_IMPLICITLY_WAIT_0, TimeUnit.SECONDS);
        boolean isWait= waitElementUntilConditions(BrowserSingleton.getExplicitlyWaitTime(), PRESENCE);
        driver.manage().timeouts().implicitlyWait(BrowserSingleton.getImplicitlyWaitTime(), TimeUnit.SECONDS);
        return isWait;
    }

    //Метод ожидает появления элемента в DOM и видимости этого элемента на странице
       public boolean waitElementIsVisiblePresent(){
        driver.manage().timeouts().implicitlyWait(TIMEOUT_IMPLICITLY_WAIT_0, TimeUnit.SECONDS);
        boolean isWait= waitElementUntilConditions(BrowserSingleton.getExplicitlyWaitTime(), VISIBLE);
        driver.manage().timeouts().implicitlyWait(BrowserSingleton.getImplicitlyWaitTime(), TimeUnit.SECONDS);
        return isWait;
    }

    //Метод ожидает появления элемента в DOM и видимости этого элемента на странице не выдавая стек возможного исключения
    public boolean waitElementIsVisiblePresentWithoutException(){
        driver.manage().timeouts().implicitlyWait(TIMEOUT_IMPLICITLY_WAIT_0, TimeUnit.SECONDS);
        boolean isWait= waitElementUntilConditionsWithoutException(BrowserSingleton.getExplicitlyWaitTime(), VISIBLE);
        driver.manage().timeouts().implicitlyWait(BrowserSingleton.getImplicitlyWaitTime(), TimeUnit.SECONDS);
        return isWait;
    }

    //Метод ожидает, когда элемент будет кликабельным
    public boolean waitElementIsClickablePresent(){
        driver.manage().timeouts().implicitlyWait(TIMEOUT_IMPLICITLY_WAIT_0, TimeUnit.SECONDS);
        boolean isWait= waitElementUntilConditions(BrowserSingleton.getExplicitlyWaitTime(), CLICKABLE);
        driver.manage().timeouts().implicitlyWait(BrowserSingleton.getImplicitlyWaitTime(), TimeUnit.SECONDS);
        return isWait;
    }

    //Метод ожидает, когда элемент окажеться невидимым
    public boolean waitElementIsInvisibilityPresent(){
        driver.manage().timeouts().implicitlyWait(TIMEOUT_IMPLICITLY_WAIT_0, TimeUnit.SECONDS);
        boolean isWait= waitElementUntilConditions(BrowserSingleton.getExplicitlyWaitTime(), INVISIBILITY);
        driver.manage().timeouts().implicitlyWait(BrowserSingleton.getImplicitlyWaitTime(), TimeUnit.SECONDS);
        return isWait;
    }

    //Метод ожидает появления группы элементов в DOM
    public boolean waitElementsIsPresencePresent(){
        driver.manage().timeouts().implicitlyWait(TIMEOUT_IMPLICITLY_WAIT_0, TimeUnit.SECONDS);
        boolean isWait= waitElementsUntilConditions(BrowserSingleton.getExplicitlyWaitTime(), PRESENCE);
        driver.manage().timeouts().implicitlyWait(BrowserSingleton.getImplicitlyWaitTime(), TimeUnit.SECONDS);
        return isWait;
    }

    //Метод ожидает появления группы элементов в DOM и видимости этих элементов на странице
    public boolean waitElementsIsVisablePresent(){
        driver.manage().timeouts().implicitlyWait(TIMEOUT_IMPLICITLY_WAIT_0, TimeUnit.SECONDS);
        boolean isWait= waitElementsUntilConditions(BrowserSingleton.getExplicitlyWaitTime(), VISIBLE);
        driver.manage().timeouts().implicitlyWait(BrowserSingleton.getImplicitlyWaitTime(), TimeUnit.SECONDS);
        return isWait;
    }


    public WebElement getElement() {
        waitElementIsVisiblePresent();
        return element;
    }

    //Метод возвращает список элементов страницы
    public List<WebElement> getElements (){
        waitElementsIsVisablePresent();
        return elements;
    }

    //Метод возвращает контент элементастраницы
    public String getContent() {
        waitElementIsVisiblePresent();
        return element.getText();
    }

    //Метод возвращает заглавие страницы
    public String getTitleContent() {
        waitElementIsVisiblePresent();
        return driver.getTitle();
    }

    //Метод кликает по элементу страницы
    public void click() {
        waitElementIsClickablePresent();
        element.click();
    }

    //Метод наводит курсор на элемент
    public void mouseOverElement(){
        waitElementIsVisiblePresent();
        actions.moveToElement(element).build().perform();
    }

    //Метод выводит выбранные элементы страницы коллекцией ArrayList в текстовом формате
    public ArrayList<String> returnStringArrayListContentElements(){
        waitElementsIsVisablePresent();
        ArrayList<String> list=new ArrayList<String>();
        for(int i=0; i<elements.size(); i++) {
            list.add(i, elements.get(i).getText());
        }
        return list;
    }
}
