package com.pylypchak.airfast.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.pylypchak.airfast.model.Ticket;
import com.pylypchak.airfast.statements.TicketSQLStatements;
import com.pylypchak.airfast.util.ConnectionManager;
import com.pylypchak.airfast.util.Transformer;

public class TicketDAO {
	public void saveTicket(Ticket ticket) throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
		PreparedStatement statement = connection
				.prepareStatement(TicketSQLStatements.INSERT_TICKET_SQL);
		Transformer.valueIntoPreparedStatement(statement, ticket,
				TicketSQLStatements.INSERT_TICKET_SQL_ORDER);
		statement.executeUpdate();
		}
	}

	public void updateTicket(Ticket ticket) throws SQLException,
	ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
	
PreparedStatement statement = connection
		.prepareStatement(TicketSQLStatements.UPDATE_TICKET_BY_ID_SQL);
Transformer.valueIntoPreparedStatement(statement, ticket,
		TicketSQLStatements.UPDATE_TICKET_BY_ID_SQL_ORDER);
statement.executeUpdate();
		}
}

	
	public List<Ticket> getTicketByUserId(int userId) throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
		List<Ticket> result = null;
		PreparedStatement statement = connection
				.prepareStatement(
						TicketSQLStatements.SELECT_TICKETS_BY_USER_ID_SQL);
		Ticket ticket = new Ticket();
		ticket.setUserId(userId);
		Transformer.valueIntoPreparedStatement(statement, ticket,
				TicketSQLStatements.SELECT_TICKETS_BY_USER_ID_SQL_ORDER);
		ResultSet resultSet = statement.executeQuery();
		result = Transformer
				.transformResultSetIntoList(resultSet, Ticket.class);
		return result;
		}
	}
	
	public Ticket getTicketById(int id) throws ReflectiveOperationException, SQLException{
		try (Connection connection = ConnectionManager.getConnection()) {
		PreparedStatement statement = connection
				.prepareStatement(
						TicketSQLStatements.SELECT_TICKET_BY_ID_SQL);
		Ticket ticket = new Ticket();
		ticket.setId(id);
		Transformer.valueIntoPreparedStatement(statement, ticket,
				TicketSQLStatements.SELECT_TICKET_BY_ID_SQL_ORDER);
		ResultSet resultSet = statement.executeQuery();
		return Transformer
				.transformResultSetIntoObject(resultSet, Ticket.class);
		}
	}

}
