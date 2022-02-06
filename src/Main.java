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
			System.out.println("2- Sair");
			r = scan.nextInt();
		}while(r != 0);
		travel.cadastro(scan);
		
	}

}
