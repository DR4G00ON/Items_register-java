package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.ImportedProduct;
import entities.Product;
import entities.UserProduct;

public class Program {

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		List<Product> list = new ArrayList<>();
		try(Scanner sc = new Scanner(System.in)){
			System.out.print("Enter the number of products: ");
			int n = sc.nextInt();
			
			for(int i=1;i<=n;i++) {
				System.out.println("Product #"+i+" data:");
				System.out.print("Common, used or imported (c/u/i)? ");
				char type = sc.next().charAt(0);
				sc.nextLine();
				System.out.print("Name: ");
				String name = sc.nextLine();
				System.out.print("Price: ");
				Double price = sc.nextDouble();
				if(type == 'c') {
					list.add(new Product(name, price));
				}
				else if(type == 'u') {
					System.out.print("Manufacture date (DD/MM/YYYY): ");
					LocalDate manufactureDate = LocalDate.parse(sc.next(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
					list.add(new UserProduct(name, price, manufactureDate));
				}
				else if(type == 'i') {
					System.out.print("Customs fee: ");
					double customFee = sc.nextDouble();
					list.add(new ImportedProduct(name, price, customFee));
				}
			}
			System.out.println("PRICE TAGS:");
			for(Product p : list) {
				System.out.println(p.priceTag());
			}
			
		}
	}

}
