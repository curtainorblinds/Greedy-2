import java.util.HashMap;
import java.util.Map;

/**
 * Leetcode 621. Task Scheduler
 * Link: https://leetcode.com/problems/task-scheduler/description/
 */
public class TaskScheduler {
    /**
     * Greedy solution where we define our solution around bottle tasks which are repeating the most (maxFreq). This
     * inevitably creates slots between them based on their maxFreq count and given cooling period n in between. From this,
     * we compute how many availableSlots are created to accommodate other pending tasks. If availableSlots are more than
     * pending tasks, these are idle slots needed in the solution, if they are less they can be fit in between slots (problem
     * is asking to cool down for at least n slots, so more can be used if needed)
     *
     * TC: O(n)
     * SC: O(1) -> only 26 lower case alphabets
     */
    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> freqMap = new HashMap<>();
        int maxFreq = 0;
        for (char task: tasks) {
            freqMap.put(task, freqMap.getOrDefault(task, 0) + 1);
            maxFreq = Math.max(maxFreq, freqMap.get(task));
        }

        int tasksWithMaxFreq = 0;
        for (char task: freqMap.keySet()) {
            if (freqMap.get(task) == maxFreq) {
                tasksWithMaxFreq++;
            }
        }

        int partitions = maxFreq - 1;
        int availableSlots = partitions * (n - (tasksWithMaxFreq - 1));
        int pendingtasks = tasks.length - maxFreq * tasksWithMaxFreq;
        int extraIdleSlots = Math.max(0, availableSlots - pendingtasks);

        return tasks.length + extraIdleSlots;
    }
}
