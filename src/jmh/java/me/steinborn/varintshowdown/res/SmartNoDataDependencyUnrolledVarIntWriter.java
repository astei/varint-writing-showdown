package me.steinborn.varintshowdown.res;

import io.netty.buffer.ByteBuf;

public class SmartNoDataDependencyUnrolledVarIntWriter implements VarIntWriter {

  @Override
  public void write(ByteBuf buf, int value) {
    if ((value & (0xFFFFFFFF << 7)) == 0) {
      buf.writeByte(value);
    } else {
      buf.writeByte(value & 0x7F | 0x80);
      if ((value & (0xFFFFFFFF << 14)) == 0) {
        buf.writeByte(value >>> 7);
      } else {
        buf.writeByte((value >>> 7) & 0x7F | 0x80);
        if ((value & (0xFFFFFFFF << 21)) == 0) {
          buf.writeByte(value >>> 14);
        } else {
          buf.writeByte((value >>> 14) & 0x7F | 0x80);
          if ((value & (0xFFFFFFFF << 28)) == 0) {
            buf.writeByte(value >>> 21);
          } else {
            buf.writeByte((value >>> 21) & 0x7F | 0x80);
            buf.writeByte(value >>> 28);
          }
        }
      }
    }
  }
}
