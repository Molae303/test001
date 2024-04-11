package clothstore.user;

import java.io.Serializable;

public class Order implements Serializable{
	
	private User user;
	private String orderdate;
	
	public Order(User user, String orderdate) {
		super();
		this.user = user;
		this.orderdate = orderdate;
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
	
	
}
