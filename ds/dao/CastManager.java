package edu.neu.cs5200.msn.ds.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import edu.neu.cs5200.msn.ds.model.Movie;
import edu.neu.cs5200.msn.ds.model.Cast;
import edu.neu.cs5200.msn.ds.model.Actor;

public class CastManager {

	DataSource ds;
	
	public CastManager()
	{
	  try {
		Context ctx = new InitialContext();
		ds = (DataSource)ctx.lookup("java:comp/env/jdbc/MovieSocialNetworkDB");
		System.out.println(ds);
	  } catch (NamingException e) {
		e.printStackTrace();
	  }
	}
	
	// create a cast
	public void createCast(Cast cast)
	{
		String sql = "insert into cast values (null,?,?,?)";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, cast.getCharacterName());
			statement.setInt(2, cast.getActorId());
			statement.setInt(3, cast.getMovieId());
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// retrieve all cast
	public List<Cast> readAllCasts()
	{
		List<Cast> casts = new ArrayList<Cast>();
		String sql = "select * from cast";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				Cast cast = new Cast();
				cast.setId(results.getInt("id"));
				cast.setCharacterName(results.getString("characterName"));
				cast.setActorId(results.getInt("actorId"));
				cast.setMovieId(results.getInt("movieId"));
				casts.add(cast);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return casts;
	}
	
	public List<Cast> readAllCastsForActor(int actorId)
	{
		List<Cast> casts = new ArrayList<Cast>();
		String sql = "select * from cast where actorId = ?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, actorId);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				Cast cast =new Cast(); 
				cast.setId(results.getInt("id"));
				cast.setCharacterName(results.getString("characterName"));
				cast.setActorId(results.getInt("actorId"));
				cast.setMovieId(results.getInt("movieId"));
				casts.add(cast);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return casts;
	}
	
	public List<Cast> readAllCastsForMovie(int movieId)
	{
		List<Cast> casts = new ArrayList<Cast>();
		String sql = "select * from cast where movieId = ?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, movieId);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				Cast cast =new Cast(); 
				cast.setId(results.getInt("id"));
				cast.setCharacterName(results.getString("characterName"));
				cast.setActorId(results.getInt("actorId"));
				cast.setMovieId(results.getInt("movieId"));
				casts.add(cast);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return casts;
	}
	
	public Cast readCastForId(int id)
	{
		Cast cast = new Cast();
		
		String sql = "select * from cast where id = ?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			if(result.next())
			{
				cast.setId(result.getInt("id"));
				cast.setCharacterName(result.getString("characterName"));
				cast.setActorId(result.getInt("actorId"));
				cast.setMovieId(result.getInt("movieId"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return cast;
	}
	// update a cast by id
	public void updateCast(int id, Cast cast)
	{
		String sql = "update cast set charactorName=?, actorId=?, movieId=? where id=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, cast.getCharacterName());
			statement.setInt(2, cast.getActorId());
			statement.setInt(3, cast.getMovieId());
			statement.setInt(4, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	// delete a cast by id
	public void deleteCast(int id)
	{
		String sql = "delete from cast where id=?";
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
