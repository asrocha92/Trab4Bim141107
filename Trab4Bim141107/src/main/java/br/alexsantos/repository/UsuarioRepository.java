package br.alexsantos.repository;

import java.io.Serializable;

import javax.persistence.Query;

import br.alexsantos.entity.UsuarioEntity;
import br.alexsantos.model.UsuarioModel;
import br.alexsantos.uteis.Uteis;

/**
 * Classe que implemena o serializable para poder retornar um objeto do tipo usu�rio
 * @author Alex Santos Rocha
 * @data 02/11/2016 �s 19:32
 */

public class UsuarioRepository implements Serializable {

	private static final long serialVersionUID = 1L;//vers�o da classe

	/**
	 * Retorna um objeto do tipo UsuarioUnity se existir ap�s a consulta a base de dados
	 * @param usuarioModel
	 * @return UsuarioUnity ou return null
	 */
	public UsuarioEntity ValidaUsuario(UsuarioModel usuarioModel){
		try {
			Query query = Uteis.JpaEntityManager().createNamedQuery("UsuarioEntity.findUser");//Cria uma inst�ncia do nome definido na entity (UsuarioEntity.findUser)

			query.setParameter("usuario", usuarioModel.getUsuario()); //define o par�metro e passa o valor do usu�rio
			query.setParameter("senha", usuarioModel.getSenha()); //define o par�metro e passa o valor da senha

			return (UsuarioEntity)query.getSingleResult(); //Retorna usu�rio se for localizado se n�o retorna null
		} catch (Exception e) {
			return null;//Caso de uma erro de alguma forma retorna null
		}
	}
}