package clothstore.user;

import java.io.Serializable;
import java.util.ArrayList;

import clothstore.product.Cloth;
import clothstore.product.ShopList;

public class Bag implements Serializable {

	private ArrayList<BagProduct> bagList;
	private int totalPrice;

	public Bag() {
		this.bagList = new ArrayList<>();
		this.totalPrice = 0;
	}

	public ArrayList<BagProduct> getBagList() {
		return bagList;
	}

	public void setBagList(ArrayList<BagProduct> bagList) {
		this.bagList = bagList;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void insertBag(Cloth cloth, int quantity) {
		boolean flag = false;
		for (BagProduct data : bagList) {
			if (data.getClothCode().equals(cloth.getCode())) {
				data.setQuantity(data.getQuantity() + quantity);
				flag = true;
			}
		}
		if (!flag) {
			bagList.add(new BagProduct(cloth, quantity));
		}

	}

	public void updateTotalPrice() {
		int sum = 0;
		for (BagProduct data : bagList) {
			sum += (data.getBagCloth().getPrice() * data.getQuantity());
		}
		this.totalPrice = sum;
	}
	
	public void showBagList() {
		System.out.println("\n카테고리\t상품코드\t상품명\t\t가격\t수량");
		for(BagProduct data : bagList) {
			data.printBagProduct();
		}
		this.updateTotalPrice();
		System.out.print("\n상품의 총가격 : " + this.totalPrice);
		System.out.println("\n");
	}

}
