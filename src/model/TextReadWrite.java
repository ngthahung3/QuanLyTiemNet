package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class TextReadWrite {
	public static void writeNV(String path, List<NhanVien> nhanvien) {
		try {
			FileOutputStream fos = new FileOutputStream(path, false);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(nhanvien);
            oos.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<NhanVien> readNV(String path){
		ArrayList<NhanVien> nhanvien = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            nhanvien = (ArrayList<NhanVien>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            System.out.println("Không tìm thấy file hoặc file rỗng");
        }
        return nhanvien;
	}
	
	public static void writePC(String path, List<MayTinh> maytinh) {
		try {
			FileOutputStream fos = new FileOutputStream(path, false);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(maytinh);
            oos.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<MayTinh> readPC(String path){
		ArrayList<MayTinh> maytinh = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            maytinh = (ArrayList<MayTinh>) ois.readObject();
            ois.close();
        } catch (Exception ex) {
            System.out.println("Không tìm thấy file hoặc file rỗng");
        }
        return maytinh;
	}
	
	public static void writeService(String path, List<DichVu> dichvu) {
		try {
			FileOutputStream fos = new FileOutputStream(path, false);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(dichvu);
            oos.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<DichVu> readService(String path){
		ArrayList<DichVu> dichvu = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            dichvu = (ArrayList<DichVu>) ois.readObject();
            ois.close();
        } catch (Exception ex) {
            System.out.println("Không tìm thấy file hoặc file rỗng");
        }
        return dichvu;
	}
	
	public static void writeReport(String path, List<ThongKe> thongke) {
		try {
            FileOutputStream fos = new FileOutputStream(path, false);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(thongke);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static ArrayList<ThongKe> readReport(String path){
		ArrayList<ThongKe> tk = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            tk = (ArrayList<ThongKe>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            System.out.println("Không tìm thấy file hoặc file rỗng");
        }
        return tk;
	}
}
