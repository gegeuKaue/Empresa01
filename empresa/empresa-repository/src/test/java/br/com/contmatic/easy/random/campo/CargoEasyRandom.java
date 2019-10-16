package br.com.contmatic.easy.random.campo;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.jeasy.random.api.Randomizer;

public class CargoEasyRandom implements Randomizer<String> {
	List<String> cargos = Arrays.asList("Estagiário", "Desenvolvedor", "Gerente", "Supervisor", "Analista", "Diretor");

	@Override
	public String getRandomValue() {
		return cargos.get(new Random().nextInt(cargos.size() - 1));
	}

}
