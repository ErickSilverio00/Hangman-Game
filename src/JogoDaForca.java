import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Random;
import java.util.Scanner;

public class JogoDaForca {
	public static void main(String[] args) throws IOException {
		
		System.out.println("======= SEJA BEM VINDO AO JOGO DA FORCA =======");
		System.out.println("\n==== REGRAS ==== ");
		System.out.println("1- Você terá apenas 15 tentativas de escolhas de letras;");
		System.out.println("2- O sublinhado de cada letra só irá aparecer a partir do momento que você acertar a primeira letra;");
		System.out.println("3- Você pode tentar adivinhar a palavra, por escrito, a partir da 8ª tentativa;");
		System.out.println("4- Você perde se não conseguir adivinhar a palavra durante as 15 tentativas;");
		System.out.println("5- São aceitas somente letras minúsculas.");
		System.out.println("================");
		
		System.out.println("\nCOMEÇANDO O JOGO......");
		
		Scanner entrada = new Scanner(System.in);
		
		String letrasUsadas = "";
		String palavraAdivinhada = "";
		String palavraChutada;
		int tentativaMaxima = 15;
		
		Random random = new Random();
		
		LineNumberReader leitorDeLinhas = new LineNumberReader(new FileReader("geradorDePalavras.txt"));
		leitorDeLinhas.skip(Long.MAX_VALUE);
		int quantidadeDePalavras = leitorDeLinhas.getLineNumber() +1;
		System.out.println("Temos " +quantidadeDePalavras + " palavras!!!!");
		leitorDeLinhas.close();
		
		String [] palavras = new String[quantidadeDePalavras];
				
		File file = new File("geradorDePalavras.txt");
		BufferedReader leitorDoArquivo = new BufferedReader(new FileReader("geradorDePalavras.txt"));
		String linhaLida;
		int linha = 0;
		while ((linhaLida = leitorDoArquivo.readLine()) != null) {
			palavras[linha] = linhaLida;
			linha ++;
		}
		leitorDoArquivo.close();
		
		int indiceSorteado = random.nextInt(quantidadeDePalavras);
		String sorteada = palavras[indiceSorteado];
		
		for (int i = 0; i < sorteada.length(); i++) {
			palavraAdivinhada += "_";
			
			for (int tentativa = 1; ; tentativa++) {
				System.out.printf("\nTentativa %d.\nFoi adivinhado: %s \nLetras usadas até agora: %s", tentativa, palavraAdivinhada.toUpperCase(), letrasUsadas.toUpperCase());
				System.out.print("\nEscolha a próxima letra: ");
				
				char letraUsada = new Scanner(System.in).next().charAt(0);
				
				if(letrasUsadas.indexOf(letraUsada) >= 0) {
					System.out.printf("\nA letra '%c.%n' já foi usada....", letraUsada);
				} else {
					letrasUsadas += letraUsada;
					
					if(sorteada.indexOf(letraUsada) >= 0) {
						palavraAdivinhada = "";
						
						for (int j = 0; j < sorteada.length(); j++) {
							palavraAdivinhada += letrasUsadas.indexOf(sorteada.charAt(j)) >= 0 ? sorteada.charAt(j): " _ ";
						}
						
						if(palavraAdivinhada.contains("_")) {
							System.out.printf("\nExcelente!! A letra '%s' está nessa palavra.%n", letraUsada);
						} else {
							System.out.printf("\n==========Parabéns, Você acertou!!!!! A palavra que você adivinhou era '%s'==========", palavraAdivinhada.toUpperCase());
							System.exit(0);
						}
					} else {
						System.out.printf("\nA letra '%c' não existe nessa palavra....\n", letraUsada);
					}
				}
				
				if(tentativa == tentativaMaxima) {
					System.out.printf("Limite de tentativas atingido! \nA palavra era %s", sorteada);
					System.exit(0);
				}
				
				if (tentativa >= 8) {
					System.out.print("\nTente adivinhar a palavra >>>>>");
					palavraChutada = entrada.next();
				
				if (palavraChutada.equals(sorteada)) {
					System.out.printf("\n========== Parabéns, Você acertou!!!!! A palavra que você adivinhou era '%s'==========", sorteada.toUpperCase());
					System.exit(0);
				} else {
					System.out.println("Infelizmente a palavra correta não é essa! Continue tentando....");
				}
				}
			}
		}
		
	}
}