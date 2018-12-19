package framework.utils;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Класс извлекает подстроку из строки, используя регулярные выражения
public class ReturnListSubstrsFromStrRegExp {

    /*Метов извлекает подстроку из строки которя находиться меожду beforeRegExp и afterRegExp регулярными выражениями
    Регулярные выражения и строка передаются в качестве параметров
    Возвращает извлеченную посдтроку
     */
    public ArrayList<String> returnListSubstrsWithStrRegExp(String beforeRegExp, String afterRegExp, String str) {
        ArrayList<String> subStrs = new ArrayList<String>();
        Pattern beforePat = Pattern.compile(beforeRegExp);
        Matcher beforeMat = beforePat.matcher(str);
        Pattern afterPat = Pattern.compile(afterRegExp);
        Matcher afterMat = afterPat.matcher(str);
        while (beforeMat.find() && afterMat.find()) {
            int start = beforeMat.end();
            int end = afterMat.start();
            subStrs.add(str.substring(start, end));
        }
        return subStrs;
    }

    /*Метод получает список строк и возвращает список подстрок, который соответствует регулярному выражению
     */
    public ArrayList<String> returnListSubstrFromListStrRegExp(ArrayList<String> arrList, String innerRegExp) {
        ArrayList<String> subStrs = new ArrayList<String>();
        Pattern innerPat = Pattern.compile(innerRegExp);
        for (int i = 0; i < arrList.size(); i++) {
            Matcher mat = innerPat.matcher(arrList.get(i));
            if (mat.find()) {
                subStrs.add(i, arrList.get(i).substring(mat.start(), mat.end()));
            } else {
                System.out.format("Проверьте регулярное выражение. Соответствие не найдено.%n");
            }
        }
        return subStrs;

    }
}