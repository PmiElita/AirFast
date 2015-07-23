package com.pylypchak.airfast.statements;

public class CitySQLStatements {
	public static final String INSERT_CITY_SQL = "INSERT INTO city (name, country_id) VALUES (?,?)";
	public static final String SELECT_CITIES_BY_COUNTRY_ID_SQL ="SELECT * FROM city WHERE country_id=?";
	public static final String SELECT_CITY_BY_ID_SQL ="SELECT * FROM city WHERE id =?";
	public static final String SELECT_ALL_CITIES_SQL = "SELECT * FROM city";
	public static final String UPDATE_CITY_SQL = "UPDATE city SET country_id= ?, name = ? WHERE id =? ";
	
	public static final String[] INSERT_CITY_SQL_ORDER = { "name", "country_id" };
	public static final String[] SELECT_CITIES_BY_COUNTRY_ID_SQL_ORDER ={"country_id"};
	public static final String[] SELECT_CITY_BY_ID_SQL_ORDER ={"id"};
	public static final String[] UPDATE_CITY_SQL_ORDER= {"country_id", "name","id"};

}
