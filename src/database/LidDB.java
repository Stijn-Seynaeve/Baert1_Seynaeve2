package database;

import databag.Lid;
import database.connect.ConnectionManager;
import datatype.Geslacht;
import java.util.ArrayList;

import java.util.Date;
import exception.DBException;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class LidDB implements InterfaceLidDB {

    @Override
    public void toevoegenLid(Lid lid)  {
        try(Connection conn = ConnectionManager.getConnection();){
            try (PreparedStatement stmt = conn.prepareStatement(
                    "insert into lid(emailadres, geslacht, naam, opmerkingen, rijksregisternummer, start_lidmaatschap, telnr, voornaam) values(?,?,?,?,?,?,?,?)");) {
            
            stmt.setString(1, lid.getEmailadres());
            stmt.setString(2, lid.getGeslacht().toString());
            stmt.setString(3, lid.getNaam());
            stmt.setString(4, lid.getOpmerkingen());
            stmt.setString(5, lid.getRijksregisternummer());
            stmt.setString(6, lid.getStart_lidmaatschap().toString());
            stmt.setString(7, lid.getTelnr());
            stmt.setString(8, lid.getVoornaam());
            
            stmt.execute();
            
        } catch (SQLException sqlEx) {
                System.out.println("SQL-exception - statement");
            }
        } catch (SQLException sqlEx) {
            System.out.println("SQL-exception - connection");
        } catch (DBException db) {
            System.out.println(db.getMessage());
        }
        
    }

    @Override
    public void wijzigenLid(Lid lid)  {
                try(Connection conn = ConnectionManager.getConnection();){
            try (PreparedStatement stmt = conn.prepareStatement(
                    "update lid set emailadres = ?, geslacht= ?, naam=?, opmerkingen=?, rijksregisternummer, start_lidmaatschap=?, telnr=?, voornaam=?"+
                            " where rijksregisternummer = "+ lid.getRijksregisternummer());) {
            
            stmt.setString(1, lid.getEmailadres());
            stmt.setString(2, lid.getGeslacht().toString());
            stmt.setString(3, lid.getNaam());
            stmt.setString(4, lid.getOpmerkingen());
            stmt.setString(6, lid.getStart_lidmaatschap().toString());
            stmt.setString(7, lid.getTelnr());
            stmt.setString(8, lid.getVoornaam());
            stmt.execute();
            
        } catch (SQLException sqlEx) {
                System.out.println("SQL-exception - statement");
            }
        } catch (SQLException sqlEx) {
            System.out.println("SQL-exception - connection");
        } catch (DBException db) {
            System.out.println(db.getMessage());
        }
        
    }

    @Override
    public void uitschrijvenLid(String rr)  {
                        try(Connection conn = ConnectionManager.getConnection();){
            try (PreparedStatement stmt = conn.prepareStatement(
                    "update lid set eindelidmaatschap = ? "+
                            " where rijksregisternummer = " +rr);) {
            stmt.setString(1,LocalDateTime.now().toString());
            stmt.execute();
            
        } catch (SQLException sqlEx) {
                System.out.println("SQL-exception - statement");
            }
        } catch (SQLException sqlEx) {
            System.out.println("SQL-exception - connection");
        } catch (DBException db) {
            System.out.println(db.getMessage());
        }
        
      
    }

    @Override
    public Lid zoekLid(String rijksregisternummer)  {
                Lid returnLid = null;
                try (Connection conn = ConnectionManager.getConnection();) {
            // preparedStatement opstellen (en automtisch sluiten)
            try (PreparedStatement stmt = conn.prepareStatement(
                    "select * from lid "
                        + "where rijksregisternummer ="+ rijksregisternummer);) {
                // execute voert het SQL-statement uit

                stmt.execute();
                try(ResultSet r = stmt.getResultSet()){
                    Lid l = new Lid();
                    if(r.next()){
                        l.setEmailadres(r.getString("emailadres"));
                        //l.setGeslacht(r.getObject("geslacht"));
                        l.setNaam(r.getString("naam"));
                        l.setOpmerkingen(r.getString("opmerkingen"));
                        //l.setRijksregisternummer(r.getString("rijksregisternummer").toString());
                        //l.setStart_lidmaatschap(r.getString("start_lidmaatschap"));
                        l.setTelnr(r.getString("telnr"));
                        l.setVoornaam(r.getString("voornaam"));
                        returnLid = l;
                        
                    }
                }
                
            } catch (SQLException sqlEx) {
                System.out.println("SQL-exception - statement");
            }
        } catch (SQLException sqlEx) {
            System.out.println("SQL-exception - connection");
        } catch (DBException db) {
            System.out.println(db.getMessage());
        }
                
           return returnLid; 
    }

    @Override
    public ArrayList<Lid> zoekAlleLeden()  {
                ArrayList<Lid> l = new ArrayList<Lid>();
        try (Connection conn = ConnectionManager.getConnection();) {
            // preparedStatement opstellen (en automtisch sluiten)
            try (PreparedStatement stmt = conn.prepareStatement(
                    "select * from lid ");) {
                // execute voert het SQL-statement uit

                stmt.execute();
                
                try(ResultSet r = stmt.getResultSet()){
                    ResultSetMetaData rs = r.getMetaData();
                    int columnsNumber = rs.getColumnCount();
                
                    while(r.next()){
                        
                            Lid nieuw = new Lid();
                            //nieuw.setOpmerking(r.getString("opmerking"));
                            //nieuw.setRegistratienummer(r.getInt("registratienummer"));
                            //nieuw.setStandplaats((Standplaats)r.getObject("standplaats"));
                            //nieuw.setStatus((Status)r.getObject("Status"));
                        l.add(nieuw);
                        
                    }          
                
                }

            } catch (SQLException sqlEx) {
                System.out.println("SQL-exception - statement");
            }
        } catch (SQLException sqlEx) {
            System.out.println("SQL-exception - connection");
        } catch (DBException db) {
            System.out.println(db.getMessage());
        }
                

        
        return l;
    }

   
  
}
