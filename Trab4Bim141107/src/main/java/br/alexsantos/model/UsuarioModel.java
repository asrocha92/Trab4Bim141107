package br.alexsantos.model;

import java.io.Serializable;
/**
 * Classe que será serializada para passar valores no context de um conteiner
 * @author Alex Santos ROcha
 * @data 02/111/2016 às 19:24
 */

public class UsuarioModel implements Serializable {

	private static final long serialVersionUID = 1L;//versão da classe

	private String codigo; //variavél compativél com a tabela do banco de dados
	private String usuario; //variavél compativél com a tabela do banco de dados
	private String senha; //variavél compativél com a tabela do banco de dados

	/**
	 * retorna o objeto do tipo codigo
	 * @return codigo
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * O parâmetro passa seu valor para o objeto da classe
	 * @param codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	/**
	 * Retorna o obejto armazenado em usuário
	 * @return usuario
	 */
	public String getUsuario() {
		return usuario;
	}
	/**
	 * O parâmetro passa seu valor para o objeto da classe
	 * @param usuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	/**
	 * Retorna o obejto armazenado na senha
	 * @return senha
	 */
	public String getSenha() {
		return senha;
	}
	/**
	 * O parâmetro passa seu valor para o objeto da classe
	 * @param senha
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}


}