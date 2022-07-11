import com.github.lipen.satlib.op.exactlyOne
import com.github.lipen.satlib.solver.CadicalSolver
import com.github.lipen.satlib.solver.CryptoMiniSatSolver
import com.github.lipen.satlib.solver.GlucoseSolver
import com.github.lipen.satlib.solver.MiniSatSolver
import com.github.lipen.satlib.solver.solve

fun main(args: Array<String>) {
    println("Hello World!")
    println("Program arguments: ${args.joinToString()}")

    for (solver in listOf(MiniSatSolver(), GlucoseSolver(), CryptoMiniSatSolver(), CadicalSolver())) {
        println("=".repeat(42))
        println("Using $solver")
        with(solver) {
            val x = newLiteral()
            val y = newLiteral()
            val z = newLiteral()

            println("Encoding exactlyOne(x, y, z)")
            exactlyOne(x, y, z)

            println("nVars = $numberOfVariables")
            println("nClauses = $numberOfClauses")

            println("Solving...")
            check(solve())
            println("model = ${getModel()}")

            println("Solving with assumption x=true...")
            check(solve(x))
            println("model = ${getModel()}")
            check(getValue(x))

            println("Solving with assumption y=true...")
            check(solve(y))
            println("model = ${getModel()}")
            check(getValue(y))

            println("Solving with assumption z=true...")
            check(solve(z))
            println("model = ${getModel()}")
            check(getValue(z))

            println("Everything OK.")
        }
    }
}
