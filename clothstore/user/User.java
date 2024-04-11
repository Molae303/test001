package clothstore.user;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable{
	
	private String id;
	private String pw;
	private String phone;
	private String name;
	private String address;
	private boolean isAdmin;
	private Bag bag;
	
	public User(String id, String pw, String name, String phone) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.phone = phone;
		this.isAdmin = false;
		this.bag = new Bag();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public Bag getBag() {
		return bag;
	}
	public void setBag(Bag bag) {
		this.bag = bag;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public void showUserInfo() {
		System.out.println("이름\t전화번호\t\t배송지");
		System.out.print(this.name+"\t");
		System.out.print(this.phone+"\t");
		System.out.println(this.address+"\t");
	}

	@Override
	public String toString() {
		return "로그인유저 [id=" + id +", name=" + name + ", phone=" + phone + ", address=" + address + "]";
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(id, phone);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id) && Objects.equals(phone, other.phone);
	}
	
	
	
}
