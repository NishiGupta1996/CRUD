import java.util.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class StudentDao {
   //Establish Connection
	public static Connection getConnection(){
		Connection con=null;
		try{
			//Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/DB","root","root123");
		}catch(Exception e){System.out.println(e+" hello");}
		return con;
	}
	//Create Operation
	public static int save(Student e){
		int status=0;
		try{
			Connection con=StudentDao.getConnection();
			PreparedStatement ps=con.prepareStatement("insert into student(stud_name,phNumber,email) values (?,?,?)");
			ps.setString(1,e.getName());
			ps.setString(2,e.getPhNumber());
			ps.setString(3,e.getEmail());
						
			status=ps.executeUpdate();
			
			con.close();
		}catch(Exception ex){ex.printStackTrace();}
		
		return status;
	}
	//Update operation
	public static int update(Student e){
		int status=0;
		try{
			Connection con=StudentDao.getConnection();
			PreparedStatement ps=con.prepareStatement("update student set stud_name=?,phNumber=?,email=? where stud_id=?");
			ps.setString(1,e.getName());
			ps.setString(2,e.getPhNumber());
			ps.setString(3,e.getEmail());
			ps.setInt(4,e.getId());
			
			status=ps.executeUpdate();
			
			con.close();
		}catch(Exception ex){ex.printStackTrace();}
		
		return status;
	}
	//Delete Operation
	public static int delete(int id){
		int status=0;
		try{
			Connection con=StudentDao.getConnection();
			PreparedStatement ps=con.prepareStatement("delete from student where stud_id=?");
			ps.setInt(1,id);
			status=ps.executeUpdate();
			
			con.close();
		}catch(Exception e){e.printStackTrace();}
		
		return status;
	}
	//View Operation
	public static Student getStudentById(int id){
		Student e=new Student();
		
		try{
			Connection con=StudentDao.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from student where stud_id=?");
			ps.setInt(1,id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setPhNumber(rs.getString(3));
				e.setEmail(rs.getString(4));
				
			}
			con.close();
		}catch(Exception ex){ex.printStackTrace();}
		
		return e;
	}
	//list all the students
	public static List<Student> getAllStudents(){
		List<Student> list=new ArrayList<Student>();
		
		try{
			Connection con=StudentDao.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from student");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Student e=new Student();
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setPhNumber(rs.getString(3));
				e.setEmail(rs.getString(4));
				list.add(e);
			}
			con.close();
		}catch(Exception e){e.printStackTrace();}
		
		return list;
	}
}
