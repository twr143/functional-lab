package sl
import shapeless._


/**
  * Created by Ilya Volynin on 27.10.2018 at 17:33.
  */
object LensesApp extends App {
  case class Address(street : String, city : String, postcode : String)
  case class Person(name : String, age : Int, address : Address)

  // Some lenses over Person/Address ...
  val nameLens     = lens[Person].name
    val ageLens      = lens[Person].age
    val addressLens  = lens[Person].address
    val streetLens   = lens[Person].address.street
    val cityLens     = lens[Person].address.city
    val postcodeLens = lens[Person].address.postcode

  val person = Person("Joe Grey", 37, Address("Southover Street", "Brighton", "BN2 9UA"))

  val age1 = ageLens.get(person)               // Read field, note inferred type

  val person2 = ageLens.set(person)(38)        // Update field

  val person3 = ageLens.modify(person2)(_ + 1) // Transform field

  val street = streetLens.get(person3)         // Read nested field

  val person4 = streetLens.set(person3)("Montpelier Road")  // Update nested field

  println(s"person4= $person4")

  val person5 = person.copy(name= "Ilya", address = person.address.copy(postcode = "150034"))

  println(s"person5= $person5")

}
