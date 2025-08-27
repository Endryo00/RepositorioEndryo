package entidades

import enumeradores.Sexo
import java.math.BigDecimal

open class Pessoa(
    val nome: String,
    val cpf: Long,
    val sexo: Sexo,
    val idade: Int
) {
    //Comportamento
    open fun receberDivida(
        conta: Conta,
        numeroConta: Int,
        debito: BigDecimal
    )
    {
        conta.saldo = conta.saldo.add(debito)
    }
}