
class Profissional(
    id: Int = 0,
    nome: String = "",
    cpf: String = "",
    var profissao: String = ""
) : Pessoa(id, nome, cpf)