package de.akquinet.jbosscc;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolationException;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;

import de.akquinet.jbosscc.testdata.BlogEntryTestdataBuilder;
import de.akquinet.jbosscc.needle.db.transaction.VoidRunnable;
import de.akquinet.jbosscc.needle.junit.DatabaseRule;

public class BlogEntryTest {

	@Rule
	public DatabaseRule databaseRule = new DatabaseRule();

	private EntityManager entityManager = databaseRule.getEntityManager();

	@Test
	public void testPersist() throws Exception {
		BlogEntry blogEntry = new BlogEntryTestdataBuilder(entityManager)
				.buildAndSave();

		BlogEntry blogEntryFromDb = entityManager.find(BlogEntry.class,
				blogEntry.getId());

		Assert.assertEquals(blogEntry.getId(), blogEntryFromDb.getId());
		Assert.assertNotSame(blogEntry, blogEntryFromDb);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testPersistWithoutAuthor() throws Exception {
		final BlogEntry blogEntry = new BlogEntryTestdataBuilder(entityManager)
				.build();
		blogEntry.setAuthor(null);

		databaseRule.getTransactionHelper().executeInTransaction(
				new VoidRunnable() {

					@Override
					public void doRun(EntityManager entityManager)
							throws Exception {
						entityManager.persist(blogEntry);

					}
				});
	}

}
