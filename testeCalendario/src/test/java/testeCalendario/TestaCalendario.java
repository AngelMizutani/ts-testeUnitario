package testeCalendario;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import paranavai.calendario.Calendario;

public class TestaCalendario {
	private final ByteArrayOutputStream saidaConsole = new ByteArrayOutputStream();
	private final PrintStream saidaOriginal = System.out;
	
	
	@Before
	public void executaAntes() {
		System.setOut(new PrintStream(saidaConsole));
	}
	
	@After
	public void executaDepois() {
		System.setOut(new PrintStream(saidaOriginal));
	}
	
	//V(1)
	@Test
	public void testarImpressaoDoAnoRecebidoPorParametro() throws IOException {
		Path arquivo = Paths.get("src\\test\\resources", "2022.txt");
		String ano2022 = Files.readString(arquivo); // saída esperada
		Calendario.mostrarCalendario("2022");
		String obtido = saidaConsole.toString();
		assertEquals(ano2022.replaceAll("\\s+", ""), obtido.replaceAll("\\s+", ""));
	}
	
	//I(2)
	@Test
	public void testarQuantidadeDeParametrosMaiorQueDois() throws IOException {
		Path arquivo = Paths.get("src\\test\\resources", "janeiro2022.txt");
		String janeiro2022 = Files.readString(arquivo); // saída esperada
		Calendario.mostrarCalendario("1", "2022", "12");
		String obtido = saidaConsole.toString();
		assertEquals(janeiro2022.replaceAll("\\s+", ""), obtido.replaceAll("\\s+", ""));
	}
	
	//I(3)
	@Test
	public void testarAnoNaoInteiro() {
		Throwable excecao = assertThrows(NumberFormatException.class, 
				() -> Calendario.mostrarCalendario("@"));
		assertEquals("mostrarCalendario: @: ano inválido.", excecao.getMessage());
	}
	
	//I(4)
	@Test
	public void testarAnoMenorQueUm() {
		Throwable excecao = assertThrows(NumberFormatException.class, 
				() -> Calendario.mostrarCalendario("0"));
		assertEquals("mostrarCalendario: 0: ano inválido.", excecao.getMessage());
	}
	
	//I(5)
	@Test
	public void testarAnoMaiorQue9999() {
		Throwable excecao = assertThrows(NumberFormatException.class, 
				() -> Calendario.mostrarCalendario("10000"));
		assertEquals("mostrarCalendario: 10000: ano inválido.", excecao.getMessage());
	}
	
	//I(6)
	@Test
	public void testarAnoEntre1E9999() throws IOException {
		Path arquivo = Paths.get("src\\test\\resources", "2022.txt");
		String ano2022 = Files.readString(arquivo); // saída esperada
		Calendario.mostrarCalendario("2022");
		String obtido = saidaConsole.toString();
		assertEquals(ano2022.replaceAll("\\s+", ""), obtido.replaceAll("\\s+", ""));
	}
	
	//I(7)
	@Test
	public void testarMesAnoNaoInteiro() {
		Throwable excecao = assertThrows(NumberFormatException.class, 
				() -> Calendario.mostrarCalendario("@", "@"));
		assertEquals("mostrarCalendario: @: mês inválido.", excecao.getMessage());
	}
	
	//I(8)
	@Test
	public void testarMesNaoInteiroAnoMenorQueUm() {
		Throwable excecao = assertThrows(NumberFormatException.class,
				() -> Calendario.mostrarCalendario("@", "0"));
		assertEquals("mostrarCalendario: @: mês inválido.", excecao.getMessage());
	}
	
	//I(9)
	@Test
	public void testarMesNaoInteiroAnoMaiorQue9999() {
		Throwable excecao = assertThrows(NumberFormatException.class,
				() -> Calendario.mostrarCalendario("@", "10000"));
		assertEquals("mostrarCalendario: @: mês inválido.", excecao.getMessage());
	}
	
	//I(10)
	@Test
	public void testarMesNaoInteiroAnoEntre1E9999() {
		Throwable excecao = assertThrows(NumberFormatException.class,
				() -> Calendario.mostrarCalendario("@", "2022"));
		assertEquals("mostrarCalendario: @: mês inválido.", excecao.getMessage());
	}
	
	//I(11)
	@Test
	public void testarMesMenorQueUmAnoNaoInteiro() {
		Throwable excecao = assertThrows(NumberFormatException.class,
				() -> Calendario.mostrarCalendario("0", "@"));
		assertEquals("mostrarCalendario: 0: mês inválido.", excecao.getMessage());
	}
	
	//I(12)
	@Test
	public void testarMesAnoMenorQueUm() {
		Throwable excecao = assertThrows(NumberFormatException.class,
				() -> Calendario.mostrarCalendario("0", "0"));
		assertEquals("mostrarCalendario: 0: mês inválido.", excecao.getMessage());
	}
	
	//I(13)
	@Test
	public void testarMesMenorQueUmAnoMaiorQue9999() {
		Throwable excecao = assertThrows(NumberFormatException.class,
				() -> Calendario.mostrarCalendario("0", "10000"));
		assertEquals("mostrarCalendario: 0: mês inválido.", excecao.getMessage());
	}
		
	//I(14)
	@Test
	public void testarMesMenorQueUmAnoEntre1E9999() {
		Throwable excecao = assertThrows(NumberFormatException.class,
				() -> Calendario.mostrarCalendario("0", "2022"));
		assertEquals("mostrarCalendario: 0: mês inválido.", excecao.getMessage());
	}
		
	//I(15)
	@Test
	public void testarMesMaiorQue12AnoNaoInteiro() {
		Throwable excecao = assertThrows(NumberFormatException.class,
				() -> Calendario.mostrarCalendario("13", "@"));
		assertEquals("mostrarCalendario: 13: mês inválido.", excecao.getMessage());
	}
	
	//I(16)
	@Test
	public void testarMesMaiorQue12AnoMenorQueUm() {
		Throwable excecao = assertThrows(NumberFormatException.class,
				() -> Calendario.mostrarCalendario("13", "0"));
		assertEquals("mostrarCalendario: 13: mês inválido.", excecao.getMessage());
	}
	
	//I(17)
	@Test
	public void testarMesMaiorQue12AnoMaiorQue9999() {
		Throwable excecao = assertThrows(NumberFormatException.class,
				() -> Calendario.mostrarCalendario("13", "10000"));
		assertEquals("mostrarCalendario: 13: mês inválido.", excecao.getMessage());
	}
	
	//I(18)
	@Test
	public void testarMesMaiorQue12AnoEntre1E9999() {
		Throwable excecao = assertThrows(NumberFormatException.class,
				() -> Calendario.mostrarCalendario("13", "2022"));
		assertEquals("mostrarCalendario: 13: mês inválido.", excecao.getMessage());
	}
	
	//I(19)
	@Test
	public void testarMesEntre1E12AnoNaoInteiro() {
		Throwable excecao = assertThrows(NumberFormatException.class,
				() -> Calendario.mostrarCalendario("5", "@"));
		assertEquals("mostrarCalendario: @: ano inválido.", excecao.getMessage());
	}
	
	//I(20)
	@Test
	public void testarMesEntre1E12AnoMenorQueUm() {
		Throwable excecao = assertThrows(NumberFormatException.class,
				() -> Calendario.mostrarCalendario("5", "0"));
		assertEquals("mostrarCalendario: 0: ano inválido.", excecao.getMessage());
	}
	
	//I(21)
	@Test
	public void testarMesEntre1E12AnoMaiorQue9999() {
		Throwable excecao = assertThrows(NumberFormatException.class,
				() -> Calendario.mostrarCalendario("5", "10000"));
		assertEquals("mostrarCalendario: 10000: ano inválido.", excecao.getMessage());
	}
	
	//V(22)
	@Test
	public void testarMesEntre1E12AnoEntre1E9999() throws IOException{
		Path arquivo = Paths.get("src\\test\\resources", "janeiro2022.txt");
		String janeiro2022 = Files.readString(arquivo); // saída esperada
		Calendario.mostrarCalendario("1", "2022");
		String obtido = saidaConsole.toString();
		assertEquals(janeiro2022.replaceAll("\\s+", ""), obtido.replaceAll("\\s+", ""));
	}
}
