package br.alexsantos.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Classe utilizada para manipular o objeto usu�rio
 * A classe trabalha com arquiterura JPA
 * @author Alex Santos Rocha
 * @date 02/11/2016 �s 19:08
 */

@Table(name="tb_usuario") //Nome da tabela na base de dados
@Entity	//Declara��o da Entidade
@NamedQuery(name = "UsuarioEntity.findUser",
		    query= "SELECT u FROM UsuarioEntity u WHERE u.usuario = :usuario AND u.senha = :senha") //query que ir� altenficar o usu�rio no banco de dados
public class UsuarioEntity implements Serializable {

	private static final long serialVersionUID = 1L; //vers�o da classe

	@Id //annotation para declarar o primary key da tabela
	@GeneratedValue // gera uma chave toda vez que for inserido no banco
	@Column(name="id_usuario") // definiu o nome da coluna forme o campo na tabela usu�rio no banco de dados
	private String codigo; // objeto do tipo da coluna do banco de dados

	@Column(name="ds_login") // definiu o nome da coluna forme o campo na tabela usu�rio no banco de dados
	private String usuario; // objeto do tipo da coluna do banco de dados

	@Column(name="ds_senha") // definiu o nome da coluna forme o campo na tabela usu�rio no banco de dados
	private String senha; // objeto do tipo da coluna do banco de dados

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