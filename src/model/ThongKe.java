package model;

import java.io.Serializable;
import java.time.LocalDate;

public class ThongKe implements Serializable{
	private final LocalDate date;
	private long thunhap = 0;
	
	private static final long serialVersionUID = 1;

	public ThongKe(LocalDate date, long thunhap) {
		this.date = date;
		this.thunhap = thunhap;
	}

	public ThongKe(LocalDate date) {
		this.date = date;
	}

	public long getThunhap() {
		return thunhap;
	}

	public void setThunhap(long thunhap) {
		this.thunhap += thunhap;
	}

	public LocalDate getDate() {
		return date;
	}

	@Override
	public String toString() {
		return "ThongKe [Ngày: " + date + ", Doanh thu: " + thunhap + "]";
	}
	
	
}
