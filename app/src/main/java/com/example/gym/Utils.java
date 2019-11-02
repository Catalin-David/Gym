package com.example.gym;

import java.util.ArrayList;

public class Utils {
    private static ArrayList<GymExercise> allTrainings;
    private static ArrayList<Plan> userPlans;

    public Utils() {
    }

    public static void initializeAll(){

        allTrainings = new ArrayList<>();

        if(null == userPlans){
            userPlans = new ArrayList<>();
        }

        GymExercise bench = new GymExercise();
        bench.setName("Bench press");
        bench.setShortDesc("Tags: Chest Shoulders");
        bench.setLongDesc("The bench press is an upper-body weight training exercise in which the trainee presses a weight upwards while lying on a weight training bench.");
        bench.setImageUrl("https://cdn2.coachmag.co.uk/sites/coachmag/files/2017/05/bench-press_0.jpg");
        allTrainings.add(bench);

        GymExercise pullUp = new GymExercise();
        pullUp.setName("Pull-up");
        pullUp.setShortDesc("Tags: Back Shoulders");
        pullUp.setLongDesc("The pull-up is a closed-chain movement where the body is suspended by the hands and pulls up.");
        pullUp.setImageUrl("https://cdn.aosom.ca/media/catalog/product/cache/7a422fd5b0e1a2dcadc9e1cf25a4d584/1/_/1_39_11.jpg");
        allTrainings.add(pullUp);

        GymExercise bicepCurl = new GymExercise();
        bicepCurl.setName("Biceps curl");
        bicepCurl.setShortDesc("Tags: Biceps, Arms");
        bicepCurl.setLongDesc("The term \"biceps curl\" may refer to any of a number of weight training exercises that target the biceps brachii muscle.");
        bicepCurl.setImageUrl("https://www.exercise.co.uk/wp/wp-content/uploads/2019/02/Proper-Form-Series-Bicep-Curls-Main.jpg");
        allTrainings.add(bicepCurl);

        GymExercise squat = new GymExercise();
        squat.setName("Squat");
        squat.setShortDesc("Tags: Legs, Back");
        squat.setLongDesc("A squat is a strength exercise in which the trainee lowers their hips from a standing position and then stands back up.");
        squat.setImageUrl("https://www.mensjournal.com/wp-content/uploads/2018/02/squats-mens-journal-february-2018.jpg");
        allTrainings.add(squat);

    }

    public static ArrayList<GymExercise> getAllTrainings() {
        return allTrainings;
    }

    public static void setAllTrainings(ArrayList<GymExercise> allTrainings) {
        Utils.allTrainings = allTrainings;
    }

    public static ArrayList<Plan> getUserPlans() {
        return userPlans;
    }

    public static void setUserPlans(ArrayList<Plan> userPlans) {
        Utils.userPlans = userPlans;
    }

    public static boolean addToUserPlan(Plan plan){
        return userPlans.add(plan);
    }

    public static boolean removeUserPlan(Plan plan){
        return userPlans.remove(plan);
    }
}
