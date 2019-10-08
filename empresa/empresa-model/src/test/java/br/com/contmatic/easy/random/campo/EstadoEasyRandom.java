package br.com.contmatic.easy.random.campo;

import java.util.Random;

import org.jeasy.random.api.Randomizer;

import br.com.caelum.stella.type.Estado;

public class EstadoEasyRandom implements Randomizer<Estado> {

	@Override
	public Estado getRandomValue() {
		return Estado.values()[new Random().nextInt(new Random().nextInt(Estado.values().length - 1))];
	}

}
