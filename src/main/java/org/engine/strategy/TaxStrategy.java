import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.text.NumberFormat;
import java.util.UUID;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Currency;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

record UserPayload(String username, String cpf) {
    public String masked() {
        return cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.***.***-$4");
    }
}

record TaxResult(UUID id, BigDecimal value, String strategyName, LocalDateTime timestamp) {
    public TaxResult {
        Objects.requireNonNull(id);
        Objects.requireNonNull(value);
        if (value.signum() < 0) throw new IllegalArgumentException("O imposto não pode ser negativo!");
    }
}

interface TaxStrategy {
    String getName();
    BigDecimal calculate(BigDecimal amount);
}

public class JavaMasterclassTest {

    static void main() {
        System.out.println("=== SISTEMA FINANCEIRO MULTI-ESTRATÉGIA ===\n");

        Locale localeBR = Locale.of("pt", "BR");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(localeBR);
        Currency currencyBRL = Currency.getInstance("BRL");

        System.out.println("Moeda Ativa: " + currencyBRL.getDisplayName() + " [" + currencyBRL.getSymbol() + "]\n");

        Map<String, TaxStrategy> taxRegistry = new HashMap<>();

        taxRegistry.put("ICMS", new TaxStrategy() {
            @Override public String getName() { return "ICMS Brasil (18%)"; }
            @Override public BigDecimal calculate(BigDecimal amount) {
                return amount.multiply(new BigDecimal("0.18")).setScale(2, RoundingMode.HALF_UP);
            }
        });

        taxRegistry.put("ISS", new TaxStrategy() {
            @Override public String getName() { return "ISS Municipal (5%)"; }
            @Override public BigDecimal calculate(BigDecimal amount) {
                return amount.multiply(new BigDecimal("0.05")).setScale(2, RoundingMode.HALF_UP);
            }
        });

        List<BigDecimal> invoices = Arrays.asList(
                new BigDecimal("5000.00"),
                new BigDecimal("1250.75"),
                new BigDecimal("300.00"),
                new BigDecimal("-10.00")
        );

        TaxStrategy activeStrategy = taxRegistry.get("ICMS");

        System.out.println("Iniciando motor com: " + activeStrategy.getName());
        long startTime = System.currentTimeMillis();

        List<TaxResult> results = invoices.parallelStream()
                .map(amount -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        BigDecimal taxValue = activeStrategy.calculate(amount);
                        return new TaxResult(UUID.randomUUID(), taxValue, activeStrategy.getName(), LocalDateTime.now());
                    } catch (Exception e) {
                        System.err.println("[FAIL-FAST] Bloqueado: " + amount + " | Motivo: " + e.getMessage());
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .toList();

        long endTime = System.currentTimeMillis();

        System.out.println("\n--- RELATÓRIO DE TAXAS ---");
        results.forEach(r -> System.out.println("Transação: " + r.id() + " | Taxa: " + formatter.format(r.value())));

        System.out.println("\nTempo total de execução: " + (endTime - startTime) + "ms");

        System.out.println("\n=== ANONIMIZANDO DADOS DE CLIENTES ===");
        List<UserPayload> users = List.of(
                new UserPayload("alex_dev", "12345678901"),
                new UserPayload("bia_manager", "98765432100")
        );

        users.forEach(u -> System.out.println("User: " + u.username() + " | CPF: " + u.masked()));

        exportToCSV(results);
    }

    private static void exportToCSV(List<TaxResult> results) {
        try {
            String csvContent = "ID,VALOR,DATA\n" + results.stream()
                    .map(r -> r.id() + "," + r.value() + "," + r.timestamp())
                    .collect(Collectors.joining("\n"));

            Files.write(Paths.get("tax_report.csv"), csvContent.getBytes());
            System.out.println("\n[NIO] Relatório CSV 'tax_report.csv' gerado com sucesso.");
        } catch (Exception e) {
            System.err.println("Erro ao exportar NIO: " + e.getMessage());
        }
    }
}
