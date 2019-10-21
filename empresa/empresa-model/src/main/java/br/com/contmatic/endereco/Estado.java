package br.com.contmatic.endereco;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * The Enum Estado.
 *
 * @author geovane.santos
 */

/**
 * The Enum Estado.
 */
public enum Estado {

	/** The ac. */
	AC("Acre"),

	/** The al. */
	AL("Alagoas"),

	/** The ap. */
	AP("Amapá"),

	/** The am. */
	AM("Amazonas"),

	/** The ba. */
	BA("Bahia"),

	/** The ce. */
	CE("Ceará"),

	/** The df. */
	DF("Distrito Federal"),

	/** The es. */
	ES("Espírito Santo"),

	/** The go. */
	GO("Goiás"),

	/** The ma. */
	MA("Maranhão"),

	/** The mt. */
	MT("Mato Grosso"),

	/** The ms. */
	MS("Mato Grosso do Sul"),

	/** The mg. */
	MG("Minas Gerais"),

	/** The pa. */
	PA("Pará"),

	/** The pb. */
	PB("Paraíba"),

	/** The pr. */
	PR("Paraná"),

	/** The pe. */
	PE("Pernambuco"),

	/** The pi. */
	PI("Piauí"),

	/** The rj. */
	RJ("Rio de Janeiro"),

	/** The rn. */
	RN("Rio Grande do Norte"),

	/** The rs. */
	RS("Rio Grande do Sul"),

	/** The ro. */
	RO("Rondônia"),

	/** The rr. */
	RR("Roraima"),

	/** The sc. */
	SC("Santa Catarina"),

	/** The sp. */
	SP("São Paulo"),

	/** The se. */
	SE("Sergipe"),

	/** The to. */
	TO("Tocantins");

	/** The nome estado. */
	private String nomeEstado;

	/**
	 * Instantiates a new estado.
	 *
	 * @param nomeEstado the nome estado
	 */
	private Estado(String nomeEstado) {
		this.nomeEstado = nomeEstado;
	}

	/**
	 * Gets the nome estado.
	 *
	 * @return the nome estado
	 */
	public String getNomeEstado() {
		return nomeEstado;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}
