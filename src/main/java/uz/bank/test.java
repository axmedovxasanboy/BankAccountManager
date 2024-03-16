package uz.bank;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import uz.bank.db.DataBase;
import uz.bank.model.User;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) {
        DataBase db = new DataBase();
        try {
            JsonReader customerInfo = null;
            Gson gson = new Gson();
            try {
                customerInfo = new JsonReader(new FileReader("src\\main\\java\\uz\\bank\\db\\json\\users.json"));
            } catch (FileNotFoundException e) {
                System.out.println("Error occurred" + e.getMessage());
            }
//            customerInfo.
            Type type = TypeToken.getParameterized(ArrayList.class, User.class).getType();
            List<User> customers = db.getUsers();
            if (customerInfo != null) {
                customers.addAll(gson.fromJson(customerInfo, type));
            }

            System.out.println("Success. All customer files loaded");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error occurred \n" + e.getMessage());
        }
    }
}
