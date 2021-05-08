package me.steinborn.varintshowdown.states;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import me.steinborn.varintshowdown.res.SmartNoDataDependencyUnrolledVarIntWriter;
import me.steinborn.varintshowdown.res.SmartUnrolledVarIntWriter;
import me.steinborn.varintshowdown.res.VarIntWriter;
import org.openjdk.jmh.annotations.*;

@State(Scope.Benchmark)
public class SmartNoDataDependencyUnrolledVarintState {

  private ByteBuf buf;
  private VarIntWriter varIntWriter;

  @Setup(Level.Trial)
  public void setup() {
    buf = Unpooled.directBuffer(5);
    varIntWriter = new SmartNoDataDependencyUnrolledVarIntWriter();
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
