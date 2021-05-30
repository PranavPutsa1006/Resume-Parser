import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class Main {
	static List<String> emails = new ArrayList<String>();
	static List<String> links = new ArrayList<String>();
	//1. B.sc Computer Science Resume
	//2. Resume
	static String resumeFile = "D://enginnering/temp/temp2/bluetooth/sachin verma & savita/sachin/amrita/Sem 6/compiler design(15CSE311)/resume/resume text/testing/Indu-Varshini-Jayapal_CB.EN.U4CSE17121.pdf";
	static String sample = "D://enginnering/temp/temp2/bluetooth/sachin verma & savita/sachin/amrita/Sem 6/compiler design(15CSE311)/resume/resume text/sample.txt";

	public static void email(String s) {
		Matcher m = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(s);
		while (m.find()) {
			emails.add(m.group());
		}
	}

	public static void getProjects(Map<Integer, String> m) throws FileNotFoundException {
		List<String> proj = new ArrayList<String>();
		for (String s : m.values()) {
			Scanner sc = new Scanner(s.toLowerCase());
			String s2 = sc.nextLine();
			String temp;
			if (s2.length() > 21)
				temp = s2.substring(0, 21);
			else {
				temp = s2;
			}

			if (temp.indexOf("projects") != -1) {
				Scanner i = new Scanner(s);
				String new_s = "";
				i.nextLine();

				while (i.hasNext()) {
					String temp2 = i.nextLine();
					if (temp2.indexOf('?') != -1 && (temp2.indexOf("Page") == -1 || temp2.indexOf("page") == -1)) {
						new_s += temp2.substring(temp2.indexOf('?') + 1) + "\n";
					} else {
						if (temp2.indexOf("Page") == -1 && temp2.indexOf("page") == -1)
							new_s += temp2 + "\n";
					}
				}
				i.close();

				proj.add(new_s);
			}
			sc.close();
		}

		System.out.println(proj);

	}

	public static void getExp() throws IOException {
		File f1 = new File(sample);
		String[] words = null;
		FileReader fr = new FileReader(f1);
		BufferedReader br = new BufferedReader(fr);
		Scanner sc = new Scanner(br);
		String s;
		String input1 = "experience";

		File file = new File(sample);
		words = null;
		fr = new FileReader(f1);
		br = new BufferedReader(fr);

		sc.close();

		sc = new Scanner(br);

		while ((s = br.readLine()) != null) {
			String t = s.toLowerCase();
			words = t.split(" ");

			for (String word : words) {
				if (word.equals(input1 + ":") || word.equals("&" + input1)) {
					System.out.println("\nWork Experience: \n");
					String p;
					while (!((p = sc.nextLine()).equals("-------------"))) {
						System.out.println(p);
					}
				}
			}
		}

		sc.close();

		file = new File(resumeFile);
		PDDocument document = PDDocument.load(file);
		PDFTextStripper pdfStripper = new PDFTextStripper();
		String text = pdfStripper.getText(document);
		sc = new Scanner(text);
		words = null;
		try {
			while ((s = sc.nextLine()) != null) {
				String t = s.toLowerCase();
				words = t.split(" ");
				for (String word : words) {
					if (word.matches("[6-9][0-9]{9}")) {
						System.out.println("\nMobile number: " + word);
					}
				}
				if (t.contains("name") && !t.contains("father") && !t.contains("mother")) {
					System.out.println();
					System.out.println(s);
				}
			}
			sc.close();
		} catch (Exception e) {

		}
	}

	public static void activities() throws IOException {
		System.out.println("\nActivities:");
		File f1 = new File(sample);
		String[] words = null;
		FileReader fr = new FileReader(f1);
		BufferedReader br = new BufferedReader(fr);
		Scanner sc = new Scanner(br);
		String s;
		String input = "activities";
		while ((s = br.readLine()) != null) {
			String t = s.toLowerCase();
			words = t.split(" ");
			for (String word : words) {
				if (word.equals(input) || word.equals(input + ":") || word.equals(input + "-")
						|| word.equals(input + "&") || word.equals("&" + input)) {
					String p;
					try {
						while (!((p = sc.nextLine()).equals("-------------"))) {
							System.out.println(p);
						}
					} catch (Exception e) {
						break;
					}
				}
			}
		}
		sc.close();
	}

	public static HashSet<String> getHeadings() throws FileNotFoundException {
		File file1 = new File(
				"D://enginnering/temp/temp2/bluetooth/sachin verma & savita/sachin/amrita/Sem 6/compiler design(15CSE311)/resume/resume text/Headings.txt");
		Scanner sc = new Scanner(file1);
		HashSet<String> h = new HashSet<String>();
		while (sc.hasNextLine()) {
			String s = sc.nextLine();
			for (String i : s.split(" ")) {
				h.add(i.toLowerCase());
			}
		}
		sc.close();
		return h;
	}

	public static List<String> getSkills(Map<Integer, String> m) throws FileNotFoundException {
		List<String> skills = new ArrayList<String>();
		for (String s : m.values()) {
			Scanner sc = new Scanner(s.toLowerCase());
			String s2 = sc.nextLine();
			String temp;
			if (s2.length() > 21)
				temp = s2.substring(0, 21);
			else {
				temp = s2;
			}

			if (temp.indexOf("skills") != -1) {
				Scanner i = new Scanner(s);
				String new_s = "";
				i.nextLine();

				while (i.hasNext()) {
					String temp2 = i.nextLine();
					if (temp2.indexOf('?') != -1 && (temp2.indexOf("Page") == -1 || temp2.indexOf("page") == -1)) {
						new_s += temp2.substring(temp2.indexOf('?') + 1) + "\n";
					} else {
						if (temp2.indexOf("Page") == -1 && temp2.indexOf("page") == -1)
							new_s += temp2 + "\n";
					}
				}
				i.close();

				skills.add(new_s);
			}
			sc.close();
		}

		return skills;

	}

	public static String getCategory(Map<Integer, String> m) throws FileNotFoundException {
		List<String> skills = getSkills(m);
		File file1 = new File(
				"D://enginnering/temp/temp2/bluetooth/sachin verma & savita/sachin/amrita/Sem 6/compiler design(15CSE311)/resume/Category/Cybersecurity.txt");
		File file2 = new File(
				"D://enginnering/temp/temp2/bluetooth/sachin verma & savita/sachin/amrita/Sem 6/compiler design(15CSE311)/resume/Category/Web-Dev.txt");
		File file3 = new File(
				"D://enginnering/temp/temp2/bluetooth/sachin verma & savita/sachin/amrita/Sem 6/compiler design(15CSE311)/resume/Category/Machine-Learning.txt");
		File file4 = new File(
				"D://enginnering/temp/temp2/bluetooth/sachin verma & savita/sachin/amrita/Sem 6/compiler design(15CSE311)/resume/Category/Networking.txt");

		
		HashSet<String> s1 = new HashSet<String>();// cybersecurity
		HashSet<String> s2 = new HashSet<String>();// webdev
		HashSet<String> s3 = new HashSet<String>();// machine learning
		HashSet<String> s4 = new HashSet<String>();// Netwrking

		Scanner sc = new Scanner(file1);
		while (sc.hasNextLine()) {
			String s = sc.nextLine();
			String temp[] = s.split(" ");

			for (String t : temp) {
				s1.add(t);
			}
		}
		sc.close();
		
		sc = new Scanner(file2);
		while (sc.hasNextLine()) {
			String s = sc.nextLine();
			String temp[] = s.split(" ");

			for (String t : temp) {
				s2.add(t);
			}
		}
		sc.close();
		
		sc = new Scanner(file3);
		while (sc.hasNextLine()) {
			String s = sc.nextLine();

			String temp[] = s.split(" ");

			for (String t : temp) {
				s3.add(t);
			}
		}

		sc.close();
		
		sc = new Scanner(file4);
		while (sc.hasNextLine()) {
			String s = sc.nextLine();

			String temp[] = s.split(" ");

			for (String t : temp) {
				s4.add(t);
			}
		}
		sc.close();

		int cyber = 0, ml = 0, web = 0, net = 0;
		for (String s : s1) {
			String temp = "";
			if (skills.size() > 0)
			{
				temp = skills.get(0);
				temp=temp.toLowerCase();
			}

			if (temp.indexOf(s.toLowerCase()) != -1) {
				cyber++;
			}
		}

		for (String s : s2) {
			String temp = "";
			if (skills.size() > 0)
			{
				temp = skills.get(0);
				temp=temp.toLowerCase();
			}

			if (temp.indexOf(s.toLowerCase()) != -1) {
				web++;
			}
		}

		for (String s : s3) {
			String temp = "";
			if (skills.size() > 0)
			{
				temp = skills.get(0);
				temp=temp.toLowerCase();
			}

			if (temp.indexOf(s.toLowerCase()) != -1) {
				ml++;
			}
		}

		for (String s : s4) {
			String temp = "";
			if (skills.size() > 0)
			{
				temp = skills.get(0);
				temp=temp.toLowerCase();
			}

			if (temp.indexOf(s.toLowerCase()) != -1) {
				net++;
			}
		}
		
		if (ml >=cyber && ml >=web && ml >=net)
			return "machine-learning";
		if (cyber >=ml && cyber >=web && cyber >=net)
			return "cybersecurity";
		if (web >=cyber && web >=net && web >=ml)
			return "web-development";
		return "Networking";

	}

	public static void main(String[] args) throws IOException {

		HashSet<String> h = getHeadings();
		Map<Integer, String> content = new Hashtable<Integer, String>();

		int count = 1;

		File file = new File(resumeFile);
		PDDocument document = PDDocument.load(file);
		PDFTextStripper pdfStripper = new PDFTextStripper();
		String text = pdfStripper.getText(document);

		Scanner sc = new Scanner(text);
		char lastletter = '.';
		int flag = 0;
		while (sc.hasNextLine()) {
			String s = sc.nextLine(); // getHeadings(s);

			email(s);

			if (s.length() >= 1 && s.indexOf(' ') != -1) {
				String temp = s.substring(0, s.indexOf(' '));
				String temp2 = s.toLowerCase();
				if ((temp2.indexOf("skills") != -1) || ((lastletter == '.' || lastletter == '\n' || lastletter == ' ')
						&& temp.length() >= 1 && (temp.charAt(0) >= 'A' && temp.charAt(0) <= 'Z')
						&& h.contains(temp.toLowerCase()))) {

					count++;
					content.put(count, s);
					flag = 1;
				}
			}
			if (flag == 0) {
				content.replace(count, content.get(count) + '\n' + s);
			} else {
				flag = 0;
			}

			lastletter = s.charAt(s.length() - 1);
		}

		File f = new File(sample);
		PrintWriter out = new PrintWriter(f);

		for (String j : content.values()) {
			out.println("-------------");
			out.println(j);

		}
		out.close();
		sc.close();

		System.out.println("Emails:");
		for (String j : emails) {
			if (j.charAt(j.length() - 1) != '.') {

				System.out.println(j);
			} else {
				System.out.println(j);
				System.out.println(j.substring(0, j.length() - 1));
			}
		}

		System.out.println();
		System.out.println();
		System.out.println("Category: " + getCategory(content));
		System.out.println();
		System.out.println();
		System.out.println("Skills: \n" + getSkills(content));

		activities();
		System.out.println();
		System.out.println();
		System.out.println("Projects: \n");
		getProjects(content);

		getExp();

	}

}