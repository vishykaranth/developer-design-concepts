package com.javabydeveloper.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.javabydeveloper.model.User;

public class CSVUtils {

	private static final String USER_CSV_FILE_PATH = "src/main/resources/data/userData.csv";

	public static CSVParser getParser(String csvFile) throws FileNotFoundException, IOException {

		Reader reader = Files.newBufferedReader(Paths.get(csvFile));

		return new CSVParser(reader,
				CSVFormat.RFC4180.withFirstRecordAsHeader().withIgnoreHeaderCase().withRecordSeparator(',').withTrim());
	}

	public static List<User> getAllUsersData() {
		List<User> users = null;
		CSVParser parser = null;
		try {
			parser = getParser(USER_CSV_FILE_PATH);
			users = new ArrayList<User>();

			for (CSVRecord row : parser)
				users.add(mapUser(row));

		} catch (IOException | ParseException e) {
           e.printStackTrace();
		}

		return users;
	}

	private static User mapUser(CSVRecord row) throws ParseException {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

		User user = new User();
		// string to datauser.setId(Long.valueOf(row.get("ID")));
		user.setUserName(row.get("USERNAME"));
		user.setPassword(row.get("PASSWORD"));
		user.setCreatedTime(new Date());
		//user.setUpdatedTime(f.parse(row.get("UPDATEDTIME"))); //handle null
		user.setUserType(UserType.valueOf(row.get("USERTYPE")));
		user.setDateofBirth(f.parse(row.get("DATEOFBIRTH")));

		return user;
	}

	public static void main(String[] args) {
		System.out.println(CSVUtils.getAllUsersData());
	}
}
