package me.steinborn.varintshowdown.res;

import io.netty.buffer.ByteBuf;

public interface VarIntWriter {
  void write(ByteBuf buf, int value);
}
