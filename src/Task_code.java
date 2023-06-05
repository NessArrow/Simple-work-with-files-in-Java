import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task_code {
    //разобраться, как делать запись в файл + чтение, поиск, выдача поискового запроса
    /*
    Парсинг файла по аргументам класса
    Сортировка файла
    Запись в файл в нужное место
    Удалить строку по найденному слову и вывести эту строку в консоль
    Заполнение листа типа Human
    поиск по регулярному выражению
    чтобы поиск шел по определенному столбцу и весь этот столбец записывать в массив

    Метод, который делает запись в файл из строки и из массива
    Метод, который открывает файл, делает туда запись из консоли
    Получение информации из файла, вывод в консоль
    Построчное считывание файла, в строке несколько аргументов,
        разделенных табуляцией, разбить по строкам и по аргументам,
        в массив, класс, лист
    Поиск в файле нужного слова
    Аргументы: айди, имя, статус, дата
    поиск и вывод в консоль по любому из аргументов списка совпадений
    Сортировка данных файла по любому аргументу
    Запись в файл в нужное место
    Удалить строку по найденному слову и вывести эту строку в консоль
    Заполнение листа типа Human
    поиск по регулярному выражению
    чтобы поиск шел по определенному столбцу и весь этот столбец записывать в массив
     */
    public static void main(String[] args) {
        String fileName = "src/peoples.txt";
//        Scanner scanner = new Scanner(System.in);
//        String line = scanner.nextLine();
//        writeInFileFromString(line, fileName);
//        System.out.println(findWord(fileName, line));
//        System.out.println(findLineByWord(line, fileName));
//        listOfMatches(line, fileName).forEach(System.out::println);
        List <Human> humans = humansFromFileToList(fileName);
        humans.forEach(System.out::println);

    }
    public static void ofw(String line) {
        for (int i = 0; i < line.length(); i++) {
            System.out.print(line.indexOf(i) + " ");
            System.out.println((int) line.indexOf(i));
        }
    }
    public static List <Human> humansFromFileToList(String fileName){
        List <Human> humans = new ArrayList<>();

            try (FileReader fileReader = new FileReader(fileName)){
            int c;
            StringBuilder tmp = new StringBuilder();
            while ((c = fileReader.read()) != -1) {
//                System.out.printf("%c %d\n", c, c);
                if (c == 10) {
                    if (!tmp.isEmpty()) humans.add(humanFromString(tmp.toString()));
                    tmp = new StringBuilder();
                } else {
                    tmp.append((char) c);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return humans;
    }
    public static Human humanFromString (String line) {
//        int tab = 9;
//        int space = 32;
//        System.out.println(line);
//        List <String> partsOfHuman = new ArrayList<>();
        String[] partsOfHuman = new String[4];
//        Pattern pattern = Pattern.compile("\\w+");
//        Matcher matcher = pattern.matcher(line);
//        while (matcher.find()) {
//            partsOfHuman.add(line.substring(matcher.start(), matcher.end()));
//        }
//        partsOfHuman.forEach(System.out::println);
        partsOfHuman = line.split("[\\s\t]+");
//        int j = 0;
//        char letter = 0;
//        String tmp = "";
//        for (int i = 0; i < line.length(); i++) {
//            letter = line.charAt(i);
//            if (letter == 9 || letter == 32) {
//                if (line.indexOf(i-1) == letter) {
//                    tmp = "";
//                } else {
//                    partsOfHuman[j++] = tmp;
//                    tmp = "";
//                }
//            } else {
//                tmp += (char)letter;
//            }
//            if (i == line.length() - 1) {
//                partsOfHuman[j] = tmp;
//            }
//        }
//        Arrays.stream(partsOfHuman).forEach(System.out::println);
//        Human human = new Human(partsOfHuman.get(0), Integer.parseInt(partsOfHuman.get(1)), partsOfHuman.get(2), strToProfession(partsOfHuman.get(3)));
//        return human;
        return new Human(partsOfHuman[0], Integer.parseInt(partsOfHuman[1]),partsOfHuman[2],strToProfession(partsOfHuman[3]));
    }
    public static Profession strToProfession(String line) {
        Profession profession = null;
        switch (line) {
            case "doctor" -> profession = Profession.DOCTOR;
            case "engineer" -> profession = Profession.ENGINEER;
            case "teacher" -> profession = Profession.TEACHER;
            case "policeman" -> profession = Profession.POLICEMAN;
            case "lawyer" -> profession = Profession.LAWYER;
            case "actor" -> profession = Profession.ACTOR;
            default -> profession = Profession.NOPE;
        }
        return profession;
    }
    public static void writeInFileFromString(String line, String fileName) {
        try (FileWriter writer = new FileWriter(fileName,true)){
            writer.write(line);
            writer.append('\n');
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void readFile(String fileName) {
        try (FileReader fileReader = new FileReader(fileName)){
            int c;
            while ((c = fileReader.read()) != -1) {
                System.out.print((char) c);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public static boolean findWord(String fileName, String word) {
        boolean result = false;
        try (FileReader fileReader = new FileReader(fileName)){
            int c;
            StringBuilder tmp = new StringBuilder();
            while ((c = fileReader.read()) != -1) {
                if (c != 9 && c != 10) {
                    tmp.append((char) c);
                } else {
                    if (tmp.toString().equals(word)){
                        result = true;
                        break;
                    }
                    tmp = new StringBuilder();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
    public static String findLineByWord(String word, String fileName) {
        String result = "Not found";
        try (FileReader fileReader = new FileReader(fileName)){
            int c;
            StringBuilder tmp = new StringBuilder();
            while ((c = fileReader.read()) != -1) {
                if (c != 10) {
                    tmp.append((char)c);
                } else {
                    if (tmp.indexOf(word) != -1) {
                        result = tmp.toString();
                        break;
                    }
                    tmp = new StringBuilder();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
    public static List <String> listOfMatches (String word, String fileName) {
        List <String> result = new ArrayList<>();

        try (FileReader fileReader = new FileReader(fileName)){
            int c;
            StringBuilder tmp = new StringBuilder();
            while ((c = fileReader.read()) != -1) {
                if (c != 10) {
                    tmp.append((char)c);
                } else {
                    if (tmp.indexOf(word) != -1) {
                        result.add(tmp.toString());
                    }
                    tmp = new StringBuilder();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }
    //метод считывает из файла и построчно записывает в лист
}
