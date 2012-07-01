package de.akquinet.jbosscc.msc;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Circle implements SimpleShape {
	private static final Icon m_icon = new ImageIcon(
			Circle.class.getResource("/circle.png"));;

	@Override
	public String getName() {
		return Circle.class.getSimpleName();
	}

	@Override
	public Icon getIcon() {
		return m_icon;
	}

	@Override
	public void draw(Graphics2D g2, Point p) {
		int x = p.x - 25;
		int y = p.y - 25;
		GradientPaint gradient = new GradientPaint(x, y, Color.RED, x + 50, y,
				Color.WHITE);
		g2.setPaint(gradient);
		g2.fill(new Ellipse2D.Double(x, y, 50, 50));
		BasicStroke wideStroke = new BasicStroke(2.0f);
		g2.setColor(Color.black);
		g2.setStroke(wideStroke);
		g2.draw(new Ellipse2D.Double(x, y, 50, 50));
	}

}
