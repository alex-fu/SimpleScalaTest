
object hello {
    def testWhile() = {
        println("Test While: find the sum of [1,100]! ")
        var i = 0
        var sum = 0
        while(i < 101) {
            sum += i
            i += 1
        }
        println("Sum of [1, 100] is " + sum)
    }

    def testForeach(args: Array[String]) = {
        println("Test Foreach")
        args.foreach(arg => println(arg))
        println("Another way function test: ")
        args.foreach(println)
    }

    def testFor(args: Array[String]) = {
        println("=== Test for ===")
        for(arg <- args) {
            println(arg)
        }
    }

    def main(args: Array[String]) = {
        if(args.length > 0)
            println("Hello, " + args(0) + "!")
        else
            println("Hello, cruel world!")

        testWhile()
        testForeach(args)
        testFor(args)
    }
}
