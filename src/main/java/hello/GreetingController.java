package hello;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }

    @CrossOrigin
    @RequestMapping("/netflix")
    public List<String> netflix(@RequestParam(value = "searchString", defaultValue = "") String searchString) throws IOException {
        List<String> titles = new ArrayList<>();
        File file = new File("netflix-titles.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.toLowerCase().contains(searchString.toLowerCase())) {
                    titles.add(line);
                }
            }
        }

        return titles;
    }

    @CrossOrigin
    @RequestMapping("/hulu")
    public List<String> hulu(@RequestParam(value = "searchString", defaultValue = "") String searchString) throws IOException {
        List<String> titles = new ArrayList<>();
        File file = new File("hulu-titles.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.toLowerCase().contains(searchString.toLowerCase())) {
                    titles.add(line);
                }
            }
        }

        return titles;
    }
}
