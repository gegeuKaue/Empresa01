package br.com.contmatic.easy.random.campo;

import org.apache.commons.lang3.RandomStringUtils;
import org.jeasy.random.api.Randomizer;

public class NumeroTelefoneEasyRandom implements Randomizer<String> {

	@Override
	public String getRandomValue() {
		return (9 + RandomStringUtils.randomNumeric(8));
	}

}
