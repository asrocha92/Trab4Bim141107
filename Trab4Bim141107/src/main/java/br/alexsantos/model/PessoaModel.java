package br.alexsantos.model;

import java.time.LocalDateTime;

/**
 * Modelo classe do objeto real Pessoa.
 * @author Alex Santos Rocha
 * @Data 05/11/2016 às 10:25
 */

public class PessoaModel {

	private Integer 		codigo; // objeto para armazenar o código da pessoa
	private String  		nome; // objeto para armazenar o nome da pessoa
	private String  		sexo; // objeto para armazenar o sexo da pessoa
	private LocalDateTime	dataCadastro; // objeto para armazenar o dataCadastro da pessoa
	private String  		email; // objeto para armazenar o email da pessoa
	private String  		endereco; // objeto para armazenar o endereco da pessoa
	private String  		origemCadastro; // objeto para armazenar o origemCadastro da pessoa
	private UsuarioModel    usuarioModel; // objeto para armazenar o objeto do tipo usuarioModel da pessoa

	/**
	  * @return retorna o valor da informação armazenado no objeto
	  */
	 public Integer getCodigo() {
	  return codigo;
	 }
	 /**
	  * Armazena valor passado por parâmetro no atributo da classe
	  * @param codigo
	  */
	 public void setCodigo(Integer codigo) {
	  this.codigo = codigo;
	 }
	 /**
	  * @return retorna o valor da informação armazenado no objeto
	  */
	 public String getNome() {
	  return nome;
	 }
	 /**
	  * Armazena valor passado por parâmetro no atributo da classe
	  * @param nome
	  */
	 public void setNome(String nome) {
	  this.nome = nome;
	 }
	 /**
	  * @return retorna o valor da informação armazenado no objeto
	  */
	 public String getSexo() {
	  return sexo;
	 }
	 /**
	  * Armazena valor passado por parâmetro no atributo da classe
	  * @param sexo
	  */

	 public void setSexo(String sexo) {
	  this.sexo = sexo;
	 }
	 /**
	  * @return retorna o valor da informação armazenado no objeto
	  */
	 public LocalDateTime getDataCadastro() {
	  return dataCadastro;
	 }
	 /**
	  * Armazena valor passado por parâmetro no atributo da classe
	  * @param dataCadastro
	  */
	 public void setDataCadastro(LocalDateTime dataCadastro) {
	  this.dataCadastro = dataCadastro;
	 }
	 /**
	  * @return retorna o valor da informação armazenado no objeto
	  */
	 public String getEmail() {
	  return email;
	 }
	 /**
	  * Armazena valor passado por parâmetro no atributo da classe
	  * @param email
	  */
	 public void setEmail(String email) {
	  this.email = email;
	 }
	 /**
	  * @return retorna o valor da informação armazenado no objeto
	  */
	 public String getEndereco() {
	  return endereco;
	 }
	 /**
	  * Armazena valor passado por parâmetro no atributo da classe
	  * @param endereco
	  */
	 public void setEndereco(String endereco) {
	  this.endereco = endereco;
	 }
	 /**
	  * @return retorna o valor da informação armazenado no objeto
	  */
	 public String getOrigemCadastro() {
	  return origemCadastro;
	 }
	 /**
	  * Armazena valor passado por parâmetro no atributo da classe
	  * @param origemCadastro
	  */
	 public void setOrigemCadastro(String origemCadastro) {
	  this.origemCadastro = origemCadastro;
	 }
	 /**
	  * @return retorna o valor da informação armazenado no objeto do Tipo usuarioModel
	  */
	 public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	 }
	 /**
	  * Armazena valor passado por parâmetro no atributo da classe
	  * @param usuarioModel
	  */
	 public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	 }



}