package br.alexsantos.uteis;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;

/**
 * Classe utilizada para converter valores de datas que seram visualizada na views de pessoas cadastradas do sistema.
 * A classe herda valores da classe DaeTimerConverter
 * @author Alex Java
 * @Data 05/11/2016 às 20:02
 */
@FacesConverter(value= LocalDateTimeConverter.ID) //Anotação do CDI, invocada para converter data atravé de um ID
public class LocalDateTimeConverter extends DateTimeConverter {

	public static final String ID="br.alexsantos.uteis.LocalDateTimeConverter"; // Criada uma variavél estatica sem modificação com valor apontando para o caminho onde a classe LocalDateTimeConverter

	/**
	 * @param facesContext -> parâmetro de contexto do CDI que irá utilizae
	 * @param uiComponent  -> qual o componente do obejto que irá utilizar o arquivo
	 * @param value		   -> a data que será passada para conversão
	 * @return localDateTime
	 */
	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {

		Date date = null; // inicia uma variavél do tipo data igual a null
		LocalDateTime localDateTime = null; // inicia a variavél do tipo

		Object object = super.getAsObject(facesContext, uiComponent, value); // chama o método da super classe

		//verifica se o objeto é do tipo date
		if(object instanceof Date){

			date = (Date) object;//realiza um cast do objeto e atribui para a variavel date

			Instant instant = Instant.ofEpochMilli(date.getTime()); //converte para um número será sempre entre 0 e 999.999.999
			localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()); // converte para um objeto data e hora imutável que é visto como ano-mês-dia-hora-minuto-segundo
	  		return localDateTime; //retorna a data conevertida

		}
		else{

			throw new IllegalArgumentException(String.format("value=%s Não foi possível converter LocalDateTime, resultado super.getAsObject=%s",value,object));
		}


	}

	/**
	 * Método recupera um valor de data e converte em string, do objeto recupedos do sistema
	 * @param FacesContext
	 * @param uiComponent
	 * @param value
	 */
	  @Override
	  public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
		  if(value == null)
			  return super.getAsString(facesContext, uiComponent, value);

		  if(value instanceof LocalDateTime){

			  LocalDateTime localDateTime = (LocalDateTime) value;

			  Instant instant = localDateTime.toInstant(ZoneOffset.UTC);

			  Date  date =  Date.from(instant);

			  return super.getAsString(facesContext, uiComponent, date);
		  }
		  else{

			  throw new IllegalArgumentException(String.format("value=%s não é um LocalDateTime",value));
		  }

	  }
}