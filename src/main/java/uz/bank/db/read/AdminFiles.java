package uz.bank.db.read;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import uz.bank.db.DataBase;
import uz.bank.db.Files;
import uz.bank.model.Admin;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class AdminFiles extends Files implements Callable<String> {
    private final DataBase db = new DataBase();

    @Override
    public String call() {
        try {
            JsonReader adminInfo;
            Gson gson = new Gson();
            try {
                adminInfo = new JsonReader(new FileReader("src\\main\\java\\uz\\bank\\db\\json\\admin.json"));
            } catch (FileNotFoundException e) {
                return "Error occurred" + e.getMessage();
            }
            Type type = TypeToken.getParameterized(ArrayList.class, Admin.class).getType();
            List<Admin> admins = db.getAdmins();
            admins.addAll(gson.fromJson(adminInfo, type));

            return "Success. All admin files loaded";
        } catch (Exception e) {
            return "Error occurred \n" + e.getMessage();
        }
    }
}
