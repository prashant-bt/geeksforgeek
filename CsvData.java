package demo.csv;

import java.io.*;
import java.sql.*;
 
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;
 
public class CsvData {
 
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/csv_database?autoReconnect=true&useSSL=false";
        String username = "root";
        String password = "pinku123";
 
        String csvFilePath = "C://Users/609136841/Downloads/Reviews-simple.csv";
 
        int batchSize = 20;
 
        Connection connection = null;
 
        ICsvBeanReader beanReader = null;
        CellProcessor[] processors = new CellProcessor[] {
                new NotNull(), 
                new NotNull(), 
                new ParseTimestamp(), 
                new ParseDouble(), 
                new Optional()
        };
 
        try {
            long start = System.currentTimeMillis();
 
            connection = DriverManager.getConnection(jdbcURL, username, password);
            connection.setAutoCommit(false);
 
            String sql = "INSERT INTO csv_table (result_time, granularity_period, object_name, cell_id, call_attemps) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
 
            beanReader = new CsvBeanReader(new FileReader(csvFilePath),
                    CsvPreference.STANDARD_PREFERENCE);
 
            beanReader.getHeader(true); 
 
            String[] header = {"Result Time", "Granularity Period", "Object Name", "Cell ID", "CallAttemps"};
            Review bean = null;
 
            int count = 0;
 
            while ((bean = beanReader.read(Review.class, header, processors)) != null) {
                String object_name = bean.getObjectName();
                Integer granularity_period = bean.getGranularityPeriod();
                Timestamp result_time = bean.getResultTime();
                Integer cell_id = bean.getCellId();
                Integer call_attemps = bean.getCallAttemps();
 
                statement.setString(1, object_name);
                statement.setInt(2, granularity_period);
 
                statement.setTimestamp(3, result_time);
 
                statement.setInt(4, cell_id);
 
                statement.setInt(5, call_attemps);
 
                statement.addBatch();
 
                if (count % batchSize == 0) {
                    statement.executeBatch();
                }
            }
 
            beanReader.close();
 
            statement.executeBatch();
 
            connection.commit();
            connection.close();
 
            long end = System.currentTimeMillis();
            System.out.println("Execution Time: " + (end - start));
        } catch (IOException ex) {
            System.err.println(ex);
        } catch (SQLException ex) {
            ex.printStackTrace();
 
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
 
    }
}