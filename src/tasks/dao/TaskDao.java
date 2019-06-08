package tasks.dao;

import tasks.model.Task;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TaskDao {

	private final Connection connection;

	/*@Autowired
	 * public TaskDao(Connection connection) { this.connection = connection; }
	 */
	
	@Autowired
	public TaskDao(DataSource dataSource) {
		try {
			this.connection = dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}
	
	/*
	 * public TaskDao() { try { this.connection = new
	 * ConnectionFactory().getConnection(); } catch (SQLException e) { throw new
	 * RuntimeException(e); }
	 * 
	 * }
	 */


	public void inserir(Task task) {
		String sql = "insert into tasks (descricao, finalizada) values (?,?)";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, task.getDescricao());
			stmt.setBoolean(2, task.isFinalizada());
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}		
	}


	public List<Task> getTasks() {
		try {
			List<Task> tasks = new ArrayList<Task>();
			PreparedStatement stmt = this.connection.prepareStatement("select * from tasks");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				//adiciona a task na lista
				tasks.add(populaTask(rs));
			}
			rs.close();
			stmt.close();
			return tasks;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private Task populaTask(ResultSet rs) throws SQLException {
		Task task = new Task();
		//popula o objeto task
		task.setId(rs.getLong("id"));
		task.setDescricao(rs.getString("descricao"));
		task.setFinalizada(rs.getBoolean("finalizada"));
		//popula a data de finalizacao da task, fazendo a conversao
		Date data = rs.getDate("dataFinalizacao");
		if(data != null) {
			Calendar dataFinalizacao = Calendar.getInstance();
			dataFinalizacao.setTime(data);
			task.setDataFinalizacao(dataFinalizacao);
		}
		return task;
	}


	public void exclui(Task task) {
		if(task.getId() == null) {
			throw new IllegalStateException("Id da task não deve ser nula.");
		}
		String sql = "delete from tasks where id = ?";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setLong(1, task.getId());
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public Task getById(Long id) {
		if(id == null) {
			throw new IllegalStateException("Id da task não deve ser nula.");
		}
		try {
			PreparedStatement stmt = this.connection.prepareStatement("select * from tasks where id = ?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				return populaTask(rs);
			}
			rs.close();
			stmt.close();
			return null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public void edita(Task task) {
		String sql = "update tasks set descricao = ?, finalizada = ?, dataFinalizacao = ? where id = ?";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, task.getDescricao());
			stmt.setBoolean(2, task.isFinalizada());
			stmt.setDate(3, task.getDataFinalizacao() != null ? new Date(task.getDataFinalizacao().getTimeInMillis()) : null);
			stmt.setLong(4, task.getId());
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public void finaliza(Long id) {
		if(id == null) {
			throw new IllegalStateException("Id da task não deve ser nula.");
		}
		String sql = "update tasks set finalizada = ?, dataFinalizacao = ? where id = ?";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setBoolean(1, true);
			stmt.setDate(2, new Date(Calendar.getInstance().getTimeInMillis()));
			stmt.setLong(3, id);
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}