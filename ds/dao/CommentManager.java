package edu.neu.cs5200.msn.ds.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date; 

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import edu.neu.cs5200.msn.ds.model.Movie;
import edu.neu.cs5200.msn.ds.model.Comment;
import edu.neu.cs5200.msn.ds.model.User;

public class CommentManager {

	DataSource ds;
	
	public CommentManager()
	{
	  try {
		Context ctx = new InitialContext();
		ds = (DataSource)ctx.lookup("java:comp/env/jdbc/MovieSocialNetworkDB");
		System.out.println(ds);
	  } catch (NamingException e) {
		e.printStackTrace();
	  }
	}
	
	// create a comment
	public void createComment(Comment comment)
	{
		String sql = "insert into comment values (null,?,?,?,?)";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, comment.getComment());
			statement.setDate(2, comment.getDate());
			statement.setString(3, comment.getUsername());
			statement.setInt(4, comment.getMovieId());
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// retrieve all comment
	public List<Comment> readAllComments()
	{
		List<Comment> comments = new ArrayList<Comment>();
		String sql = "select * from comment";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				Comment comment = new Comment();
				comment.setId(results.getInt("id"));
				comment.setComment(results.getString("comment"));
				comment.setDate(results.getDate("date"));
				comment.setUsername(results.getString("username"));
				comment.setMovieId(results.getInt("movieId"));
				comments.add(comment);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return comments;
	}
	
	public List<Comment> readAllCommentsForUsername(String username)
	{
		List<Comment> comments = new ArrayList<Comment>();
		String sql = "select * from comment where username = ?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				Comment comment =new Comment(); 
				comment.setId(results.getInt("id"));
				comment.setComment(results.getString("comment"));
				comment.setDate(results.getDate("date"));
				comment.setUsername(results.getString("username"));
				comment.setMovieId(results.getInt("movieId"));
				comments.add(comment);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return comments;
	}
	
	public List<Comment> readAllCommentsForMovie(int movieId)
	{
		List<Comment> comments = new ArrayList<Comment>();
		String sql = "select * from comment where movieId = ?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, movieId);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				Comment comment =new Comment(); 
				comment.setId(results.getInt("id"));
				comment.setComment(results.getString("comment"));
				comment.setDate(results.getDate("date"));
				comment.setUsername(results.getString("username"));
				comment.setMovieId(results.getInt("movieId"));
				comments.add(comment);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return comments;
	}
	
	public Comment readCommentForId(int id)
	{
		Comment comment = new Comment();
		
		String sql = "select * from comment where id = ?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			if(result.next())
			{
				comment.setId(result.getInt("id"));
				comment.setComment(result.getString("comment"));
				comment.setDate(result.getDate("date"));
				comment.setUsername(result.getString("username"));
				comment.setMovieId(result.getInt("movieId"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return comment;
	}
	// update a comment by id
	public void updateComment(int id, Comment comment)
	{
		String sql = "update comment set comment=?, date=?, username=?,movieId=? where id=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, comment.getComment());
			statement.setDate(2, comment.getDate());
			statement.setString(3, comment.getUsername());
			statement.setInt(4, comment.getMovieId());
			statement.setInt(5, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	// delete a comment by id
	public void deleteComment(int id)
	{
		String sql = "delete from comment where id=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
}
