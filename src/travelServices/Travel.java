package travelServices;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import connector.Conection;
import model.Destino;
import model.Pessoa;

public class Travel {

	public void cadastro(Scanner scan) {
		Scanner x = scan;

		System.out.println("Olá, bem vindo à Drake's Travel'o Light Agency");
		System.out.println("Vamos começar ?");
		System.out.println("Primeiro, precisamos saber qual o seu nome.");
		System.out.println("Nome:");
		String nome = x.nextLine();
		System.out.println("Qual o seu Sobrenome:");
		String sobrenome = x.nextLine();
		System.out.println("Email:");
		String email = x.nextLine();
		System.out.println("Senha (8 caractere):");
		String senha = x.nextLine();
		Pessoa pessoa1 = new Pessoa(nome, sobrenome, email, senha);

		String sql = "INSERT INTO Pessoa(Nome, Sobrenome, ano) VALUES(?, ?, ?, ?)";

		Connection conn = null;

		PreparedStatement pstm = null;
		try {
			conn = Conection.createConnection();
			pstm = conn.prepareStatement(sql);

			pstm.setString(1, pessoa1.getNome());
			pstm.setString(2, pessoa1.getSobrenome());
			pstm.setString(3, pessoa1.getEmail());
			pstm.setString(4, pessoa1.getSenha());

			pstm.execute();

			System.out.println("Pessoa Criada com sucesso!");

			pstm.close();
			conn.close();

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public void Agendar_Viagem(Scanner scan) throws Exception {
		Scanner x = scan;

		System.out.println("Pra onde você gostaria de viajar ?");
		String destino = x.nextLine();
		System.out.println("De onde você gostaria de viajar ? [ Origem ] ");
		String origem = x.nextLine();
		System.out.println("É uma viagem de ida e volta ?");
		Integer r;
		do
		{
			System.out.println("1- Ida\n2- Ida e Volta");
			r = x.nextInt();
		}
		while (r != 1 || r != 2);
		if(r == 1)
		{
			System.out.println("Em que data você gostaria de estar indo ? (dd-mm-aaaa");
			String data_ida = x.next();
			
	
			SimpleDateFormat objeto_de_formatacao = new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date date = objeto_de_formatacao.parse(data_ida);
			long ms = date.getTime();
			java.sql.Date Date_sql_data_ida = new java.sql.Date(ms);  
			
			Destino destino1 = new Destino(origem, destino, Date_sql_data_ida);
			
			String sql = "INSERT INTO Pessoa(Nome, Sobrenome, ano) VALUES(?, ?, ?)";

			Connection conn = null;

			PreparedStatement pstm = null;
			try {
				conn = Conection.createConnection();
				pstm = conn.prepareStatement(sql);

				pstm.setString(1, destino1.getOrigem());
				pstm.setString(2, destino1.getOrigem());
				pstm.setDate(3, destino1.getData_Ida());

				pstm.execute();

				System.out.println("Viagem agendada com sucesso!");

				pstm.close();
				conn.close();

			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
		else
		{
			System.out.println("Em que data você gostaria de estar indo ? (dd-mm-aaaa");
			String data_ida = x.next();
			//Formantando a data de ida
			SimpleDateFormat objeto_de_formatacao = new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date date = objeto_de_formatacao.parse(data_ida);
			long ms = date.getTime();
			java.sql.Date Date_sql_data_ida = new java.sql.Date(ms);  
			
			System.out.println("Em que data você gostaria de estar voltando ? (dd-mm-aaaa");
			String data_volta = x.next();
			//Formantando a data de Volta
			SimpleDateFormat objeto_de_formatacao2 = new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date date2 = objeto_de_formatacao2.parse(data_volta);
			long ms2 = date2.getTime();
			java.sql.Date Date_sql_data_volta = new java.sql.Date(ms2);
			
			
			Destino destino1 = new Destino(origem, destino, Date_sql_data_ida, Date_sql_data_volta);
			
			String sql = "INSERT INTO Destino(Nome, Sobrenome, ano) VALUES(?, ?, ?, ?)";

			Connection conn = null;

			PreparedStatement pstm = null;
			try {
				conn = Conection.createConnection();
				pstm = conn.prepareStatement(sql);

				pstm.setString(1, destino1.getOrigem());
				pstm.setString(2, destino1.getOrigem());
				pstm.setDate(3, destino1.getData_Ida());
				pstm.setDate(4, destino1.getData_Volta());
				
				pstm.execute();

				System.out.println("Viagem agendada com sucesso!");

				pstm.close();
				conn.close();

			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
	}
}
