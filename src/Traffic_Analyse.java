import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Traffic_Analyse {
    int shortVideoCount;
    int innerDistractCount;
    int totalDistractionCount;
    int normalCount;
    int totalCount;

    public Traffic_Analyse() {
        shortVideoCount = 0;
        innerDistractCount = 0;
        normalCount = 0;
        totalCount = 0;
    }

    public void analyse(String path) throws FileNotFoundException {

        File TikTok = new File("../Cyanea/src/Filter/TikTok");
        Scanner shortVideoScanner = new Scanner(TikTok);
        String[] shortVideoUrls = shortVideoScanner.useDelimiter("\\A").next().split("\n");

        File innerFile = new File("../Cyanea/src/Filter/Inner");
        Scanner longVideoScanner = new Scanner(innerFile);
        String[] innerDisUrls = longVideoScanner.useDelimiter("\\A").next().split("\n");

        File trafficFile = new File(path);
        Scanner trafficScanner = new Scanner(trafficFile);
        while (trafficScanner.hasNextLine()) {
            String line = trafficScanner.nextLine();
            String[] parts = line.split(" ");

            String url = parts[1];
            double duration = Double.parseDouble(parts[2]);
            if (isInArray(url, shortVideoUrls)) {
                shortVideoCount += duration;
                totalCount += duration;
                totalDistractionCount += duration;
            }
            else if (isInArray(url, innerDisUrls)) {
                innerDistractCount += duration;
                totalCount += duration;
                totalDistractionCount += duration;
            }
            else {
                normalCount += duration;
                totalCount += duration;
            }
        }

    }

    private boolean isInArray(String url, String[] shortVideoUrls) {
        for (String shortVideoUrl : shortVideoUrls) {
            if (url.equals(shortVideoUrl)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Traffic_Analyse traffic_analyse = new Traffic_Analyse();
        try {
            traffic_analyse.analyse("../Cyanea/src/User_Traffic/Traffic");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("ShortTraffic：" + traffic_analyse.shortVideoCount);
        System.out.println("InnerTraffic：" + traffic_analyse.innerDistractCount);
        System.out.println("NormalTraffic：" + traffic_analyse.normalCount);
    }
}
