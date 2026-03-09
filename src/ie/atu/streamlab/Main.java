package ie.atu.streamlab;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, StreamLab!");

        List<Integer> numbs = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        numbs.stream().filter(n -> n%2 != 0).forEach(n -> System.out.println(n));

        List<String> fruits = Arrays.asList("apple", "banana", "grape", "kiwi", "orange", "mango");

        List<String> filteredfruits = fruits.stream().filter(f -> f.length()>4).sorted().map(String::toUpperCase).collect(Collectors.toList());

        filteredfruits.forEach(f-> System.out.println(f));

        List<Integer> number = Arrays.asList(1,2,3,4,5);

        number.stream().map(NumberUtils::doubleNumber).forEach(System.out::println);

        List<Integer> evenNumb = Arrays.asList(2,4,6,8,10);

        int product = evenNumb.stream().reduce(1, (a,b) ->a*b);
        int min = evenNumb.stream().reduce(Integer.MAX_VALUE, (c,d) ->Math.min(c, d));
        System.out.println("Product: " + product+"\nMin: "+min);

        String inputPath = "resources/input.txt";

        long lineAmount;
        long lineAverage;


        try(Stream<String> lines = Files.lines(Paths.get(inputPath))){
            lineAmount = lines.filter(line -> line.contains("never")).map(String::trim).count();
            
            
        } catch (IOException e){
            System.out.println("Error Message: "+e.getLocalizedMessage()+"\nValues have been set to 0");
            lineAmount = 0;
            lineAverage = 0;
        }
        try(Stream<String> lines = Files.lines(Paths.get(inputPath))){
            OptionalDouble avg = lines.mapToInt(String::length).average();
            lineAverage = (long) avg.orElse(0.0);
        } catch (IOException e){
            System.out.println("Error Message: "+e.getLocalizedMessage()+"\nValues have been set to 0");
            lineAmount = 0;
            lineAverage = 0;
        }
        
        System.out.println("Amount of lines containing the word 'Never': "+lineAmount+"\nAverage line length: "+lineAverage);
    }
}