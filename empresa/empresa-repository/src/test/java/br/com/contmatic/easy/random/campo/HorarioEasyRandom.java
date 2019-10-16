package br.com.contmatic.easy.random.campo;

import org.jeasy.random.api.Randomizer;
import org.joda.time.LocalTime;

public class HorarioEasyRandom implements Randomizer<LocalTime> {

	@Override
	public LocalTime getRandomValue() {
		return LocalTime.now();
	}

}
