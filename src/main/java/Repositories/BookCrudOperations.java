package Repositories;import Models.Book;import Settings.DataBaseConnection;import java.sql.*;import java.util.ArrayList;import java.util.Date;import java.util.List;public class BookCrudOperations implements CrudOperations<Book> {    private Connection connection;    public BookCrudOperations(){        DataBaseConnection.ManagingDataBaseConnection managingDataBaseConnection = new DataBaseConnection.ManagingDataBaseConnection();        this.connection = managingDataBaseConnection.getConnection();    }      @Override    public List<Book> findAll() {        List<Book> booksList = new ArrayList<>();        try {            String query = "SELECT * FROM book";            try (PreparedStatement statement = connection.prepareStatement(query);                 ResultSet resultSet = statement.executeQuery()){                while (resultSet.next()){                    Book book = mapResultSetToBook(resultSet);                    booksList.add(book);                }            }        } catch (SQLException e){            throw new RuntimeException(e);        }        return booksList;    }    @Override    public List<Book> saveAll(List<Book> toSave){        List<Book> savedBooks = new ArrayList<>();        try {            connection.setAutoCommit(false);            String insertQuery = "INSERT INTO book (id, bookName, pageNumbers, topic, releaseDate, authorID, status) VALUES (?, ?, ?, ?, ?, ?, ?)";            try(PreparedStatement statement = connection.prepareStatement(insertQuery)){               for (Book book : toSave){                   statement.setInt(1, Integer.parseInt(book.getId()));                   statement.setString(2, book.getBookName());                   statement.setInt(3, book.getPageNumbers());                   statement.setString(4, String.valueOf(book.getTopic()));                   statement.setDate(5, new java.sql.Date(book.getReleaseDate().getYear()));                   statement.setInt(6, book.getAuthorID());                   statement.setString(7, book.getStatus());                   statement.addBatch();               }               int[] results = statement.executeBatch();               connection.commit();               for (int result : results){                   if (result == Statement.EXECUTE_FAILED){                       throw new SQLException("Batch execution failed.");                   }               }               savedBooks.addAll(toSave);            }        } catch (SQLException e){            throw new RuntimeException(e);        }finally {            try{                connection.setAutoCommit(true);            }catch (SQLException e){                throw new RuntimeException(e);            }        }        return savedBooks;    }    @Override    public Book save(Book toSave){        try {            String insertQuery = "INSERT INTO book (id, bookName, pageNumbers, topic, releaseDate, authorID, status) VALUES (?, ?, ?, ?, ?, ?, ?)";            try (PreparedStatement statement = connection.prepareStatement(insertQuery)){                statement.setString(1, String.valueOf(Integer.parseInt(toSave.getId())));                statement.setString(2, toSave.getBookName());                statement.setInt(3, toSave.getPageNumbers());                statement.setString(4, String.valueOf(toSave.getTopic()));                statement.setDate(5,new java.sql.Date(toSave.getReleaseDate().getYear()));                statement.setInt(6, toSave.getAuthorID());                statement.setString(7, toSave.getStatus());                statement.executeUpdate();            }        }catch (SQLException e){            throw new RuntimeException(e);        }        return toSave;    }    @Override    public Book delete (Book toDelete){        try{            String deleteQuery = "DELETE FROM book WHERE id = ?";            try (PreparedStatement statement = connection.prepareStatement(deleteQuery)){                statement.setString(1, toDelete.getId());                statement.executeUpdate();            }        }catch (SQLException e){            throw new RuntimeException(e);        }        return toDelete;    }    private Book mapResultSetToBook(ResultSet resultSet) throws SQLException {        String id = resultSet.getString("id");        String bookName = resultSet.getString("bookName");        int pageNumbers = resultSet.getInt("pageNumbers");        String topicString = resultSet.getString("topic");        Book.Topic topic = Book.Topic.valueOf(topicString);        Date releaseDate = resultSet.getDate("releaseDate");        int authorID = resultSet.getInt("authorID");        String status = resultSet.getString("status");        return new Book(id, bookName, pageNumbers, topic, releaseDate, authorID, status);    }}