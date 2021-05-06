package me.steinborn.varintshowdown;

import bench.states.*;
import me.steinborn.varintshowdown.states.*;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@Fork(value = Constants.FORK)
@Warmup(iterations = Constants.WARM_UP_ITERATIONS, time = Constants.WARM_UP_ITERATIONS_TIME)
@Measurement(iterations = Constants.ITERATIONS, time = Constants.ITERATIONS_TIME)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class VarIntWriterBenchmark {

  @Param({
          /*"0",*/ "127", /*"128",*/ "16383", /*"16384",*/ "2097151", /*"2097152",*/ "268435455", /*"268435456",*/ "2147483647",
          /*"-1", "-2147483648"*/
  })
  public int value;

  @Benchmark
  @CompilerControl(CompilerControl.Mode.DONT_INLINE)
  public void oldVelocityVarintWrite(OldVarintState state) {
    state.write(value);
  }

  @Benchmark
  @CompilerControl(CompilerControl.Mode.DONT_INLINE)
  public void startinVarintWrite(StartinVarintState state) {
    state.write(value);
  }

  @Benchmark
  @CompilerControl(CompilerControl.Mode.DONT_INLINE)
  public void bungeeVarintWrite(BungeeVarintState state) {
    state.write(value);
  }

  @Benchmark
  @CompilerControl(CompilerControl.Mode.DONT_INLINE)
  public void unrolledVarintWrite(UnrolledVarintState state) {
    state.write(value);
  }

  @Benchmark
  @CompilerControl(CompilerControl.Mode.DONT_INLINE)
  public void blendVarintWrite(BlendedVarintState state) {
    state.write(value);
  }
}
