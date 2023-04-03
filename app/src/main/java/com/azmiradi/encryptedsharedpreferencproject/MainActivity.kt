package com.azmiradi.encryptedsharedpreferencproject

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.security.crypto.EncryptedSharedPreferences

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val saveBu = findViewById<Button>(R.id.save)
        val name = findViewById<EditText>(R.id.name)

        val readBu = findViewById<Button>(R.id.read)
        val text = findViewById<TextView>(R.id.text)

        saveBu.setOnClickListener {
            saveName(name.text.toString())
        }

        readBu.setOnClickListener {
            text.text = readName()
        }
    }

    private fun saveName(name: String) {
        val sharedPrefsFile: String = "shared_setting"
        val sharedPreferences: SharedPreferences = EncryptedSharedPreferences.create(
            sharedPrefsFile,
            "key_setting",
            applicationContext,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

        with(sharedPreferences.edit()) {
            putString("name", name)
            apply()
        }
    }

    private fun readName(): String {
        val sharedPrefsFile: String = "shared_setting"
        val sharedPreferences: SharedPreferences = EncryptedSharedPreferences.create(
            sharedPrefsFile,
            "key_setting",
            applicationContext,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
        return sharedPreferences.getString("name","not found data")?:"not found data"
    }
}

