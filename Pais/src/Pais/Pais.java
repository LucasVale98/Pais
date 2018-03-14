package Pais;

import com.mysql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Pais {
	
	private int id;
	private String nome;
	private long populacao;
	private double area;

	public Pais() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Pais(int id, String nome, double d, double area) {
		super();
		this.id = id;
		this.nome = nome;
		this.populacao = (long) d;
		this.area = area;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public long getPopulacao() {
		return populacao;
	}

	public void setPopulacao(double d) {
		this.populacao = (long) d;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}
	
	//Conectando com Driver
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	//Conexão com o banco
	public Connection obterConexao() throws SQLException {
		return DriverManager.getConnection(
				"jdbc:mysql://localhost/professor?user=alunospassword=lucasvale");
	}
	
	//CRUD Inserir
	public void Criar(){
		
		String sqlInsert = "INSER INTO pais(id, nome, populacao, area) VALUES (?, ?, ?, ?)";
		
		try (Connection conn = obterConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);){
			stm.setInt(1, getId());
			stm.setString(2, getNome());
			stm.setLong(3, getPopulacao());
			stm.setDouble(4, getArea());
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try(PreparedStatement stm2 = conn.prepareStatement(sqlQuery)){
				ResultSet rs = stm2.executeQuery();
				if(rs.next()) {
					setId(rs.getInt(1));
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void atualizar() {
		
		String sqlUpdate = "UPDATE cliente SET nome=?, populacao=?, area=? WHERE id=?";
		try (Connection conn = obterConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);){
			stm.setString(1, getNome());
			stm.setLong(2, getPopulacao());
			stm.setDouble(3, getArea());
			stm.setInt(4, getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void excluir() {
		
		String sqlDelete = "DELETE FROM clinte WHERE id = ?";
		try (Connection conn = obterConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void carregar() {
		
		String sqlSelect = "SELECT nome, populacao, area FROM pais WHERE pais.id=?";
		try(Connection conn = obterConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);){
			stm.setInt(1, getId());
				try (ResultSet rs = stm.executeQuery()) {
					if(rs.next()) {
						setNome(rs.getString("nome"));
						setPopulacao(rs.getShort("populacao"));
						setArea(rs.getDouble("email"));
					}else {
						setId(-1);
						setNome(null);
						setPopulacao(null);
						setArea(null);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			} catch (SQLException e1) {
				System.out.print(e1.getStackTrace());
			}
		
		}
	
	public double Populacao(double maiorPopulacao) {
		if(maiorPopulacao >= populacao) {
			return maiorPopulacao = populacao;
		}
		return maiorPopulacao;
		
	}

	public double Area(double menorArea) {
		if(menorArea <= area) {
			return menorArea = area;
		}
		return menorArea;
		
	}

	@Override
	public String toString() {
		return "Pais [id=" + id + ", nome=" + nome + ", populacao=" + 
				populacao + ", area=" + area + ", Maior Populacao()="
				+ getPopulacao() + ", Menor Area()=" + getArea() + "]";
	}

	
	
	
}
