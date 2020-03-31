package Sales_Tax;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class SalesTax {
	static double totalPriceOfProducts = 0.00;

	static class Products {
		String prodName;
		String prodCategory;
		double prodPrice;
		boolean isImported;

		public Products(String prodCategory, double prodPrice, String prodName, boolean isImported) {
			super();
			this.prodCategory = prodCategory;
			this.prodPrice = prodPrice;
			this.prodName = prodName;
			this.isImported = isImported;
		}

		public String getProdCategory() {
			return prodCategory;
		}

		public void setProdCategory(String prodCategory) {
			this.prodCategory = prodCategory;
		}

		public double getProdPrice() {
			return prodPrice;
		}

		public void setProdPrice(double prodPrice) {
			this.prodPrice = prodPrice;
		}

		public String getProdName() {
			return prodName;
		}

		public void setProdName(String prodName) {
			this.prodName = prodName;
		}

		public boolean isImported() {
			return isImported;
		}

		public void setEmported(boolean isImported) {
			this.isImported = isImported;
		}

		public static void computeSalesTax(List<Products> listOfProducts) {
			double priceWithTaxes = 0.00;
			double productPrice = 0.00;
			double salesTax;
			double oldTotal = 0.0;
			DecimalFormat df = new DecimalFormat("0.00");

			for (Products product : listOfProducts) {
				productPrice = product.getProdPrice();
				oldTotal = productPrice + oldTotal;

				if (product.getProdCategory().equalsIgnoreCase("Book")
						|| product.getProdCategory().equalsIgnoreCase("Medical")
						|| product.getProdCategory().equalsIgnoreCase("Food")) {
					priceWithTaxes = productPrice;
				} else {

					priceWithTaxes = productPrice * 1.1;
				}

				if (product.isImported() == true) {
					priceWithTaxes = priceWithTaxes * 1.05;
				}

				priceWithTaxes = Double.parseDouble((df.format(priceWithTaxes)));
				totalPriceOfProducts = totalPriceOfProducts + priceWithTaxes;

				System.out.println(product.getProdName() + "" + ":" + priceWithTaxes);

			}
			salesTax = totalPriceOfProducts - oldTotal;
			salesTax = Double.parseDouble(df.format(salesTax));
			System.out.println("salesTax is :" + " " + salesTax);
		}

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			Scanner sc = new Scanner(System.in);
			Scanner sc1 = new Scanner(System.in);
			List<Products> listOfProducts = new ArrayList<Products>();
			String prodCategory;
			String prodName;
			boolean isImported;
			Double prodPrice;
			char choice = 'y';
			do {

				System.out.println("Enter Product Category::Food/Medical/Book/Others::");
				prodCategory = sc.nextLine();
				if (!(prodCategory.equalsIgnoreCase("Food") || (prodCategory.equalsIgnoreCase("Medical"))
						|| (prodCategory.equalsIgnoreCase("Book")) || (prodCategory.equalsIgnoreCase("others")))) {
					System.out.println("Please enter valid choice");
					continue;
				}
				System.out.println("Enter Product Name::");
				prodName = sc.nextLine();
				if (prodName.isEmpty()) {
					System.out.println("Please enter valid Product name ");
					continue;
				}
				System.out.println("Enter whether Product is Imported or not ?::true/false:: ");
				isImported = sc.nextBoolean();

				if (!(isImported == true || isImported == false)) {
					System.out.println("Please enter either true/false");
					continue;
				}

				System.out.println("Enter Product price::");
				prodPrice = sc.nextDouble();
				if (prodPrice.isNaN()) {
					System.out.println("Please enter valid price ");
					continue;
				}
				sc.nextLine();

				Products p = new Products(prodCategory, prodPrice, prodName, isImported);

				listOfProducts.add(p);
				System.out.println("Do you wish to add more Products ?\n Enter Y for yes or N for no: ");

				choice = sc1.next().charAt(0);
			} while (choice == 'y' || choice == 'Y');

			DecimalFormat df = new DecimalFormat("0.00");
			computeSalesTax(listOfProducts);
			System.out.println("Total price is :: " + df.format(totalPriceOfProducts));

		}

	}
}