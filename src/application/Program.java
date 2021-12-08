package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilianTaxService;
import model.services.RentalService;

public class Program {

	public static void main(String[] args) throws ParseException{

		Locale.setDefault(Locale.US);
		
		Scanner sc =  new Scanner(System.in);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:ss");

		System.out.println("Enter Rental Data");
		
		//Modelo do carro
		System.out.print("Car Model :");
		String carModel = sc.nextLine();
		
		//Date de Retirada
		System.out.print("Pickup (dd/MM/yyyy hh:mm) :");
		Date start = sdf.parse(sc.nextLine());
		
		//Date de retorno do veiculo
		System.out.print("Return (dd/MM/yyyy hh:mm) :");
		Date finish = sdf.parse(sc.nextLine());
		
		//Obj de aluguel de carro
		CarRental cr = new CarRental(start, finish, new Vehicle(carModel));
		
		System.out.println("Enter Price per Hour : ");
		double pricePerHour = sc.nextDouble();
		
		System.out.println("Enter Price per Day : ");
		double pricePerDay = sc.nextDouble();


		//Vamos instanciar o serviço de aluguel.
		RentalService rentalService = new RentalService(pricePerDay, pricePerDay, new BrazilianTaxService() );
		
		rentalService.processInvoice(cr);
		
		
		System.out.println("INVOICE : ");
		
		System.out.println("Basic Paymnent : " + String.format("%.2f", cr.getInvoice().getBasicPayment()));
		
		System.out.println("Tax : " + String.format("%.2f", cr.getInvoice().getTax()));

		System.out.println("Total Paymnent : " + String.format("%.2f", cr.getInvoice().getTotalPayment()));
		
		sc.close();
	}

}
