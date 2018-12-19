package tests;

import forms.*;
import framework.browser.BrowserSingleton;
import org.testng.annotations.Test;

import static org.testng.Assert.fail;


public class TestSteam extends BaseTest {

    @Test
    public void testSteam() {
         new StartSelectLanguageForm().selectLanguage(); //Выбираем язык тестируемого сайта
         MainForm mainForm=new MainForm(); //Переходим на стартовую страницу
         mainForm.goToAction(); //Переходим по ссылке Экшен
         ActionForm actionForm=new ActionForm(); //Переходим на страницу Action
         actionForm.goTopSelling(); //Переходим на вкладку Лидеры продаж
         actionForm.selectGameWithMaxDiscount();//Выбираем игру с максимальной скидкой и кликаем по ней
         actionForm.openAgeQueryScreen();//Проверяем, открывается ли страницы запроса возраста, и, если открывается, то нажимаем кнопку перехода на страницу с игрой
         ActionGameForm actionGameForm=new ActionGameForm();//Переходим на страницу с загружаемой игрой
        //Проверяем корректность цены и скидки игры
         int expectIntMaxDiscount=ActionForm.getIntMaxDiscount();
         double expectDoublePrice=ActionForm.getDoublePrice();
         String expectStringNameGames=ActionForm.getStringNameGamesMaxDiscount();
         actionGameForm.takeNumberPriceAndDiscount(); //Инициализируем числовые переменные числовым значение цены и скидки открытой игры
         int intMaxDiscount=ActionGameForm.getIntMaxDiscount();
         double doublePrice=ActionGameForm.getDoublePrice();
         System.out.format("Проверяем корректность скидки и цены игры %s.%n", expectStringNameGames);
         if(expectIntMaxDiscount==intMaxDiscount && expectDoublePrice==doublePrice){
             System.out.format("Скидка и цена корректны.%n");
         }
         else{
             String log=String.format("Скидка и цена не корректны. Ожидали скидка: %s, цена: %s. Получили скидка: %s, цена: %s.%n",
                     expectIntMaxDiscount, expectDoublePrice, intMaxDiscount, doublePrice);
             fail(log);
         }
         actionGameForm.goToInstallSteam();//Переходим по ссылке "Установить Steam"
        InstallSteamForm installSteamForm=new InstallSteamForm(); //Переходим на страницу  загрузки Steam
        installSteamForm.installSteamClick(); //Загружаем  Steam
        System.out.format("Проверяем, скачивается ли установочный файл %s за %s секунд.%n", installSteamForm.getDownloadFileName(),
                BrowserSingleton.getExplicitlyWaitTime());
        if(installSteamForm.waitDownloadSteamSetup()&& installSteamForm.getSizeSteam()>0){
            System.out.format("Установочный файл скачивается. Размер файла: %s байт.%n", installSteamForm.getSizeSteam());
        }
        else {
            String log=String.format("Установочный файл не скачивается");
            fail(log);
        }
    }

}
