package entidades

import java.math.BigDecimal

class CaixaDAgua (
    val capacidade : Int,
    val cor : String,
    val peso : Double,
    val preco : BigDecimal,
    val dimensao : Array<Double>,
    val material : String,
    val conteudo : String,
    val marca : String,
    val vazao : Int
)
{

}