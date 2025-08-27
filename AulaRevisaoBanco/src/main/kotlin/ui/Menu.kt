package org.example.ui

fun menu()
{
    println("1 - Cadastrar Caixa D'Agua ")
    println("2 - Editar Caixa D'Agua ")
    println("3 - Listar Caixa D'Agua ")
    println("4 - Excluir Caixa D'Agua ")

    var opcao = readln().toInt()

    when (opcao) {
        1 -> println("Cadastrando caixa...")
        2 -> println("Editando caixa...")
        3 -> println("Listando caixa...")
        4 -> println("Excluindo caixa...")
        else -> println("Opçao Invalida, por favor digite o numero da opção")
    }

}