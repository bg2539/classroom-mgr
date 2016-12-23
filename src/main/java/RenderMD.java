import java.io.IOException;
import java.nio.charset.*;
import java.nio.file.*;

public class RenderMD {
	public static void main(String[] arg) throws IOException {
		System.out.print(Utils.md_serv_admin
				.getHtml(new String(Files.readAllBytes(Paths.get(arg[0])), Charset.defaultCharset()), "markdown"));
	}
}
