package br.com.contmatic.util;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;

import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.fixture.FixtureEmpresa;
import br.com.contmatic.util.Conexao;
import br.com.six2six.fixturefactory.Fixture;

public class InsercaoEmpresa {

	public static void main(String[] args) {
		FixtureEmpresa.fixture();
		Set<Empresa> empresas = new HashSet<Empresa>();

		String cnpj;
		Empresa empresa = null;

		while (empresas.size() < 200000) {
			cnpj = RandomStringUtils.randomNumeric(14);
			if (isCNPJ(cnpj)) {
				empresa = Fixture.from(Empresa.class).gimme("empresa");
				empresa.setCnpj(cnpj);
				empresas.add(empresa);
			}
		}
		System.out.println("Ola" + empresas.size());

		Conexao conexao = new Conexao();
		empresas.forEach(empr -> conexao.salvar(empr));

	}

	public static boolean isCNPJ(String CNPJ) {
		if (CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111") || CNPJ.equals("22222222222222")
				|| CNPJ.equals("33333333333333") || CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555")
				|| CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777") || CNPJ.equals("88888888888888")
				|| CNPJ.equals("99999999999999") || (CNPJ.length() != 14))
			return (false);

		char dig13, dig14;
		int sm, i, r, num, peso;

		try {
			sm = 0;
			peso = 2;
			for (i = 11; i >= 0; i--) {
				num = (int) (CNPJ.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso + 1;
				if (peso == 10)
					peso = 2;
			}
			r = sm % 11;
			if ((r == 0) || (r == 1))
				dig13 = '0';
			else
				dig13 = (char) ((11 - r) + 48);

			sm = 0;
			peso = 2;
			for (i = 12; i >= 0; i--) {
				num = (int) (CNPJ.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso + 1;
				if (peso == 10)
					peso = 2;
			}

			r = sm % 11;
			if ((r == 0) || (r == 1))
				dig14 = '0';
			else
				dig14 = (char) ((11 - r) + 48);
			if ((dig13 == CNPJ.charAt(12)) && (dig14 == CNPJ.charAt(13)))
				return (true);
			else
				return (false);
		} catch (InputMismatchException erro) {
			return (false);
		}
	}
}
