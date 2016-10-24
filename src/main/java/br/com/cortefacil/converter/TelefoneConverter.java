package br.com.cortefacil.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.cortefacil.util.FormatarUtil;

/**
 * <b>Converter para telefone.</b>
 *
 * 
 */
@FacesConverter("TelefoneConverter")
public class TelefoneConverter implements Converter {

	/**
	 * <b>Método que remove a mascara do telefone.</b>
	 *
	 * @param facesContext
	 * @param uIcomponent
	 * @param telefone
	 * @return Object
	 */
	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uIcomponent, String telefone) {
		if (telefone != null) {
			telefone = telefone.replace("-", "");
			telefone = telefone.replace("(", "");
			telefone = telefone.replace(")", "");
			telefone = telefone.replace(" ", "");
			return telefone;
		}
		return telefone;
	}
	/*
	 * Método Converter antes de criar os relatórios public Object
	 * getAsObject(FacesContext facesContext, UIComponent uIcomponent, String
	 * telefone) { if (telefone.trim().equals("")) { return null; } else {
	 * telefone = telefone.replace("-", ""); telefone = telefone.replace("(",
	 * ""); telefone = telefone.replace(")", ""); telefone = telefone.replace(
	 * " ", ""); return telefone; } }
	 */

	/**
	 * <b>Método que retorna a String do telefone.</b>
	 *
	 * @param facesContext
	 * @param uIcomponent
	 * @param object
	 * @return String
	 */
	@Override
	public String getAsString(FacesContext facesContext, UIComponent uIcomponent, Object value) {
		String telefone = (String) value;
		return telefone != null ? FormatarUtil.formatarTelefone(telefone) : telefone;
	}
}