package br.edu.ifsp.scl.calculadorasdmkt.model

interface ConfiguracaoDao {
    fun createOrUpdateConfiguracao(configuracao: Configuracao)
    fun readConfiguracao(): Configuracao
}