package com.example.project_2.components.charts;

import java.awt.Graphics2D;

public abstract class BlankPlotBarChatRender {

    public abstract String getLabelText(int index);

    public abstract void renderSeries(BlankPlotBarChart chart, Graphics2D g2, SeriesSize size, int index);
}
