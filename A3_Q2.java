// -------------------------------------------------------
// Assignment 3
// For COMP 248 Section 2222-R – Fall 2022
// --------------------------------------------------------
// Importing the scanner so that the user can input using his keyboard after opening the scanner
import java.util.Scanner;

public class A3_Q2 {

	public static void main(String[] args) {
//		********************************************************************
//		This program allows the to go through all options as much as he/she
//		likes (infinite loop). This programs lets the user to enter 
//		candidate names and their ID which then let's him enter the votes
//		and display the results in a ranking System. In any moment, the user
//		can display the candidates or add new candidates.
//		********************************************************************
		Scanner keyIn = new Scanner(System.in);
		System.out.println("Welcome to the Simple Electronic Voting System (SEVS):");
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++" + "\n");
		System.out.println("Please enter a String collection of electoral candidates below:");
		// the user enters the candidates
		String id_name = keyIn.nextLine();
		//trims down any spaces at the beginning and the end of the string
		id_name = id_name.trim();
		id_name = id_name.toUpperCase();
		//this array contains all candidates after splitting by ";"
		String[] candidates = id_name.split(";");
		// I replace the comma by >> so it can be displayed properly for future displays
		//also replaced any consecutive white spaces by a single space.
		for (int i = 0; i <= (candidates.length - 1); i++) {
			candidates[i] = candidates[i].trim();
			candidates[i] = candidates[i].replace(",", " >> ");
			candidates[i] = candidates[i].replaceAll("\\s+", " ");
		}
		//declaring and initialing variables
		//creating an array with the same length as the candidates array for the votes
		int[] vote = new int[candidates.length];
		int[] temp_vote;
		System.out.println("********************************\n" 
				+ "| Code >> Description          |\n"
				+ "|  1   >> Display candidates   |\n" 
				+ "|  2   >> Vote a candidate     |\n"
				+ "|  3   >> Add new candidate(s) |\n" 
				+ "|  4   >> Display results      |\n"
				+ "|  0   >> End SEVS             |\n" 
				+ "********************************\n");
		System.out.print("Enter a code, from the aformentioned, that corresponds to your task: ");
		//this while loop allow the user to loop through all cases in the switch without ending the program (if case 0 is not used)
		boolean isboolean=true;
		while (isboolean) {
			//code here is a String because it'll him to write whatever and the program still runs, but will keep asking him for the code
			//until the code is one of the cases, hence the default
			String code = keyIn.nextLine();
			switch (code) {
			case "1": {
				System.out.println("****************************************\n"
						+ "| ID >> Candidate's Name               |\n" 
						+ "****************************************");
				//loops through all candidates and if it happens that candidates had | from previous cases, it'll be replaced by >> for printing purposes
				for (int i = 0; i <= (candidates.length - 1); i++) {
					candidates[i] = candidates[i].replace("|", ">>");
					System.out.print("| " + candidates[i]);
					//prints the white spaces and the | after each candidate's name
					for (int n = 3 + candidates[i].length(); n < 40; n++)
						System.out.print(" ");
					System.out.print("|\n");
				}
				System.out.println("****************************************");
				System.out.print("\nKindly continue by entering a valid code, from the aformentioned, that corresponds to your task: ");
				break;
			}
			case "2": {
				System.out.print("\nPlease enter the ID of the candidate you wish to vote for: ");
				String vote_For  = keyIn.nextLine();
				for (int i = 0; i <= (candidates.length - 1); i++) {
					//declares and initialize the array name type string every time it loops
					//let's the program go through all candidates to see if id equals the one the user entered
					//here the size is 0 because I want to let position 0 be the id of the candidate that iteration is on
					String[] name = new String[0];
//					System.out.println(candidates[i]);
					// splits the candidate id and name
					if (candidates[i].contains("|"))
						name = candidates[i].split(" | ");
					if (candidates[i].contains(">>"))
						name = candidates[i].split(" >> ");
					//if id of the candidate is equal to the value the user entered then it prompts
					if (vote_For.equals(name[0])) {
						vote[i]++;
						System.out.println("Your ballot has been successfully casted for: " + name[1] + " bearing Candidate ID: " + vote_For);
					}

				}
//				check why it displays the below twice when uncommented
				System.out.print("\nKindly continue by entering a valid code, from the aformentioned, that corresponds to your task: ");
				break;
			}
			case "3": {
				System.out.println("\nPlease enter a String collection of the NEW electoral candidates below:");
				String new_id_name = keyIn.nextLine();
				//if the last character of the initial string input by the user is not ;, i want to add it so my other cases methods work
				if (id_name.charAt(id_name.length() - 1) != ';')
					new_id_name = ";" + new_id_name;
				new_id_name = new_id_name.toUpperCase();
				//making a single big string of all candidates
				String new_id_name2 = id_name.concat(new_id_name);
				//making the new string equal to the last one so everything in the old string becomes the new one so the initial variable
				//id_name is used when looping through the cases
				id_name = new_id_name2;
				//candidates now has all the new users and the old ones in order
				candidates = id_name.split(";");
				//trimming down and removing white spaces of the new string
				for (int i = 0; i <= (candidates.length - 1); i++) {
					candidates[i] = candidates[i].trim();
					candidates[i] = candidates[i].replace(",", " >> ");
					candidates[i] = candidates[i].replaceAll("\\s+", " ");
				}
				// initializing the temp_vote variable to the length of the new candidates array length
				temp_vote = new int[candidates.length];
				//every votes entered by the user is not lost when new candidates are added, they'll transfer over
				for (int i = 0; i < vote.length; i++) {
					temp_vote[i] = vote[i];
					//anything transfered to the temp_vote goes back to the old vote variable to keep the same order and votes when looping through the cases
					vote = temp_vote;
				}
				System.out.println("Successfully added a NEW set of electoral candidates to the Simple Electronic Voting System (SEVS).");
				System.out.print("\nKindly continue by entering a valid code, from the aformentioned, that corresponds to your task: ");
				break;
			}
			case "4": {
				System.out.println("******************************************************************\n"
						+ "| Position | Votes/Ballots | ID | Candidate's Name               |\n"
						+ "******************************************************************");
				//declaring variables needed to sort the array descending
				int max, temp;
				String temp2;
				//these nested for loops allow me to sort the arrays of votes descending
				//at the same sorting the candidates name and id with the position of their votes in the vote array
				for (int index = 0; index < vote.length - 1; index++) {
					max = index;
					for (int scan = index + 1; scan < vote.length; scan++)
						if (vote[scan] > vote[max])
							max = scan;
					temp = vote[max];
					temp2 = candidates[max];
					vote[max] = vote[index];
					candidates[max] = candidates[index];
					vote[index] = temp;
					candidates[index] = temp2;
				}
				//replacing >> by | for displaying purposes
				for (int r = 0; r <= (candidates.length - 1); r++) 
					candidates[r] = candidates[r].replace(">>", "|");
				//for loop allows me to rank the candidates by position starting at 1, if the a user has the same votes as the previous one
				//position is not incremented, if not the same then it is incremented
				for (int s = 0, position = 1; s < candidates.length; s++) {
					if (s != 0)
						if (vote[s] != vote[s - 1])
							position++;
					System.out.print("|        " + (position) + " |             " + vote[s] + " | " + candidates[s]);
					for (int n = 30 + candidates[s].length(); n < 66; n++) {
						System.out.print(" ");
					}
					System.out.print("|\n");
				}

				System.out.println("******************************************************************");
				System.out.print("\nKindly continue by entering a valid code, from the aformentioned, that corresponds to your task: ");
				break;
			}
			case "0": {
				System.out.print("\nThank you for using our Simple Electronic Voting System (SEVS).");
				//when isboolean is false we get out of the loop
				isboolean = false;
				break;
			}
			//if the users enters something different than the cases, then it will ask him to enter a valid code
			default:{
				System.out.print("\nKindly continue by entering a valid code, from the aformentioned, that corresponds to your task: ");
			}
			}
		}
	keyIn.close();
	}
}
