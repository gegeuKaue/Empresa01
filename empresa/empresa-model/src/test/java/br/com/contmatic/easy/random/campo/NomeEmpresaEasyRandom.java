package br.com.contmatic.easy.random.campo;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.jeasy.random.api.Randomizer;

public class NomeEmpresaEasyRandom implements Randomizer<String> {
	private List<String> nomes = Arrays.asList("Contmatic", "Cont", "Avon", "Natura", "Cacau",
			RandomStringUtils.randomAlphabetic(new Random().nextInt(45) + 20));

	@Override
	public String getRandomValue() {
		return nomes.get(new Random().nextInt(nomes.size() - 1));
	}
}
