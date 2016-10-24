package br.com.cortefacil.util;

public final class FormatarUtil {
	public static String formatarTelefone(String telefone) {
		return telefone.replaceAll("(\\d{2})(\\d{4})(\\d{4})", "($1) $2-$3");
	}
	public static String formatarCep(String cep) {
		return cep.replaceAll("(\\d{5})(\\d{3})", "$1-$2");
	}
/*	public static String formatarData(Date data) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dataFormatada = sdf.format(data);
		return dataFormatada; 
	}*/
}
