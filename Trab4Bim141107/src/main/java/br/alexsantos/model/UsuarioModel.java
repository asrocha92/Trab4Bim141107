package br.alexsantos.model;

import java.io.Serializable;
/**
 * Classe que ser� serializada para passar valores no context de um conteiner
 * @author Alex Santos ROcha
 * @data 02/111/2016 �s 19:24
 */

public class UsuarioModel implements Serializable {

	private static final long serialVersionUID = 1L;//vers�o da classe

	private String codigo; //variav�l compativ�l com a tabela do banco de dados
	private String usuario; //variav�l compativ�l com a tabela do banco de dados
	private String senha; //variav�l compativ�l com a tabela do banco de dados

	/**
	 * retorna o objeto do tipo codigo
	 * @return codigo
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * O par�metro passa seu valor para o objeto da classe
	 * @param codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	/**
	 * Retorna o obejto armazenado em usu�rio
	 * @return usuario
	 */
	public String getUsuario() {
		return usuario;
	}
	/**
	 * O par�metro passa seu valor para o objeto da classe
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
	 * O par�metro passa seu valor para o objeto da classe
	 * @param senha
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}


}