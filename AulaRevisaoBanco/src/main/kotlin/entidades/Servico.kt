package entidades

import enumeradores.Material
import java.math.BigDecimal

class Servico (
    val funcionarios : Funcionario,
    val custoMontagem : BigDecimal,
    val custoTransporte : BigDecimal,
    val dataInicio : Int,
    val horarioInicio: Int,
    val local : String,
    val equipamentos: String,
    val material: Material
)
{

}