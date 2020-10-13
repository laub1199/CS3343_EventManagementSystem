package EventManagementSystem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String [] args) throws CloneNotSupportedException {
        Scanner in = new Scanner(System.in);

        System.out.println("Welcome to the Event Management System");

        boolean isValidSystemDate = false;
        do {
            try {
                System.out.println("Please input today date (e.g. 01-jan-2020).");
                SystemDate.createTheInstance(in.nextLine()); // get command from user
                isValidSystemDate = true;
            }
            catch (ExDateFormatYear | ExDateFormatMonth | ExDateFormatDay | ExInvalidDate e) {
                System.out.println(e.getMessage());
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid Date Format: dd-mmm-yyyy");
            }
        } while (!isValidSystemDate);

        String command;
        boolean exit = false;
        do {
            try {
            System.out.println("Please enter your command: ");

            command = in.nextLine(); // get command from user
            String[] cmdParts = command.split(" "); // split the command by ' '

            if (cmdParts[0].equals("exit")) {
                exit = true;
            }
            else if (cmdParts[0].equals("create")) {
                if (cmdParts[1].equals("student")) {
                    (new CmdCreateStudent()).execute(cmdParts);
                }
                else if (cmdParts[1].equals("group")) {
                    (new CmdCreateGroup()).execute(cmdParts);
                }
                else if (cmdParts[1].equals("event")) {
                    (new CmdCreateEvent()).execute(cmdParts);
                }
                else {
                    throw new ExWrongCommand();
                }
            }
            else if (cmdParts[0].equals("delete")) {
                if (cmdParts[1].equals("student")) {
                    (new CmdDeleteStudent()).execute(cmdParts);
                }
                else if (cmdParts[1].equals("group")) {
                    (new CmdDeleteGroup()).execute(cmdParts);
                }
                else if (cmdParts[1].equals("event")) {
                    (new CmdDeleteEvent()).execute(cmdParts);
                }
                else {
                    throw new ExWrongCommand();
                }
            }
            else if (cmdParts[0].equals("list")) {
                if (cmdParts[1].equals("student")) {
                    (new CmdListStudent()).execute(cmdParts);
                }
                else if (cmdParts[1].equals("event")) {
                    (new CmdListEvent()).execute(cmdParts);
                }
                else if (cmdParts[1].equals("group")) {
                    (new CmdListGroup()).execute(cmdParts);
                }
                else if (cmdParts[1].equals("studentJoinedEvent")) {
                    (new CmdListStudentJoinedEvent()).execute(cmdParts);
                }
                else if (cmdParts[1].equals("studentJoinedGroup")) {
                    (new CmdListStudentJoinedGroup()).execute(cmdParts);
                }
                else {
                    throw new ExWrongCommand();
                }
            }
            else if (cmdParts[0].equals("studentJoin")) {
                if (cmdParts[1].equals("group")) {
                    (new CmdStudentJoinGroup()).execute(cmdParts);
                }
                else if (cmdParts[1].equals("event")) {
                    (new CmdStudentJoinEvent()).execute(cmdParts);
                }
                else {
                    throw new ExWrongCommand();
                }
            }
            else if (cmdParts[0].equals("groupJoin")) {
                if (cmdParts[1].equals("event")) {
                    (new CmdGroupJoin()).execute(cmdParts);
                }
                else {
                    throw new ExWrongCommand();
                }
            }
            else if (cmdParts[0].equals("search")) {
                if (cmdParts[1].equals("student")) {
                    (new CmdSearchStudent()).execute(cmdParts);
                }
                else if (cmdParts[1].equals("group")) {
                    (new CmdSearchGroup()).execute(cmdParts);
                }
                else if (cmdParts[1].equals("event")) {
                    (new CmdSearchEvent()).execute(cmdParts);
                }
                else {
                    throw new ExWrongCommand();
                }
            }
            else if (cmdParts[0].equals("studentQuit")) {
                (new CmdStudentQuit()).execute(cmdParts);
            }
            else if (cmdParts[0].equals("groupQuit")) {
                (new CmdGroupQuit()).execute(cmdParts);
            }
            else if (cmdParts[0].equals("help")) {
            	
            } 
            else if(cmdParts[0].equals("recommend")){ 
            	(new CmdRecommend()).execute(cmdParts); 
            }     
            else
                throw new ExWrongCommand();
            }
            catch (ExWrongCommand e) {
                System.out.println("Unknown command - ignored!");
            }
        } while (!exit);
    }
}
