package travelServices;

import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import connector.Conection;
import model.Destino;
import model.Pessoa;

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
		System.out.println("Qual o seu nome ?");
//		String passageiro = x.nextLine();
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
				/* Agendando a viagem na tabela Destino */
				conn = Conection.createConnection();
				pstm = conn.prepareStatement(sql);

				pstm.setString(1, destino.getOrigem());
				pstm.setString(2, destino.getDestino());
				pstm.setDate(3, destino.getData_Ida());
				pstm.execute();
				
				System.out.println("Viagem agendada com sucesso!");
				
				pstm.close();
				conn.close();

			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		} else {
			System.out.println("Em que data você gostaria de estar indo ? (dd-mm-aaaa)");
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
		Integer a;
		Integer b;
		Integer m;
		do {
			System.out.println("============================");
			System.out.println("        EDITAR DADOS");
			System.out.println("============================");
			System.out.println("1- Alterar dados cadastrados.");
			System.out.println("2- Deletar dados cadastrados.");
			System.out.println("3- Voltar.");
			a = x.nextInt();
			if (a == 1) {
				do {
					System.out.println("===================================");
					System.out.println("         ESCOLHA UMA OPÇÃO         ");
					System.out.println("===================================");
					System.out.println("O que você gostaria de editar ?");
					System.out.println("1- Nome.");
					System.out.println("2- Sobrenome.");
					System.out.println("3- Email.");
					System.out.println("4- Senha.");
					System.out.println("5- Voltar.");
					z = x.nextInt();
					switch (z) {
					case 1:
						// Atualizar Nome.
						x.nextLine();
						System.out.println("Primeiro digite o seu nome atual:");
						String nome_Antigo = x.nextLine();
						System.out.println("Novo Nome:");
						pessoa.setNome(x.nextLine());

						System.out.println("=============================================");
						System.out.println("O Nome escolhido foi: " + pessoa.getNome());
						System.out.println("=============================================");

						String sql = "{call Atualizar_Cadastro_8(?, ?)}";

						Connection conn = null;
						PreparedStatement pstm = null;

						try {
							conn = Conection.createConnection();
							pstm = conn.prepareStatement(sql);

							pstm.setString(1, nome_Antigo);
							pstm.setString(2, pessoa.getNome());

							pstm.execute();

							System.out.println("Nome Atualizado com sucesso!");

							pstm.close();
							conn.close();

						} catch (Exception ex) {
							System.out.println("Não foi possivel fazer a Atualização");
							System.out.println("O Erro foi:\t" + ex.getMessage());
						}

						break;

					case 2:
						// // Atualizar Sobrenome.
						x.nextLine();
						System.out.println("Primeiro digite o seu nome atual:");
						nome_Antigo = x.nextLine();
						System.out.println("Novo Sobrenome:");
						pessoa.setSobrenome(x.nextLine());

						System.out.println("=============================================");
						System.out.println("O Sobrenome escolhido foi: " + pessoa.getSobrenome());
						System.out.println("=============================================");

						sql = "{call Atualizar_Cadastro_12(?, ?)}";

						conn = null;
						pstm = null;

						try {
							conn = Conection.createConnection();
							pstm = conn.prepareStatement(sql);

							pstm.setString(1, nome_Antigo);
							pstm.setString(2, pessoa.getSobrenome());

							pstm.execute();

							System.out.println("Sobrenome Atualizado com sucesso!");

							pstm.close();
							conn.close();

						} catch (Exception ex) {
							System.out.println("Não foi possivel fazer a Atualização");
							System.out.println("O Erro foi:\t" + ex.getMessage());
						}

						break;

					case 3:
						// Atualizar Email.
						x.nextLine();
						System.out.println("Primeiro digite o seu nome atual:");
						nome_Antigo = x.nextLine();
						System.out.println("Novo Email:");
						pessoa.setEmail(x.nextLine());

						System.out.println("=============================================");
						System.out.println("O Email escolhido foi: " + pessoa.getEmail());
						System.out.println("=============================================");

						sql = "{call Atualizar_Cadastro_14(?, ?)}";

						conn = null;
						pstm = null;

						try {
							conn = Conection.createConnection();
							pstm = conn.prepareStatement(sql);

							pstm.setString(1, nome_Antigo);
							pstm.setString(2, pessoa.getEmail());

							pstm.execute();

							System.out.println("Email Atualizado com sucesso!");

							pstm.close();
							conn.close();

						} catch (Exception ex) {
							System.out.println("Não foi possivel fazer a Atualização");
							System.out.println("O Erro foi:\t" + ex.getMessage());
						}
						break;

					case 4:
						// Atualizar Senha.
						x.nextLine();
						System.out.println("Primeiro digite o seu nome atual:");
						nome_Antigo = x.nextLine();
						System.out.println("Nova Senha:");
						pessoa.setSenha(x.nextLine());

						System.out.println("=============================================");
						System.out.println("O Email escolhido foi: " + pessoa.getSenha());
						System.out.println("=============================================");

						sql = "{call Atualizar_Cadastro_15(?, ?)}";

						conn = null;
						pstm = null;

						try {
							conn = Conection.createConnection();
							pstm = conn.prepareStatement(sql);

							pstm.setString(1, nome_Antigo);
							pstm.setString(2, pessoa.getSenha());

							pstm.execute();

							System.out.println("Senha Atualizada com sucesso!");

							pstm.close();
							conn.close();

						} catch (Exception ex) {
							System.out.println("Não foi possivel fazer a Atualização");
							System.out.println("O Erro foi:\t" + ex.getMessage());
						}
						break;
					case 5:
						break;
					default:
						System.out.println("Opção invalida");
						break;
					}
				} while (z != 5);
				

			} else if (a == 2) {
				do {
					System.out.println("============================");
					System.out.println("      DELETAR DADOS      ");
					System.out.println("============================");
					System.out.println("1- Nome, Sobrenome, email e Senha.");
					System.out.println("2- Voltar.");
					b = x.nextInt();
					if (b == 1) {
						do {
							System.out.println("============================");
							System.out.println("           CUIDADO!         ");
							System.out.println("============================");
							System.out.println(
									"Você está prestes a deletar todos os seus dados, Nome, Sobrenome, Email e Senha.\nVocê tem certeza ?");
							System.out.println("1- Sim.\n2- Não.");
							m = x.nextInt();
							if (m == 1) {
								// Deletar toda a linha.
								x.nextLine();
								System.out.println("Qual o seu nome ?");
								String nome = x.nextLine();
								String sql = "DELETE FROM Pessoa WHERE Nome like ?";

								Connection conn = null;
								PreparedStatement pstm = null;

								try {
									conn = Conection.createConnection();
									pstm = conn.prepareStatement(sql);

									pstm.setString(1, nome);

									pstm.execute();
									System.out.println("=======================================");
									System.out.println(" Dados Deletados com Sucesso!");
									System.out.println("Recomendamos fazer um novo cadastro.");
									System.out.println("=======================================");

									pstm.close();
									conn.close();

								} catch (Exception ex) {
									System.out.println("Não foi possivel fazer a Atualização");
									System.out.println("O Erro foi\n:" + ex.getMessage());
								}
								m = 2;
							}
							else {
							System.out.println("============");
							System.out.println("Voltando...");
							System.out.println("============");
							}
						} while (m != 1 && m != 2);
						}	 
				}while (b != 2);
			} 
		}while (a != 1 && a != 2 && a != 3);
}

	public void Seus_dados(Scanner scan) {
		Scanner x = scan;
		System.out.println("====================");
		System.out.println("     SEUS DADOS     ");
		System.out.println("====================");
		x.nextLine();
		System.out.println("Qual o seu nome ?");
		String nome = x.nextLine();
		Integer q;
		do {
			System.out.println("Você já agendou uma viagem ?\n"
					+ "1- Sim.\n"
					+ "2-Não.");
			q = x.nextInt();
		} while(q != 1 && q != 2);
		if(q == 1) {
			System.out.println("Pra onde você vai viajar ?");
			//String destino = x.nextLine();
			String sql = "SELECT * FROM Pessoa WHERE Nome like ?";

			Connection conn = null;
			PreparedStatement pstm = null;

			try {
				conn = Conection.createConnection();
				pstm = conn.prepareStatement(sql);

				pstm.setString(1, nome);

				ResultSet result = pstm.executeQuery();
				while (result.next()) {
					System.out.println("===================================");
					System.out.println("Nome:" + result.getString("Nome"));
					System.out.println("Sobrenome:" + result.getString("Sobrenome"));
					System.out.println("Email:" + result.getString("Email"));
					System.out.println("Senha:" + result.getString("Senha"));
					System.out.println("===================================");

					x.nextLine();
				}

				pstm.close();
				conn.close();

			} catch (Exception ex) {
				System.out.println("Não foi possivel fazer a Atualização");
				System.out.println("O Erro foi\n:" + ex.getMessage());
			}
		}
		else {
			String sql = "SELECT * FROM Pessoa WHERE Nome like ?";

			Connection conn = null;
			PreparedStatement pstm = null;

			try {
				conn = Conection.createConnection();
				pstm = conn.prepareStatement(sql);

				pstm.setString(1, nome);

				ResultSet result = pstm.executeQuery();
				while (result.next()) {
					System.out.println("===================================");
					System.out.println("Nome:" + result.getString("Nome"));
					System.out.println("Sobrenome:" + result.getString("Sobrenome"));
					System.out.println("Email:" + result.getString("Email"));
					System.out.println("Senha:" + result.getString("Senha"));
					System.out.println("===================================");

					x.nextLine();
				}

				pstm.close();
				conn.close();

			} catch (Exception ex) {
				System.out.println("Não foi possivel fazer a Atualização");
				System.out.println("O Erro foi\n:" + ex.getMessage());
			}
		}
	}
}
