package uz.bank.db.read;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import uz.bank.db.DataBase;
import uz.bank.db.Files;
import uz.bank.model.Employee;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class EmployerFiles extends Files implements Callable<String> {
    private final DataBase db = new DataBase();


    @Override
    public String call() {
        try {
            JsonReader employerInfo;
            Gson gson = new Gson();
            try {
                employerInfo = new JsonReader(new FileReader("src\\main\\java\\uz\\bank\\db\\json\\employers.json"));
            } catch (FileNotFoundException e) {
                return "Error occurred" + e.getMessage();
            }
            Type type = TypeToken.getParameterized(ArrayList.class, Employee.class).getType();
            List<Employee> employers = db.getEmployers();
            employers.addAll(gson.fromJson(employerInfo, type));

            return "Success. All employer files loaded";

        } catch (Exception e) {
            return "Error occurred" + e.getMessage();
        }
    }
}
