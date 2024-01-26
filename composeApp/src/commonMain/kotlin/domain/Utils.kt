package domain

fun String.removeNonNumbers(): String {
    return filter { it != '"' && it != '\'' }
}
