package br.edu.ifsp.scl.calculadorasdmkt.controller

import br.edu.ifsp.scl.calculadorasdmkt.model.Configuracao
import br.edu.ifsp.scl.calculadorasdmkt.model.ConfiguracaoService
import br.edu.ifsp.scl.calculadorasdmkt.model.StorageConfig
import br.edu.ifsp.scl.calculadorasdmkt.view.ConfiguracaoActivity

class ConfiguracaoController(val view: ConfiguracaoActivity) {
    // Model
    val model: ConfiguracaoService
    init{
        model = ConfiguracaoService(view.applicationContext)
    }

    fun salvaConfiguracao(configuracao: Configuracao, storageConfig: StorageConfig) {
        model.setConfiguracao(configuracao)
        model.setStorageConfig(storageConfig)
        view.atualizaView(configuracao, storageConfig)
    }

    fun buscaConfiguracao() {
        val configuracao = model.getConfiguracao()
        val storageConfig = model.getStorageConfig()
        view.atualizaView(configuracao, storageConfig)
    }
}