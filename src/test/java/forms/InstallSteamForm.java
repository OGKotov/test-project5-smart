package forms;

import framework.browser.BrowserFactory;
import framework.browser.BrowserSingleton;
import framework.elements.DownloadElement;
import org.openqa.selenium.By;
import java.io.File;



/*Класс предназначен для работы со странницей Скачивания SteamSetup, инициализирует необходимые для работы с ней переменные,
осуществает необходимую для задач тестирования работу
 */
public class InstallSteamForm extends BaseForm {

    private static String stringTitleRu="Steam — потрясающая игровая Интернет-платформа";
    private static String stringTitleEn="Steam, The Ultimate Online Game Platform";

    private By locatorInstallSteamRu= By.xpath("//*[@id=\"about_header_area\"]//*[text()='Установить Steam']");
    private By locatorInstallSteamEn=By.xpath("//*[@id=\"about_header_area\"]//*[text()='Install Steam Now']");

    private String downloadFileName="SteamSetup.exe";
    private DownloadElement clickInstall=new DownloadElement(selectLanguageByPar(locatorInstallSteamRu, locatorInstallSteamEn));
    private long sizeSteam=0;

    public InstallSteamForm() {
        super(selectLanguageStringPar(stringTitleRu, stringTitleEn));
    }

    public long getSizeSteam() {
        return sizeSteam;
    }

    public String getDownloadFileName() {
        return downloadFileName;
    }

    //Метод скачивает Steam
    public void installSteamClick() {
        System.out.format("Скачиваем Steam.%n");
        clickInstall.click();
    }

    //Метод ожидает скачивания Steam, еси Steam скачался, инициализирует его размер. Удаляет полностью всю папку загрузок
    public boolean waitDownloadSteamSetup() {
        boolean isDownloaded=clickInstall.waitForFileDownload(BrowserSingleton.getExplicitlyWaitTime(), BrowserFactory.getDownloadDirectoryPath(),
                    downloadFileName);
        File downloadedDrectory=new File(BrowserFactory.getDownloadDirectoryPath());
        File downloadedFile=new File(BrowserFactory.getDownloadDirectoryPath(), downloadFileName);
        if(isDownloaded) {
            sizeSteam=downloadedFile.length();
            downloadedFile.delete();
            downloadedDrectory.delete();
            return true;
        }
        else {
            File[] files=downloadedDrectory.listFiles();
            for (File file: files) {
                file.delete();
            }
            downloadedDrectory.delete();
            return false;
        }
    }

}
