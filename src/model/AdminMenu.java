package model;

import java.util.Scanner;

public class AdminMenu {
	public static Scanner sc = new Scanner(System.in);
	public static void menu() {
		while (true) {
            System.out.println("|==================================================|");
            System.out.println("|Hệ thống quản lý phòng net                        |");
            System.out.println("|1. Hiển thị danh sách máy                         |");
            System.out.println("|2. Thêm máy mới                                   |");
            System.out.println("|3. Bán máy                                        |");
            System.out.println("|4. Nâng cấp máy                                   |");
            System.out.println("|5. Bật máy                                        |");
            System.out.println("|6. Tính tiền máy                                  |");
            System.out.println("|7. Xem doanh thu                                  |");
            System.out.println("|8. Xem doanh thu trong khoảng thời gian           |");
            System.out.println("|9. Thêm dịch vụ mới                               |");
            System.out.println("|10. Thêm dịch vụ vào máy                          |");
            System.out.println("|11. Xóa dịch vụ                                   |");
            System.out.println("|12. Sửa dịch vụ                                   |");
            System.out.println("|13. Thêm tài khoản quản trị                       |");
            System.out.println("|14. Xóa tài khoản quản trị                        |");
            System.out.println("|15. Sửa tài khoản quản trị                        |");
            System.out.println("|==================================================|");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1 : QuanNet.hienThiTatCaMayTinh(); break;
                case 2 : QuanNet.themPC();	break;
                case 3 : QuanNet.banMayTinh();	break;
                case 4 : QuanNet.nangMayTinh();	break;
                case 5 : QuanNet.batMayTinh();	break;
                case 6 : QuanNet.tinhTien();	break;
                case 7 : QuanNet.hienThiThongKe();	break;
                case 8 : QuanNet.thongKeTrongNgay();	break;
                case 9 : QuanNet.themMoiDichVu();	break;
                case 10 : QuanNet.goiDichVU();	break;
                case 11 : QuanNet.xoaDichVu();	break;
                case 12 : QuanNet.suaDichVu();	break;
                case 13 : QuanNet.themNhanVien();	break;
                case 14 : QuanNet.xoaNhanVien();	break;
                case 15 : QuanNet.suaNhanVien();	break;
                default : System.exit(0);
            }
        }
	}
}
