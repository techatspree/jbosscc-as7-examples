package de.akquinet.jbosscc.msc;

import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class DefaultShape implements SimpleShape {
	private static final ImageIcon m_icon = new ImageIcon(
			DefaultShape.class.getResource("/underc.png"));;

	@Override
	public String getName() {
		return DefaultShape.class.getSimpleName();
	}

	@Override
	public Icon getIcon() {
		return m_icon;
	}

	@Override
	public void draw(Graphics2D g2, Point p) {
	    g2.drawImage(m_icon.getImage(), 0, 0, null);
	}

}
