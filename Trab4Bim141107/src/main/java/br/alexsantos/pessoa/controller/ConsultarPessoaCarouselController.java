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
 * Classe de consulta utilizando Carousel PrimeFaces
 * Clasee implementa a interface serializable
 * @author Alex Santos Rocha
 * @Data 06/11/2016 às 14:54
 */

@Named(value="consultarPessoaCarouselController") // nome do controlador de classe para ter acesso pelo contexto utilizando CDI.
@ViewScoped //Conteiner do CDI, que visa controlar uma transação passando valores através de um beans usando em transações.
public class ConsultarPessoaCarouselController implements Serializable {

	private static final long serialVersionUID = 1L; //versçao da classe

	@Inject transient // Determina que esse contexto, irá ter uma transição em sua injeção
	private PessoaRepository pessoaRepository;

	@Produces // Anotação utilizada para métodos génericos pois possui mais de um valor em sua injeção
	private List<PessoaModel> pessoas;

	/**
	 *  retorna uma lista de pessoas armazena no objeto do tipo lista da classe
	 * @return
	 */
	public List<PessoaModel> getPessoas() {
		return pessoas;
	}

	@PostConstruct // É um método do ciclo de vida de um CDI na aplicação, quando for feita um enjeção do DCI nessa classe vai ser chamado
	private void init(){

		this.pessoas = pessoaRepository.GetPessoas(); // chama o método associado ao objeto pessoaRepository para retorna uma lista de pessoa armazenas e atribuir a variavél de classe pessoas
	}




}