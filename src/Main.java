import java.util.Scanner;

import travelServices.Travel;

public class Main {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		Travel travel = new Travel();
		int r;
		do {
			System.out.println("1- Cadastrar");
			System.out.println("2- Agendar Viagem");
			System.out.println("3- Editar dados do Cadastro");
			System.out.println("4- Editar Voo");
			System.out.println("5- Sair");
			r = scan.nextInt();
			switch(r) {
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
				System.out.println("Estamos trabalhando nisso.");
				break;
				
			case 5:
				r = 5;
				break;
			default:
				System.out.println("Opção invalida");
				break;
			}	
		}while(r != 5);
		scan.close();
		System.out.println("Volte Sempre!");
	}

}
