package br.alexsantos.usuario.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import br.alexsantos.entity.UsuarioEntity;
import br.alexsantos.model.UsuarioModel;
import br.alexsantos.repository.UsuarioRepository;
import br.alexsantos.uteis.Uteis;

/**
 * Classe de controla inje��o de contextos de conteiners de beans
 *
 * @author Alex Santos Rocha
 * @Data 02/11/2016 �s 20:42.
 */

@Named(value = "usuarioController") // Estamos dizendo que pertence o CDI, quer dizer que a classe � um beans gerenciav�l
@SessionScoped // Essa anota��o informa que o escopo de nosso bean gerenciado � de sess�o
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = 1L;// vers�o da classe

	@Inject // annotation para realizar inje��o de um beans
	private UsuarioModel usuarioModel; // inst�ncia UsarioModel

	@Inject // annotation para realizar inje��o de um beans
	private UsuarioRepository usuarioRepository; // Inst�ncia UsuarioRpository

	@Inject // annotation para realizar inje��o de um beans
	private UsuarioEntity usuarioEntity; // Inst�ncia UsuarioFactory

	/**
	 * retorna o objeto do tipo usuarioModel
	 * @return usuarioModel
	 */
	public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}

	/**
	 * passa o valor do objeto passado por par�metro para variavel de classe
	 * @param usuarioModel
	 */
	public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}

	/**
	 * Retorna o valor do usu�rio na autentifica��o
	 * @return UsuarioModel
	 */
	public UsuarioModel GetUsuarioSession() {
		FacesContext facesContext = FacesContext.getCurrentInstance(); // Busca a isnt�cia do FacesContext
		return (UsuarioModel) facesContext.getExternalContext().getSessionMap().get("usuarioAutenticado"); // nessa linha faz autentifica��o da entidade e retorna
	}

	/**
	 * Retorna o pagina index.xhtml se o usu�rio se a sess�o for valida
	 * @return index.xhtml
	 */
	public String Logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();// Valida sess�o
		return "/index.xhtml?faces-redirect=true"; // retorna a p�gina index.xhtml se a valida��o da sess�o for igual true
	}

	/**
	 * M�todo que autentifica se o usu�tio � valido
	 * @return deve retorna a p�gina do sistema, se passar pelos pres requisitos
	 *         do m�todo, se for falso retorna null
	 */
	public String EfetuarLogin() {
		if (StringUtils.isEmpty(usuarioModel.getUsuario()) || StringUtils.isBlank(usuarioModel.getUsuario())) {
			Uteis.Mensagem("Favor informar o login!"); // chama o m�todo que mostra a mensagem informada
			return null;
		} else if (StringUtils.isEmpty(usuarioModel.getSenha()) || StringUtils.isBlank(usuarioModel.getSenha())) {
			Uteis.Mensagem("Favor informara senha!"); // chama o m�todo que mostra a mensagem informada
			return null;
		} else {
			usuarioEntity = usuarioRepository.ValidaUsuario(usuarioModel); // se o usu�rio for valido retorna dados do usu�rio
			if (usuarioEntity != null) {
				usuarioModel.setSenha(null); //seta o valor do senha null, para poder realizar uma vali��o futura
				usuarioModel.setCodigo(usuarioEntity.getCodigo());  //deixa o valor do c�digo armazenado, para uma valida��o se tiver algum time de tempo no sistema
				FacesContext facesContext = FacesContext.getCurrentInstance(); // busca a instancia do sistema que o usu�rio est� usando e retorna
				facesContext.getExternalContext().getSessionMap().put("usuarioAutenticado", usuarioModel); //deixa mapeado o contexto do cliente
				return "sistema/home?faces-redirect=true"; // retorna a p�gina prinipal do sistema
			} else {
				Uteis.Mensagem("N�o foi poss�vel efetuar o login com esse usu�rio e senha!"); // retoena menssagem caso o usu�rio n�o exista ou senha incorreta
				return null;
			}
		}

	}

}