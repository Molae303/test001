package product;

import java.io.Serializable;

public class Top extends Cloth implements Serializable{

	private static int codeCount = 1000;

	public Top(String name, int price) {
		super(name, price);
		this.setCategory("TOP");
		this.setCode("top" + codeCount);
	}

	public static int getCodeCount() {
		return codeCount;
	}

	public static void setCodeCount(int codeCount) {
		Top.codeCount = codeCount;
	}


	

	
}
