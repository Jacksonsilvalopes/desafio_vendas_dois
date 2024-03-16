package services;
import entities.Sale;
import java.util.*;
import java.util.stream.Collectors;



public class SaleService {

    private List<Sale> saleList;

    private class SellerTotal {
        private String seller;
        private Double total;

        public SellerTotal(String seller, Double total) {
            this.seller = seller;
            this.total = total;
        }
        @Override
        public String toString() {
            String totalValue = String.format("%.2f", total);
            return seller + " - R$ " + totalValue;
        }
    }


    public SaleService() {
        this.saleList = saleList = new ArrayList<>();
    }
    public void add(Sale sale) {
        saleList.add(sale);
    }
    private Set<String> getName() {
        return saleList.stream().map(x -> x.getSeller()).collect(Collectors.toSet());
    }
    public void summary() {
        List<SellerTotal> sellerTotal = getName().stream()
                .map((x) -> new SellerTotal(
                        x.toString()
                        ,
                        total(x)
                )).collect(Collectors.toList());

        System.out.println();
        System.out.println("Total de vendas por vendedor: ");
        sellerTotal.forEach(System.out::println);
    }

    private double total(String x) {
        return saleList.stream()
                .filter(s -> s.getSeller().equals(x))
                .map(s -> s.getTotal())
                .reduce(0.0, (a, b) -> a + b);
    }

}
