package br.edu.ifsp.scl.calculadorasdmkt.model
import android.content.Context

class ConfiguracaoService(context: Context) {

    var configuracaoDao: ConfiguracaoDao
    var storageConfigDao: StorageConfigDao

    init {
        // Inicializando conforme a fonte de dados utilizada
        storageConfigDao = StorageConfigSharedPreferences(context)
        val storageConfigValue = storageConfigDao.readStorageConfig()

        if (storageConfigValue.database)
            configuracaoDao = ConfiguracaoSqlite(context)
        else
            configuracaoDao = ConfiguracaoSharedPreferences(context)
    }

    fun setConfiguracao(configuracao: Configuracao) {
        /* Qualquer tratamento necessário aos dados antes de salvá-los
        na fonte de dados escolhida deve ser feita no Service.
        As classes que implementam o DAO devem esconder as peculiaridades
        para acesso a cada fonte de dados diferente e executar apenas as funções
        de CRUD.*/
        // Tratamento de dados aqui!
        // Delegando ao modelo
        configuracaoDao.createOrUpdateConfiguracao(configuracao)
    }

    fun setStorageConfig(storageConfig : StorageConfig){
        storageConfigDao.createOrUpdateStorageConfig(storageConfig)
    }

    fun getStorageConfig() : StorageConfig {
        return storageConfigDao.readStorageConfig()
    }

    fun getConfiguracao(): Configuracao {
        // Tratamento de dados aqui!
        // Delegando ao modelo
        return configuracaoDao.readConfiguracao()
    }
}