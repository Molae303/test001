package product;

import java.io.Serializable;
import java.util.Objects;

public class Cloth implements Serializable{
	
	private String code;
	private String category;
	private String name;
	private int price;
	
	public Cloth(String name, int price) {
		this.name = name;
		this.price = price;
		this.category = null;
		this.code = null;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		return Objects.hash(category, code);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cloth other = (Cloth) obj;
		return Objects.equals(category, other.category) && Objects.equals(code, other.code);
	}
	
	
}
