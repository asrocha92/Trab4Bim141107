package br.alexsantos.filters;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * @author Alex Santos Rocha
 * @data 02/11/206 �s 18:19.
 * Classe de filtro chamada toda vez que for requisitada uma transa��o para Faces Servlet.
 */
@WebFilter(servletNames ={ "Faces Servlet" }) //Definido o WebFilter, com o nome Faces Servlet
public class JPAFilter implements Filter {

	private EntityManagerFactory entityManagerFactory; //variav�l que recebe valores de varias entidades para poder estabeler troca de informa��o com o banco

	private String persistence_unit_name = "unit_app"; //variav�l que recebe o nome unidade de persist�cia

	/**
	 * Serve para fechar a conex�o da entidade que est� na variav�l
	 * @see destroy()
	 */
	public void destroy() {
		this.entityManagerFactory.close();
	}

	/**
	 * M�todo por par�metros de informa��o do protocolo Http.
	 * @see doFilter(ServletRequest, ServletResponse, FilterChain)
	 * @throws retorna IOException, ServlerException
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		//Cria uma uma EntityManager e passa para a variavel do m�todo
		EntityManager entityManager =  this.entityManagerFactory.createEntityManager();

		//Adicionando a variav�l do m�todo para fazer parte da requis��o
		request.setAttribute("entityManager", entityManager);

		//Inicia a transa��o requisitada
		entityManager.getTransaction().begin();

		//Nesse momento incia "FACES SERVLET"
		chain.doFilter(request, response);

		try {

			//realiza um commit na base dados atrav�s dessa trasa��o
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			//Se ocorrer qual erro na trasa��o da um rollback na opera��o realizada no banco de dados
			entityManager.getTransaction().rollback();
		}
		finally{

			//Depois de realizar qualquer opera��o dentro do try cath ele entra nesse bloco finally para fechar a conex�o da entidade.
			entityManager.close();
		}

	}

	/**
	 * Cria EntityManagerFactory atrav� do arquivo de persist�ncia com o banco de dados
	 * @see Filter#init(FilterConfig)
	 * @throws retorna ServletException
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		//Cria  entityManagerFactory com os par�metros no arquivo persistence.xml
		this.entityManagerFactory = Persistence.createEntityManagerFactory(this.persistence_unit_name);
	}

}
