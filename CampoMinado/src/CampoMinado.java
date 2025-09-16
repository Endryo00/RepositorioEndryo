import java.util.Random;
import java.util.Scanner;

public class CampoMinado {

    public static void main(String[] args) {

        int linha = 10;
        int coluna = 10;
        Random sortear = new Random();

        char[][] campo = new char[linha][coluna];

        for (int i = 0; i < linha; i++) {

            for (int j = 0; j < coluna; j++) {
                campo[i][j] = '*';
                System.out.print(" * ");
            }

            System.out.println(" ");

        }

        Scanner sc = new Scanner(System.in);

        System.out.println("Quantas bombas vamos adicionar");
        int quantidade = sc.nextInt();

        int plantada = 0;

        while (plantada < quantidade) {

            int linhaSorteio = sortear.nextInt(linha);
            int colunaSorteada = sortear.nextInt(coluna);

            char valorPosicao = campo[linhaSorteio][colunaSorteada];

            if (valorPosicao == '*') {
                campo[linhaSorteio][colunaSorteada] = 'O';
                plantada++;
            }
        }

        for (int i = 0; i < linha; i++) {

            for (int j = 0; j < coluna; j++) {
                System.out.print(" " + campo[i][j] + " ");
            }

            System.out.println(" ");
        }


        boolean continuar = true;
        int tentativas = 0;
        do {

            System.out.println("Informe uma linha : ");
            int linhaEscolhida = sc.nextInt();

            System.out.println("Informe uma coluna :");
            int colunaEscolhida = sc.nextInt();

            char valorEscolhido = campo[linhaEscolhida][colunaEscolhida];

            if (valorEscolhido == 'O') {

                System.out.println("BOMMMMMBAAAAA");

            } else {

                tentativas++;

                if (tentativas == (linha * coluna - quantidade)) {

                    System.out.println("Parabens Você Ganhou!!");
                    continuar = false;
                }


            }
        } while (continuar);
    }
}