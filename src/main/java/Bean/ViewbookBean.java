package Bean;

public class ViewbookBean {
	private int id;
	private String name;
	private double price;
	private  int quantity;
	private String category;
	private String description;
	private String publish;
	private String image;
	private int Adminid;
	
   
	private AdminLoginBean supplier = new AdminLoginBean();


	public AdminLoginBean getSupplier() {
		return supplier;
	}
	public void setSupplier(AdminLoginBean supplier) {
		this.supplier = supplier;
	}
	public int getAdminid() {
		return Adminid;
	}
	public void setAdminid(int adminid) {
		Adminid = adminid;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getPublish() {
		return publish;
	}
	public void setPublish(String publish) {
		this.publish = publish;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
