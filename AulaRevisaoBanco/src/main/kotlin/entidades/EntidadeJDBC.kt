package org.example.entidades

import java.sql.Connection

class EntidadeJDBC(
    val usuario : String,
    val url : String,
    val senha : String
)
{
    fun conectarComBanco() : Connection(

    )
}