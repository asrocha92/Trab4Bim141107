package br.alexsantos.repository;

import java.io.Serializable;

import javax.persistence.Query;

import br.alexsantos.entity.UsuarioEntity;
import br.alexsantos.model.UsuarioModel;
import br.alexsantos.uteis.Uteis;

/**
 * Classe que implemena o serializable para poder retornar um objeto do tipo usuário
 * @author Alex Santos Rocha
 * @data 02/11/2016 às 19:32
 */

public class UsuarioRepository implements Serializable {

	private static final long serialVersionUID = 1L;//versão da classe

	/**
	 * Retorna um objeto do tipo UsuarioUnity se existir após a consulta a base de dados
	 * @param usuarioModel
	 * @return UsuarioUnity ou return null
	 */
	public UsuarioEntity ValidaUsuario(UsuarioModel usuarioModel){
		try {
			Query query = Uteis.JpaEntityManager().createNamedQuery("UsuarioEntity.findUser");//Cria uma instância do nome definido na entity (UsuarioEntity.findUser)

			query.setParameter("usuario", usuarioModel.getUsuario()); //define o parâmetro e passa o valor do usuário
			query.setParameter("senha", usuarioModel.getSenha()); //define o parâmetro e passa o valor da senha

			return (UsuarioEntity)query.getSingleResult(); //Retorna usuário se for localizado se não retorna null
		} catch (Exception e) {
			return null;//Caso de uma erro de alguma forma retorna null
		}
	}
}