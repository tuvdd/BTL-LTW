package repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import models.Book;
import models.dtos.AdminBookView;
import models.dtos.GetBooksDTO;

public class BookRepo extends Repo<Book> {
	public List<Book> getAll(int page, int size) {
		List<Book> books = new ArrayList<>();
		CreateConnection();
		try {
			sql = "SELECT * FROM books ";

			if (page > 0 && size > 0) {
				int offset = (page - 1) * size;
				sql += " OFFSET ? LIMIT ? ;";
				statement = connection.prepareStatement(sql);
				statement.setInt(1, offset);
				statement.setInt(2, size);
			} else {
				sql += " ;";
				statement = connection.prepareStatement(sql);
			}
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Book book = setObjectFromResultSet(resultSet);
				books.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseConnection();
		}
		return books;
	}

	public List<Book> getAll(int page, int size, boolean status) {
		List<Book> books = new ArrayList<>();
		CreateConnection();
		try {
			sql = "SELECT * FROM books WHERE status = " + status + " ";

			if (page > 0 && size > 0) {
				int offset = (page - 1) * size;
				sql += " OFFSET ? LIMIT ? ;";
				statement = connection.prepareStatement(sql);
				statement.setInt(1, offset);
				statement.setInt(2, size);
			} else {
				sql += " ;";
				statement = connection.prepareStatement(sql);
			}
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Book book = setObjectFromResultSet(resultSet);
				books.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseConnection();
		}
		return books;
	}

	public List<Book> searchBooks(String searchQuery, int page, int size) {
		List<Book> books = new ArrayList<>();
		CreateConnection();
		try {
			String sql = "SELECT * FROM books WHERE name LIKE ? OR author LIKE ?";
			int offset = (page - 1) * size;
			sql += " OFFSET ? LIMIT ? ;";
			statement = connection.prepareStatement(sql);
			String searchParam = "%" + searchQuery + "%";
			statement.setString(1, searchParam);
			statement.setString(2, searchParam);
			statement.setInt(3, offset);
			statement.setInt(4, size);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Book book = setObjectFromResultSet(resultSet);
				books.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseConnection();
		}
		return books;
	}

	public int getCountSearchBook(String searchQuery, String urldanhmuc) {
		int res = 0;
		List<Object> param = new ArrayList<>();
		
		if (urldanhmuc == null) {
			sql = "SELECT count(*) FROM books WHERE status = TRUE ";
		} else {
			sql = "SELECT count(*) FROM books JOIN categories ON books.category_id = categories.id WHERE categories.url = ? AND books.status = TRUE AND categories.status = TRUE";
			param.add(urldanhmuc);
		}
		sql += "and books.name LIKE ? OR author LIKE ?";
		String searchParam = "%" + searchQuery + "%";
		param.add(searchParam);
		param.add(searchParam);
		try {
			CreateConnection();
			statement = connection.prepareStatement(sql);
			for(int i = 0; i < param.size(); i++) {
				statement.setObject(i+1, param.get(i));
			}
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

	public List<Book> get4LastestBooks() {
		List<Book> books = new ArrayList<>();
		CreateConnection();
		try {
			sql = "SELECT * FROM books ORDER BY create_time DESC LIMIT 4;";
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Book book = setObjectFromResultSet(resultSet);
				books.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseConnection();
		}
		return books;
	}

	public Book getById(UUID id) throws Exception {
		Book book = null;
		CreateConnection();
		if (id == null)
			throw new Exception("ID = null");
		try {
			sql = "SELECT * FROM books WHERE id=?";
			statement = connection.prepareStatement(sql);
			statement.setObject(1, id);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				book = setObjectFromResultSet(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseConnection();
		}
		return book;
	}

	public Book getById(UUID id, boolean status) throws Exception {
		Book book = null;
		CreateConnection();
		if (id == null)
			throw new Exception("ID = null");
		try {
			sql = "SELECT * FROM books WHERE id=? AND status = ?;";
			statement = connection.prepareStatement(sql);
			statement.setObject(1, id);
			statement.setBoolean(2, status);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				book = setObjectFromResultSet(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseConnection();
		}
		return book;
	}

	public List<Book> GetListBookByPrice(double fromMoney, double toMoney, int page, int size) {
		List<Book> books = new ArrayList<>();
		CreateConnection();
		try {
			sql = "SELECT * FROM books WHERE price>=? AND price<=? AND status = true";
			if (page > 0 && size > 0) {
				sql += " OFFSET ? LIMIT ? ;";
				statement = connection.prepareStatement(sql);
				statement.setDouble(1, fromMoney);
				statement.setDouble(2, toMoney);

				statement.setInt(3, (page - 1) * size);
				statement.setInt(4, size);
			} else {
				sql = " ;";
				statement = connection.prepareStatement(sql);
				statement.setDouble(1, fromMoney);
				statement.setDouble(2, toMoney);

			}
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Book book = setObjectFromResultSet(resultSet);
				books.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseConnection();
		}
		return books;
	}

	public List<Book> GetListBookByCategoryID(String uuid, int page, int size) {
		List<Book> books = new ArrayList<>();
		CreateConnection();
		try {
			sql = "SELECT * FROM books WHERE category_id=? AND status = true;";
			if (page == -1 || size == -1) {
				int offset = (page - 1) * size;
				sql += " OFFSET ? LIMIT ? ;";
				statement = connection.prepareStatement(sql);
				statement.setObject(1, UUID.fromString(uuid));
				statement.setInt(2, offset);
				statement.setInt(3, size);
			} else {
				sql += ";";
				statement = connection.prepareStatement(sql);
				statement.setObject(1, UUID.fromString(uuid));
			}

			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Book book = setObjectFromResultSet(resultSet);
				books.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseConnection();
		}
		return books;
	}

	public int add(Book book) throws Exception {
		int rowsAffected = 0;
		CreateConnection();

		if (book.name == null || book.image == null) {
			throw new Exception("Thiếu dữ liệu");
		}

		try {
			sql = "INSERT INTO books (id, name, image, author, release_year, category_id, price, promote_price, description, sub_description, status, create_time, create_by, last_update_time, last_update_by) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			statement = connection.prepareStatement(sql);
			statement.setObject(1, UUID.randomUUID());
			statement.setString(2, book.name);
			statement.setBytes(3, book.image);
			statement.setString(4, book.author);
			statement.setInt(5, book.release_year);
			statement.setObject(6, book.category_id);
			statement.setDouble(7, book.price);
			statement.setDouble(8, book.promote_price);
			statement.setString(9, book.description);
			statement.setString(10, book.sub_description);
			statement.setBoolean(11, book.status);
			statement.setTimestamp(12, book.create_time);
			statement.setObject(13, book.create_by);
			statement.setTimestamp(14, book.last_update_time);
			statement.setObject(15, book.last_update_by);
			rowsAffected = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseConnection();
		}
		return rowsAffected;
	}

	public int update(Book book) {
		int rowsAffected = 0;
		CreateConnection();
		try {
			sql = "UPDATE books SET name=?, author=?, release_year=?, category_id=?, price=?, promote_price=?, description=?, sub_description=?, status=?, last_update_time=?, last_update_by=? WHERE id=?;";
			statement = connection.prepareStatement(sql);
			statement.setString(1, book.name);
			statement.setString(2, book.author);
			statement.setInt(3, book.release_year);
			statement.setObject(4, book.category_id);
			statement.setDouble(5, book.price);
			statement.setDouble(6, book.promote_price);
			statement.setString(7, book.description);
			statement.setString(8, book.sub_description);
			statement.setBoolean(9, book.status);
			statement.setTimestamp(10, book.last_update_time);
			statement.setObject(11, book.last_update_by);
			statement.setObject(12, book.id);
			rowsAffected = statement.executeUpdate();
			if (book.image != null)
				if (book.image.length > 0) {
					sql = "UPDATE books SET image=? WHERE id=?;";
					statement.setBytes(1, book.image);
					statement.setObject(2, book.id);
					rowsAffected += statement.executeUpdate();
				}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseConnection();
		}
		return rowsAffected;
	}

	public int delete(UUID id) {
		int rowsAffected = 0;
		CreateConnection();
		try {
			sql = "DELETE FROM books WHERE id=?;";
			statement = connection.prepareStatement(sql);
			statement.setObject(1, id);
			rowsAffected = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseConnection();
		}
		return rowsAffected;
	}

	@Override
	protected Book setObjectFromResultSet(ResultSet resultSet) throws SQLException {
		Book book = new Book();
		book.id = UUID.fromString(resultSet.getString("id"));
		book.name = resultSet.getString("name");
		book.image = resultSet.getBytes("image");
		book.author = resultSet.getString("author");
		book.release_year = resultSet.getInt("release_year");
		book.category_id = UUID.fromString(resultSet.getString("category_id"));
		book.price = resultSet.getDouble("price");
		book.promote_price = resultSet.getDouble("promote_price");
		book.description = resultSet.getString("description");
		book.sub_description = resultSet.getString("sub_description");
		book.status = resultSet.getBoolean("status");
		book.create_time = resultSet.getTimestamp("create_time");
		book.create_by = resultSet.getString("create_by") == null ? null
				: UUID.fromString(resultSet.getString("create_by"));
		book.last_update_time = resultSet.getTimestamp("last_update_time");
		book.last_update_by = resultSet.getString("last_update_by") == null ? null
				: UUID.fromString(resultSet.getString("last_update_by"));
		;
		return book;
	}

	public List<AdminBookView> GetsAdminBookView(int page, int size) throws SQLException {
		List<AdminBookView> response = null;
		try {
			String sql = "SELECT b.id id, " + "          b.name name, " + "          b.image image, "
					+ "          b.author author, " + "          b.release_year release_year, "
					+ "          b.category_id category_id, " + "          c.name category_name, "
					+ "          b.price price, " + "          b.promote_price promote_price, "
					+ "          b.description description, " + "          b.sub_description sub_description, "
					+ "          b.status status, " + "          b.create_time create_time, "
					+ "          b.create_by create_by, " + "          ac.name create_by_name, "
					+ "          b.last_update_time last_update_time, " + "          b.last_update_by last_update_by, "
					+ "          au.name last_update_by_name " + "     FROM books b "
					+ "LEFT JOIN categories c ON b.category_id = c.id " + "LEFT JOIN admins ac ON ac.id = b.create_by "
					+ "LEFT JOIN admins au ON au.id = b.last_update_by  " + " ORDER BY b.name " + "    LIMIT " + size
					+ " " + "   OFFSET " + ((page - 1) * size) + " " + " ;";
			CreateConnection();
			statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			response = new ArrayList<>();

			while (rs.next()) {
				try {
					AdminBookView bfd = new AdminBookView();
					bfd.id = UUID.fromString(rs.getString("id"));
					bfd.name = rs.getString("name");
					bfd.image = rs.getBytes("image");
					bfd.author = rs.getString("author");
					bfd.release_year = rs.getInt("release_year");
					bfd.category_id = UUID.fromString(rs.getString("category_id"));
					bfd.category_name = rs.getString("category_name");
					bfd.price = rs.getDouble("price");
					bfd.promote_price = rs.getDouble("promote_price");
					bfd.description = rs.getString("description");
					bfd.sub_description = rs.getString("sub_description");
					bfd.status = rs.getBoolean("status");
					bfd.create_time = rs.getTimestamp("create_time");
					try {
						bfd.create_by = UUID.fromString(rs.getString("create_by"));
					} catch (Exception e) {
						bfd.create_by = null;
					}
					bfd.create_by_name = rs.getString("create_by_name");
					bfd.last_update_time = rs.getTimestamp("last_update_time");
					try {
						bfd.last_update_by = UUID.fromString(rs.getString("last_update_by"));
					} catch (Exception e) {
						bfd.last_update_by = null;
					}
					bfd.last_update_by_name = rs.getString("last_update_by_name");

					response.add(bfd);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseConnection();
		}

		return response;
	}

	public int getCount() {
		int res = 0;
		sql = "SELECT COUNT(*) FROM books ;";
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

	public int getCount(boolean status) {
		int res = 0;
		sql = "SELECT COUNT(*) FROM books WHERE status = ?;";
		try {
			CreateConnection();
			statement = connection.prepareStatement(sql);
			statement.setBoolean(1, status);
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

	public List<Book> getListFeaturedBook() {
		List<Book> books = new ArrayList<>();
		sql = "select books.* as total_rate from books join comments on book_id = BOOKS.id group by books.id";
		try {
			CreateConnection();
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Book book = setObjectFromResultSet(resultSet);
				books.add(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseConnection();
		}
		return books;
	}
	
	public float getAverageComment(String bookID) {
		float res = 0;
		sql = "SELECT COUNT(*) AS comment_count, SUM(rate) AS total_rate FROM books LEFT JOIN comments ON books.id = comments.book_id WHERE books.id = ?;";
		try {
			CreateConnection();
			statement = connection.prepareStatement(sql);
			statement.setObject(1, UUID.fromString(bookID));
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				int commentCount = resultSet.getInt("comment_count");
				int totalRate = resultSet.getInt("total_rate");
//				System.out.println(commentCount);
//				System.out.println(totalRate);
				res = (float) totalRate / (float) commentCount;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseConnection();
		}
		String formattedNumber = String.format("%.2f", res);
		return Float.parseFloat(formattedNumber);
	}
	
	public int getNumberComments(String bookID) {
		int res = 0;
		sql = "SELECT COUNT(*) AS comment_count FROM books LEFT JOIN comments ON books.id = comments.book_id WHERE books.id = ?;";
		try {
			CreateConnection();
			statement = connection.prepareStatement(sql);
			statement.setObject(1, UUID.fromString(bookID));
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				res = resultSet.getInt("comment_count");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseConnection();
		}
		return res;
	}

	public List<Book> getListSearchBooks(String searchQuery, int page, int size, String urldanhmuc, String filter, String priceMin, String priceMax) {
		List<Object> params = new ArrayList<Object>();

		// Xử lý điều kiện với urldanhmuc.
		if (urldanhmuc == null) {
			// Nếu urldanhmuc bị null, lấy tất cả sách.
			sql = "SELECT * FROM books WHERE status = TRUE ";
		} else {
			// Ngược lại, join với bảng categories với điều kiện categories.id =
			// books.category_id.
			sql = "SELECT * FROM books JOIN categories ON books.category_id = categories.id WHERE categories.url = ? AND books.status = TRUE AND categories.status = TRUE";
			params.add(urldanhmuc);
		}
		
		// Xử lý tìm kiếm
		sql += "and ( lower(books.name) LIKE lower(?) OR author LIKE lower(?) )";
		String searchParam = "%" + searchQuery + "%";
		params.add(searchParam);
		params.add(searchParam);
		
		// Xử lý điều kiện với priceMin và priceMax.
		if (priceMin != null) {
			sql += " AND COALESCE(promote_price, price) >= ?";
			params.add(Double.parseDouble(priceMin));
		}
		if (priceMax != null) {
			sql += " AND COALESCE(promote_price, price) <= ?";
			params.add(Double.parseDouble(priceMax));
		}

		// Xử lý điều kiện với filter.
		if (filter == null) {
			// Nếu filter bị null, mặc định lấy theo thời gian tạo mới nhất.
			sql += " ORDER BY create_time DESC";
		} else {
			switch (filter) {
			case "AZ":
				sql += " ORDER BY books.name ASC";
				break;
			case "ZA":
				sql += " ORDER BY books.name DESC";
				break;
			case "NEWEST":
				sql += " ORDER BY create_time DESC";
				break;
			case "OLDEST":
				sql += " ORDER BY create_time ASC";
				break;
			case "LOWEST":
				sql += " ORDER BY price ASC";
				break;
			case "HIGHEST":
				sql += " ORDER BY price DESC";
				break;
			default:
				break;
			}
		}
		
		//Phân trang
		int offset = (page - 1) * size;
		sql += " OFFSET ? LIMIT ? ;";
		params.add(offset);
		params.add(size);
		
		// Thực hiện câu truy vấn và trả về kết quả.
		List<Book> books = new ArrayList<Book>();
				try {
					CreateConnection();
					statement = connection.prepareStatement(sql);
					for (int i = 0; i < params.size(); i++) {
						statement.setObject(i + 1, params.get(i));
					}
					resultSet = statement.executeQuery();
					while (resultSet.next()) {
						Book book = setObjectFromResultSet(resultSet);
						books.add(book);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					CloseConnection();
				}
		return books;
	}
	
	public GetBooksDTO gets(String urldanhmuc, String page, String filter, String priceMin, String priceMax) {
		List<Object> params = new ArrayList<Object>();

		// Xử lý điều kiện với urldanhmuc.
		if (urldanhmuc == null) {
			// Nếu urldanhmuc bị null, lấy tất cả sách.
			sql = "SELECT * FROM books WHERE status = TRUE ";
		} else {
			// Ngược lại, join với bảng categories với điều kiện categories.id =
			// books.category_id.
			sql = "SELECT * FROM books JOIN categories ON books.category_id = categories.id WHERE categories.url = ? AND books.status = TRUE AND categories.status = TRUE";
			params.add(urldanhmuc);
		}

		// Xử lý điều kiện với priceMin và priceMax.
		if (priceMin != null) {
			sql += " AND COALESCE(promote_price, price) >= ?";
			params.add(Double.parseDouble(priceMin));
		}
		if (priceMax != null) {
			sql += " AND COALESCE(promote_price, price) <= ?";
			params.add(Double.parseDouble(priceMax));
		}

		// Xử lý điều kiện với filter.
		if (filter == null) {
			// Nếu filter bị null, mặc định lấy theo thời gian tạo mới nhất.
			sql += " ORDER BY create_time DESC";
		} else {
			switch (filter) {
			case "AZ":
				sql += " ORDER BY books.name ASC";
				break;
			case "ZA":
				sql += " ORDER BY books.name DESC";
				break;
			case "NEWEST":
				sql += " ORDER BY create_time DESC";
				break;
			case "OLDEST":
				sql += " ORDER BY create_time ASC";
				break;
			case "LOWEST":
				sql += " ORDER BY price ASC";
				break;
			case "HIGHEST":
				sql += " ORDER BY price DESC";
				break;
			default:
				break;
			}
		}

		// Thêm điều kiện phân trang.
		int limit = 9;
		int offset = page != null ? (Integer.parseInt(page) - 1) * limit : 0;
		sql += " LIMIT ? OFFSET ?";
		params.add(limit);
		params.add(offset);

		// Thực hiện câu truy vấn và trả về kết quả.
		List<Book> books = new ArrayList<Book>();
		int total = 0;
		try {
			CreateConnection();
			statement = connection.prepareStatement(sql);
			for (int i = 0; i < params.size(); i++) {
				statement.setObject(i + 1, params.get(i));
			}
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				books.add(setObjectFromResultSet(resultSet));
			}
			// Lấy số lượng sách trong cơ sở dữ liệu.
			sql = "SELECT COUNT(*) AS total FROM books WHERE status = TRUE";
			if (urldanhmuc != null) {
				sql = "SELECT COUNT(*) AS total FROM books JOIN categories ON books.category_id = categories.id WHERE categories.url = ? AND books.status = TRUE AND categories.status = TRUE";
			}
			statement = connection.prepareStatement(sql);
			if (urldanhmuc != null) {
				statement.setString(1, urldanhmuc);
			}
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				total = resultSet.getInt("total");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseConnection();
		}

		// Tạo đối tượng GetBooksDTO và trả về.
		GetBooksDTO result = new GetBooksDTO();
		result.setData(books);
		result.setPage(page != null ? Integer.parseInt(page) : 1);
		result.setSize(books.size());
		result.setTotal(total);
		return result;
	}

}