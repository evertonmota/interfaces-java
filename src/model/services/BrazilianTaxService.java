package model.services;

public class BrazilianTaxService implements TaxService{

	//tipo primitivo
	// double porque sempre considero que vai ter um valor e que sempre irei retornar um valor tambem.
	public double tax(double amount) {
		
		if(amount <= 100.0) {
			return (amount * 0.2 ) ;
		}else {
			return (amount * 0.15) ;
		}
	}
}
