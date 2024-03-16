package uz.bank.db.read;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import uz.bank.db.DataBase;
import uz.bank.db.Files;
import uz.bank.model.User;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class UserFiles extends Files implements Callable<String> {
    private final DataBase db = new DataBase();


    @Override
    public String call() {
        try {
            JsonReader customerInfo;
            Gson gson = new Gson();
            try {
                customerInfo = new JsonReader(new FileReader("src\\main\\java\\uz\\bank\\db\\json\\users.json"));
            } catch (FileNotFoundException e) {
                return "Error occurred" + e.getMessage();
            }
            Type type = TypeToken.getParameterized(ArrayList.class, User.class).getType();
            List<User> customers = db.getUsers();
            customers.addAll(gson.fromJson(customerInfo, type));

            return "Success. All customer files loaded";
        } catch (Exception e) {
            return "Error occurred" + e.getMessage();
        }
    }
}
