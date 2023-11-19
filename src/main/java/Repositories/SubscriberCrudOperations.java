package Repositories;import Models.Subscriber;import java.sql.*;import java.util.ArrayList;import java.util.List;public class SubscriberCrudOperations implements CrudOperations<Subscriber> {    private Connection connection;    public SubscriberCrudOperations(Connection connection){        this.connection = connection;    }    @Override    public List<Subscriber> findAll() {        List<Subscriber> subscribersList = new ArrayList<>();        try {            String query = "SELECT * FROM subscribers";            try (PreparedStatement statement = connection.prepareStatement(query);                 ResultSet resultSet = statement.executeQuery()){                while (resultSet.next()){                    Subscriber subscribers= mapResultSetToSubscribers(resultSet);                    subscribersList.add(subscribers);                }            }        } catch (SQLException e){            throw new RuntimeException(e);        }        return subscribersList;    }    @Override    public List<Subscriber> saveAll(List<Subscriber> toSave){        List<Subscriber> savedSubscribers = new ArrayList<>();        try {            connection.setAutoCommit(false);            String insertQuery = "INSERT INTO subscribers (id, name, sex, username, password) VALUES (?, ?, ?, ?, ?)";            try(PreparedStatement statement = connection.prepareStatement(insertQuery)){                for (Subscriber subscribers : toSave){                    statement.setInt(1, Integer.parseInt(subscribers.getId()));                    statement.setString(2, subscribers.getName());                    statement.setString(3, String.valueOf(subscribers.getSex()));                    statement.setString(4, subscribers.getUsername());                    statement.setString(5, subscribers.getPassword());                    statement.addBatch();                }                int[] results = statement.executeBatch();                connection.commit();                for (int result : results){                    if (result == Statement.EXECUTE_FAILED){                        throw new SQLException("Batch execution failed.");                    }                }                savedSubscribers.addAll(toSave);            }        } catch (SQLException e){            throw new RuntimeException(e);        }finally {            try{                connection.setAutoCommit(true);            }catch (SQLException e){                throw new RuntimeException(e);            }        }        return savedSubscribers;    }    @Override    public Subscriber save(Subscriber toSave){        try {            String insertQuery = "INSERT INTO subscribers (id, name, sex, username, password) VALUES (?, ?, ?, ?, ?)";            try (PreparedStatement statement = connection.prepareStatement(insertQuery)){                statement.setInt(1, Integer.parseInt(toSave.getId()));                statement.setString(2, toSave.getName());                statement.setString(3, String.valueOf(toSave.getSex()));                statement.setString(4, toSave.getUsername());                statement.setString(5, toSave.getPassword());                statement.executeUpdate();            }        }catch (SQLException e){            throw new RuntimeException(e);        }        return toSave;    }    @Override    public Subscriber delete (Subscriber toDelete){        try{            String deleteQuery = "DELETE FROM subscribers WHERE id = ?";            try (PreparedStatement statement = connection.prepareStatement(deleteQuery)){                statement.setString(1, toDelete.getId());                statement.executeUpdate();            }        }catch (SQLException e){            throw new RuntimeException(e);        }        return toDelete;    }    private Subscriber mapResultSetToSubscribers(ResultSet resultSet) throws SQLException {        String id = resultSet.getString("id");        String name = resultSet.getString("name");        char sex = resultSet.getString("sex").charAt(0);        String username = resultSet.getString("username");        String password = resultSet.getString("password");        Subscriber subscribers = new Subscriber();        subscribers.setId(id);        subscribers.setName(name);        subscribers.setSex(sex);        subscribers.setUsername(username);        subscribers.setPassword(password);        return subscribers;    }}