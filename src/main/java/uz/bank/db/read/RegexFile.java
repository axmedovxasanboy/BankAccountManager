package uz.bank.db.read;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import uz.bank.db.DataBase;
import uz.bank.db.Files;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import java.util.concurrent.Callable;

public class RegexFile extends Files implements Callable<String> {
    private final DataBase db = new DataBase();

    @Override
    public String call() {
        try {
            JsonReader regex;
            Gson gson = new Gson();
            try {
                regex = new JsonReader(new FileReader("src\\main\\java\\uz\\bank\\db\\json\\regex.json"));
            } catch (FileNotFoundException e) {
                return "Error occurred" + e.getMessage();
            }
            Map<String, String> regexes = gson.fromJson(regex, Map.class);
            db.setRegex(regexes);

            return "Success. All admin files loaded";
        } catch (Exception e) {
            return "Error occurred \n" + e.getMessage();
        }
    }
}
