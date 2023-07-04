package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DangNhap {
	private static List<NhanVien> list = new ArrayList<>();
	private static final String admin = "admin";
	private static NhanVien user;
	
	
	
	public static List<NhanVien> getList() {
		return list;
	}



	public static void setList(List<NhanVien> list) {
		DangNhap.list = list;
	}



	public static NhanVien getUser() {
		return user;
	}



	public static void setUser(NhanVien user) {
		DangNhap.user = user;
	}



	public static void dangnhap() {
		Scanner input = new Scanner(System.in);
        boolean login = false;
        while (!login) {
            list = TextReadWrite.readNV("src/Files/NhanVien.txt");
            System.out.println("Đăng nhập vào hệ thống quản lý quán net");
            System.out.println("Username");
            String username = input.nextLine();
            System.out.println("Password");
            String password = input.nextLine();
            if (username.equalsIgnoreCase(admin) && password.equals(admin)) {
                user = new NhanVien(admin, admin);
                login = true;
                AdminMenu.menu();
            } else {
                for (NhanVien nv : list) {
                    if (nv.getUsername().equals(username) && nv.getPassword().equals(password)) {
                        user = nv;
                        login = true;
                        AdminMenu.menu();
                    }
                }
                System.out.println("Nhập sai username hoặc password");
            }
        }
	}
}
