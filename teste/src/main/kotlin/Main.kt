
import java.math.BigDecimal
import java.sql.ResultSet

// Configuração da conexão (ajuste URL, usuário e senha conforme necessário)
val conectar = EntidadeJDBC(
    url = "jdbc:postgresql://localhost:5432/projetopessoa",  // Mude o nome do DB se necessário
    usuario = "postgres",
    senha = "postgres"
)

// Função para criar as tabelas (chame uma vez para inicializar o banco)
fun criarTabelas() {
    // Tabela base para Pessoa (campos comuns)
    val sqlPessoa = """
        CREATE TABLE IF NOT EXISTS pessoa (
            id SERIAL PRIMARY KEY,
            nome VARCHAR(255) NOT NULL,
            cpf VARCHAR(255) NOT NULL
        )
    """.trimIndent()

    // Tabela para Profissional (herda id de pessoa)
    val sqlProfissional = """
        CREATE TABLE IF NOT EXISTS profissional (
            id INT PRIMARY KEY REFERENCES pessoa(id) ON DELETE CASCADE,
            profissao VARCHAR(255) NOT NULL
        )
    """.trimIndent()

    // Tabela para Cliente (herda id de pessoa)
    val sqlCliente = """
        CREATE TABLE IF NOT EXISTS cliente (
            id INT PRIMARY KEY REFERENCES pessoa(id) ON DELETE CASCADE,
            endereco VARCHAR(255) NOT NULL
        )
    """.trimIndent()

    val banco = conectar.conectarComBanco()
    banco?.createStatement()?.execute(sqlPessoa)
    banco?.createStatement()?.execute(sqlProfissional)
    banco?.createStatement()?.execute(sqlCliente)

    println("Tabelas criadas com sucesso!")  // Se der certo, retorna false (sem rows affected)
    banco?.close()
}

// Função para cadastrar ou editar (simples: insere/atualiza pessoa e subclasse)
fun cadastrar(id: Int = 0) {
    val banco = conectar.conectarComBanco() ?: return

    // Coleta dados comuns
    println("Nome:")
    val nome = readln()
    println("CPF:")
    val cpf = readln()

    // Escolha do tipo
    println("Tipo: 1 - Profissional, 2 - Cliente")
    val tipo = readln().toIntOrNull() ?: 1
    val ehProfissional = tipo == 1

    var generatedId = id
    if (id == 0) {
        // INSERT em pessoa e pega ID gerado
        val sqlInsert = """
            INSERT INTO pessoa (nome, cpf) VALUES (?, ?) RETURNING id
        """.trimIndent()
        val stmt = banco.prepareStatement(sqlInsert)
        stmt.setString(1, nome)
        stmt.setString(2, cpf)
        val rs: ResultSet = stmt.executeQuery()
        if (rs.next()) {
            generatedId = rs.getInt("id")
        }
    } else {
        // UPDATE em pessoa
        val sqlUpdate = "UPDATE pessoa SET nome = ?, cpf = ? WHERE id = ?"
        val stmt = banco.prepareStatement(sqlUpdate)
        stmt.setString(1, nome)
        stmt.setString(2, cpf)
        stmt.setInt(3, id)
        stmt.executeUpdate()
    }

    // Coleta e insere/atualiza na subclasse
    if (ehProfissional) {
        println("Profissão:")
        val profissao = readln()
        if (id == 0) {
            val sqlInsert = "INSERT INTO profissional (id, profissao) VALUES (?, ?)"
            val stmt = banco.prepareStatement(sqlInsert)
            stmt.setInt(1, generatedId)
            stmt.setString(2, profissao)
            stmt.executeUpdate()
        } else {
            val sqlUpdate = "UPDATE profissional SET profissao = ? WHERE id = ?"
            val stmt = banco.prepareStatement(sqlUpdate)
            stmt.setString(1, profissao)
            stmt.setInt(2, id)
            stmt.executeUpdate()
        }
    } else {
        println("Endereço:")
        val endereco = readln()
        if (id == 0) {
            val sqlInsert = "INSERT INTO cliente (id, endereco) VALUES (?, ?)"
            val stmt = banco.prepareStatement(sqlInsert)
            stmt.setInt(1, generatedId)
            stmt.setString(2, endereco)
            stmt.executeUpdate()
        } else {
            val sqlUpdate = "UPDATE cliente SET endereco = ? WHERE id = ?"
            val stmt = banco.prepareStatement(sqlUpdate)
            stmt.setString(1, endereco)
            stmt.setInt(2, id)
            stmt.executeUpdate()
        }
    }

    println(if (id == 0) "Cadastrado com ID: $generatedId" else "Atualizado!")
    banco.close()
}

// Função para editar (busca e chama cadastrar com ID)
fun editar() {
    println("ID para editar:")
    val id = readln().toIntOrNull() ?: return
    // Busca e exibe dados atuais (simples, usando JOIN)
    val banco = conectar.conectarComBanco() ?: return
    val sql = """
        SELECT p.nome, p.cpf, pr.profissao, c.endereco 
        FROM pessoa p 
        LEFT JOIN profissional pr ON p.id = pr.id 
        LEFT JOIN cliente c ON p.id = c.id 
        WHERE p.id = ?
    """.trimIndent()
    val stmt = banco.prepareStatement(sql)
    stmt.setInt(1, id)
    val rs: ResultSet = stmt.executeQuery()
    if (rs.next()) {
        println("Dados atuais - Nome: ${rs.getString("nome")}, CPF: ${rs.getString("cpf")}")
        val prof = rs.getString("profissao")
        val end = rs.getString("endereco")
        if (prof != null) println("Profissão: $prof")
        if (end != null) println("Endereço: $end")
    } else {
        println("ID não encontrado!")
        banco.close()
        return
    }
    banco.close()
    println("Faça alterações:")
    cadastrar(id)
}

// Função para listar todas (usa JOIN para resgatar herança sem conflitos)
fun listar() {
    val banco = conectar.conectarComBanco() ?: return
    val sql = """
        SELECT p.id, p.nome, p.cpf, pr.profissao, c.endereco 
        FROM pessoa p 
        LEFT JOIN profissional pr ON p.id = pr.id 
        LEFT JOIN cliente c ON p.id = c.id
    """.trimIndent()
    val rs: ResultSet = banco.createStatement().executeQuery(sql)

    while (rs.next()) {
        println("--- ID: ${rs.getInt("id")} ---")
        println("Nome: ${rs.getString("nome")}")
        println("CPF: ${rs.getString("cpf")}")
        val prof = rs.getString("profissao")
        val end = rs.getString("endereco")
        if (prof != null) {
            println("Tipo: Profissional - Profissão: $prof")
        } else if (end != null) {
            println("Tipo: Cliente - Endereço: $end")
        }
    }
    banco.close()
}

// Função para excluir (deleta subclasse primeiro, depois pessoa)
fun excluir() {
    println("ID para excluir:")
    val id = readln().toIntOrNull() ?: return
    // Confirmação simples
    println("Confirmar? (sim)")
    if (readln().lowercase() != "sim") {
        println("Cancelado!")
        return
    }
    val banco = conectar.conectarComBanco() ?: return
    // Deleta subclasse se existir
    val sqlProf = "DELETE FROM profissional WHERE id = ?"
    banco.prepareStatement(sqlProf).use { it.setInt(1, id); it.executeUpdate() }
    val sqlCli = "DELETE FROM cliente WHERE id = ?"
    banco.prepareStatement(sqlCli).use { it.setInt(1, id); it.executeUpdate() }
    // Deleta pessoa
    val sqlDel = "DELETE FROM pessoa WHERE id = ?"
    val stmt = banco.prepareStatement(sqlDel)
    stmt.setInt(1, id)
    stmt.executeUpdate()
    println("Excluído!")
    banco.close()
}

// Função main para testar (opcional, chame as funções conforme necessário)
fun main() {
    criarTabelas()  // Chame uma vez
    // Exemplo de uso:
    // cadastrar()  // Novo
    // editar()     // Editar
    // listar()     // Listar
    // excluir()    // Excluir
}