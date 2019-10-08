package br.com.contmatic.fixture;

import java.util.ArrayList;
import java.util.List;

import br.com.contmatic.endereco.Bairro;
import br.com.contmatic.endereco.Cidade;
import br.com.contmatic.endereco.Estado;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

public class FixtureCidade {
	public static void fixture() {
		Fixture.of(Cidade.class).addTemplate("cidade", new Rule() {
			{
				add("nome", random("Rua Sardônica", "Rua Farrapos", "Rua Duque de Caxias", "Rua Planalto",
						"Rua Jaguariun", "Rua Pacaembu"));
				add("estado", Estado.SP);
				List<Bairro> bairros = new ArrayList<Bairro>();
				Bairro bairro = new Bairro();
				bairro.setBairro("Estância Paraíso");
				bairros.add(bairro);
				bairro.setBairro("Vila Bartira");
				bairros.add(bairro);

				bairro.setBairro("Parque Recanto Mônica");
				bairros.add(bairro);

				bairro.setBairro("Jardim Anita");
				bairros.add(bairro);

				bairro.setBairro("Jardim Gonçalves");
				bairros.add(bairro);

				bairro.setBairro("Jardim Zélia");
				bairros.add(bairro);

				bairro.setBairro("Parque Novo Horizonte");
				bairros.add(bairro);
				add("bairro", bairros);
			}
		});
	}

}
