package br.alexsantos.pessoa.controller;

import java.util.Hashtable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.PieChartModel;

import br.alexsantos.repository.PessoaRepository;

/**
 * Classe para controlar gráfico do tipo pessoa
 * @author Alex Santos Rocha
 * @data 06/11/2016 às 16:14
 */

@Named(value="graficoPizzaPessoaController") // adiciona um nome para o controlador da classe ser acessado pelo contexto do CDI
@RequestScoped  //Conteiner do CDI, que visa controlar uma transação passando valores através de um beans usando em transações.
public class GraficoPizzaPessoaController {

	@Inject // Anotação para injeção de depêndencia do objeto
	private PessoaRepository pessoaRepository; // criada um variavél do classe do tipo pessoaRepository

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

		this.MontaGrafico(); //chama o método para motar o gráfico
	}

	/**
	 * Método para monta o gráfico da página
	 */
	private void MontaGrafico(){

		Hashtable<String, Integer> hashtableRegistros = pessoaRepository.GetOrigemPessoa(); //Implementação de tabela hash com base do Mapa interface

		hashtableRegistros.forEach((chave,valor) -> { //usando api do java 8, percorrendo a Hash Table mapeada

			pieChartModel.set(chave, valor); // nesse momento adiciona os valores para poder criar o gráfico.

		});

		pieChartModel.setTitle("Total de Pessoas cadastrado por Tipo"); // Informa um título para o gráfico
		pieChartModel.setShowDataLabels(true); // gerenciando a aparência para legenda do gráfico
		pieChartModel.setLegendPosition("e"); // Seta posição da legenda

	}
}