package pl.slowik.PriceList;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.slowik.PriceList.catalog.application.CatalogInitializeService;

@SpringBootApplication
@AllArgsConstructor
public class LenovoPriceListAppApplication implements CommandLineRunner {

	private final CatalogInitializeService catalogService;

	public static void main(String[] args) {
		SpringApplication.run(LenovoPriceListAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
