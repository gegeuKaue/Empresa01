package br.com.contmatic.fixture;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.telefone.Telefone;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

public class FixtureFuncionario {
	public static void fixture() {
		Fixture.of(Funcionario.class).addTemplate("funcionario", new Rule() {
			{
				add("nome", regex("\\w{" + new Random().nextInt(89) + "}"));
				add("cargo", random("estagiario", "programador", "gestor"));
				add("idade", regex("\\d{2}"));
				add("entrada", new LocalTime(9, 0, 0));
				add("saida", new LocalTime(9, 0, 0));
				add("dataContratacao", new LocalDate(2017, 10, 31));
				add("cpf", random("81970188120", "80217988407", "84418932259"));
				Set<Telefone> telefones = new HashSet<Telefone>();
				FixtureTelefone.fixture();

				telefones.add((Telefone) Fixture.from(Telefone.class).gimme("telefone"));
				telefones.add((Telefone) Fixture.from(Telefone.class).gimme("telefone"));
				add("telefones", telefones);
				add("email", "${nome}@gmail.com");
			}
		});
	}
}
