package br.alexsantos.pessoa.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.alexsantos.model.PessoaModel;
import br.alexsantos.repository.PessoaRepository;

/**
 * Classe Criada para poder Exportar XML atrav�s do StreamedContent, usado para transfer�ncia de arquivos, videos e fotos pelo navegador.
 * Foi criados dois m�todos uma para gerar o arquivo e outro para realizar download do arquivo xml que ser� exportado.
 *
 * @author Alex Santos Rocha
 * @Data 06/11/2016 �s 21:54
 */
@Named(value="exportarRegistrosXmlController") // nome do controlador de classe para ter acesso pelo contexto utilizando CDI.
@RequestScoped //Conteiner do CDI, que visa controlar uma transa��o passando valores atrav�s de um beans usando em transa��es.
public class ExportarRegistrosXmlController implements Serializable {

	private static final long serialVersionUID = 1L;//vers�o da classe

	@Inject transient // Anota��o para inje��o de dep�ndencia do objeto
	PessoaRepository pessoaRepository; // variav�l que ser� injetada no contexto do CDI

	private StreamedContent arquivoDownload; // inst�ncia da variav�l que envia o conteudo para download

	/***
	 * Realiza Download do arquivo XML
	 * @return retornda o objeto do tipo StreamedContent
	 */
	public StreamedContent getArquivoDownload() {

		this.DownlaodArquivoXmlPessoa(); // chama o m�todo que realiza download do arquivo xml.

		return arquivoDownload;
	}

	/**
	 * Gera o arquivo do tipo pessoa para exporta��o em xml
	 * @return arquivo xml, ou retorna nulo
	 */
	private File GerarXmlPessoas(){

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"); // Mascara para formatar data ao adicionar no aqruivo XML

		List<PessoaModel> pessoasModel = pessoaRepository.GetPessoas(); // Carrega a lista do tipo pessoa, atrav�s do m�todo contido objeto pessoaRepository

		Element elementPessoas = new Element("Pessoas"); //Emento raiz do arquivo xml que ser� gerado

		Document documentoPessoas = new Document(elementPessoas); //nesse momento libera, a grava��o do arquivo XML

		pessoasModel.forEach(pessoa -> { //nessa linha usa API do Java 8. Pecorre a lista pessaosModel

			Element elementPessoa = new Element("Pessoa"); // monta a tag do xml do tipo pessoa
			elementPessoa.addContent(new Element("codigo").setText(pessoa.getCodigo().toString())); // monta a tag codigo com o valor respectivo ao atributo da classe pessoa
			elementPessoa.addContent(new Element("nome").setText(pessoa.getNome())); // monta a tag nome com o valor respectivo ao atributo da classe pessoa
			elementPessoa.addContent(new Element("sexo").setText(pessoa.getSexo())); // monta a tag sexo com o valor respectivo ao atributo da classe pessoa

			String dataCadastroFormatada = pessoa.getDataCadastro().format(dateTimeFormatter); // formata a data

			elementPessoa.addContent(new Element("dataCadastro").setText(dataCadastroFormatada));// monta a tag dataCadastro com o valor respectivo ao atributo da classe pessoa

			elementPessoa.addContent(new Element("email").setText(pessoa.getEmail())); // monta a tag email com o valor respectivo ao atributo da classe pessoa
			elementPessoa.addContent(new Element("endereco").setText(pessoa.getEndereco())); // monta a tag endere�o com o valor respectivo ao atributo da classe pessoa
			elementPessoa.addContent(new Element("origemCadastro").setText(pessoa.getOrigemCadastro())); // monta a tag origemCadastro com o valor respectivo ao atributo da classe pessoa
			elementPessoa.addContent(new Element("usuarioCadastro").setText(pessoa.getUsuarioModel().getUsuario())); // monta a tag usu�rio que est� realizando a opera��o

			elementPessoas.addContent(elementPessoa); // adiciona todas tag criadas para uma pessoa
		});

		XMLOutputter xmlGerado = new XMLOutputter(); // Cria um novo documento JDOM que � um fluxo de bytes, que fica armazenado na mem�ria, funciona como um gerador de documentos

		try {

			String nomeArquivo =  "pessoas_".concat(java.util.UUID.randomUUID().toString()).concat(".xml"); // gera o nome do arquivo xml, e atribui o valor para variav�l nomeArquivo

			File arquivo = new File(System.getProperty("catalina.base")+ "/".concat(nomeArquivo)); // catalina.base, path onde meu projeto est� sendo executado e concateno ao final o nome do meu arquivo xml que ser� gerado

			FileWriter fileWriter =  new FileWriter(arquivo); // passa o objeto do tipo File de input e output no construtor do novo objeto criado do tipo FileWriter e atriubi o valor para variav�l fileWriter

			xmlGerado.output(documentoPessoas, fileWriter); // escreve o documento xml

			return arquivo; // retorna o arquivo

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null; // retorna nulo
	}

	/**
	 * Gera o arquivo XML, e prepara o mesmo para download
	 */
	public void DownlaodArquivoXmlPessoa(){

		File arquivoXml = this.GerarXmlPessoas(); // chama o m�todo para gerar o arquivo xml

		InputStream inputStream; // inst�ncia de uma classe abstrata e superclasse de todas as classes representando um fluxo de entrada de bytes.

		try {
			inputStream = new FileInputStream(arquivoXml.getPath()); // cria um novo objeto, que realizar� o input do arquivo na path passada no construtor

			arquivoDownload = new DefaultStreamedContent(inputStream,"application/xml",arquivoXml.getName()); //Transmite o arquivo XML, em um Stream de bytes, atribuido a uma variav�l do tipo StreamedContent

		}catch (FileNotFoundException e) {

			e.printStackTrace();
		}
	}
}