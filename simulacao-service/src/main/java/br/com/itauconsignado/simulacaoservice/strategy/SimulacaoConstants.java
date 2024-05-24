package br.com.itauconsignado.simulacaoservice.strategy;


import java.math.BigDecimal;

public class SimulacaoConstants {
    public static final String CONVENIO_EP = "EP";
    public static final String CONVENIO_OP = "OP";
    public static final String CONVENIO_INSS = "INSS";

    public static final BigDecimal TAXA_CONVENIO_EP = BigDecimal.valueOf(2.6);
    public static final BigDecimal TAXA_CONVENIO_OP = BigDecimal.valueOf(2.2);
    public static final BigDecimal TAXA_CONVENIO_INSS = BigDecimal.valueOf(1.6);

}
