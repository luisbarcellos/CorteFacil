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

@FacesValidator("EmailValidator")
public class EmailValidator implements Validator {

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\."
			+ "[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*" + "(\\.[A-Za-z]{2,})$";

	private Pattern pattern;
	private Matcher matcher;

	public EmailValidator() {
		pattern = Pattern.compile(EMAIL_PATTERN);
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
						"Email inválido!", String.class);

				FacesMessage auxMensagem = new FacesMessage(mensagem);
				auxMensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(auxMensagem);
			}
		}
	}
}