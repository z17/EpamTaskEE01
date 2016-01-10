package t01.languages;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.ResourceBundle;

// todo: загружать тексты из файла 1 раз и хранить для разных локалей. Сейчас чтение происходит при каждом запросе.
public class Text {
    @NotNull
    public static String get(String key, String localeName) {
        Locale locale;
        if (localeName != null) {
            switch (localeName) {
                case "ru":
                    locale = new Locale("ru", "RU");
                    break;
                case "en":
                    locale = new Locale("en", "US");
                    break;
                default:
                    locale = Locale.getDefault();
                    break;
            }
        } else {
            locale = Locale.getDefault();
        }
        ResourceBundle text = ResourceBundle.getBundle("t01.languages.text", locale);
        return text.getString(key);
    }
}
