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
 * @data 02/11/206 às 18:19.
 * Classe de filtro chamada toda vez que for requisitada uma transação para Faces Servlet.
 */
@WebFilter(servletNames ={ "Faces Servlet" }) //Definido o WebFilter, com o nome Faces Servlet
public class JPAFilter implements Filter {

	private EntityManagerFactory entityManagerFactory; //variavél que recebe valores de varias entidades para poder estabeler troca de informação com o banco

	private String persistence_unit_name = "unit_app"; //variavél que recebe o nome unidade de persistêcia

	/**
	 * Serve para fechar a conexão da entidade que está na variavél
	 * @see destroy()
	 */
	public void destroy() {
		this.entityManagerFactory.close();
	}

	/**
	 * Método por parâmetros de informação do protocolo Http.
	 * @see doFilter(ServletRequest, ServletResponse, FilterChain)
	 * @throws retorna IOException, ServlerException
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		//Cria uma uma EntityManager e passa para a variavel do método
		EntityManager entityManager =  this.entityManagerFactory.createEntityManager();

		//Adicionando a variavél do método para fazer parte da requisção
		request.setAttribute("entityManager", entityManager);

		//Inicia a transação requisitada
		entityManager.getTransaction().begin();

		//Nesse momento incia "FACES SERVLET"
		chain.doFilter(request, response);

		try {

			//realiza um commit na base dados através dessa trasação
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			//Se ocorrer qual erro na trasação da um rollback na operação realizada no banco de dados
			entityManager.getTransaction().rollback();
		}
		finally{

			//Depois de realizar qualquer operação dentro do try cath ele entra nesse bloco finally para fechar a conexão da entidade.
			entityManager.close();
		}

	}

	/**
	 * Cria EntityManagerFactory atravé do arquivo de persistência com o banco de dados
	 * @see Filter#init(FilterConfig)
	 * @throws retorna ServletException
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		//Cria  entityManagerFactory com os parâmetros no arquivo persistence.xml
		this.entityManagerFactory = Persistence.createEntityManagerFactory(this.persistence_unit_name);
	}

}
