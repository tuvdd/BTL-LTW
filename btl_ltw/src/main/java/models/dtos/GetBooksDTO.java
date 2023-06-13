package models.dtos;

import java.util.List;

import models.Book;

public class GetBooksDTO {
    List<Book> data;
    int page;
    int size;
    int total;
    public List<Book> getData() {
        return data;
    }
    public void setData(List<Book> data) {
        this.data = data;
    }
    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    
}
