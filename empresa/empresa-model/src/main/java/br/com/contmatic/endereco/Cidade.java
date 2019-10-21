package br.com.contmatic.endereco;

import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import br.com.contmatic.groups.Post;
import br.com.contmatic.groups.Put;

/**
 * The Class Cidade.
 */
public class Cidade {

	/** The nome. */
	@NotBlank(message = "Nome da cidade não pode ser vazio.", groups = { Put.class, Post.class })
	@Length(max = 500, message = "Nome da cidade não deve ser maior que {max}", groups = { Put.class, Post.class })
	private String nome;

	/** The estado. */
	@NotNull(message = "Nome do estado não pode ser nulo.", groups = { Put.class, Post.class })
	private Estado estado;

	/** The bairro. */
	@NotNull(message = "O Bairro da cidade não pode está vazio", groups = { Put.class, Post.class })
	@Size.List({ @Size(min = 1, message = "O bairro da cidade não deve ser vazio", groups = { Put.class, Post.class }),
			@Size(max = 500, message = "O bairro limite da bairro da cidade é de {max}", groups = { Put.class,
					Post.class }) })
	private List<Bairro> bairro;

	/**
	 * Gets the bairro.
	 *
	 * @return the bairro
	 */
	public List<Bairro> getBairro() {
		return bairro;
	}

	/**
	 * Sets the bairro.
	 *
	 * @param bairro the new bairro
	 */
	public void setBairro(List<Bairro> bairro) {
		this.bairro = bairro;
	}

	/**
	 * Gets the nome.
	 *
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Sets the nome.
	 *
	 * @param nome the new nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Gets the estado.
	 *
	 * @return the estado
	 */
	public Estado getEstado() {
		return estado;
	}

	/**
	 * Sets the estado.
	 *
	 * @param estado the new estado
	 */
	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.nome).append(this.estado).hashCode();
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
		Cidade cidade = (Cidade) obj;
		return new EqualsBuilder().append(this.nome, cidade.getNome()).append(this.estado, cidade.getEstado())
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
