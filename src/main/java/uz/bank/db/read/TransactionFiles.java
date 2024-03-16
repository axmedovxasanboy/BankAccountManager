package uz.bank.db.read;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import uz.bank.db.DataBase;
import uz.bank.db.Files;
import uz.bank.model.Transactions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class TransactionFiles extends Files implements Callable<String> {
    private DataBase db = new DataBase();
    @Override
    public String call() {
        try {
            JsonReader info;
            Gson gson = new Gson();
            try {
                info = new JsonReader(new FileReader("src\\main\\java\\uz\\bank\\db\\json\\transactions.json"));
            } catch (FileNotFoundException e) {
                return "Error occurred" + e.getMessage();
            }
            Type type = TypeToken.getParameterized(ArrayList.class, Transactions.class).getType();
            List<Transactions> transactions = db.getTransactions();
            transactions.addAll(gson.fromJson(info, type));

            return "Success. All admin files loaded";
        } catch (Exception e) {
            return "Error occurred \n" + e.getMessage();
        }
    }
}
