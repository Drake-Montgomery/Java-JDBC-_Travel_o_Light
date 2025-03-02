import java.util.Scanner;

import travelServices.Travel;

public class Main {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		Travel travel = new Travel();
		int r;
		do {
			System.out.println("============================");
			System.out.println("\t    MENU");
			System.out.println("============================");
			System.out.println("1- Cadastrar.");
			System.out.println("2- Agendar Viagem.");
			System.out.println("3- Editar dados do Cadastro.");
			System.out.println("4- Seus dados.");
			System.out.println("5- Editar Voo.");
			System.out.println("6- Sair.");
			r = scan.nextInt();
			switch (r) {
			case 1:
				travel.Cadastro(scan);
				break;

			case 2:
				try {
					travel.Agendar_Viagem(scan);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;

			case 3:
				travel.Editar_Cadastro(scan);
				break;
				
			case 4:
				travel.Seus_dados(scan);
				break;
				
			case 5:
				System.out.println("Estamos trabalhando nisso.");
				break;

			case 6:
				r = 6;
				break;
				
			default:
				System.out.println("Op��o invalida");
				break;
			}
		} while (r != 6);
		scan.close();
		System.out.println("Volte Sempre!");
	}

}
