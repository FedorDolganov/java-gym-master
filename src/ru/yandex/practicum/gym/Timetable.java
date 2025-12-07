package ru.yandex.practicum.gym;


import java.util.*;

public class Timetable {

    private Map<DayOfWeek, TreeMap<TimeOfDay, List<TrainingSession>>> timetable = new HashMap<>();

    private TreeSet<CounterOfTrainings> counterOfCoach = new TreeSet<>();

    public void addNewTrainingSession(TrainingSession trainingSession) {
        TimeOfDay timeOfDay = trainingSession.getTimeOfDay();

        Coach coach = trainingSession.getCoach();

        TreeMap<TimeOfDay, List<TrainingSession>> treeMap = timetable.getOrDefault(trainingSession.getDayOfWeek(), new TreeMap<>());

        List<TrainingSession> trainingSessionList = treeMap.getOrDefault(timeOfDay, new ArrayList<>());

        trainingSessionList.add(trainingSession);

        treeMap.put(timeOfDay, trainingSessionList);

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

    public TreeMap<TimeOfDay, List<TrainingSession>> getTrainingSessionsForDay(DayOfWeek dayOfWeek) {
        if (timetable.containsKey(dayOfWeek)) {
            return timetable.get(dayOfWeek);
        } else {
            return new TreeMap<>();
        }
    }

    public List<TrainingSession> getTrainingSessionsForDayAndTime(DayOfWeek dayOfWeek, TimeOfDay timeOfDay) {
        return timetable.get(dayOfWeek).getOrDefault(timeOfDay, new ArrayList<>());
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
