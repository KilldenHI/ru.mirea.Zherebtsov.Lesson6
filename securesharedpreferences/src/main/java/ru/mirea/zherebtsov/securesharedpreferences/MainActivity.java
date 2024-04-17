package ru.mirea.zherebtsov.securesharedpreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;

import java.io.IOException;
import java.security.GeneralSecurityException;

import ru.mirea.zherebtsov.securesharedpreferences.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding	=	ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        KeyGenParameterSpec keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC;
        try {
            String mainKey = MasterKeys.getOrCreate(keyGenParameterSpec);
            SharedPreferences securePreferences = EncryptedSharedPreferences.create(
                    "secret_shared_pref",
                    mainKey,
                    getBaseContext(),
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
            securePreferences.edit().putString("NAME", "Джейсон Стетхэм").apply();
            binding.textView.setText(securePreferences.getString("NAME", ""));
            binding.imageView.setImageResource(R.drawable.stetem);
        } catch (IOException | GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }
}