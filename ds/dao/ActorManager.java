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


import edu.neu.cs5200.msn.ds.model.Actor;

public class ActorManager {

	DataSource ds;
	
	public ActorManager()
	{
	  try {
		Context ctx = new InitialContext();
		ds = (DataSource)ctx.lookup("java:comp/env/jdbc/MovieSocialNetworkDB");
		System.out.println(ds);
	  } catch (NamingException e) {
		e.printStackTrace();
	  }
	}
	
	// create a actor
	public Actor createActor(Actor actor)
	{
		String sql = "insert into actor values (null,?,?,?)";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, actor.getFirstName());
			statement.setString(2, actor.getLastName());
			statement.setDate(3, actor.getDateOfBirth());
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	// retrieve all actor
	public List<Actor> readAllactors()
	{
		List<Actor> actors = new ArrayList<Actor>();
		String sql = "select * from actor";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				Actor actor = new Actor();
				actor.setId(results.getInt("id"));
				actor.setFirstName(results.getString("firstName"));
				actor.setLastName(results.getString("lastName"));
				actor.setDateOfBirth(results.getDate("dateOfBirth"));
				actors.add(actor);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return actors;
	}
	// retrieve a actor by id
	public Actor readActor(int id)
	{
		Actor actor = new Actor();
		
		String sql = "select * from actor where id = ?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			if(result.next())
			{
				actor.setId(result.getInt("id"));
				actor.setFirstName(result.getString("firstName"));
				actor.setLastName(result.getString("lastName"));
				actor.setDateOfBirth(result.getDate("dateOfBirth"));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return actor;
	}
	// update a actor by id
	public void updateActor(int id, Actor actor)
	{
		String sql = "update actor set firstName=?, lastName=?, dateOfBirth=? where id=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, actor.getFirstName());
			statement.setString(2, actor.getLastName());
			statement.setDate(3, actor.getDateOfBirth());
			statement.setInt(4, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	
	}
	// delete a actor by id
	public void deleteActor(int id)
	{
		String sql = "delete from actor where id=?";
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
