package br.com.contmatic.easy.random;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;

import br.com.contmatic.easy.random.campo.CepEasyRandom;
import br.com.contmatic.easy.random.campo.NumeroEnderecoEasyRandom;
import br.com.contmatic.endereco.Endereco;

public class EnderecoEasyRandom {
	public static Endereco endereco() {
		EasyRandomParameters parameters = new EasyRandomParameters().randomize(FieldPredicates.named("numero")
				.and(FieldPredicates.ofType(Integer.class)).and(FieldPredicates.inClass(Endereco.class)),
				new NumeroEnderecoEasyRandom());
		parameters.randomize(FieldPredicates.named("cep").and(FieldPredicates.ofType(String.class))
				.and(FieldPredicates.inClass(Endereco.class)), new CepEasyRandom());

		return new EasyRandom(parameters).nextObject(Endereco.class);
	}

	public static void main(String[] args) {
		System.out.println(endereco());
	}
}
