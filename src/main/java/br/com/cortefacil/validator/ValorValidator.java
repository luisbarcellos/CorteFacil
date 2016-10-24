package br.com.cortefacil.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("ValorValidator")
public class ValorValidator implements Validator {
	
	@Override
	public void validate(FacesContext context, UIComponent componente, Object value) throws ValidatorException{
		Double valor = ((Double) value).doubleValue();
		
		if(valor < 0){
			context = FacesContext.getCurrentInstance();	
			String mensagem;
			
			mensagem = context.getApplication().evaluateExpressionGet(context, "Valor inválido", String.class);
			
			FacesMessage auxMensagem = new FacesMessage(mensagem);
			auxMensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(auxMensagem);
		}
	}
}
