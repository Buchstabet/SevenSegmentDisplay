package dev.buchstabet.sevensegmentdisplay;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.awt.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@RequiredArgsConstructor
public enum Segment {


  A((point, graphic, length, wide) -> paintHorizontal(graphic, point, wide, 0, length, wide)),
  B((point, graphic, length, wide) -> paintVertical(graphic, point, wide + length, wide, length, wide)),
  C((point, graphic, length, wide) -> paintVertical(graphic, point, wide + length, wide + length + wide, length, wide)),
  D((point, graphic, length, wide) -> paintHorizontal(graphic, point, wide, wide + length + wide + length, length, wide)),
  E((point, graphic, length, wide) -> paintVertical(graphic, point, 0, wide + length + wide, length, wide)),
  F((point, graphic, length, wide) -> paintVertical(graphic, point, 0, wide, length, wide)),
  G((point, graphic, length, wide) -> paintHorizontal(graphic, point, wide, wide + length, length, wide));


  @Getter private final SegmentExecutor consumer;

  public static Segment[] transform(int number) {
    switch (number) {
      case 1: return new Segment[] {Segment.B, Segment.C};
      case 2: return new Segment[] {Segment.A, Segment.B, Segment.G, Segment.E, Segment.D};
      case 3: return new Segment[] {Segment.A, Segment.B, Segment.G, Segment.C, Segment.D};
      case 4: return new Segment[] {Segment.F, Segment.G, Segment.B, Segment.C};
      case 5: return new Segment[] {Segment.A, Segment.F, Segment.G, Segment.C, Segment.D};
      case 6: return new Segment[] {Segment.A, Segment.F, Segment.G, Segment.E, Segment.C, Segment.D};
      case 7: return new Segment[] {Segment.A, Segment.B, Segment.C};
      case 8: return Segment.values();
      case 9: return new Segment[] {Segment.A, Segment.B, Segment.C, Segment.D, Segment.F, Segment.G};
      default: return new Segment[] {Segment.A, Segment.B, Segment.C, Segment.D, Segment.F, Segment.E};
    }
  }

  private static void paintHorizontal(Graphics2D graphics2D, Point from, int movementX, int movementY, int length, int wide) {
    int[] a = new int[] {from.x + movementX, from.y + movementY};
    int[] b = new int[] {a[0] + length, a[0] + wide};
    graphics2D.fillPolygon(a, b, a.length);
  }

  private static void paintVertical(Graphics2D graphics2D, Point from, int movementX, int movementY, int length, int wide) {
    int[] a = new int[] {from.x + movementX, from.y + movementY};
    int[] b = new int[] {a[0] + wide, a[0] + length};
    graphics2D.fillPolygon(a, b, a.length);
  }
}
