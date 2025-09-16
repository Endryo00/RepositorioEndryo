
class Cliente(
    id: Int = 0,
    nome: String = "",
    cpf: String = "",
    var endereco: String = ""
) : Pessoa(id, nome, cpf)