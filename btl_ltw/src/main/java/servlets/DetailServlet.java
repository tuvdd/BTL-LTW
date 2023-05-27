package servlets;
import java.util.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Book;
import models.Comment;
import repositories.BookRepo;
import repositories.CommentRepo;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.UUID;

@WebServlet({ "/detail", "/detail/" })
public class DetailServlet extends HttpServlet {
    CommentRepo commentRepo;
    public DetailServlet() {
        commentRepo = new CommentRepo();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int currentPage = Integer.parseInt(req.getParameter("page") != null ? req.getParameter("page") : "1");
        int commentsPerPage = 5;
        BookRepo repoB = new BookRepo();
        Book book;
        // String bookid = req.getParameter("bookid");
        String bookID = "9377f02d-5f88-4bd6-b251-9183a63bcf87";
        book = repoB.getById(UUID.fromString(bookID));
        req.setAttribute("book",book);
        List<Comment> listComments;
        listComments = commentRepo.GetlistCommentByBookID(bookID, currentPage, commentsPerPage);
        System.out.print("List Count " + " ");
        System.out.println(listComments.size());
        req.setAttribute("listComments", listComments);
        req.getRequestDispatcher("/Detail.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String rateStr = req.getParameter("rate");
        String commentText = req.getParameter("comment_text");
        System.out.println("comment text:");
        System.out.println(commentText);
        if (rateStr == null || commentText == null || commentText.isEmpty()) {
            System.out.println("Vui lòng nhập comment và chọn số sao!");
            req.setAttribute("error", "Vui lòng nhập comment và chọn số sao!");
            resp.sendRedirect("/btl_ltw/detail");
        } else {
            int rate = Integer.parseInt(rateStr);
            System.out.println(rate);
            Comment newComment = new Comment();
            newComment.set(UUID.randomUUID(), UUID.fromString("9377f02d-5f88-4bd6-b251-9183a63bcf87"), rate, commentText, new Timestamp(new Date().getTime()));
            int res = commentRepo.Add(newComment);
            System.out.println("okeee");
            if (res != 1) {
                System.out.println("Thêm mới không thành công!");
                req.setAttribute("error", "Thêm mới không thành công");
            }
            resp.sendRedirect("/btl_ltw/detail");
        }
    }
}
