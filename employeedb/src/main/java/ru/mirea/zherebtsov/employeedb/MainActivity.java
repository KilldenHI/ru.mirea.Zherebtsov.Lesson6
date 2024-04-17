package ru.mirea.zherebtsov.employeedb;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase db = App.getInstance().getDatabase();
        EmployeeDao employeeDao = db.employeeDao();
        Employee employee = new Employee();
        employee.id = 1;
        employee.name = "Iron man";
        employee.info = "Rich";
        // запись в базу
        employeeDao.insert(employee);
        // Загрузка всех героев
        List<Employee> employees = employeeDao.getAll();
        // Получение определенного героя с id = 1
        employee = employeeDao.getById(1);
        // Обновление полей объекта
        employee.info = "Very rich";
        employeeDao.update(employee);
        Log.d(TAG, employee.name + " " + employee.info);
    }
}