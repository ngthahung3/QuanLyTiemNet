package model;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MayTinh implements Serializable, Runnable{
	private String CPU;
	private String GPU;
	private String RAM;
	private String HardDrive;
	private boolean online = false;
	private String trangthai = "off";
	private boolean ketthuc;
	private long gia;
	private long giaDV = 0;
	private String timeTQ;
	
	private static final long serialVersionUID = 1;
	private List<DichVu> dichvus = new ArrayList<>();
	
	public MayTinh(String CPU, String GPU, String RAM, String HardDrive) {
		this.CPU = CPU;
		this.GPU = GPU;
		this.RAM = RAM;
		this.HardDrive = HardDrive;
	}

	public String getCPU() {
		return CPU;
	}

	public void setCPU(String cPU) {
		CPU = cPU;
	}

	public String getGPU() {
		return GPU;
	}

	public void setGPU(String gPU) {
		GPU = gPU;
	}

	public String getRAM() {
		return RAM;
	}

	public void setRAM(String rAM) {
		RAM = rAM;
	}

	public String getHardDrive() {
		return HardDrive;
	}

	public void setHardDrive(String hardDrive) {
		HardDrive = hardDrive;
	}

	public List<DichVu> getDichvus() {
		return dichvus;
	}

	public boolean isOnline() {
		return online;
	}
	
	public void setOnline(boolean online) {
		this.online = online;
	}
	
	public String isMTOnline() {
		if(online) {
			return "online";
		}else {
			return "offline";
		}
	}
	
	public String getTrangThai() {
		return trangthai;
	}
	
	public void setTrangThai() {
		if(online) {
			this.trangthai = "on";
		}else {
			this.trangthai = "off";
		}
	}
	
	public void batDau() {
		Thread thread = new Thread(this);
		thread.start();
		setOnline(true);
		setTrangThai();
	}	
	
	public void ketThuc() {
		ketthuc = true;
		setOnline(false);
		setTrangThai();
	}
	
	public String trangThaiOnline() {
		return "Thời gian:" + timeTQ + ", thành tiền: " + gia;
	}

	@Override
	public void run() {
		LocalTime batdau = LocalTime.now();
		while(!ketthuc) {
			LocalTime now = LocalTime.now();
			long duration = Duration.between(batdau, now).getSeconds();
			timeTQ = String.format("%d:%02d:%02d", duration / 3600, (duration % 3600) / 60, duration % 60);
			if ((duration % 3600) / 60 < 15) {
                gia = 3000;
            } else {
                gia = calculate(batdau, now);
            }
		}
		checkOut();
	}

	private long calculate(LocalTime batdau, LocalTime now) {
		Duration duration = Duration.between(batdau, now);
        Duration doan = Duration.ofMinutes(15);
        int doans = Math.toIntExact(duration.dividedBy(doan));
        long giaTrenPhut = 3000;
        return giaTrenPhut * doans;
	}
	
	private void checkOut() {
		System.out.println("Tiền máy:" + gia);
		if(!dichvus.isEmpty()) {
			for (DichVu dv : dichvus) {
                System.out.println(" + " + dv.getName());
                giaDV = giaDV+dv.getPrice();
            }
        }
        System.out.println("Tiền dịch vụ: " + giaDV);
        long tong = gia + giaDV;
        System.out.println("Tổng: " + tong);
        
        giaDV = 0;
        gia = 15000;
	
	}

	public String displayPC() {
		return "[CPU=" + getCPU() + ", GPU=" + getGPU() + ", RAM=" + getRAM() + ", HardDrive=" + getHardDrive() + "]";
	}
	
	
}
