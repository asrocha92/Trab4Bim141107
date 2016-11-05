package br.alexsantos.uteis;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

/**
 * Criação de métodos que poderam ser utilizados por todo o sitema
 * @author Alex Santos Rocha
 * @data 02/11/2016 às 18:51
 *
 */
public class Uteis {

	/**
	 * Retorna uma conexão que for requisitada para entity configurada com JPA.
	 * @return entiTyManager
	 */
	public static EntityManager JpaEntityManager() {
		FacesContext facesContext = FacesContext.getCurrentInstance();// isntancia um configurador de regra de negocionas
		ExternalContext externalContext = facesContext.getExternalContext();// verifica se o contexto da regra de negócio está correta
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();// instanciada para recer varias solitações simultânias.
		return (EntityManager) request.getAttribute("entityManager"); // retorna um EntityManager
	}

	/**
	 * Mensagem tratada para informar um Alerta
	 * @param mensagem
	 */
	public static void Mensagem(String mensagem) {
		FacesContext facesContext = FacesContext.getCurrentInstance();//Instância facesContext para lançar mensagem no contexto da página web
		facesContext.addMessage(null, new FacesMessage("Alerta", mensagem)); //escreve a mensagem e sob na tela
	}

	/**
	 * Mensagem tratada para quando precisa mostrar alguma Informação que possua Atenção
	 * @param mensagem
	 */
	public static void MensagemAtencao(String mensagem) {
		FacesContext facesContext = FacesContext.getCurrentInstance();//Instância facesContext para lançar mensagem no contexto da página web
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção:", mensagem)); //escreve a mensagem e sob na tela
	}

	/**
	 * Mensagem quando é necessário retorna alguma informação avulsa ou de erro
	 * @param mensagem
	 */
	public static void MensagemInfo(String mensagem) {
		FacesContext facesContext = FacesContext.getCurrentInstance();//Instância facesContext para lançar mensagem no contexto da página web
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", mensagem)); //escreve a mensagem e sob na tela
	}

}