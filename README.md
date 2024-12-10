# Scala: Subtle Concurrency Issue with Private Var

This repository demonstrates a subtle concurrency issue that can arise when using `private var` in Scala classes without proper synchronization mechanisms.  The example involves a `MyClass` with an `age` property that enforces non-negativity. While the single-threaded version seems correct, concurrent access could lead to unpredictable results. The solution showcases how to address this problem using synchronization.