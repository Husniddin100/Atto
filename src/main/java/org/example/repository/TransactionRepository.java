package org.example.repository;


import org.example.db.DataBase;
import org.example.dto.Transaction;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
@Component
public class TransactionRepository {

    public int createTransaction(Transaction transaction) {
        try (Connection connection = DataBase.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "insert into transaction(card_id,terminal_id,amount,type,created_date) " +
                            "values (?,?,?,?,?)");
            statement.setInt(1, transaction.getCardId());

            if (transaction.getTerminalId() != null) {
                statement.setInt(2, transaction.getTerminalId());
            } else {
                statement.setObject(2, null);
            }

            statement.setDouble(3, transaction.getAmount());
            statement.setString(4, transaction.getTransactionType().name());
            statement.setTimestamp(5, Timestamp.valueOf(transaction.getCreatedDate()));

            int resultSet = statement.executeUpdate();
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;

    }
}
