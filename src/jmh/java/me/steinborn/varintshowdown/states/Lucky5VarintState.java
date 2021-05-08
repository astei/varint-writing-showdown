package me.steinborn.varintshowdown.states;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import me.steinborn.varintshowdown.res.BlendedVarIntWriter;
import me.steinborn.varintshowdown.res.Lucky5VarIntWriter;
import me.steinborn.varintshowdown.res.VarIntWriter;
import org.openjdk.jmh.annotations.*;

@State(Scope.Benchmark)
public class Lucky5VarintState {

  private ByteBuf buf;
  private VarIntWriter varIntWriter;

  @Setup(Level.Trial)
  public void setup() {
    buf = Unpooled.directBuffer(5);
    varIntWriter = new Lucky5VarIntWriter();
  }

  @TearDown(Level.Trial)
  public void tearDown() {
    buf.release();
  }

  public void write(int value) {
    varIntWriter.write(buf, value);
    buf.clear();
  }

}
