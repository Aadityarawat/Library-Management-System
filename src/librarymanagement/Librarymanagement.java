/*

 */
package librarymanagement;

/**
 *
 * @author ADee
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;


public class Librarymanagement{
    
    public static JFrame fr;
    public static void insertt(int id,String book,String author ){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/librarymanagement","root","admin");
            PreparedStatement ps = con.prepareStatement("insert into book (ID,book,author) values(?,?,?)");
            
            ps.setInt(1,id);
            ps.setString(2,book);
            ps.setString(3,author);
            JOptionPane.showMessageDialog(fr, "Insert Successfully");
            ps.executeUpdate();
        }catch(Exception e){
            JOptionPane.showMessageDialog(fr, e);
        }
    }
    
    
    
    public static void updatee(int id,String book,String author ){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/librarymanagement","root","admin");
            PreparedStatement ps = con.prepareStatement("Update book set book=?,author=? where ID=?");
            ps.setString(1,book);
            ps.setString(2,author);
            ps.setInt(3,id);
            JOptionPane.showMessageDialog(fr, "Updated Successfully");
            ps.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(fr, e);
        }
    }
    
    public static void deletee(int id){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/librarymanagement","root","admin");
            PreparedStatement ps = con.prepareStatement("delete from book where ID=?");
           
            ps.setInt(1,id);
            JOptionPane.showMessageDialog(fr, "Deleted Successfully");
            ps.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(fr, e);
        }
    }
    
    public static void admin(){
        
        try{
        JMenu menu, submenu; 
        JMenuItem i1, i2, i3, i4;

        JFrame fr = new JFrame("LibraryManagementSystem");
        JMenuBar mb = new JMenuBar();
        menu = new JMenu("Menu");
        submenu = new JMenu("Sub Menu");
        i1 = new JMenuItem("About Us");
        i3 = new JMenuItem("Record View");
        
        Label l1 = new Label("WELCOME TO ADMIN SIDE");
        l1.setBounds(100,10,150,30);
        Label id = new Label("ID");
        id.setBounds(70,100,90,30);
        Label title = new Label("Book Name");
        title.setBounds(70,150,90,30);
        Label author = new Label("Author Name");
        author.setBounds(70,200,90,30);
        
        TextField t1 = new TextField();
        t1.setBounds(160,100,150,30);
        TextField t2 = new TextField();
        t2.setBounds(160,150,150,30);
        TextField t3 = new TextField();
        t3.setBounds(160,200,150,30);
        
        Button insert = new Button("Add Book");
        insert.setBounds(70,250,60,20);
        Button update = new Button("Update Book");
        update.setBounds(140,250,80,20);
        Button delete = new Button("Delete Book");
        delete.setBounds(230,250,80,20);
        menu.add(i1);
        submenu.add(i3);
        menu.add(submenu);
        mb.add(menu);
        fr.setJMenuBar(mb);
        fr.add(l1);
        fr.add(id);
        fr.add(title);
        fr.add(author);
        fr.add(t1);
        fr.add(t2);
        fr.add(t3);
        fr.add(insert);

        fr.add(update);
        fr.add(delete);
        fr.setSize(400,400);
        fr.setLayout(null);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setVisible(true);
        
        i3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                viewrec();
            }
        });
        
        insert.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int idd = Integer.parseInt(t1.getText());
                String book = t2.getText();
                String author1 = t3.getText();
                insertt(idd,book, author1);
            }
        });
        
        update.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int idd = Integer.parseInt(t1.getText());
                String book = t2.getText();
                String author1 = t3.getText();
                updatee(idd,book, author1);
            }
        });
        
        delete.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int idd = Integer.parseInt(t1.getText());
                String book = t2.getText();
                String author1 = t3.getText();
                deletee(idd);
            }
        });
                
        }catch(Exception e){
           
            JOptionPane.showMessageDialog(fr, e);
        }
        
    }
    
    public static void viewrec(){        
     
        
    try {
            
            // Step 1: Connect to the database
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/librarymanagement","root","admin");
            // Step 2: Retrieve data from the database
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM book");

            // Step 3: Create JTable and DefaultTableModel
            JTable jTable = new JTable();
            DefaultTableModel tableModel = new DefaultTableModel();

            // Step 4: Populate the DefaultTableModel with data
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Add column names to the table model
            for (int column = 1; column <= columnCount; column++) {
                tableModel.addColumn(metaData.getColumnName(column));
            }

            // Add rows to the table model
            while (resultSet.next()) {
                Object[] row = new Object[columnCount];
                for (int column = 1; column <= columnCount; column++) {
                    row[column - 1] = resultSet.getObject(column);
                }
                tableModel.addRow(row);
            }

            // Step 5: Set the DefaultTableModel as the model for JTable
            jTable.setModel(tableModel);

            // Step 6: Create a JScrollPane if necessary
            JScrollPane jScrollPane = new JScrollPane(jTable);

            // Step 7: Display the JTable
            JFrame frame = new JFrame();
            frame.add(jScrollPane);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);

            // Step 8: Close the connection
            resultSet.close();
            statement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
    }
    
    public static void main(String [] args){
        
        JFrame fr = new JFrame("Login Page");
        fr.setSize(400,400);
        fr.setLayout(null);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Label l1,l2;

        l1 = new Label("UserName");
        l1.setBounds(50,100,80,30);
        l2 = new Label("Password");
        l2.setBounds(50,150,80,30);
        TextField t1;
        t1 = new TextField();
        t1.setBounds(150,100,100,30);
        JPasswordField t2 = new JPasswordField();
        t2.setBounds(150,150,100,30);
        Button btn = new Button("Submit");
        btn.setBounds(120,250,100,30);
        fr.add(l1);
        fr.add(t1);
        fr.add(l2);
        fr.add(t2);
        fr.add(btn);

        fr.setVisible(true);
        btn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                admin();   
            }
        });
    }
}
