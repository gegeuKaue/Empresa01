package br.com.contmatic.easy.random.campo;

import org.apache.commons.lang3.RandomStringUtils;
import org.jeasy.random.api.Randomizer;

public class CepEasyRandom implements Randomizer<String> {

	@Override
	public String getRandomValue() {
		return RandomStringUtils.randomNumeric(8);
	}
}
