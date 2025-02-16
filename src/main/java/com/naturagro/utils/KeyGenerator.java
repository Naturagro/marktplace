package com.naturagro.utils;

import java.time.Instant;
import java.util.Random;

public class KeyGenerator {

    public static String generateKey() {

        final Random random = new Random();

        // Pega o timestamp atual
        long timestamp = Instant.now().toEpochMilli();

        // Usa os últimos 3 dígitos do timestamp
        long timeComponent = timestamp % 1000;

        // Gera um número aleatório de 3 dígitos
        int randomComponent = random.nextInt(900) + 100; // Gera um número entre 100 e 999

        // Combina os componentes para formar uma chave única de 6 dígitos
        return String.format("%03d%03d", timeComponent, randomComponent);

    }
}
