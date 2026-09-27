package pe.edu.cibertec.appgrupo1consumidor.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FibonacciService {
    private final Map<Integer, Long> cache = new HashMap<>();

    public long fibonacci(int n) {

        if (n <= 1) return n;
        if (cache.containsKey(n)) return cache.get(n);

        long result = fibonacci(n - 1) + fibonacci(n - 2);

        cache.put(n, result);

        return result;
    }

    public List<Long> calculateSequence(List<Integer> positions) {
        return positions.stream()
                .map(this::fibonacci)
                .collect(Collectors.toList());
    }
}
