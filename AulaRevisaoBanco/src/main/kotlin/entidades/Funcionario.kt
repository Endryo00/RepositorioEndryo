package entidades

import entidades.Conta
import enumeradores.Setor
import enumeradores.Sexo
import java.math.BigDecimal

class Funcionario(
    nome : String,
     idade: Int,
     sexo: Sexo,
    val anosExperiencia: Int,
    val nacionalidade: String,
    val idioma: String,
     cpf: Long,
    val rg: Long,
    val telefone: Int,
    var salario: BigDecimal,
    val endereco: String,
    val turno: String,
    val setor: Setor,

) : Pessoa(
    nome = nome,
    cpf = cpf,
    sexo = sexo,
    idade = idade
)
{
    //Comportamentos do Profissional
    fun instalarCaixaDAgua(CLT: Funcionario)
    {
        if (CLT.setor.equals(Setor.MONTAGEM)){
            println("Profissional Habilitado")
        }
        else{
            println("Profissional Desqualificado")
        }
    }

    override fun receberDivida(
        conta: Conta,
        numeroConta: Int,
        divida: BigDecimal
    )
    {
        conta.saldo = conta.saldo.subtract(divida)
    }

}


