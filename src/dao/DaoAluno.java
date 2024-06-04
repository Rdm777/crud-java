package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import db.DB;
import modelo.Aluno;

public class DaoAluno {
	private Connection conn;
	
	  PreparedStatement st = null;
	  ResultSet rs = null;
	
	public DaoAluno() {
		conn = DB.getConnection();
	}
	
	public void insert(Aluno aluno) {
		String sql = "insert into aluno (nome, email, idade) values (?, ?, ?)";
		
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, aluno.getNome());
			st.setString(2, aluno.getEmail());
			st.setInt(3, aluno.getIdade());
			
			st.execute();
			System.out.println("Registro incluido com Sucesso!");
			
		} catch (SQLException e) {
			System.out.println("Falha na inclusão do Registro");
		}
	}
	
	public List<Aluno> select() {
	    String sql = "SELECT * FROM aluno;";
	    List<Aluno> listaAlunos = new ArrayList<>();
	    
	    try {
	        st = conn.prepareStatement(sql);
	        rs = st.executeQuery();
	        
	        while (rs.next()) {
	            Aluno aluno = new Aluno();
	            aluno.setId(rs.getInt("id"));
	            aluno.setNome(rs.getString("nome"));
	            aluno.setEmail(rs.getString("email"));
	            aluno.setIdade(rs.getInt("idade"));
	            listaAlunos.add(aluno);
	        }
	        //System.out.println(listaAlunos);
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Erro ao listar alunos");
	        
	    }
	    return listaAlunos;
	}
	
	public void delete(int codigo) {
		String sql = "delete from aluno where id = ?";
	    
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, codigo);
			st.execute();
			System.out.println("Registro excluído com sucesso");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Falha na exclusão do Registro");
		}
	}
	
	public void update(Aluno aluno) {
		String sql = "update aluno set nome = ?, email = ?, " + "idade = ? where id = ?";
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, aluno.getNome());
			st.setString(2, aluno.getEmail());
			st.setInt(3, aluno.getIdade());
			st.setInt(4, aluno.getId());
			st.execute();
			System.out.println("Registro alterado com sucesso");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Aluno> selectByNome(String nome){
		List<Aluno> pesquisaAlunos = new ArrayList<Aluno>();
		try {
			String sql = "select * from aluno where upper(nome) LIKE '%" + nome.toUpperCase() + "%'";
			PreparedStatement stmt = this.conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			 while (rs.next()) {
				Aluno aluno = new Aluno();
				aluno.setId(rs.getInt("id"));
				aluno.setNome(rs.getString("nome"));
				aluno.setEmail(rs.getString("email"));
				aluno.setIdade(rs.getInt("idade"));
				pesquisaAlunos.add(aluno);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao pesquisar alunos");
		}
		return pesquisaAlunos;
	}

}

