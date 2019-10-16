package br.com.contmatic.easy.random.campo;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.jeasy.random.api.Randomizer;

public class BairroEasyRandom implements Randomizer<String> {
	List<String> bairros = Arrays.asList("Res. Flamboyant", "Caiubi", "Village", "Jardim Rio", "Jardim São Paulo",
			"São Carlos");

	@Override
	public String getRandomValue() {
		return bairros.get(new Random().nextInt(bairros.size() - 1));
	}

}
