package servlets.client;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Book;
import models.Category;
import repositories.impls.BookRepo;
import repositories.impls.CategoryRepo;
import repositories.impls.DAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@WebServlet({ "/client/shopping1", "/client/shopping1/" })
public class Shopping1Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String[] pp = {"Dưới 1 triệu",
//                "Từ 1-3 triệu",
//                "Từ 3-5 triệu",
//                "Từ 5-10 triệu",
//                "Trên 10 triệu"};
//        boolean[] pb = new boolean[pp.length + 1];
//        DAO d = new DAO();
//
//        List<Category> categories = d.getAll();
//        boolean[] chid = new boolean[categories.size() + 1];
//        List<Book> products = new ArrayList<>();
//        String key = req.getParameter("key");
//        if (key != null) {
//            products=d.searchByKey(key);
//        }
//        String cid_raw = req.getParameter("cid");
//        String[] cidd_raw = req.getParameterValues("cidd");
//        String[] price = req.getParameterValues("price");
//        int cid = 0;
//        int[] cidd = null;
//        if (cid_raw != null) {
//            cid = Integer.parseInt(cid_raw);
//            products = d.getBooksByCid(cid);
//            if (cid == 0) {
//                chid[0] = true;
//            }
//        }
//        if (cidd_raw != null) {
//            cidd = new int[cidd_raw.length];
//            for (int i = 0; i < cidd.length; i++) {
//                cidd[i] = Integer.parseInt(cidd_raw[i]);
//            }
//            products = d.searchByCheck(cidd);
//        }
//        if (price != null) {
//            double from = 0, to = 0;
//            for (int i = 0; i < price.length; i++) {
//                List<Book> temp = new ArrayList<>();
//                if (price[i].equals("0")) {
//                    from = 0;
//                    to = 20000;
//                    products = d.getBooksByPrice(from, to);
//                    pb[0] = true;
//                    break;
//                } else {
//                    if (price[i].equals("1")) {
//                        from = 0;
//                        to = 1000;
//                        temp = d.getBooksByPrice(from, to);
//                        products.addAll(temp);
//                        pb[1] = true;
//                    }
//                    if (price[i].equals("2")) {
//                        from = 1000;
//                        to = 3000;
//                        temp = d.getBooksByPrice(from, to);
//                        products.addAll(temp);
//                        pb[2] = true;
//                    }
//                    if (price[i].equals("3")) {
//                        from = 3000;
//                        to = 5000;
//                        temp = d.getBooksByPrice(from, to);
//                        products.addAll(temp);
//                        pb[3] = true;
//                    }
//                    if (price[i].equals("4")) {
//                        from = 5000;
//                        to = 10000;
//                        temp = d.getBooksByPrice(from, to);
//                        products.addAll(temp);
//                        pb[4] = true;
//                    }
//                    if (price[i].equals("5")) {
//                        from = 10000;
//                        to = 20000;
//                        temp = d.getBooksByPrice(from, to);
//                        products.addAll(temp);
//                        pb[5] = true;
//                    }
//                }
//            }
//        }
//        if (price == null) {
//            pb[0] = true;
//        }
//        if (cid_raw == null) {
//            chid[0] = true;
//        }
//        if ((cidd_raw != null) && (cidd[0] != 0)) {
//            chid[0] = false;
//            for (int i = 1; i < chid.length; i++) {
//                if (ischeck(categories.get(i - 1).getId(), cidd)) {
//                    chid[i] = true;
//                } else {
//                    chid[i] = false;
//                }
//            }
//        }
//        req.setAttribute("data", categories);
//        req.setAttribute("products", products);
//        req.setAttribute("pp", pp);
//        req.setAttribute("key", key);
//        req.setAttribute("pb", pb);
//        req.setAttribute("cid", cid);
//        req.setAttribute("chid", chid);
//        req.getRequestDispatcher("list.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    private boolean ischeck(int d, int[] id) {
        if (id == null) {
            return false;
        } else {
            for (int i = 0; i < id.length; i++) {
                if (id[i] == d) {
                    return true;
                }
            }
            return false;
        }
    }

}
