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
                System.out.print(e.getMessage());
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid Date Format: dd-mmm-yyyy");
            }
        } while (!isValidSystemDate);

        String command;
        String returnstr = "";
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
                	returnstr = (new CmdCreateStudent()).execute(cmdParts);
                }
                else if (cmdParts[1].equals("group")) {
                	returnstr = (new CmdCreateGroup()).execute(cmdParts);
                }
                else if (cmdParts[1].equals("event")) {
                	returnstr = (new CmdCreateEvent()).execute(cmdParts);
                }
                else {
                    throw new ExWrongCommand();
                }
            }
            else if (cmdParts[0].equals("delete")) {
                if (cmdParts[1].equals("student")) {
                	returnstr = (new CmdDeleteStudent()).execute(cmdParts);
                }
                else if (cmdParts[1].equals("group")) {
                    (new CmdDeleteGroup()).execute(cmdParts);
                }
                else if (cmdParts[1].equals("event")) {
                	returnstr = (new CmdDeleteEvent()).execute(cmdParts);
                }
                else {
                    throw new ExWrongCommand();
                }
            }
            else if (cmdParts[0].equals("list")) {
                if (cmdParts[1].equals("student")) {
                	returnstr = (new CmdListStudent()).execute(cmdParts);
                }
                else if (cmdParts[1].equals("event")) {
                	returnstr = (new CmdListEvent()).execute(cmdParts);
                }
                else if (cmdParts[1].equals("group")) {
                	returnstr = (new CmdListGroup()).execute(cmdParts);
                }
                else if (cmdParts[1].equals("studentJoinedEvent")) {
                	returnstr = (new CmdListStudentJoinedEvent()).execute(cmdParts);
                }
                else if (cmdParts[1].equals("studentJoinedGroup")) {
                	returnstr = (new CmdListStudentJoinedGroup()).execute(cmdParts);
                }
                else {
                    throw new ExWrongCommand();
                }
            }
            else if (cmdParts[0].equals("studentJoin")) {
                if (cmdParts[1].equals("group")) {
                	returnstr = (new CmdStudentJoinGroup()).execute(cmdParts);
                }
                else if (cmdParts[1].equals("event")) {
                	returnstr = (new CmdStudentJoinEvent()).execute(cmdParts);
                }
                else {
                    throw new ExWrongCommand();
                }
            }
            else if (cmdParts[0].equals("groupJoin")) {
                if (cmdParts[1].equals("event")) {
                	returnstr = (new CmdGroupJoin()).execute(cmdParts);
                }
                else {
                    throw new ExWrongCommand();
                }
            }
            else if (cmdParts[0].equals("search")) {
                if (cmdParts[1].equals("student")) {
                	returnstr = (new CmdSearchStudent()).execute(cmdParts);
                }
                else if (cmdParts[1].equals("group")) {
                	returnstr = (new CmdSearchGroup()).execute(cmdParts);
                }
                else if (cmdParts[1].equals("event")) {
                	returnstr = (new CmdSearchEvent()).execute(cmdParts);
                }
                else {
                    throw new ExWrongCommand();
                }
            }
            else if (cmdParts[0].equals("studentQuit")) {
            	returnstr = (new CmdStudentQuit()).execute(cmdParts);
            }
            else if (cmdParts[0].equals("groupQuit")) {
            	returnstr = (new CmdGroupQuit()).execute(cmdParts);
            }
            else if (cmdParts[0].equals("help")) {
            	
            } 
            else if(cmdParts[0].equals("recommend")){ 
            	returnstr = (new CmdRecommend()).execute(cmdParts); 
            }     
            else
                throw new ExWrongCommand();
            }
            catch (ExWrongCommand e) {
            	returnstr = "Unknown command - ignored!";
            } 
            finally {
            	System.out.println(returnstr);
            }
        } while (!exit);
    }
}
