package com.cmbookrental.prj.dto;

import java.time.LocalDate;

public class RentalDTO {
    private int id;
    private LocalDate rentalDate;
    private ComicBookDTO rentalBook;
    private CustomerDTO rentalCustomer;

    public RentalDTO(int id, LocalDate rentalDate, ComicBookDTO rentalBook, CustomerDTO rentalCustomer){
        this.id = id;
        this.rentalDate = rentalDate;
        this.rentalBook = rentalBook;
        this.rentalCustomer = rentalCustomer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(LocalDate rentalDate) {
        this.rentalDate = rentalDate;
    }

    public ComicBookDTO getRentalBook() {
        return rentalBook;
    }

    public void setRentalBook(ComicBookDTO rentalBook) {
        this.rentalBook = rentalBook;
    }

    public CustomerDTO getRentalCustomer() {
        return rentalCustomer;
    }

    public void setRentalCustomer(CustomerDTO rentalCustomer) {
        this.rentalCustomer = rentalCustomer;
    }

    @Override
    public String toString() {
        return "대여 정보 | " +
                "대여 번호 : " + id +
                ", 대여일 : " + rentalDate +
                ", 대여한 고객 : [" + rentalCustomer +
                "] , 대여한 만화책번호 : " + rentalBook.getId();
    }
}
