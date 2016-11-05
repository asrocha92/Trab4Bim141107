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
 * @Data 05/11/2016 às 10:55
 */

@Named(value="cadastrarPessoaController") // Anotação para nome a nosso controlador da classe CadastrarPessoaController
@RequestScoped // Anotação para realizar requisição através do Request, através de um protocolo.
public class CadastrarPessoaController {

	@Inject // Anotação para injeção de depêndencia do objeto
	PessoaModel pessoaModel; // Objeto que será no contexto como conteiner

	@Inject // Anotação para injeção de depêndencia do objeto
	UsuarioController usuarioController; // Objeto que será no contexto como conteiner

	@Inject // Anotação para injeção de depêndencia do objeto
	PessoaRepository pessoaRepository; // Objeto que será no contexto como conteiner

	/**
	 * @return Objeto do tipo pessoaModel
	 */
	public PessoaModel getPessoaModel() {
		return pessoaModel;
	}
	/**
	 * através do parâmetro é atribuido os objetos passado para atributo de classe
	 * @param pessoaModel
	 */
	public void setPessoaModel(PessoaModel pessoaModel) {
		this.pessoaModel = pessoaModel;
	}

	/**
	 * Salva um registro do tipo pessoa armazenando no banco de dados
	 */
	public void SalvarNovaPessoa(){

		pessoaModel.setUsuarioModel(this.usuarioController.GetUsuarioSession()); //verifica á sessão do usuário

		pessoaModel.setOrigemCadastro("I");// Informa que o cadastro foi realizado via input

		pessoaRepository.SalvarNovoRegistro(this.pessoaModel); // salvo o registro do tipo pessoaModel

		this.pessoaModel = null; // seta o objeto de classe com valor null

		Uteis.MensagemInfo("Registro cadastrado com sucesso"); // Informa o usuário com uma mensagem passada por parâmetro

	}

}