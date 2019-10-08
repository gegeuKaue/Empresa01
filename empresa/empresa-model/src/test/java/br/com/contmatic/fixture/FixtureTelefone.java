package br.com.contmatic.fixture;

import java.util.Random;

import br.com.contmatic.telefone.Telefone;
import br.com.contmatic.telefone.TelefoneDDD;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

public class FixtureTelefone {
	public static void fixture() {
		Fixture.of(Telefone.class).addTemplate("telefone", new Rule() {
			{
				add("ddd", TelefoneDDD.values()[new Random().nextInt(TelefoneDDD.values().length - 1)]);
				add("numero", random("965123453", "954162165", "985151565", "985153136", "955885423", "954477525",
						"985211445"));
			}
		});
	}
}
