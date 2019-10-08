package br.com.contmatic.fixture;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.telefone.Telefone;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

public class FixtureEmpresa {
	public static void fixture() {
		Fixture.of(Empresa.class).addTemplate("empresa", new Rule() {
			{

				add("nome", regex("\\w{" + (new Random().nextInt(10) + 10) + "}"));
				add("email", "${nome}@gmail.com");
				add("cnpj", random("78434004000175", "41285742000198", "52643314000106", "80861671000186"));
				FixtureEndereco.fixture();
				Set<Endereco> enderecos = new HashSet<Endereco>();
				enderecos.add((Endereco) Fixture.from(Endereco.class).gimme("endereco"));
				enderecos.add((Endereco) Fixture.from(Endereco.class).gimme("endereco"));
				add("enderecos", enderecos);

				FixtureFuncionario.fixture();
				List<Funcionario> funcionarios = new ArrayList<Funcionario>();

				funcionarios.add((Funcionario) Fixture.from(Funcionario.class).gimme("funcionario"));
				funcionarios.add((Funcionario) Fixture.from(Funcionario.class).gimme("funcionario"));
				funcionarios.add((Funcionario) Fixture.from(Funcionario.class).gimme("funcionario"));
				funcionarios.add((Funcionario) Fixture.from(Funcionario.class).gimme("funcionario"));
				funcionarios.add((Funcionario) Fixture.from(Funcionario.class).gimme("funcionario"));
				funcionarios.add((Funcionario) Fixture.from(Funcionario.class).gimme("funcionario"));
				funcionarios.add((Funcionario) Fixture.from(Funcionario.class).gimme("funcionario"));

				Set<Telefone> telefones = new HashSet<Telefone>();
				FixtureTelefone.fixture();

				telefones.add((Telefone) Fixture.from(Telefone.class).gimme("telefone"));
				telefones.add((Telefone) Fixture.from(Telefone.class).gimme("telefone"));
				telefones.add((Telefone) Fixture.from(Telefone.class).gimme("telefone"));
				telefones.add((Telefone) Fixture.from(Telefone.class).gimme("telefone"));

				add("funcionarios", funcionarios);
				add("url", "https://${nome}.com");
			}
		});
	}

	public static void main(String[] args) {
		fixture();
		System.out.println("sss");
		Empresa empresa = Fixture.from(Empresa.class).gimme("empresa");
		System.out.println(empresa);
	}
}
