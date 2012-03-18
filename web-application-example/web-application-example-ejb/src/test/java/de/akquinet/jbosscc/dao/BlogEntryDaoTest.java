package de.akquinet.jbosscc.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;

import de.akquinet.jbosscc.BlogEntry;
import de.akquinet.jbosscc.testdata.BlogEntryTestdataBuilder;
import de.akquinet.jbosscc.needle.annotation.ObjectUnderTest;
import de.akquinet.jbosscc.needle.junit.DatabaseRule;
import de.akquinet.jbosscc.needle.junit.NeedleRule;

public class BlogEntryDaoTest {

	@Rule
	public DatabaseRule databaseRule = new DatabaseRule();

	@Rule
	public NeedleRule needleRule = new NeedleRule(databaseRule);

	@Inject
	private EntityManager entityManager;

	@ObjectUnderTest
	private BlogEntryDaoBean blogEntryDao;

	@Test
	public void testFind() throws Exception {
		new BlogEntryTestdataBuilder(entityManager).buildAndSave();
		new BlogEntryTestdataBuilder(entityManager).buildAndSave();
		BlogEntry latest = new BlogEntryTestdataBuilder(entityManager)
				.buildAndSave();

		List<BlogEntry> list = blogEntryDao.find(2, 0);

		Assert.assertEquals(2, list.size());
		Assert.assertEquals(latest.getId(), list.get(0).getId());
	}

	@Test
	public void testCount() throws Exception {
		Long count = blogEntryDao.count();
		Assert.assertEquals(new Long(0), count);

		new BlogEntryTestdataBuilder(entityManager).buildAndSave();

		count = blogEntryDao.count();
		Assert.assertEquals(new Long(1), count);
	}
}
