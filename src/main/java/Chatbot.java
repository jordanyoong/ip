import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import java.util.ArrayList;

public class Chatbot {

    public static void chat() throws DukeException {
        String start = "Hello! I'm Duke \nWhat can I do for you?";
        System.out.println(start);
        Scanner sc = new Scanner(System.in);
        String[] x = new String[]{"todo", "deadline", "event"};
        List<String> all = Arrays.asList(x);
        ArrayList<Task> arr = new ArrayList<>();
        while (true) {
            String line = sc.nextLine();
            Scanner scan = new Scanner(line);
            String in = scan.next();
            if (in.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (in.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int j = 1; j <= arr.size(); j++) {
                    String output = j + ". " + arr.get(j - 1);
                    System.out.println(output);
                }
            } else if (in.equals("done")) {
                System.out.println("Nice! I've marked this task as done:");
                int ind = scan.nextInt() - 1;
                arr.get(ind).markAsDone();
                System.out.println(arr.get(ind));
            } else if (in.equals("delete")) {
                System.out.println("Noted. I've removed this task:");
                int ind = scan.nextInt() - 1;
                System.out.println(arr.get(ind));
                arr.remove(ind);
                System.out.println("Now you have " + arr.size() + " tasks in the list.");
            } else if (in.equals("todo")) {
                if (!scan.hasNext()) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                String desc = scan.nextLine().substring(1);
                System.out.println("Got it. I've added this task:");
                Todo curr = new Todo(desc);
                System.out.println(curr);
                arr.add(curr);
                System.out.println("Now you have " + arr.size() + " tasks in the list.");
            } else if (in.equals("deadline")) {
                if (!scan.hasNext()) {
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
                String desc = scan.next();
                System.out.println("Got it. I've added this task:");
                String word = scan.next();
                while (word.indexOf('/') < 0) {
                    desc += " ";
                    desc += word;
                    word = scan.next();
                }
                String dtString = (scan.nextLine()).substring(1);
                Scanner dT = new Scanner(dtString);
                LocalDate date = LocalDate.parse(dT.next());
                Deadline curr;
                if (dT.hasNext()) {
                    String duration = dT.next();
                    curr = new Deadline(desc, date, duration);
                } else {
                    curr = new Deadline(desc, date);
                }
                System.out.println(curr);
                arr.add(curr);
                System.out.println("Now you have " + arr.size() + " tasks in the list.");
            } else if (in.equals("event")) {
                if (!scan.hasNext()) {
                    throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                }
                String desc = scan.next();
                System.out.println("Got it. I've added this task:");
                String word = scan.next();
                while (word.indexOf('/') < 0) {
                    desc += " ";
                    desc += word;
                    word = scan.next();
                }
                String dtString = (scan.nextLine()).substring(1);
                Scanner dT = new Scanner(dtString);
                LocalDate date = LocalDate.parse(dT.next());
                Event curr;
                if (dT.hasNext()) {
                    String duration = dT.next();
                    curr = new Event(desc, date, duration);
                } else {
                    curr = new Event(desc, date);
                }
                System.out.println(curr);
                arr.add(curr);
                System.out.println("Now you have " + arr.size() + " tasks in the list.");
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}
