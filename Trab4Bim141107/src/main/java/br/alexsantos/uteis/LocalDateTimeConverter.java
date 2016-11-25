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
 * @Data 05/11/2016 �s 20:02
 */
@FacesConverter(value= LocalDateTimeConverter.ID) //Anota��o do CDI, invocada para converter data atrav� de um ID
public class LocalDateTimeConverter extends DateTimeConverter {

	public static final String ID="br.alexsantos.uteis.LocalDateTimeConverter"; // Criada uma variav�l estatica sem modifica��o com valor apontando para o caminho onde a classe LocalDateTimeConverter

	/**
	 * @param facesContext -> par�metro de contexto do CDI que ir� utilizae
	 * @param uiComponent  -> qual o componente do obejto que ir� utilizar o arquivo
	 * @param value		   -> a data que ser� passada para convers�o
	 * @return localDateTime
	 */
	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {

		Date date = null; // inicia uma variav�l do tipo data igual a null
		LocalDateTime localDateTime = null; // inicia a variav�l do tipo

		Object object = super.getAsObject(facesContext, uiComponent, value); // chama o m�todo da super classe

		//verifica se o objeto � do tipo date
		if(object instanceof Date){

			date = (Date) object;//realiza um cast do objeto e atribui para a variavel date

			Instant instant = Instant.ofEpochMilli(date.getTime()); //converte para um n�mero ser� sempre entre 0 e 999.999.999
			localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()); // converte para um objeto data e hora imut�vel que � visto como ano-m�s-dia-hora-minuto-segundo
	  		return localDateTime; //retorna a data conevertida

		}
		else{

			throw new IllegalArgumentException(String.format("value=%s N�o foi poss�vel converter LocalDateTime, resultado super.getAsObject=%s",value,object));
		}


	}

	/**
	 * M�todo recupera um valor de data e converte em string, do objeto recupedos do sistema
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

			  throw new IllegalArgumentException(String.format("value=%s n�o � um LocalDateTime",value));
		  }

	  }
}