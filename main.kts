// Explore a simple class

println("UW Homework: Simple Kotlin")

// write a "whenFn" that takes an arg of type "Any" and returns a String
fun whenFn(word: Any) : String {
    return when(word) {
        "Hello" -> "world";
        0 -> "zero";
        1 -> "one";
        in 2..10 -> "low number";
        is Int -> "a number";
        else -> "I don't understand";
    }
}

// write an "add" function that takes two Ints, returns an Int, and adds the values
fun add(i1: Int, i2: Int): Int {
    return i1 + i2;
}

// write a "sub" function that takes two Ints, returns an Int, and subtracts the values
fun sub(i1: Int, i2: Int): Int {
    return i1 - i2;
}

// write a "mathOp" function that takes two Ints and a function (that takes two Ints and returns an Int), returns an Int, and applies the passed-in-function to the arguments
fun mathOp(i1: Int, i2: Int, op: (i1: Int, i2: Int) -> Int): Int{
    return op(i1, i2);
}

// write a class "Person" with first name, last name and age
class Person (firstName: String, lastName: String, age: Int) {
    var firstName: String;
    var lastName: String;
    var age: Int;

    val debugString: String
        get() {
          return "[Person firstName:$firstName lastName:$lastName age:$age]";
        }

    init{
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    fun equals(p: Person): Boolean{
        return p.firstName == this.firstName && p.lastName == this.lastName && p.age == this.age;
    }

    override fun hashCode(): Int{
        var result = 17;
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + age;
        return result;
    }
}

// write a class "Money"
class Money (amount: Int, currency: String) {
    var amount: Int;
    var currency: String;

    init{
        this.amount = amount;
        this.currency = currency;
    }
    fun convert(convertTo: String): Money {
        var money = this.amount.toDouble();
        when(this.currency) {
            "GBP" -> money *= 2;
            "EUR" -> money /= 1.5;
            "CAN" -> money /= 1.25;
        }
        when(convertTo) {
            "GBP" -> money /= 2;
            "CAN" -> money *= 1.25;
            "EUR" -> money *= 1.5;
        }
        return Money(money.toInt(), convertTo);
    }
    operator fun plus(m: Money): Money {
        if (this.currency == m.currency) {
            return Money(this.amount + m.amount, this.currency);
        } else {
            return Money((this.convert(m.currency).amount + m.amount), m.currency);
        }
    }
}

// ============ DO NOT EDIT BELOW THIS LINE =============

print("When tests: ")
val when_tests = listOf(
    "Hello" to "world",
    "Howdy" to "Say what?",
    "Bonjour" to "Say what?",
    0 to "zero",
    1 to "one",
    5 to "low number",
    9 to "low number",
    17.0 to "I don't understand"
)
for ((k,v) in when_tests) {
    print(if (whenFn(k) == v) "." else "!")
}
println("")

print("Add tests: ")
val add_tests = listOf(
    Pair(0, 0) to 0,
    Pair(1, 2) to 3,
    Pair(-2, 2) to 0,
    Pair(123, 456) to 579
)
for ( (k,v) in add_tests) {
    print(if (add(k.first, k.second) == v) "." else "!")
}
println("")

print("Sub tests: ")
val sub_tests = listOf(
    Pair(0, 0) to 0,
    Pair(2, 1) to 1,
    Pair(-2, 2) to -4,
    Pair(456, 123) to 333
)
for ( (k,v) in sub_tests) {
    print(if (sub(k.first, k.second) == v) "." else "!")
}
println("")

print("Op tests: ")
print(if (mathOp(2, 2, { l,r -> l+r} ) == 4) "." else "!")
print(if (mathOp(2, 2, ::add ) == 4) "." else "!")
print(if (mathOp(2, 2, ::sub ) == 0) "." else "!")
print(if (mathOp(2, 2, { l,r -> l*r} ) == 4) "." else "!")
println("")


print("Person tests: ")
val p1 = Person("Ted", "Neward", 47)
print(if (p1.firstName == "Ted") "." else "!")
p1.age = 48
print(if (p1.debugString == "[Person firstName:Ted lastName:Neward age:48]") "." else "!")
println("")

print("Money tests: ")
val tenUSD = Money(10, "USD")
val twelveUSD = Money(12, "USD")
val fiveGBP = Money(5, "GBP")
val fifteenEUR = Money(15, "EUR")
val fifteenCAN = Money(15, "CAN")
val convert_tests = listOf(
    Pair(tenUSD, tenUSD),
    Pair(tenUSD, fiveGBP),
    Pair(tenUSD, fifteenEUR),
    Pair(twelveUSD, fifteenCAN),
    Pair(fiveGBP, tenUSD),
    Pair(fiveGBP, fifteenEUR)
)
for ( (from,to) in convert_tests) {
    print(if (from.convert(to.currency).amount == to.amount) "." else "!")
}
val moneyadd_tests = listOf(
    Pair(tenUSD, tenUSD) to Money(20, "USD"),
    Pair(tenUSD, fiveGBP) to Money(20, "USD"),
    Pair(fiveGBP, tenUSD) to Money(10, "GBP")
)
for ( (pair, result) in moneyadd_tests) {
    print(if ((pair.first + pair.second).amount == result.amount &&
              (pair.first + pair.second).currency == result.currency) "." else "!")
}
println("")
