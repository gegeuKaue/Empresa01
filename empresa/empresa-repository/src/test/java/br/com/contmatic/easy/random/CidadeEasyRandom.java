package br.com.contmatic.easy.random;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;

import br.com.contmatic.easy.random.campo.NomeCidadeEasyRandom;
import br.com.contmatic.endereco.Cidade;

public class CidadeEasyRandom {
	public static Cidade cidade() {
		EasyRandomParameters parameters = new EasyRandomParameters().randomize(FieldPredicates.named("nome")
				.and(FieldPredicates.ofType(String.class)).and(FieldPredicates.inClass(Cidade.class)),
				new NomeCidadeEasyRandom());

		return new EasyRandom(parameters).nextObject(Cidade.class);
	}

	public static void main(String[] args) {
		System.out.println(cidade());
	}
}
