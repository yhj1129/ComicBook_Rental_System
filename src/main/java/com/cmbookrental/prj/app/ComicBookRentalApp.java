package com.cmbookrental.prj.app;

import com.cmbookrental.prj.comm.MyLogger;
import com.cmbookrental.prj.controller.Controller;
import com.cmbookrental.prj.dto.ComicBookDTO;
import com.cmbookrental.prj.dto.CustomerDTO;
import com.cmbookrental.prj.dto.RentalDTO;
import com.cmbookrental.prj.factory.Factory;

import java.util.ArrayList;
import java.util.Scanner;

import static com.cmbookrental.prj.comm.CommonCode.*;

/*
 ** 코드 구성 **
    1. MainApp
    2. 첫 번째 콘솔 메뉴 창
    3. 만화책 등록, 수정, 삭제, 조회 메뉴창
    4. 대여 메뉴창
    5. 반납 메뉴창
    6. 고객 등록, 수정, 삭제, 조회 메뉴창
    7. 고객 정보를 입력받는 콘솔 창
    8. 만화책의 정보를 입력받는 콘솔 창
    9. 대여 객체를 생성하는 메서드
    10. 각 타입의 ArrayList에 해당 id가 존재하는지 확인하는 메서드

    <사용자 정의 에러 클래스>
    11. 요청한 ID의 객체가 존재하지 않는 경우 발생할 오류
    12. 대여를 요청한 만화책이 이미 대여중일 경우 발생할 오류

    <static으로 선언되어 있는 고객과 만화책>

    만화책 - id: 1, 제목: "1", 작가: "1"
            id: 2, 제목: "2", 작가: "2"

    고객 - id: 1,이름: "1"
          id: 2,이름: "2"
 */

public class ComicBookRentalApp {
    private Controller comicBookController = Factory.getInstance().getController(COMIC_BOOK);
    private Controller rentalController = Factory.getInstance().getController(RENTAL);
    private Controller customerController = Factory.getInstance().getController(CUSTOMER);
    private ArrayList<ComicBookDTO> comicBookList = comicBookController.findAll();
    private ArrayList<RentalDTO> rentalList = rentalController.findAll();
    private ArrayList<CustomerDTO> customerList = customerController.findAll();

    static MyLogger myLogger = MyLogger.getLogger();

    //1. MainApp
    public static void main(String[] args) throws NullPointerException {

        try {
            ComicBookRentalApp app = new ComicBookRentalApp();
            app.mainMenu();
        } catch (NullPointerException e) {
            throw new RuntimeException(e);
        }
    }
    //----------------------------------------------------//
    //2. 첫 번째 콘솔 메뉴 창
    public void mainMenu(){
        boolean on = true;

        while (on){
            System.out.println("<메뉴 선택>");
            System.out.println("1. 만화책 등록/수정/삭제/조회"); //comicBookMenu()로 이동
            System.out.println("2. 만화책 대여"); //rentalMenu()로 이동
            System.out.println("3. 만화책 반납"); //backMenu()로 이동
            System.out.println("4. 고객 등록/수정/삭제/조회"); //customerMenu()로 이동
            System.out.println("0. 프로그램 종료"); //while 스위치를 false로
            System.out.println("===================");

            Scanner scanner = new Scanner(System.in);
            System.out.print("메뉴 입력 : ");
            int num = scanner.nextInt();
            switch (num){
                case 1:
                    System.out.println("만화책 메뉴 ");
                    comicBookMenu();
                    break;
                case 2:
                    System.out.println("대여 메뉴 ");
                    rentalMenu();
                    break;
                case 3:
                    System.out.println("반납 메뉴 ");
                    backMenu();
                    break;
                case 4:
                    System.out.println("고객 메뉴 ");
                    customerMenu();
                    break;
                case 0:
                    System.out.println("프로그램이 종료됩니다.");
                    on = false;
                    break;
            }
        }
    }
    //----------------------------------------------------//
    //3. 만화책 등록, 수정, 삭제, 조회 메뉴창
    private void comicBookMenu() {
        boolean on = true;
        while (on){
            System.out.println("<메뉴 선택>");
            System.out.println("1. 만화책 등록");
            System.out.println("2. 만화책 수정");
            System.out.println("3. 만화책 삭제");
            System.out.println("4. 만화책 조회");
            System.out.println("0. 이전 메뉴");
            System.out.println("===================");

            Scanner scanner = new Scanner(System.in);
            System.out.print("메뉴 입력 : ");
            int num = scanner.nextInt();

            switch (num){
                case 1://만화책 등록
                    ComicBookDTO comicBook = createComicBook();
                    comicBookController.create(comicBook);
                    System.out.println("만화책 등록이 완료되었습니다.");
                    break;
                case 2://만화책 수정
                    boolean again = true;
                    System.out.print("수정할 만화책의 ID를 입력하세요 : ");
                    while (again) {
                        try {
                            int updateComicBookID = scanner.nextInt();
                            int updateIndex = contains(COMIC_BOOK, updateComicBookID);
                            if (updateIndex == -1) {
                                //수정할 만화책이 존재하지 않는 오류 발생
                                throw new NoInstanceError("만화책 ID가 존재하지 않습니다. 다시 입력해주세요 : ");
                            } else {
                                System.out.println("<수정 정보 입력>");
                                System.out.print("제목 : ");
                                String updateTitle = scanner.next();
                                System.out.print("작가명 : ");
                                String updateAuthor = scanner.next();
                                ComicBookDTO updateComicBook = new ComicBookDTO(updateComicBookID, updateTitle, updateAuthor);
                                comicBookController.update(updateIndex, updateComicBook);
                                System.out.println("만화책 정보 수정이 완료되었습니다.");
                                again = false;
                            }
                        }catch (NoInstanceError e){
                        }
                    }
                    break;
                case 3://만화책 삭제
                    again = true;
                    System.out.print("삭제할 만화책의 ID를 입력해주세요 : ");
                    while (again) {
                        try {
                            int id = scanner.nextInt();
                            if (contains(COMIC_BOOK, id) == -1) {
                                //삭제할 만화책이 존재하지 않는 오류
                                throw new NoInstanceError("삭제할 만화책이 존재하지 않습니다. 다시 입력해주세요 : ");
                            } else {
                                comicBookController.delete(id);
                                System.out.println("만화책 삭제가 완료되었습니다.");
                                again = false;
                            }
                        }catch (NoInstanceError e){
                        }
                    }
                    break;
                case 4://만화책 조회
                    System.out.print("조회할 만화책의 제목을 입력해주세요 : ");
                    again = true;
                    while (again) {
                        try {
                            String searchName = scanner.next();
                            boolean exist = false;
                            for (int i = 0; i < comicBookList.size(); i++) {
                                ComicBookDTO searchBook = comicBookList.get(i);
                                String title = searchBook.getTitle();
                                if (searchName.equals(title)) {
                                    System.out.println(searchBook);
                                    exist = true;
                                    again = false;
                                }
                            }
                            if (!exist) {
                                throw new NoInstanceError("조회할 만화책이 존재하지 않습니다. 다시 입력해주세요 : ");
                            }
                        }catch (NoInstanceError e){

                        }
                    }
                    break;
                case 0://이전 메뉴
                    on = false;
                    break;
                default :
                    System.out.println("잘못된 번호입니다. 다시 입력해주세요");
                    break;
            }
        }
    }
    //----------------------------------------------------//
    //4. 대여 메뉴창
    private void rentalMenu() {
        System.out.println("<만화책 대여 메뉴>");
        boolean on = true;
        System.out.print("대여할 만화책의 ID를 입력해주세요 : ");
        while(on){
            try {
                Scanner scanner = new Scanner(System.in);
                int comicBookId = scanner.nextInt();
                //1. 만화책이 등록되어 있는가?
                int index = contains(COMIC_BOOK, comicBookId); //없으면 -1
                if (index == -1){
                    //대여할 만화책이 존재하지 않는 오류
                    throw new NoInstanceError("대여할 만화책이 존재하지 않습니다. 다시 입력해주세요 : ");
                }else {
                    boolean rentaled = false;
                    //2. 만화책이 이미 대여중인가?
                    for (int i = 0; i < rentalList.size(); i++){
                        if(rentalList.get(i).getRentalBook().getId() == comicBookId){ // 일치하면 대여중
                            rentaled = true;
                            break;
                        }
                    }
                    if (rentaled) {
                        //대여할 만화책이 이미 대여중인 오류
                        throw new AlreadyRented("만화책이 이미 대여중입니다. 다른 만화책을 입력해주세요");
                    }else{
                        //대여중이 아니면 고객 등록 확인
                        System.out.println("해당 책은 대여가 가능합니다");
                        System.out.println("고객 등록 확인을 진행하겠습니다");
                        System.out.print("고객 ID를 입력해주세요 : ");
                        int customerID = scanner.nextInt();
                        ComicBookDTO rentalBook = comicBookList.get(index);
                        if (contains(CUSTOMER, customerID) == -1){ //3. 고객이 등록되어있지 않은가?
                            System.out.println("등록된 고객이 아닙니다. 고객 등록을 진행하겠습니다");
                            CustomerDTO newCustomer = createCustomer();
                            customerController.create(newCustomer);
                            System.out.println("고객 등록을 완료했습니다. 대여를 진행하겠습니다");
                            rentalController.create(createRental(rentalBook, newCustomer));
                        }else{
                            System.out.println("등록된 고객입니다.");
                            for (int i = 0; i < rentalList.size(); i++){
                                if (rentalList.get(i).getRentalCustomer().getId() == customerID){ //4. 고객이 대여중인 만화책이 있는가?
                                    System.out.println(rentalList.get(i).getRentalBook().getId() + "번 만화책을 대여중입니다. 반납을 진행합니다");
                                    rentalController.delete(rentalList.get(i).getId()); // i는 index, id는 rental ID. i+1 해도 됨
                                    System.out.println("반납이 완료되었습니다");
                                }
                            }
                            System.out.println("대여를 진행합니다");
                            rentalController.create(createRental(rentalBook, customerList.get(customerID-1)));
                        }
                        System.out.println("대여가 완료되었습니다.");
                    }
                }
                on = false;
            }catch (NoInstanceError e){
            }catch (AlreadyRented e){
            }
        }
    }
    //----------------------------------------------------//
    //5. 반납 메뉴창
    private void backMenu() {
        System.out.println("<만화책 반납 메뉴>");
        boolean on = true;
        Scanner scanner = new Scanner(System.in);
        System.out.print("반납할 만화책의 ID를 입력해주세요 : ");
        while (on){
            try {
                int comicBookId = scanner.nextInt();
                int index = -1;
                for (int i = 0; i < rentalList.size(); i++) {
                    if (rentalList.get(i).getRentalBook().getId() == comicBookId) index = i;
                }
                if (index == -1) { //대여 목록에 만화책이 없으면
                    //반납할 만화책이 대여 목록에 존재하지 않는 오류
                    throw new NoInstanceError("렌탈 목록에 만화책이 존재하지 않습니다. 다시 입력해주세요 : ");
                } else {
                    //대여 목록에 만화책이 있는 경우
                    rentalController.delete(index + 1); //렌탈번호(ID) 입력해서 반납(delete)
                    System.out.println(comicBookId + "번 만화책 반납이 완료되었습니다.");
                    on = false;
                }
            }catch (NoInstanceError e){
            }
        }

    }
    //----------------------------------------------------//
    //6. 고객 등록, 수정, 삭제, 조회 메뉴창
    private void customerMenu() {
        boolean on = true;
        while (on){
            System.out.println("<메뉴 선택>");
            System.out.println("1. 고객 등록");
            System.out.println("2. 고객 수정");
            System.out.println("3. 고객 삭제");
            System.out.println("4. 고객 조회");
            System.out.println("0. 이전 메뉴");
            System.out.println("===================");

            Scanner scanner = new Scanner(System.in);
            System.out.print("메뉴 입력 : ");
            int num = scanner.nextInt();

            switch (num){
                case 1:// 고객 등록
                    CustomerDTO customer = createCustomer();
                    customerController.create(customer);
                    System.out.println(customer.getCustomerName() + "님 등록이 완료되었습니다.");
                    break;
                case 2:// 고객 수정
                    boolean again = true;
                    System.out.print("수정할 고객의 ID를 입력해주세요 : ");
                    while (again) {
                        try {
                            int updateCustomerID = scanner.nextInt();
                            int updateIndex = contains(CUSTOMER, updateCustomerID);
                            if (updateIndex == -1) {
                                throw new NoInstanceError("고객 ID가 존재하지 않습니다. 다시 입력해주세요 : ");
                            } else {
                                System.out.println("<수정 정보 입력>");
                                System.out.print("이름 : ");
                                String updateName = scanner.next();
                                CustomerDTO updateCustomer = new CustomerDTO(updateCustomerID, updateName);
                                customerController.update(updateIndex, updateCustomer);
                                System.out.println("고객 정보 수정이 완료되었습니다");
                                again = false;
                            }
                        } catch (NoInstanceError e) {
                        }
                    }
                    break;
                case 3:// 고객 삭제
                    again = true;
                    System.out.print("삭제할 고객의 ID를 입력해주세요 : ");
                    while (again) {
                        try {
                            int deleteCustomerID = scanner.nextInt();
                            int deleteIndex = contains(CUSTOMER, deleteCustomerID);
                            if (deleteIndex == -1) {
                                //삭제할 고객이 존재하지 않는 오류 발생
                                throw new NoInstanceError("고객 ID가 존재하지 않습니다. 다시 입력해주세요 : ");
                            } else {
                                customerController.delete(deleteCustomerID);
                                System.out.println("고객 삭제가 완료되었습니다");
                                again = false;
                            }
                        } catch (NoInstanceError e) {
                        }
                    }
                    break;
                case 4:// 모든 고객 조회
                    System.out.print("조회할 고객의 이름을 입력해주세요 : ");
                    String searchName = scanner.next();
                    for (int i = 0; i < customerList.size(); i++) {
                        CustomerDTO searchCustomer = customerList.get(i);
                        String name = searchCustomer.getCustomerName();
                        if (searchName.equals(name)){
                            System.out.println(searchCustomer);
                        }
                    }
                    break;
                case 0:
                    on = false;
                    break;
            }
        }
    }
    //----------------------------------------------------//
    //7. 고객 정보를 입력받는 콘솔 창
    private CustomerDTO createCustomer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("고객 번호 : " + CUSTOMER_ID);
        System.out.print("고객 명 : ");
        String customerName = scanner.next();

        CustomerDTO customer = new CustomerDTO(CUSTOMER_ID, customerName);
        CUSTOMER_ID++;
        return customer;
    }
    //----------------------------------------------------//
    //8. 만화책의 정보를 입력받는 콘솔 창
    private ComicBookDTO createComicBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("책 번호 : " + BOOK_ID);
        System.out.print("만화책 명 : ");
        String title = scanner.next();
        System.out.print("작가 명 : ");
        String author = scanner.next();

        ComicBookDTO comicBook = new ComicBookDTO(BOOK_ID, title, author);
        BOOK_ID++;
        return comicBook;
    }
    //----------------------------------------------------//
    //9. 대여 객체를 생성하는 메서드
    private RentalDTO createRental(ComicBookDTO comicBook, CustomerDTO customer){
        RentalDTO rental = new RentalDTO(RENTAL_ID, DATE, comicBook, customer);
        RENTAL_ID++;
        return rental;
    }
    //----------------------------------------------------//
    //10. 각 타입의 ArrayList에 해당 id가 존재하는지 확인하는 메서드
    private int contains(int type, int id) {
        int index = -1; //없을 경우 default : -1이 출력된다

        switch (type) {
            case COMIC_BOOK:
                for (int i = 0; i < comicBookList.size(); i++) {
                    if(comicBookList.get(i).getId() == id){
                        index = i;
                    }
                }
                break;
            case CUSTOMER:
                for (int i = 0; i < customerList.size(); i++) {
                    if(customerList.get(i).getId() == id){
                        index = i;
                    }
                }
                break;
            case RENTAL:
                for (int i = 0; i < rentalList.size(); i++) {
                    if(rentalList.get(i).getId() == id){
                        index = i;
                    }
                }
                break;
        }
        return index;
    }

    //11. 요청한 ID의 객체가 존재하지 않는 경우 발생할 오류
    private class NoInstanceError extends Exception {
        public NoInstanceError(String message) {
            super(message);
            myLogger.log(message);
        }
    }

    //12. 대여를 요청한 만화책이 이미 대여중일 경우 발생할 오류
    private class AlreadyRented extends Exception {
        public AlreadyRented(String message) {
            super(message);
            myLogger.log(message);
        }
    }
}
