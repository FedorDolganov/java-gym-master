package ru.yandex.practicum.gym;

public class CounterOfTrainings implements Comparable<CounterOfTrainings> {

    private int traingsCount;
    private Coach coach;


    public CounterOfTrainings(int traingsCount, Coach coach) {
        this.traingsCount = traingsCount;
        this.coach = coach;
    }

    @Override
    public int compareTo(CounterOfTrainings o) {
        return this.traingsCount - o.getTraingsCount();
    }

    public int getTraingsCount() {
        return traingsCount;
    }

    public Coach getCoach() {
        return coach;
    }

}
