package dev.buchstabet.sevensegmentdisplay;

import java.awt.*;

public interface SegmentExecutor {

  void paint(Point point, Graphics2D graphic, int length, int wide);

}
