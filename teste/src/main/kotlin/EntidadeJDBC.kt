

import java.sql.Connection
import java.sql.DriverManager

class EntidadeJDBC(
    val url: String,
    val usuario: String,
    val senha: String
) {
    fun conectarComBanco(): Connection? {
        return try {
            Class.forName("org.postgresql.Driver")
            DriverManager.getConnection(url, usuario, senha)
        } catch (e: Exception) {
            println("Erro na conexão: ${e.message}")
            null
        }
    }
}