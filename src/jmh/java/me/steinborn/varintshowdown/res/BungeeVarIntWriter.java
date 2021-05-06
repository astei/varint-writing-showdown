package me.steinborn.varintshowdown.res;

import io.netty.buffer.ByteBuf;

public class BungeeVarIntWriter implements VarIntWriter {

  @Override
  public void write(ByteBuf buf, int value) {
    int part;
    while (true) {
      part = value & 0x7F;
      value >>>= 7;
      if (value != 0) {
        part |= 0x80;
      }
      buf.writeByte(part);
      if (value == 0) {
        break;
      }
    }
  }
}
