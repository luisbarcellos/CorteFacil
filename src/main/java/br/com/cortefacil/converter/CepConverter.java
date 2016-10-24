package br.com.cortefacil.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.cortefacil.util.FormatarUtil;

/**
 * <b>Converter para cep.</b>
 *
 * 
 */
@FacesConverter("CepConverter")
public class CepConverter implements Converter {

	/**
	 * <b>Método que remove a mascara do cep.</b>
	 *
	 * @param facesContext
	 * @param uIcomponent
	 * @param cep
	 * @return Object
	 */
	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uIcomponent, String cep) {
		if (cep != null) {
			cep = cep.replace("-", "");
			return cep;
		}
		return cep;
	}
	
	/**
	 * <b>Método que retorna a String do cep.</b>
	 *
	 * @param facesContext
	 * @param uIcomponent
	 * @param object
	 * @return String
	 */
	@Override
	public String getAsString(FacesContext facesContext, UIComponent uIcomponent, Object value) {
		String cep = (String) value;
		return cep != null ? FormatarUtil.formatarCep(cep) : cep;
	}
}