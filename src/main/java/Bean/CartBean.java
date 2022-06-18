package Bean;

public class CartBean {
  
	private int id;
	private int custId;
	private int prodId;

	private UserLoginBean customer = new UserLoginBean();
	private ViewbookBean product = new ViewbookBean();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public int getProdId() {
		return prodId;
	}

	public void setProdId(int prodId) {
		this.prodId = prodId;
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
