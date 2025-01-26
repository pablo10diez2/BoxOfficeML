package dom;

import java.util.ArrayList;
import java.util.Objects;

public class Actor {
	private int id;
	private String knownForDepartment;
	private String name;
	private double popularity;
	private String picture;
	private ArrayList<String> films;
	
	
	public Actor(int id, String knownForDepartment, String name, double popularity, String picture,
			ArrayList<String> films) {
		super();
		this.id = id;
		this.knownForDepartment = knownForDepartment;
		this.name = name;
		this.popularity = popularity;
		this.picture = picture;
		this.films = films;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getKnownForDepartment() {
		return knownForDepartment;
	}


	public void setKnownForDepartment(String knownForDepartment) {
		this.knownForDepartment = knownForDepartment;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public double getPopularity() {
		return popularity;
	}


	public void setPopularity(double popularity) {
		this.popularity = popularity;
	}


	public String getPicture() {
		return picture;
	}


	public void setPicture(String picture) {
		this.picture = picture;
	}


	public ArrayList<String> getFilms() {
		return films;
	}


	public void setFilms(ArrayList<String> films) {
		this.films = films;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Actor other = (Actor) obj;
		return id == other.id;
	}


	@Override
	public String toString() {
		return "Actor [id=" + id + ", knownForDepartment=" + knownForDepartment + ", name=" + name + ", popularity="
				+ popularity + ", picture=" + picture + ", films=" + films + "]";
	}
	
	
	

		
}
