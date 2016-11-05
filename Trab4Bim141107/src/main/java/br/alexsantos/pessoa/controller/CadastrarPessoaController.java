package br.alexsantos.pessoa.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.alexsantos.model.PessoaModel;
import br.alexsantos.repository.PessoaRepository;
import br.alexsantos.usuario.controller.UsuarioController;
import br.alexsantos.uteis.Uteis;

/**
 * Classe para controlar o contexto de cadastro do pessoa
 * @author Alex Santos Rocha
 * @Data 05/11/2016 �s 10:55
 */

@Named(value="cadastrarPessoaController") // Anota��o para nome a nosso controlador da classe CadastrarPessoaController
@RequestScoped // Anota��o para realizar requisi��o atrav�s do Request, atrav�s de um protocolo.
public class CadastrarPessoaController {

	@Inject // Anota��o para inje��o de dep�ndencia do objeto
	PessoaModel pessoaModel; // Objeto que ser� no contexto como conteiner

	@Inject // Anota��o para inje��o de dep�ndencia do objeto
	UsuarioController usuarioController; // Objeto que ser� no contexto como conteiner

	@Inject // Anota��o para inje��o de dep�ndencia do objeto
	PessoaRepository pessoaRepository; // Objeto que ser� no contexto como conteiner

	/**
	 * @return Objeto do tipo pessoaModel
	 */
	public PessoaModel getPessoaModel() {
		return pessoaModel;
	}
	/**
	 * atrav�s do par�metro � atribuido os objetos passado para atributo de classe
	 * @param pessoaModel
	 */
	public void setPessoaModel(PessoaModel pessoaModel) {
		this.pessoaModel = pessoaModel;
	}

	/**
	 * Salva um registro do tipo pessoa armazenando no banco de dados
	 */
	public void SalvarNovaPessoa(){

		pessoaModel.setUsuarioModel(this.usuarioController.GetUsuarioSession()); //verifica � sess�o do usu�rio

		pessoaModel.setOrigemCadastro("I");// Informa que o cadastro foi realizado via input

		pessoaRepository.SalvarNovoRegistro(this.pessoaModel); // salvo o registro do tipo pessoaModel

		this.pessoaModel = null; // seta o objeto de classe com valor null

		Uteis.MensagemInfo("Registro cadastrado com sucesso"); // Informa o usu�rio com uma mensagem passada por par�metro

	}

}