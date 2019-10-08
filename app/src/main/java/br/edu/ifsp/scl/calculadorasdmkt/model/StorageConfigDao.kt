package br.edu.ifsp.scl.calculadorasdmkt.model

interface StorageConfigDao {
    fun createOrUpdateStorageConfig(storageConfig: StorageConfig)
    fun readStorageConfig(): StorageConfig
}