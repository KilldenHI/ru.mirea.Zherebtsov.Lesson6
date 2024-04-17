package ru.mirea.zherebtsov.rumireazherebtsovlesson6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import ru.mirea.zherebtsov.rumireazherebtsovlesson6.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private SharedPreferences.Editor	editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding	=	ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences sharedPref = getSharedPreferences("mirea_settings",	Context.MODE_PRIVATE);
        editor	=	sharedPref.edit();

        binding.editText1.setText(sharedPref.getString("GROUP", ""));
        binding.editText2.setText(sharedPref.getString("NUMBER", ""));
        binding.editText3.setText(sharedPref.getString("FILM", ""));
    }
    public void DataSave(View view){
        editor.putString("GROUP", binding.editText1.getText().toString());
        editor.putString("NUMBER", binding.editText2.getText().toString());
        editor.putString("FILM", binding.editText3.getText().toString());
        editor.apply();
    }
}