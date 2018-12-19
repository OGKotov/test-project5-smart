package forms;

import framework.elements.Label;
import org.openqa.selenium.By;

//Класс для работы с главным меню.
public class TopMenuForm {

    /*Начальная часть общего меню в текстовой форме*/
    private String menuPase="//*[@id='store_nav_area']//%s";

    /*Конеченая часть в текстовом формате элементов пункта меню "Игры" для русскоязычной версии сайта*/
    enum MenuGameRu {
        Games("*[@id='genre_tab']//*[@class='pulldown_desktop']"),
        FryToPay("*[@id='genre_flyout']/div/./a[1]"),
        EarlyAccsess("*[@id='genre_flyout']/div/./a[2]"),
        Demos("*[@id='genre_flyout']/div/./a[3]"),
        VirtualReality("*[@id='genre_flyout']/div/./a[4]"),
        SteamControllerFriendly("*[@id='genre_flyout']/div/./a[5]"),
        PcCafeGamesOnSteam("*[@id='genre_flyout']/div/./a[6]"),
        Racing("*[@id='genre_flyout']/div/./a[7]"),
        Indie("*[@id='genre_flyout']/div/./a[8]"),
        Casual("*[@id='genre_flyout']/div/./a[9]"),
        MassivelyMultiplayer("*[@id='genre_flyout']/div/./a[10]"),
        Adventure("*[@id='genre_flyout']/div/./a[11]"),
        RPG("*[@id='genre_flyout']/div/./a[12]"),
        Simulation("*[@id='genre_flyout']/div/./a[13]"),
        Sports("*[@id='genre_flyout']/div/./a[14]"),
        Strategy("*[@id='genre_flyout']/div/./a[15]"),
        Action("*[@id='genre_flyout']/div/./a[16]"),
        SeePopular("*[@id='genre_flyout']/div/./a[17]"),
        MacOsX("*[@id='genre_flyout']/div/./a[18]"),
        SteamOsLinux("*[@id='genre_flyout']/div/./a[19]");

        private String pathGame;
        MenuGameRu(String path) {
            this.pathGame = path;
        }

        public String getPathGame() {
            return pathGame;
        }

    }

    /*Метод собирает начальную и конечную часть пункта меню и возыращает локатор этого пункта для русскоязычной
     версии сайта*/
    public  By fullByPath(MenuGameRu item) {
        String stringPath=String.format(menuPase, item.getPathGame());
        By byPath=By.xpath(stringPath);
        return byPath;
    }

    //Метод кликает по передонному в параметре элементу
    public void clickTopMenuElement(MenuGameRu item){
        Label topMenuElement=new Label(fullByPath(item));
        topMenuElement.click();
    }

    //Метод активирует переданный в параметре элемент
    public void mouseOver(MenuGameRu item){
        Label topMenuElement=new Label(fullByPath(item));
        topMenuElement.mouseOverElement();
    }

    /*Конеченая часть в текстовом формате элементов пункта меню "Games" для англоязычной версии сайта*/
    enum MenuGameEn {
        Games("*[@id='genre_tab']//*[@class='pulldown_desktop']"),
        FryToPay("*[@id='genre_flyout']/div/./a[1]"),
        EarlyAccsess("*[@id='genre_flyout']/div/./a[2]"),
        Demos("*[@id='genre_flyout']/div/./a[3]"),
        VirtualReality("*[@id='genre_flyout']/div/./a[4]"),
        SteamControllerFriendly("*[@id='genre_flyout']/div/./a[5]"),
        PcCafeGamesOnSteam("*[@id='genre_flyout']/div/./a[6]"),
        Action("*[@id='genre_flyout']/div/./a[7]"),
        Adventure("*[@id='genre_flyout']/div/./a[8]"),
        Casual("*[@id='genre_flyout']/div/./a[9]"),
        Indie("*[@id='genre_flyout']/div/./a[10]"),
        MassivelyMultiplayer("*[@id='genre_flyout']/div/./a[11]"),
        Racing("*[@id='genre_flyout']/div/./a[12]"),
        RPG("*[@id='genre_flyout']/div/./a[13]"),
        Simulation("*[@id='genre_flyout']/div/./a[14]"),
        Sports("*[@id='genre_flyout']/div/./a[15]"),
        Strategy("*[@id='genre_flyout']/div/./a[16]"),
        SeePopular("*[@id='genre_flyout']/div/./a[17]"),
        MacOsX("*[@id='genre_flyout']/div/./a[18]"),
        SteamOsLinux("*[@id='genre_flyout']/div/./a[19]");

        private String pathGame;
        MenuGameEn(String path) {
            this.pathGame = path;
        }

        public String getPathGame() {
            return pathGame;
        }
    }

    /*Метод собирает начальную и конечную часть пункта меню и возыращает локатор этого пункта для англоязычной
     версии сайта*/
    public  By fullByPath(MenuGameEn item) {
        String stringPath=String.format(menuPase, item.getPathGame());
        By byPath=By.xpath(stringPath);
        return byPath;
    }

    //Метод кликает по передонному в параметре элементу
    public void clickTopMenuElement(MenuGameEn item){
        Label topMenuElement=new Label(fullByPath(item));
        topMenuElement.click();
    }

    //Метод активирует переданный в параметре элемент
    public void mouseOver(MenuGameEn item){
        Label topMenuElement=new Label(fullByPath(item));
        topMenuElement.mouseOverElement();
    }
}
