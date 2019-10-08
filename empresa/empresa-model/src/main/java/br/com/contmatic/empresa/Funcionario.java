package br.com.contmatic.empresa;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import br.com.caelum.stella.bean.validation.CPF;
import br.com.contmatic.regex.RegexType;
import br.com.contmatic.telefone.Telefone;

/**
 * The Class Funcionario.
 *
 * @author geovane.santos
 */
public class Funcionario {
	/** The nome. */
	@NotBlank(message = "O nome do funcionario não deve ser vázio.")
	@Length(max = 500, message = "O nome máximo de funcionario é de {max} caracteres")
	@Pattern(regexp = RegexType.NOME, message = "O nome do funcionário está incorreto")
	private String nome;

	/** The cargo. */
	@NotBlank(message = "O cargo do funcionario não deve ser vázio.")
	@Length(max = 500, message = "O cargo máximo de funcionario é de {max} caracteres")
	private String cargo;

	/** The idade. */
	@Min(value = 1, message = "A idade do funcionario não pode ser negativa.")
	private Integer idade;

	/** The entrada. */
	@NotNull(message = "O horario de entrada funcionario não pode ser nulo.")
	private LocalTime entrada;

	/** The saida. */
	@NotNull(message = "O horario de saida funcionario não pode ser nulo.")
	private LocalTime saida;

	@NotNull(message = "A data de contratação do funcionario não deve ser nula")
	@Past(message = "A data de contratação do funcionario não deve ser maior que a atual")
	private LocalDate dataContratacao;

	/** The cpf. */
	@CPF(message = "O CPF do funcionario está inválido")
	@NotBlank(message = "O cep do funcionario não pode ser nulo.")
	private String cpf;

	/** The telefone. */
	@Valid
	@NotNull(message = "O telefone do funcionario não pode ser nulo")
	@Size.List({ @Size(min = 1, message = "A lista de endereço do funcionario não deve ser vazio."),
			@Size(max = 500, message = "A lista de endereço do funcionario máxima é de {max}.") })
	private Set<Telefone> telefones;

	/** The email. */
	@NotBlank(message = "O email não pode ser nulo")
	@Email(message = "O email do funcionario está invalido.")
	@Length(max = 500, message = "O e-mail do funcionario deve ter no máximo {max} caracteres")
	private String email;

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
	 * Gets the cargo.
	 *
	 * @return the cargo
	 */
	public String getCargo() {
		return cargo;
	}

	/**
	 * Sets the cargo.
	 *
	 * @param cargo the new cargo
	 */
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	/**
	 * Gets the idade.
	 *
	 * @return the idade
	 */
	public int getIdade() {
		return idade;
	}

	/**
	 * Sets the idade.
	 *
	 * @param idade the new idade
	 */
	public void setIdade(int idade) {
		this.idade = idade;
	}

	/**
	 * Gets the entrada.
	 *
	 * @return the entrada
	 */
	public LocalTime getEntrada() {
		return entrada;
	}

	/**
	 * Sets the entrada.
	 *
	 * @param entrada the new entrada
	 */
	public void setEntrada(LocalTime entrada) {
		this.entrada = entrada;
	}

	/**
	 * Gets the saida.
	 *
	 * @return the saida
	 */
	public LocalTime getSaida() {
		return saida;
	}

	/**
	 * Sets the saida.
	 *
	 * @param saida the new saida
	 */
	public void setSaida(LocalTime saida) {
		this.saida = saida;
	}

	/**
	 * Gets the data contratacao.
	 *
	 * @return the data contratacao
	 */
	public LocalDate getDataContratacao() {
		return dataContratacao;
	}

	/**
	 * Sets the data contratacao.
	 *
	 * @param dataContratacao the new data contratacao
	 */
	public void setDataContratacao(LocalDate dataContratacao) {
		this.dataContratacao = dataContratacao;
	}

	/**
	 * Gets the cpf.
	 *
	 * @return the cpf
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * Sets the cpf.
	 *
	 * @param cpf the new cpf
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	/**
	 * Gets the telefones.
	 *
	 * @return the telefones
	 */
	public Set<Telefone> getTelefones() {
		return telefones;
	}

	/**
	 * Sets the telefones.
	 *
	 * @param telefones the new telefones
	 */
	public void setTelefones(Set<Telefone> telefones) {
		this.telefones = telefones;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.cpf).hashCode();
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
		Funcionario funcionario = (Funcionario) obj;
		return new EqualsBuilder().append(this.cpf, funcionario.getCpf()).isEquals();
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
