package com.cmbookrental.prj.comm;

import java.time.LocalDate;

public class CommonCode {
    public static final int COMIC_BOOK = 1;
    public static final int CUSTOMER = 2;
    public static final int RENTAL = 3;

    public static LocalDate DATE = LocalDate.now();
    public static int CUSTOMER_ID = 3; //1, 2는 static으로 미리 생성했기 때문에 3부터
    public static int RENTAL_ID = 1;
    public static int BOOK_ID = 3; //1, 2는 static으로 미리 생성했기 때문에 3부터
}
