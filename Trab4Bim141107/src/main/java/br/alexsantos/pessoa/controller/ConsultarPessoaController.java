package br.alexsantos.pessoa.controller;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.alexsantos.model.PessoaModel;
import br.alexsantos.repository.PessoaRepository;

/**
 * Classe para realizar consulta de pessoas cadastradas.
 * @author Alex Santos Rocha
 * @Data 05/11/2016 às 19:10
 */

@Named(value="consultarPessoaController") //Anotação para indenticar a o controle de consulta da Pessoa.
@ViewScoped //Conteiner do CDI, que visa controlar uma transação passando valores através de um beans usando em transações.
public class ConsultarPessoaController implements Serializable {

	private static final long serialVersionUID = 1L; //versão da classe

	@Inject transient // Determina que esse contexto, irá ter uma transição em sua injeção
	private PessoaModel pessoaModel;

	@Produces // Anotação utilizada para métodos génericos pois possui mais de um valor em sua injeção
	private List<PessoaModel> pessoas;

	@Inject transient // Determina que esse contexto, irá ter uma transição em sua injeção
	private PessoaRepository pessoaRepository;

	/**
	 * @return retorna um lista de pessoa
	 */
	public List<PessoaModel> getPessoas() {
		return pessoas;
	}
	/**
	 * O valor do prâmetro é uma lista, a qual é atribuida para variavél da classe
	 * @param pessoas
	 */
	public void setPessoas(List<PessoaModel> pessoas) {
		this.pessoas = pessoas;
	}
	/**
	 * @return objeto do tipo pessoaModel
	 */
	public PessoaModel getPessoaModel() {
		return pessoaModel;
	}
	/**
	 * O valor do prâmetro é um tipo de pessoaModel, que é atribuido para a variavél de classe
	 * @param pessoaModel
	 */
	public void setPessoaModel(PessoaModel pessoaModel) {
		this.pessoaModel = pessoaModel;
	}

	/**
	 * retorna uma lista de pessoa cadastradas chamada pelo método getPessoas e atribui para this.pessoas
	 */
	@PostConstruct // É um método do ciclo de vida de um CDI na aplicação, quando for feita um enjeção do DCI nessa classe vai ser chamado
	public void init(){
		this.pessoas = pessoaRepository.GetPessoas();
	}


	/**
	 * Passa o valor o objeto que foi passado por parâmetro para o objeto de classe do tipo Pessoa modelo
	 * @param pessoaModel
	 */
	public void Editar(PessoaModel pessoaModel){

		/*PEGA APENAS A PRIMEIRA LETRA DO SEXO PARA SETAR NO CAMPO(M OU F)*/
		pessoaModel.setSexo(pessoaModel.getSexo().substring(0, 1)); // Retira somente a primeira letra da palavra exemplo:Masculino, pega a posição 1 que igual a (M) e atribui

		this.pessoaModel = pessoaModel; //atribui o valor do parâmetro

	}

	/**
	 * Realiza alteração do registro que foi alteradoa
	 */
	public void AlterarRegistro(){

		this.pessoaRepository.AlterarRegistro(this.pessoaModel); // chama o método que contem no objeto pessoaRepository e passa o valor do objeto que irá ser alterado.

		this.init(); //carrega o geristro na views do usuário
	}

}