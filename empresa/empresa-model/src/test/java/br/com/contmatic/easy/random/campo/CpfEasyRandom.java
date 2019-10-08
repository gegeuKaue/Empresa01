package br.com.contmatic.easy.random.campo;

import org.apache.commons.lang3.RandomStringUtils;
import org.jeasy.random.api.Randomizer;

public class CpfEasyRandom implements Randomizer<String> {

	@Override
	public String getRandomValue() {
		return RandomStringUtils.randomNumeric(11);
	}
}
