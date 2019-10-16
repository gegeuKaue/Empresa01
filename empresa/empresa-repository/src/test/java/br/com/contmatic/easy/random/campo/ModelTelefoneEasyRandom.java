package br.com.contmatic.easy.random.campo;

import org.jeasy.random.api.Randomizer;

import br.com.contmatic.easy.random.TelefoneEasyRandom;
import br.com.contmatic.telefone.Telefone;

public class ModelTelefoneEasyRandom implements Randomizer<Telefone> {

	@Override
	public Telefone getRandomValue() {
		return TelefoneEasyRandom.telefone();
	}
}
