package br.alexsantos.uteis;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Classe conversora de data, para porder persistir com JPA
 * @author Alex Java
 * @Data 05/11/2016 �s 11:53
 */
@Converter(autoApply = true) // Anota��o de covers�o automatico ao trabalhar com JPA, na aplica��o
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

	/**
	 * M�todo implementado da API do Java. Tramsforma o objeto na hora do JPA persistir com o banco de dados ao gravar.
	 * @param localDateTime converte o objeto se n�o for nulo e retorna o mesmo
	 * 		  caso sej� nulo somente retorna o objeto
	 */
    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime localDateTime) {
    	if(localDateTime != null)
    		return Timestamp.valueOf(localDateTime);

    	return null;

    }

    /**
     * M�todo implementado da API do Java. Tramsforma o objeto na hora do JPA persistir na hora que retorna dados do banco de dados para entidade de persistencia.
     * @param timestamp converte o objeto se n�o for nulo e retorna o mesmo
	 * 		  caso sej� nulo somente retorna o objeto
     */
    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp timestamp) {
    	if(timestamp != null)
    		return timestamp.toLocalDateTime();

    	return null;
    }
}