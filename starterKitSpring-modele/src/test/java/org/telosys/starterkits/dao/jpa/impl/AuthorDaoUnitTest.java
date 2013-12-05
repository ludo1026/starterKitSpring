package org.telosys.starterkits.dao.jpa.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.telosys.starterkits.bean.Author;

@RunWith(MockitoJUnitRunner.class)
public class AuthorDaoUnitTest {

	@InjectMocks
	private AuthorDaoImpl authorDao;
	@Mock
	private EntityManager entityManager;

	@Test
	public void load() {
		// Given
		Integer id = Integer.valueOf("1");

		Author author = new Author();
		author.setId(id);

		when(entityManager.find(Author.class, id)).thenReturn(author);

		// When
		Author authorResult = authorDao.load(id);

		// Then
		assertEquals(id, authorResult.getId());
	}

	@Test
	public void save() {
		// Given
		Author authorToSave = new Author();
		Author authorSaved = new Author();

		when(entityManager.merge(authorToSave)).thenReturn(authorSaved);

		// When
		Author authorResult = authorDao.save(authorToSave);

		// Then
		assertTrue(authorResult != authorToSave);
		assertTrue(authorResult == authorSaved);
	}

	@Test
	public void delete() {
		// Given
		Integer id = Integer.valueOf("1");

		Author author = new Author();
		when(entityManager.find(Author.class, id)).thenReturn(author);

		// When
		authorDao.delete(id);

		// Then
		verify(entityManager).remove(author);
	}

	@Test
	public void loadAll() {
		// Given
		List<Author> authors = new ArrayList<Author>();
		authors.add(new Author());

		Query query = mock(Query.class);
		when(entityManager.createQuery("from " + Author.class.getName()))
				.thenReturn(query);

		when(query.getResultList()).thenReturn(authors);

		// When
		List<Author> authorsResult = authorDao.loadAll();

		// Then
		assertTrue(authorsResult == authors);
	}

	@Test
	public void search_without_criteria() {
		// Given
		List<Author> authors = new ArrayList<Author>();
		authors.add(new Author());

		Query query = mock(Query.class);
		when(entityManager.createQuery("from " + Author.class.getName()))
				.thenReturn(query);

		when(query.getResultList()).thenReturn(authors);

		// When
		List<Author> authorsResult = authorDao.search(null);

		// Then
		assertTrue(authorsResult == authors);
	}

}
