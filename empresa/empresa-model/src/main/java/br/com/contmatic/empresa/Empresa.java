package br.com.contmatic.empresa;

import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import br.com.caelum.stella.bean.validation.CNPJ;
import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.regex.RegexType;
import br.com.contmatic.telefone.Telefone;

/**
 * The Class Empresa.
 */
/**
 * @author geovane.santos
 *
 */
public class Empresa {

	/** The nome. */
	@NotBlank(message = "O nome da empresa não pode ser nulo.")
	@Length(max = 100, message = "O nome da empresa deve ter {max} caracteres")
	@Pattern(regexp = RegexType.NOME, message = "O nome da empresa está incorreto")
	private String nome;

	/** The email. */
	@Email(message = "O email da empresa está inválido")
	@NotBlank(message = "O email da empresa não pode ser nulo")
	@Length(max = 500, message = "O e-mail da empresa deve ter no máximo {max} caracteres")
	private String email;

	/** The cnpj. */

	@NotBlank(message = "O cnpj não pode ser nulo")
	@CNPJ(message = "O CNPJ da empresa está invalido")
	private String cnpj;

	/** The endereco. */
	@Valid
	@NotNull(message = "O endereço da empresa está vazio")
	@Size.List({ @Size(min = 1, message = "A lista de endereço está vazia"),
			@Size(max = 50, message = "A lista de endereço máxima é de {max}") })
	private Set<Endereco> enderecos;

	/** The telefones. */
	@Valid
	@NotNull(message = "O telefone da empresa não pode ser nulo")
	@Size.List({ @Size(min = 1, message = "A lista de telefone da empresa não deve ser vazio."),
			@Size(max = 500, message = "A lista de telefone da empresa máxima é de {max}.") })
	private Set<Telefone> telefones;

	/** The lista funcionario. */
	@Valid
	@NotNull(message = "Os funcionários da empresa está nulo.")
	@Size.List({ @Size(max = 1000, message = "O número máximo de funcionario da empresa é de {max}"),
			@Size(min = 1, message = "Não tem nenhum funcionario cadastrado na empresa.") })
	private List<Funcionario> funcionarios;

	/** The url. */
	@NotBlank(message = "A url do site ada empresa não pode ser vazio.")
	@Pattern(regexp = RegexType.URL, message = "A url do site da empresa está invalida.")
	private String url;

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
	 * Gets the nome.
	 *
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Gets the url.
	 *
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Sets the url.
	 *
	 * @param url the new url
	 */
	public void setUrl(String url) {
		this.url = url;
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
	 * Gets the cnpj.
	 *
	 * @return the cnpj
	 */
	public String getCnpj() {
		return cnpj;
	}

	/**
	 * Sets the cnpj.
	 *
	 * @param cnpj the new cnpj
	 */
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	/**
	 * Gets the enderecos.
	 *
	 * @return the enderecos
	 */
	public Set<Endereco> getEnderecos() {
		return enderecos;
	}

	/**
	 * Sets the enderecos.
	 *
	 * @param enderecos the new enderecos
	 */
	public void setEnderecos(Set<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	/**
	 * Gets the funcionarios.
	 *
	 * @return the funcionarios
	 */
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	/**
	 * Sets the funcionarios.
	 *
	 * @param funcionarios the new funcionarios
	 */
	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.cnpj).toHashCode();
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
		Empresa empresa = (Empresa) obj;
		return new EqualsBuilder().append(this.cnpj, empresa.getCnpj()).isEquals();
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
