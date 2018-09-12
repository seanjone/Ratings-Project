package ratings.ratables;

public class Product extends Ratable{
	private String amazonID;
	
	public Product(String amazonID) {
		this.amazonID = amazonID;
	}
	@Override
	public String getID() {
		return this.amazonID;
	}

	@Override
	public String getLink() {
		return  "https://www.amazon.com/dp/" + this.amazonID;
	}

	@Override
	public String getDescription() {
		return this.amazonID;
	}
	
}
