package br.alexsantos.pessoa.controller;

import java.util.Hashtable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.PieChartModel;

import br.alexsantos.repository.PessoaRepository;

/**
 * Classe para controlar gr�fico do tipo pessoa
 * @author Alex Santos Rocha
 * @data 06/11/2016 �s 16:14
 */

@Named(value="graficoPizzaPessoaController") // adiciona um nome para o controlador da classe ser acessado pelo contexto do CDI
@RequestScoped  //Conteiner do CDI, que visa controlar uma transa��o passando valores atrav�s de um beans usando em transa��es.
public class GraficoPizzaPessoaController {

	@Inject // Anota��o para inje��o de dep�ndencia do objeto
	private PessoaRepository pessoaRepository; // criada um variav�l do classe do tipo pessoaRepository

	private PieChartModel pieChartModel; // Classe da API do java para criar graficos formato de pizza

	/**
	 * @return Retorna objeto do tipo PieChartModel
	 */
	public PieChartModel getPieChartModel() {
		return pieChartModel;
	}

	@PostConstruct //Inicia o beans injetado no contexto do CDI
	public  void init(){

		this.pieChartModel = new PieChartModel(); // cria um novo obejto

		this.MontaGrafico(); //chama o m�todo para motar o gr�fico
	}

	/**
	 * M�todo para monta o gr�fico da p�gina
	 */
	private void MontaGrafico(){

		Hashtable<String, Integer> hashtableRegistros = pessoaRepository.GetOrigemPessoa(); //Implementa��o de tabela hash com base do Mapa interface

		hashtableRegistros.forEach((chave,valor) -> { //usando api do java 8, percorrendo a Hash Table mapeada

			pieChartModel.set(chave, valor); // nesse momento adiciona os valores para poder criar o gr�fico.

		});

		pieChartModel.setTitle("Total de Pessoas cadastrado por Tipo"); // Informa um t�tulo para o gr�fico
		pieChartModel.setShowDataLabels(true); // gerenciando a apar�ncia para legenda do gr�fico
		pieChartModel.setLegendPosition("e"); // Seta posi��o da legenda

	}
}