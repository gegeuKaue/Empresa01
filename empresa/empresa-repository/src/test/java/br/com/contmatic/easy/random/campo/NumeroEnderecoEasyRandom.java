package br.com.contmatic.easy.random.campo;

import java.util.Random;

import org.jeasy.random.api.Randomizer;

public class NumeroEnderecoEasyRandom implements Randomizer<Integer> {

	@Override
	public Integer getRandomValue() {
		return (Integer) new Random().nextInt(999) + 1;
	}

}
