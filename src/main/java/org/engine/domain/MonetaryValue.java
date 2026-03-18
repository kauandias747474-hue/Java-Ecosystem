import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.*;



record MonetaryValue(BigDecimal amount, Currency currency, RoundingMode roundingMode) {
 public MonetaryValue {
  Objects.requireNonNull(amount);
  Objects.requireNonNull(currency);

  amount = amount.setScale(currency.getDefaultFractionDigits(), roundingMode);
 }

 public static MonetaryValue of(String value, String currencyCode) {
  return new MonetaryValue(
          new BigDecimal(value),
          Currency.getInstance(currencyCode),
          RoundingMode.HALF_UP
  );
 }

 public MonetaryValue applyTax(BigDecimal rate) {
  BigDecimal factor = rate.divide(new BigDecimal("100"), 10, RoundingMode.HALF_UP);
  return new MonetaryValue(this.amount.multiply(factor), this.currency, this.roundingMode);
 }

 public String format(Locale locale) {
  NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
  formatter.setCurrency(this.currency);
  return formatter.format(this.amount);
 }
}


interface TaxStrategy {
 String name();
 MonetaryValue calculate(MonetaryValue base);
}

record TaxReport(UUID id, String taxName, MonetaryValue result, LocalDateTime emittedAt) {}



static class TaxProcessor {
 public TaxReport execute(MonetaryValue base, TaxStrategy strategy) {
  MonetaryValue taxValue = strategy.calculate(base);
  return new TaxReport(UUID.randomUUID(), strategy.name(), taxValue, LocalDateTime.now());
 }
}



void main() {
 // Setup inicial
 var valorBase = MonetaryValue.of("1500.00", "BRL");
 var localeBR = Locale.of("pt", "BR");


 TaxStrategy icms = new TaxStrategy() {
  @Override
  public String name() {
   return "ICMS São Paulo (18%)";
  }

  @Override
  public MonetaryValue calculate(MonetaryValue base) {
   return base.applyTax(new BigDecimal("18.00"));
  }
 };


 var processor = new TaxProcessor();
 var relatorio = processor.execute(valorBase, icms);


 System.out.println("=== RELATÓRIO FISCAL - MONETARY VALUE ===");
 System.out.println("ID Operação: " + relatorio.id());
 System.out.println("Valor Base:  " + valorBase.format(localeBR));
 System.out.println("Imposto:     " + relatorio.taxName());
 System.out.println("Valor Taxa:  " + relatorio.result().format(localeBR));
 System.out.println("Data/Hora:   " + relatorio.emittedAt());
}
