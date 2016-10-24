package br.com.cortefacil.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("InteiroValidator")
public class InteiroValidator implements Validator {
	
	@Override
	public void validate(FacesContext context, UIComponent componente, Object value) throws ValidatorException{
		Integer inteiro = ((Integer) value).intValue();
		
		if(inteiro < 0){
			context = FacesContext.getCurrentInstance();	
			String mensagem;
			
			mensagem = context.getApplication().evaluateExpressionGet(context, "Valor inválido", String.class);
			
			FacesMessage auxMensagem = new FacesMessage(mensagem);
			auxMensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(auxMensagem);
		}
	}
}
