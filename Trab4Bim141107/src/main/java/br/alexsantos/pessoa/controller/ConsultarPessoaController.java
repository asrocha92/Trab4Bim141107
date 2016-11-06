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
 * @Data 05/11/2016 �s 19:10
 */

@Named(value="consultarPessoaController") //Anota��o para indenticar a o controle de consulta da Pessoa.
@ViewScoped //Conteiner do CDI, que visa controlar uma transa��o passando valores atrav�s de um beans usando em transa��es.
public class ConsultarPessoaController implements Serializable {

	private static final long serialVersionUID = 1L; //vers�o da classe

	@Inject transient // Determina que esse contexto, ir� ter uma transi��o em sua inje��o
	private PessoaModel pessoaModel;

	@Produces // Anota��o utilizada para m�todos g�nericos pois possui mais de um valor em sua inje��o
	private List<PessoaModel> pessoas;

	@Inject transient // Determina que esse contexto, ir� ter uma transi��o em sua inje��o
	private PessoaRepository pessoaRepository;

	/**
	 * @return retorna um lista de pessoa
	 */
	public List<PessoaModel> getPessoas() {
		return pessoas;
	}
	/**
	 * O valor do pr�metro � uma lista, a qual � atribuida para variav�l da classe
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
	 * O valor do pr�metro � um tipo de pessoaModel, que � atribuido para a variav�l de classe
	 * @param pessoaModel
	 */
	public void setPessoaModel(PessoaModel pessoaModel) {
		this.pessoaModel = pessoaModel;
	}

	/**
	 * retorna uma lista de pessoa cadastradas chamada pelo m�todo getPessoas e atribui para this.pessoas
	 */
	@PostConstruct // � um m�todo do ciclo de vida de um CDI na aplica��o, quando for feita um enje��o do DCI nessa classe vai ser chamado
	public void init(){
		this.pessoas = pessoaRepository.GetPessoas();
	}


	/**
	 * Passa o valor o objeto que foi passado por par�metro para o objeto de classe do tipo Pessoa modelo
	 * @param pessoaModel
	 */
	public void Editar(PessoaModel pessoaModel){

		/*PEGA APENAS A PRIMEIRA LETRA DO SEXO PARA SETAR NO CAMPO(M OU F)*/
		pessoaModel.setSexo(pessoaModel.getSexo().substring(0, 1)); // Retira somente a primeira letra da palavra exemplo:Masculino, pega a posi��o 1 que igual a (M) e atribui

		this.pessoaModel = pessoaModel; //atribui o valor do par�metro

	}

	/**
	 * Realiza altera��o do registro que foi alteradoa
	 */
	public void AlterarRegistro(){

		this.pessoaRepository.AlterarRegistro(this.pessoaModel); // chama o m�todo que contem no objeto pessoaRepository e passa o valor do objeto que ir� ser alterado.

		this.init(); //carrega o geristro na views do usu�rio
	}

}