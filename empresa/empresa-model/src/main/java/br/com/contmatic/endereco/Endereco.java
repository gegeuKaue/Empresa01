package br.com.contmatic.endereco;

import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;

import br.com.contmatic.groups.Post;
import br.com.contmatic.groups.Put;
import br.com.contmatic.regex.RegexType;


/**
 * The Class Endereco.
 *
 * @author geovane.santos
 */

/**
 * The Class Endereco.
 */
public class Endereco {

	/** The cidade. */
	@Valid
	@NotNull(message = "Os valores da cidade não pode ser nulo", groups = { Put.class, Post.class })
	private Cidade cidade;

	/** The numero. */
	@Min(value = 1, message = "O número não deve ser negativo.", groups = { Put.class, Post.class })
	private int numero;

	/** The cep. */
	@NotBlank(message = "O CEP não pode está vázio.", groups = { Put.class, Post.class })
	@Pattern(regexp = RegexType.CEP, message = "Digite um cep valido", groups = { Put.class, Post.class })
	@Size(min = 8, max = 8, message = "O CEP deve ter 8 digitos.", groups = { Put.class, Post.class })
	private String cep;

	/**
	 * Gets the cidade.
	 *
	 * @return the cidade
	 */
	public Cidade getCidade() {
		return cidade;
	}

	/**
	 * Sets the cidade.
	 *
	 * @param cidade the new cidade
	 */
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	/**
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * Sets the numero.
	 *
	 * @param numero the new numero
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}

	/**
	 * Gets the cep.
	 *
	 * @return the cep
	 */
	public String getCep() {
		return cep;
	}

	/**
	 * Sets the cep.
	 *
	 * @param cep the new cep
	 */
	public void setCep(String cep) {
		this.cep = cep;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.cep).append(this.numero).toHashCode();
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
		Endereco endereco = (Endereco) obj;
		return new EqualsBuilder().append(this.cep, endereco.getCep()).append((int) this.numero, endereco.getNumero())
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
