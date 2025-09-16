/*
Endryo Henrique de Moraes RA:60002686
Ryan Stanger RA: 60001050
Rafael Junkes Alberti RA: 60001175
João Grigolo RA: 60300661
*/

import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet

fun buscarPessoasPorNome(parteNome: String): List<String> {
    val pessoas = mutableListOf<String>()

    // Corrigido: parâmetros posicionais
    val url = "jdbc:postgresql://localhost:5433/questao2"
    val usuario = "postgres"
    val senha = "postgres"

    val conexao: Connection = DriverManager.getConnection(url, usuario, senha)

    val sql = "SELECT nome FROM Pessoa WHERE nome LIKE ?"
    val stmt: PreparedStatement = conexao.prepareStatement(sql)
    stmt.setString(1, "%$parteNome%")

    val rs: ResultSet = stmt.executeQuery()
    while (rs.next()) {
        pessoas.add(rs.getString("nome"))
    }

    rs.close()
    stmt.close()
    conexao.close()

    return pessoas
}

fun main() {
    val resultado = buscarPessoasPorNome("A")
    println("Pessoas encontradas:")
    resultado.forEach { println(it) }
}
