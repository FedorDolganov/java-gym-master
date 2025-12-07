package ru.yandex.practicum.gym;


import java.util.*;

public class Timetable {

    private HashMap<DayOfWeek, TreeMap<TimeOfDay, TrainingSession>> timetable = new HashMap<>();

    private TreeSet<CounterOfTrainings> counterOfCoach = new TreeSet<>();

    public void addNewTrainingSession(TrainingSession trainingSession) {
        TimeOfDay timeOfDay = trainingSession.getTimeOfDay();

        Coach coach = trainingSession.getCoach();

        TreeMap<TimeOfDay, TrainingSession> treeMap = timetable.getOrDefault(trainingSession.getDayOfWeek(), new TreeMap<>());

        treeMap.put(timeOfDay, trainingSession);

        treeMap.put(timeOfDay, treeMap.get(timeOfDay));

        int coachCount = 1;

        for (CounterOfTrainings counterOfTrainings:counterOfCoach) {
            if (counterOfTrainings.getCoach().equals(trainingSession.getCoach())) {
                coachCount = counterOfTrainings.getTraingsCount();
                counterOfCoach.remove(counterOfTrainings);
            }
        }

        counterOfCoach.add(new CounterOfTrainings(coachCount, coach));

        timetable.put(trainingSession.getDayOfWeek(), treeMap);
    }

    public LinkedList<TrainingSession> getTrainingSessionsForDay(DayOfWeek dayOfWeek) {
        if (timetable.containsKey(dayOfWeek)) {
            return new LinkedList<>(timetable.get(dayOfWeek).values());
        } else {
            return null;
        }
    }

    public TrainingSession getTrainingSessionsForDayAndTime(DayOfWeek dayOfWeek, TimeOfDay timeOfDay) {
        return timetable.get(dayOfWeek).getOrDefault(timeOfDay, null);
    }

    public int getCountByCoaches(Coach coach) {
        for (CounterOfTrainings counterOfTrainings:counterOfCoach) {
            if (counterOfTrainings.getCoach().equals(coach)) {
                return counterOfTrainings.getTraingsCount();
            }
        }
        return -1;
    }
}
