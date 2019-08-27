package dukepkg;

public class Task {
    private boolean done_ = false;

    private String task_ = "";

    Task(String task) {
        task_ = task;
    }

    public boolean isDone() {
        return done_;
    }

    public String getTask() {
        return task_;
    }

    public void markDone() {
        done_ = true;
    }

    private String getSymbol() {
        if(isDone()) {
            return "\u2713";
        } else {
            return "\u2718";
        }
    }

    @Override
    public String toString() {
        return "[" + getSymbol() + "] " + task_;
    }
    public boolean equals(Object obj) {
        Task o = (Task) obj;
        return (this.task_.equals(o.task_));
    }
}
