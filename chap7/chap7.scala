import java.io.File
import java.net.URL
import java.net.MalformedURLException

object chap7 {
    def fileLines(file: java.io.File) = 
        scala.io.Source.fromFile(file).getLines.toList

    def testFor() {
        val files = (new java.io.File(".")).listFiles
        println("=== test for")
        for(file <- files)
            println(file)

        // 使用多个条件加强过滤
        println("=== test 多条件过滤")
        for {
            file <- files
            if file.isFile
            if file.getName.endsWith(".scala")
        } println(file)

        // 嵌套循环
        println("=== test 嵌套循环")
        for {
            file <- files
            if file.getName.endsWith(".scala")
            line <- fileLines(file)
            trimmed = line.trim  // 定义临时val变量，无需写val
            if trimmed.matches(".*def.*")
        } println(file + ": " + line.trim)

        // for yield 语法
        println("=== test for yield")
        val forLineLengths = 
            for {
                file <- files
                if file.getName.endsWith(".scala")
                line <- fileLines(file)
                trimmed = line.trim
                if trimmed.matches(".*def.*")
            } yield {
                trimmed.length
            }
        println(forLineLengths.mkString(" "))

        println("=== test for range")
        for(i <- 1 to 5)
            print(i + " ")
        println

        for(i <- 1 until 10)
            print(i + ".")
        println
    }

    def urlFor(path: String) = 
        try {
            new URL(path)
        } catch {
            case e: MalformedURLException =>
                new URL("http://www.scalalang.org")
        }

    def testTryCatch() {
        println("=== test try catch")
        val baidu = urlFor("http://www.baidu.com")
        val haha = urlFor("haha")

        println(baidu)
        println(haha)
    }

    def matchCase(s: String) =
        s match {
            case "salt" => println("pepper")
            case "chips" => println("salsa")
            case "eggs" => println("bacon")
            case _ => println("huh?")
        }

    // match也能返回值
    def getMatchCase(s: String) =
        s match {
            case "salt" => "pepper"
            case "chips" => "salsa"
            case "eggs" => "bacon"
            case _ => "huh?"
        }

    def testMatch() {
        println("=== test match")
        matchCase("eggs")
        matchCase("chips")
        matchCase("dddd")

        println(getMatchCase("salt"))
    }

    //打印乘法表
    def makeRowSeq(row: Int) = 
        for (col <- 1 to 10) yield {
            val prod = (row * col).toString
            val padding = " " * (4 - prod.length)
            padding + prod
        }

    def makeRow(row: Int) = makeRowSeq(row).mkString

    def multiTable() = {
        val tableSeq =
            for(row <- 1 to 10)
                yield makeRow(row)
        tableSeq.mkString("\n")
    }

    def main(args: Array[String]) {
        testFor
        testTryCatch
        testMatch
        println(multiTable)
    }
}
