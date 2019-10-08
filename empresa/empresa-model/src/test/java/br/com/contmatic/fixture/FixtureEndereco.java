package br.com.contmatic.fixture;

import java.util.Random;

import br.com.contmatic.endereco.Cidade;
import br.com.contmatic.endereco.Endereco;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

public class FixtureEndereco {
	public static void fixture() {
		Fixture.of(Endereco.class).addTemplate("endereco", new Rule() {
			{
				FixtureCidade.fixture();

				add("numero", new Random().nextInt(700) + 1);
				add("cep", random("08596800", "08577430", "08583690", "08583003"));
				add("cidade", (Cidade) Fixture.from(Cidade.class).gimme("cidade"));
			}
		});
	}
}
