package product;

import java.io.Serializable;

public class Pants extends Cloth implements Serializable{

	private static int codeCount = 2000;
	
	public Pants(String name, int price) {
		super(name, price);
		this.setCategory("PANTS");
		this.setCode("pan" + codeCount);
	}

	public static int getCodeCount() {
		return codeCount;
	}

	public static void setCodeCount(int codeCount) {
		Pants.codeCount = codeCount;
	}

	
}
