package es.deusto.test;

import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import es.deusto.server.jdo.*;

public class DAOTest {
	
	public DAO dao;
	
	@Before
	public void setUpClass() {
		dao = new DAO();
	}
	
	public void testClientCreation() {
		System.out.println("Testing client creation");
		Client c = new Client("test@test.com", "test");
		c.setName("client");
		c.setSurname("client");
		c.setPhone("123");
		dao.begin();
		dao.registerClient(c);
		dao.detachOnCommit();
		dao.end();
		dao.begin();
		Client c2 = dao.getClient("test@test.com", "test");
		dao.detachOnCommit();
		dao.end();
		assertTrue(c.fieldsEqual(c2));
		System.out.println("Finished testing client creation");
	}
	
	public void testClientModification() {
		System.out.println("Testing client modification");
		dao.begin();
		Client c = dao.getClient("test@test.com", "test");
		c.setPhone("456");
		dao.end();
		dao.begin();
		c = dao.getClient("test@test.com", "test");
		assertEquals(c.getPhone(), "456");
		dao.end();
		System.out.println("Finished esting client modification");
	}
	
	public void testClientDeletion() {
		System.out.println("Testing client deletion");
		dao.begin();
		dao.deleteClient("test@test.com", "test");
		dao.end();
		dao.begin();
		Client c = dao.getClient("test@test.com", "test");
		assertTrue(c == null);
		dao.end();
		System.out.println("Finished testing client deletion");
	}
	
	@Test
	public void testClientDAO() {
		testClientCreation();
		testClientModification();
		testClientDeletion();
	}
	
	@Test
	public void testMovieSearchDAO() {
		Movie m = new Movie();
		m.setDirector("asdf");
		m.setTitle("my title for searching");
		dao.begin();
		dao.storeObject(m);
		dao.end();
		
		dao.begin();
		List<Movie> movies = dao.searchMovieByTitle("searching");
		assertTrue(movies.size() == 1);
		assertTrue(movies.get(0).getTitle().equals("my title for searching"));
		dao.deleteMovie(movies.get(0));
		dao.end();
	}
	
	@Test
	public void testTicketDAO() {
		Movie m = new Movie();
		m.setDirector("asdf");
		m.setTitle("my title for ticket");
		Session s = new Session(2019, 4, 30, 20, 30);
		m.getSessions().add(s);
		Client c = new Client("ticket@ticket", "ticket");
		Ticket t = new Ticket(s, c);
		c.getPurchases().add(t);
		dao.begin();
		dao.storeObject(c);
		dao.storeObject(m);
		dao.storeObject(t);
		dao.end();
		
		dao.begin();
		c = dao.getClient("ticket@ticket", "ticket");
		System.out.println(c.getPurchases().size());
		assertTrue(c.getPurchases().size() == 1);
		assertEquals(c.getPurchases().get(0).getSession().getMovie().getTitle(), "my title for ticket");
		dao.deleteClient(c);
		dao.deleteMovie("my title for ticket");
		dao.end();
		
	}
	
	
}
