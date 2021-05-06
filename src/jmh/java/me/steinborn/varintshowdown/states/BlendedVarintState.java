package me.steinborn.varintshowdown.states;

import me.steinborn.varintshowdown.res.BlendedVarIntWriter;
import me.steinborn.varintshowdown.res.VarIntWriter;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.openjdk.jmh.annotations.*;

@State(Scope.Benchmark)
public class BlendedVarintState {

  private ByteBuf buf;
  private VarIntWriter varIntWriter;

  @Setup(Level.Trial)
  public void setup() {
    buf = Unpooled.directBuffer(5);
    varIntWriter = new BlendedVarIntWriter();
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
