package user;

import java.io.Serializable;
import java.util.ArrayList;

import product.Cloth;
import product.ShopList;

public class BagProduct implements Serializable {

	private Cloth bagCloth;
	private String clothCode;
	private int quantity;

	public BagProduct(Cloth bagCloth) {
		this.bagCloth = bagCloth;
		this.clothCode = bagCloth.getCode();
		this.quantity = 1;
	}

	public Cloth getBagCloth() {
		return bagCloth;
	}

	public void setBagCloth(Cloth bagCloth) {
		this.bagCloth = bagCloth;
	}

	public String getClothCode() {
		return clothCode;
	}

	public void setClothCode(String clothCode) {
		this.clothCode = clothCode;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void orderProtuct(ArrayList<ShopList> shopList) {
		
		for(ShopList data : shopList) {
			if(data.getCloth().equals(this.bagCloth)) {
				data.setClothStack(data.getClothStack()-this.quantity);
			}
		}
		
	}

	public void printBagProduct() {
		System.out.print(this.bagCloth.getCategory() + "\t");
		System.out.print(this.bagCloth.getCode() + "\t");
		System.out.print(this.bagCloth.getName() + "\t\t");
		System.out.print(this.bagCloth.getPrice() + "\t");
		System.out.println(this.quantity);

	}

}
