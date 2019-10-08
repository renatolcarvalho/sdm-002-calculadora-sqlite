package br.edu.ifsp.scl.calculadorasdmkt.model

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.json.JSONObject

public class StorageConfigSharedPreferences(context: Context) : StorageConfigDao{
    // Constantes
    companion object{
        val MODO_ARQUIVO = Context.MODE_PRIVATE
        val STORAGE_CONFIG_PATH = "pathconfig"
        val STORAGE_CONFIG_TAG = "pathconfig"
    }

    val sharedPreferences: SharedPreferences
    val gson: Gson

    init{
        sharedPreferences = context.getSharedPreferences(STORAGE_CONFIG_PATH,
            MODO_ARQUIVO)
        gson = GsonBuilder().create()
    }

    override fun createOrUpdateStorageConfig(config: StorageConfig) {
        val configuracaoJson = JSONObject(gson.toJson(config))
        val spEditor: SharedPreferences.Editor = sharedPreferences.edit()
        spEditor.putString(STORAGE_CONFIG_TAG, configuracaoJson.toString())
        spEditor.commit()
    }

    override fun readStorageConfig() : StorageConfig {
        val configuracaoString = sharedPreferences.getString(STORAGE_CONFIG_TAG, "")
        return if (configuracaoString != "")
            gson.fromJson(configuracaoString, StorageConfig::class.java)
        else
            StorageConfig()
    }
}