import scala.io.Source

// 测试本地函数，即定义在函数内部的函数，可以防止很多小函数名导致过多的实例函数
object LongLines {
    def processFile(file: String, width: Int) = {
        def isLongLine(line: String) =
            line.length > width

        def getLine(line: String) =
            line.length + "| " + line

        val lines = Source.fromFile(file).getLines.toList
        val longLines =
            for(line <- lines
                if isLongLine(line))
            yield getLine(line)
        println(longLines.mkString("\n"))
    }

    def main(args: Array[String]) {
        def usage() {
            println("Arguments: <width> <files...>")
        }
        if(args.length < 2) {
            usage
            sys.exit()
        }

        val width = args(0).toInt
        for(file <- args.drop(1))
            processFile(file, width)
    }
}
