package br.com.contmatic.easy.random;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;

import br.com.contmatic.easy.random.campo.CargoEasyRandom;
import br.com.contmatic.easy.random.campo.CpfEasyRandom;
import br.com.contmatic.easy.random.campo.EmailEasyRandom;
import br.com.contmatic.easy.random.campo.IdadeEasyRandom;
import br.com.contmatic.easy.random.campo.NomeEasyRandom;
import br.com.contmatic.empresa.Funcionario;

public class FuncionarioEasyRandom {
	public static Funcionario funcionario() {
		EasyRandomParameters parameters = new EasyRandomParameters().randomize(FieldPredicates.named("nome")
				.and(FieldPredicates.ofType(String.class)).and(FieldPredicates.inClass(Funcionario.class)),
				new NomeEasyRandom());
		parameters.randomize(FieldPredicates.named("cargo").and(FieldPredicates.ofType(String.class))
				.and(FieldPredicates.inClass(Funcionario.class)), new CargoEasyRandom());
		
		parameters.randomize(FieldPredicates.named("idade").and(FieldPredicates.ofType(Integer.class))
				.and(FieldPredicates.inClass(Funcionario.class)), new IdadeEasyRandom());
		
		parameters.randomize(FieldPredicates.named("cpf").and(FieldPredicates.ofType(String.class))
				.and(FieldPredicates.inClass(Funcionario.class)), new CpfEasyRandom());
		
		parameters.randomize(FieldPredicates.named("email").and(FieldPredicates.ofType(String.class))
				.and(FieldPredicates.inClass(Funcionario.class)), new EmailEasyRandom());

		return new EasyRandom(parameters).nextObject(Funcionario.class);
	}

	public static void main(String[] args) {
		System.out.println(funcionario());
	}
}
