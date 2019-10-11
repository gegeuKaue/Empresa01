package br.com.contmatic.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.bson.Document;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.endereco.Bairro;
import br.com.contmatic.endereco.Cidade;
import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.endereco.Estado;
import br.com.contmatic.telefone.Telefone;
import br.com.contmatic.telefone.TelefoneDDD;

public class Selecionar {

	public List<Empresa> selecionar(Empresa empresa) throws IOException {
		Conexao conexao = Conexao.getInstance();
		MongoDatabase database = conexao.getDatabase();

		List<Empresa> empresas = new LinkedList<Empresa>();
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.append("_id", empresa.getCnpj());
		FindIterable<Document> find = database.getCollection("empresa").find(whereQuery);
		List<Funcionario> funcionarioEmpresa = new LinkedList<Funcionario>();

		for (Document empre : find) {
			empresa.setNome(empre.getString("nome"));
			empresa.setEmail(empre.getString("email"));
			empresa.setUrl(empre.getString("url"));
			empresa.setCnpj(empre.getString("_id"));

			Set<Endereco> enderecos = new HashSet<Endereco>();
			for (Document endereco : empre.getList("enderecos", Document.class)) {
				Endereco end = new Endereco();
				end.setCep(endereco.getString("cep"));
				end.setNumero(endereco.getInteger("numero"));
				Document cidade = endereco.get("cidade", Document.class);
				Cidade cid = new Cidade();
				cid.setNome(cidade.getString("nome"));
				cid.setEstado(Estado.valueOf(((Document) cidade.get("estado")).getString("name")));

				List<Bairro> bairros = new ArrayList<Bairro>();
				for (Document bairro : cidade.getList("bairro", Document.class)) {
					Bairro bar = new Bairro();
					bar.setBairro(bairro.getString("bairro"));
					bairros.add(bar);
				}
				cid.setBairro(bairros);
				end.setCidade(cid);
				enderecos.add(end);
			}
			
			empresa.setEnderecos(enderecos);

			for (Document funcio : empre.getList("funcionarios", Document.class)) {
				Funcionario funcionario = new Funcionario();
				funcionario.setNome(funcio.getString("nome"));
				funcionario.setEmail(funcio.getString("email"));
				funcionario.setCpf(funcio.getString("cpf"));
				funcionario.setIdade(funcio.getInteger("idade"));

				funcionario.setEntrada(LocalTime.parse(funcio.getString("entrada")));
				funcionario.setSaida(LocalTime.parse(funcio.getString("saida")));
				funcionario.setDataContratacao(LocalDate.parse(funcio.getString("dataContratacao")));
				
				// funcionario.setDataContratacao(new
				// LocalDate(funcio.getDate("entrada").toString()));
				funcionario.setCargo(funcio.getString("cargo"));
				Set<Telefone> telefones = new HashSet<Telefone>();

				for (Document telefone : funcio.getList("telefones", Document.class)) {
					Telefone tel = new Telefone();
					tel.setDdd(TelefoneDDD.valueOf(((Document) (telefone.get("ddd"))).getString("name")));
					tel.setNumero(telefone.getString("numero"));
					telefones.add(tel);
				}
				funcionario.setTelefones(telefones);
				funcionarioEmpresa.add(funcionario);
			}
			empresa.setFuncionarios(funcionarioEmpresa);
			empresas.add(empresa);
		}
		conexao.close();
		return empresas;

	}

	public static void main(String[] args) throws IOException {
		Empresa empresa = new Empresa();
		empresa.setCnpj("54581050332999");
		System.out.print(new Selecionar().selecionar(empresa));
		System.out.print(new Selecionar().selecionar(empresa));
	}
}
