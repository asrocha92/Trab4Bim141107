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
 * Faz aultentificação pelo protocolo Http.
 * @author Alex Santos Rocha
 * @Data 02/11/2016 às 21:34.
 */

@WebFilter("/sistema/*")
public class AutenticacaoFilter implements Filter {

	/**
	 * Método da interface Filter
	 * @see destroy()
	 */
	public void destroy() {
		// Método vazio
	}

	/**
	 * Método para realiar os filtros, através do protocolo Http
	 * @see doFilter(request,response,chain)
	 * @throws retorna IOException, ServlerException
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpSession httpSession 				= ((HttpServletRequest) request).getSession(); //inicia a sessão
		HttpServletRequest httpServletRequest   = (HttpServletRequest) request; // atribui o valor do parâmetro resquest
		HttpServletResponse httpServletResponse = (HttpServletResponse) response; // atribui o valor do parâmetro response

		if(httpServletRequest.getRequestURI().indexOf("index.xhtml") <= -1){
			UsuarioModel usuarioModel =(UsuarioModel) httpSession.getAttribute("usuarioAutenticado"); // atribuido o atributo presente na sessão
			if(usuarioModel == null){
				httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+ "/index.xhtml"); // se o valor usuarioModel não for nulo redireciona para página index.xhtml
			} else{
				chain.doFilter(request, response); // somente faz um request na página
			}
		}else{
			chain.doFilter(request, response); // somente faz um request na página
		}
	}

	/**
	 * Método da interface Filter
	 * @see init(fConfig)
	 * @throws retorna ServletException
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		//método vazio
	}

}