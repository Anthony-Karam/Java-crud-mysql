package jdbcdemo;
import java.awt.*;  
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.ResultSet;
import java.sql.Statement;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;  
public class Driver {  
public static void main(String[] args) {  

    JFrame f= new JFrame("401");  
    	 JLabel l1,l2,l3;
    	 JTextField t11,t21,t31; 
    	    JButton b11,b21,b31,b41;
    	    //Buttons
    	    b11= new JButton("ADD");
    	    b11.setBounds(50,200,100,40);
    	    b21= new JButton("Upgrade");
    	    b21.setBounds(50,250,100,40);
    	    b31= new JButton("Delete");
    	    b31.setBounds(50,300,100,40);
    	    b41= new JButton("Get list ");
    	    b41.setBounds(50,350,100,40);
    	    //labels
    	    l3=new JLabel("Id:");  
    	    l3.setBounds(170,355, 100,30);  
    	    l1=new JLabel("Name:");  
    	    l1.setBounds(50,75, 100,30);  
    	    l2=new JLabel("Last Name:");  
    	    l2.setBounds(50,125, 100,30);
    	    //textfields
    	    t31=new JTextField();  
    	    t31.setBounds(190,355, 50,30);    
    	    t11=new JTextField();  
    	    t11.setBounds(50,100, 200,30);  
    	    t21=new JTextField();  
    	    t21.setBounds(50,150, 200,30); 
    	    
    	    //Action listener
    	    
//    	    b4.addActionListener(this);
    	    //add to Jframe
    	    f.add(l1);
    	    f.add(l2);
    	    f.add(l3);
    	    f.add(b11);
    	    f.add(b21);
    	    f.add(b31);
    	    f.add(b41);
    	    f.add(t11); f.add(t21);  
    	    f.add(t31);
    	    f.setSize(400,500);  
    	    f.setLayout(null);  
    	    f.setVisible(true);  
    	    
  //////////////ADD/////////////
    b11.addActionListener(new ActionListener(){  
	private PreparedStatement insert;
	public void actionPerformed(ActionEvent e){  
    	  String name =t11.getText();
    	  String lastName =t21.getText();
    	 
    	        try {
    	        	Connection myConn= DriverManager.getConnection("jdbc:mysql://localhost:3306/linda","root","newpassword");
    	        	myConn.createStatement();
    	        	insert =  myConn.prepareStatement("insert into records (name,lastName)values(?,?)");
    	            insert.setString(1,name);
    	            insert.setString(2,lastName);
    	            insert.executeUpdate();
    	            t11.setText("");
    	            t21.setText("");
    	        }
    	        catch(Exception exc) {
    	           	exc.printStackTrace();
    	        }

    };
    }) ; 
    
    
    ////////////////Select By ID///////////////
    b41.addActionListener(new ActionListener(){  
	public void actionPerformed(ActionEvent e){  
	String r=t31.getText();
		try
	    {
	      // create our mysql database connection
	
	      String myUrl = "jdbc:mysql://localhost:3306/linda";
	      Connection conn = DriverManager.getConnection(myUrl, "root", "newpassword");
	      
	      // our SQL SELECT query. 
	      // if you only need a few columns, specify them by name instead of using "*"
	      String query = "SELECT * FROM records WHERE id="+r+"";

	      // create the java statement
	      Statement st = conn.createStatement();
	      
	      // execute the query, and get a java resultset
	      ResultSet rs = st.executeQuery(query);
	      
	      // iterate through the java resultset
	      while (rs.next())
	      {
	        String firstName = rs.getString("name");
	        String lastName = rs.getString("lastName");
	        t11.setText(firstName);
	        t21.setText(lastName);
	        // print the results
	      }
	      st.close();
	    }
	    catch (Exception e1)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e1.getMessage());
	    }
    	

    };
    
    }) ;     
    
    ///////////////Delete///////////////
    
    b31.addActionListener(new ActionListener(){  
    	public void actionPerformed(ActionEvent e){  
    	String r=t31.getText();
    	try
    	    {
    	      // create our mysql database connection
    	
    	      String myUrl = "jdbc:mysql://localhost:3306/linda";
    	      Connection conn = DriverManager.getConnection(myUrl, "root", "newpassword");
    	      String sql = "DELETE FROM records WHERE id=?";
    	      
    	      PreparedStatement statement = conn.prepareStatement(sql);
    	      statement.setString(1, r);
    	      statement.executeUpdate();    
    	      conn.close();
    	      t11.setText("deleted");
    	      t21.setText("deleted");
    	    
    	    }
    	    catch (Exception e1)
    	    {
    	      System.err.println("Got an exception! ");
    	      System.err.println(e1.getMessage());
    	    }
        	

        };
        
        }) ;     
    
    
    b21.addActionListener(new ActionListener(){  
    	public void actionPerformed(ActionEvent e){  
    	String r=t31.getText();
    	String newName=t11.getText();
    	String newLastName=t21.getText();
    	try
    	    {
    	      // create our mysql database connection
    	
    	      String myUrl = "jdbc:mysql://localhost:3306/linda";
    	      Connection conn = DriverManager.getConnection(myUrl, "root", "newpassword");
    	      String sql = "UPDATE records SET name=?, lastName=? WHERE id=?";
    	      
    	      PreparedStatement statement = conn.prepareStatement(sql);
    	      statement.setString(1, newName);
    	      statement.setString(2, newLastName);
    	      statement.setString(3, r);
    	      statement.executeUpdate();
    	
    	      t11.setText("Updated");
    	      t21.setText("Updated");
    	    
    	    }
    	    catch (Exception e1)
    	    {
    	      System.err.println("Got an exception! ");
    	      System.err.println(e1.getMessage());
    	    }
        	

        };
        
        }) ;    

}};