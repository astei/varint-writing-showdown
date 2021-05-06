package me.steinborn.varintshowdown.res;

import io.netty.buffer.ByteBuf;

public class BlendedVarIntWriter implements VarIntWriter {

  @Override
  public void write(ByteBuf buf, int value) {
    // Optimization: focus on 1-3 byte VarInts as they are the most common
    if ((value & (0xFFFFFFFF << 7)) == 0) {
      buf.writeByte(value);
    } else if ((value & (0xFFFFFFFF << 14)) == 0) {
      int w = (value & 0x7F | 0x80) << 8 | (value >>> 7);
      buf.writeShort(w);
    } else if ((value & (0xFFFFFFFF << 21)) == 0) {
      int w = (value & 0x7F | 0x80) << 16 | ((value >>> 7) & 0x7F | 0x80) << 8 | (value >>> 14);
      buf.writeMedium(w);
    } else {
      // 4 and 5 byte VarInts aren't common so split those cases off
      writeVarIntUncommon(buf, value);
    }
  }

  private static void writeVarIntUncommon(ByteBuf buf, int value) {
    while (true) {
      if ((value & 0xFFFFFF80) == 0) {
        buf.writeByte(value);
        return;
      }

      buf.writeByte(value & 0x7F | 0x80);
      value >>>= 7;
    }
  }
}
