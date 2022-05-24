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
	
	@Test
	public void testarImpressaoDoAnoRecebidoPorParametro() throws IOException {
		Path arquivo = Paths.get("src\\test\\resources", "2022.txt");
		String ano2022 = Files.readString(arquivo); // saída esperada
		Calendario.mostrarCalendario("2022");
		String obtido = saidaConsole.toString();
		assertEquals(ano2022.replaceAll("\\s+", ""), obtido.replaceAll("\\s+", ""));
	}
	
	@Test
	public void testarQuantidadeDeParametrosMaiorQueDois() throws IOException {
		Path arquivo = Paths.get("src\\test\\resources", "janeiro2022.txt");
		String janeiro2022 = Files.readString(arquivo); // saída esperada
		Calendario.mostrarCalendario("1", "2022", "12");
		String obtido = saidaConsole.toString();
		assertEquals(janeiro2022.replaceAll("\\s+", ""), obtido.replaceAll("\\s+", ""));
	}
	@Test
	public void testarAnoNaoInteiro() {
		Throwable excecao = assertThrows(NumberFormatException.class, 
				() -> Calendario.mostrarCalendario("@"));
		assertEquals("mostrarCalendario: @: ano inválido.", excecao.getMessage());
	}
}
