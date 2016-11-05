package br.alexsantos.pessoa.controller;


import java.io.IOException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.primefaces.model.UploadedFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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

	private UploadedFile file; // caminho do arquivo que vai ser� lido.

	/**
	 * @return file, retorna o caminho para o arquivo que ir� ser lido
	 */
	public UploadedFile getFile() {
		return file;
	}
	/**
	 * Passa por par�metro o arquivo que vai ser lido e atribuido a variav�l de classe.
	 * @param file
	 */
	public void setFile(UploadedFile file) {
		this.file = file;
	}

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

	/**
	 * M�todo que realiza upLoand do arquivo.
	 * @return Se o caminho onde o arquivo esta armazenado n�o for passado para variav�l file da classe retorna uma mensagem informando.
	 * @return Se for passado o nome do arquivo ir� retorna uma mensagem de sucesso.
	 */
	 public void UploadRegistros() {

		 DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); // inst�ncia um objeto para chamar m�todos para ler o arquivo

		 try {
			 //verifica se o caminho passado n�o � nulo se for deve lan�ar uma mensagem para o usu�rio
			 if(this.file.getFileName().isEmpty()){
				 Uteis.MensagemAtencao("Nenhum arquivo selecionado!"); // chama o m�todo da classe Uteis e retorna uma mensagem para o usu�rio
				 return; // somente para finalizar o m�todo
			 }

			 DocumentBuilder builder = factory.newDocumentBuilder(); // a instancia desse documento, configurando os par�metros do XML

                Document doc = builder.parse(this.file.getInputstream()); //nesse momento libera, a leitura do arquivo XML

                Element element = doc.getDocumentElement();  // retorna quais s�o os tipos de elementos armazenadosa

                NodeList nodes = element.getChildNodes(); // retorna uma lista de elementos do documento que foi passado

                element.getChildNodes();
                //Os valores armazenados no arquivo ser�o percorridos nesse for
                for (int i = 0; i < element.getChildNodes().getLength(); i++) {

                	Node node  = nodes.item(i);// retora os dados na estrutura onde est� armazenado o item

                	//verifica se os tipos dos elemento comparados s�o iguais
                	if(node.getNodeType() == Node.ELEMENT_NODE){

                		Element elementPessoa =(Element) node; //  atribui o valor, para o elemento, faendo cast de um objeto para outro

                		String nome     = elementPessoa.getElementsByTagName("nome").item(0).getChildNodes().item(0).getNodeValue();//retorna arquivos compativeis com os nomes das colunas que est�o armazenados no XML
                		String sexo     = elementPessoa.getElementsByTagName("sexo").item(0).getChildNodes().item(0).getNodeValue();//retorna arquivos compativeis com os nomes das colunas que est�o armazenados no XML
                		String email    = elementPessoa.getElementsByTagName("email").item(0).getChildNodes().item(0).getNodeValue();//retorna arquivos compativeis com os nomes das colunas que est�o armazenados no XML
                		String endereco = elementPessoa.getElementsByTagName("endereco").item(0).getChildNodes().item(0).getNodeValue();//retorna arquivos compativeis com os nomes das colunas que est�o armazenados no XML

                		PessoaModel newPessoaModel = new PessoaModel(); // inicia uma Inst�ncia do obejto Pessoal Modelo

                		newPessoaModel.setUsuarioModel(this.usuarioController.GetUsuarioSession()); // verifica a sess�o do usu�rio
                		newPessoaModel.setEmail(email); // adiciono o valor do atributo armazenado na variavel, para o atributo da classe Pessoa modelo atrav�s do m�todo setEmail(email)
                		newPessoaModel.setEndereco(endereco); // adiciono o valor do atributo armazenado na variavel, para o atributo da classe Pessoa modelo atrav�s do m�todo setEndereco(endereco)
                		newPessoaModel.setNome(nome); // adiciono o valor do atributo armazenado na variavel, para o atributo da classe Pessoa modelo atrav�s do m�todo setNome(nome)
	        		 	newPessoaModel.setOrigemCadastro("X"); // adiciono o valor do atributo armazenado na variavel, para o atributo da classe Pessoa modelo atrav�s do m�todo setOrigem(origem)
	        		 	newPessoaModel.setSexo(sexo); // adiciono o valor do atributo armazenado na variavel, para o atributo da classe Pessoa modelo atrav�s do m�todo setSexo(sexo)

	        		 	pessoaRepository.SalvarNovoRegistro(newPessoaModel); // chama o m�todo para persistir o arquivo passado para objeto do tipo PessoaModel
                	}
                }

		     Uteis.MensagemInfo("Registros cadastrados com sucesso!"); // chama a mensagem informando para o usu�rio que a opera��o foi um sucesso

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
	 }


}
