package br.com.contmatic.empresa;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCodeFor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.code.beanmatchers.BeanMatchers;
import com.google.code.beanmatchers.ValueGenerator;

import br.com.contmatic.easy.random.FuncionarioEasyRandom;
import br.com.contmatic.easy.random.TelefoneEasyRandom;
import br.com.contmatic.groups.Post;
import br.com.contmatic.groups.Put;
import br.com.contmatic.telefone.Telefone;
import br.com.contmatic.telefone.TelefoneDDD;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

public class FuncionarioTest {

	private final static Class<Funcionario> CLASSE = Funcionario.class;
	private Validator validator;
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private Funcionario funcionario;

	@BeforeClass
	public static void setup() {
		reconhecerJodaTime();
	}

	@Before
	public void init() {
		this.funcionario = FuncionarioEasyRandom.funcionario();
	}

	@Test
	public void deve_respeitar_os_get_set() {
		assertThat(CLASSE, hasValidGettersAndSetters());
	}

	@Test
	public void deve_respeitar_hash_code() {
		assertThat(CLASSE, hasValidBeanHashCodeFor("cpf"));
	}

	@Test
	public void deve_respeitar_construtor() {
		assertThat(CLASSE, hasValidBeanConstructor());
	}

	@Test
	public void deve_respeitar_o_equals() {
		EqualsVerifier.forClass(CLASSE).withRedefinedSuperclass().usingGetClass().suppress(Warning.NONFINAL_FIELDS)
				.withOnlyTheseFields("cpf").verify();
	}

	@Test
	public void nao_deve_aceitar_nome_nulo() {
		funcionario.setNome(null);
		assertFalse(isValid(funcionario, "O nome do funcionario não deve ser vázio."));
	}

	@Test
	public void nao_deve_aceitar_nome_vazio() {
		funcionario.setNome("");
		assertFalse(isValid(funcionario, "O nome do funcionario não deve ser vázio."));
	}

	@Test
	public void deve_aceitar_nome_valido() {
		funcionario.setNome("Geovane");
		assertTrue(isValid(funcionario, "O nome do funcionario não deve ser vázio."));
	}

	@Test
	public void deve_aceitar_nome_sem_espaco() {
		funcionario.setNome("Contmatic");
		assertTrue(isValid(funcionario, "O nome do funcionário está incorreto"));
	}

	@Test
	public void deve_aceitar_nome_com_acento() {
		funcionario.setNome("João Da Esquiná");
		assertTrue(isValid(funcionario, "O nome do funcionário está incorreto"));
	}

	@Test
	public void deve_aceitar_nome_com_cedilha() {
		funcionario.setNome("Moço Legal");
		assertTrue(isValid(funcionario, "O nome do funcionário está incorreto"));
	}

	@Test
	public void deve_aceitar_nome_com_espaco() {
		funcionario.setNome("Cont matic");
		assertTrue(isValid(funcionario, "O nome do funcionário está incorreto"));
	}

	@Test
	public void nao_deve_aceitar_nome_com_arroba() {
		funcionario.setNome("Cont@ matic");
		assertFalse(isValid(funcionario, "O nome do funcionário está incorreto"));
	}

	@Test
	public void nao_deve_aceitar_nome_com_cerquilha() {
		funcionario.setNome("Cont# matic");
		assertFalse(isValid(funcionario, "O nome do funcionário está incorreto"));
	}

	@Test
	public void nao_deve_aceitar_cargo_vazio() {
		funcionario.setCargo("");
		assertFalse(isValid(funcionario, "O cargo do funcionario não deve ser vázio."));
	}

	@Test
	public void nao_deve_aceitar_cargo_nulo() {
		funcionario.setCargo(null);
		assertFalse(isValid(funcionario, "O cargo do funcionario não deve ser vázio."));
	}

	@Test
	public void nao_deve_aceitar_cargo_com_mais_500() {
		funcionario.setCargo(RandomStringUtils.randomAlphabetic(600));
		assertFalse(isValid(funcionario, "O cargo máximo de funcionario é de 500 caracteres"));
	}

	@Test
	public void deve_aceitar_cargo_valido() {
		funcionario.setCargo("estagiario");
		assertTrue(isValid(funcionario, "O cargo do funcionario não deve ser vázio."));
	}

	@Test
	public void nao_deve_aceitar_idade_negativa() {
		funcionario.setIdade(-5);
		assertFalse(isValid(funcionario, "A idade do funcionario não pode ser negativa."));
	}

	@Test
	public void nao_deve_aceitar_idade_igual_a_zero() {
		funcionario.setIdade(0);
		assertFalse(isValid(funcionario, "A idade do funcionario não pode ser negativa."));
	}

	@Test
	public void deve_aceitar_idade_valida() {
		funcionario.setIdade(18);
		assertTrue(isValid(funcionario, "A idade do funcionario não pode ser negativa."));
	}

	@Test
	public void nao_deve_aceitar_entrada_nula() {
		funcionario.setEntrada(null);
		assertFalse(isValid(funcionario, "O horario de entrada funcionario não pode ser nulo."));
	}

	@Test
	public void aceitar_entrada_valido() {
		funcionario.setEntrada(LocalTime.now());
		assertTrue(isValid(funcionario, "O horario de entrada funcionario não pode ser nulo."));
	}

	@Test
	public void nao_deve_aceitar_saida_nula() {
		funcionario.setSaida(null);
		assertFalse(isValid(funcionario, "O horario de saida funcionario não pode ser nulo."));
	}

	@Test
	public void deve_aceitar_saida_valida() {
		funcionario.setSaida(LocalTime.now());
		assertTrue(isValid(funcionario, "O horario de saida funcionario não pode ser nulo."));
	}

	@Test
	public void nao_deve_aceitar_cpf_vazio() {
		funcionario.setCpf("");
		assertFalse(isValid(funcionario, "O cep do funcionario não pode ser nulo."));
	}

	@Test
	public void nao_deve_aceitar_cpf_nulo() {
		funcionario.setCpf(null);
		assertFalse(isValid(funcionario, "O cep do funcionario não pode ser nulo."));
	}

	@Test
	public void nao_deve_aceitar_cpf_invalido() {
		funcionario.setCpf("47878244064");
		assertFalse(isValid(funcionario, "O CPF do funcionario está inválido"));
	}

	@Test
	public void nao_deve_aceitar_cpf_com_letra() {
		funcionario.setCpf("4787824406g");
		assertFalse(isValid(funcionario, "O CPF do funcionario está inválido"));
	}

	@Test
	public void nao_deve_aceitar_cpf_muito_numero() {
		funcionario.setCpf("4787824406555555");
		assertFalse(isValid(funcionario, "O CPF do funcionario está inválido"));
	}

	@Test
	public void nao_deve_aceitar_cpf_menos_numero() {
		funcionario.setCpf("55966");
		assertFalse(isValid(funcionario, "O CPF do funcionario está inválido"));
	}

	@Test
	public void nao_deve_aceitar_cpf_com_mascara() {
		funcionario.setCpf("102.057.110-14");
		assertFalse(isValid(funcionario, "O CPF do funcionario está inválido"));
	}

	@Test
	public void nao_deve_aceitar_cpf_com_numero_repitido() {
		funcionario.setCpf("11111111111");
		assertFalse(isValid(funcionario, "O CPF do funcionario está inválido"));
	}

	@Test
	public void deve_aceitar_cpf_valido() {
		funcionario.setCpf("47878244065");
		assertTrue(isValid(funcionario, "O CPF do funcionario está inválido"));
	}

	@Test
	public void nao_deve_aceitar_dataContratacao_nula() {
		funcionario.setDataContratacao(null);
		assertFalse(isValid(funcionario, "A data de contratação do funcionario não deve ser nula"));
	}

	@Test
	public void nao_deve_aceitar_dataContratacao_no_futuro() {
		funcionario.setDataContratacao(new LocalDate(2048, 10, 10));
		assertFalse(isValid(funcionario, "A data de contratação do funcionario não deve ser maior que a atual"));
	}

	@Test
	public void deve_aceitar_dataContratacao_valida() {
		funcionario.setDataContratacao(new LocalDate(2014, 10, 10));
		assertTrue(isValid(funcionario, "A data de contratação do funcionario não deve ser maior que a atual"));
	}

	@Test
	public void nao_deve_aceitar_telefones_nulos() {
		funcionario.setTelefones(null);
		assertFalse(isValid(funcionario, "O telefone do funcionario não pode ser nulo"));
	}

	@Test
	public void nao_deve_aceitar_telefones_invalidos() {
		Set<Telefone> telefones = new HashSet<Telefone>();
		Telefone telefone = TelefoneEasyRandom.telefone();
		telefone.setNumero("");
		telefones.add(telefone);
		funcionario.setTelefones(telefones);
		assertFalse(isValid(funcionario, "O número do telefone está invalido."));
	}

	@Test
	public void nao_deve_aceitar_telefones_vazio() {
		funcionario.setTelefones(new HashSet<Telefone>());
		assertFalse(isValid(funcionario, "A lista de endereço do funcionario não deve ser vazio."));
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

		funcionario.setTelefones(telefones);
		assertThat(funcionario.getTelefones().size(), is(1));
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
		funcionario.setTelefones(telefones);
		assertFalse(isValid(funcionario, "A lista de endereço do funcionario máxima é de 500."));
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

		funcionario.setTelefones(telefones);
		assertThat(funcionario.getTelefones().size(), is(2));
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

		funcionario.setTelefones(telefones);
		assertThat(funcionario.getTelefones().size(), is(2));
	}

	@Test
	public void deve_aceitar_email_valido() {
		funcionario.setEmail("geovane@gmail.com");
		assertTrue(isValid(funcionario, "O email do funcionario está invalido."));
	}

	@Test
	public void nao_deve_aceitar_email_com_mais_de_500_caracteres() {
		funcionario.setEmail("A@" + RandomStringUtils.randomAlphabetic(500) + ".com");
		assertFalse(isValid(funcionario, "O e-mail do funcionario deve ter no máximo 500 caracteres"));
	}

	@Test
	public void nao_deve_aceitar_email_com_2_arroba() {
		funcionario.setEmail("A@@gmail.com");
		assertFalse(isValid(funcionario, "O email do funcionario está invalido."));
	}

	@Test
	public void nao_deve_aceitar_email_com_arroba_no_comeco() {
		funcionario.setEmail("@gmail.com");
		assertFalse(isValid(funcionario, "O email do funcionario está invalido."));
	}

	@Test
	public void nao_deve_aceitar_email_com_ponto_no_comeco() {
		funcionario.setEmail(".a@gmail.com");
		assertFalse(isValid(funcionario, "O email do funcionario está invalido."));
	}

	@Test
	public void nao_deve_aceitar_email_com_ponto_no_final() {
		funcionario.setEmail("a@gmail.com.");
		assertFalse(isValid(funcionario, "O email do funcionario está invalido."));
	}

	@Test
	public void deve_aceitar_email_com_dorminio_brasileiro() {
		funcionario.setEmail("a@gmail.com.br");
		assertTrue(isValid(funcionario, "O email do funcionario está invalido."));
	}

	@Test
	public void nao_deve_aceitar_email_com_arroba_separado() {
		funcionario.setEmail("aa@a@gmail.com.br");
		assertFalse(isValid(funcionario, "O email do funcionario está invalido."));
	}

	@Test
	public void nao_deve_aceitar_email_com_ponto_virgula() {
		funcionario.setEmail("a@gmail;com;br");
		assertFalse(isValid(funcionario, "O email do funcionario está invalido."));
	}

	@Test
	public void deve_aceitar_email_com_numero() {
		funcionario.setEmail("a2@gmail.com");
		assertTrue(isValid(funcionario, "O email da empresa está inválido"));
	}

	@Test
	public void deve_conter_o_valor_nome_no_toString() {
		assertThat(new Funcionario().toString(), containsString("nome"));
	}

	@Test
	public void deve_conter_o_valor_cargo_no_toString() {
		assertThat(new Funcionario().toString(), containsString("cargo"));
	}

	@Test
	public void deve_conter_o_valor_idade_no_toString() {
		assertThat(new Funcionario().toString(), containsString("idade"));
	}

	@Test
	public void deve_conter_o_valor_entrada_no_toString() {
		assertThat(new Funcionario().toString(), containsString("entrada"));
	}

	@Test
	public void deve_conter_o_valor_saida_no_toString() {
		assertThat(new Funcionario().toString(), containsString("saida"));
	}

	@Test
	public void deve_conter_o_valor_dataContratacao_no_toString() {
		assertThat(new Funcionario().toString(), containsString("dataContratacao"));
	}

	@Test
	public void deve_conter_o_valor_cpf_no_toString() {
		assertThat(new Funcionario().toString(), containsString("cpf"));
	}

	@Test
	public void deve_conter_o_valor_s_no_toString() {
		assertThat(new Funcionario().toString(), containsString("s"));
	}

	@Test
	public void deve_conter_o_valor_email_no_toString() {
		assertThat(new Funcionario().toString(), containsString("email"));
	}

	public boolean isValid(Funcionario funcionario, String mensagem) {
		validator = factory.getValidator();
		boolean valido = true;
		Set<ConstraintViolation<Funcionario>> restricoes = validator.validate(funcionario, Post.class);
		for (ConstraintViolation<Funcionario> constraintViolation : restricoes)
			if (constraintViolation.getMessage().equalsIgnoreCase(mensagem))
				valido = false;
		
		Set<ConstraintViolation<Funcionario>> restricoes2 = validator.validate(funcionario, Put.class);
		for (ConstraintViolation<Funcionario> constraintViolation : restricoes2)
			if (constraintViolation.getMessage().equalsIgnoreCase(mensagem))
				valido = false;

		return valido;
	}

	public static void reconhecerJodaTime() {
		BeanMatchers.registerValueGenerator(new ValueGenerator<LocalTime>() {
			public LocalTime generate() {
				return LocalTime.now();
			}
		}, LocalTime.class);

		BeanMatchers.registerValueGenerator(new ValueGenerator<LocalDate>() {
			public LocalDate generate() {
				return LocalDate.now();
			}
		}, LocalDate.class);
	}
}
