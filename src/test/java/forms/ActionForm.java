package forms;

import framework.elements.Label;
import framework.elements.LabelElements;
import framework.utils.ReturnListSubstrsFromStrRegExp;
import org.openqa.selenium.By;

import java.util.ArrayList;

/*Класс предназначен для работы со странницей Игр жанра Экшен, инициализирует необходимые для работы с ней переменные,
осуществает необходимую для задач тестирования работу
 */
public class ActionForm extends BaseForm {

    private static String stringTitleRu="Просмотр по метке «Экшен»";
    private static String stringTitleEn="Browsing Action";

    private By locatorTopSelling= By.xpath("//*[@id='tab_select_TopSellers']/div");
    private By locatorDiscount=By.xpath("//*[@id='TopSellersRows']//*[@class='discount_pct']");
    private By locatorPrice=By.xpath("//*[@id='TopSellersRows']//*[@class='discount_pct']/..//*[@class='discount_final_price']");
    private By locatorNamesGames=By.xpath("//*[@id='TopSellersRows']//*[@class='discount_pct']/../..//*[@class='tab_item_name']");

    //Регулярное выражения для извлечения строковых литералов скидок и цены, удаля все строковый литералы, кроме цифр
    private String regExpForDiscontAndPrice="(\\d|\\.|\\d)+";

    //Числовые значения максимальной скидки и цены игры с этой скидкой
    private static int intMaxDiscount;
    private static double doublePrice;
    //Строковое  значение максимальной скидки игры, цены и названия этой игры
    private static String stringMaxDiscount, stringPrice, stringNameGamesMaxDiscount;

    public ActionForm() {
        super(selectLanguageStringPar(stringTitleRu, stringTitleEn));
    }

    public static int getIntMaxDiscount() {
        return intMaxDiscount;
    }

    public static double getDoublePrice() {
        return doublePrice;
    }

    public static String getStringNameGamesMaxDiscount() {
        return stringNameGamesMaxDiscount;
    }

    //Метод в зависимости от выбранного языка сайта осуществляет переход на вклдадку Лидеры продаж
    public void goTopSelling(){
        String ru="Переходим на вкладку \"Лидеры продаж\".%n";
        String en="Переходим на вкладку \"Top Selling\".%n";
        String log=selectLanguageStringPar(ru, en);
        System.out.format(log);
        Label label=new Label(locatorTopSelling);
        label.click();
    }


    //Метод находит индекс максимального числа в списке числовых строк, предварительно приводя строковое значени к числовому
    private int returnIndexMaxDiscount(ArrayList<String> arrList){
        int index=0;
        int intItem=Integer.parseInt(arrList.get(0));
        for(int i=1; i<arrList.size(); i++) {
            if(intItem < Integer.parseInt(arrList.get(i))){
                intItem=Integer.parseInt(arrList.get(i));
                index=i;
            }
        }
        return index;
    }

    /*Метод находит игру с максимальной  скидкой, инициализируем соответствуюищие переменные
      строковым и числовым значение цены, и скидки, и строковым начение название игры */
    public void selectGameWithMaxDiscount(){
        LabelElements labDiscount=new LabelElements(locatorDiscount);
        LabelElements labPrice=new LabelElements(locatorPrice);
        LabelElements labNamesGames=new LabelElements(locatorNamesGames);
        //Извлекаем список строк числовых значений для элементов скидки
        ArrayList<String>subStrDiscount=new ReturnListSubstrsFromStrRegExp().returnListSubstrFromListStrRegExp(
                labDiscount.returnStringArrayListContentElements(), regExpForDiscontAndPrice);
        //Извлекаем список строк числовых значений для элементов цены со скидкой
        ArrayList<String>subStrPrice=new ReturnListSubstrsFromStrRegExp().returnListSubstrFromListStrRegExp(
                labPrice.returnStringArrayListContentElements(), regExpForDiscontAndPrice);
        //Извлекаем индекс максимального значения скидки из списка
        int index=returnIndexMaxDiscount(subStrDiscount);
        stringMaxDiscount=labDiscount.getElements().get(index).getText();
        stringPrice=labPrice.getElements().get(index).getText();
        stringNameGamesMaxDiscount=labNamesGames.getElements().get(index).getText();
        intMaxDiscount=Integer.parseInt(subStrDiscount.get(index));
        doublePrice =Double.parseDouble(subStrPrice.get(index));
        System.out.format("Находим игру с максимальной скидкой: %s, со скидкой %s, ценой %s.%n", stringNameGamesMaxDiscount, stringMaxDiscount, stringPrice);
        System.out.format("Переходим по этой ссылке%n");
        labDiscount.getElements().get(index).click();
    }

    //Метод проверяет, открывается ли страница запроса возраста, и, если открывается, то после этого переходим на странцу игры
    public void openAgeQueryScreen() {
        AgeQueryScreenForm ageQueryScreen=new AgeQueryScreenForm();
        if(ageQueryScreen.isAgeWarning()) {
            System.out.format("Открылась страница запроса возраста. Переходим на страницу выбранной игры.%n");
            ageQueryScreen.clickButtonViewPage();
        }
        else {
            System.out.format("Страница запроса возрастра не загружалась.%n");
        }
    }
}
