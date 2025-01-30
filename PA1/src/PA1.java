import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class PA1 {

    public static void main(String[] args) {
        
        try {
            // Read the input file and process the data line by line
            File input = new File("/Users/ricoporter/Workspace/PA1/PA1/src/input.txt");
            Scanner reader = new Scanner(input);
            List<String> outList = new ArrayList<>();
            Map<String, List<Double>> infoMap = new HashMap<>();
            String songName;
            double rating = 0;
            
            // Read the file line by line
            while (reader.hasNextLine()) {
                // Process each line
                String[] info = reader.nextLine().split(",");
                songName = info[0];
                //userName = info[1];
                rating = Integer.parseInt(info[2]);

                //updating old entry
                if(infoMap.get(songName) != null) {
                    infoMap.get(songName).add(rating);
                }
                else {
                    //Add new entry
                    List<Double> ratings = new ArrayList<>();
                    ratings.add(rating);
                    infoMap.put(songName, ratings);
                }
            }
            
            // Calculate the mean & standard deviation for each song
            for(String song: infoMap.keySet()){
                double mean = findMean(infoMap.get(song).size(), infoMap.get(song));
                double stdDev = findStandardDeviation(infoMap.get(song).size(), mean, infoMap.get(song));
                outList.add(song + "," + infoMap.get(song).size() + "," + mean + "," + stdDev);
            }
            
            // Output to a file
            writeOutfile(outList);

            // Close the scanner
            reader.close();
        } catch (IOException e) {
            // Handle file reading errors
            e.printStackTrace();
        }
    }

    // Calculate the standard deviation for each song using the mean and the list of ratings
    private static double findStandardDeviation(double numberOfRatings, double mean, List<Double> ratings){
        double sum = 0;
        for(Double rating: ratings){
            sum += Math.pow(rating - mean, 2);
        }

        return Math.sqrt(sum / numberOfRatings);
    }

    private static void writeOutfile(List<String> outList){
        // Output to a file
        File output = new File("/Users/ricoporter/Workspace/PA1/PA1/src/output.txt");
        try (java.io.PrintWriter writer = new java.io.PrintWriter(output)) {
            writer.println("song,number of ratings,mean,standard deviation");
            for (String line : outList) {
                writer.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double findMean(double numberOfRatings, List<Double> allRatings){
        Double sum = 0.0;
        for(Double rating: allRatings) sum += rating;

        return sum/numberOfRatings;
    }

    
}
