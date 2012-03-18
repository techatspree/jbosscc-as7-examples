package de.akquinet.jbosscc.testdata;

import javax.persistence.EntityManager;

import de.akquinet.jbosscc.BlogEntry;
import de.akquinet.jbosscc.User;
import de.akquinet.jbosscc.needle.db.testdata.AbstractTestdataBuilder;

public class BlogEntryTestdataBuilder extends
		AbstractTestdataBuilder<BlogEntry> {

	private static final String CONTENT = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet";
	private static final String TITLE = "Lorem ipsum dolor sit amet";

	private User withAuthor;
	private String withContent;
	private String withTitle;

	public BlogEntryTestdataBuilder() {
		super();
	}

	public BlogEntryTestdataBuilder(EntityManager entityManager) {
		super(entityManager);
	}

	public BlogEntryTestdataBuilder withAuthor(User author) {
		withAuthor = author;
		return this;
	}

	public BlogEntryTestdataBuilder withContent(String content) {
		withContent = content;
		return this;
	}

	public BlogEntryTestdataBuilder withTitle(String title) {
		withTitle = title;
		return this;
	}

	private User getAuthor() {
		if (withAuthor != null) {
			return withAuthor;
		}

		return hasEntityManager() ? new UserTestdataBuilder(getEntityManager())
				.buildAndSave() : new UserTestdataBuilder().build();
	}

	private String getTitle() {
		return withTitle != null ? withTitle : TITLE;
	}

	private String getContent() {
		return withContent != null ? withContent : CONTENT;
	}

	@Override
	public BlogEntry build() {
		BlogEntry blogEntry = new BlogEntry();
		blogEntry.setAuthor(getAuthor());
		blogEntry.setTitle(getTitle());
		blogEntry.setContent(getContent());

		return blogEntry;
	}

}
