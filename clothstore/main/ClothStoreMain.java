package clothstore.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import clothstore.product.Cloth;
import clothstore.product.Pants;
import clothstore.product.ShopList;
import clothstore.product.Top;
import clothstore.user.BagProduct;
import clothstore.user.Order;
import clothstore.user.User;

public class ClothStoreMain {

	public static User user = null;
	public static ArrayList<User> userList = new ArrayList<>();
	public static ArrayList<ShopList> shopList = new ArrayList<>();
	public static ArrayList<Order> orderList = new ArrayList<>();
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		loadingShopList();
		loadingUserList();

		boolean exitFlag = false;

		String[] menu = { "로그인", "회원가입", "종료" };
		while (!exitFlag) {
			System.out.println("*****************************");
			System.out.println("*****\tCloth Store\t*****");
			System.out.println("*****************************");
			System.out.print("1.기존회원로그인\t2.신규가입\n3.종료\n메뉴선택 >> ");
			int inputNum = sc.nextInt();
			sc.nextLine();

			switch (menu[inputNum - 1]) {
			case "종료":
				exitFlag = true;
				break;
			case "로그인":
				if (loginUser()) {
					System.out.println("로그인완료");
					System.out.println(user.toString());
					if (user.isAdmin()) {
						// 관리자페이지();
						// 1.상품리스트보기 2.추가 3.수정 4.삭제 5.고객주문내역조회 0.로그아웃
						adminMenuSelect();
					} else {
						// 유저페이지();
						// 1.장바구니목록 2.추가 3.삭제(모두비우기) 4.주문하기 5.고객정보(주문내역) 0.로그아웃
						userMenuSelect();
					}
				}
				break;
			case "회원가입":
				createUser();
				break;
			default:
				break;
			}
		}

		System.out.println("The end");

	}

	// 주문리스트 파일에서 가져오기
	public static void loadingOrderList() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("orderListData.txt")))) {
			orderList = (ArrayList<Order>) ois.readObject();
		} catch (Exception e) {
			System.out.println("주문리스트가 존재하지않습니다.");
		}

	}

	// 제품리스트 파일에서 가져오기
	public static void loadingShopList() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("shopListData.txt")))) {
			shopList = (ArrayList<ShopList>) ois.readObject();
		} catch (Exception e) {
			System.out.println("쇼핑리스트가 존재하지않습니다.");
		}
	}

	// 기본유저리스트 데이터에서 가져오기
	public static void loadingUserList() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("userData.txt")))) {
			userList = (ArrayList<User>) ois.readObject();
		} catch (Exception e) {
			System.out.println("회원이 존재하지않습니다.");
		}
	}

	// 유저리스트 파일에 저장
	public static void saveUserList() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("userData.txt")))) {
			oos.writeObject(userList);
		} catch (FileNotFoundException e) {
			System.out.println("유저파일 생성 오류");
		} catch (IOException e) {
		}
	}

	// 로그인
	public static boolean loginUser() {
		boolean flag = false;
		System.out.println("아이디와 비밀번호를 입력해주세요");
		System.out.print("아이디 : ");
		String inputId = sc.nextLine();
		System.out.print("비밀번호: ");
		String inputPw = sc.nextLine();

		for (User data : userList) {
			if (inputId.equals(data.getId()) && inputPw.equals(data.getPw())) {
				user = data;
				flag = true;
			}
		}
		if (!flag) {
			System.out.println("아이디와비밀번호가 맞지않습니다.");
		}
		return flag;
	}

	// 신규가입
	public static void createUser() {
		boolean flag = false;
		System.out.print("아이디 : ");
		String inputId = sc.nextLine();
		System.out.print("비밀번호 : ");
		String inputPw = sc.nextLine();
		System.out.print("성함 : ");
		String inputName = sc.nextLine();
		System.out.print("핸드폰번호 : ");
		String inputPhone = sc.nextLine();

		User newUser = new User(inputId, inputPw, inputName, inputPhone);
		for (User data : userList) {
			if (data.equals(newUser)) {
				flag = true;
			}
		}
		if (flag) {
			System.out.println("이미 가입된 회원입니다.");
		} else {
			System.out.println("회원가입 완료");
			userList.add(newUser);
			saveUserList();
		}
	}

	// 유저 메뉴 페이지
	public static void userMenuSelect() {
		// 1.장바구니목록 2.추가 3.삭제(모두비우기) 4.주문하기 5.고객정보(주문내역)

		String[] menu = { "장바구니보기", "장바구니상품추가", "장바구니상품삭제", "장바구니비우기", "주문하기", "주문내역확인" };
		boolean flag = false;
		while (!flag) {
			System.out.println("\n1.장바구니목록보기\t2.장바구니상품추가\n3.장바구니상품삭제\t4.장바구니비우기\n5.장바구니상품주문하기\t6.주문내역확인하기\n0.로그아웃");
			System.out.print("메뉴선택 >> ");
			int inputNum = sc.nextInt();
			sc.nextLine();

			if (inputNum == 0) {
				flag = true;
				continue;
			}

			switch (menu[inputNum - 1]) {
			case "장바구니보기":
				showBag();
				break;
			case "장바구니상품추가":
				insertBag();
				break;
			case "장바구니상품삭제":
				deleteBag();
				break;
			case "장바구니비우기":
				clearBag();
				break;
			case "주문하기":
				orderBag();
				break;
			case "주문내역확인":
				checkOrder();
				break;
			default:
				break;
			}
		}

	}

	// 장바구니목록보기
	public static boolean showBag() {
		boolean flag = false;

		if (user.getBag().getBagList().isEmpty()) {
			System.out.println("장바구니에 항목이 없습니다.");
		} else {
			user.getBag().showBagList();
			flag = true;
		}

		return flag;

	}

	// 장바구니상품추가
	public static void insertBag() {
		System.out.println("\n================================================================");
		System.out.println("카테고리\t상품코드\t\t상품명\t\t가격");
		shopList.stream().sorted().forEach(n -> n.printShopList());
		System.out.println("================================================================\n");
		boolean flag = false;
		Cloth cloth = null;
		int inputQuantity = 0;
		System.out.print("장바구니에 추가하실 상품의 코드를 입력해주세요(메인메뉴:-1) : ");
		String inputCode = sc.nextLine();
		if (inputCode.equals("-1")) {
			return;
		}
		for (ShopList data : shopList) {
			if (data.getCloth().getCode().equals(inputCode) && data.getClothStack() != 0) {
				cloth = data.getCloth();
				System.out.print("장바구니에 담을 수량을 입력해주세요 : ");
				inputQuantity = sc.nextInt();
				sc.nextLine();
				if (data.getClothStack() - inputQuantity < 0 || inputQuantity < 0) {
					System.out.println("상품의 재고가 부족합니다. 주문가능 수량 : " + data.getClothStack());
					flag = false;
					return;
				}
				data.setClothStack(data.getClothStack() - inputQuantity);
				flag = true;
			}
		}
		if (flag) {

			user.getBag().insertBag(cloth, inputQuantity);
			saveUserData();
		} else {
			System.out.println("상품이 존재하지않거나 품절입니다.");
		}
	}

	// 장바구니 상품삭제
	public static void deleteBag() {
		BagProduct bagProduct = null;
		if (showBag()) {
			System.out.print("장바구에서 삭제하실 상품의 코드를 입력해주세요(메인메뉴:-1) : ");
			String inputCode = sc.nextLine();
			if (inputCode.equals("-1")) {
				return;
			}

			for (BagProduct data : user.getBag().getBagList()) {
				if (data.getBagCloth().getCode().equals(inputCode)) {
					bagProduct = data;
				}
			}
			if (bagProduct != null) {
				for (ShopList data : shopList) {
					if (bagProduct.getClothCode().equals(data.getCloth().getCode())) {
						data.setClothStack(data.getClothStack() + bagProduct.getQuantity());
					}
				}
				user.getBag().getBagList().remove(bagProduct);
				saveUserData();
			} else {
				System.out.println("장바구니에 상품코드가 존재하지않습니다.");
			}
		}
	}

	// 장바구니 비우기
	public static void clearBag() {
		if (user.getBag().getBagList().isEmpty()) {
			System.out.println("장바구니에 항목이 없습니다.");
		} else {
			user.getBag().getBagList().clear();
			saveUserData();
			loadingShopList();
		}
	}

	// 장바구니상품주문하기
	public static void orderBag() {
		boolean flag = false;
		if (showBag()) {
			System.out.println("장바구니 상품주문 메뉴입니다.");
			while (!flag) {
				System.out.print("배송지(메인메뉴:-1) : ");
				String inputAddress = sc.nextLine();
				if (inputAddress.equals("-1")) {
					return;
				}
				user.setAddress(inputAddress);
				user.showUserInfo();
				System.out.print("배송받을 고객정보가 맞습니까? Y|N : ");
				String input = sc.nextLine().toUpperCase();
				if (input.equals("Y")) {
					flag = true;
				}
			}
			if (flag) {
				loadingOrderList();
				loadingShopList();
				Date nowDate = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				Order newOrder = new Order(user, dateFormat.format(nowDate));
				orderList.add(newOrder);
				for (BagProduct data : user.getBag().getBagList()) {
					data.orderProtuct(shopList);
				}
				System.out.println("주문이 완료 되었습니다.");
				saveOrderList();
				saveShopList();
				clearBag();
				saveUserData();
			}
		}

	}

	// 주문리스트 파일로 저장
	public static void saveOrderList() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("orderListData.txt")))) {
			oos.writeObject(orderList);
		} catch (FileNotFoundException e) {
			System.out.println("주문리스트 파일 생성 오류");
		} catch (IOException e) {
		}

	}

	// 주문내역확인하기
	public static void checkOrder() {
		loadingOrderList();
		boolean flag = false;

		for (Order data : orderList) {
			if (data.getUser().equals(user)) {
				System.out.println("================================================================");
				System.out.print("주문일 : " + data.getOrderdate());
				data.getUser().getBag().showBagList();
				System.out.println("================================================================");
				flag = true;
			}
		}
		if (!flag) {
			System.out.println("주문내역이 없습니다.");
		}
	}

	// 유저데이터 파일에 저장
	public static void saveUserData() {
		for (User data : userList) {
			if (user.equals(data)) {
				data = user;
			}
		}
		saveUserList();

	}

	// 관리자 메뉴 페이지
	public static void adminMenuSelect() {

		boolean flag = false;
		String[] menu = { "판매리스트보기", "판매제품추가", "판매제품수정", "판매제품삭제", "주문내역조회" };

		while (!flag) {
			System.out.print("1.제품리스트보기\t2.제품추가\n3.제품수정\t\t4.제품삭제\n5.주문내역조회\t0.로그아웃\n메뉴선택>> ");
			int inputNum = sc.nextInt();
			sc.nextLine();

			if (inputNum == 0) {
				flag = true;
				continue;
			}

			switch (menu[inputNum - 1]) {
			case "판매리스트보기":
				showShopList();
				break;
			case "판매제품추가":
				insertShopList();
				break;
			case "판매제품수정":
				updateShopList();
				break;
			case "판매제품삭제":
				deleteShopList();
				break;
			case "주문내역조회":
				showOrderList();
				break;
			default:
				break;
			}
		}
	}

	// (admin)제품리스트보기
	public static void showShopList() {
		System.out.println("================================================================");
		System.out.println("상품코드\t\t카테고리\t상품명\t\t가격\t재고");
		shopList.stream().sorted().forEach(n -> n.printShopListForAdmin());
		System.out.println("================================================================");
	}

	// (admin)제품추가
	public static void insertShopList() {
		System.out.println("제품추가메뉴입니다.");
		System.out.print("추가하실제품의 카테고리(메인메뉴:-1) : ");
		String inputCategory = sc.nextLine().toUpperCase();
		if (inputCategory.equals("-1")) {
			return;
		}
		System.out.print("제품명 : ");
		String inputName = sc.nextLine();
		System.out.print("가격 : ");
		int inputPrice = sc.nextInt();
		System.out.print("재고량 : ");
		int inputStack = sc.nextInt();
		boolean flag = false;
		switch (inputCategory) {
		case "TOP":
			Top top = new Top(inputName, inputPrice);
			while (!flag) {
				flag = true;
				for (ShopList data : shopList) {
					if (top.getCode().equals(data.getCloth().getCode())) {
						top.setCodeCount(top.getCodeCount() + 1);
						top.setCode("top" + top.getCodeCount());
						flag = false;
					}
				}
			}
			ShopList newTop = new ShopList(top, inputStack);
			shopList.add(newTop);
			break;
		case "PANTS":
			Pants pants = new Pants(inputName, inputPrice);
			while (!flag) {
				flag = true;
				for (ShopList data : shopList) {
					if (pants.getCode().equals(data.getCloth().getCode())) {
						pants.setCodeCount(pants.getCodeCount() + 1);
						pants.setCode("pan" + pants.getCodeCount());
						flag = false;
					}
				}
			}
			ShopList newPants = new ShopList(pants, inputStack);
			shopList.add(newPants);
			break;
		}
		saveShopList();
	}

	// shopList 파일저장
	public static void saveShopList() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("shopListData.txt")))) {
			oos.writeObject(shopList);
		} catch (FileNotFoundException e) {
			System.out.println("쇼핑리스트파일저장오류");
		} catch (IOException e) {
		}

	}

	// (admin)제품수정
	public static void updateShopList() {
		boolean flag = false;
		ShopList updateShopList = null;
		showShopList();
		System.out.print("수정하실 제품코드를 입력해주세요(메인메뉴:-1) : ");
		String inputCode = sc.nextLine();
		if (inputCode.equals("-1")) {
			return;
		}
		for (ShopList data : shopList) {
			if (data.getCloth().getCode().equals(inputCode)) {
				updateShopList = data;
				flag = true;
			}
		}
		if (!flag) {
			System.out.println("상품코드가 존재하지않습니다.");
		}

		while (flag) {
			updateShopList.printShopListForAdmin();
			String[] menu = { "상품명", "가격", "수량" };

			System.out.print("수정하실 메뉴를 선택해주세요\n1.상품명 2.가격 3.수량 -1.메인메뉴 >> ");
			int inputNum = sc.nextInt();
			sc.nextLine();
			if (inputNum == -1) {
				flag = false;
				continue;
			}
			switch (menu[inputNum - 1]) {
			case "상품명":
				System.out.print("상품명 : ");
				String inputName = sc.nextLine();
				updateShopList.getCloth().setName(inputName);
				break;
			case "가격":
				System.out.print("가격 : ");
				int inputPrice = sc.nextInt();
				updateShopList.getCloth().setPrice(inputPrice);
				break;
			case "수량":
				System.out.print("재고량 : ");
				int inputStack = sc.nextInt();
				updateShopList.setClothStack(inputStack);
				break;
			default:
				break;
			}
		}

		saveShopList();
	}

	// (admin)제품삭제
	public static void deleteShopList() {
		boolean flag = false;
		ShopList modifyShopList = null;
		showShopList();
		System.out.print("삭제하실 제품코드를 입력해주세요(메인메뉴:-1) : ");
		String inputCode = sc.nextLine();
		if (inputCode.equals("-1")) {
			return;
		}
		for (ShopList data : shopList) {
			if (data.getCloth().getCode().equals(inputCode)) {
				modifyShopList = data;
				flag = true;
			}
		}
		if (flag) {
			shopList.remove(modifyShopList);
			saveShopList();
			System.out.println("상품삭제가 완료되었습니다.");
		} else {
			System.out.println("상품코드가 존재하지않습니다.");
		}
	}

	// (admin)주문내역조회
	public static void showOrderList() {
		loadingOrderList();
		int totalSales = 0;
		for (Order data : orderList) {
			System.out.println("================================================================");
			System.out.println("주문일 : " + data.getOrderdate());
			System.out.println("\n주문자 정보 ");
			data.getUser().showUserInfo();
			System.out.print("\n주문내역");
			data.getUser().getBag().showBagList();
			System.out.println("================================================================");
			totalSales += data.getUser().getBag().getTotalPrice();
		}
		System.out.println("총매출 : " + totalSales);
		System.out.println("================================================================");

	}

}
