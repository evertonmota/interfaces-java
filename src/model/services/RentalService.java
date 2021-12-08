package model.services;

import model.entities.CarRental;
import model.entities.Invoice;

public class RentalService {

	private Double pricePerHour;
	private Double pricePerDay;
	
	private TaxService taxService;
	
	public RentalService(Double pricePerHour, Double pricePerDay, TaxService taxService) {
		super();
		this.pricePerHour = pricePerHour;
		this.pricePerDay = pricePerDay;
		this.taxService = taxService;
	}

	// Será responsável de Gerar a nota de pagamentos do carRental.
	public void processInvoice(CarRental carRental) {
	
		// Recuperar a Data em Milissegundos.
		long t1 =  carRental.getStart().getTime();
		long t2 = carRental.getFinish().getTime();
		
		// achar a diferença em milisegundos para segundos , minutos e horas;
		double hours = (double) (t2 - t1) / 1000 / 60 / 60;
		
		double basicPayment; 
		if(hours <= 12.0) {
			basicPayment = Math.ceil(hours) * pricePerHour;
		}else {
			basicPayment = Math.ceil(hours / 24) * pricePerDay;
		}
		
		double tax = taxService.tax(basicPayment);
		
		carRental.setInvoice(new Invoice(basicPayment, tax));
	}
}
