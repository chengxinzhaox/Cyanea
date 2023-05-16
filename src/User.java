import java.io.FileNotFoundException;
import java.util.Objects;

public class User {
    final double HARD_VALUE = 1.0;
    final double NORMAL_VALUE = 0.6;
    final double EASY_VALUE = 0.2;

    double totalTime;
    double attentiveRatio;
    double tentacleFact;

    public User(double totalTime, Traffic_Analyse traffic_analyse, String mode) throws FileNotFoundException {
        this.totalTime = totalTime;
        traffic_analyse.analyse("../Cyanea/src/User_Traffic/Traffic");
        double attentive = (traffic_analyse.totalCount / totalTime)/2 + (traffic_analyse.totalDistractionCount/ totalTime)/2;
        if (Objects.equals(mode, "hard")) {
            attentiveRatio = attentive * HARD_VALUE;
        }
        else if (Objects.equals(mode, "normal")) {
            attentiveRatio = attentive * NORMAL_VALUE;
        }
        else if (Objects.equals(mode, "easy")) {
            attentiveRatio = attentive * EASY_VALUE;
        }

        double tentacle = 1 - attentiveRatio;

        switch (mode) {
            case "hard" -> tentacleFact = 0.5 - tentacle;
            case "normal" -> tentacleFact = 0.7 - tentacle;
            case "easy" -> tentacleFact = 1.0 - tentacle;
        }
    }

    public static void main(String[] args) {
        try {
            Traffic_Analyse traffic_analyse = new Traffic_Analyse();
            User user = new User(1000, traffic_analyse, "hard");
            System.out.println(user.attentiveRatio);
            System.out.println(user.tentacleFact);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
