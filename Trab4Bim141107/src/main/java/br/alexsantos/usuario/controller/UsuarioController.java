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
 * Classe de controla injeção de contextos de conteiners de beans
 *
 * @author Alex Santos Rocha
 * @Data 02/11/2016 às 20:42.
 */

@Named(value = "usuarioController") // Estamos dizendo que pertence o CDI, quer dizer que a classe é um beans gerenciavél
@SessionScoped // Essa anotação informa que o escopo de nosso bean gerenciado é de sessão
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = 1L;// versão da classe

	@Inject // annotation para realizar injeção de um beans
	private UsuarioModel usuarioModel; // instância UsarioModel

	@Inject // annotation para realizar injeção de um beans
	private UsuarioRepository usuarioRepository; // Instância UsuarioRpository

	@Inject // annotation para realizar injeção de um beans
	private UsuarioEntity usuarioEntity; // Instância UsuarioFactory

	/**
	 * retorna o objeto do tipo usuarioModel
	 * @return usuarioModel
	 */
	public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}

	/**
	 * passa o valor do objeto passado por parâmetro para variavel de classe
	 * @param usuarioModel
	 */
	public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}

	/**
	 * Retorna o valor do usuário na autentificação
	 * @return UsuarioModel
	 */
	public UsuarioModel GetUsuarioSession() {
		FacesContext facesContext = FacesContext.getCurrentInstance(); // Busca a isntâcia do FacesContext
		return (UsuarioModel) facesContext.getExternalContext().getSessionMap().get("usuarioAutenticado"); // nessa linha faz autentificação da entidade e retorna
	}

	/**
	 * Retorna o pagina index.xhtml se o usuário se a sessão for valida
	 * @return index.xhtml
	 */
	public String Logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();// Valida sessão
		return "/index.xhtml?faces-redirect=true"; // retorna a página index.xhtml se a validação da sessão for igual true
	}

	/**
	 * Método que autentifica se o usuátio é valido
	 * @return deve retorna a página do sistema, se passar pelos pres requisitos
	 *         do método, se for falso retorna null
	 */
	public String EfetuarLogin() {
		if (StringUtils.isEmpty(usuarioModel.getUsuario()) || StringUtils.isBlank(usuarioModel.getUsuario())) {
			Uteis.Mensagem("Favor informar o login!"); // chama o método que mostra a mensagem informada
			return null;
		} else if (StringUtils.isEmpty(usuarioModel.getSenha()) || StringUtils.isBlank(usuarioModel.getSenha())) {
			Uteis.Mensagem("Favor informara senha!"); // chama o método que mostra a mensagem informada
			return null;
		} else {
			usuarioEntity = usuarioRepository.ValidaUsuario(usuarioModel); // se o usuário for valido retorna dados do usuário
			if (usuarioEntity != null) {
				usuarioModel.setSenha(null); //seta o valor do senha null, para poder realizar uma valição futura
				usuarioModel.setCodigo(usuarioEntity.getCodigo());  //deixa o valor do código armazenado, para uma validação se tiver algum time de tempo no sistema
				FacesContext facesContext = FacesContext.getCurrentInstance(); // busca a instancia do sistema que o usuário está usando e retorna
				facesContext.getExternalContext().getSessionMap().put("usuarioAutenticado", usuarioModel); //deixa mapeado o contexto do cliente
				return "sistema/home?faces-redirect=true"; // retorna a página prinipal do sistema
			} else {
				Uteis.Mensagem("Não foi possível efetuar o login com esse usuário e senha!"); // retoena menssagem caso o usuário não exista ou senha incorreta
				return null;
			}
		}

	}

}