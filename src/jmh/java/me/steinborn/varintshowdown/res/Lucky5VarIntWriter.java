package me.steinborn.varintshowdown.res;

import io.netty.buffer.ByteBuf;

public class Lucky5VarIntWriter implements VarIntWriter {

  @Override
  public void write(ByteBuf buf, int value) {
    int w = (value & 0x7F | 0x80) << 24 | ((value >>> 7) & 0x7F | 0x80) << 16 | ((value >>> 14) & 0x7F | 0x80)
            | ((value >>> 21) & 0x7F | 0x80);
    buf.writeInt(w);
    buf.writeByte(value >>> 28);
  }
}
