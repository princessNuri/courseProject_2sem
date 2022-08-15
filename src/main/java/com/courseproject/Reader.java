package com.courseproject;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
    static boolean accountParser(String role, String login, String pass) {
        try {
//            System.out.println(Reader.class.getResource("accounts.txt").getPath());
            String path = Reader.class.getResource("accounts.txt").getPath();
            File file = new File(path);
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file);

            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку
            String line = reader.readLine();
            while (line != null) {
                String[] acc = new String[3];
                int i = 0;
                for (String word : line.split(" ")) {
                    acc[i] = word;
                    i++;
                }
                if (acc[0].equals(role)) {
                    if (acc[1].equals(login)) {
                        if (acc[2].equals(pass)) {
                            return true;
                        }
                    }
                }
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(accountParser("worker", "work", "111"));
        System.out.println(accountParser("worker", "222", "111"));


    }
}
