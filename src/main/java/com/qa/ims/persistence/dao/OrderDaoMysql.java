package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.Order;

public class OrderDaoMysql implements Dao<Order> {
	
	public static final Logger LOGGER = Logger.getLogger(OrderDaoMysql.class);

	private String jdbcConnectionUrl;
	private String username;
	private String password;

	public OrderDaoMysql(String username, String password) {
		this.jdbcConnectionUrl = "jdbc:mysql:/104.155.21.66:3306/ims";
		this.username = username;
		this.password = password;
	}

	public OrderDaoMysql(String jdbcConnectionUrl, String username, String password) {
		this.jdbcConnectionUrl = jdbcConnectionUrl;
		this.username = username;
		this.password = password;
	}

	Order orderFromResultSet(ResultSet resultSet) throws SQLException {
		Long order_id = resultSet.getLong("Order_id");
		Long cust_id = resultSet.getLong("Customer_id");
		Double totalPrice = resultSet.getDouble("Tottal_Price");
		return new Order(order_id, cust_id, totalPrice);
	}

	@Override
	public List<Order> readAll() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select * from orders");) {
			ArrayList<Order> orders = new ArrayList<>();
			while (resultSet.next()) {
				orders.add(orderFromResultSet(resultSet));
			}
			return orders;
		} catch (SQLException e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}
	
	public Order readLatest() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY id DESC LIMIT 1");) {
			resultSet.next();
			return orderFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	@Override
	public Order create(Order order) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("insert into orders(Order_id, Customer_id, Tottal_Price) values('" + order.getOrder_id()
					+ "','" + order.getCust_id() + "','" + order.getTotalPrice() + "')");
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public Order readOrder(Long order_id) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT FROM orders where Order_id = " + order_id);) {
			resultSet.next();
			return orderFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Order update(Order order) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("update orders set Order_id ='" + order.getOrder_id() + "', Custoemr_id ='"
					+ order.getCust_id() + "' where Tottal_Price =" + order.getTotalPrice());
			return readOrder(order.getOrder_id());
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public void delete(long order_id) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("delete from orders where Order_id = " + order_id);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
	}
}