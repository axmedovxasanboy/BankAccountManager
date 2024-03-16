package uz.bank.db.read;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import uz.bank.db.DataBase;
import uz.bank.db.Files;
import uz.bank.model.Admin;
import uz.bank.model.Card;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class CardFiles extends Files implements Callable<String> {
    private DataBase db = new DataBase();
    @Override
    public String call() {
        try {
            JsonReader cardInfo;
            Gson gson = new Gson();
            try {
                cardInfo = new JsonReader(new FileReader("src\\main\\java\\uz\\bank\\db\\json\\card.json"));
            } catch (FileNotFoundException e) {
                return "Error occurred" + e.getMessage();
            }
            Type type = TypeToken.getParameterized(ArrayList.class, Card.class).getType();
            List<Card> cards = db.getCards();
            cards.addAll(gson.fromJson(cardInfo, type));

            return "Success. All admin files loaded";
        } catch (Exception e) {
            return "Error occurred \n" + e.getMessage();
        }
    }
}
