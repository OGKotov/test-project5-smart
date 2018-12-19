package forms;

import framework.BaseEntity;
import framework.elements.Label;
import org.openqa.selenium.By;

import java.util.Date;

import static org.testng.Assert.*;

//Класс для унификации работы с необходимыми для теста вебстраницами
public class BaseForm extends BaseEntity {

    protected By titleLocator=By.xpath("//html");
    protected String title;
    protected String name;

    //Метод возвращает один из строковый входных параметров, в зависимости от параметра языка в конфигурациооном файле
    public static String selectLanguageStringPar(String ru, String en) {
        if(getStringLanguageSite().equals("RU")) {
            return ru;
        }
        else {
            return en;
        }
    }

    //Метод возвращает один из входных параметров типа By, в зависимости от параметра языка в конфигурациооном файле
    public static By selectLanguageByPar(By ru, By en){
        if(getStringLanguageSite().equals("RU")) {
            return ru;
        }
        else {
            return en;
        }
    }

    public BaseForm(String title) {
        init(title);
        assertIsOpen();
    }

    private void init(String title) {
        this.title =title;
    }

    //Метод сравнивает название открывшейся странице с ожидаемым названием и логирует полученный результат
    public void assertIsOpen() {
        String beforeTime=formatForDate.format(new Date());
        Label elem = new Label(titleLocator);
        System.out.format("Открываем страницу тестируемого сайта: \"%s\". Время начала открытия страницы: %s %n", title, beforeTime);
        if (elem.getTitleContent().equals(title)){
            String downloadedTime=formatForDate.format(new Date());
            System.out.format("Страница загружена. Время завершения открытия страницы: %s.%n", downloadedTime);
        }
        else {
            String failTime=formatForDate.format(new Date());
            String coment=String.format("СТРНИЦА НЕ ЗАГРУЖЕНА. Время: %s.%n", failTime);
            fail(coment);
        }
    }
}
