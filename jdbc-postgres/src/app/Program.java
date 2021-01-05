package app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import db.DB;
import entities.Order;
import entities.OrderStatus;
import entities.Product;

public class Program {

	public static void main(String[] args) throws SQLException {
		
		Connection conn = DB.getConnection();
	
		Statement st = conn.createStatement();
			
		ResultSet rs = st.executeQuery("select * from tb_order");
			
		while (rs.next()) {
			Order o = getOrder(rs);
			System.out.println(o);
		}
	}

	private static Order getOrder(ResultSet rs) throws SQLException {
		return new Order(
				rs.getLong("id"), rs.getDouble("latitude"), rs.getDouble("longitude"),
				rs.getTimestamp("moment").toInstant(), OrderStatus.values()[rs.getInt("status")],
				new ArrayList<>()
		);
	}


	private static Product getProduct(ResultSet rs) throws SQLException {
		return new Product(
				rs.getLong("id"), rs.getString("name"),
				rs.getDouble("price"), rs.getString("description"),
				rs.getString("image_uri")
		);
	}
}
