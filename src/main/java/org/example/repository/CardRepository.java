package org.example.repository;


import org.example.db.DataBase;
import org.example.dto.Card;
import org.example.enums.GeneralStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;

@Repository
public class CardRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(Card card) {
        String sql = "insert into card (card_number, exp_date,balance,status,phone,created_date) values (?,?,?,?,?,?)";
        PreparedStatementSetter preparedStatement = ps -> {
            ps.setString(1, card.getCardNumber());
            ps.setString(2, String.valueOf(card.getExpDate()));
            ps.setDouble(3, card.getBalance());
            ps.setString(4, String.valueOf(card.getStatus()));
            ps.setString(5, card.getPhone());
            ps.setTimestamp(6, Timestamp.valueOf(card.getCreatedDate()));
        };
        return jdbcTemplate.update(sql, preparedStatement);

    }

    public Card getCardById(Integer id) {
        String sql = "select * from card where visible = true and  id = " + id;

        ResultSetExtractor<Card> extractor = new ResultSetExtractor<Card>() {
            @Override
            public Card extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    Card card = new Card();
                    card.setId(rs.getInt("id"));
                    card.setCardNumber(rs.getString("cardNumber"));
                    card.setExpDate(LocalDate.parse(rs.getString("expDate")));
                    card.setBalance(Double.valueOf("balance"));
                    card.setStatus(GeneralStatus.valueOf(rs.getString("status")));
                    card.setPhone(String.valueOf(rs.getTimestamp("createdDate")));

                    return card;
                }
                return null;
            }
        };

        Card card = jdbcTemplate.query(sql, extractor);
        return card;
    }

    public Boolean rechargeBalance(Integer cardId, Double balance) {
        try {
            Connection connection = DataBase.getConnection();

            String sql = String.format("update card set balance = %d where id = %d", balance, cardId);
            Statement statement = connection.createStatement();

            int n = statement.executeUpdate(sql);
            return n != 0 ? true : false;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return false;
    }

    public LinkedList<Card> getList() {
        LinkedList<Card> result = new LinkedList<>();
        try {
            Connection connection = DataBase.getConnection();
            String sql = "select * from card ";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Integer cardId = resultSet.getInt("id");
                String cardNumber = resultSet.getString("card_number");
                Double balance = resultSet.getDouble("balance");
                LocalDate expDate = resultSet.getDate("exp_date").toLocalDate();
                String status = resultSet.getString("status");
                String phone = resultSet.getString("phone");
                LocalDateTime createdDate = resultSet.getTimestamp("created_date").toLocalDateTime();

                Card card = new Card();
                card.setId(cardId);
                card.setCardNumber(cardNumber);
                card.setBalance(balance);
                card.setExpDate(expDate);
                card.setStatus(GeneralStatus.valueOf(status));
                card.setPhone(phone);
                card.setCreatedDate(createdDate);

                result.add(card);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result;
    }

    public Card getCardByNumber(String number) {
        try {
            Connection connection = DataBase.getConnection();
            String sql = "select * from card where visible = true and card_number = '" + number + "';";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                Integer cardId = resultSet.getInt("id");
                String cardNumber = resultSet.getString("card_number");
                Double balance = resultSet.getDouble("balance");
                LocalDate expDate = resultSet.getDate("exp_date").toLocalDate();
                String status = resultSet.getString("status");
                String phone = resultSet.getString("phone");
                LocalDateTime createdDate = resultSet.getTimestamp("created_date").toLocalDateTime();

                Card card = new Card();
                card.setId(cardId);
                card.setCardNumber(cardNumber);
                card.setBalance(balance);
                card.setExpDate(expDate);
                card.setStatus(GeneralStatus.valueOf(status));
                card.setPhone(phone);
                card.setCreatedDate(createdDate);
                return card;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    public int assignPhoneToCard(String phone, String cardNum) {
        try (Connection connection = DataBase.getConnection()) {

            String sql = String.format("update card set phone = '%s' where card_number ='%s'", phone, cardNum);
            Statement statement = connection.createStatement();
            return statement.executeUpdate(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public LinkedList<Card> getCardByProfilePhone(String phone) {
        try {
            Connection connection = DataBase.getConnection();
            String sql = "select * from card where visible = true and phone = '" + phone + "';";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            LinkedList<Card> cardList = new LinkedList<>();
            if (resultSet.next()) {
                Integer cardId = resultSet.getInt("id");
                String cardNumber = resultSet.getString("card_number");
                Double balance = resultSet.getDouble("balance");
                LocalDate expDate = resultSet.getDate("exp_date").toLocalDate();
                String status = resultSet.getString("status");
                LocalDateTime createdDate = resultSet.getTimestamp("created_date").toLocalDateTime();

                Card card = new Card();
                card.setId(cardId);
                card.setCardNumber(cardNumber);
                card.setBalance(balance);
                card.setExpDate(expDate);
                card.setStatus(GeneralStatus.valueOf(status));
                card.setPhone(phone);
                card.setCreatedDate(createdDate);

                cardList.add(card);
            }
            return cardList;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    public int updateCardStatus(String cardNum, GeneralStatus status) {
        try (Connection connection = DataBase.getConnection()) {
            String sql = String.format("update card set status = '%s' where  card_number = '%s'", status.name(), cardNum);

            Statement statement = connection.createStatement();
            return statement.executeUpdate(sql);

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return 0;
    }

    public int deleteCard(String cardNumber) {
        try (Connection connection = DataBase.getConnection()) {
            String sql = String.format("update card set visible = false where  card_number = '%s'", cardNumber);

            Statement statement = connection.createStatement();
            return statement.executeUpdate(sql);

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return 0;
    }

    public int updateCard(Card card) {
        try (Connection connection = DataBase.getConnection()) {
            String sql = String.format("update card set exp_date = '%s' where  card_number = '%s'", card.getExpDate(), card.getCardNumber());

            Statement statement = connection.createStatement();
            return statement.executeUpdate(sql);

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return 0;
    }

    public int refillCard(String cardNum, Double amount) {
        try (Connection connection = DataBase.getConnection()) {

            PreparedStatement ps = connection.prepareStatement("update card set balance = ? where card_number = ?");
            ps.setDouble(1, amount);
            ps.setString(2, cardNum);

            return ((PreparedStatement) ps).executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return 0;
    }


    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
