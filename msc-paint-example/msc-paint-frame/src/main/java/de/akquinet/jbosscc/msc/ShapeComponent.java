package de.akquinet.jbosscc.msc;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;

import javax.swing.JComponent;

public class ShapeComponent extends JComponent {
	private static final long serialVersionUID = 1L;
	private PaintFrame m_frame;
	private String m_shapeName;

	/**
	 * Construct a component for the specified drawing frame with the specified
	 * named shape. The component acquires the named shape from the drawing
	 * frame at the time of painting, which helps it account for dynamism.
	 * 
	 * @param frame
	 *            The drawing frame associated with the component.
	 * @param shapeName
	 *            The name of the shape to draw.
	 **/
	public ShapeComponent(PaintFrame frame, String shapeName) {
		m_frame = frame;
		m_shapeName = shapeName;
	}

	/**
	 * Paints the contents of the component. The component acquires the named
	 * shape from the drawing frame at the time of painting, which helps it
	 * account for dynamism.
	 * 
	 * @param g
	 *            The graphics object to use for painting.
	 **/
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		SimpleShape shape = m_frame.getShape(m_shapeName);

		shape.draw(g2, new Point(getWidth() / 2, getHeight() / 2));

	}
}
