package servlets;
import java.util.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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
    String bookID = "";
    public DetailServlet() {
        commentRepo = new CommentRepo();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int currentPage = Integer.parseInt(req.getParameter("page") != null ? req.getParameter("page") : "1");
        int commentsPerPage = 5;
        BookRepo repoB = new BookRepo();
        Book book = null;;
        bookID = req.getParameter("bookid");
        // String bookID = "9377f02d-5f88-4bd6-b251-9183a63bcf87";
        try {
            book = repoB.getById(UUID.fromString(bookID));
            System.out.println(book.getName());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // System.out.print("BOOKID");
        // System.out.println(bookID);
        List<Comment> listComments;
        listComments = commentRepo.GetlistCommentByBookID(bookID, currentPage, commentsPerPage);
        int numberOfPages = commentRepo.getNumberOfPages(bookID, commentsPerPage);
        int numberComment = repoB.getNumberComments(bookID);
        float averageComment = repoB.getAverageComment(bookID);
        // System.out.print("count");
        // System.out.println(numberComment);
        // System.out.println(averageComment);
        req.setAttribute("bookid", bookID);
        req.setAttribute("listComments", listComments);
        req.setAttribute("book",book);
        req.setAttribute("numberOfPages", numberOfPages);
        req.setAttribute("averageComment", averageComment);
        req.setAttribute("numberComment", numberComment);
        req.getRequestDispatcher("/Detail.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        if (session.getAttribute("userID") == null) {
            String currentURL = req.getRequestURL().toString();
            currentURL += "?bookid=" + bookID;
            session.setAttribute("currentURL", currentURL);
            resp.sendRedirect("/btl_ltw/user/login");
            return;
        }
        String userID = (String) session.getAttribute("userID");
        String rateStr = req.getParameter("rate").trim();
        String commentText = req.getParameter("comment_text").trim();
        if (rateStr == null || commentText == null || commentText.isEmpty()) {
            System.out.println("Vui lòng nhập comment và chọn số sao!");
            req.getSession().setAttribute("error", "Vui lòng nhập comment và chọn số sao!");
            resp.sendRedirect("/btl_ltw/detail?bookid="+bookID);
        } else {
            int rate = Integer.parseInt(rateStr);
            System.out.println(rate);
            Comment newComment = new Comment();
            newComment.set(UUID.randomUUID(), UUID.fromString(userID), UUID.fromString(bookID), rate, commentText, new Timestamp(new Date().getTime()));
            int res = commentRepo.Add(newComment);
            System.out.println("okeee");
            if (res != 1) {
                System.out.println("Thêm mới không thành công!");
                req.getSession().setAttribute("error", "Thêm mới không thành công");
            }
            resp.sendRedirect("/btl_ltw/detail?bookid="+bookID);
        }
    }
}
