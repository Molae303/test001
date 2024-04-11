package clothstore.product;

import java.io.Serializable;

public class Pants extends Cloth implements Serializable{

	private int codeCount = 2000;
	
	public Pants(String name, int price) {
		super(name, price);
		this.setCategory("PANTS");
		this.setCode("pan" + codeCount);
	}

	public int getCodeCount() {
		return this.codeCount;
	}

	public void setCodeCount(int codeCount) {
		this.codeCount = codeCount;
	}

	
}
