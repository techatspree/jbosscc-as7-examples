package de.akquinet.jbosscc;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;

import de.akquinet.jbosscc.testdata.UserTestdataBuilder;
import de.akquinet.jbosscc.needle.db.transaction.VoidRunnable;
import de.akquinet.jbosscc.needle.junit.DatabaseRule;

public class UserTest {

	@Rule
	public DatabaseRule databaseRule = new DatabaseRule();

	private EntityManager entityManager = databaseRule.getEntityManager();

	@Test
	public void testPersist() throws Exception {
		User user = new UserTestdataBuilder(entityManager).buildAndSave();

		User userFromDb = databaseRule.getEntityManager().find(User.class,
				user.getId());

		Assert.assertEquals(user.getId(), userFromDb.getId());
		Assert.assertNotSame(user, userFromDb);
	}

	@Test(expected = PersistenceException.class)
	public void testUniqueUsername() throws Exception {
		new UserTestdataBuilder(entityManager).withUsername("username").buildAndSave();

		databaseRule.getTransactionHelper().executeInTransaction(
				new VoidRunnable() {

					@Override
					public void doRun(EntityManager entityManager)
							throws Exception {
						databaseRule.getEntityManager().persist(
								new UserTestdataBuilder().withUsername("username").build());

					}
				});

	}

	@Test
	public void testVerifyPassword() throws Exception {
		User user = new UserTestdataBuilder().build();
		Assert.assertTrue(user.verifyPassword("secret"));
		Assert.assertFalse(user.verifyPassword("other"));
	}

}
