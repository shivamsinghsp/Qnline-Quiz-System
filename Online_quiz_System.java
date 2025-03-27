import java.util.*;

class Question {
    String question;
    String[] options;
    int correctAnswer; // Index of the correct answer

    public Question(String question, String[] options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
}

public class Online_quiz_System {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Question> questions = new ArrayList<>();
    private static int score = 0;
    private static int timer = 10; // Time limit per question in seconds

    public static void main(String[] args) {
        loadQuestions();
        System.out.println("Welcome to the Online Quiz System!");
        
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        
        System.out.println("\nHello, " + name + "! Let's start the quiz.\n");
        
        startQuiz();
        showResult(name);
    }

    private static void loadQuestions() {
        questions.add(new Question("What is the capital of France?", new String[]{"1) Berlin", "2) Madrid", "3) Paris", "4) Rome"}, 2));
        questions.add(new Question("Which data type is used to store decimal numbers in Java?", new String[]{"1) int", "2) float", "3) char", "4) boolean"}, 1));
        questions.add(new Question("Who invented Java?", new String[]{"1) Dennis Ritchie", "2) Bjarne Stroustrup", "3) James Gosling", "4) Guido van Rossum"}, 2));
        questions.add(new Question("Which keyword is used to inherit a class in Java?", new String[]{"1) extends", "2) implements", "3) inherits", "4) override"}, 0));
    }

    private static void startQuiz() {
        for (Question q : questions) {
            System.out.println("\n" + q.question);
            for (String option : q.options) {
                System.out.println(option);
            }

            System.out.print("Enter your answer (1-4): ");
            long startTime = System.currentTimeMillis();
            int userAnswer = getUserInputWithTimer();
            long endTime = System.currentTimeMillis();

            if (userAnswer == -1) {
                System.out.println("Time's up! Moving to the next question.");
            } else if (userAnswer == q.correctAnswer + 1) {
                System.out.println("✅ Correct!");
                score++;
            } else {
                System.out.println("❌ Incorrect! The correct answer was " + (q.correctAnswer + 1));
            }

            System.out.println("Time taken: " + ((endTime - startTime) / 1000) + " seconds.");
        }
    }

    private static int getUserInputWithTimer() {
        long startTime = System.currentTimeMillis();
        while ((System.currentTimeMillis() - startTime) < (timer * 1000)) {
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            }
        }
        return -1; // Return -1 if time runs out
    }

    private static void showResult(String name) {
        System.out.println("\n🎉 Quiz Completed! 🎉");
        System.out.println("Player: " + name);
        System.out.println("Your Score: " + score + "/" + questions.size());
        
        if (score == questions.size()) {
            System.out.println("🏆 Excellent! You got all answers right!");
        } else if (score >= questions.size() / 2) {
            System.out.println("😊 Good job! Keep practicing.");
        } else {
            System.out.println("😢 Better luck next time. Keep learning!");
        }
    }
}
