package br.alexsantos.filters;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.alexsantos.model.UsuarioModel;

/**
 * Implementa a interface Filter
 * Faz aultentifica��o pelo protocolo Http.
 * @author Alex Santos Rocha
 * @Data 02/11/2016 �s 21:34.
 */

@WebFilter("/sistema/*")
public class AutenticacaoFilter implements Filter {

	/**
	 * M�todo da interface Filter
	 * @see destroy()
	 */
	public void destroy() {
		// M�todo vazio
	}

	/**
	 * M�todo para realiar os filtros, atrav�s do protocolo Http
	 * @see doFilter(request,response,chain)
	 * @throws retorna IOException, ServlerException
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpSession httpSession 				= ((HttpServletRequest) request).getSession(); //inicia a sess�o
		HttpServletRequest httpServletRequest   = (HttpServletRequest) request; // atribui o valor do par�metro resquest
		HttpServletResponse httpServletResponse = (HttpServletResponse) response; // atribui o valor do par�metro response

		if(httpServletRequest.getRequestURI().indexOf("index.xhtml") <= -1){
			UsuarioModel usuarioModel =(UsuarioModel) httpSession.getAttribute("usuarioAutenticado"); // atribuido o atributo presente na sess�o
			if(usuarioModel == null){
				httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+ "/index.xhtml"); // se o valor usuarioModel n�o for nulo redireciona para p�gina index.xhtml
			} else{
				chain.doFilter(request, response); // somente faz um request na p�gina
			}
		}else{
			chain.doFilter(request, response); // somente faz um request na p�gina
		}
	}

	/**
	 * M�todo da interface Filter
	 * @see init(fConfig)
	 * @throws retorna ServletException
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		//m�todo vazio
	}

}