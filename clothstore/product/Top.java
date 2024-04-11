package clothstore.product;

import java.io.Serializable;

public class Top extends Cloth implements Serializable{

	private int codeCount = 1000;

	public Top(String name, int price) {
		super(name, price);
		this.setCategory("TOP");
		this.setCode("top" + codeCount);
	}

	public int getCodeCount() {
		return this.codeCount;
	}

	public void setCodeCount(int codeCount) {
		this.codeCount = codeCount;
	}


	

	
}
