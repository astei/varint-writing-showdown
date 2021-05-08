package me.steinborn.varintshowdown.res;

import io.netty.buffer.ByteBuf;

public class StartinVarIntWriter implements VarIntWriter{

  @Override
  public void write(ByteBuf buf, int value) {
    int continuationBytes = (31 - Integer.numberOfLeadingZeros(value)) / 7;
    for (int i = 0; i < continuationBytes; ++i) {
      buf.writeByte((value & 0x7F) | 0x80);
      value >>>= 7;
    }
    buf.writeByte(value);
  }
}
