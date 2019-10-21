package br.com.contmatic.telefone;

import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;

import br.com.contmatic.groups.Post;
import br.com.contmatic.groups.Put;

/**
 * The Class Telefone.
 *
 * @author geovane.santoss
 */
/**
 * The Class Telefone.
 */
public class Telefone {

	/** The ddd. */
	@NotNull(message = "O ddd do telefone não pode ser vazio.", groups = { Put.class, Post.class })
	private TelefoneDDD ddd;

	/** The numero. */
	@NotBlank(message = "O número do telefone não pode ser vazio.", groups = { Put.class, Post.class })
	@Pattern(regexp = "9\\d+", message = "O número do telefone está invalido.", groups = { Put.class, Post.class })
	@Size(min = 9, max = 9, message = "O número do telefone tem que ter 9 numeros", groups = { Put.class, Post.class })
	private String numero;

	/**
	 * Gets the ddd.
	 *
	 * @return the ddd
	 */
	public TelefoneDDD getDdd() {
		return ddd;
	}

	/**
	 * Sets the ddd.
	 *
	 * @param ddd the new ddd
	 */
	public void setDdd(TelefoneDDD ddd) {
		this.ddd = ddd;
	}

	/**
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Sets the numero.
	 *
	 * @param numero the new numero
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.ddd).append(this.numero).hashCode();
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		Telefone telefone = (Telefone) obj;
		return new EqualsBuilder().append(this.ddd, telefone.getDdd()).append(this.numero, telefone.getNumero())
				.isEquals();
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, JSON_STYLE);
	}
}
