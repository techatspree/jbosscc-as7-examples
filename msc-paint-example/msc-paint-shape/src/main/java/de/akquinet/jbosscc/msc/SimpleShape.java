package de.akquinet.jbosscc.msc;

import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.Icon;

public interface SimpleShape {

	public String getName();

	public Icon getIcon();

	/**
	 * Draw this shape at the given position.
	 *
	 * @param g2
	 *            The graphics object used for painting.
	 * @param p
	 *            The position to paint the shape.
	 **/
	public void draw(Graphics2D g2, Point p);
}
