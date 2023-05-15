package com.class8;

import com.class8.model.Person;
import com.class8.util.DBUtil;
import org.apache.log4j.Logger;
import org.example.Main;

import java.sql.*;

public class PersonManagement {
    final static Logger logger = Logger.getLogger(Main.class);

    private static void logInfoString(String message) {
        logger.info("This is info : " + message);
    }

    private static void logWarningString(String message) {
        logger.warn("This is warn : " + message);
    }
    public void addNewPerson(Person person) throws Exception {
        try {
            Connection conn =  DBUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(
                    "INSERT INTO Person(Name, Email, Address,Phone) VALUES (?,?,?,?)");
            pstmt.setString(1, person.getName());
            pstmt.setString(2, person.getEmail());
            pstmt.setString(3, person.getAddress());
            pstmt.setString(4, person.getPhone());

            int updated = pstmt.executeUpdate();
            if(updated > 0) {
                System.out.println("Insert Person success!!!");
            }


            pstmt.close();
            conn.close();
            logInfoString("User add new person");
            logWarningString("Connect DB timeout");

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void getAllPersons() {
        try {
            Connection conn = DBUtil.getConnection();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT Id, Name, Email, Address,Phone FROM Person");

            while (rs.next()) {
                Person person = new Person(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                );

                System.out.println(person.toString());
            }

            rs.close();
            stmt.close();
            conn.close();
            logInfoString("User view all");
            logWarningString("Connect DB timeout");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public Person getPersonByIdStored(int id) throws Exception {

        Person person = null;

        try {
            Connection conn =  DBUtil.getConnection();
            CallableStatement cstmt = conn.prepareCall("{call spGetSPerson(?)}");

            cstmt.setInt(1, id);

            ResultSet rs = cstmt.executeQuery();
            if(rs.next()) {
                person = new Person(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                );
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        logInfoString("User search person");

        return person;
    }
    public Person getPersonById(int id) throws Exception {

        Person person= null;

        try {
            Connection conn =  DBUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(
                    "SELECT Id, Name, Email, Address,Phone FROM Person WHERE Id = ?");
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                person = new Person(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                );
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return person;
    }
    public void updatePerson(int id) throws Exception {
        try {

            Person updatingPerson  = this.getPersonById(id);
            if(updatingPerson != null) {
                //input new data of Student
                updatingPerson .inputData();
                // UPDATE Student into Database

                Connection conn =  DBUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(
                        "UPDATE Person SET Name = ?, Email = ?, Address = ?,Phone = ? WHERE Id = ?");
                pstmt.setString(1, updatingPerson.getName());
                pstmt.setString(2, updatingPerson.getEmail());
                pstmt.setString(3, updatingPerson.getAddress());
                pstmt.setString(4, updatingPerson.getPhone());
                pstmt.setInt(5,updatingPerson.getId());

                int updated = pstmt.executeUpdate();
                if(updated > 0) {
                    System.out.println("Update Person success!!!");
                }

                pstmt.close();
                conn.close();
                logInfoString("User update ");
                logWarningString("Connect DB timeout");
            } else  {
                System.out.println("Person not found");
            }

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    public void deletePerson(int id) throws Exception {
        try {
            Person deletePerson  = this.getPersonById(id);
            if(deletePerson  != null) {

                Connection conn =  DBUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(
                        "DELETE FROM Person WHERE id = ? ");

                pstmt.setInt(1, id);
                int updated = pstmt.executeUpdate();
                if(updated > 0) {
                    System.out.println("Delete  Person success!!!");
                }

                pstmt.close();
                conn.close();
                logInfoString("User delete ");
                logWarningString("Connect DB timeout");
            } else  {
                System.out.println("Person not found");
            }

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
