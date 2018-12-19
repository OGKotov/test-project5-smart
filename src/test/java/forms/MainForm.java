package forms;

/*Класс предназначен для работы со стартовой странницей сайта, инициализирует необходимые для работы с ней переменные,
осуществает необходимую для задач тестирования работу
 */
public class MainForm extends BaseForm {

    private static String stringTitleRu="Добро пожаловать в Steam";
    private static String stringTitleEn="Welcome to Steam";

    public MainForm() {
        super(selectLanguageStringPar(stringTitleRu, stringTitleEn));
    }

    //В зависимости от параметра языка в конфигурациооном файле, метод кликает по по ссылке Экшен(Action)
    public void goToAction(){
        if(getStringLanguageSite().equals("RU")){
            System.out.format("Переходим по ссылке \"Игры/Экшен\"%n");
            new TopMenuForm().mouseOver(TopMenuForm.MenuGameRu.Games);
            new TopMenuForm().clickTopMenuElement(TopMenuForm.MenuGameRu.Action);
        }
        else {
            System.out.format("Переходим по ссылке \"Games/Action\"%n");
            new TopMenuForm().mouseOver(TopMenuForm.MenuGameEn.Games);
            new TopMenuForm().clickTopMenuElement(TopMenuForm.MenuGameEn.Action);

        }
    }


}
