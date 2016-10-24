package br.com.cortefacil.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.validation.ValidationException;

@FacesValidator("CepValidator")
public class CepValidator implements Validator {

	private static final String CEP_PATTERN = "\\d{5}[-]\\d{2}";

	private Pattern pattern;
	private Matcher matcher;

	public CepValidator() {
		pattern = Pattern.compile(CEP_PATTERN);
	}

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidationException {
		// value = value == null? "" : value;
		if (value == null) {
			value = "";
		} else {
			matcher = pattern.matcher(value.toString());
			if (!matcher.matches()) {
				context = FacesContext.getCurrentInstance();
				String mensagem;

				mensagem = context.getApplication().evaluateExpressionGet(context,
						"Cep inválido!", String.class);

				FacesMessage auxMensagem = new FacesMessage(mensagem);
				auxMensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(auxMensagem);
			}
		}
	}
}