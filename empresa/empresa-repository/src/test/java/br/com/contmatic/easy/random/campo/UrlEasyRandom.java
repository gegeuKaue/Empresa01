package br.com.contmatic.easy.random.campo;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.jeasy.random.api.Randomizer;

public class UrlEasyRandom implements Randomizer<String> {

	@Override
	public String getRandomValue() {
		return "http:\\\\www." + RandomStringUtils.randomAlphabetic(new Random().nextInt(18) + 10) + ".com.br";
	}
}
