package comeaa.workout;

import java.io.Serializable;

/**
 * Created by ErinA on 7/26/2017.
 */

public class ExerciseItem  implements Serializable{
    String name;
    String sets;
    String reps;
    String weight;
    String max;
    /* 0 -&gt; checkbox disable, 1 -&gt; checkbox enable */

    ExerciseItem(String name, String sets, String reps, String weight, String max) {
        this.name = name;
        this.sets = sets;
        this.weight = weight;
        this.reps = reps;
        this.max = max;
    }
    public String getName(){
        return this.name;
    }
    public String getSets(){
        return this.sets;
    }
    public String getReps(){
        return this.reps;
    }
    public String getWeight() { return this.weight;}
    public String getMax() { return this.max;}

    public void setReps(String x){this.reps = x;}
    public void setSets(String x){this.sets =x;}
    public void setWeight(String x){this.weight = x;}
    public void setMax(String x){this.max = x;}
    public void setName(String s) { this.name = s;}


    @Override
    public boolean equals(Object myObject){
        boolean isEqual = false;
        if(myObject != null && myObject instanceof ExerciseItem){
            ExerciseItem myItem = (ExerciseItem) myObject;
            if (name.equals(myItem.name)) {
                isEqual = true;
            }
        }

        return isEqual;
    }




}

