package travelServices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import connector.Conection;
import model.Destino;
import model.Pessoa;

import java.sql.CallableStatement;

public class Travel {

	public void Cadastro(Scanner scan) {
		Scanner x = scan;
		Pessoa pessoa = new Pessoa();
		System.out.println("Olá, bem vindo à Drake's Travel'o Light Agency");
		System.out.println("Vamos começar ?");
		System.out.println("Primeiro, precisamos saber qual o seu nome.");
		x.nextLine();
		System.out.println("Nome:");
		pessoa.setNome(x.nextLine());
		System.out.println("Qual o seu Sobrenome:");
		pessoa.setSobrenome(x.nextLine());
		System.out.println("Email:");
		pessoa.setEmail(x.nextLine());
		System.out.println("Senha:");
		pessoa.setSenha(x.nextLine());

		String sql = "INSERT INTO Pessoa(Nome, Sobrenome, Email, Senha) VALUES(?, ?, ?, ?)";

		Connection conn = null;

		PreparedStatement pstm = null;
		try {
			conn = Conection.createConnection();
			pstm = conn.prepareStatement(sql);

			pstm.setString(1, pessoa.getNome());
			pstm.setString(2, pessoa.getSobrenome());
			pstm.setString(3, pessoa.getEmail());
			pstm.setString(4, pessoa.getSenha());

			pstm.execute();

			System.out.println("Pessoa Criada com sucesso!");

			pstm.close();
			conn.close();

		} catch (Exception ex) {
			System.out.println("Não foi possivel fazer o cadastro");
			System.out.println("O Erro foi\n:" + ex.getMessage());
		}
	}

	public void Agendar_Viagem(Scanner scan) throws Exception {
		Scanner x = scan;
		x.nextLine();
		System.out.println("Pra onde você gostaria de viajar ?");
		String var_destino = x.nextLine();
		System.out.println("De onde você gostaria de viajar ? [ Origem ] ");
		String origem = x.nextLine();
		Integer r;
		do {
			System.out.println("É uma viagem de ida e volta ?");
			System.out.println("1- Ida\n2- Ida e Volta");
			r = x.nextInt();
		} while (r != 1 && r != 2);
		if (r == 1) {
			System.out.println("Em que data você gostaria de estar indo ? (dd-mm-aaaa)");
			String data_ida = x.next();

			SimpleDateFormat objeto_de_formatacao = new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date date = objeto_de_formatacao.parse(data_ida);
			long ms = date.getTime();
			java.sql.Date Date_sql_data_ida = new java.sql.Date(ms);

			Destino destino = new Destino(origem, var_destino, Date_sql_data_ida);

			String sql = "INSERT INTO Destino(Origem, Destino, Data_Ida) VALUES(?, ?, ?)";

			Connection conn = null;

			PreparedStatement pstm = null;
			try {
				conn = Conection.createConnection();
				pstm = conn.prepareStatement(sql);

				pstm.setString(1, destino.getOrigem());
				pstm.setString(2, destino.getOrigem());
				pstm.setDate(3, destino.getData_Ida());

				pstm.execute();

				System.out.println("Viagem agendada com sucesso!");

				pstm.close();
				conn.close();

			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		} else {
			System.out.println("Em que data você gostaria de estar indo ? (dd-mm-aaaa");
			String data_ida = x.next();
			// Formantando a data de ida
			SimpleDateFormat objeto_de_formatacao = new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date date = objeto_de_formatacao.parse(data_ida);
			long ms = date.getTime();
			java.sql.Date Date_sql_data_ida = new java.sql.Date(ms);

			System.out.println("Em que data você gostaria de estar voltando ? (dd-mm-aaaa");
			String data_volta = x.next();
			// Formantando a data de Volta
			SimpleDateFormat objeto_de_formatacao2 = new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date date2 = objeto_de_formatacao2.parse(data_volta);
			long ms2 = date2.getTime();
			java.sql.Date Date_sql_data_volta = new java.sql.Date(ms2);

			Destino destino = new Destino(origem, var_destino, Date_sql_data_ida, Date_sql_data_volta);

			String sql = "INSERT INTO Destino(Origem, Destino, Data_Ida, Data_Volta) VALUES(?, ?, ?, ?)";

			Connection conn = null;

			PreparedStatement pstm = null;
			try {
				conn = Conection.createConnection();
				pstm = conn.prepareStatement(sql);

				pstm.setString(1, destino.getOrigem());
				pstm.setString(2, destino.getOrigem());
				pstm.setDate(3, destino.getData_Ida());
				pstm.setDate(4, destino.getData_Volta());

				pstm.execute();

				System.out.println("Viagem agendada com sucesso!");

				pstm.close();
				conn.close();

			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
	}

	public void Editar_Cadastro(Scanner scan) {
		Scanner x = scan;
		Pessoa pessoa = new Pessoa();
		Integer z;
		do {
			/*
			 * System.out.println("O que você gostaria de editar ?");
			 * System.out.println("1- Nome, Sobrenome, Email e senha.");
			 * System.out.println("2- Nome, Sobrenome e Email.");
			 * System.out.println("3- Nome, Sobrenome e senha.");
			 * System.out.println("4- Nome, Email e senha.");
			 * System.out.println("5- Nome e Sobrenome.");
			 * System.out.println("6- Nome e Senha.");
			 * System.out.println("7- Nome e Email.");
			 */
			System.out.println("8- Nome.");
			/*
			 * System.out.println("9- Sobrenome, Email e senha.");
			 * System.out.println("10- Sobrenome e Email.");
			 * System.out.println("11- Sobrenome e senha.");
			 * System.out.println("12- Sobrenome.");
			 * System.out.println("13- Email e senha."); System.out.println("14- Email.");
			 * System.out.println("15- Senha.");
			 */
			z = x.nextInt();
		} while (z <= 0 && z > 15);
		switch (z) {
		case 1:
			// Atuaçozar Nome, Sobrenome, Email e senha.
			/*
			 * System.out.println("Primeiro digite o seu nome atual:"); String nome_Antigo =
			 * x.nextLine(); x.nextLine(); System.out.println("Novo Nome:");
			 * pessoa.setNome(x.nextLine()); System.out.println("Novo Sobrenome:");
			 * pessoa.setSobrenome(x.nextLine()); System.out.println("Novo Email:");
			 * pessoa.setEmail(x.nextLine()); System.out.println("Nova Senha:");
			 * pessoa.setSenha(x.nextLine());
			 * 
			 * // String sql = "call Atualizar_Cadastro_1(?, ?, ?, ?, ?)"; String sql =
			 * "UPDATE Pessoa SET Nome = ?, Sobrenome = ?, Email= ?, Senha= ? WHERE Nome = ?"
			 * ;
			 * 
			 * Connection conn = null; PreparedStatement pstm = null;
			 * 
			 * try { conn = Conection.createConnection(); pstm = conn.prepareStatement(sql);
			 * 
			 * pstm.setString(5, nome_Antigo); pstm.setString(1, pessoa.getNome());
			 * pstm.setString(2, pessoa.getSobrenome()); pstm.setString(3,
			 * pessoa.getEmail()); pstm.setString(4, pessoa.getSenha());
			 * 
			 * pstm.execute();
			 * 
			 * System.out.println("Pessoa Atualizada com sucesso!");
			 * 
			 * pstm.close(); conn.close();
			 * 
			 * } catch (Exception ex) {
			 * System.out.println("Não foi possivel fazer a Atualização");
			 * System.out.println("O Erro foi\n:" + ex.getMessage()); }
			 */
			break;

		case 2:
			// Atualizar Nome, Sobrenome e Email.
			/*
			 * System.out.println("Primeiro digite o seu nome atual:"); nome_Antigo =
			 * x.nextLine(); x.nextLine(); System.out.println("Novo Nome:");
			 * pessoa.setNome(x.nextLine()); System.out.println("Novo Sobrenome:");
			 * pessoa.setSobrenome(x.nextLine()); System.out.println("Novo Email:");
			 * pessoa.setEmail(x.nextLine());
			 * 
			 * sql = "call Atualizar_Cadastro_2(?, ?, ?, ?)";
			 * 
			 * conn = null; pstm = null;
			 * 
			 * try { conn = Conection.createConnection(); pstm = conn.prepareStatement(sql);
			 * 
			 * pstm.setString(1, nome_Antigo); pstm.setString(2, pessoa.getNome());
			 * pstm.setString(3, pessoa.getSobrenome()); pstm.setString(4,
			 * pessoa.getEmail());
			 * 
			 * pstm.execute();
			 * 
			 * System.out.println("Pessoa Atualizada com sucesso!");
			 * 
			 * pstm.close(); conn.close();
			 * 
			 * } catch (Exception ex) {
			 * System.out.println("Não foi possivel fazer a Atualização");
			 * System.out.println("O Erro foi:" + ex.getMessage()); }
			 */
			break;

		case 3:
			// Atualizar Nome, Sobrenome e Senha.
			/*
			 * System.out.println("Primeiro digite o seu nome atual:"); nome_Antigo =
			 * x.nextLine(); x.nextLine(); System.out.println("Novo Nome:");
			 * pessoa.setNome(x.nextLine()); System.out.println("Novo Sobrenome:");
			 * pessoa.setSobrenome(x.nextLine()); System.out.println("Nova Senha:");
			 * pessoa.setSenha(x.nextLine());
			 * 
			 * sql = "call Atualizar_Cadastro_3(?, ?, ?, ?)";
			 * 
			 * conn = null; pstm = null;
			 * 
			 * try { conn = Conection.createConnection(); pstm = conn.prepareStatement(sql);
			 * 
			 * pstm.setString(1, nome_Antigo); pstm.setString(2, pessoa.getNome());
			 * pstm.setString(3, pessoa.getSobrenome()); pstm.setString(4,
			 * pessoa.getSenha());
			 * 
			 * pstm.execute();
			 * 
			 * System.out.println("Pessoa Atualizada com sucesso!");
			 * 
			 * pstm.close(); conn.close();
			 * 
			 * } catch (Exception ex) {
			 * System.out.println("Não foi possivel fazer a Atualização");
			 * System.out.println("O Erro foi\n:" + ex.getMessage()); }
			 */
			break;

		case 4:
			// Atualizar Nome e Email Senha.
			/*System.out.println("Primeiro digite o seu nome atual:");
			nome_Antigo = x.nextLine();
			x.nextLine();
			System.out.println("Novo Nome:");
			pessoa.setNome(x.nextLine());
			System.out.println("Novo Email:");
			pessoa.setEmail(x.nextLine());
			System.out.println("Nova Senha:");
			pessoa.setSenha(x.nextLine());

			sql = "call Atualizar_Cadastro_4(?, ?, ?, ?)";

			conn = null;
			pstm = null;

			try {
				conn = Conection.createConnection();
				pstm = conn.prepareStatement(sql);

				pstm.setString(1, nome_Antigo);
				pstm.setString(2, pessoa.getNome());
				pstm.setString(3, pessoa.getEmail());
				pstm.setString(4, pessoa.getSenha());

				pstm.execute();

				System.out.println("Pessoa Atualizada com sucesso!");

				pstm.close();
				conn.close();

			} catch (Exception ex) {
				System.out.println("Não foi possivel fazer a Atualização");
				System.out.println("O Erro foi\n:" + ex.getMessage());
			}*/
			break;
			
		case 5:
			// Atualizar Nome e Sobrenome.
			/*System.out.println("Primeiro digite o seu nome atual:");
			nome_Antigo = x.nextLine();
			x.nextLine();
			System.out.println("Novo Nome:");
			pessoa.setNome(x.nextLine());
			System.out.println("Novo Sobrenome:");
			pessoa.setSobrenome(x.nextLine());

			sql = "call Atualizar_Cadastro_5(?, ?, ?)";

			conn = null;
			pstm = null;

			try {
				conn = Conection.createConnection();
				pstm = conn.prepareStatement(sql);

				pstm.setString(1, nome_Antigo);
				pstm.setString(2, pessoa.getNome());
				pstm.setString(3, pessoa.getSobrenome());

				pstm.execute();

				System.out.println("Pessoa Atualizada com sucesso!");

				pstm.close();
				conn.close();

			} catch (Exception ex) {
				System.out.println("Não foi possivel fazer a Atualização");
				System.out.println("O Erro foi\n:" + ex.getMessage());
			}*/
			break;

		case 6:
			// Atualizar Nome, Sobrenome e senha.
			/*
			 * System.out.println("Primeiro digite o seu nome atual:"); nome_Antigo =
			 * x.nextLine(); x.nextLine(); System.out.println("Novo Nome:");
			 * pessoa.setNome(x.nextLine()); System.out.println("Nova Senha:");
			 * pessoa.setSenha(x.nextLine());
			 * 
			 * sql = "call Atualizar_Cadastro_6(?, ?, ?)";
			 * 
			 * conn = null; pstm = null;
			 * 
			 * try { conn = Conection.createConnection(); pstm = conn.prepareStatement(sql);
			 * 
			 * pstm.setString(1, nome_Antigo); pstm.setString(2, pessoa.getNome());
			 * pstm.setString(3, pessoa.getSenha());
			 * 
			 * 
			 * pstm.execute();
			 * 
			 * System.out.println("Pessoa Atualizada com sucesso!");
			 * 
			 * pstm.close(); conn.close();
			 * 
			 * } catch (Exception ex) {
			 * System.out.println("Não foi possivel fazer a Atualização");
			 * System.out.println("O Erro foi\n:" + ex.getMessage()); }
			 */
			break;

		case 7:
			// Atualizar Nome, Sobrenome.
			/*
			 * System.out.println("Primeiro digite o seu nome atual:"); nome_Antigo =
			 * x.nextLine(); x.nextLine(); System.out.println("Novo Nome:");
			 * pessoa.setNome(x.nextLine()); System.out.println("Novo Email:");
			 * pessoa.setEmail(x.nextLine());
			 * 
			 * sql = "call Atualizar_Cadastro_7(?, ?, ?)";
			 * 
			 * conn = null; pstm = null;
			 * 
			 * try { conn = Conection.createConnection(); pstm = conn.prepareStatement(sql);
			 * 
			 * pstm.setString(1, nome_Antigo); pstm.setString(2, pessoa.getNome());
			 * pstm.setString(3, pessoa.getEmail());
			 * 
			 * 
			 * pstm.execute();
			 * 
			 * System.out.println("Pessoa Atualizada com sucesso!");
			 * 
			 * pstm.close(); conn.close();
			 * 
			 * } catch (Exception ex) {
			 * System.out.println("Não foi possivel fazer a Atualização");
			 * System.out.println("O Erro foi\n:" + ex.getMessage()); }
			 */
			break;

		case 8:
			// Atualizar Nome.
			System.out.println("Primeiro digite o seu nome atual:");
			String nome_Antigo = x.nextLine();
			x.nextLine();
			System.out.println("Novo Nome:");
			pessoa.setNome(x.nextLine());
			
//			sql = "{call Atualizar_Cadastro_8(?, ?)}";
//			String sql = "UPDATE pessoa SET Nome = ? WHERE Nome = ?";
			String sql = "{call Atualizar_Cadastro_8(?, ?)}";

			Connection conn = null;
			CallableStatement stmt = null;
			
			try {
				conn = Conection.createConnection();
				stmt = conn.prepareCall(sql);
				
				stmt.setString(1, pessoa.getNome());
				stmt.setString(2, nome_Antigo);
				stmt.execute();
//				Integer linha = cs.executeUpdate();
				System.out.println("Pessoa Atualizada com sucesso!");
				
//				System.out.printf("%d row(s) updated!", linha+"\n"); 
//				teste02	

				stmt.close();
				stmt.close();

				/*while (rs.next()) {
					   System.out.println(String.format("%s - %s",
					                      rs.getString("Nome") + " "
					                      + rs.getString("Sobrenome"),
					                      rs.getString("Email")));
					}*/

			} catch (Exception ex) {
				System.out.println("Não foi possivel fazer a Atualização");
				System.out.println("O Erro foi\n:" + ex.getMessage());
			}
			break;

		case 9:
			// Atualizar Sobrenome, Email e senha.
			/*
			 * System.out.println("Primeiro digite o seu nome atual:"); nome_Antigo =
			 * x.nextLine(); x.nextLine(); System.out.println("Novo Sobrenome:");
			 * pessoa.setSobrenome(x.nextLine()); System.out.println("Novo Email:");
			 * pessoa.setEmail(x.nextLine()); System.out.println("Nova Senha:");
			 * pessoa.setSenha(x.nextLine());
			 * 
			 * sql = "call Atualizar_Cadastro_9(?, ?, ?, ?)";
			 * 
			 * conn = null; pstm = null;
			 * 
			 * try { conn = Conection.createConnection(); pstm = conn.prepareStatement(sql);
			 * 
			 * pstm.setString(1, nome_Antigo); pstm.setString(2, pessoa.getSobrenome());
			 * pstm.setString(3, pessoa.getEmail()); pstm.setString(4, pessoa.getSenha());
			 * 
			 * pstm.execute();
			 * 
			 * System.out.println("Pessoa Atualizada com sucesso!");
			 * 
			 * pstm.close(); conn.close();
			 * 
			 * } catch (Exception ex) {
			 * System.out.println("Não foi possivel fazer a Atualização");
			 * System.out.println("O Erro foi\n:" + ex.getMessage()); }
			 */
			break;

		case 10:
			// Atualizar Sobrenome e Email.
			/*
			 * System.out.println("Primeiro digite o seu nome atual:"); nome_Antigo =
			 * x.nextLine(); x.nextLine(); System.out.println("Novo Sobrenome:");
			 * pessoa.setSobrenome(x.nextLine()); System.out.println("Novo Email:");
			 * pessoa.setEmail(x.nextLine());
			 * 
			 * sql = "call Atualizar_Cadastro_10(?, ?, ?)";
			 * 
			 * conn = null; pstm = null;
			 * 
			 * try { conn = Conection.createConnection(); pstm = conn.prepareStatement(sql);
			 * 
			 * pstm.setString(1, nome_Antigo); pstm.setString(2, pessoa.getSobrenome());
			 * pstm.setString(3, pessoa.getEmail());
			 * 
			 * pstm.execute();
			 * 
			 * System.out.println("Pessoa Atualizada com sucesso!");
			 * 
			 * pstm.close(); conn.close();
			 * 
			 * } catch (Exception ex) {
			 * System.out.println("Não foi possivel fazer a Atualização");
			 * System.out.println("O Erro foi\n:" + ex.getMessage()); }
			 */
			break;

		case 11:
			// Atualizar Sobrenome e senha.
			/*
			 * System.out.println("Primeiro digite o seu nome atual:"); nome_Antigo =
			 * x.nextLine(); x.nextLine(); System.out.println("Novo Sobrenome:");
			 * pessoa.setSobrenome(x.nextLine()); System.out.println("Nova Senha:");
			 * pessoa.setSenha(x.nextLine());
			 * 
			 * sql = "call Atualizar_Cadastro_11(?, ?, ?)";
			 * 
			 * conn = null; pstm = null;
			 * 
			 * try { conn = Conection.createConnection(); pstm = conn.prepareStatement(sql);
			 * 
			 * pstm.setString(1, nome_Antigo); pstm.setString(2, pessoa.getSobrenome());
			 * pstm.setString(3, pessoa.getSenha());
			 * 
			 * pstm.execute();
			 * 
			 * System.out.println("Pessoa Atualizada com sucesso!");
			 * 
			 * pstm.close(); conn.close();
			 * 
			 * } catch (Exception ex) {
			 * System.out.println("Não foi possivel fazer a Atualização");
			 * System.out.println("O Erro foi\n:" + ex.getMessage()); }
			 */
			break;

		case 12:
			// Atualizar Sobrenome.
			/*
			 * System.out.println("Primeiro digite o seu nome atual:"); nome_Antigo =
			 * x.nextLine(); x.nextLine(); System.out.println("Novo Sobrenome:");
			 * pessoa.setSobrenome(x.nextLine());
			 * 
			 * sql = "CALL Atualizar_Cadastro_12(?, ?)";
			 * 
			 * conn = null; pstm = null;
			 * 
			 * try { conn = Conection.createConnection(); pstm = conn.prepareStatement(sql);
			 * 
			 * pstm.setString(1, nome_Antigo); pstm.setString(2, pessoa.getSobrenome());
			 * 
			 * pstm.execute();
			 * 
			 * System.out.println("Pessoa Atualizada com sucesso!");
			 * 
			 * pstm.close(); conn.close();
			 * 
			 * } catch (Exception ex) {
			 * System.out.println("Não foi possivel fazer a Atualização");
			 * System.out.println("O Erro foi\n:" + ex.getMessage()); }
			 */
			break;

		case 13:
			// Atualizar Email e senha.
			/*
			 * System.out.println("Primeiro digite o seu nome atual:"); nome_Antigo =
			 * x.nextLine(); x.nextLine(); System.out.println("Novo Email:");
			 * pessoa.setEmail(x.nextLine()); System.out.println("Nova Senha:");
			 * pessoa.setSenha(x.nextLine());
			 * 
			 * sql = "call Atualizar_Cadastro_13(?, ?, ?)";
			 * 
			 * conn = null; pstm = null;
			 * 
			 * try { conn = Conection.createConnection(); pstm = conn.prepareStatement(sql);
			 * 
			 * pstm.setString(1, nome_Antigo); pstm.setString(2, pessoa.getEmail());
			 * pstm.setString(3, pessoa.getSenha());
			 * 
			 * pstm.execute();
			 * 
			 * System.out.println("Pessoa Atualizada com sucesso!");
			 * 
			 * pstm.close(); conn.close();
			 * 
			 * } catch (Exception ex) {
			 * System.out.println("Não foi possivel fazer a Atualização");
			 * System.out.println("O Erro foi\n:" + ex.getMessage()); }
			 */
			break;

		case 14:
			// Atualizar Email.
			/*
			 * System.out.println("Primeiro digite o seu nome atual:"); nome_Antigo =
			 * x.nextLine(); x.nextLine(); System.out.println("Novo Email:");
			 * pessoa.setEmail(x.nextLine());
			 * 
			 * sql = "CALL Atualizar_Cadastro_14(?, ?)";
			 * 
			 * conn = null; pstm = null;
			 * 
			 * try { conn = Conection.createConnection(); pstm = conn.prepareStatement(sql);
			 * 
			 * pstm.setString(1, nome_Antigo); pstm.setString(2, pessoa.getEmail());
			 * 
			 * pstm.execute();
			 * 
			 * System.out.println("Pessoa Atualizada com sucesso!");
			 * 
			 * pstm.close(); conn.close();
			 * 
			 * } catch (Exception ex) {
			 * System.out.println("Não foi possivel fazer a Atualização");
			 * System.out.println("O Erro foi\n:" + ex.getMessage()); }
			 */
			break;

		case 15:
			// Atualizar Senha.
			/*
			 * System.out.println("Primeiro digite o seu nome atual:"); nome_Antigo =
			 * x.nextLine(); x.nextLine(); System.out.println("Nova Senha:");
			 * pessoa.setSenha(x.nextLine());
			 * 
			 * sql = "call Atualizar_Cadastro_15(?, ?)";
			 * 
			 * conn = null; pstm = null;
			 * 
			 * try { conn = Conection.createConnection(); pstm = conn.prepareStatement(sql);
			 * 
			 * pstm.setString(1, nome_Antigo); pstm.setString(2, pessoa.getSenha());
			 * 
			 * pstm.execute();
			 * 
			 * System.out.println("Pessoa Atualizada com sucesso!");
			 * 
			 * pstm.close(); conn.close();
			 * 
			 * } catch (Exception ex) {
			 * System.out.println("Não foi possivel fazer a Atualização");
			 * System.out.println("O Erro foi\n:" + ex.getMessage()); }
			 */
			break;

		default:
			System.out.println("Opção invalida");
			break;
		}

	}
	
	public void Seus_dados(Scanner scan) {
		/*
		 * Scanner x = scan;
		 * 
		 * System.out.println("Qual o seu nome ?"); String nome = x.nextLine(); String
		 * sql = "SELECT * FROM Pessoa WHERE Nome ='?'";
		 * 
		 * Connection conn = null; PreparedStatement pstm = null;
		 * 
		 * try { conn = Conection.createConnection(); pstm = conn.prepareStatement(sql);
		 * 
		 * pstm.setString(1, nome);
		 * 
		 * pstm.execute();
		 * 
		 * System.out.println("Pessoa Atualizada com sucesso!");
		 * 
		 * pstm.close(); conn.close();
		 * 
		 * } catch (Exception ex) {
		 * System.out.println("Não foi possivel fazer a Atualização");
		 * System.out.println("O Erro foi\n:" + ex.getMessage()); }
		 */
	}
}
