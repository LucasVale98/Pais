package teste;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import Pais.Pais;

import org.junit.jupiter.api.Test;

class PaisTeste {
	
	Pais pais, copia;
	static int id = 0;

	@Test
	public void test00Select() {
		System.out.println("Select");
		Pais fixture = new Pais(1, "Alemanha", 80.619, 357.022);
		Pais novo = new Pais(1, null, null, null);
		novo.carregar();
		assertEquals("Teste Inclusao", novo, fixture);
	}
	

	@Test
	public void test01Insert() {
		System.out.println("Insert");
		pais.Criar();
		id =  pais.getId();
		System.out.println(id);
		copia.setId(id);
		assertEquals("Teste Criacao", pais, copia);
	}
	
	@Test
	public void test02Update() {
		System.out.println("Update");
		pais.setPopulacao(41.660);
		copia.setPopulacao(44.900);
		pais.atualizar();
		assertEquals("Teste Atualzacao", pais, copia);
	}
	
	@Test
	public void test03Delete() {
		System.out.println("Delete");
		copia.setId(-1);
		copia.setNome(null);
		copia.setPopulacao(null);
		copia.setArea(null);
		pais.excluir();
		pais.carregar();
		assertEquals("Teste Atualzacao", pais, copia);
	}


}
