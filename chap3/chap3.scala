
import scala.collection.mutable.HashSet
import scala.collection.mutable.Map
import scala.io.Source

object chap3 {
    def testArray() = {
        // the length of Array can't be changed after you create it
        // but you can change each value in the Array
        // Also, the type of each component in Array must be the same
        val greetingString = new Array[String](3)
        greetingString(0) = "hello, "
        greetingString(1) = "newbie"
        greetingString(2) = "!\n"

        for(i <- 0 to 2) {
            print(greetingString(i))
        }
    }

    def testArray2() = {
        val numNames = Array("zero", "one", "two") // equals to Array.apply("zero", "one", "two"), object Array is the companion object of class Array
        numNames.foreach(println)
    }


    def testList() = {
        // the elements of list can't be changed
        // the element type can't be changed
        // the element value also can't be changed
        // you can concat two Lists to build a new List
        // scala.List is very similar to List in Haskell
        val oneTwoThree = List(1, 2, 3)
        println("First List is " + oneTwoThree)

        val oneTwo = List(1, 2)
        val threeFour = List(3, 4)
        val ottf = oneTwo ::: threeFour
        println("Concat two lists result: " + ottf)

        // we can only 'cons' a elem from left
        // :: is the right-operational operator
        val zottf = 0 :: ottf  // means ottf.::(0)
        println("Cons zero to ottf: " + zottf)
    }

    def testNil() = {
        val ott = 1 :: 2 :: 3 :: Nil
        println("testNil: " + ott)
        println("The second elem of ott is: " + ott(1))
    }

    def testListOps() = {
        println("Test ListOps")
        val l = "Will" :: "fill" :: "until" :: Nil

        // apply
        assert(l(1) == "fill")
        // count
        assert(l.count(s => s.length == 4) == 2)
        // exists
        assert(l.exists(s => s == "until"))
        // filter
        assert(l.filter(s => s.length == 4) == List("Will", "fill"))
        // forall
        assert(l.forall(s => s.endsWith("l")))
        // foreach
        l.foreach(print)
        println
        // head/tail
        assert(l.head == "Will")
        assert(l.tail == List("fill", "until"))
        // init/last
        assert(l.init == List("Will", "fill"))
        assert(l.last == "until")
        // isEmpty
        assert(!l.isEmpty)
        // length
        assert(l.length == 3)
        // map
        assert(l.map(s => s + "y") == List("Willy", "filly", "untily"))
        // mkString
        assert(l.mkString(",") == "Will,fill,until")
        // drop
        assert(l.drop(2) == List("until"))
        // dropRight
        assert(l.dropRight(2) == List("Will"))
        // reverse
        assert(l.reverse == List("until", "fill", "Will"))
        // sort
        assert(l.sortWith((s, t) => s.toLowerCase.charAt(0) < t.toLowerCase.charAt(0)) == List("fill", "until", "Will"))
    }

    def testTuple() = {
        println("testTuple")

        val pair = (99, "Alex")
        println("_1: " + pair._1)
        println("_2: " + pair._2)
    }

    def testSet() = {
        println("testSet")

        val set = Set("Boeing", "Airbus")
        println(set)
        set.foreach(println)

        // because we've imported scala.collection.mutable.HashSet, so we
        // can use 'val' instead of 'var' on set2
        val set2 = HashSet("Boeing", "Airbus")
        set2 += "Lear"
        println(set2) 
    }

    def testMap() = {
        println("=== testMap ===")
        val map : Map[Char, String] = Map()
        // val map = Map[Char, String]()  // another way to new a map
        map += ('1' -> "Boeing")
        map += ('2' -> "Airbus")
        map.foreach(t => println(t._1 + " -> " + t._2))
        println(map('1'))
        println(map('2'))

        println(map.mkString(", "))

        // -> operator can be applied on any type to build a Tuple
        var a = 1 -> "Boeing"
        println(a)
    }

    def widthOfLength(s: String) = s.length.toString.length
    def testFile(filepath: String) = {
        println("testFile on " + filepath)
        // open file and get file content
        val file = Source.fromFile(filepath)

        // get the longest line's width
        val lineList = file.getLines.toList
        val longestLine = lineList.reduceLeft(
            (a, b) => if(a.length > b.length) a else b
        )
        val longestWidth = widthOfLength(longestLine)

        // output the file with length of line
        for(line <- lineList) {
            val space = longestWidth - widthOfLength(line)
            println(" " * space + line.length + " | " + line)
        }
    }

    def testFile2(filepath: String) = {
        // open file
        val file = Source.fromFile(filepath)

        // get file content and lines
        val content = file.getLines.toList
        val lineNum = content.length
        val maxLineNumWidth = lineNum.toString.length

        // output the file with line number
        var i = 1
        for(line <- content) {
            val padding = maxLineNumWidth - i.toString.length
            println(" " * padding + i + " | " + line)
            i+=1
        }
    }
    
    def main(args: Array[String]) = {
        testArray()
        testArray2()
        testList // () can be omitted when no arguments needed
        testNil
        testListOps
        testTuple
        testSet
        testMap
        testFile("./chap3.scala")
        testFile2("./chap3.scala")
    }
}
