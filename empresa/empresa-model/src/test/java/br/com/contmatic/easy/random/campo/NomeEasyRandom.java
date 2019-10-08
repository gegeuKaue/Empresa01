package br.com.contmatic.easy.random.campo;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.jeasy.random.api.Randomizer;

public class NomeEasyRandom implements Randomizer<String> {
	List<String> nomes = Arrays.asList("Geovane", "Kaue", "Santos", "Kaue Geovane", "Geovane Kaue", "Santos Kaue",
			"Kaue Santos", "Geovane Kaue Santos", RandomStringUtils.randomAlphabetic(30));

	@Override
	public String getRandomValue() {
		return nomes.get(new Random().nextInt(nomes.size() - 1));
	}
}
