package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuanNet {
	 private static List<MayTinh> dsMayTinh = new ArrayList<>();
	 private static List<DichVu> dsDichVu = new ArrayList<>();
	 private static List<ThongKe> thongke = new ArrayList<>();
	 
	 private static Scanner sc = new Scanner(System.in);
	 private static final String admin = "admin";
	 private static final String cancel = "cancel";
	 
	 public static List<ThongKe> getThongKe(){
		 return thongke;
	 }
	 
	 public static void themPC() {
		 try {
			 MayTinh pc = taoMoiPC();
			 if(pc == null) {
				 System.out.println("Thiếu linh kiện hoặc admin ko muốn thêm máy");
				 return;
			 }
			 dsMayTinh = TextReadWrite.readPC("src/Files/MayTinh.txt");
	         dsMayTinh.add(pc);
	         TextReadWrite.writePC("src/Files/MayTinh.txt", dsMayTinh);
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 
	 }
	 
	 private static MayTinh taoMoiPC() {
		String[] tenThanhPhan = {"CPU", "GPU", "RAM", "Ổ cứng"};
		String[] thanhPhan = new String[tenThanhPhan.length];
		for (int i = 0; i < tenThanhPhan.length; i++) {
			String hardware = getHardware("Nhập " + tenThanhPhan[i] + " :");
		        if (hardware.equals(cancel)) {
		            return null;
		        }
		    thanhPhan[i] = hardware;
		}
		return new MayTinh(thanhPhan[0], thanhPhan[1], thanhPhan[2], thanhPhan[3]);
	}
	 
	 private static String getHardware(String s) {
		String hardware;
		do {
			System.out.println(s);
		    hardware = sc.nextLine();
		} while (!hardware.matches("^\\w+"));
		    return hardware;
	}
	 
	 public static void hienThiTatCaMayTinh() {
		 try {
			 
			hienThiThongTin();
			hienThiChiTiet();
			 
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
	 }

	private static void hienThiChiTiet() {
		for(int i = 0; i < dsMayTinh.size(); i++) {
			System.out.println("Máy số: " + (i+1) + dsMayTinh.get(i).isMTOnline());
		}
		
	}

	private static void hienThiThongTin() {
		try {
            System.out.println("Bạn có muốn xem tình trạng máy tính không?");
            System.out.println("Bấm phím số 1 để xem tình trạng máy.");
            System.out.println("Bấm phím số 2 để thoát.");
            int choice = Integer.parseInt(sc.nextLine());
            if(choice == 1) {
            	hienThiTinhTrang();
            }
        } catch (Exception e) {
            System.out.println("Quay lại màn hình chính...");
        }
		
	}

	private static void hienThiTinhTrang() {
		try {
			System.out.println("Bấm phím 1 để xem cấu hình");
			System.out.println("Bám phím 2 để xem trạng thái bật-tắt");
			int choice = Integer.parseInt(sc.nextLine());
			switch(choice) {
				case 1: xemCauHinh();	break;
				case 2: xemOnOFF();	break;
			}
		}catch(Exception e) {
			System.out.println("Quay lại màn hình chính...");
		}
		
	}

	private static void xemOnOFF() {
		try {
            System.out.println("Nhập số của máy tính");
            int index = Integer.parseInt(sc.nextLine());
            if (dsMayTinh.get(index - 1).isOnline()) {
                System.out.println(dsMayTinh.get(index - 1).trangThaiOnline());
            } else {
                System.out.println(dsMayTinh.get(index - 1).isMTOnline());
            }
        } catch (Exception e) {
            System.out.println("Không tìm thấy máy");
        }
    }

	private static void xemCauHinh() {
		try {
            System.out.println("Nhập số của máy");
            int index = Integer.parseInt(sc.nextLine());
            System.out.println(dsMayTinh.get(index - 1).displayPC());
        } catch (Exception e) {
            System.out.println("Không tìm thấy máy");
        }
		
	}
	
	public static void nangMayTinh() {
		try {
			hienThiTatCaMayTinh();
			System.out.println("Nhập số của máy tính cần nâng cấp");
            int index = Integer.parseInt(sc.nextLine());
            System.out.println("Bạn muốn nâng cấp bộ phận nào của máy tính?");
            System.out.println("1. CPU");
            System.out.println("2. GPU");
            System.out.println("3. RAM");
            System.out.println("4. HardDriver");
            System.out.println("5. Toàn bộ máy");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1 : dsMayTinh.get(index - 1).setCPU(getHardware("Nhập CPU "));
                case 2 : dsMayTinh.get(index - 1).setGPU(getHardware("Nhập GPU"));
                case 3 : dsMayTinh.get(index - 1).setRAM(getHardware("Nhập RAM"));
                case 4 : dsMayTinh.get(index - 1).setHardDrive(getHardware("Nhập ổ cứng"));
                case 5 : dsMayTinh.set(index - 1, taoMoiPC());
            }
            System.out.println("Nâng cấp hoàn thành");
            TextReadWrite.writePC("src/Files/MayTinh.txt", dsMayTinh);
		}catch (Exception e) {
            System.out.println("Nâng cấp máy tính thất bại");
        }
	}

	public static void banMayTinh() {
		try {
            if (!DangNhap.getUser().getUsername().equalsIgnoreCase(admin)) {
                System.out.println("Bạn không phải admin nên không bán được");
                return;
            }
            hienThiTatCaMayTinh();
            System.out.println("Nhập số của máy cần bán");
            int index = Integer.parseInt(sc.nextLine());
            dsMayTinh.remove(index - 1);
            System.out.println("Đã bán máy");
            TextReadWrite.writePC("src/Files/MayTinh.txt", dsMayTinh);
        } catch (Exception e) {
            System.out.println("Không có máy hoặc file rỗng");
        }
	}
	
	public static void batMayTinh() {
		try {
			hienThiTatCaMayTinh();
            System.out.println("Nhập số của máy tính cần bật");
            int index = Integer.parseInt(sc.nextLine());
            dsMayTinh.get(index - 1).batDau();
            System.out.println("Máy đã được bật");
		}catch (Exception e) {
            System.out.println("Không bật được máy tính");
        }
	}
	
	public static void tinhTien() {
		try {
			hienThiTatCaMayTinh();
            System.out.println("Nhập số của máy tính cần tính tiền");
            int index = Integer.parseInt(sc.nextLine());
            dsMayTinh.get(index - 1).ketThuc();
            System.out.println("Máy đã được bật");
		}catch (Exception e) {
            System.out.println("Hủy tính tiền");
        }
	}
	
	public static void themMoiDichVu() {
		try {
            dsDichVu = TextReadWrite.readService("src/Files/DichVu.txt");
            DichVu dv = themDichVu();
            if (dv == null) {
                return;
            }
            dsDichVu.add(dv);
            TextReadWrite.writeService("src/Files/DichVu.txt", dsDichVu);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	private static DichVu themDichVu() {
		try {
            System.out.println("Nhập tên dịch vụ");
            String name = sc.nextLine();
            if (name.equals(cancel)) {
                System.out.println("Hủy quá trình thêm dịch vụ");
                return null;
            }
            System.out.println("Nhập giá tiền của dịch vụ");
            long price = Long.parseLong(sc.nextLine());
            return new DichVu(name, price);
        } catch (Exception e) {
            System.out.println("Hủy quá trình thêm dịch vụ");
        }
		return null;
	}
	
	public static void goiDichVU() {
		try {
			hienThiTatCaMayTinh();
			System.out.println("Nhập số của máy tính gọi dịch vụ");
            int index = Integer.parseInt(sc.nextLine());
            if (!dsMayTinh.get(index - 1).isOnline()) {
                System.out.println("Máy chưa được mở");
                return;
            }
            hienThiTatCaDichVu();
            System.out.println("Nhập dịch vụ (số thứ tự)");
            int index2 = Integer.parseInt(sc.nextLine());
            dsMayTinh.get(index - 1).getDichvus().add(dsDichVu.get(index2 - 1));
		} catch (Exception e) {
            System.out.println("Hủy gọi dịch vụ");
        }
	}

	private static void hienThiTatCaDichVu() {
		dsDichVu = TextReadWrite.readService("src/Files/DichVu.txt");
		if(dsDichVu.isEmpty()) {
			System.out.println("Dịch vụ này không có");
			return;
		}
		for(int i = 0; i < dsDichVu.size(); i++) {
			System.out.println("Dịch vụ số: " + (i+1) + dsDichVu.get(i));
		}
		
	}
	
	public static void suaDichVu() {
		try {
            hienThiTatCaDichVu();
            System.out.println("Nhập số của dịch vụ cần sửa");
            int index = Integer.parseInt(sc.nextLine());
            dsDichVu.set(index - 1, themDichVu());
            TextReadWrite.writeService("src/Files/Service.txt", dsDichVu);
        } catch (Exception e) {
            System.out.println("Hủy quá trình sửa dịch vụ");
        }
	}
	
	public static void xoaDichVu() {
		try {
			hienThiTatCaDichVu();
			System.out.println("Nhập số của dịch vụ cần xóa");
            int index = Integer.parseInt(sc.nextLine());
            dsDichVu.remove(index - 1);
            TextReadWrite.writeService("src/Files/DichVu.txt", dsDichVu);
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public static void themNhanVien() {
		try {
			if (!DangNhap.getUser().getUsername().equalsIgnoreCase(admin)) {
                System.out.println("Không phải chủ quán, không thể thêm tài khoản nhân viên");
                return;
            }
            DangNhap.setList(TextReadWrite.readNV("src/Files/NhanVien.txt"));
            NhanVien temp = taoNhanVien();
            if (temp == null) {
                System.out.println("Tạo người dùng thất bại");
                return;
            }
            DangNhap.getList().add(temp);
            TextReadWrite.writeNV("src/Files/NhanVien.txt", DangNhap.getList());
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public static NhanVien taoNhanVien() {
		System.out.println("Thêm một nhân viên mới");
        System.out.println("Nhập tên");
        String username = sc.nextLine();
        System.out.println("Nhập mật khẩu");
        String password = sc.nextLine();
        if (username.equalsIgnoreCase(admin)) {
            return null;
        }
        if(username.equalsIgnoreCase(cancel) || password.equalsIgnoreCase(cancel)){
            return null;
        }
        return new NhanVien(username, password);
	}
	
	public static void xoaNhanVien() {
		try {
            if (!DangNhap.getUser().getUsername().equalsIgnoreCase(admin)) {
                System.out.println("Không phải chủ quán, không thể xóa tài khoản nhân viên");
                return;
            }
            hienThiDSNhanVien();
            System.out.println("Nhập số của tài khoản cần xóa");
            int index = Integer.parseInt(sc.nextLine());
            DangNhap.getList().remove(index - 1);
            TextReadWrite.writeNV("src/Files/NhanVien.txt", DangNhap.getList());
        } catch (Exception e) {
            System.out.println("Hủy quá trình xóa tài khoản");
        }
	}

	private static void hienThiDSNhanVien() {
		DangNhap.setList(TextReadWrite.readNV("src/Files/NhanVien.txt"));
        for (int i = 0; i < DangNhap.getList().size(); i++) {
            System.out.println("Số " + (i+1) + " " + DangNhap.getList().get(i).getUsername());
        }
	}
	
	public static void suaNhanVien() {
		try {
            if (!DangNhap.getUser().getUsername().equalsIgnoreCase(admin)) {
                System.out.println("Không phải chủ quán, không thể sửa tài khoản nhân viên");
                return;
            }
            hienThiDSNhanVien();
            System.out.println("Nhập số của tài khoản cần sửa");
            int index = Integer.parseInt(sc.nextLine());
            DangNhap.getList().set(index - 1, taoNhanVien());
            TextReadWrite.writeNV("src/Files/NhanVien.txt", DangNhap.getList());
        } catch (Exception e) {
            System.out.println("Hủy quá trình sửa tài khoản");
        }
	}
	
	public static void getThongKe(long total) {
		try {
            thongke = TextReadWrite.readReport("src/Files/ThongKe.txt");
            if (thongke.isEmpty()) {
                QuanNet.getThongKe().add(new ThongKe(LocalDate.now(), total));
            } else {
                for (ThongKe tk : QuanNet.getThongKe()) {
                    if (tk.getDate().equals(LocalDate.now())) {
                        tk.setThunhap(total);
                    } else {
                        QuanNet.getThongKe().add(new ThongKe(LocalDate.now(), total));
                    }
                }
            }
            TextReadWrite.writeReport("src/Files/ThongKe.txt", thongke);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public static void hienThiThongKe() {
		try {
			thongke = TextReadWrite.readReport("src/Files/ThongKe.txt");
            for (ThongKe tk : thongke) {
                System.out.println(tk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public static void thongKeTrongNgay() {
		
	}
}
