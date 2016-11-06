package br.alexsantos.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Essa Entity é para configuração da tabela pessoa
 * Configurada com JPA
 * @Alex Santos Rocha
 * @Data 04/11/2016 às 13:18
 */

@Entity //Anotação para endentificar que essa classe é uma tabela, de persistência com JPA
@Table(name="tb_pessoa") // Essa anotação indetifica o nome da tabela no banco de dados
@NamedQueries({@NamedQuery(name = "PessoaEntity.findAll",
			               query= "SELECT p FROM PessoaEntity p"), //query para realizar consulta, e trabalhar com persistência do JPA
			  @NamedQuery(name="PessoaEntity.GroupByOrigemCadastro",
			  			   query= "SELECT p.origemCadastro, count(p) as total FROM PessoaEntity p GROUP By p.origemCadastro") // segunda Query é para utlizar para consulta para o grafico, retorna a origem e conta quantos foram cadastrados com a mesma origems

})
public class PessoaEntity {

	 @Id // Anotação de indetificação de chave primaria da tabela
	 @GeneratedValue // Gera a chave como auto incremente da chave estrangeira
	 @Column(name = "id_pessoa") // Anotação que indetifica no nome do campo da tabela no banco de dados
	 private Integer codigo; // Atributo utilizado para manipular o objeto para armazenar no banco de dados

	 @Column(name = "nm_pessoa") // Anotação que indetifica no nome do campo da tabela no banco de dados
	 private String  nome; // Atributo utilizado para manipular o objeto para armazenar no banco de dados

	 @Column(name = "fl_sexo") // Anotação que indetifica no nome do campo da tabela no banco de dados
	 private String  sexo; // Atributo utilizado para manipular o objeto para armazenar no banco de dados

	 @Column(name = "dt_cadastro") // Anotação que indetifica no nome do campo da tabela no banco de dados
	 private LocalDateTime dataCadastro; // Atributo utilizado para manipular o objeto para armazenar no banco de dados

	 @Column(name = "ds_email") // Anotação que indetifica no nome do campo da tabela no banco de dados
	 private String  email; // Atributo utilizado para manipular o objeto para armazenar no banco de dados

	 @Column(name = "ds_endereco") // Anotação que indetifica no nome do campo da tabela no banco de dados
	 private String  endereco; // Atributo utilizado para manipular o objeto para armazenar no banco de dados

	 @Column(name = "fl_origemCadastro") // Anotação que indetifica no nome do campo da tabela no banco de dados
	 private String  origemCadastro; // Atributo utilizado para manipular o objeto para armazenar no banco de dados

	 @OneToOne // Anotação de relação de Um pra Um
	 @JoinColumn(name="id_usuario_cadastro") // Anotação que indetifica no nome do campo da tabela no banco de dados
	 private UsuarioEntity usuarioEntity; // Atributo utilizado para manipular o objeto para armazenar no banco de dados, Objeto do tipo Usuário

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
	  * @return retorna o valor da informação armazenado no objeto do Tipo UsuarioEntity
	  */
	 public UsuarioEntity getUsuarioEntity() {
	  return usuarioEntity;
	 }
	 /**
	  * Armazena valor passado por parâmetro no atributo da classe
	  * @param usuarioEntity
	  */
	 public void setUsuarioEntity(UsuarioEntity usuarioEntity) {
	  this.usuarioEntity = usuarioEntity;
	 }

}