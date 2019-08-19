package sl
import shapeless._


/**
  * Created by Ilya Volynin on 27.10.2018 at 17:33.
  */
object LensesApp extends App {
  case class Street(name : String, number :Int)

  case class Address(street : Street, city : String, postcode : String)
  case class Person(name : String, age : Int, address : Address)

  // Some lenses over Person/Address ...
  val nameLens     = lens[Person].name
    val ageLens      = lens[Person].age
    val addressLens  = lens[Person].address
    val streetNameLens  = lens[Person].address.street.name
    val streetNoLens  = lens[Person].address.street.number
    val cityLens     = lens[Person].address.city
    val postcodeLens = lens[Person].address.postcode

  val person = Person("Ilya V", 37, Address(Street("Lebedev",7), "Yaroslavl", "150000"))

  val age1 = ageLens.get(person)               // Read field, note inferred type

  val person2 = ageLens.set(person)(38)        // Update field

  val person3 = ageLens.modify(person2)(_ + 1) // Transform field

  val street = streetNameLens.get(person3)         // Read nested field

  val person4 = streetNameLens.set(person3)("Spartak")  // Update nested field
  val person41 = nameLens.set(person4)("Ilyaaaa")  // Update nested field

  println(s"person4= $person41")

  val person5 = person.copy(name= "Ilya", address = person.address.copy(postcode = "150034"))

  println(s"person5= $person5")

}
