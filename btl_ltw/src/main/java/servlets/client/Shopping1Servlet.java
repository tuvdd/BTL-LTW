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
import java.util.UUID;

@WebServlet({ "/client/shopping1", "/client/shopping1/" })
public class Shopping1Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] pp={"Dưới 100k",
                "Từ 100k - 200k",
                "Từ 200k - 500k",
                "Từ 500k - 1 triệu",
                "Trên 1 triệu"};
        boolean[] pb = new boolean[pp.length + 1];
        DAO d = new DAO();
        CategoryRepo repoC = new CategoryRepo();
        BookRepo repoB = new BookRepo();
        List<Book> products = null, olds = null;
        List<Category> categories = null;
        try {
            categories = repoC.Gets("", "");
            System.out.println("test");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



        boolean[] chid = new boolean[categories.size() + 1];
        String cid_raw = req.getParameter("cid");
        String[] cidd_raw = req.getParameterValues("cidd");
        String[] price = req.getParameterValues("price");
        int cid = 0;
        int[] cidd = null;
        if (cid_raw != null) {
            cid = Integer.parseInt(cid_raw);
            if (cid == 0){
                try {
                    products = repoB.Gets("","");
                    chid[0] = true;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }else {
                try {
                    products = repoB.GetListBookByCategoryID(categories.get(cid-1).getId().toString(),1,20);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        if (cidd_raw != null) {
            cidd = new int[cidd_raw.length];
            for (int i = 0; i < cidd.length; i++) {
                cidd[i] = Integer.parseInt(cidd_raw[i]);
            }
            for (int i = 0; i < cidd.length; i++) {
                try {
                    List<Book> products_temp = repoB.GetListBookByCategoryID(categories.get(cidd[i]-1).getId().toString(),1,20);
                    products.addAll(products_temp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        if (price != null) {
            double from = 0, to = 0;
            for (int i = 0; i < price.length; i++) {
                List<Book> temp_book_price;
                if (price[i].equals("0")) {
                    from = 0;
                    to = 10000000;
                    try {
                        products = repoB.GetListBookByPrice(from, to, 1,10);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    pb[0] = true;
                    break;
                } else {
                    if (price[i].equals("1")) {
                        from = 0;
                        to = 100000;
                        try {
                            temp_book_price = repoB.GetListBookByPrice(from, to, 1,10);
                            products.addAll(temp_book_price);

                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        pb[1] = true;
                    }
                    if (price[i].equals("2")) {
                        from = 100000;
                        to = 200000;
                        try {
                            temp_book_price = repoB.GetListBookByPrice(from, to, 1,10);
                            products.addAll(temp_book_price);

                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        pb[2] = true;
                    }
                    if (price[i].equals("3")) {
                        from = 200000;
                        to = 500000;
                        try {
                            temp_book_price = repoB.GetListBookByPrice(from, to, 1,10);
                            products.addAll(temp_book_price);

                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        pb[3] = true;
                    }
                    if (price[i].equals("4")) {
                        from = 500000;
                        to = 1000000;
                        try {
                            temp_book_price = repoB.GetListBookByPrice(from, to, 1,10);
                            products.addAll(temp_book_price);

                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }

                        pb[4] = true;
                    }
                    if (price[i].equals("5")) {
                        from = 1000000;
                        to = 10000000;
                        try {
                            temp_book_price = repoB.GetListBookByPrice(from, to, 1,10);
                            products.addAll(temp_book_price);

                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }

                        pb[5] = true;
                    }
                }
            }
        }
        if (price == null) {
            pb[0] = true;
        }
        if (cid_raw == null) {
            chid[0] = true;
        }
        if ((cidd_raw != null) && (cidd[0] != 0)) {
            chid[0] = false;
            for (int i = 1; i < chid.length; i++) {
                if (ischeck(i, cidd)) {
                    chid[i] = true;
                } else {
                    chid[i] = false;
                }
            }
        }

        req.setAttribute("data", categories);
        req.setAttribute("products", products);
        req.setAttribute("pp", pp);
        req.setAttribute("pb", pb);
        req.setAttribute("cid", cid);
        req.setAttribute("chid", chid);
        req.getRequestDispatcher("/client/Shopping.jsp").forward(req, resp);

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
