package me.steinborn.varintshowdown.res;

import io.netty.buffer.ByteBuf;

public class OldVelocityVarIntWriter implements VarIntWriter {

  @Override
  public void write(ByteBuf buf, int value) {
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
