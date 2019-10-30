package br.com.contmatic.empresa;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCodeFor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static nl.jqno.equalsverifier.Warning.NONFINAL_FIELDS;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

import br.com.contmatic.easy.random.EmpresaEasyRandom;
import br.com.contmatic.easy.random.FuncionarioEasyRandom;
import br.com.contmatic.easy.random.TelefoneEasyRandom;
import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.groups.Post;
import br.com.contmatic.groups.Put;
import br.com.contmatic.telefone.Telefone;
import br.com.contmatic.telefone.TelefoneDDD;
import nl.jqno.equalsverifier.EqualsVerifier;

public class EmpresaTest {

	private final static Class<Empresa> CLASSE = Empresa.class;
	private Empresa empresa;
	private Validator validator;
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

	@Before
	public void init() {
		this.empresa = EmpresaEasyRandom.empresa();
	}

	@Test
	public void deve_respeitar_os_get_set() {
		assertThat(CLASSE, hasValidGettersAndSetters());
	}

	@Test
	public void deve_respeitar_construtor() {
		assertThat(CLASSE, hasValidBeanConstructor());
	}

	@Test
	public void deve_respeitar_hash_code() {
		assertThat(CLASSE, hasValidBeanHashCodeFor("cnpj"));
	}

	@Test
	public void deve_respeitar_o_equals_da_empresa() {
		EqualsVerifier.forClass(CLASSE).withRedefinedSuperclass().usingGetClass().suppress(NONFINAL_FIELDS)
				.withOnlyTheseFields("cnpj").verify();
	}

	@Test
	public void deve_aceitar_nome_valido() {
		empresa.setNome("Contmatic");
		assertTrue(isValid(empresa, "O nome da empresa deve ter 100 caracteres"));
	}

	@Test
	public void nao_deve_aceitar_nome_com_mais_de_100() {
		empresa.setNome(RandomStringUtils.randomAlphanumeric(105));
		assertFalse(isValid(empresa, "O nome da empresa deve ter 100 caracteres"));
	}

	@Test
	public void nao_deve_aceitar_nome_nulo() {
		empresa.setNome(null);
		assertFalse(isValid(empresa, "O nome da empresa não pode ser nulo."));
	}

	@Test
	public void nao_deve_aceitar_nome_vazio() {
		empresa.setNome("");
		assertFalse(isValid(empresa, "O nome da empresa não pode ser nulo."));
	}

	@Test
	public void deve_aceitar_nome_sem_espaco() {
		empresa.setNome("Contmatic");
		assertTrue(isValid(empresa, "O nome da empresa está incorreto"));
	}

	@Test
	public void deve_aceitar_nome_com_acento() {
		empresa.setNome("João Da Esquiná");
		assertTrue(isValid(empresa, "O nome da empresa está incorreto"));
	}

	@Test
	public void deve_aceitar_nome_com_cedilha() {
		empresa.setNome("Moço Legal");
		assertTrue(isValid(empresa, "O nome da empresa está incorreto"));
	}

	@Test
	public void deve_aceitar_nome_com_espaco() {
		empresa.setNome("Cont matic");
		assertTrue(isValid(empresa, "O nome da empresa está incorreto"));
	}

	@Test
	public void nao_deve_aceitar_nome_com_arroba() {
		empresa.setNome("Cont@ matic");
		assertFalse(isValid(empresa, "O nome da empresa está incorreto"));
	}

	@Test
	public void nao_deve_aceitar_nome_com_cerquilha() {
		empresa.setNome("Cont# matic");
		assertFalse(isValid(empresa, "O nome da empresa está incorreto"));
	}

	@Test
	public void nao_deve_aceitar_email_invalido() {
		empresa.setEmail("A@");
		assertFalse(isValid(empresa, "O email da empresa está inválido"));
	}

	@Test
	public void nao_deve_aceitar_email_com_mais_de_500_caracteres() {
		empresa.setEmail("A@" + RandomStringUtils.randomAlphabetic(500) + ".com");
		assertFalse(isValid(empresa, "O e-mail da empresa deve ter no máximo 500 caracteres"));
	}

	@Test
	public void nao_deve_ter_lista_de_funcionario_vazia() {
		empresa.setFuncionarios(new ArrayList<Funcionario>());
		assertFalse(isValid(empresa, "Não tem nenhum funcionario cadastrado na empresa."));
	}

	@Test
	public void deve_ter_lista_de_funcionario_com_funcionario() {
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		funcionarios.add(FuncionarioEasyRandom.funcionario());
		empresa.setFuncionarios(funcionarios);
		assertTrue(isValid(empresa, "Não tem nenhum funcionario cadastrado na empresa."));
	}

	@Test
	public void nao_deve_ter_lista_de_funcionario_com_mais_de_1000() {
		List<Funcionario> funcionarios = new LinkedList<Funcionario>();
		for (int i = 0; i <= 1005; i++) {
			funcionarios.add(FuncionarioEasyRandom.funcionario());
		}
		empresa.setFuncionarios(funcionarios);
		assertFalse(isValid(empresa, "O número máximo de funcionario da empresa é de 1000"));
	}

	@Test
	public void nao_deve_aceitar_email_com_2_arroba() {
		empresa.setEmail("A@@gmail.com");
		assertFalse(isValid(empresa, "O email da empresa está inválido"));
	}

	@Test
	public void nao_deve_aceitar_email_com_2_ponto_seguindo() {
		empresa.setEmail("A@gmail..com");
		assertFalse(isValid(empresa, "O email da empresa está inválido"));
	}

	@Test
	public void nao_deve_aceitar_email_com_arroba_no_comeco() {
		empresa.setEmail("@gmail.com");
		assertFalse(isValid(empresa, "O email da empresa está inválido"));
	}

	@Test
	public void nao_deve_aceitar_email_com_ponto_no_comeco() {
		empresa.setEmail(".a@gmail.com");
		assertFalse(isValid(empresa, "O email da empresa está inválido"));
	}

	@Test
	public void deve_aceitar_email_com_numero() {
		empresa.setEmail("a2@gmail.com");
		assertTrue(isValid(empresa, "O email da empresa está inválido"));
	}

	@Test
	public void deve_aceitar_email_valido() {
		empresa.setEmail("A@a.com");
		assertTrue(isValid(empresa, "O email da empresa está inválido"));
	}

	@Test
	public void nao_deve_aceitar_email_nulo() {
		empresa.setEmail(null);
		assertFalse(isValid(empresa, "O email da empresa não pode ser nulo"));
	}

	@Test
	public void nao_deve_aceitar_email_vazio() {
		empresa.setEmail("");
		assertFalse(isValid(empresa, "O email da empresa não pode ser nulo"));
	}

	@Test
	public void nao_deve_aceitar_email_com_ponto_no_final() {
		empresa.setEmail("a@gmail.com.");
		assertFalse(isValid(empresa, "O email da empresa está inválido"));
	}

	@Test
	public void deve_aceitar_email_com_dorminio_brasileiro() {
		empresa.setEmail("a@gmail.com.br");
		assertTrue(isValid(empresa, "O email da empresa está inválido"));
	}

	@Test
	public void nao_deve_aceitar_email_com_arroba_separado() {
		empresa.setEmail("aa@a@gmail.com.br");
		assertFalse(isValid(empresa, "O email da empresa está inválido"));
	}

	@Test
	public void nao_deve_aceitar_email_com_ponto_virgula() {
		empresa.setEmail("a@gmail;com;br");
		assertFalse(isValid(empresa, "O email da empresa está inválido"));
	}

	@Test
	public void nao_deve_aceitar_cnpj_vazio() {
		empresa.setCnpj("");
		assertFalse(isValid(empresa, "O cnpj não pode ser nulo"));
	}

	@Test
	public void nao_deve_aceitar_cnpj_nulo() {
		empresa.setCnpj(null);
		assertFalse(isValid(empresa, "O cnpj não pode ser nulo"));
	}

	@Test
	public void deve_aceitar_cnpj_valido() {
		empresa.setCnpj("68263601000120");
		assertTrue(isValid(empresa, "O CNPJ da empresa está invalido"));
	}

	@Test
	public void nao_deve_aceitar_cnpj_com_menos_numeros() {
		empresa.setCnpj("682636");
		assertFalse(isValid(empresa, "O CNPJ da empresa está invalido"));
	}

	@Test
	public void nao_deve_aceitar_cnpj_com_mais_numeros() {
		empresa.setCnpj("68263888888888888888888886");
		assertFalse(isValid(empresa, "O CNPJ da empresa está invalido"));
	}

	@Test
	public void nao_deve_aceitar_cnpj_com_numeros_repetido() {
		empresa.setCnpj("11111111111111");
		assertFalse(isValid(empresa, "O CNPJ da empresa está invalido"));
	}

	@Test
	public void nao_deve_aceitar_cnpj_com_letras() {
		empresa.setCnpj("6826360100012g");
		assertFalse(isValid(empresa, "O CNPJ da empresa está invalido"));
	}

	@Test
	public void nao_deve_aceitar_cnpj_valido_com_mascara() {
		empresa.setCnpj("54.873.456/0001-95");
		assertFalse(isValid(empresa, "O CNPJ da empresa está invalido"));
	}

	@Test
	public void nao_deve_aceitar_cnpj_invalido() {
		empresa.setCnpj("68263601000121");
		assertFalse(isValid(empresa, "O CNPJ da empresa está invalido"));
	}

	@Test
	public void nao_deve_aceitar_funcionarios_nulos() {
		empresa.setFuncionarios(null);
		assertFalse(isValid(empresa, "Os funcionários da empresa está nulo."));
	}

	@Test
	public void nao_deve_aceitar_funcionarios_invalido() {
		Funcionario funcionario = FuncionarioEasyRandom.funcionario();
		funcionario.setIdade(-1);
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		funcionarios.add(funcionario);
		empresa.setFuncionarios(funcionarios);
		assertFalse(isValid(empresa, "A idade do funcionario não pode ser negativa."));
	}

	@Test
	public void deve_aceitar_funcionarios_valido() {
		Funcionario funcionario = FuncionarioEasyRandom.funcionario();
		funcionario.setIdade(18);
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		funcionarios.add(funcionario);
		empresa.setFuncionarios(funcionarios);
		assertTrue(isValid(empresa, "A idade do funcionario não pode ser negativa."));
	}

	@Test
	public void nao_deve_aceitar_enderecos_nulos() {
		empresa.setEnderecos(null);
		assertFalse(isValid(empresa, "O endereço da empresa está vazio"));
	}

	@Test
	public void nao_deve_aceitar_enderecos_vazio() {
		empresa.setEnderecos(new HashSet<Endereco>());
		assertFalse(isValid(empresa, "A lista de endereço está vazia"));
	}

	@Test
	public void nao_deve_aceitar_enderecos_iguais() {
		Set<Endereco> enderecos = new HashSet<Endereco>();
		Endereco end = new Endereco();
		end.setCep("08588145");
		end.setNumero(367);

		Endereco end2 = new Endereco();
		end2.setCep("08588145");
		end2.setNumero(367);

		enderecos.add(end);
		enderecos.add(end2);

		empresa.setEnderecos(enderecos);
		assertThat(empresa.getEnderecos().size(), is(1));
	}

	@Test
	public void deve_aceitar_enderecos_diferente() {
		Set<Endereco> enderecos = new HashSet<Endereco>();
		Endereco end = new Endereco();
		end.setCep("08588145");
		end.setNumero(367);

		Endereco end2 = new Endereco();
		end2.setCep("08588456");
		end2.setNumero(37);

		enderecos.add(end);
		enderecos.add(end2);

		empresa.setEnderecos(enderecos);
		assertThat(empresa.getEnderecos().size(), is(2));
	}

	@Test
	public void nao_deve_aceitar_enderecos_acima_de_50() {
		Set<Endereco> enderecos = new HashSet<Endereco>();
		Endereco end = new Endereco();

		for (int i = 0; i < 58; i++) {
			end.setCep("08588145");
			end.setNumero(i);
			enderecos.add(end);
		}

		empresa.setEnderecos(enderecos);
		assertFalse(isValid(empresa, "A lista de endereço máxima é de 50"));
	}

	@Test
	public void deve_aceitar_enderecos_com_mesmo_cep_e_numero_diferente() {
		Set<Endereco> enderecos = new HashSet<Endereco>();
		Endereco end = new Endereco();
		end.setCep("08588145");
		end.setNumero(367);

		Endereco end2 = new Endereco();
		end2.setCep("08588145");
		end2.setNumero(37);

		enderecos.add(end);
		enderecos.add(end2);

		empresa.setEnderecos(enderecos);
		assertThat(empresa.getEnderecos().size(), is(2));
	}

	@Test
	public void deve_aceitar_enderecos_com_o_cep_diferente_e_numero_igual() {
		Set<Endereco> enderecos = new HashSet<Endereco>();
		Endereco end = new Endereco();
		end.setCep("088888888");
		end.setNumero(367);

		Endereco end2 = new Endereco();
		end2.setCep("08588145");
		end2.setNumero(367);

		enderecos.add(end);
		enderecos.add(end2);

		empresa.setEnderecos(enderecos);
		assertThat(empresa.getEnderecos().size(), is(2));
	}

	@Test
	public void nao_deve_aceitar_url_nula() {
		empresa.setUrl(null);
		assertFalse(isValid(empresa, "A url do site ada empresa não pode ser vazio."));
	}

	@Test
	public void nao_deve_aceitar_url_vazio() {
		empresa.setUrl("");
		assertFalse(isValid(empresa, "A url do site ada empresa não pode ser vazio."));
	}

	@Test
	public void nao_deve_aceitar_somente_o_protocolo_da_url() {
		empresa.setUrl("http://");
		assertFalse(isValid(empresa, "A url do site da empresa está invalida."));
	}

	@Test
	public void nao_deve_aceitar_somente_o_www_da_url() {
		empresa.setUrl("www");
		assertFalse(isValid(empresa, "A url do site da empresa está invalida."));
	}

	@Test
	public void deve_aceitar_url_valida() {
		empresa.setUrl("http://contmatic.com.br");
		assertTrue(isValid(empresa, "A url do site da empresa está invalida."));
	}

	@Test
	public void nao_deve_aceitar_somente_o_protocolo_e_www_da_url() {
		empresa.setUrl("http://contmatic.com.br");
		assertTrue(isValid(empresa, "A url do site da empresa está invalida."));
	}

	@Test
	public void nao_deve_aceitar_somente_o_dominio_da_url() {
		empresa.setUrl("contmatic.com");
		assertFalse(isValid(empresa, "A url do site da empresa está invalida."));
	}

	@Test
	public void nao_deve_aceitar_somente_o_dominio_da_url_com_dois_ponto_seguido() {
		empresa.setUrl("contmatic..com");
		assertFalse(isValid(empresa, "A url do site da empresa está invalida."));
	}

	@Test
	public void nao_deve_aceitar_url_com_ponto_no_final() {
		empresa.setUrl("http://www.contmatic.com.");
		assertFalse(isValid(empresa, "A url do site da empresa está invalida."));
	}

	@Test
	public void nao_deve_aceitar_mais_de_telefones_500() {
		Telefone telefone = new Telefone();
		telefone.setDdd(TelefoneDDD.DDD11);
		Set<Telefone> telefones = new HashSet<Telefone>();
		for (int i = 0; i < 505; i++) {
			telefone.setNumero("9" + RandomStringUtils.randomNumeric(8));
			telefones.add(telefone);
		}
		empresa.setTelefones(telefones);
		assertFalse(isValid(empresa, "A lista de telefone da empresa máxima é de 500."));
	}

	@Test
	public void nao_deve_aceitar_url_sem_www() {
		empresa.setUrl("http://contmatic.com.");
		assertFalse(isValid(empresa, "A url do site da empresa está invalida."));
	}

	@Test
	public void nao_deve_aceitar_telefones_nulos() {
		empresa.setTelefones(null);
		assertFalse(isValid(empresa, "O telefone da empresa não pode ser nulo"));
	}

	@Test
	public void nao_deve_aceitar_telefones_vazio() {
		empresa.setTelefones(new HashSet<Telefone>());
		assertFalse(isValid(empresa, "A lista de telefone da empresa não deve ser vazio."));
	}

	@Test
	public void nao_deve_aceitar_telefones_iguais() {
		Set<Telefone> telefones = new HashSet<Telefone>();
		Telefone telefone = new Telefone();
		telefone.setDdd(TelefoneDDD.DDD11);
		telefone.setNumero("985191606");

		Telefone telefone2 = new Telefone();
		telefone2.setDdd(TelefoneDDD.DDD11);
		telefone2.setNumero("985191606");

		telefones.add(telefone);
		telefones.add(telefone2);

		empresa.setTelefones(telefones);
		assertThat(empresa.getTelefones().size(), is(1));
	}

	@Test
	public void deve_aceitar_telefones_com_ddd_diferente() {
		Set<Telefone> telefones = new HashSet<Telefone>();
		Telefone telefone = new Telefone();
		telefone.setDdd(TelefoneDDD.DDD11);
		telefone.setNumero("985191606");

		Telefone telefone2 = new Telefone();
		telefone2.setDdd(TelefoneDDD.DDD13);
		telefone2.setNumero("985191606");

		telefones.add(telefone);
		telefones.add(telefone2);

		empresa.setTelefones(telefones);
		assertThat(empresa.getTelefones().size(), is(2));
	}

	@Test
	public void deve_aceitar_telefones_com_o_numero_diferente() {
		Set<Telefone> telefones = new HashSet<Telefone>();
		Telefone telefone = new Telefone();
		telefone.setDdd(TelefoneDDD.DDD11);
		telefone.setNumero("985191606");

		Telefone telefone2 = new Telefone();
		telefone2.setDdd(TelefoneDDD.DDD11);
		telefone2.setNumero("985191617");

		telefones.add(telefone);
		telefones.add(telefone2);

		empresa.setTelefones(telefones);
		assertThat(empresa.getTelefones().size(), is(2));
	}

	@Test
	public void nao_deve_aceitar_telefones_invalidos() {
		Set<Telefone> telefones = new HashSet<Telefone>();
		Telefone telefone = TelefoneEasyRandom.telefone();
		telefone.setNumero("");
		telefones.add(telefone);
		empresa.setTelefones(telefones);
		assertFalse(isValid(empresa, "O número do telefone está invalido."));
	}

	@Test
	public void deve_conter_o_valor_nome_no_toString() {
		assertThat(new Empresa().toString(), containsString("nome"));
	}

	@Test
	public void deve_conter_o_valor_email_no_toString() {
		assertThat(new Empresa().toString(), containsString("email"));
	}

	@Test
	public void deve_conter_o_valor_cnpj_no_toString() {
		assertThat(new Empresa().toString(), containsString("cnpj"));
	}

	@Test
	public void deve_conter_o_valor_funcionarios_no_toString() {
		assertThat(new Empresa().toString(), containsString("funcionarios"));
	}

	@Test
	public void deve_conter_o_valor_enderecos_no_toString() {
		assertThat(new Empresa().toString(), containsString("enderecos"));
	}

	public boolean isValid(Empresa empresa, String mensagem) {
		validator = factory.getValidator();
		boolean valido = true;
		Set<ConstraintViolation<Empresa>> restricoes = validator.validate(empresa, Post.class);
		for (ConstraintViolation<Empresa> constraintViolation : restricoes)
			if (constraintViolation.getMessage().equalsIgnoreCase(mensagem))
				valido = false;
		
		Set<ConstraintViolation<Empresa>> restricoes2 = validator.validate(empresa, Put.class);
		for (ConstraintViolation<Empresa> constraintViolation : restricoes2)
			if (constraintViolation.getMessage().equalsIgnoreCase(mensagem))
				valido = false;

		return valido;
	}
}
