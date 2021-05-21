package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.reavture.utils.ConnectionUtils;
import com.revature.bank.Account;
import com.revature.bank.AccountStatus;
import com.revature.bank.Client;

public class ClientDOAIMP implements  ClientDAO {

	@Override
	public Client getClientByID(int id) {
		try (Connection conn = ConnectionUtils.getConnection()){
			String sql = "select * from clients where client_id = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);

			ResultSet r = statement.executeQuery();

			if(r.next())
			{
				return makeClient(r);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public Client getClientByUserAndPass(String username, String password) {
		try (Connection conn = ConnectionUtils.getConnection()){
			
			String sql = "select * from clients where username = ?and password = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			statement.setString(2, password);
			
			ResultSet r = statement.executeQuery();

			if(r.next())
			{
				return makeClient(r);
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean addClient(Client c) {
		try (Connection conn = ConnectionUtils.getConnection()){
			String sql = "insert into clients(username, password, firstname, lastname, address) "
					+ "values (?, ?, ?, ?, ?);"; //REMEMBER SMART ONE, INDEX STARTS AT 1 FOR SQL!!!
			
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, c.getUsername());
			statement.setString(2, c.getPassword());
			statement.setString(3, c.getFirstName());
			statement.setString(4, c.getLastName());
			statement.setString(5, c.getAddress());
			
			
			statement.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean removeClientByID(int id) {
		try (Connection conn = ConnectionUtils.getConnection()){
			
			String sql = "delete from clients where client_id =?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			
			statement.execute();
			
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	
	@Override
	public boolean removeClientByUserAndPassword(String username, String password) {
		try (Connection conn = ConnectionUtils.getConnection()){
			
			String sql = "delete from clients where username = ? and password = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1,username);
			statement.setString(2, password);
			
			statement.execute();
			return true;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
	public boolean updateClient(Client c) {
		try (Connection conn = ConnectionUtils.getConnection()){
			String sql = "update clients set username = ?, password = ?, firstname = ?, "
					+ "lastname = ?, address = ? where client_id = ?; ";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, c.getUsername());
			statement.setString(2, c.getPassword());
			statement.setString(3, c.getFirstName());
			statement.setString(4, c.getLastName());
			statement.setString(5, c.getAddress());
			statement.setInt(6, c.getClientID());
			
			statement.execute();
			return true;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	private Client makeClient(ResultSet r) {
		try {
			return new Client(r.getInt("client_id"), r.getString("username"), r.getString("password"), 
					r.getString("firstname"), r.getString("lastname"), r.getString("address"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
