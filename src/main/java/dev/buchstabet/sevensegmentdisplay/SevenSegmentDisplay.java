package dev.buchstabet.sevensegmentdisplay;

import lombok.Builder;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Builder
@Getter
public class SevenSegmentDisplay implements BiConsumer<Graphics2D, Integer> {

  private final Point start;
  private final int numberAmount, length, wide, space;
  private final Color color;

  @Override
  public void accept(Graphics2D graphics2D, Integer numbers) {
    graphics2D.setColor(color);

    for (Integer number : Arrays.stream(numbers.toString().split("")).map(Integer::valueOf).collect(Collectors.toList())) {
      Point point = new Point(start.x + (number * length) + (number * space), start.y);

      Segment[] segments = Segment.transform(number);
      for (Segment segment : segments) {
        segment.getConsumer().paint(point, graphics2D, length, wide);
      }
    }
  }

}
