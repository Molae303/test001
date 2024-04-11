package product;

import java.io.Serializable;

public class ShopList implements Comparable<ShopList> ,Serializable{

	private Cloth cloth;
	private int clothStack;

	public ShopList(Cloth cloth, int clothStack) {
		super();
		this.cloth = cloth;
		this.clothStack = clothStack;
	}

	public Cloth getCloth() {
		return cloth;
	}

	public void setCloth(Cloth cloth) {
		this.cloth = cloth;
	}

	public int getClothStack() {
		return clothStack;
	}

	public void setClothStack(int clothStack) {
		this.clothStack = clothStack;
	}

	public void printShopList() {
		System.out.print(this.cloth.getCategory()+"\t");
		System.out.print(this.cloth.getCode()+"\t\t");
		System.out.print(this.cloth.getName()+"\t\t");
		if (this.clothStack <= 0) {
			System.out.println("품절");
		} else {
			System.out.println(cloth.getPrice());
		}
	}
	
	public void printShopListForAdmin() {
		
		System.out.print(this.cloth.getCode() + "\t\t");
		System.out.print(this.cloth.getCategory() + "\t");
		System.out.print(this.cloth.getName() + "\t\t");
		System.out.print(this.cloth.getPrice() + "\t");
		System.out.println(this.clothStack);
	}

	@Override
	public int compareTo(ShopList o) {
		return o.getCloth().getCategory().compareToIgnoreCase(this.cloth.getCategory());
	}

}
