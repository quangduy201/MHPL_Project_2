package com.example.project_2.components.charts;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

public abstract class BlankPlotCurveLineChatRender {

    public abstract String getLabelText(int index);

    public abstract void renderGraphics(BlankPlotCurveLineChart chart, Graphics2D g2, Rectangle2D rectangle);

    public abstract void mouseMove(Rectangle2D rectangle, MouseEvent mouse);
}
