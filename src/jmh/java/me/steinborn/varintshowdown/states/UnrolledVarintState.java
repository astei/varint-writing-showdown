package me.steinborn.varintshowdown.states;

import me.steinborn.varintshowdown.res.UnrolledVarIntWriter;
import me.steinborn.varintshowdown.res.VarIntWriter;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;

@State(Scope.Benchmark)
public class UnrolledVarintState {

  private ByteBuf buf;
  private VarIntWriter varIntWriter;

  @Setup(Level.Trial)
  public void setup() {
    buf = Unpooled.directBuffer(5);
    varIntWriter = new UnrolledVarIntWriter();
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
