package model;

import java.io.Serializable;

public class DichVu implements Serializable{
	private String name;
	private long price;
	private static final long serialVersionUID = 1;
	public DichVu(String name, long price) {
		this.name = name;
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "DichVu [name=" + name + ", price=" + price + "]";
	}
	
	
}
