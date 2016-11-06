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
 * @Data 06/11/2016 �s 14:54
 */

@Named(value="consultarPessoaCarouselController") // nome do controlador de classe para ter acesso pelo contexto utilizando CDI.
@ViewScoped //Conteiner do CDI, que visa controlar uma transa��o passando valores atrav�s de um beans usando em transa��es.
public class ConsultarPessoaCarouselController implements Serializable {

	private static final long serialVersionUID = 1L; //vers�ao da classe

	@Inject transient // Determina que esse contexto, ir� ter uma transi��o em sua inje��o
	private PessoaRepository pessoaRepository;

	@Produces // Anota��o utilizada para m�todos g�nericos pois possui mais de um valor em sua inje��o
	private List<PessoaModel> pessoas;

	/**
	 *  retorna uma lista de pessoas armazena no objeto do tipo lista da classe
	 * @return
	 */
	public List<PessoaModel> getPessoas() {
		return pessoas;
	}

	@PostConstruct // � um m�todo do ciclo de vida de um CDI na aplica��o, quando for feita um enje��o do DCI nessa classe vai ser chamado
	private void init(){

		this.pessoas = pessoaRepository.GetPessoas(); // chama o m�todo associado ao objeto pessoaRepository para retorna uma lista de pessoa armazenas e atribuir a variav�l de classe pessoas
	}




}