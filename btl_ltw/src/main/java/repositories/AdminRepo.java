package repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import models.Admin;
import utils.Utils;

public class AdminRepo extends Repo<Admin> {
	public int add(Admin admin) throws Exception {
		if (admin.name == null || admin.email == null || admin.phonenum == null || admin.cccd == null
				|| admin.username == null || admin.password == null) {
			throw new Exception("Thiếu dữ liệu");
		}
		if (Utils.isExistNotNumberChar(admin.phonenum)) {
			throw new Exception("Số điện thoại không hợp lệ");
		}

		if (Utils.isExistNotNumberChar(admin.cccd) || admin.cccd.length() != 12) {
			throw new Exception("CCCD không hợp lệ");
		}

		int res;
		try {
			CreateConnection();
			sql = "INSERT INTO admins (id, name, email, phonenum, cccd, username, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
			statement = connection.prepareStatement(sql);
			statement.setObject(1, UUID.randomUUID());
			statement.setString(2, admin.name);
			statement.setString(3, admin.email);
			statement.setString(4, admin.phonenum);
			statement.setString(5, admin.cccd);
			statement.setString(6, admin.username);
			statement.setString(7, admin.password);
			res = statement.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			CloseConnection();
		}

		return res;
	}

	public int update(Admin admin) throws Exception {
		if (admin.id == null || admin.name == null || admin.email == null || admin.phonenum == null
				|| admin.cccd == null || admin.username == null || admin.password == null) {
			throw new Exception("Thiếu dữ liệu");
		}
		if (Utils.isExistNotNumberChar(admin.phonenum)) {
			throw new Exception("Số điện thoại không hợp lệ");
		}

		if (Utils.isExistNotNumberChar(admin.cccd) || admin.cccd.length() != 12) {
			throw new Exception("CCCD không hợp lệ");
		}

		int res;
		try {
			CreateConnection();
			sql = "UPDATE admins SET name=?, email=?, phonenum=?, cccd=? WHERE id=?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, admin.name);
			statement.setString(2, admin.email);
			statement.setString(3, admin.phonenum);
			statement.setString(4, admin.cccd);
			statement.setObject(5, admin.id);
			res = statement.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			CloseConnection();
		}

		return res;
	}

	public int delete(UUID id) throws SQLException {
		int res;
		try {
			CreateConnection();
			sql = "DELETE FROM admins WHERE id=?";
			statement = connection.prepareStatement(sql);
			statement.setObject(1, id);
			res = statement.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			CloseConnection();
		}

		return res;
	}

	public List<Admin> getAll(int page, int pageSize) throws SQLException {
		List<Admin> admins = null;
		try {
			CreateConnection();
			sql = "SELECT * FROM admins";
			if (page > 0 && pageSize > 0) {
				int offset = (page - 1) * pageSize;
				sql += " OFFSET ? LIMIT ? ;";
				statement = connection.prepareStatement(sql);
				statement.setInt(1, offset);
				statement.setInt(2, pageSize);
			} else {
				sql += " ;";
				statement = connection.prepareStatement(sql);
			}
			resultSet = statement.executeQuery();

			admins = new ArrayList<>();
			while (resultSet.next()) {
				Admin admin = new Admin();
				admin.id = UUID.fromString(resultSet.getString("id"));
				admin.name = resultSet.getString("name");
				admin.email = resultSet.getString("email");
				admin.phonenum = resultSet.getString("phonenum");
				admin.cccd = resultSet.getString("cccd");
				admin.username = resultSet.getString("username");
				admin.password = resultSet.getString("password");
				admins.add(admin);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			CloseConnection();
		}

		return admins;
	}

	public Admin getById(UUID id) throws SQLException {
		Admin admin;
		try {
			CreateConnection();
			sql = "SELECT * FROM admins WHERE id=?;";
			statement = connection.prepareStatement(sql);
			statement.setObject(1, id);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				admin = new Admin();
				admin.id = UUID.fromString(resultSet.getString("id"));
				admin.name = resultSet.getString("name");
				admin.email = resultSet.getString("email");
				admin.phonenum = resultSet.getString("phonenum");
				admin.cccd = resultSet.getString("cccd");
				admin.username = resultSet.getString("username");
				admin.password = resultSet.getString("password");

			} else {
				admin = null;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			CloseConnection();
		}

		return admin;
	}

	public Admin getByUsernameAndPassword(String username, String password) throws SQLException {
		Admin admin;
		try {
			CreateConnection();
			sql = "SELECT * FROM admins WHERE username=? AND password=?;";
			statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			statement.setString(2, password);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				admin = new Admin();
				admin.id = UUID.fromString(resultSet.getString("id"));
				admin.name = resultSet.getString("name");
				admin.email = resultSet.getString("email");
				admin.phonenum = resultSet.getString("phonenum");
				admin.cccd = resultSet.getString("cccd");
				admin.username = resultSet.getString("username");
				admin.password = resultSet.getString("password");
			} else {
				admin = null;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			CloseConnection();
		}
		return admin;
	}

	public int changePass(UUID id, String oldPass, String newPass, String confirmNewpass) throws Exception {
		if (id == null || oldPass == null || newPass == null || confirmNewpass == null)
			throw new Exception("Thiếu dữ liệu");
		if (!confirmNewpass.equals(newPass))
			throw new Exception("Xác nhận mật khẩu không đúng");
		if (oldPass.equals(newPass))
			throw new Exception("Mật khẩu mới giống mạt khẩu cũ");
		int res;
		try {
			CreateConnection();
			sql = "UPDATE admins SET password = ? WHERE id = ? AND password = ? ;";
			statement = connection.prepareStatement(sql);
			statement.setString(1, newPass);
			statement.setObject(2, id);
			statement.setString(3, oldPass);
			res = statement.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			CloseConnection();
		}

		return res;
	}

	@Override
	protected Admin setObjectFromResultSet(ResultSet resultSet) throws SQLException {
		Admin admin = new Admin();
		admin.id = UUID.fromString(resultSet.getString("id"));
		admin.name = resultSet.getString("name");
		admin.email = resultSet.getString("email");
		admin.phonenum = resultSet.getString("phonenum");
		admin.cccd = resultSet.getString("cccd");
		return admin;
	}

	public int getCount() {
		int res = 0;
		sql = "SELECT COUNT(*) FROM admins ;";
		try {
			CreateConnection();
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				res = resultSet.getInt("count");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseConnection();
		}

		return res;
	}
}