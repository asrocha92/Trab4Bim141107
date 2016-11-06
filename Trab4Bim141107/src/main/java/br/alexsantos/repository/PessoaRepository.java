package br.alexsantos.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

import javax.persistence.Query;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.alexsantos.entity.PessoaEntity;
import br.alexsantos.entity.UsuarioEntity;
import br.alexsantos.model.PessoaModel;
import br.alexsantos.model.UsuarioModel;
import br.alexsantos.uteis.Uteis;

/**
 * Classe reposav�l por persistir o objeto da classe PessoaEntity.
 * @author Alex Santos Rocha
 * @Data 05/11/2016 �s 10:36
 */
public class PessoaRepository {

	@Inject // Anoota��o para passar o objeto no contexto de um REST
	PessoaEntity pessoaEntity; // objeto conteiner

	EntityManager entityManager; // objeto de manipu��o com o banco de dados

	/***
	 * M�todo responsav�l por salvar o obejto passado do tipo pessoaModel
	 * @param pessoaModel
	 */
	public void SalvarNovoRegistro(PessoaModel pessoaModel){

		entityManager =  Uteis.JpaEntityManager(); // objeto de persitencia com cone��o com banco

		pessoaEntity = new PessoaEntity(); // obejto PessoaEntity que trabalha com JPA
		pessoaEntity.setDataCadastro(LocalDateTime.now()); // passa o obejto do tipo data para dataCadastro
		pessoaEntity.setEmail(pessoaModel.getEmail()); // passa o obejto do tipo string para email
		pessoaEntity.setEndereco(pessoaModel.getEndereco()); // passa o obejto do tipo string para endere�o
		pessoaEntity.setNome(pessoaModel.getNome()); // passa o obejto do tipo string para nome
		pessoaEntity.setOrigemCadastro(pessoaModel.getOrigemCadastro()); // passa o obejto do tipo string para origemCadastro
		pessoaEntity.setSexo(pessoaModel.getSexo()); // passa o obejto do tipo string para sexo

		UsuarioEntity usuarioEntity = entityManager.find(UsuarioEntity.class, pessoaModel.getUsuarioModel().getCodigo()); // deixa o objeto serializado armazenado na memoria

		pessoaEntity.setUsuarioEntity(usuarioEntity); // seta o objeto usuarioEntity

		entityManager.persist(pessoaEntity); // persite com o banco de dados o objeto

	}


	/***
	 * M�todo para cosultar pessoa
	 * @return pessoasModel.
	 * Retorna uma lista de pessoa.
	 */
	public List<PessoaModel> GetPessoas(){

		List<PessoaModel> listaPessoasModel = new ArrayList<PessoaModel>(); //Inst�ncia um list do tipo pessoaModel

		entityManager =  Uteis.JpaEntityManager(); //Inicia a persist�ncia com o banco usando JPA

		Query query = entityManager.createNamedQuery("PessoaEntity.findAll"); // Usa a inst�ncia do JPA, e trabalha persist�ncia que esta contida na classe PessoaEntity, assim passando o valor para query da consulta

		Collection<PessoaEntity> pessoasEntity = (Collection<PessoaEntity>)query.getResultList(); //realiza um cast da consulta da query, passando para Collection.

		PessoaModel pessoaModel = null; // Somenete Inst�ncia a classe PessoaModel e atribui valor nulo para mesma

		//percorre a Collection com foreach, e atribui o valor um objeto do tipo pessoaEntity
		for (PessoaEntity pessoaEntity : pessoasEntity) {

			pessoaModel = new PessoaModel(); // A cada intera��o do foreach cria um novo objeto de PessoalModel
			pessoaModel.setCodigo(pessoaEntity.getCodigo()); //atribui valor que est� armazenado no pessoaEntity e setando o valor do objeto pessoalModel conforme a l�gica apresentada
			pessoaModel.setDataCadastro(pessoaEntity.getDataCadastro()); //atribui valor que est� armazenado no pessoaEntity e setando o valor do objeto pessoalModel conforme a l�gica apresentada
			pessoaModel.setEmail(pessoaEntity.getEmail()); //atribui valor que est� armazenado no pessoaEntity e setando o valor do objeto pessoalModel conforme a l�gica apresentada
			pessoaModel.setEndereco(pessoaEntity.getEndereco()); //atribui valor que est� armazenado no pessoaEntity e setando o valor do objeto pessoalModel conforme a l�gica apresentada
			pessoaModel.setNome(pessoaEntity.getNome()); //atribui valor que est� armazenado no pessoaEntity e setando o valor do objeto pessoalModel conforme a l�gica apresentada

			// Verifica se a Origem do Cadastro � igual a X(XML), se n�o form passa a ser I(INPUT)
			if(pessoaEntity.getOrigemCadastro().equals("X")){
				pessoaModel.setOrigemCadastro("XML"); //atribui valor que est� armazenado no pessoaEntity e setando o valor do objeto pessoalModel conforme a l�gica apresentada
			}else{
				pessoaModel.setOrigemCadastro("INPUT"); //atribui valor que est� armazenado no pessoaEntity e setando o valor do objeto pessoalModel conforme a l�gica apresentada
			}
			//verifica se SEXO � igual a M(masculino), se n�o for � F(Feminino)
			if(pessoaEntity.getSexo().equals("M")){
				pessoaModel.setSexo("Masculino");
			}else{
				pessoaModel.setSexo("Feminino");
			}
			UsuarioEntity usuarioEntity =  pessoaEntity.getUsuarioEntity(); // Retorna o usu�rio que est� realizando a requi��o

			UsuarioModel usuarioModel = new UsuarioModel(); // Inicia a classe UsuarioModel
			usuarioModel.setUsuario(usuarioEntity.getUsuario()); // E passa somente o valor do usu�rio que est� realizando a requis�o usu�rioModel

			pessoaModel.setUsuarioModel(usuarioModel); // E passa o objeto usu�rioEntity para o usu�rioModel

			listaPessoasModel.add(pessoaModel); // nesse momento � adicionado o valor do objeto  do tipo pessoaModel na lista
		}
		return listaPessoasModel; //retorna a lista mesmo existindo ou n�o pessoas armazenadas.
	}


	/***
	 * M�todo para consultar somente uma pessoa e a retornar pelo seu c�digo.
	 * @param codigo
	 * @return Pessoa que esteja cadastrada no sistema
	 */
	private PessoaEntity GetPessoa(int codigo){

		entityManager =  Uteis.JpaEntityManager(); // Inicia um conex�o e persist�ncia JPA, para realizar oper�o com o banco de dados

		return entityManager.find(PessoaEntity.class, codigo); //retorna uma pessoa armazenada no banco de dados pelo c�digo de pessoa passado por par�metro
	}

	/***
	 * M�todo para alterar registro de pessoa no banco de dados.
	 * @param pessoaModel
	 */
	public void AlterarRegistro(PessoaModel pessoaModel){

		entityManager =  Uteis.JpaEntityManager(); // Inicia um conex�o e persist�ncia JPA

		PessoaEntity pessoaEntity = this.GetPessoa(pessoaModel.getCodigo()); // Busca pessoa pelo c�digo e a atribui para a variav�l do tipo pessoa

		pessoaEntity.setEmail(pessoaModel.getEmail()); // seta o valor alterado para email da pessoa
		pessoaEntity.setEndereco(pessoaModel.getEndereco()); // seta o valor alterado para endere�o da pessoa
		pessoaEntity.setNome(pessoaModel.getNome()); // seta o valor alterado para nome da pessoa
		pessoaEntity.setSexo(pessoaModel.getSexo()); // seta o valor alterado para sexo da pessoa

		entityManager.merge(pessoaEntity); // faz um merge, essa persit�ncia � do JPA, ela funciona para salvar a altera��o feita no banco de dados.
	}


	/***
	 * M�todo para excluir registro da pessoa armazenado no banco de dados, pelo c�digo da pessoa
	 * @param codigo
	 */
	public void ExcluirRegistro(int codigo){

		entityManager =  Uteis.JpaEntityManager();	// Inicia um conex�o e persist�ncia JPA

		PessoaEntity pessoaEntity = this.GetPessoa(codigo); //recupera todos os dados da pessoa que possui aquele c�digo

		entityManager.remove(pessoaEntity); // o m�todo remove, m�todo de persist�ncia que o JPA, usa para remover registro do banco de dados
	}

	/***
	 * Retorna os tipos de pessoa agrupados da origem do cadastro
	 * @return retorna uma HashTable de pessoa
	 */
	public Hashtable<String, Integer> GetOrigemPessoa(){

		Hashtable<String, Integer> hashtableRegistros = new Hashtable<String,Integer>(); //Inicia uma tabela hash para armazenar informa��es de pessoas cadastradas no sistema

		entityManager =  Uteis.JpaEntityManager(); // Inicia um conex�o e persist�ncia JPA

		Query query = entityManager.createNamedQuery("PessoaEntity.GroupByOrigemCadastro"); // Usa a inst�ncia do JPA, cria uma grupo onde s�o armazenada informa��o das pessoas cadsastradas

		Collection<Object[]> collectionRegistros  = (Collection<Object[]>)query.getResultList(); //realiza um cast da consulta da query, passando para Collection.

		// usa o foreach para percorre a tabela de pessoa
		for (Object[] objects : collectionRegistros) {

			String tipoPessoa 		= (String)objects[0]; //atribui o objeto da primeira posi��o do hash, para a variav�l tipoPessoa
			int	   totalDeRegistros = ((Number)objects[1]).intValue(); // atribui osvalor da segunda posi��o do hash

			//verifica se a origem do cadastro e por xml, se n�o port INPUT
			if(tipoPessoa.equals("X")){
				tipoPessoa = "XML"; // atribui o valor XML
			}else{
				tipoPessoa = "INPUT"; // atribui o valor INPUT
			}
			hashtableRegistros.put(tipoPessoa, totalDeRegistros); // adiciona o objeto tipoPessoa para chave, e totalDeRegistros para o valor daquela posi��o
		}
		return hashtableRegistros; // retorna a lista Hash com os valores armazenados
	}

}