package de.akquinet.jbosscc;

import javax.enterprise.context.Conversation;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.junit.Rule;
import org.junit.Test;

import de.akquinet.jbosscc.dao.BlogEntryDao;
import de.akquinet.jbosscc.dao.BlogEntryDaoBean;
import de.akquinet.jbosscc.injectionprovider.CurrentUserInjectionProvider;
import de.akquinet.jbosscc.testdata.BlogEntryTestdataBuilder;
import de.akquinet.jbosscc.needle.annotation.InjectIntoMany;
import de.akquinet.jbosscc.needle.annotation.ObjectUnderTest;
import de.akquinet.jbosscc.needle.db.transaction.Runnable;
import de.akquinet.jbosscc.needle.junit.DatabaseRule;
import de.akquinet.jbosscc.needle.junit.NeedleRule;
import de.akquinet.jbosscc.needle.mock.EasyMockProvider;

public class BlogEntryServiceTest {

	@Rule
	public DatabaseRule databaseRule = new DatabaseRule();

	@Rule
	public NeedleRule needleRule = new NeedleRule(databaseRule,
			new CurrentUserInjectionProvider());

	@ObjectUnderTest
	private BlogEntryService blogEntryService;

	@SuppressWarnings("unused")
	@InjectIntoMany
	@ObjectUnderTest(implementation = BlogEntryDaoBean.class)
	private BlogEntryDao blogEntryDao;

	@Inject
	private EasyMockProvider mockProvider;

	@Inject
	private Conversation conversationMock;

	@Test
	public void testPersistNewInstance() throws Exception {

		final Long id = databaseRule.getTransactionHelper()
				.executeInTransaction(new Runnable<Long>() {

					@Override
					public Long run(EntityManager entityManager)
							throws Exception {


						blogEntryService.newInstance();
						BlogEntry instance = blogEntryService.getInstance();
						entityManager.persist(instance.getAuthor());
						instance.setTitle("title");
						instance.setContent("content");
						blogEntryService.persistOrUpdate();

						return instance.getId();
					}

				});

		BlogEntry blogEntry = databaseRule.getTransactionHelper().loadObject(
				BlogEntry.class, id);

		Assert.assertNotNull(blogEntry);

	}

	@Test
	public void testGetInstanceById() throws Exception {
		BlogEntry blogEntry = new BlogEntryTestdataBuilder(
				databaseRule.getEntityManager()).buildAndSave();

		blogEntryService.setId(blogEntry.getId());

		BlogEntry instance = blogEntryService.getInstance();

		Assert.assertEquals(blogEntry.getId(), instance.getId());
	}

	@Test
	public void testDelete() throws Exception {
		BlogEntry blogEntry = new BlogEntryTestdataBuilder(
				databaseRule.getEntityManager()).buildAndSave();

		EasyMock.expect(conversationMock.isTransient()).andReturn(false)
				.anyTimes();

		mockProvider.replayAll();
		// init instance
		blogEntryService.setId(blogEntry.getId());
		blogEntryService.getInstance();

		blogEntryService.delete();
		mockProvider.verifyAll();

		BlogEntry loadObject = databaseRule.getTransactionHelper().loadObject(
				BlogEntry.class, blogEntry.getId());

		Assert.assertNull(loadObject);

	}

}
