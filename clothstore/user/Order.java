package clothstore.user;

import java.io.Serializable;

public class Order implements Serializable{
	
	private User user;
	private String orderdate;
	private String code;
	
	public Order(User user, String orderdate) {
		super();
		this.user = user;
		this.orderdate = orderdate;
		this.code = null;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
	
}
