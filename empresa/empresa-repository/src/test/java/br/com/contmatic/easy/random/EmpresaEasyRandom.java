package br.com.contmatic.easy.random;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;

import br.com.contmatic.easy.random.campo.CNPJEasyRandom;
import br.com.contmatic.easy.random.campo.EmailEasyRandom;
import br.com.contmatic.easy.random.campo.ModelTelefoneEasyRandom;
import br.com.contmatic.easy.random.campo.NomeEmpresaEasyRandom;
import br.com.contmatic.easy.random.campo.UrlEasyRandom;
import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.telefone.Telefone;

public class EmpresaEasyRandom {
	public static Empresa empresa() {
		EasyRandomParameters parameters = new EasyRandomParameters().randomize(FieldPredicates.named("nome")
				.and(FieldPredicates.ofType(String.class)).and(FieldPredicates.inClass(Empresa.class)),
				new NomeEmpresaEasyRandom());

		parameters.randomize(FieldPredicates.named("email").and(FieldPredicates.ofType(String.class))
				.and(FieldPredicates.inClass(Empresa.class)), new EmailEasyRandom());

		parameters.randomize(FieldPredicates.named("cnpj").and(FieldPredicates.ofType(String.class))
				.and(FieldPredicates.inClass(Empresa.class)), new CNPJEasyRandom());

		parameters.randomize(FieldPredicates.named("numero").and(FieldPredicates.ofType(Telefone.class))
				.and(FieldPredicates.inClass(Empresa.class)), new ModelTelefoneEasyRandom());

		parameters.randomize(FieldPredicates.named("url").and(FieldPredicates.ofType(String.class))
				.and(FieldPredicates.inClass(Empresa.class)), new UrlEasyRandom());

		return new EasyRandom(parameters).nextObject(Empresa.class);
	}

	public static void main(String[] args) {
		System.out.println(empresa());
	}
}
