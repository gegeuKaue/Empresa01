package br.com.contmatic.easy.random;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;

import br.com.contmatic.easy.random.campo.NumeroTelefoneEasyRandom;
import br.com.contmatic.telefone.Telefone;

public class TelefoneEasyRandom {
	public static Telefone telefone() {
		EasyRandomParameters parameters = new EasyRandomParameters().randomize(FieldPredicates.named("numero")
				.and(FieldPredicates.ofType(String.class)).and(FieldPredicates.inClass(Telefone.class)),
				new NumeroTelefoneEasyRandom());

		return new EasyRandom(parameters).nextObject(Telefone.class);
	}

	public static void main(String[] args) {
		System.out.println(telefone());
	}
}
