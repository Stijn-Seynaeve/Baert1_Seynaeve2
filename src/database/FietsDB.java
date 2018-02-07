package database;
import database.connect.ConnectionManager;
import exception.DBException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import databag.Fiets;
import datatype.Standplaats;
import datatype.Status;
import exception.DBException;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;



public class FietsDB implements InterfaceFietsDB {

    @Override
    public Integer toevoegenFiets(Fiets fiets)  {
        try (Connection conn = ConnectionManager.getConnection();) {
            // preparedStatement opstellen (en automtisch sluiten)
            try (PreparedStatement stmt = conn.prepareStatement(
                    "insert into fiets(rijksregisternummer, status, standplaats, opmerkingen) values(?,?,?,?)");) {
                // execute voert het SQL-statement uit
                
                stmt.setString(1, fiets.getRegistratienummer().toString());
                stmt.setString(2, fiets.getStatus().toString());
                stmt.setString(3, fiets.getStandplaats().toString());
                stmt.setString(4, fiets.getOpmerking());
                stmt.execute();
                
            } catch (SQLException sqlEx) {
                System.out.println("SQL-exception - statement");
            }
        } catch (SQLException sqlEx) {
            System.out.println("SQL-exception - connection");
        } catch (DBException db) {
            System.out.println(db.getMessage());
        }
        return 0;
    }

    @Override
    public void wijzigenToestandFiets(Integer regnr, Status status)  {
        try (Connection conn = ConnectionManager.getConnection();) {
            // preparedStatement opstellen (en automtisch sluiten)
            try (PreparedStatement stmt = conn.prepareStatement(
                    "update fiets set status = ?,"
                        + "where registratienummer ="+ regnr);) {
                // execute voert het SQL-statement uit
                stmt.setString(1, status.toString());
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
    public void wijzigenOpmerkingFiets(Integer regnr, String opmerking)  {
                try (Connection conn = ConnectionManager.getConnection();) {
            // preparedStatement opstellen (en automtisch sluiten)
            try (PreparedStatement stmt = conn.prepareStatement(
                    "update fiets set opmerkingen = ?,"
                        + "where registratienummer ="+ regnr);) {
                // execute voert het SQL-statement uit
                stmt.setString(1, opmerking);
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
    public Fiets zoekFiets(Integer regnr)  {
        Fiets returnFiets = null;
                try (Connection conn = ConnectionManager.getConnection();) {
            // preparedStatement opstellen (en automtisch sluiten)
            try (PreparedStatement stmt = conn.prepareStatement(
                    "select * from fiets "
                        + "where registratienummer ="+ regnr);) {
                // execute voert het SQL-statement uit

                stmt.execute();
                try(ResultSet r = stmt.getResultSet()){
                    Fiets f = new Fiets();
                    if(r.next()){
                        f.setRegistratienummer(regnr);
                        //f.setStandplaats());
                        //f.getStatus();
                        f.setOpmerking(r.getString("opmerkingen"));
                        returnFiets = f;
                        
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
                
           return returnFiets;     
    }

    @Override
    public ArrayList<Fiets> zoekAlleFietsen()  {

        ArrayList<Fiets> f = new ArrayList<Fiets>();
        try (Connection conn = ConnectionManager.getConnection();) {
            // preparedStatement opstellen (en automtisch sluiten)
            try (PreparedStatement stmt = conn.prepareStatement(
                    "select * from fiets ");) {
                // execute voert het SQL-statement uit

                stmt.execute();
                
                try(ResultSet r = stmt.getResultSet()){
                    ResultSetMetaData rs = r.getMetaData();
                    int columnsNumber = rs.getColumnCount();
                
                    while(r.next()){
                        
                            Fiets nieuw = new Fiets();
                            nieuw.setOpmerking(r.getString("opmerking"));
                            nieuw.setRegistratienummer(r.getInt("registratienummer"));
                            nieuw.setStandplaats((Standplaats)r.getObject("standplaats"));
                            nieuw.setStatus((Status)r.getObject("Status"));
                        f.add(nieuw);
                        
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
                

        
        return f;
    }


   
}
