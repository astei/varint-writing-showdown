package me.steinborn.varintshowdown.res;

import io.netty.buffer.ByteBuf;

public class SmartUnrolledVarIntWriter implements VarIntWriter {

  @Override
  public void write(ByteBuf buf, int value) {
    if ((value & 0xFFFFFF80) == 0) {
      buf.writeByte(value);
    } else {
      buf.writeByte(value & 0x7F | 0x80);
      value >>>= 7;
      if ((value & 0xFFFFFF80) == 0) {
        buf.writeByte(value);
      } else {
        buf.writeByte(value & 0x7F | 0x80);
        value >>>= 7;
        if ((value & 0xFFFFFF80) == 0) {
          buf.writeByte(value);
        } else {
          buf.writeByte(value & 0x7F | 0x80);
          value >>>= 7;
          if ((value & 0xFFFFFF80) == 0) {
            buf.writeByte(value);
          } else {
            buf.writeByte(value & 0x7F | 0x80);
            value >>>= 7;
            buf.writeByte(value);
          }
        }
      }
    }
  }
}
