package me.steinborn.varintshowdown.res;

import io.netty.buffer.ByteBuf;

public class UnrolledVarIntWriter implements VarIntWriter {

  @Override
  public void write(ByteBuf buf, int value) {
    if (value < 0) {
      buf.writeByte(value & 0x7F | 0x80);
      buf.writeByte((value >>>= 7) & 0x7F | 0x80);
      buf.writeByte((value >>>= 7) & 0x7F | 0x80);
      buf.writeByte((value >>>= 7) & 0x7F | 0x80);
      buf.writeByte(value >>> 7);
    } else if (value < 128) {
      buf.writeByte(value);
    } else if (value < 16384) {
      buf.writeByte(value & 0x7F | 0x80);
      buf.writeByte(value >>> 7);
    } else if (value < 2097152) {
      buf.writeByte(value & 0x7F | 0x80);
      buf.writeByte((value >>>= 7) & 0x7F | 0x80);
      buf.writeByte(value >>> 7);
    } else if (value < 268435456) {
      buf.writeByte(value & 0x7F | 0x80);
      buf.writeByte((value >>>= 7) & 0x7F | 0x80);
      buf.writeByte((value >>>= 7) & 0x7F | 0x80);
      buf.writeByte(value >>> 7);
    } else {
      buf.writeByte(value & 0x7F | 0x80);
      buf.writeByte((value >>>= 7) & 0x7F | 0x80);
      buf.writeByte((value >>>= 7) & 0x7F | 0x80);
      buf.writeByte((value >>>= 7) & 0x7F | 0x80);
      buf.writeByte(value >>> 7);
    }
  }
}
