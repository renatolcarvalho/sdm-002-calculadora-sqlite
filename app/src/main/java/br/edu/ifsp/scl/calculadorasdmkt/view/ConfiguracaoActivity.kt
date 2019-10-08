package br.edu.ifsp.scl.calculadorasdmkt.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.calculadorasdmkt.R
import br.edu.ifsp.scl.calculadorasdmkt.controller.ConfiguracaoController
import br.edu.ifsp.scl.calculadorasdmkt.model.Configuracao
import br.edu.ifsp.scl.calculadorasdmkt.model.Separador
import br.edu.ifsp.scl.calculadorasdmkt.model.StorageConfig
import kotlinx.android.synthetic.main.activity_configuracao.*
import kotlinx.android.synthetic.main.toolbar.*

class ConfiguracaoActivity: AppCompatActivity() {
    object Constantes {
        // Chave de retorno para a MainActivity
        val CONFIGURACAO = "CONFIGURACAO"
        val STORAGE_CONFIG = "STORAGE_CONFIG"
    }

    // Referência para Controller
    lateinit var configuracaoController: ConfiguracaoController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuracao)

        // Toolbar
        toolbar.title = "Configuração"
        setSupportActionBar(toolbar)

        // Chama controller e atualizar view
        configuracaoController = ConfiguracaoController(this)
        configuracaoController.buscaConfiguracao()
    }

    // Função chamada pelo Controller depois de acessar o Model
    fun atualizaView(configuracao: Configuracao, storageConfig: StorageConfig) {
        // Ajusta o leiaute conforme a configuração
        storageConfigSpin.setSelection(if (storageConfig.database) 1 else 0 )
        leiauteSpn.setSelection( if (configuracao.leiauteAvancado) 1 else 0 )
        separadorRg.check(
            if (configuracao.separador == Separador.PONTO)
                R.id.pontoRb
            else
                R.id.virgulaRb
        )

        // SETAR RESULTADO PARA MAIN ACTIVTY
        setResult(AppCompatActivity.RESULT_OK, Intent().
            putExtra(Constantes.CONFIGURACAO, configuracao).
            putExtra(Constantes.STORAGE_CONFIG, storageConfig))
    }

    fun onClickSalvaConfiguracao(v: View) {
        // Pega os dados da tela
        val leiauteAvancado = leiauteSpn.selectedItemPosition == 1
        val separador = if (pontoRb.isChecked) Separador.PONTO else Separador.VIRGULA
        val database = storageConfigSpin.selectedItemPosition == 1

        // Criar um objeto Configuracao
        val novaConfiguracao: Configuracao = Configuracao(leiauteAvancado, separador)
        var storageConfig: StorageConfig = StorageConfig(database)

        // Chamar o Controller para salvar
        configuracaoController.salvaConfiguracao(novaConfiguracao, storageConfig)

        Toast.makeText(this, "Configuração salvaa!", Toast.LENGTH_SHORT).show()
    }
}