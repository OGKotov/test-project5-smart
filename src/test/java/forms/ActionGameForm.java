package forms;

import framework.elements.Label;
import framework.utils.ReturnListSubstrsFromStrRegExp;
import org.openqa.selenium.By;

import java.util.ArrayList;

/*Класс предназначен для работы со странницей открывшейся Игры, инициализирует необходимые для работы с ней переменные,
осуществает необходимую для задач тестирования работу
 */
public class ActionGameForm extends BaseForm {

    private By locatorDiscount=By.xpath("//*[@id='game_area_purchase']//*[@class='game_area_purchase_game']//*[@class='discount_pct']");
    private By locatorPrice=By.xpath("//*[@id='game_area_purchase']//*[@class='game_area_purchase_game']//*[@class='discount_final_price']");

    //Числовые значения максимальной скидки и цены игры с этой скидкой
    private static int intMaxDiscount;
    private static double doublePrice;
    //Строковое  значение максимальной скидки игры, цены и названия этой игры
    private static String stringMaxDiscount, stringPrice, stringNameGamesMaxDiscount;

    private static String stringTitleRu=String.format("Сэкономьте %s%% при покупке %s в Steam", String.valueOf(ActionForm.getIntMaxDiscount()), ActionForm.getStringNameGamesMaxDiscount());
    private static String stringTitleEn=String.format("Save %s%% on %s on Steam", String.valueOf(ActionForm.getIntMaxDiscount()), ActionForm.getStringNameGamesMaxDiscount());

    //Регулярное выражения для извлечения строковых литералов скидок и цены, удаля все строковый литералы, кроме цифр
    private String regExpForDiscontAndPrice="(\\d|\\.|\\d)+";

    public ActionGameForm() {
        super(selectLanguageStringPar(stringTitleRu, stringTitleEn));
    }

    public static int getIntMaxDiscount() {
        return intMaxDiscount;
    }

    public static double getDoublePrice() {
        return doublePrice;
    }

    //Метод инициализирует числовые переменные числовым значение цены и скидки открытой игры
    public void takeNumberPriceAndDiscount() {
        Label labelDiscount=new Label(locatorDiscount);
        Label labelPrice=new Label(locatorPrice);
        ArrayList<String> listDiscount=new ArrayList<String>();
        listDiscount.add(labelDiscount.getContent());
        ArrayList<String> listPrice=new ArrayList<String>();
        listPrice.add(labelPrice.getContent());
        intMaxDiscount=Integer.parseInt(new ReturnListSubstrsFromStrRegExp().returnListSubstrFromListStrRegExp(listDiscount, regExpForDiscontAndPrice).get(0));
        doublePrice=Double.parseDouble(new ReturnListSubstrsFromStrRegExp().returnListSubstrFromListStrRegExp(listPrice, regExpForDiscontAndPrice).get(0));
    }

    //Метод переходит по ссылке "Установить Steam"
    public void goToInstallSteam() {
        String ru="Переходим по ссылке \"Установить Steam\".%n";
        String en="Переходим по ссылке \"Install Steam\".%n";
        String log=selectLanguageStringPar(ru, en);
        System.out.format(log);
        new HeaderForm().clickHeaderElement(HeaderForm.InstallSteamHeadRuEn.InstallSteam);
    }
}
