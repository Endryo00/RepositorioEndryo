package org.example.crud

import enumeradores.Material

fun cadastrarCaixa()
{ /*
    val capacidade : Int,
    val cor : String,
    val peso : Double,
    val preco : BigDecimal,
    val dimensao : Array<Double>,
    val material : String,
    val conteudo : String,
    val marca : String,
    val vazao : Int
*/
    var material : Material
    //var peso : Peso
    //var cor : Cor
    //var capacidade : Capacidade
    //var dimensao : Dimensao

    println("Capacidade da Caixa em litros ")
    {
        val capacidade = readln().toInt()
    }

    println("Cor da Caixa ")
    {
        val cor = readln().toString()
    }


    println("Peso da Caixa em Kg ")
    {
        val peso = readln().toBigDecimal()
    }

    println("Preço da Caixa ")
    {
        val preco = readln().toInt()
    }

   /* println("Dimensao da Caixa ")
    {
        val dimensao = readln().toArray()
    }

    */

    println("Escolha o material da caixa:")
    println("1- Argamassa")
    println("1- Concreto")
    println("1- Tijolo")
    val opcao = readln().toInt()
    when (opcao)
    {
        1-> material = Material.ARGAMASSA
        2-> material = Material.CONCRETO
        3-> material = Material.TIJOLO
        else -> material = Material.TIJOLO
    }

    //Continuar isso aqui, e colocar em ordem

    //salvar as variaveis agora dentro da classe
    //conecte o atributo da classe a variavel que o usuario digitou

    material = material,
    dimensao = dimensao,
}

fun editarCaixa()
{

}

fun listarCaixas()
{

}

fun excluirCaixa()
{

}
