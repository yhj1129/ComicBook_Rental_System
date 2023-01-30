package com.cmbookrental.prj.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComicBookDTO {

    private int id;
    private String title;
    private String author;

    public ComicBookDTO(int id, String title, String author){
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "만화책 정보 | " +
                "책 번호 : " + id +
                ", 책 제목 : '" + title + '\'' +
                ", 작가명 : '" + author + '\'';
    }
}
