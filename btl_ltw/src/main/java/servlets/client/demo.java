package servlets.client;

import models.Book;
import models.BookImage;
import models.Category;
import repositories.impls.BookImageRepo;
import repositories.impls.BookRepo;
import repositories.impls.CategoryRepo;

import java.sql.SQLException;
import java.util.List;

public class demo {
    public static void main(String[] args) {
        CategoryRepo repoC = new CategoryRepo();
        BookRepo repoB = new BookRepo();
        BookImageRepo repoBI = new BookImageRepo();
        List<Book> listB;
        List<BookImage> listBI;
        List<Category> listC;
        try {
            listB = repoB.Gets("","");
            listC = repoC.Gets("", "");
            listBI = repoBI.Gets("","");
            System.out.println(listB);
            System.out.println(listC);
            System.out.println(listBI);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

