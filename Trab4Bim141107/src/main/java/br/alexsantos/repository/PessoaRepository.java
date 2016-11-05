package br.alexsantos.repository;
import java.time.LocalDateTime;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.alexsantos.entity.PessoaEntity;
import br.alexsantos.entity.UsuarioEntity;
import br.alexsantos.model.PessoaModel;
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
}