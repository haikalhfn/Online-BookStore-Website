package Bean;

public class OrderBean {

	private int orderid;
	private String ordername;
	private String email;
	private double total;
	private int userid;
	private int bookid;
	private int quantity;
	private String date;
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	private UserLoginBean customer = new UserLoginBean();
	private ViewbookBean product = new ViewbookBean();
	
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public String getOrdername() {
		return ordername;
	}
	public void setOrdername(String ordername) {
		this.ordername = ordername;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getBookid() {
		return bookid;
	}
	public void setBookid(int bookid) {
		this.bookid = bookid;
	}
	
	
	
	
	public UserLoginBean getCustomer() {
		return customer;
	}
	public void setCustomer(UserLoginBean customer) {
		this.customer = customer;
	}
	public ViewbookBean getProduct() {
		return product;
	}
	public void setProduct(ViewbookBean product) {
		this.product = product;
	}
	
	
}
