package repositories.cart_demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderDao {
    private Connection con;

    private String query;
    private PreparedStatement pst;
    private ResultSet rs;


    public OrderDao(Connection con) {
        super();
        this.con = con;
    }

    public List<String> getOrderListId(String userID){
        List<String> orderListid = new ArrayList<>();
        try{
            query = "SELECT id FROM orders WHERE user_id = ?";
            pst = this.con.prepareStatement(query);
            pst.setObject(1, UUID.fromString(userID));
            rs = pst.executeQuery();
            while(rs.next()){
                String order_id = rs.getString("id");
                orderListid.add(order_id);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return  orderListid;
    }

}
