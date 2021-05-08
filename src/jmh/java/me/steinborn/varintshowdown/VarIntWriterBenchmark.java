package me.steinborn.varintshowdown;

import me.steinborn.varintshowdown.states.*;
import org.openjdk.jmh.annotations.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
@Fork(value = Constants.FORK)
@Warmup(iterations = Constants.WARM_UP_ITERATIONS, time = Constants.WARM_UP_ITERATIONS_TIME)
@Measurement(iterations = Constants.ITERATIONS, time = Constants.ITERATIONS_TIME)
@OutputTimeUnit(TimeUnit.SECONDS)
public class VarIntWriterBenchmark {

  private int[] numbers;

  @Setup
  public void setupNumbers() {
    Random random = new Random(77083993792645L);
    this.numbers = new int[2048];
    for (int i = 0; i < 2048; i++) {
      this.numbers[i] = generateRandomBitNumber(random, random.nextInt(30) + 1);
    }
  }

  private static int generateRandomBitNumber(Random random, int i) {
    int lowerBound = (1 << (i - 1));
    int upperBound = (1 << i) - 1;
    if (lowerBound == upperBound) {
      return lowerBound;
    }
    return lowerBound + random.nextInt(upperBound - lowerBound);
  }

  @Benchmark
  @CompilerControl(CompilerControl.Mode.DONT_INLINE)
  public void oldVelocityVarintWrite(OldVarintState state) {
    for (int number : numbers) {
      state.write(number);
    }
  }

  @Benchmark
  @CompilerControl(CompilerControl.Mode.DONT_INLINE)
  public void startinVarintWrite(StartinVarintState state) {
    for (int number : numbers) {
      state.write(number);
    }
  }

  @Benchmark
  @CompilerControl(CompilerControl.Mode.DONT_INLINE)
  public void unrolledVarintWrite(UnrolledVarintState state) {
    for (int number : numbers) {
      state.write(number);
    }
  }

  @Benchmark
  @CompilerControl(CompilerControl.Mode.DONT_INLINE)
  public void smartUnrolledVarintWrite(SmartUnrolledVarintState state) {
    for (int number : numbers) {
      state.write(number);
    }
  }

  @Benchmark
  @CompilerControl(CompilerControl.Mode.DONT_INLINE)
  public void smartNoDataDependencyUnrolledVarintWrite(SmartNoDataDependencyUnrolledVarintState state) {
    for (int number : numbers) {
      state.write(number);
    }
  }

  @Benchmark
  @CompilerControl(CompilerControl.Mode.DONT_INLINE)
  public void blendedVarintWrite(BlendedVarintState state) {
    for (int number : numbers) {
      state.write(number);
    }
  }
}
