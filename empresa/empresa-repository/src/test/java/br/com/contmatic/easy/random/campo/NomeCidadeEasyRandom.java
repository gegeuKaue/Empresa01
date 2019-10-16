package br.com.contmatic.easy.random.campo;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.jeasy.random.api.Randomizer;

public class NomeCidadeEasyRandom implements Randomizer<String> {
	List<String> cidades = Arrays.asList("Itaquaquecetuba", "Itaquera", "Suzano", "Mogi das Cruzes", "Aruja", "Poá");

	@Override
	public String getRandomValue() {
		return cidades.get(new Random().nextInt(cidades.size() - 1));
	}
}
