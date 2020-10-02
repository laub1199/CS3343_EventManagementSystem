package EventManagementSystem;

import java.util.*;

public class Main {
    public static void main(String [] args) throws CloneNotSupportedException {
        Scanner in = new Scanner(System.in);

        System.out.println("Welcome to the Event Management System");

        try {
            String command;
            boolean exit = false;
            do {
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
                }
                else if (cmdParts[0].equals("studentJoin")) {
                    if (cmdParts[1].equals("group")) {
                        (new CmdStudentJoinGroup()).execute(cmdParts);
                    }
                    else if (cmdParts[1].equals("event")) {
                        (new CmdStudentJoinEvent()).execute(cmdParts);
                    }
                }
                else if (cmdParts[0].equals("groupJoin")) {
                    if (cmdParts[1].equals("event")) {
                        (new CmdGroupJoin()).execute(cmdParts);
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
                }
                else if (cmdParts[0].equals("help")) {

                }
                else
                    throw new ExWrongCommand();

            } while (!exit);
        }
        catch (ExWrongCommand e) {
            System.out.println("Unknown command - ignored!");
        }


    }
}
