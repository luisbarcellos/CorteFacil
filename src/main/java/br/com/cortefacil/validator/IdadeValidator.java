package br.com.cortefacil.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("IdadeValidator")
public class IdadeValidator implements Validator {
	
	@Override
	public void validate(FacesContext context, UIComponent componente, Object value) throws ValidatorException{
		Integer idade = ((Integer) value).intValue();
		
		if(idade > 150 || idade < 1){
			context = FacesContext.getCurrentInstance();	
			String mensagem;
			
			mensagem = context.getApplication().evaluateExpressionGet(context, "#{msg['classeIdadeValidator.MensagemAlerta']}", String.class);
			
			FacesMessage auxMensagem = new FacesMessage(mensagem);
			auxMensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(auxMensagem);
		}
	}
}
