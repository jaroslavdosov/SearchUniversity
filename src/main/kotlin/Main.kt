import bd.DbSettings

fun main() {
    DbSettings.db

    print("Введите название страны: ")
    val country = readlnOrNull()

    val universityManager = UniversityManager()
    val universityList = universityManager.getUniversityListByCountry(country.toString())

    if (universityList.isNotEmpty()) {
        universityManager.insertUniversitiesInBd(universityList)

        print("Введите название вуза: ")
        val universityName = readlnOrNull()
        universityManager.searchUniversityByName(universityName.toString())

    }
    else {
        println("Страна не найдена. Пример страны: Germany")
    }
}
