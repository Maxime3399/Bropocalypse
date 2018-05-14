package fr.Maxime3399.Bropocalypse.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.Maxime3399.Bropocalypse.MainClass;

public class MySQLUtils {
	
	private static String database = MainClass.getConfiguration().getString("Database.Database");
    private static String url = "jdbc:mysql://"+MainClass.getConfiguration().getString("Database.Adress")+":"+MainClass.getConfiguration().getInt("Database.Port")+"/"+database+"?useSSL="+MainClass.getConfiguration().getString("Database.UseSSL")+"";
    private static String user = MainClass.getConfiguration().getString("Database.User");
    private static String passwd = MainClass.getConfiguration().getString("Database.Password");
    
    private static Statement state;
	
    public static boolean connect(){

        try {
        	
            Class.forName("com.mysql.jdbc.Driver");
            
        } catch (ClassNotFoundException e1) {
        	
            e1.printStackTrace();

        }

        try {

			Connection conn = DriverManager.getConnection(url, user, passwd);
            state = conn.createStatement();
            
        } catch (SQLException e) {
        	
            e.printStackTrace();
            
        }
        
        if(state == null) {
        	
        	return false;
        	
        }else {
        	
        	return true;
        	
        }

    }
    
    public static String getString(String table, String key, String id, String value) {
    	
		String result = null;
		
		try{
			
			ResultSet r = state.executeQuery("SELECT * FROM `"+database+"`.`"+table+"` WHERE "+key+" = '"+id+"'");
			r.next();
			result = r.getString(value);
			
		}catch(SQLException e){
			
			e.printStackTrace();
			
		}
		
		return result;
    	
    }
    
    public static int getInt(String table, String key, String id, String value) {
    	
		int result = 0;
		
		try{
			
			ResultSet r = state.executeQuery("SELECT * FROM `"+database+"`.`"+table+"` WHERE "+key+" = '"+id+"'");
			r.next();
			result = r.getInt(value);
			
		}catch(SQLException e){
			
			e.printStackTrace();
			
		}
		
		return result;
    	
    }
    
    public static boolean setString(String table, String key, String id, String type, String value) {
    	
    	boolean result = true;
    	
		try{
			
			state.executeUpdate("UPDATE `"+database+"`.`"+table+"` SET `"+type+"` = '"+value+"' WHERE `"+table+"`.`"+key+"` = '"+id+"';");
			
		}catch(SQLException e){
			
			e.printStackTrace();
			result = false;
			
		}
		
		return result;
    	
    }
    
    public static boolean setInt(String table, String key, String id, String type, int value) {
    	
    	boolean result = true;
    	
		try{
			
			state.executeUpdate("UPDATE `"+database+"`.`"+table+"` SET `"+type+"` = '"+value+"' WHERE `"+table+"`.`"+key+"` = '"+id+"';");
			
		}catch(SQLException e){
			
			e.printStackTrace();
			result = false;
			
		}
		
		return result;
    	
    }
    
	public static ArrayList<String> getValues(String table){
		
	    ArrayList<String> values = new ArrayList<String>();
	    
	    try {
	    	
	        ResultSet result = state.executeQuery("SELECT * FROM `"+table+"`");
	        
	        while(result.next()){
	        	
	            values.add(result.getString(1));
	            
	        }
	        
	    }catch (SQLException e) {
	    	
	        e.printStackTrace();
	        
	    }
	    
	    return values;
	}
	
	public static boolean execute(String command, boolean ignoreError) {
		
		boolean result = true;
		
		command = command.replaceAll("#database#", database);
		
		try{
			
			state.executeUpdate(command);
			
		}catch(SQLException e){
			
			if(!ignoreError) {
				
				e.printStackTrace();
				result = false;
				
			}
			
		}
		
		return result;
		
	}

}