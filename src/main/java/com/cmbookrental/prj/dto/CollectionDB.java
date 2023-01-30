package com.cmbookrental.prj.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CollectionDB {

    public static CollectionDB instance = new CollectionDB();

    public static final String COMIC_BOOK_DATA = "ComicBook";
    public static final String RENTAL_DATA = "Rental";
    public static final String CUSTOMER_DATA = "Customer";

    public static Map<String, ArrayList> allData = new HashMap<>();

    private CollectionDB(){}

    static {
        ArrayList<ComicBookDTO> comicBookArrayList = new ArrayList<ComicBookDTO>();
        ComicBookDTO comicBook1 = new ComicBookDTO(1, "1", "1");
        ComicBookDTO comicBook2 = new ComicBookDTO(2, "2", "2");
        comicBookArrayList.add(comicBook1);
        comicBookArrayList.add(comicBook2);
        allData.put(COMIC_BOOK_DATA, comicBookArrayList);

        ArrayList<CustomerDTO> customerArrayList = new ArrayList<CustomerDTO>();
        CustomerDTO customer1 = new CustomerDTO(1, "1");
        CustomerDTO customer2 = new CustomerDTO(2, "2");
        customerArrayList.add(customer1);
        customerArrayList.add(customer2);
        allData.put(CUSTOMER_DATA, customerArrayList);

        allData.put(RENTAL_DATA, new ArrayList<RentalDTO>());

    }

    public static CollectionDB getInstance() {
        if (instance == null) {
            instance = new CollectionDB();
        }
        return instance;
    }

    public Map getDatabase(){
        return this.allData;
    }
}
