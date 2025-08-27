package entidades

import entidades.Pessoa
import enumeradores.Sexo
import java.math.BigDecimal

open class Conta
    (
    cpf: Long,
    nome: String,
    idade: Int,
    sexo: Sexo,
            val tipoConta: String,
            val numeroConta: Int,
            val senha: String,
            var saldo: BigDecimal,
            val debito: BigDecimal,
            val extrato: BigDecimal,
            val creditoDisponivel: BigDecimal,
            val creditoLimite: BigDecimal,
            val creditoGasto: BigDecimal,
            val emprestimoDisponivel: BigDecimal,
            val dividaTotal: BigDecimal,
            val dividaPaga: BigDecimal,
            val dividaRestante: BigDecimal
            ) : Pessoa(
    nome = nome,
    idade = idade,
    cpf = cpf,
    sexo = sexo
)
{

}