package exercises.week2oop



object ClassesOOP extends App {
  class Instructor(val id: Int, name: String, surname: String) {
    def fullName(): String = {
      s"${this.name.substring(0,1).toUpperCase}${this.name.substring(1)} ${this.surname.substring(0,1).toUpperCase}${this.surname.substring(1)}"
    }
  }

  class Course(courseID: Int, title: String, val releaseYear: String, instructor: Instructor) {

    def getId(): Int = {
      (this.courseID.toString + this.instructor.id.toString).toInt
    }

    def isTaughtBy(instructor: Instructor): Boolean = {
      instructor == this.instructor
    }

    def copyCourse(newReleaseYear: String) : Course = {
      new Course(this.courseID, this.title, newReleaseYear, this.instructor)
    }
  }

  val instructor = new Instructor(123,"arsen", "zaseev")
  println(instructor.fullName())
}
