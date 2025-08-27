package entidades

import enumeradores.Sexo
import java.math.BigDecimal

class Cliente(
    cpf: Long,
    nome: String,
    idade: Int,
    sexo: Sexo,
    divida: BigDecimal,
    val servicoRequisitado: String,
    val telefone: Int,
    val email: String,
    val endereco: String,
    val pedidos: Array<String>
) : Pessoa(
    nome = nome,
    idade = idade,
    cpf = cpf,
    sexo = sexo
)
{
    //comportamento
    /* fun receberDivida(
        conta: Conta,
        numeroConta: Int,
        debito: BigDecimal
    )
    {
        conta.saldo = conta.saldo.add(debito)
    }
     */
}
