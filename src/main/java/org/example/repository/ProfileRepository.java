package org.example.repository;


import org.example.db.DataBase;
import org.example.dto.Profile;
import org.example.enums.GeneralStatus;
import org.example.enums.ProfileRole;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
@Repository
public class ProfileRepository {
    public Profile getProfileByPhoneAndPassword(String phone, String password) {
        Connection connection = null;
        try {
            connection = DataBase.getConnection();
            Statement statement = connection.createStatement();
            String sql = String.format("Select  * from profile where phone= '%s' and password = '%s' ;", phone, password);
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Profile profile = new Profile();
                profile.setId(resultSet.getInt("id"));
                profile.setName(resultSet.getString("name"));
                profile.setSurname(resultSet.getString("surname"));
                profile.setPhone(resultSet.getString("phone"));
                profile.setPassword(resultSet.getString("password"));
                profile.setRole(ProfileRole.valueOf(resultSet.getString("role")));
                profile.setStatus(GeneralStatus.valueOf(resultSet.getString("status")));
                return profile;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        } finally {

            try {
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
                System.exit(-1);
            }

        }
        return null;
    }

    public Profile getProfileByPhone(String phone) {
        Connection connection = null;
        try {
            connection = DataBase.getConnection();
            Statement statement = connection.createStatement();
            String sql = String.format("Select  * from profile where phone= '%s';", phone);
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Profile profile = new Profile();
                profile.setId(resultSet.getInt("id"));
                profile.setName(resultSet.getString("name"));
                profile.setSurname(resultSet.getString("surname"));
                profile.setPhone(resultSet.getString("phone"));
                profile.setPassword(resultSet.getString("password"));
                profile.setRole(ProfileRole.valueOf(resultSet.getString("role")));
                profile.setStatus(GeneralStatus.valueOf(resultSet.getString("status")));
                return profile;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        } finally {

            try {
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
                System.exit(-1);
            }

        }
        return null;
    }

    public Boolean isPhoneExist(String phone) {
        Connection connection = null;
        try {
            connection = DataBase.getConnection();
            Statement statement = connection.createStatement();
            String sql = String.format("Select  id from profile where phone= '%s';", phone);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        } finally {

            try {
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
                System.exit(-1);
            }

        }
        return false;
    }

    public Integer saveProfile(Profile profile) {
        Connection connection = null;
        try {
            connection = DataBase.getConnection();
            String sql = "insert into profile(name,surname,phone,password,role,status,created_date) " +
                    "values ('%s','%s','%s','%s','%s','%s','%s');";
            sql = String.format(sql, profile.getName(), profile.getSurname(), profile.getPhone(), profile.getPassword(),
                    profile.getRole().name(), profile.getStatus().name(), profile.getCreatedDate());
            Statement statement = connection.createStatement();

            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        } finally {

            try {
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
                System.exit(-1);
            }

        }
        return 0;
    }


    public LinkedList<Profile> getProfileList() {
        try (Connection connection = DataBase.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from profile");
            LinkedList<Profile> profileList = new LinkedList<>();
            while (resultSet.next()) {
                Profile profile = new Profile();
                profile.setId(resultSet.getInt("id"));
                profile.setName(resultSet.getString("name"));
                profile.setSurname(resultSet.getString("surname"));
                profile.setPhone(resultSet.getString("phone"));
                profile.setPassword(resultSet.getString("password"));
                profile.setRole(ProfileRole.valueOf(resultSet.getString("role")));
                profile.setStatus(GeneralStatus.valueOf(resultSet.getString("status")));

                profileList.add(profile);
            }
            return profileList;
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return null;
    }

    public Integer changeProfileStatus(String phone, GeneralStatus status) {
        try (Connection connection = DataBase.getConnection()) {
            String sql = String.format("update profile set status = '%s' where phone = '%s'", status.name(), phone);
            Statement statement = connection.createStatement();
            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return 0;
    }
}
