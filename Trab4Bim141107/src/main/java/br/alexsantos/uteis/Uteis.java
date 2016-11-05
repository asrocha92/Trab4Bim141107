package br.alexsantos.uteis;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

/**
 * Cria��o de m�todos que poderam ser utilizados por todo o sitema
 * @author Alex Santos Rocha
 * @data 02/11/2016 �s 18:51
 *
 */
public class Uteis {

	/**
	 * Retorna uma conex�o que for requisitada para entity configurada com JPA.
	 * @return entiTyManager
	 */
	public static EntityManager JpaEntityManager() {
		FacesContext facesContext = FacesContext.getCurrentInstance();// isntancia um configurador de regra de negocionas
		ExternalContext externalContext = facesContext.getExternalContext();// verifica se o contexto da regra de neg�cio est� correta
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();// instanciada para recer varias solita��es simult�nias.
		return (EntityManager) request.getAttribute("entityManager"); // retorna um EntityManager
	}

	/**
	 * Mensagem tratada para informar um Alerta
	 * @param mensagem
	 */
	public static void Mensagem(String mensagem) {
		FacesContext facesContext = FacesContext.getCurrentInstance();//Inst�ncia facesContext para lan�ar mensagem no contexto da p�gina web
		facesContext.addMessage(null, new FacesMessage("Alerta", mensagem)); //escreve a mensagem e sob na tela
	}

	/**
	 * Mensagem tratada para quando precisa mostrar alguma Informa��o que possua Aten��o
	 * @param mensagem
	 */
	public static void MensagemAtencao(String mensagem) {
		FacesContext facesContext = FacesContext.getCurrentInstance();//Inst�ncia facesContext para lan�ar mensagem no contexto da p�gina web
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aten��o:", mensagem)); //escreve a mensagem e sob na tela
	}

	/**
	 * Mensagem quando � necess�rio retorna alguma informa��o avulsa ou de erro
	 * @param mensagem
	 */
	public static void MensagemInfo(String mensagem) {
		FacesContext facesContext = FacesContext.getCurrentInstance();//Inst�ncia facesContext para lan�ar mensagem no contexto da p�gina web
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", mensagem)); //escreve a mensagem e sob na tela
	}

}