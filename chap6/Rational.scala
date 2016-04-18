// 类参数默认是private val的，所以不能从外面访问，也不能被改变
class Rational(n: Int, d: Int) {
    // 'require' 是参数校验，类似assert，若不满足则抛出异常
    require(d != 0)
    // 下面的println类似与java里面的构造函数的内容
    println("Created " + n + "/" + d)

    // 定义两个成员变量，默认是public
    private val g = gcd(n.abs, d.abs)
    private val numer = n/g
    private val denom = d/g

    // 从构造函数; 注意：每一个从构造函数的第一个动作都必须是调用同一个类里面的其他构造器
    def this(n: Int) = this(n, 1)
    
    // 覆盖java.Object的toString方法
    override def toString() = if(denom != 1) numer + "/" + denom
                              else numer.toString

    // 定义加法
    def +(that: Rational) = 
        new Rational(
            this.numer * that.denom + that.numer * this.denom, 
            this.denom * that.denom
        )
    
    def *(that: Rational) = new Rational(numer * that.numer, denom * that.denom)

    // 定义求最大公约数
    def gcd(a: Int, b: Int): Int = 
        if(b == 0) a else gcd(b, a % b)
}

object Rational {
    // 定义隐式转换，隐式转换需要定义在使用它的作用范围内，因此不能定义到class Rational中
    implicit def intToRational(x: Int) = new Rational(x)
    
    def main(args: Array[String]) {
        println("=== test")

        val r = new Rational(1,2)
        println("r", r)
        val r2 = new Rational(1,2)
        println("r2", r2)

        val r3 = r + r2
        println("r3", r3)
        
        val r4 = r * r2
        println("r4", r4)
        
        println(r4 * 2)
        
        println(3 * r4)

        println("=== test done")
    }
}
